package com.jerry.mekextras.common.block;

import mekanism.api.text.ILangEntry;
import mekanism.common.block.interfaces.IHasDescription;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

public class BlockExtraOre extends Block implements IHasDescription {

    private String descriptionTranslationKey;

    public BlockExtraOre(Properties properties) {
        super(properties);
    }

    @NotNull
    public String getDescriptionTranslationKey() {
        if (descriptionTranslationKey == null) {
//            descriptionTranslationKey = Util.makeDescriptionId("description", MekanismExtras.rl(ore.getResource().getRegistrySuffix() + "_ore"));
        }
        return descriptionTranslationKey;
    }

    @Override
    public @NotNull ILangEntry getDescription() {
        return this::getDescriptionTranslationKey;
    }
}
