package com.jerry.mekanism_extras.common.attachments.containers.chemical;

import com.jerry.mekanism_extras.common.item.block.ExtraItemBlockChemicalTank;
import com.jerry.mekanism_extras.common.tier.CTTier;
import mekanism.api.Action;
import mekanism.api.AutomationType;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.chemical.BasicChemicalTank;
import mekanism.api.chemical.ChemicalStack;
import mekanism.common.attachments.containers.ContainerType;
import mekanism.common.attachments.containers.chemical.AttachedChemicals;
import mekanism.common.attachments.containers.chemical.ComponentBackedChemicalTank;
import net.minecraft.world.item.ItemStack;

@NothingNullByDefault
public class ExtraComponentBackedChemicalTankTank extends ComponentBackedChemicalTank {

    private final boolean isCreative;

    public static ExtraComponentBackedChemicalTankTank create(ContainerType<?, ?, ?> ignored, ItemStack attachedTo, int tankIndex) {
        if (!(attachedTo.getItem() instanceof ExtraItemBlockChemicalTank item)) {
            throw new IllegalStateException("Attached to should always be a chemical tank item");
        }
        return new ExtraComponentBackedChemicalTankTank(attachedTo, tankIndex, item.getAdvanceTier());
    }

    private ExtraComponentBackedChemicalTankTank(ItemStack attachedTo, int tankIndex, CTTier tier) {
        super(attachedTo, tankIndex, BasicChemicalTank.alwaysTrueBi, BasicChemicalTank.alwaysTrueBi, BasicChemicalTank.alwaysTrue,
                tier::getOutput, tier::getStorage, null);
        isCreative = false;
    }

    @Override
    public ChemicalStack insert(ChemicalStack stack, Action action, AutomationType automationType) {
        return super.insert(stack, action.combine(!isCreative), automationType);
    }

    @Override
    public ChemicalStack extract(AttachedChemicals attachedChemicals, ChemicalStack stored, long amount, Action action, AutomationType automationType) {
        return super.extract(attachedChemicals, stored, amount, action.combine(!isCreative), automationType);
    }

    /**
     * {@inheritDoc}
     *
     * Note: We are only patching {@link #setStackSize(AttachedChemicals, ChemicalStack, long, Action)}, as both {@link #growStack(long, Action)} and
     * {@link #shrinkStack(long, Action)} are wrapped through this method.
     */
    @Override
    public long setStackSize(AttachedChemicals attachedChemicals, ChemicalStack stored, long amount, Action action) {
        return super.setStackSize(attachedChemicals, stored, amount, action.combine(!isCreative));
    }
}
