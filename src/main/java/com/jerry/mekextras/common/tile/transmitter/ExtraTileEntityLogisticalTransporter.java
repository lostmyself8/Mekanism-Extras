package com.jerry.mekextras.common.tile.transmitter;

import com.jerry.mekextras.api.tier.AdvanceTier;
import com.jerry.mekextras.common.content.network.transmitter.ExtraLogisticalTransporter;
import mekanism.api.providers.IBlockProvider;
import mekanism.client.model.data.TransmitterModelData;
import mekanism.common.block.states.BlockStateHelper;
import mekanism.common.block.states.TransmitterType;
import com.jerry.mekextras.common.registry.ExtraBlocks;
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

    @NotNull
    @Override
    protected BlockState upgradeResult(@NotNull BlockState current, @NotNull AdvanceTier tier) {
        return BlockStateHelper.copyStateData(current, switch (tier) {
            case ABSOLUTE -> ExtraBlocks.ABSOLUTE_LOGISTICAL_TRANSPORTER;
            case SUPREME -> ExtraBlocks.SUPREME_LOGISTICAL_TRANSPORTER;
            case COSMIC -> ExtraBlocks.COSMIC_LOGISTICAL_TRANSPORTER;
            case INFINITE -> ExtraBlocks.INFINITE_LOGISTICAL_TRANSPORTER;
        });
    }
}
