package com.jerry.mekextras.common.item.block.transmitter;

import com.jerry.mekextras.common.tier.transmitter.TPTier;
import com.jerry.mekextras.common.tile.transmitter.ExtraTileEntityLogisticalTransporter;
import mekanism.api.text.EnumColor;
import mekanism.common.MekanismLang;
import mekanism.common.block.attribute.Attribute;
import mekanism.common.block.transmitter.BlockLargeTransmitter;
import mekanism.common.tier.TransporterTier;
import mekanism.common.util.MekanismUtils;
import net.minecraft.SharedConstants;
import net.minecraft.network.chat.Component;
import net.minecraft.world.TickRateManager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class ExtraItemBlockLogisticalTransporter extends ExtraItemBlockTransporter<ExtraTileEntityLogisticalTransporter> {
    public ExtraItemBlockLogisticalTransporter(BlockLargeTransmitter<ExtraTileEntityLogisticalTransporter> block, Properties properties) {
        super(block, properties);
    }

    @NotNull
    @Override
    public TransporterTier getTier() {
        return Objects.requireNonNull(Attribute.getTier(getBlock(), TransporterTier.class));
    }

    @Override
    protected void addStats(@NotNull ItemStack stack, @Nullable TooltipContext context, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        super.addStats(stack, context, tooltip, flag);
        TransporterTier tier = getTier();
        float tickRate = Math.max(context.tickRate(), TickRateManager.MIN_TICKRATE);
        float speed = TPTier.getSpeed(tier) / (5 * SharedConstants.TICKS_PER_SECOND / tickRate);
        float pull = TPTier.getPullAmount(tier) * tickRate / MekanismUtils.TICKS_PER_HALF_SECOND;
        tooltip.add(MekanismLang.SPEED.translateColored(EnumColor.INDIGO, EnumColor.GRAY, speed));
        tooltip.add(MekanismLang.PUMP_RATE.translateColored(EnumColor.INDIGO, EnumColor.GRAY, pull));
    }
}
