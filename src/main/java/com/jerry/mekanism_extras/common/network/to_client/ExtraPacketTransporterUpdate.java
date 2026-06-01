package com.jerry.mekanism_extras.common.network.to_client;

import com.jerry.mekanism_extras.common.tile.transmitter.ExtraTileEntityLogisticalTransporterBase;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntSet;
import mekanism.common.content.network.transmitter.LogisticalTransporterBase;
import mekanism.common.content.transporter.TransporterStack;
import mekanism.common.network.BasePacketHandler;
import mekanism.common.network.IMekanismPacket;
import mekanism.common.util.WorldUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

public class ExtraPacketTransporterUpdate implements IMekanismPacket {

    private final boolean isSync;
    private final BlockPos pos;

    private LogisticalTransporterBase transporter;

    private int stackId;
    private TransporterStack stack;
    private Int2ObjectMap<TransporterStack> updates;
    private IntSet deletes;

    public ExtraPacketTransporterUpdate(LogisticalTransporterBase transporter, int stackId, TransporterStack stack) {
        this(transporter, true);
        this.stackId = stackId;
        this.stack = stack;
    }

    public ExtraPacketTransporterUpdate(LogisticalTransporterBase transporter, Int2ObjectMap<TransporterStack> updates, IntSet deletes) {
        this(transporter, false);
        this.updates = updates;
        this.deletes = deletes;
    }

    private ExtraPacketTransporterUpdate(LogisticalTransporterBase transporter, boolean isSync) {
        this.isSync = isSync;
        this.pos = transporter.getTilePos();
        this.transporter = transporter;
    }

    private ExtraPacketTransporterUpdate(BlockPos pos, boolean isSync) {
        this.pos = pos;
        this.isSync = isSync;
    }

    @Override
    public void handle(NetworkEvent.Context context) {
        ExtraTileEntityLogisticalTransporterBase tile = WorldUtils.getTileEntity(ExtraTileEntityLogisticalTransporterBase.class, Minecraft.getInstance().level, pos);
        if (tile != null) {
            LogisticalTransporterBase transporter = tile.getTransmitter();
            if (isSync) {
                transporter.addStack(stackId, stack);
            } else {
                for (Int2ObjectMap.Entry<TransporterStack> entry : updates.int2ObjectEntrySet()) {
                    transporter.addStack(entry.getIntKey(), entry.getValue());
                }
                for (int toDelete : deletes) {
                    transporter.deleteStack(toDelete);
                }
            }
        }
    }

    @Override
    public void encode(FriendlyByteBuf buffer) {
        buffer.writeBlockPos(pos);
        buffer.writeBoolean(isSync);
        if (isSync) {
            buffer.writeVarInt(stackId);
            writeClientSafeStack(stack, transporter, buffer);
        } else {
            BasePacketHandler.writeMap(buffer, updates, (key, value, buf) -> {
                buf.writeVarInt(key);
                writeClientSafeStack(value, transporter, buf);
            });
            buffer.writeCollection(deletes, FriendlyByteBuf::writeVarInt);
        }
    }

    private static void writeClientSafeStack(TransporterStack stack, LogisticalTransporterBase transporter, FriendlyByteBuf buffer) {
        ItemStack original = stack.itemStack;
        if (!original.isEmpty() && original.getCount() > original.getMaxStackSize()) {
            //对超过64数量的物品，采用64物品渲染，不然会不渲染
            stack.itemStack = original.copyWithCount(Math.min(original.getCount(), original.getMaxStackSize()));
            stack.write(transporter, buffer);
            stack.itemStack = original;
        } else {
            stack.write(transporter, buffer);
        }
    }

    public static ExtraPacketTransporterUpdate decode(FriendlyByteBuf buffer) {
        ExtraPacketTransporterUpdate packet = new ExtraPacketTransporterUpdate(buffer.readBlockPos(), buffer.readBoolean());
        if (packet.isSync) {
            packet.stackId = buffer.readVarInt();
            packet.stack = TransporterStack.readFromPacket(buffer);
        } else {
            packet.updates = BasePacketHandler.readMap(buffer, Int2ObjectOpenHashMap::new, FriendlyByteBuf::readVarInt, TransporterStack::readFromPacket);
            packet.deletes = buffer.readCollection(IntOpenHashSet::new, FriendlyByteBuf::readVarInt);
        }
        return packet;
    }
}
