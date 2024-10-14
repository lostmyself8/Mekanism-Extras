package com.jerry.mekanism_extras.common.registry;

import com.jerry.mekanism_extras.MekanismExtras;
import com.jerry.mekanism_extras.common.ExtraLang;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ExtraTab extends CreativeModeTab {
    public ExtraTab() {
        super(MekanismExtras.MODID);
    }

    @Override
    public @NotNull ItemStack makeIcon() {
        return ExtraItem.INFINITE_CONTROL_CIRCUIT.getItemStack();
    }

    @NotNull
    @Override
    public Component getDisplayName() {
        return ExtraLang.EXTRA_TAB.translate();
    }
}
