package com.jerry.mekextras.common.item.block;

import com.jerry.mekextras.client.render.ExtraRenderPropertiesProvider;
import com.jerry.mekextras.common.block.attribute.ExtraAttribute;
import com.jerry.mekextras.common.tier.ECTier;
import com.jerry.mekextras.common.block.ExtraBlockEnergyCube;
import com.jerry.mekextras.common.capabilities.energy.item.ExtraEnergyCubeRateLimitEnergyContainer;
import mekanism.api.energy.IEnergyContainer;
import mekanism.api.text.EnumColor;
import mekanism.common.MekanismLang;
import mekanism.common.config.MekanismConfig;
import mekanism.common.registration.impl.CreativeTabDeferredRegister;
import mekanism.common.util.StorageUtils;
import mekanism.common.util.text.EnergyDisplay;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public class ExtraItemBlockEnergyCube extends ExtraItemBlockTooltip<ExtraBlockEnergyCube> implements CreativeTabDeferredRegister.ICustomCreativeTabContents {

    public ExtraItemBlockEnergyCube(ExtraBlockEnergyCube block) {
        super(block);
    }

    @Override
    public void initializeClient(@NotNull Consumer<IClientItemExtensions> consumer) {
        consumer.accept(ExtraRenderPropertiesProvider.extraEnergyCube());
    }

    @Override
    public ECTier getAdvanceTier() {
        return ExtraAttribute.getAdvanceTier(getBlock(), ECTier.class);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level world, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        StorageUtils.addStoredEnergy(stack, tooltip, true);
        tooltip.add(MekanismLang.CAPACITY.translateColored(EnumColor.INDIGO, EnumColor.GRAY, EnergyDisplay.of(getAdvanceTier().getMaxEnergy())));
        super.appendHoverText(stack, world, tooltip, flag);
    }

    @Override
    protected void addTypeDetails(@NotNull ItemStack stack, @Nullable Level world, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        //Don't call super so that we can exclude the stored energy from being shown as we show it in hover text
    }

    @Override
    public boolean isBarVisible(@NotNull ItemStack stack) {
        //If we are currently stacked, don't display the bar as it will overlap the stack count
        return stack.getCount() == 1;
    }

    @Override
    public int getBarWidth(@NotNull ItemStack stack) {
        return StorageUtils.getEnergyBarWidth(stack);
    }

    @Override
    public int getBarColor(@NotNull ItemStack stack) {
        return MekanismConfig.client.energyColor.get();
    }

    @Override
    public void addItems(CreativeModeTab.Output tabOutput) {
        tabOutput.accept(StorageUtils.getFilledEnergyVariant(new ItemStack(this)));
    }

    @Override
    public boolean addDefault() {
        return CreativeTabDeferredRegister.ICustomCreativeTabContents.super.addDefault();
    }

    @Override
    protected IEnergyContainer getDefaultEnergyContainer(ItemStack stack) {
        return ExtraEnergyCubeRateLimitEnergyContainer.create(getAdvanceTier());
    }
}
