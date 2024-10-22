package com.jerry.mekanism_extras.common.capabilities.chemical;

import com.jerry.mekanism_extras.common.capabilities.chemical.variable.ExtraVariableCapacityChemicalTank;
import com.jerry.mekanism_extras.common.tile.TileEntityLargeCapRadioactiveWasteBarrel;
import mekanism.api.Action;
import mekanism.api.AutomationType;
import mekanism.api.IContentsListener;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.chemical.Chemical;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.chemical.IChemicalHandler;
import mekanism.api.chemical.IChemicalTank;
import mekanism.api.chemical.attribute.ChemicalAttribute;
import mekanism.api.chemical.attribute.ChemicalAttributeValidator;
import mekanism.api.chemical.attribute.ChemicalAttributes;
import mekanism.common.util.WorldUtils;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

@NothingNullByDefault
public class StackedLargeCapWasteBarrel extends ExtraVariableCapacityChemicalTank implements IChemicalHandler, IChemicalTank {
    private static final ChemicalAttributeValidator ATTRIBUTE_VALIDATOR = new ChemicalAttributeValidator() {
        @Override
        public boolean validate(ChemicalAttribute attr) {
            return attr instanceof ChemicalAttributes.Radiation;
        }

        @Override
        public boolean process(Chemical chemical) {
            return chemical.isRadioactive();
        }
    };

    public static StackedLargeCapWasteBarrel create(TileEntityLargeCapRadioactiveWasteBarrel tile, @Nullable IContentsListener listener) {
        Objects.requireNonNull(tile, "Radioactive Waste Barrel tile entity cannot be null");
        return new StackedLargeCapWasteBarrel(tile, listener);
    }

    private final TileEntityLargeCapRadioactiveWasteBarrel tile;

    protected StackedLargeCapWasteBarrel(TileEntityLargeCapRadioactiveWasteBarrel tile, @Nullable IContentsListener listener) {
        super(tile.getTier().getStorage(), alwaysTrueBi, alwaysTrueBi, alwaysTrue, ATTRIBUTE_VALIDATOR, listener);
        this.tile = tile;
    }

    @Override
    public ChemicalStack insert(ChemicalStack stack, Action action, AutomationType automationType) {
        ChemicalStack remainder = super.insert(stack, action, automationType);
        if (!remainder.isEmpty()) {
            //If we have any leftover check if we can send it to the tank that is above
            TileEntityLargeCapRadioactiveWasteBarrel tileAbove = WorldUtils.getTileEntity(TileEntityLargeCapRadioactiveWasteBarrel.class, tile.getLevel(), tile.getBlockPos().above());
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
                TileEntityLargeCapRadioactiveWasteBarrel tileAbove = WorldUtils.getTileEntity(TileEntityLargeCapRadioactiveWasteBarrel.class, tile.getLevel(), tile.getBlockPos().above());
                if (tileAbove != null) {
                    long leftOverToInsert = amount - grownAmount;
                    //Note: We do external so that it is not limited by the internal rate limits
                    ChemicalStack remainder = tileAbove.getGasTank().insert(stored.copyWithAmount(leftOverToInsert), action, AutomationType.EXTERNAL);
                    grownAmount += leftOverToInsert - remainder.getAmount();
                }
            }
        }
        return grownAmount;
    }
}
