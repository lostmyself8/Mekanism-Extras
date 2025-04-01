package com.jerry.mekanism_extras.common.content.blocktype;

import com.jerry.mekanism_extras.common.block.attribute.ExtraAttributeUpgradeable;
import com.jerry.mekanism_extras.common.registry.ExtraBlock;
import com.jerry.mekanism_extras.common.tier.AdvancedFactoryTier;
import mekanism.common.MekanismLang;
import mekanism.common.block.attribute.AttributeFactoryType;
import mekanism.common.content.blocktype.BlockTypeTile;
import mekanism.common.content.blocktype.FactoryType;
import mekanism.common.content.blocktype.Machine;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import mekanism.common.tile.base.TileEntityMekanism;

import java.util.Objects;
import java.util.function.Supplier;

public class AdvancedMachine {

    public static class AdvancedFactoryMachine<TILE extends TileEntityMekanism> extends Machine<TILE> {

        public AdvancedFactoryMachine(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntitySupplier, MekanismLang description, FactoryType factoryType) {
            super(tileEntitySupplier, description);
            // 原本是机器到基础工厂，但我不需要这个（或许我可以跨Tier进行升级但似乎有些麻烦，所以我现在不打算用它）
            add(new AttributeFactoryType(factoryType), new ExtraAttributeUpgradeable(() -> ExtraBlock.getAdvancedFactory(AdvancedFactoryTier.ABSOLUTE, getFactoryType())));
        }


        public FactoryType getFactoryType() {
            return Objects.requireNonNull(get(AttributeFactoryType.class)).getFactoryType();
        }
    }

    public static class AdvancedMachineBuilder<MACHINE extends Machine<TILE>, TILE extends TileEntityMekanism, T extends AdvancedMachineBuilder<MACHINE, TILE, T>> extends BlockTypeTile.BlockTileBuilder<MACHINE, TILE, T> {

        protected AdvancedMachineBuilder(MACHINE holder) {
            super(holder);
        }

        public static <TILE extends TileEntityMekanism> AdvancedMachineBuilder<AdvancedFactoryMachine<TILE>, TILE, ?> createAdvancedFactoryMachine(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntityRegistrar,
                                                                                                                                                   MekanismLang description, FactoryType factoryType) {
            return new AdvancedMachineBuilder<>(new AdvancedFactoryMachine<>(tileEntityRegistrar, description, factoryType));
        }
    }
}
