package com.jerry.generator_extras.common.item;

import com.jerry.mekanism_extras.common.ExtraTag;
import com.jerry.mekanism_extras.common.registry.ExtraGases;
import mekanism.api.chemical.ChemicalTankBuilder;
import mekanism.api.chemical.gas.GasStack;
import mekanism.api.chemical.gas.IGasHandler;
import mekanism.api.text.EnumColor;
import mekanism.common.MekanismLang;
import mekanism.common.capabilities.Capabilities;
import mekanism.common.capabilities.ItemCapabilityWrapper;
import mekanism.common.capabilities.chemical.item.RateLimitGasHandler;
import mekanism.common.item.CapabilityItem;
import mekanism.common.registration.impl.CreativeTabDeferredRegister;
import mekanism.common.util.ChemicalUtil;
import mekanism.common.util.StorageUtils;
import com.jerry.generator_extras.common.config.GenLoadConfig;
import mekanism.generators.common.GeneratorsLang;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class ItemNquadahHohlraum extends CapabilityItem implements CreativeTabDeferredRegister.ICustomCreativeTabContents {
    public ItemNquadahHohlraum(Properties properties) {
        super(properties.stacksTo(1));
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level world, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        Optional<IGasHandler> capability = stack.getCapability(Capabilities.GAS_HANDLER).resolve();
        if (capability.isPresent()) {
            IGasHandler gasHandlerItem = capability.get();
            if (gasHandlerItem.getTanks() > 0) {
                GasStack storedGas = gasHandlerItem.getChemicalInTank(0);
                if (!storedGas.isEmpty()) {
                    tooltip.add(MekanismLang.STORED.translate(storedGas, storedGas.getAmount()));
                    if (storedGas.getAmount() == gasHandlerItem.getTankCapacity(0)) {
                        tooltip.add(GeneratorsLang.READY_FOR_REACTION.translateColored(EnumColor.DARK_GREEN));
                    } else {
                        tooltip.add(GeneratorsLang.INSUFFICIENT_FUEL.translateColored(EnumColor.DARK_RED));
                    }
                    return;
                }
            }
        }
        tooltip.add(MekanismLang.NO_GAS.translate());
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
    public void addItems(CreativeModeTab.Output tabOutput) {
        tabOutput.accept(ChemicalUtil.getFilledVariant(new ItemStack(this), GenLoadConfig.generatorConfig.hohlraumMaxGas, ExtraGases.SILICON_URANIUM_FUEL));
    }

    @Override
    protected boolean areCapabilityConfigsLoaded() {
        return super.areCapabilityConfigsLoaded() && GenLoadConfig.generatorConfig.isLoaded();
    }

    @Override
    protected void gatherCapabilities(List<ItemCapabilityWrapper.ItemCapability> capabilities, ItemStack stack, CompoundTag nbt) {
        super.gatherCapabilities(capabilities, stack, nbt);
        capabilities.add(RateLimitGasHandler.create(GenLoadConfig.generatorConfig.hohlraumFillRate, GenLoadConfig.generatorConfig.hohlraumMaxGas,
                ChemicalTankBuilder.GAS.notExternal, ChemicalTankBuilder.GAS.alwaysTrueBi, ExtraTag.Gases.SILICON_URANIUM_FUEL_LOOKUP::contains));
    }
}
