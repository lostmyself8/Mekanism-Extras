package com.jerry.mekextras.common.item;

import com.jerry.mekextras.common.registry.ExtraBlocks;
import com.jerry.mekextras.common.tile.transmitter.ExtraTileEntityTransmitter;
import com.jerry.mekextras.common.util.IExtraUpgradeableTransmitter;
import mekanism.api.IAlloyInteraction;
import mekanism.api.tier.BaseTier;
import mekanism.common.Mekanism;
import mekanism.common.advancements.MekanismCriteriaTriggers;
import mekanism.common.block.states.BlockStateHelper;
import mekanism.common.capabilities.Capabilities;
import mekanism.common.config.MekanismConfig;
import mekanism.common.content.network.transmitter.BufferedTransmitter;
import mekanism.common.content.network.transmitter.IUpgradeableTransmitter;
import mekanism.common.content.network.transmitter.Transmitter;
import mekanism.common.lib.transmitter.DynamicBufferedNetwork;
import mekanism.common.lib.transmitter.DynamicNetwork;
import mekanism.common.tile.transmitter.*;
import mekanism.common.upgrade.transmitter.TransmitterUpgradeData;
import mekanism.common.util.WorldUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ExtraItemAlloyRadiance extends Item {
    public ExtraItemAlloyRadiance(Properties properties) {
        super(properties);
    }
    @NotNull
    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        if (player != null && MekanismConfig.general.transmitterAlloyUpgrade.get()) {
            Level world = context.getLevel();
            BlockPos pos = context.getClickedPos();
            IAlloyInteraction alloyInteraction = WorldUtils.getCapability(world, Capabilities.ALLOY_INTERACTION, pos, context.getClickedFace());

            if (alloyInteraction != null && world.getBlockEntity(pos) instanceof TileEntityTransmitter) {
                if (!world.isClientSide) {
                    this.onExtraAlloyInteraction(player,context.getItemInHand(),world,pos,(TileEntityTransmitter) world.getBlockEntity(pos));
                }
                return InteractionResult.sidedSuccess(world.isClientSide);
            }
        }
        return InteractionResult.PASS;
    }
    public void onExtraAlloyInteraction(Player player, ItemStack stack,Level level,BlockPos worldPosition,TileEntityTransmitter tileEntityTransmitter) {
        if (level != null && tileEntityTransmitter.getTransmitter().hasTransmitterNetwork()) {
            DynamicNetwork<?, ?, ?> transmitterNetwork = tileEntityTransmitter.getTransmitter().getTransmitterNetwork();
            List<Transmitter<?, ?, ?>> list = new ArrayList<>(transmitterNetwork.getTransmitters());
            list.sort((o1, o2) -> {
                if (o1 != null && o2 != null) {
                    return Double.compare(o1.getBlockPos().distSqr(worldPosition), o2.getBlockPos().distSqr(worldPosition));
                }
                return 0;
            });
            boolean sharesSet = false;
            int upgraded = 0;
            for (Transmitter<?, ?, ?> transmitter : list) {
                if (transmitter instanceof IUpgradeableTransmitter<?> upgradeableTransmitter && upgradeableTransmitter.getTier().getBaseTier() == BaseTier.ULTIMATE) {
                    TileEntityTransmitter transmitterTile = transmitter.getTransmitterTile();
                    BlockState state = transmitterTile.getBlockState();
                    Holder<Block> target = getiBlockProvider(transmitterTile);
                    BlockState upgradeState= BlockStateHelper.copyStateData(state, target);
                    if (state == upgradeState) {
                        //Skip if it would not actually upgrade anything
                        continue;
                    }
                    if (!sharesSet) {
                        if (transmitterNetwork instanceof DynamicBufferedNetwork dynamicNetwork) {
                            //Ensure we save the shares to the tiles so that they can properly take them, and they don't get voided
                            dynamicNetwork.validateSaveShares((BufferedTransmitter<?, ?, ?, ?>) transmitter);
                        }
                        sharesSet = true;
                    }
                    transmitter.startUpgrading();
                    TransmitterUpgradeData upgradeData = upgradeableTransmitter.getUpgradeData();
                    BlockPos transmitterPos = transmitter.getBlockPos();
                    Level transmitterWorld = transmitter.getLevel();
                    if (upgradeData == null) {
                        Mekanism.logger.warn("Got no upgrade data for transmitter at position: {} in {} but it said it would be able to provide some.",
                                transmitterPos, transmitterWorld);
                    } else {
                        transmitterWorld.setBlockAndUpdate(transmitterPos, upgradeState);
                        ExtraTileEntityTransmitter upgradedTile = WorldUtils.getTileEntity(ExtraTileEntityTransmitter.class, transmitterWorld, transmitterPos);
                        if (upgradedTile == null) {
                            Mekanism.logger.warn("Error upgrading transmitter at position: {} in {}.", transmitterPos, transmitterWorld);
                        } else {
                            Transmitter<?, ?, ?> upgradedTransmitter = upgradedTile.getTransmitter();
                            if (upgradedTransmitter instanceof IExtraUpgradeableTransmitter) {
                                transferUpgradeData((IExtraUpgradeableTransmitter<?>) upgradedTransmitter, upgradeData);
                            } else {
                                Mekanism.logger.warn("Unhandled upgrade data.", new IllegalStateException());
                            }
                            upgraded++;
                            if (upgraded == 8) {
                                break;
                            }
                        }
                    }
                }
            }
            if (upgraded > 0) {
                //Invalidate the network so that it properly has new references to everything
                transmitterNetwork.invalidate(null);
                if (!player.isCreative()) {
                    stack.shrink(1);
                }
                if (player instanceof ServerPlayer serverPlayer) {
                    MekanismCriteriaTriggers.ALLOY_UPGRADE.value().trigger(serverPlayer);
                }
            }
        }
    }

    private static Holder<Block> getiBlockProvider(TileEntityTransmitter transmitterTile) {
        return switch (transmitterTile) {
            case TileEntityPressurizedTube ignored ->
                   ExtraBlocks.ABSOLUTE_PRESSURIZED_TUBE;
            case TileEntityUniversalCable ignored ->
                    ExtraBlocks.ABSOLUTE_UNIVERSAL_CABLE;
            case TileEntityMechanicalPipe ignored ->
                    ExtraBlocks.ABSOLUTE_MECHANICAL_PIPE;
            case TileEntityThermodynamicConductor ignored ->
                    ExtraBlocks.ABSOLUTE_THERMODYNAMIC_CONDUCTOR;
            default -> ExtraBlocks.ABSOLUTE_LOGISTICAL_TRANSPORTER;
        };
    }

    private <DATA extends TransmitterUpgradeData> void transferUpgradeData(IExtraUpgradeableTransmitter<DATA> upgradeableTransmitter, TransmitterUpgradeData data) {
        if (upgradeableTransmitter.dataTypeMatches(data)) {
            upgradeableTransmitter.parseUpgradeData((DATA) data);
        } else {
            Mekanism.logger.warn("Unhandled upgrade data.", new IllegalStateException());
        }
    }
}
