package com.jerry.mekanism_extras.common.content.blocktype;

import com.jerry.mekanism_extras.api.ExtraUpgrade;
import com.jerry.mekanism_extras.common.block.attribute.ExtraAttributeUpgradeable;
import com.jerry.mekanism_extras.common.registries.ExtraBlocks;
import com.jerry.mekanism_extras.common.tier.ExtraFactoryTier;

import mekanism.api.Upgrade;
import mekanism.api.text.ILangEntry;
import mekanism.common.MekanismLang;
import mekanism.common.block.attribute.AttributeFactoryType;
import mekanism.common.block.attribute.AttributeUpgradeSupport;
import mekanism.common.content.blocktype.BlockTypeTile;
import mekanism.common.content.blocktype.BlockTypeTile.BlockTileBuilder;
import mekanism.common.content.blocktype.FactoryType;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import mekanism.common.tile.base.TileEntityMekanism;

import com.jerry.mekmm.common.block.attribute.AttributeMoreMachineFactoryType;
import com.jerry.mekmm.common.content.blocktype.MoreMachineFactoryType;

import java.util.EnumSet;
import java.util.Objects;
import java.util.function.Supplier;

public class ExtraMachine {

    public static class ExtraFactoryMachine<TILE extends TileEntityMekanism> extends BlockTypeTile<TILE> {

        public ExtraFactoryMachine(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntitySupplier, ILangEntry description, FactoryType factoryType) {
            super(tileEntitySupplier, description);
            add(new AttributeUpgradeSupport(EnumSet.of(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.MUFFLING, ExtraUpgrade.STACK, ExtraUpgrade.CREATIVE)));
            add(new AttributeFactoryType(factoryType), new ExtraAttributeUpgradeable(() -> ExtraBlocks.getAdvancedFactory(ExtraFactoryTier.ABSOLUTE, getFactoryType())));
        }

        public ExtraFactoryMachine(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntitySupplier, ILangEntry description, MoreMachineFactoryType factoryType) {
            super(tileEntitySupplier, description);
            add(new AttributeUpgradeSupport(EnumSet.of(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.MUFFLING, ExtraUpgrade.STACK, ExtraUpgrade.CREATIVE)));
            add(new AttributeMoreMachineFactoryType(factoryType), new ExtraAttributeUpgradeable(() -> ExtraBlocks.getAdvancedFactory(ExtraFactoryTier.ABSOLUTE, getFactoryType())));
        }

        public FactoryType getFactoryType() {
            return Objects.requireNonNull(get(AttributeFactoryType.class)).getFactoryType();
        }

        public MoreMachineFactoryType getMoreMachineFactoryType() {
            return Objects.requireNonNull(get(AttributeMoreMachineFactoryType.class)).getMoreMachineFactoryType();
        }
    }

    public static class ExtraMachineBuilder<MACHINE extends ExtraFactoryMachine<TILE>, TILE extends TileEntityMekanism, T extends ExtraMachineBuilder<MACHINE, TILE, T>> extends BlockTileBuilder<MACHINE, TILE, T> {

        protected ExtraMachineBuilder(MACHINE holder) {
            super(holder);
        }

        public static <TILE extends TileEntityMekanism> ExtraMachineBuilder<ExtraFactoryMachine<TILE>, TILE, ?> createExtraFactoryMachine(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntityRegistrar,
                                                                                                                                          MekanismLang description, FactoryType factoryType) {
            return new ExtraMachineBuilder<>(new ExtraFactoryMachine<>(tileEntityRegistrar, description, factoryType));
        }

        public static <TILE extends TileEntityMekanism> ExtraMachineBuilder<ExtraFactoryMachine<TILE>, TILE, ?> createExtraMoreMachineFactoryMachine(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntityRegistrar,
                                                                                                                                                     ILangEntry description, MoreMachineFactoryType factoryType) {
            return new ExtraMachineBuilder<>(new ExtraFactoryMachine<>(tileEntityRegistrar, description, factoryType));
        }
    }
}
