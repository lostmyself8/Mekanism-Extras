package com.jerry.mekanism_extras.common.content.plasma;

import net.minecraftforge.registries.tags.IReverseTag;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class EmptyPlasma extends Plasma {
    public EmptyPlasma() {
        super(PlasmaBuilder.builder().hidden());
    }

    @NotNull
    @Override
    protected Optional<IReverseTag<Plasma>> getReverseTag() {
        //Empty gas is in no tags
        return Optional.empty();
    }
}
