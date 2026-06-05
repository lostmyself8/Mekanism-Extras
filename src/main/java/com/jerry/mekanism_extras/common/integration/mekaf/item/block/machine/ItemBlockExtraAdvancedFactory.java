package com.jerry.mekanism_extras.common.integration.mekaf.item.block.machine;

import com.jerry.mekanism_extras.common.block.attribute.ExtraAttribute;
import com.jerry.mekanism_extras.common.integration.mekaf.block.prefab.BlockExtraAdvancedFactory;
import com.jerry.mekanism_extras.common.item.block.machine.ExtraItemBlockMachine;
import com.jerry.mekanism_extras.common.tier.ExtraFactoryTier;

import mekanism.api.text.EnumColor;
import mekanism.common.MekanismLang;
import mekanism.common.block.attribute.Attribute;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import com.jerry.mekaf.common.block.attribute.AttributeAdvancedFactoryType;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ItemBlockExtraAdvancedFactory extends ExtraItemBlockMachine {

    public ItemBlockExtraAdvancedFactory(BlockExtraAdvancedFactory<?> block) {
        super(block);
    }

    @Override
    public ExtraFactoryTier getAdvanceTier() {
        return ExtraAttribute.getTier(getBlock(), ExtraFactoryTier.class);
    }

    @Override
    protected void addTypeDetails(@NotNull ItemStack stack, Level world, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        Attribute.ifPresent(getBlock(), AttributeAdvancedFactoryType.class, attribute -> tooltip.add(MekanismLang.FACTORY_TYPE.translateColored(EnumColor.INDIGO, EnumColor.GRAY,
                attribute.getAdvancedFactoryType())));
        super.addTypeDetails(stack, world, tooltip, flag);
    }
}
