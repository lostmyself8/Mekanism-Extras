package com.jerry.mekanism_extras.common.item.block.transmitter;

import com.jerry.mekanism_extras.common.tier.transmitter.PTier;
import com.jerry.mekanism_extras.common.tile.transmitter.ExtraTileEntityMechanicalPipe;
import mekanism.api.text.EnumColor;
import mekanism.common.MekanismLang;
import mekanism.common.block.attribute.Attribute;
import mekanism.common.block.transmitter.BlockLargeTransmitter;
import mekanism.common.item.block.ItemBlockTooltip;
import mekanism.common.tier.PipeTier;
import mekanism.common.util.text.TextUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class ExtraItemBlockMechanicalPipe extends ItemBlockTooltip<BlockLargeTransmitter<ExtraTileEntityMechanicalPipe>> {

    public ExtraItemBlockMechanicalPipe(BlockLargeTransmitter<ExtraTileEntityMechanicalPipe> block, Properties properties) {
        super(block, true, properties);
    }

    @NotNull
    @Override
    public PipeTier getTier() {
        return Objects.requireNonNull(Attribute.getTier(getBlock(), PipeTier.class));
    }

    @Override
    protected void addDetails(@NotNull ItemStack stack, @Nullable TooltipContext context, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        super.addDetails(stack, context, tooltip, flag);
        tooltip.add(MekanismLang.CAPABLE_OF_TRANSFERRING.translateColored(EnumColor.DARK_GRAY));
        tooltip.add(MekanismLang.FLUIDS.translateColored(EnumColor.PURPLE, EnumColor.GRAY, MekanismLang.FORGE));
    }

    @Override
    protected void addStats(@NotNull ItemStack stack, @Nullable TooltipContext context, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        super.addStats(stack, context, tooltip, flag);
        PipeTier tier = getTier();
        tooltip.add(MekanismLang.CAPACITY_MB_PER_TICK.translateColored(EnumColor.INDIGO, EnumColor.GRAY, TextUtils.format(PTier.getPipeCapacity(tier))));
        tooltip.add(MekanismLang.PUMP_RATE_MB.translateColored(EnumColor.INDIGO, EnumColor.GRAY, TextUtils.format(PTier.getPipePullAmount(tier))));
    }
}
