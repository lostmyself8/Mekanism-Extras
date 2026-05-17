package com.jerry.mekanism_extras.common.item.block.machine;

import com.jerry.mekanism_extras.common.block.attribute.ExtraAttribute;
import com.jerry.mekanism_extras.common.block.prefab.BlockExtraFactoryMachine.BlockExtraFactory;
import com.jerry.mekanism_extras.common.tier.ExtraFactoryTier;

import mekanism.api.text.EnumColor;
import mekanism.common.MekanismLang;
import mekanism.common.block.attribute.Attribute;
import mekanism.common.block.attribute.AttributeFactoryType;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ItemBlockExtraFactory extends ExtraItemBlockMachine {

    public ItemBlockExtraFactory(BlockExtraFactory<?> block) {
        super(block);
    }

    @Override
    public ExtraFactoryTier getAdvanceTier() {
        return ExtraAttribute.getTier(getBlock(), ExtraFactoryTier.class);
    }

    @Override
    protected void addTypeDetails(@NotNull ItemStack stack, Level world, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        // Should always be present but validate it just in case
        Attribute.ifPresent(getBlock(), AttributeFactoryType.class, attribute -> tooltip.add(MekanismLang.FACTORY_TYPE.translateColored(EnumColor.INDIGO, EnumColor.GRAY,
                attribute.getFactoryType())));
        super.addTypeDetails(stack, world, tooltip, flag);
    }
}
