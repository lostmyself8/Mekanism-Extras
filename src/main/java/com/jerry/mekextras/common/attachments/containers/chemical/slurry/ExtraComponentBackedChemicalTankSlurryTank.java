package com.jerry.mekextras.common.attachments.containers.chemical.slurry;

import com.jerry.mekextras.common.item.block.ExtraItemBlockChemicalTank;
import com.jerry.mekextras.common.tier.CTTier;
import mekanism.api.Action;
import mekanism.api.AutomationType;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.chemical.ChemicalTankBuilder;
import mekanism.api.chemical.slurry.SlurryStack;
import mekanism.common.attachments.containers.ContainerType;
import mekanism.common.attachments.containers.chemical.slurry.AttachedSlurries;
import mekanism.common.attachments.containers.chemical.slurry.ComponentBackedSlurryTank;
import net.minecraft.world.item.ItemStack;

@NothingNullByDefault
public class ExtraComponentBackedChemicalTankSlurryTank extends ComponentBackedSlurryTank {

    private final boolean isCreative;

    public static ExtraComponentBackedChemicalTankSlurryTank create(ContainerType<?, ?, ?> ignored, ItemStack attachedTo, int tankIndex) {
        if (!(attachedTo.getItem() instanceof ExtraItemBlockChemicalTank item)) {
            throw new IllegalStateException("Attached to should always be a chemical tank item");
        }
        return new ExtraComponentBackedChemicalTankSlurryTank(attachedTo, tankIndex, item.getAdvanceTier());
    }

    private ExtraComponentBackedChemicalTankSlurryTank(ItemStack attachedTo, int tankIndex, CTTier tier) {
        super(attachedTo, tankIndex, ChemicalTankBuilder.SLURRY.alwaysTrueBi, ChemicalTankBuilder.SLURRY.alwaysTrueBi, ChemicalTankBuilder.SLURRY.alwaysTrue,
                tier::getOutput, tier::getStorage, null);
        isCreative = false;
    }

    @Override
    public SlurryStack insert(SlurryStack stack, Action action, AutomationType automationType) {
        return super.insert(stack, action.combine(!isCreative), automationType);
    }

    @Override
    public SlurryStack extract(AttachedSlurries attachedSlurries, SlurryStack stored, long amount, Action action, AutomationType automationType) {
        return super.extract(attachedSlurries, stored, amount, action.combine(!isCreative), automationType);
    }

    /**
     * {@inheritDoc}
     *
     * Note: We are only patching {@link #setStackSize(AttachedSlurries, SlurryStack, long, Action)}, as both {@link #growStack(long, Action)} and
     * {@link #shrinkStack(long, Action)} are wrapped through this method.
     */
    @Override
    public long setStackSize(AttachedSlurries attachedSlurries, SlurryStack stored, long amount, Action action) {
        return super.setStackSize(attachedSlurries, stored, amount, action.combine(!isCreative));
    }
}
