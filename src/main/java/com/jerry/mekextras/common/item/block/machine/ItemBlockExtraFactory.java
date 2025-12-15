package com.jerry.mekextras.common.item.block.machine;

import com.jerry.mekextras.common.block.attribute.ExtraAttribute;
import com.jerry.mekextras.common.block.prefab.BlockExtraFactoryMachine.BlockExtraFactory;
import com.jerry.mekextras.common.item.block.ExtraItemBlockTooltip;
import com.jerry.mekextras.common.tier.ExtraFactoryTier;
import mekanism.api.text.EnumColor;
import mekanism.common.MekanismLang;
import mekanism.common.attachments.component.AttachedEjector;
import mekanism.common.attachments.component.AttachedSideConfig;
import mekanism.common.block.attribute.Attribute;
import mekanism.common.block.attribute.AttributeFactoryType;
import mekanism.common.block.prefab.BlockTile;
import mekanism.common.registries.MekanismDataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ItemBlockExtraFactory extends ExtraItemBlockTooltip<BlockTile<?, ?>> {

private static AttachedSideConfig getSideConfig(BlockExtraFactory<?> block) {
    return switch (Attribute.getOrThrow(block.builtInRegistryHolder(), AttributeFactoryType.class).getFactoryType()) {
        case SMELTING, ENRICHING, CRUSHING, SAWING -> AttachedSideConfig.ELECTRIC_MACHINE;
        case COMPRESSING, INFUSING -> AttachedSideConfig.ADVANCED_MACHINE;
        case COMBINING -> AttachedSideConfig.EXTRA_MACHINE;
        case PURIFYING, INJECTING -> AttachedSideConfig.ADVANCED_MACHINE_INPUT_ONLY;
    };
}

public ItemBlockExtraFactory(BlockExtraFactory<?> block, Properties properties) {
    super(block, true, properties
            .component(MekanismDataComponents.SORTING, false)
            .component(MekanismDataComponents.EJECTOR, AttachedEjector.DEFAULT)
            .component(MekanismDataComponents.SIDE_CONFIG, getSideConfig(block))
    );
}

@Override
public ExtraFactoryTier getAdvancedTier() {
    return ExtraAttribute.getAdvanceTier(getBlock(), ExtraFactoryTier.class);
}

@Override
protected void addTypeDetails(@NotNull ItemStack stack, @NotNull TooltipContext context, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
    //Should always be present but validate it just in case
    AttributeFactoryType factoryType = Attribute.get(getBlock(), AttributeFactoryType.class);
    if (factoryType != null) {
        tooltip.add(MekanismLang.FACTORY_TYPE.translateColored(EnumColor.INDIGO, EnumColor.GRAY, factoryType.getFactoryType()));
    }
    super.addTypeDetails(stack, context, tooltip, flag);
}
}
