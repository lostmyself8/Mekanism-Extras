package com.jerry.mekanism_extras.common.item.block;

import com.jerry.mekanism_extras.client.render.ExtraRenderPropertiesProvider;
import com.jerry.mekanism_extras.common.block.attribute.ExtraAttribute;
import com.jerry.mekanism_extras.common.tier.ECTier;
import com.jerry.mekanism_extras.common.block.ExtraBlockEnergyCube;
import com.jerry.mekanism_extras.common.capabilities.energy.item.ExtraRateLimitEnergyHandler;
import mekanism.api.text.EnumColor;
import mekanism.common.MekanismLang;
import mekanism.common.capabilities.ItemCapabilityWrapper;
import mekanism.common.capabilities.energy.item.ItemStackEnergyHandler;
import mekanism.common.config.MekanismConfig;
import mekanism.common.item.interfaces.IItemSustainedInventory;
import mekanism.common.util.StorageUtils;
import mekanism.common.util.text.EnergyDisplay;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class ExtraItemBlockEnergyCube extends ExtraItemBlockTooltip<ExtraBlockEnergyCube> implements IItemSustainedInventory {
    public ExtraItemBlockEnergyCube(ExtraBlockEnergyCube block) {
        super(block);
    }

    @Override
    public void initializeClient(@Nonnull Consumer<IClientItemExtensions> consumer) {
        consumer.accept(ExtraRenderPropertiesProvider.extraEnergyCube());
    }

    @Nonnull
    @Override
    public ECTier getAdvanceTier() {
        return Objects.requireNonNull(ExtraAttribute.getAdvanceTier(getBlock(), ECTier.class));
    }

    @Override
    public void appendHoverText(@Nonnull ItemStack stack, Level world, @Nonnull List<Component> tooltip, @Nonnull TooltipFlag flag) {
        StorageUtils.addStoredEnergy(stack, tooltip, true);
        tooltip.add(MekanismLang.CAPACITY.translateColored(EnumColor.INDIGO, EnumColor.GRAY, EnergyDisplay.of(getAdvanceTier().getMaxEnergy())));
        super.appendHoverText(stack, world, tooltip, flag);
    }

    @Override
    protected void addTypeDetails(@Nonnull ItemStack stack, Level world, @Nonnull List<Component> tooltip, @Nonnull TooltipFlag flag) {
        //Don't call super so that we can exclude the stored energy from being shown as we show it in hover text
    }

    @Override
    public boolean isBarVisible(@NotNull ItemStack stack) {
        return true;
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
    protected void gatherCapabilities(List<ItemCapabilityWrapper.ItemCapability> capabilities, ItemStack stack, CompoundTag nbt) {
        super.gatherCapabilities(capabilities, stack, nbt);
        ItemCapabilityWrapper.ItemCapability capability = ExtraRateLimitEnergyHandler.create(getAdvanceTier());
        int index = IntStream.range(0, capabilities.size()).filter(i -> capabilities.get(i) instanceof ItemStackEnergyHandler).findFirst().orElse(-1);
        if (index != -1) {
            //This is likely always the path that will be taken
            capabilities.set(index, capability);
        } else {
            capabilities.add(capability);
        }
    }
}
