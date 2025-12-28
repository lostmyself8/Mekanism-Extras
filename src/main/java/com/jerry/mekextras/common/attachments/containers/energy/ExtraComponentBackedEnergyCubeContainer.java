package com.jerry.mekextras.common.attachments.containers.energy;

import com.jerry.mekextras.common.item.block.ItemBlockExtraEnergyCube;
import com.jerry.mekextras.common.tier.ECTier;
import mekanism.api.Action;
import mekanism.api.AutomationType;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.functions.ConstantPredicates;
import mekanism.common.attachments.containers.ContainerType;
import mekanism.common.attachments.containers.energy.ComponentBackedEnergyContainer;
import net.minecraft.world.item.ItemStack;

@NothingNullByDefault
public class ExtraComponentBackedEnergyCubeContainer extends ComponentBackedEnergyContainer {

    public static ExtraComponentBackedEnergyCubeContainer create(ContainerType<?, ?, ?> ignored, ItemStack attachedTo, int containerIndex) {
        if (!(attachedTo.getItem() instanceof ItemBlockExtraEnergyCube item)) {
            throw new IllegalStateException("Attached to should always be an energy cube item");
        }
        return new ExtraComponentBackedEnergyCubeContainer(attachedTo, containerIndex, item.getAdvancedTier());
    }

    private final boolean isCreative;

    public ExtraComponentBackedEnergyCubeContainer(ItemStack attachedTo, int containerIndex, ECTier tier) {
        super(attachedTo, containerIndex, ConstantPredicates.alwaysTrue(), ConstantPredicates.alwaysTrue(), tier::getOutput, tier::getMaxEnergy);
        isCreative = false;
    }

    @Override
    public long insert(long amount, Action action, AutomationType automationType) {
        return super.insert(amount, action.combine(!isCreative), automationType);
    }

    @Override
    public long extract(long amount, Action action, AutomationType automationType) {
        return super.extract(amount, action.combine(!isCreative), automationType);
    }
}
