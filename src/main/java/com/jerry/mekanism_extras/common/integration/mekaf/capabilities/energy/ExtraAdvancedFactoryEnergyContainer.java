package com.jerry.mekanism_extras.common.integration.mekaf.capabilities.energy;

import com.jerry.mekanism_extras.common.integration.mekaf.tile.factory.base.TileEntityExtraAdvancedFactoryBase;

import mekanism.api.AutomationType;
import mekanism.api.IContentsListener;
import mekanism.api.math.FloatingLong;
import mekanism.common.block.attribute.AttributeEnergy;
import mekanism.common.capabilities.energy.MachineEnergyContainer;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public class ExtraAdvancedFactoryEnergyContainer extends MachineEnergyContainer<TileEntityExtraAdvancedFactoryBase<?>> {

    public static ExtraAdvancedFactoryEnergyContainer input(TileEntityExtraAdvancedFactoryBase<?> tile, @Nullable IContentsListener listener) {
        AttributeEnergy electricBlock = validateBlock(tile);
        return new ExtraAdvancedFactoryEnergyContainer(electricBlock.getStorage(), electricBlock.getUsage(), notExternal, alwaysTrue, tile, listener);
    }

    private ExtraAdvancedFactoryEnergyContainer(FloatingLong maxEnergy, FloatingLong energyPerTick, Predicate<AutomationType> canExtract, Predicate<AutomationType> canInsert, TileEntityExtraAdvancedFactoryBase<?> tile, @Nullable IContentsListener listener) {
        super(maxEnergy, energyPerTick, canExtract, canInsert, tile, listener);
    }

    @NotNull
    public FloatingLong getBaseEnergyPerTick() {
        return super.getBaseEnergyPerTick().add(this.tile.getRecipeEnergyRequired());
    }
}
