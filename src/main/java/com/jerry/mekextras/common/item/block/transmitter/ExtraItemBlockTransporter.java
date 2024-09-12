package com.jerry.mekextras.common.item.block.transmitter;

import com.jerry.mekextras.common.tile.transmitter.ExtraTileEntityLogisticalTransporterBase;
import mekanism.api.text.EnumColor;
import mekanism.api.text.ILangEntry;
import mekanism.common.MekanismLang;
import mekanism.common.block.transmitter.BlockLargeTransmitter;
import mekanism.common.item.block.ItemBlockTooltip;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ExtraItemBlockTransporter <TILE extends ExtraTileEntityLogisticalTransporterBase> extends ItemBlockTooltip<BlockLargeTransmitter<TILE>> {
    @Nullable
    private final ILangEntry extraDetails;

    public ExtraItemBlockTransporter(BlockLargeTransmitter<TILE> block) {
        this(block, null);
    }

    public ExtraItemBlockTransporter(BlockLargeTransmitter<TILE> block, @Nullable ILangEntry extraDetails) {
        super(block);
        this.extraDetails = extraDetails;
    }

    @Override
    protected void addDetails(@NotNull ItemStack stack, @Nullable Level world, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        super.addDetails(stack, world, tooltip, flag);
        tooltip.add(MekanismLang.CAPABLE_OF_TRANSFERRING.translateColored(EnumColor.DARK_GRAY));
        tooltip.add(MekanismLang.ITEMS.translateColored(EnumColor.PURPLE, MekanismLang.UNIVERSAL));
        tooltip.add(MekanismLang.BLOCKS.translateColored(EnumColor.PURPLE, MekanismLang.UNIVERSAL));
        if (extraDetails != null) {
            tooltip.add(extraDetails.translateColored(EnumColor.DARK_RED));
        }
    }
}
