package com.jerry.mekextras.common.recipe.bin;

import com.jerry.mekextras.common.attachments.containers.item.ExtraComponentBackedBinInventorySlot;
import com.jerry.mekextras.common.inventory.slot.ExtraBinInventorySlot;

import mekanism.api.annotations.NothingNullByDefault;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;

// Note: We don't bother checking anywhere to ensure the bin's item stack size is one, as we only allow bins
// to be in stacks of one anyway. If this changes at some point, then we will need to adjust this recipe
@NothingNullByDefault
public abstract class ExtraBinRecipe extends CustomRecipe {

    protected ExtraBinRecipe(CraftingBookCategory category) {
        super(category);
    }

    protected static ExtraComponentBackedBinInventorySlot convertToSlot(ItemStack binStack) {
        ExtraComponentBackedBinInventorySlot slot = ExtraBinInventorySlot.getForStack(binStack);
        if (slot == null) {
            throw new IllegalStateException("Expected bin stack to have an inventory");
        }
        return slot;
    }

    @Override
    public boolean isIncomplete() {
        return false;
    }
}
