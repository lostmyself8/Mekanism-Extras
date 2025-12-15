package com.jerry.mekextras.common.integration.mekaf.capabilities.energy;

import com.jerry.mekextras.common.integration.mekaf.tile.factory.TileEntityExtraAdvancedBase;
import mekanism.api.AutomationType;
import mekanism.api.IContentsListener;
import mekanism.api.functions.ConstantPredicates;
import mekanism.common.block.attribute.AttributeEnergy;
import mekanism.common.capabilities.energy.MachineEnergyContainer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public class ExtraAdvancedFactoryEnergyContainer extends MachineEnergyContainer<TileEntityExtraAdvancedBase<?>> {
    public static ExtraAdvancedFactoryEnergyContainer input(TileEntityExtraAdvancedBase<?> tile, @Nullable IContentsListener listener) {
        AttributeEnergy electricBlock = validateBlock(tile);
        return new ExtraAdvancedFactoryEnergyContainer(electricBlock.getStorage(), electricBlock.getUsage(), notExternal, ConstantPredicates.alwaysTrue(), tile, listener);
    }

    private ExtraAdvancedFactoryEnergyContainer(long maxEnergy, long energyPerTick, Predicate<@NotNull AutomationType> canExtract, Predicate<@NotNull AutomationType> canInsert, TileEntityExtraAdvancedBase<?> tile, @Nullable IContentsListener listener) {
        super(maxEnergy, energyPerTick, canExtract, canInsert, tile, listener);
    }

    public long getBaseEnergyPerTick() {
        return super.getBaseEnergyPerTick() + this.tile.getRecipeEnergyRequired();
    }
}
