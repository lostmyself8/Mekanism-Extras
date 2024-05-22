package com.jerry.mekanism_extras.common.block.transmitter.tube;

import mekanism.api.text.EnumColor;
import mekanism.client.key.MekKeyHandler;
import mekanism.client.key.MekanismKeyHandler;
import mekanism.common.MekanismLang;
import mekanism.common.block.attribute.Attribute;
import mekanism.common.item.block.ItemBlockMekanism;
import mekanism.common.tier.TubeTier;
import mekanism.common.util.text.TextUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class ExtraItemBlockPressurizedTube extends ItemBlockMekanism<ExtraBlockPressurizedTube> {

    public ExtraItemBlockPressurizedTube(ExtraBlockPressurizedTube block) {
        super(block, new Item.Properties());
    }

    @NotNull
    @Override
    public TubeTier getTier() {
        return Objects.requireNonNull(Attribute.getTier(this.getBlock(), TubeTier.class));
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, Level world, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        if (MekKeyHandler.isKeyPressed(MekanismKeyHandler.detailsKey)) {
            tooltip.add(MekanismLang.CAPABLE_OF_TRANSFERRING.translateColored(EnumColor.DARK_GRAY));
            tooltip.add(MekanismLang.GASES.translateColored(EnumColor.PURPLE, MekanismLang.MEKANISM));
            tooltip.add(MekanismLang.INFUSE_TYPES.translateColored(EnumColor.PURPLE, MekanismLang.MEKANISM));
            tooltip.add(MekanismLang.PIGMENTS.translateColored(EnumColor.PURPLE, MekanismLang.MEKANISM));
            tooltip.add(MekanismLang.SLURRIES.translateColored(EnumColor.PURPLE, MekanismLang.MEKANISM));
        } else {
            tooltip.add(MekanismLang.CAPACITY_MB_PER_TICK.translateColored(EnumColor.INDIGO, EnumColor.GRAY, TextUtils.format(TTier.getTubeCapacity(this.getTier()))));
            tooltip.add(MekanismLang.PUMP_RATE_MB.translateColored(EnumColor.INDIGO, EnumColor.GRAY, TextUtils.format(TTier.getTubePullAmount(this.getTier()))));
            tooltip.add(MekanismLang.HOLD_FOR_DETAILS.translateColored(EnumColor.GRAY, EnumColor.INDIGO, MekanismKeyHandler.detailsKey.getTranslatedKeyMessage()));
        }
    }
}
