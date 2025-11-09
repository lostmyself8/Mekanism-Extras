package com.jerry.mekanism_extras.common.item.block;

import com.jerry.mekanism_extras.common.block.ExtraBlockRadioactiveWasteBarrel;
import com.jerry.mekanism_extras.common.config.LoadConfig;

import mekanism.api.text.EnumColor;
import mekanism.common.MekanismLang;
import mekanism.common.item.block.ItemBlockTooltip;
import mekanism.common.util.UnitDisplayUtils;
import mekanism.common.util.text.TextUtils;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ExtraItemBlockRadioactiveWasteBarrel extends ItemBlockTooltip<ExtraBlockRadioactiveWasteBarrel> {

    public ExtraItemBlockRadioactiveWasteBarrel(ExtraBlockRadioactiveWasteBarrel block, Item.Properties properties) {
        super(block, properties);
    }

    @Override
    protected void addStats(@NotNull ItemStack stack, Level world, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        tooltip.add(MekanismLang.CAPACITY_MB.translateColored(EnumColor.INDIGO, EnumColor.GRAY, TextUtils.format(LoadConfig.extraConfig.radioactiveWasteBarrelMaxGas.get())));
        int ticks = LoadConfig.extraConfig.radioactiveWasteBarrelProcessTicks.get();
        long decayAmount = LoadConfig.extraConfig.radioactiveWasteBarrelDecayAmount.get();
        if (decayAmount == 0 || ticks == 1) {
            tooltip.add(MekanismLang.WASTE_BARREL_DECAY_RATE.translateColored(EnumColor.INDIGO, EnumColor.GRAY, TextUtils.format(decayAmount)));
        } else {
            // Show decay rate to four decimals with no trailing zeros (but without decimals if it divides evenly)
            tooltip.add(MekanismLang.WASTE_BARREL_DECAY_RATE.translateColored(EnumColor.INDIGO, EnumColor.GRAY,
                    TextUtils.format(UnitDisplayUtils.roundDecimals(decayAmount / (double) ticks, 4))));
            tooltip.add(MekanismLang.WASTE_BARREL_DECAY_RATE_ACTUAL.translateColored(EnumColor.INDIGO, EnumColor.GRAY, TextUtils.format(decayAmount),
                    EnumColor.GRAY, TextUtils.format(ticks)));
        }
    }
}
