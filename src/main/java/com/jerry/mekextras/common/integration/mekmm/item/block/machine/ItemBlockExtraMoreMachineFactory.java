package com.jerry.mekextras.common.integration.mekmm.item.block.machine;

import com.jerry.mekextras.common.block.attribute.ExtraAttribute;
import com.jerry.mekextras.common.integration.mekmm.block.prefab.ExtraMoreMachineBlockFactoryMachine.BlockExtraMoreMachineFactory;
import com.jerry.mekextras.common.item.block.ItemBlockExtraTooltip;
import com.jerry.mekextras.common.tier.ExtraFactoryTier;

import mekanism.api.text.EnumColor;
import mekanism.common.MekanismLang;
import mekanism.common.attachments.component.AttachedEjector;
import mekanism.common.attachments.component.AttachedSideConfig;
import mekanism.common.block.attribute.Attribute;
import mekanism.common.block.prefab.BlockTile;
import mekanism.common.registries.MekanismDataComponents;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import com.jerry.mekmm.common.block.attribute.MoreMachineAttributeFactoryType;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ItemBlockExtraMoreMachineFactory extends ItemBlockExtraTooltip<BlockTile<?, ?>> {

    private static AttachedSideConfig getSideConfig(BlockExtraMoreMachineFactory<?> block) {
        return switch (Attribute.getOrThrow(block.builtInRegistryHolder(), MoreMachineAttributeFactoryType.class).getMoreMachineFactoryType()) {
            case CNC_STAMPING -> AttachedSideConfig.EXTRA_MACHINE;
            case RECYCLING, CNC_LATHING, CNC_ROLLING_MILL -> AttachedSideConfig.ELECTRIC_MACHINE;
            case PLANTING_STATION, REPLICATING -> AttachedSideConfig.ADVANCED_MACHINE_INPUT_ONLY;
        };
    }

    public ItemBlockExtraMoreMachineFactory(BlockExtraMoreMachineFactory<?> block, Properties properties) {
        super(block, true, properties
                .component(MekanismDataComponents.SORTING, false)
                .component(MekanismDataComponents.EJECTOR, AttachedEjector.DEFAULT)
                .component(MekanismDataComponents.SIDE_CONFIG, getSideConfig(block)));
    }

    @Override
    public ExtraFactoryTier getAdvancedTier() {
        return ExtraAttribute.getAdvancedTier(getBlock(), ExtraFactoryTier.class);
    }

    @Override
    protected void addTypeDetails(@NotNull ItemStack stack, @NotNull Item.TooltipContext context, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        // Should always be present but validate it just in case
        MoreMachineAttributeFactoryType factoryType = Attribute.get(getBlock(), MoreMachineAttributeFactoryType.class);
        if (factoryType != null) {
            tooltip.add(MekanismLang.FACTORY_TYPE.translateColored(EnumColor.INDIGO, EnumColor.GRAY, factoryType.getMoreMachineFactoryType()));
        }
        super.addTypeDetails(stack, context, tooltip, flag);
    }
}
