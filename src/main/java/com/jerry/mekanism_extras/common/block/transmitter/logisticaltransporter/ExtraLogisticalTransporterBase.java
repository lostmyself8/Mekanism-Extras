package com.jerry.mekanism_extras.common.block.transmitter.logisticaltransporter;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntSet;
import mekanism.common.Mekanism;
import mekanism.common.content.network.InventoryNetwork;
import mekanism.common.content.network.transmitter.LogisticalTransporterBase;
import mekanism.common.content.transporter.TransporterManager;
import mekanism.common.content.transporter.TransporterStack;
import mekanism.common.lib.inventory.TransitRequest;
import mekanism.common.lib.transmitter.ConnectionType;
import mekanism.common.network.to_client.PacketTransporterUpdate;
import mekanism.common.tier.TransporterTier;
import mekanism.common.tile.transmitter.TileEntityTransmitter;
import mekanism.common.util.TransporterUtils;
import mekanism.common.util.WorldUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.IntConsumer;

public class ExtraLogisticalTransporterBase extends LogisticalTransporterBase {

    private static final Logger LOGGER = LogManager.getLogger(ExtraLogisticalTransporterBase.class);

    protected ExtraLogisticalTransporterBase(TileEntityTransmitter tile, TransporterTier tier) {
        super(tile, tier);
    }

    @Override
    public void onUpdateServer() {
        if (getTransmitterNetwork() != null) {
            //Pull items into the transporter
            if (delay > 0) {
                //If a delay has been imposed, wait a bit
                delay--;
            } else {
                //Reset delay to 3 ticks; if nothing is available to insert OR inserted, we'll try again in 3 ticks
                delay = 3;
                //Attempt to pull
                for (Direction side : getConnections(ConnectionType.PULL)) {
                    BlockEntity tile = WorldUtils.getTileEntity(getTileWorld(), getTilePos().relative(side));
                    if (tile != null) {
                        TransitRequest request = TransitRequest.anyItem(tile, side.getOpposite(), TPTier.getPullAmount(tier));
                        //There's a stack available to insert into the network...
                        if (!request.isEmpty()) {
                            TransitRequest.TransitResponse response = insert(tile, request, getColor(), true, 0);
                            if (response.isEmpty()) {
                                //Insert failed; increment the backoff and calculate delay. Note that we cap retries
                                // at a max of 40 ticks (2 seconds), which would be 4 consecutive retries
                                delayCount++;
                                delay = Math.min(40, (int) Math.exp(delayCount));
                            } else {
                                //If the insert succeeded, remove the inserted count and try again for another 10 ticks
                                response.useAll();
                                delay = 10;
                            }
                        }
                    }
                }
            }
            if (!transit.isEmpty()) {
                InventoryNetwork network = getTransmitterNetwork();
                //Update stack positions
                IntSet deletes = new IntOpenHashSet();
                //Note: Our calls to getTileEntity are not done with a chunkMap as we don't tend to have that many tiles we
                // are checking at once from here and given this gets called each tick, it would cause unnecessary garbage
                // collection to occur actually causing the tick time to go up slightly.
                for (Int2ObjectMap.Entry<TransporterStack> entry : transit.int2ObjectEntrySet()) {
                    int stackId = entry.getIntKey();
                    TransporterStack stack = entry.getValue();
                    if (!stack.initiatedPath) {
                        if (stack.itemStack.isEmpty() || !recalculate(stackId, stack, null)) {
                            deletes.add(stackId);
                            continue;
                        }
                    }

                    int prevProgress = stack.progress;
                    stack.progress += TPTier.getSpeed(tier);
                    try {
                        if (stack.progress >= 100) {
                            BlockPos prevSet = null;
                            if (stack.hasPath()) {
                                int currentIndex = stack.getPath().indexOf(getTilePos());
                                if (currentIndex == 0) { //Necessary for transition reasons, not sure why
                                    deletes.add(stackId);
                                    continue;
                                }
                                BlockPos next = stack.getPath().get(currentIndex - 1);
                                if (next != null) {
                                    if (!stack.isFinal(this)) {
                                        ExtraLogisticalTransporterBase transmitter = (ExtraLogisticalTransporterBase) network.getTransmitter(next);
                                        if (stack.canInsertToTransporter(transmitter, stack.getSide(this), this)) {
                                            transmitter.entityEntering(stack, stack.progress % 100);

                                            deletes.add(stackId);
                                            continue;
                                        }
                                        prevSet = next;
                                    } else if (stack.getPathType() != TransporterStack.Path.NONE) {
                                        BlockEntity tile = WorldUtils.getTileEntity(getTileWorld(), next);
                                        if (tile != null) {
                                            TransitRequest.TransitResponse response = TransitRequest.simple(stack.itemStack).addToInventory(tile, stack.getSide(this), 0,
                                                    stack.getPathType() == TransporterStack.Path.HOME);
                                            if (!response.isEmpty()) {
                                                //We were able to add at least part of the stack to the inventory
                                                ItemStack rejected = response.getRejected();
                                                if (rejected.isEmpty()) {
                                                    //Nothing was rejected (it was all accepted); remove the stack from the prediction
                                                    // tracker and schedule this stack for deletion. Continue the loop thereafter
                                                    TransporterManager.remove(getTileWorld(), stack);
                                                    deletes.add(stackId);
                                                    continue;
                                                }
                                                //Some portion of the stack got rejected; save the remainder and
                                                // let the recalculate below sort out what to do next
                                                stack.itemStack = rejected;
                                            }//else the entire stack got rejected (Note: we don't need to update the stack to point to itself)
                                            prevSet = next;
                                        }
                                    }
                                }
                            }
                            if (!recalculate(stackId, stack, prevSet)) {
                                deletes.add(stackId);
                            } else if (prevSet == null) {
                                stack.progress = 50;
                            } else {
                                stack.progress = 0;
                            }
                        } else if (prevProgress < 50 && stack.progress >= 50) {
                            boolean tryRecalculate;
                            if (stack.isFinal(this)) {
                                TransporterStack.Path pathType = stack.getPathType();
                                if (pathType == TransporterStack.Path.DEST || pathType == TransporterStack.Path.HOME) {
                                    Direction side = stack.getSide(this);
                                    ConnectionType connectionType = getConnectionType(side);
                                    tryRecalculate = connectionType != ConnectionType.NORMAL && connectionType != ConnectionType.PUSH ||
                                            !TransporterUtils.canInsert(WorldUtils.getTileEntity(getTileWorld(), stack.getDest()), stack.color, stack.itemStack,
                                                    side, pathType == TransporterStack.Path.HOME);
                                } else {
                                    tryRecalculate = pathType == TransporterStack.Path.NONE;
                                }
                            } else {
                                ExtraLogisticalTransporterBase nextTransmitter = (ExtraLogisticalTransporterBase) network.getTransmitter(stack.getNext(this));
                                if (nextTransmitter == null && stack.getPathType() == TransporterStack.Path.NONE && stack.getPath().size() == 2) {
                                    //If there is no next transmitter, and it was an idle path, assume that we are idling
                                    // in a single length transmitter, in which case we only recalculate it at 50 if it won't
                                    // be able to go into that connection type
                                    ConnectionType connectionType = getConnectionType(stack.getSide(this));
                                    tryRecalculate = connectionType != ConnectionType.NORMAL && connectionType != ConnectionType.PUSH;
                                } else {
                                    tryRecalculate = !stack.canInsertToTransporter(nextTransmitter, stack.getSide(this), this);
                                }
                            }
                            if (tryRecalculate && !recalculate(stackId, stack, null)) {
                                deletes.add(stackId);
                            }
                        }
                    } catch (ClassCastException e) {
                        LOGGER.error("ClassCastException error :: LogisticalTransporterBase cast to ExtraLogisticalTransporterBase -> error :: coordinates: " + this.getTilePos().toString());
                    }
                }

                if (!deletes.isEmpty() || !needsSync.isEmpty()) {
                    //Notify clients, so that we send the information before we start clearing our lists
                    Mekanism.packetHandler().sendToAllTracking(new PacketTransporterUpdate(this, needsSync, deletes), getTransmitterTile());
                    // Now remove any entries from transit that have been deleted
                    deletes.forEach((IntConsumer) (this::deleteStack));

                    // Clear the pending sync packets
                    needsSync.clear();

                    // Finally, mark chunk for save
                    getTransmitterTile().markForSave();
                }
            }
        }
    }

