package com.jerry.mekextra.common.item.block;


import com.jerry.mekextra.common.block.BlockLargeCapRadioactiveWasteBarrel;
import com.jerry.mekextra.common.block.attribute.ExtraAttribute;
import com.jerry.mekextra.common.tier.RWBTier;
import mekanism.api.text.EnumColor;
import mekanism.common.MekanismLang;
import mekanism.common.util.UnitDisplayUtils;
import mekanism.common.util.text.TextUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ItemBlockLargeCapRadioactiveWasteBarrel extends ExtraItemBlockTooltip<BlockLargeCapRadioactiveWasteBarrel> {
    public ItemBlockLargeCapRadioactiveWasteBarrel(BlockLargeCapRadioactiveWasteBarrel block, Properties properties) {
        super(block, properties);
    }

    @Override
    public RWBTier getAdvanceTier() {
        return ExtraAttribute.getAdvanceTier(getBlock(), RWBTier.class);
    }

    @Override
    protected void addStats(@NotNull ItemStack stack, @Nullable TooltipContext context, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        tooltip.add(MekanismLang.CAPACITY_MB.translateColored(EnumColor.INDIGO, EnumColor.GRAY, TextUtils.format(getAdvanceTier().getStorage())));
        int ticks = getAdvanceTier().getProcessTicks();
        long decayAmount = getAdvanceTier().getDecayAmount();
        if (decayAmount == 0 || ticks == 1) {
            tooltip.add(MekanismLang.WASTE_BARREL_DECAY_RATE.translateColored(EnumColor.INDIGO, EnumColor.GRAY, TextUtils.format(decayAmount)));
        } else {
            //Show decay rate to four decimals with no trailing zeros (but without decimals if it divides evenly)
            tooltip.add(MekanismLang.WASTE_BARREL_DECAY_RATE.translateColored(EnumColor.INDIGO, EnumColor.GRAY,
                    TextUtils.format(UnitDisplayUtils.roundDecimals(decayAmount / (double) ticks, 4))));
            tooltip.add(MekanismLang.WASTE_BARREL_DECAY_RATE_ACTUAL.translateColored(EnumColor.INDIGO, EnumColor.GRAY, TextUtils.format(decayAmount),
                    EnumColor.GRAY, TextUtils.format(ticks)));
        }
    }
}
