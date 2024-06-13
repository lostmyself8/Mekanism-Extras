package com.jerry.mekanism_extras.common.block.transmitter.logisticaltransporter;

import mekanism.api.providers.IBlockProvider;
import mekanism.api.tier.BaseTier;
import mekanism.client.model.data.TransmitterModelData;
import mekanism.common.block.states.TransmitterType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class ExtraTileEntityLogisticalTransporter extends ExtraTileEntityLogisticalTransporterBase {

    public ExtraTileEntityLogisticalTransporter(IBlockProvider blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state);
    }

    @Override
    protected ExtraLogisticalTransporter createTransmitter(IBlockProvider blockProvider) {
        return new ExtraLogisticalTransporter(blockProvider, this);
    }

    @Override
    public ExtraLogisticalTransporter getTransmitter() {
        return (ExtraLogisticalTransporter) super.getTransmitter();
    }

    @Override
    public TransmitterType getTransmitterType() {
        return TransmitterType.LOGISTICAL_TRANSPORTER;
    }

    @Override
    protected void updateModelData(TransmitterModelData modelData) {
        super.updateModelData(modelData);
        modelData.setHasColor(getTransmitter().getColor() != null);
    }

//    @NotNull
//    @Override
//    protected BlockState upgradeResult(@NotNull BlockState current, @NotNull BaseTier tier) {
//        return BlockStateHelper.copyStateData(current, switch (tier) {
//            case BASIC -> ExtraBlock.ABSOLUTE_LOGISTICAL_TRANSPORTER;
//            case ADVANCED -> ExtraBlock.SUPREME_LOGISTICAL_TRANSPORTER;
//            case ELITE -> ExtraBlock.COSMIC_LOGISTICAL_TRANSPORTER;
//            case ULTIMATE -> ExtraBlock.INFINITE_LOGISTICAL_TRANSPORTER;
//            default -> null;
//        });
//    }

    @NotNull
    @Override
    protected BlockState upgradeResult(@NotNull BlockState current, @NotNull BaseTier tier) {
        return current;
    }
}