    private boolean recalculate(int stackId, TransporterStack stack, BlockPos from) {
        boolean noPath = stack.getPathType() == TransporterStack.Path.NONE || stack.recalculatePath(TransitRequest.simple(stack.itemStack), this, 0).isEmpty();
        if (noPath && !stack.calculateIdle(this)) {
            ExtraTransporterUtils.drop(this, stack);
            return false;
        }

        //Only add to needsSync if true is being returned; otherwise it gets added to deletes
        needsSync.put(stackId, stack);
        if (from != null) {
            stack.originalLocation = from;
        }
        return true;
    }

    private void entityEntering(TransporterStack stack, int progress) {
        // Update the progress of the stack and add it as something that's both
        // in transit and needs sync down to the client.
        //
        // This code used to generate a sync message at this point, but that was a LOT
        // of bandwidth in a busy server, so by adding to needsSync, the sync will happen
        // in a batch on a per-tick basis.
        int stackId = nextId++;
        stack.progress = progress;
        addStack(stackId, stack);
        needsSync.put(stackId, stack);

        // N.B. We are not marking the chunk as dirty here! I don't believe it's needed, since
        // the next tick will generate the necessary save and if we crash before the next tick,
        // it's unlikely the data will be saved anyway (since chunks aren't saved until the end of
        // a tick).
    }
}
