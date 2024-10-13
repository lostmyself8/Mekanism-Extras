package com.jerry.mekanism_extras.common.block;

import com.jerry.mekanism_extras.MekanismExtras;
import com.jerry.mekanism_extras.common.resource.ore.ExtraOreType;
import mekanism.api.text.ILangEntry;
import mekanism.common.block.interfaces.IHasDescription;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import org.jetbrains.annotations.NotNull;

public class ExtraBlockOre extends Block implements IHasDescription {

    private final ExtraOreType ore;
    private String descriptionTranslationKey;

    public ExtraBlockOre(ExtraOreType ore) {
        this(ore, BlockBehaviour.Properties.of(Material.STONE).strength(3, 3).requiresCorrectToolForDrops());
    }

    public ExtraBlockOre(ExtraOreType ore, BlockBehaviour.Properties properties) {
        super(properties);
        this.ore = ore;
    }

    @NotNull
    public String getDescriptionTranslationKey() {
        if (descriptionTranslationKey == null) {
            descriptionTranslationKey = Util.makeDescriptionId("description", MekanismExtras.rl(ore.getResource().getRegistrySuffix() + "_ore"));
        }
        return descriptionTranslationKey;
    }

    @NotNull
    @Override
    public ILangEntry getDescription() {
        return this::getDescriptionTranslationKey;
    }

    @Override
    public int getExpDrop(BlockState state, LevelReader reader, RandomSource random, BlockPos pos, int fortune, int silkTouch) {
        if (ore.getMaxExp() > 0 && silkTouch == 0) {
            return Mth.nextInt(random, ore.getMinExp(), ore.getMaxExp());
        }
        return super.getExpDrop(state, reader, random, pos, fortune, silkTouch);
    }
}
