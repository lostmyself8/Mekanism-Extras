package com.jerry.genextras.common.item;

import com.jerry.genextras.common.registries.GenExtraDataComponents;
import com.jerry.genextras.common.tile.naquadah.TileEntityNaquadahReactorLogicAdapter;
import mekanism.api.text.EnumColor;
import mekanism.common.block.prefab.BlockBasicMultiblock;
import mekanism.common.item.block.ItemBlockTooltip;
import mekanism.common.util.text.BooleanStateDisplay;
import mekanism.generators.common.GeneratorsLang;
import mekanism.generators.common.registries.GeneratorsDataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ItemBlockNaquadahLogicAdapter extends ItemBlockTooltip<BlockBasicMultiblock<TileEntityNaquadahReactorLogicAdapter>> {

    public ItemBlockNaquadahLogicAdapter(BlockBasicMultiblock<TileEntityNaquadahReactorLogicAdapter> block, Properties properties) {
        super(block, true, properties.component(GenExtraDataComponents.NAQUADAH_LOGIC_TYPE, TileEntityNaquadahReactorLogicAdapter.NaquadahReactorLogic.DISABLED));
    }

    @Override
    protected void addDetails(@NotNull ItemStack stack, @NotNull Item.TooltipContext context, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        super.addDetails(stack, context, tooltip, flag);
        TileEntityNaquadahReactorLogicAdapter.NaquadahReactorLogic logicType = stack.getOrDefault(GenExtraDataComponents.NAQUADAH_LOGIC_TYPE, TileEntityNaquadahReactorLogicAdapter.NaquadahReactorLogic.DISABLED);
        tooltip.add(GeneratorsLang.REACTOR_LOGIC_REDSTONE_MODE.translate(logicType.getColor(), logicType));
        tooltip.add(GeneratorsLang.REACTOR_LOGIC_ACTIVE_COOLING.translate(EnumColor.RED, BooleanStateDisplay.OnOff.of(stack.getOrDefault(GeneratorsDataComponents.ACTIVE_COOLED, false))));
    }
}
