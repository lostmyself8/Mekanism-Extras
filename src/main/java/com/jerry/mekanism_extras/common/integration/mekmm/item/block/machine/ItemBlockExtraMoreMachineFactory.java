package com.jerry.mekanism_extras.common.integration.mekmm.item.block.machine;

import com.jerry.mekanism_extras.common.block.attribute.ExtraAttribute;
import com.jerry.mekanism_extras.common.integration.mekmm.block.prefab.BlockExtraMoreMachineFactory;
import com.jerry.mekanism_extras.common.item.block.machine.ExtraItemBlockMachine;
import com.jerry.mekanism_extras.common.tier.ExtraFactoryTier;

import mekanism.api.text.EnumColor;
import mekanism.common.MekanismLang;
import mekanism.common.block.attribute.Attribute;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import com.jerry.mekmm.common.block.attribute.AttributeMoreMachineFactoryType;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ItemBlockExtraMoreMachineFactory extends ExtraItemBlockMachine {

    public ItemBlockExtraMoreMachineFactory(BlockExtraMoreMachineFactory<?> block) {
        super(block);
    }

    @Override
    public ExtraFactoryTier getAdvanceTier() {
        return ExtraAttribute.getTier(getBlock(), ExtraFactoryTier.class);
    }

    @Override
    protected void addTypeDetails(@NotNull ItemStack stack, Level world, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        Attribute.ifPresent(getBlock(), AttributeMoreMachineFactoryType.class, attribute -> tooltip.add(MekanismLang.FACTORY_TYPE.translateColored(EnumColor.INDIGO, EnumColor.GRAY,
                attribute.getMoreMachineFactoryType())));
        super.addTypeDetails(stack, world, tooltip, flag);
    }
}
