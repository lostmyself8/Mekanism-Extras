package com.jerry.mekextras.common.attachments.containers.chemical.infuse_type;

import com.jerry.mekextras.common.item.block.ExtraItemBlockChemicalTank;
import com.jerry.mekextras.common.tier.CTTier;
import mekanism.api.Action;
import mekanism.api.AutomationType;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.chemical.ChemicalTankBuilder;
import mekanism.api.chemical.infuse.InfusionStack;
import mekanism.common.attachments.containers.ContainerType;
import mekanism.common.attachments.containers.chemical.infuse.AttachedInfuseTypes;
import mekanism.common.attachments.containers.chemical.infuse.ComponentBackedInfusionTank;
import net.minecraft.world.item.ItemStack;

@NothingNullByDefault
public class ExtraComponentBackedChemicalTankInfusionTank extends ComponentBackedInfusionTank {

    private final boolean isCreative;

    public static ExtraComponentBackedChemicalTankInfusionTank create(ContainerType<?, ?, ?> ignored, ItemStack attachedTo, int tankIndex) {
        if (!(attachedTo.getItem() instanceof ExtraItemBlockChemicalTank item)) {
            throw new IllegalStateException("Attached to should always be a chemical tank item");
        }
        return new ExtraComponentBackedChemicalTankInfusionTank(attachedTo, tankIndex, item.getAdvanceTier());
    }

    private ExtraComponentBackedChemicalTankInfusionTank(ItemStack attachedTo, int tankIndex, CTTier tier) {
        super(attachedTo, tankIndex, ChemicalTankBuilder.INFUSION.alwaysTrueBi, ChemicalTankBuilder.INFUSION.alwaysTrueBi, ChemicalTankBuilder.INFUSION.alwaysTrue,
                tier::getOutput, tier::getStorage, null);
        isCreative = false;
    }

    @Override
    public InfusionStack insert(InfusionStack stack, Action action, AutomationType automationType) {
        return super.insert(stack, action.combine(!isCreative), automationType);
    }

    @Override
    public InfusionStack extract(AttachedInfuseTypes attachedInfuseTypes, InfusionStack stored, long amount, Action action, AutomationType automationType) {
        return super.extract(attachedInfuseTypes, stored, amount, action.combine(!isCreative), automationType);
    }

    /**
     * {@inheritDoc}
     *
     * Note: We are only patching {@link #setStackSize(AttachedInfuseTypes, InfusionStack, long, Action)}, as both {@link #growStack(long, Action)} and
     * {@link #shrinkStack(long, Action)} are wrapped through this method.
     */
    @Override
    public long setStackSize(AttachedInfuseTypes attachedInfuseTypes, InfusionStack stored, long amount, Action action) {
        return super.setStackSize(attachedInfuseTypes, stored, amount, action.combine(!isCreative));
    }
}
