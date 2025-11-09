package com.jerry.mekanism_extras.common.tile.transmitter;

import com.jerry.mekanism_extras.api.IExtraAlloyInteraction;
import com.jerry.mekanism_extras.api.tier.AdvancedTier;
import com.jerry.mekanism_extras.api.tier.ExtraAlloyTier;
import com.jerry.mekanism_extras.common.capabilities.ExtraCapabilities;
import com.jerry.mekanism_extras.common.content.network.transmitter.IExtraUpgradeableTransmitter;

import mekanism.api.providers.IBlockProvider;
import mekanism.common.Mekanism;
import mekanism.common.advancements.MekanismCriteriaTriggers;
import mekanism.common.capabilities.proxy.ProxyConfigurable;
import mekanism.common.capabilities.resolver.BasicCapabilityResolver;
import mekanism.common.content.network.transmitter.BufferedTransmitter;
import mekanism.common.content.network.transmitter.Transmitter;
import mekanism.common.lib.transmitter.DynamicBufferedNetwork;
import mekanism.common.lib.transmitter.DynamicNetwork;
import mekanism.common.tile.transmitter.TileEntityTransmitter;
import mekanism.common.upgrade.transmitter.TransmitterUpgradeData;
import mekanism.common.util.WorldUtils;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public abstract class ExtraTileEntityTransmitter extends TileEntityTransmitter implements ProxyConfigurable.ISidedConfigurable, IExtraAlloyInteraction {

    public ExtraTileEntityTransmitter(IBlockProvider blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state);
        addCapabilityResolver(BasicCapabilityResolver.constant(ExtraCapabilities.EXTRA_ALLOY_INTERACTION, this));
    }

    public static void extraTickServer(Level level, BlockPos blockPos, BlockState blockState, ExtraTileEntityLogisticalTransporter extraTileEntityLogisticalTransporter) {
        extraTileEntityLogisticalTransporter.onUpdateServer();
    }

    public void onExtraAlloyInteraction(Player player, ItemStack stack, @NotNull ExtraAlloyTier tier) {
        if (getLevel() != null && getTransmitter().hasTransmitterNetwork()) {
            DynamicNetwork<?, ?, ?> transmitterNetwork = getTransmitter().getTransmitterNetwork();
            List<Transmitter<?, ?, ?>> list = new ArrayList<>(transmitterNetwork.getTransmitters());
            list.sort((o1, o2) -> {
                if (o1 != null && o2 != null) {
                    return Double.compare(o1.getTilePos().distSqr(worldPosition), o2.getTilePos().distSqr(worldPosition));
                }
                return 0;
            });
            boolean sharesSet = false;
            int upgraded = 0;
            for (Transmitter<?, ?, ?> transmitter : list) {
                if (transmitter instanceof IExtraUpgradeableTransmitter<?> upgradeableTransmitter && upgradeableTransmitter.canUpgrade(tier)) {
                    ExtraTileEntityTransmitter transmitterTile = (ExtraTileEntityTransmitter) transmitter.getTransmitterTile();
                    BlockState state = transmitterTile.getBlockState();
                    BlockState upgradeState = transmitterTile.upgradeResult(state, tier.getAdvanceTier());
                    if (state == upgradeState) {
                        // Skip if it would not actually upgrade anything
                        continue;
                    }
                    if (!sharesSet) {
                        if (transmitterNetwork instanceof DynamicBufferedNetwork dynamicNetwork) {
                            // Ensure we save the shares to the tiles so that they can properly take them, and they
                            // don't get voided
                            dynamicNetwork.validateSaveShares((BufferedTransmitter<?, ?, ?, ?>) transmitter);
                        }
                        sharesSet = true;
                    }
                    transmitter.startUpgrading();
                    TransmitterUpgradeData upgradeData = upgradeableTransmitter.getUpgradeData();
                    BlockPos transmitterPos = transmitter.getTilePos();
                    Level transmitterWorld = transmitter.getTileWorld();
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
                // Invalidate the network so that it properly has new references to everything
                transmitterNetwork.invalidate(null);
                if (!player.isCreative()) {
                    stack.shrink(1);
                }
                if (player instanceof ServerPlayer serverPlayer) {
                    MekanismCriteriaTriggers.ALLOY_UPGRADE.trigger(serverPlayer);
                }
            }
        }
    }

    private <DATA extends TransmitterUpgradeData> void transferUpgradeData(IExtraUpgradeableTransmitter<DATA> upgradeableTransmitter, TransmitterUpgradeData data) {
        if (upgradeableTransmitter.dataTypeMatches(data)) {
            upgradeableTransmitter.parseUpgradeData((DATA) data);
        } else {
            Mekanism.logger.warn("Unhandled upgrade data.", new IllegalStateException());
        }
    }

    @NotNull
    protected BlockState upgradeResult(@NotNull BlockState current, @NotNull AdvancedTier tier) {
        return current;
    }
}
