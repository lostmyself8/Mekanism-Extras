package com.jerry.mekextras.common.item.block.transmitter;

import com.jerry.mekextras.common.tier.TPTier;
import com.jerry.mekextras.common.tile.transmitter.ExtraTileEntityLogisticalTransporter;
import mekanism.api.text.EnumColor;
import mekanism.common.MekanismLang;
import mekanism.common.block.attribute.Attribute;
import mekanism.common.block.transmitter.BlockLargeTransmitter;
import mekanism.common.tier.TransporterTier;
import net.minecraft.SharedConstants;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class ExtraItemBlockLogisticalTransporter extends ExtraItemBlockTransporter<ExtraTileEntityLogisticalTransporter> {
    public ExtraItemBlockLogisticalTransporter(BlockLargeTransmitter<ExtraTileEntityLogisticalTransporter> block) {
        super(block);
    }

    @NotNull
    @Override
    public TransporterTier getTier() {
        return Objects.requireNonNull(Attribute.getTier(getBlock(), TransporterTier.class));
    }

    @Override
    protected void addStats(@NotNull ItemStack stack, @Nullable Level world, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        super.addStats(stack, world, tooltip, flag);
        TransporterTier tier = getTier();
        int speed = TPTier.getSpeed(tier);
        int pull = TPTier.getPullAmount(tier);
        float tickRate = world == null ? SharedConstants.TICKS_PER_SECOND : world.tickRateManager().tickrate();
        if (tickRate > 0) {
            //TODO: Validate these calculations
            speed = (int) (speed / (100 / tickRate));
            pull = (int) (pull * tickRate / 10);
        } else {
            speed = 0;
            pull = 0;
        }
        tooltip.add(MekanismLang.SPEED.translateColored(EnumColor.INDIGO, EnumColor.GRAY, speed));
        tooltip.add(MekanismLang.PUMP_RATE.translateColored(EnumColor.INDIGO, EnumColor.GRAY, pull));
    }
}
