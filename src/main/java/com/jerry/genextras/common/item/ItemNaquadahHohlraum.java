package com.jerry.genextras.common.item;

import com.jerry.mekextras.common.registries.ExtraChemicals;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.chemical.IChemicalHandler;
import mekanism.api.text.EnumColor;
import mekanism.common.MekanismLang;
import mekanism.common.capabilities.Capabilities;
import mekanism.common.registration.impl.CreativeTabDeferredRegister;
import mekanism.common.util.ChemicalUtil;
import mekanism.common.util.StorageUtils;
import mekanism.generators.common.GeneratorsLang;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;

public class ItemNaquadahHohlraum extends Item implements CreativeTabDeferredRegister.ICustomCreativeTabContents {

    public ItemNaquadahHohlraum(Properties properties) {
        super(properties.stacksTo(1));
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @NotNull Item.TooltipContext context, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        IChemicalHandler gasHandlerItem = Capabilities.CHEMICAL.getCapability(stack);
        if (gasHandlerItem != null && gasHandlerItem.getChemicalTanks() > 0) {
            //Validate something didn't go terribly wrong, and we actually do have the tank we expect to have
            ChemicalStack storedGas = gasHandlerItem.getChemicalInTank(0);
            if (!storedGas.isEmpty()) {
                tooltip.add(MekanismLang.STORED.translate(storedGas, storedGas.getAmount()));
                if (storedGas.getAmount() == gasHandlerItem.getChemicalTankCapacity(0)) {
                    tooltip.add(GeneratorsLang.READY_FOR_REACTION.translateColored(EnumColor.DARK_GREEN));
                } else {
                    tooltip.add(GeneratorsLang.INSUFFICIENT_FUEL.translateColored(EnumColor.DARK_RED));
                }
                return;
            }
        }
        tooltip.add(MekanismLang.NO_CHEMICAL.translate());
        tooltip.add(GeneratorsLang.INSUFFICIENT_FUEL.translateColored(EnumColor.DARK_RED));
    }

    @Override
    public boolean isBarVisible(@NotNull ItemStack stack) {
        return true;
    }

    @Override
    public int getBarWidth(@NotNull ItemStack stack) {
        return StorageUtils.getBarWidth(stack);
    }

    @Override
    public int getBarColor(@NotNull ItemStack stack) {
        return ChemicalUtil.getRGBDurabilityForDisplay(stack);
    }

    @Override
    public void addItems(Holder<Item> item, Consumer<ItemStack> tabOutput) {
        tabOutput.accept(ChemicalUtil.getFilledVariant(item, ExtraChemicals.NAQUADAH_URANIUM_FUEL));
    }
}
