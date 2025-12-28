package com.jerry.mekextras.mixin;

import com.jerry.mekextras.api.ExtraUpgrade;

import mekanism.api.Upgrade;
import mekanism.common.content.blocktype.BlockType;
import mekanism.common.content.blocktype.BlockTypeTile;
import mekanism.common.tile.base.TileEntityUpdateable;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.Arrays;
import java.util.List;

@Mixin(value = BlockTypeTile.BlockTileBuilder.class, remap = false)
public abstract class MixinBlockTileBuilder<BLOCK extends BlockTypeTile<TILE>, TILE extends TileEntityUpdateable, T extends BlockTypeTile.BlockTileBuilder<BLOCK, TILE, T>>
                                           extends BlockType.BlockTypeBuilder<BLOCK, T> {

    protected MixinBlockTileBuilder(BLOCK holder) {
        super(holder);
    }

    @ModifyArg(method = "withSupportedUpgrades", at = @At(value = "INVOKE", target = "Lmekanism/common/block/attribute/AttributeUpgradeSupport;create([Lmekanism/api/Upgrade;)Lmekanism/common/block/attribute/AttributeUpgradeSupport;"))
    public Upgrade[] mixinWithSupportedUpgrades(Upgrade[] upgrade) {
        List<Upgrade> upgrades = Arrays.stream(upgrade).toList();
        if (upgrades.contains(Upgrade.ANCHOR)) {
            return new Upgrade[] { Upgrade.SPEED, Upgrade.ENERGY, Upgrade.ANCHOR, Upgrade.STONE_GENERATOR, ExtraUpgrade.CREATIVE };
        }
        return upgrade;
    }
}
