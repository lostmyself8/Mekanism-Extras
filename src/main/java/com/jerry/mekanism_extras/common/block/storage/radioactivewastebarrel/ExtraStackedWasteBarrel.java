package com.jerry.mekanism_extras.common.block.storage.radioactivewastebarrel;

import com.jerry.mekanism_extras.common.config.LoadConfig;
import mekanism.api.Action;
import mekanism.api.AutomationType;
import mekanism.api.IContentsListener;
import mekanism.api.chemical.ChemicalTankBuilder;
import mekanism.api.chemical.attribute.ChemicalAttributeValidator;
import mekanism.api.chemical.gas.Gas;
import mekanism.api.chemical.gas.GasStack;
import mekanism.api.chemical.gas.IGasHandler;
import mekanism.api.chemical.gas.IGasTank;
import mekanism.api.chemical.gas.attribute.GasAttributes;
import mekanism.common.capabilities.chemical.variable.VariableCapacityChemicalTank;
import mekanism.common.util.WorldUtils;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class ExtraStackedWasteBarrel extends VariableCapacityChemicalTank<Gas, GasStack> implements IGasHandler, IGasTank {

    private static final ChemicalAttributeValidator ATTRIBUTE_VALIDATOR = ChemicalAttributeValidator.createStrict(GasAttributes.Radiation.class);

    public static ExtraStackedWasteBarrel create(ExtraTileEntityRadioactiveWasteBarrel tile, @Nullable IContentsListener listener) {
        Objects.requireNonNull(tile, "Radioactive Waste Barrel tile entity cannot be null");
        return new ExtraStackedWasteBarrel(tile, listener);
    }

    private final ExtraTileEntityRadioactiveWasteBarrel tile;

    protected ExtraStackedWasteBarrel(ExtraTileEntityRadioactiveWasteBarrel tile, @Nullable IContentsListener listener) {
        super(LoadConfig.extraConfig.radioactiveWasteBarrelMaxGas, ChemicalTankBuilder.GAS.alwaysTrueBi, ChemicalTankBuilder.GAS.alwaysTrueBi,
                ChemicalTankBuilder.GAS.alwaysTrue, ATTRIBUTE_VALIDATOR, listener);
        this.tile = tile;
    }

    @Override
    public GasStack insert(GasStack stack, Action action, AutomationType automationType) {
        GasStack remainder = super.insert(stack, action, automationType);
        if (!remainder.isEmpty()) {
            //If we have any leftover check if we can send it to the tank that is above
            ExtraTileEntityRadioactiveWasteBarrel tileAbove = WorldUtils.getTileEntity(ExtraTileEntityRadioactiveWasteBarrel.class, tile.getLevel(), tile.getBlockPos().above());
            if (tileAbove != null) {
                //Note: We do external so that it is not limited by the internal rate limits
                remainder = tileAbove.getGasTank().insert(remainder, action, AutomationType.EXTERNAL);
            }
        }
        return remainder;
    }

    @Override
    public long growStack(long amount, Action action) {
        long grownAmount = super.growStack(amount, action);
        if (amount > 0 && grownAmount < amount) {
            //If we grew our stack less than we tried to, and we were actually growing and not shrinking it
            // try inserting into above tiles
            if (!tile.getActive()) {
                ExtraTileEntityRadioactiveWasteBarrel tileAbove = WorldUtils.getTileEntity(ExtraTileEntityRadioactiveWasteBarrel.class, tile.getLevel(), tile.getBlockPos().above());
                if (tileAbove != null) {
                    long leftOverToInsert = amount - grownAmount;
                    //Note: We do external so that it is not limited by the internal rate limits
                    GasStack remainder = tileAbove.getGasTank().insert(new GasStack(stored, leftOverToInsert), action, AutomationType.EXTERNAL);
                    grownAmount += leftOverToInsert - remainder.getAmount();
                }
            }
        }
        return grownAmount;
    }
}
