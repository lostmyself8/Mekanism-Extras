package com.jerry.mekanism_extras.common.item;

import com.jerry.mekanism_extras.common.content.network.transmitter.IExtraUpgradeableTransmitter;
import com.jerry.mekanism_extras.common.registry.ExtraBlock;
//import com.jerry.mekanism_extras.integration.Addons;
//import fr.iglee42.evolvedmekanism.tiers.EMBaseTier;
import mekanism.api.IAlloyInteraction;
import mekanism.api.providers.IBlockProvider;
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
import mekanism.common.util.CapabilityUtils;
import mekanism.common.util.WorldUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ItemAlloyRadiance extends Item {
    public ItemAlloyRadiance(Properties properties) {
        super(properties);
    }

    @NotNull
    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        if (player != null && MekanismConfig.general.transmitterAlloyUpgrade.get()) {
            Level world = context.getLevel();
            BlockPos pos = context.getClickedPos();
            BlockEntity tile = WorldUtils.getTileEntity(world, pos);
            LazyOptional<IAlloyInteraction> capability = CapabilityUtils.getCapability(tile, Capabilities.ALLOY_INTERACTION, context.getClickedFace());
            if (capability.isPresent() && world.getBlockEntity(pos) instanceof TileEntityTransmitter) {
                if (!world.isClientSide) {
                    onExtraAlloyInteraction(player, context.getItemInHand(), world, pos, (TileEntityTransmitter) world.getBlockEntity(pos));
                }
                return InteractionResult.sidedSuccess(world.isClientSide);
            }
        }
        return InteractionResult.PASS;
    }


    public void onExtraAlloyInteraction(Player player, ItemStack stack, Level level, BlockPos worldPosition, TileEntityTransmitter tileEntityTransmitter) {
        if (level != null && tileEntityTransmitter.getTransmitter().hasTransmitterNetwork()) {
            DynamicNetwork<?, ?, ?> transmitterNetwork = tileEntityTransmitter.getTransmitter().getTransmitterNetwork();
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
                if (transmitter instanceof IUpgradeableTransmitter<?> upgradeableTransmitter && upgradeableTransmitter.getTier().getBaseTier() == getBaseTier()) {
                    TileEntityTransmitter transmitterTile = transmitter.getTransmitterTile();
                    BlockState state = transmitterTile.getBlockState();
                    IBlockProvider target = getiBlockProvider(transmitterTile);
                    BlockState upgradeState = BlockStateHelper.copyStateData(state, target);
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
                    BlockPos transmitterPos = transmitter.getTilePos();
                    Level transmitterWorld = transmitter.getTileWorld();
                    if (upgradeData == null) {
                        Mekanism.logger.warn("Got no upgrade data for transmitter at position: {} in {} but it said it would be able to provide some.",
                                transmitterPos, transmitterWorld);
                    } else {
                        transmitterWorld.setBlockAndUpdate(transmitterPos, upgradeState);
                        TileEntityTransmitter upgradedTile = WorldUtils.getTileEntity(TileEntityTransmitter.class, transmitterWorld, transmitterPos);
                        if (upgradedTile == null) {
                            Mekanism.logger.warn("Error upgrading transmitter at position: {} in {}.", transmitterPos, transmitterWorld);
                        } else {
                            Transmitter<?, ?, ?> upgradedTransmitter = upgradedTile.getTransmitter();
                            if (upgradedTransmitter instanceof IUpgradeableTransmitter) {
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
                    MekanismCriteriaTriggers.ALLOY_UPGRADE.trigger(serverPlayer);
                }
            }
        }
    }

    private static IBlockProvider getiBlockProvider(TileEntityTransmitter transmitterTile) {
        if (transmitterTile instanceof TileEntityPressurizedTube) {
            return ExtraBlock.ABSOLUTE_PRESSURIZED_TUBE;
        } else if (transmitterTile instanceof TileEntityUniversalCable) {
            return ExtraBlock.ABSOLUTE_UNIVERSAL_CABLE;
        } else if (transmitterTile instanceof TileEntityMechanicalPipe) {
            return ExtraBlock.ABSOLUTE_MECHANICAL_PIPE;
        } else if (transmitterTile instanceof TileEntityThermodynamicConductor) {
            return ExtraBlock.ABSOLUTE_THERMODYNAMIC_CONDUCTOR;
        } else {
            // 默认情况或其他类型的处理
            return ExtraBlock.ABSOLUTE_LOGISTICAL_TRANSPORTER;
        }

    }

    private <DATA extends TransmitterUpgradeData> void transferUpgradeData(IExtraUpgradeableTransmitter<DATA> upgradeableTransmitter, TransmitterUpgradeData data) {
        if (upgradeableTransmitter.dataTypeMatches(data)) {
            upgradeableTransmitter.parseUpgradeData((DATA) data);
        } else {
            Mekanism.logger.warn("Unhandled upgrade data.", new IllegalStateException());
        }
    }

    private BaseTier getBaseTier() {
//        if (Addons.EVOLVEDMEKANISM.isLoaded()) {
//            return EMBaseTier.MULTIVERSAL;
//        }
        return BaseTier.ULTIMATE;
    }
}
