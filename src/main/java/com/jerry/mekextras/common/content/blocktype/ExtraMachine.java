package com.jerry.mekextras.common.content.blocktype;

import com.jerry.mekaf.common.block.attribute.AttributeAdvancedFactoryType;
import com.jerry.mekaf.common.content.blocktype.AdvancedFactoryType;
import com.jerry.mekextras.common.block.attribute.ExtraAttributeUpgradeSupport;
import com.jerry.mekextras.common.block.attribute.ExtraAttributeUpgradeable;
import com.jerry.mekextras.common.integration.mekaf.registries.ExtraAdvancedFactoryBlocks;
import com.jerry.mekextras.common.integration.mekmm.registries.ExtraMoreMachineBlocks;
import com.jerry.mekextras.common.registries.ExtraBlocks;
import com.jerry.mekextras.common.tier.ExtraFactoryTier;
import com.jerry.mekmm.common.block.attribute.MoreMachineAttributeFactoryType;
import com.jerry.mekmm.common.content.blocktype.MoreMachineFactoryType;
import mekanism.api.text.ILangEntry;
import mekanism.common.block.attribute.AttributeFactoryType;
import mekanism.common.content.blocktype.BlockTypeTile;
import mekanism.common.content.blocktype.FactoryType;
import mekanism.common.content.blocktype.Machine;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import mekanism.common.tile.base.TileEntityMekanism;

import java.util.Objects;
import java.util.function.Supplier;

public class ExtraMachine {

    public static class ExtraFactoryMachine<TILE extends TileEntityMekanism> extends Machine<TILE> {

        public ExtraFactoryMachine(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntitySupplier, ILangEntry description, FactoryType factoryType) {
            super(tileEntitySupplier, description);
            // 让额外工厂可以插入新的升级
            add(ExtraAttributeUpgradeSupport.EXTRA_MACHINE_UPGRADES);
            add(new AttributeFactoryType(factoryType), new ExtraAttributeUpgradeable(() -> ExtraBlocks.getExtraFactory(ExtraFactoryTier.ABSOLUTE, getFactoryType())));
        }

        public ExtraFactoryMachine(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntitySupplier, ILangEntry description, AdvancedFactoryType factoryType) {
            super(tileEntitySupplier, description);
            add(new AttributeAdvancedFactoryType(factoryType), new ExtraAttributeUpgradeable(() -> ExtraAdvancedFactoryBlocks.getAdvancedFactory(ExtraFactoryTier.ABSOLUTE, getAdvancedFactoryType())));
        }

        public ExtraFactoryMachine(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntitySupplier, ILangEntry description, MoreMachineFactoryType factoryType) {
            super(tileEntitySupplier, description);
            add(new MoreMachineAttributeFactoryType(factoryType), new ExtraAttributeUpgradeable(() -> ExtraMoreMachineBlocks.getMoreMachineFactory(ExtraFactoryTier.ABSOLUTE, getMoreMachineFactoryType())));
        }

        public FactoryType getFactoryType() {
            return Objects.requireNonNull(get(AttributeFactoryType.class)).getFactoryType();
        }

        public AdvancedFactoryType getAdvancedFactoryType() {
            return Objects.requireNonNull(get(AttributeAdvancedFactoryType.class)).getAdvancedFactoryType();
        }

        public MoreMachineFactoryType getMoreMachineFactoryType() {
            return Objects.requireNonNull(get(MoreMachineAttributeFactoryType.class)).getMoreMachineFactoryType();
        }
    }

    public static class ExtraMachineBuilder<MACHINE extends Machine<TILE>, TILE extends TileEntityMekanism, T extends ExtraMachineBuilder<MACHINE, TILE, T>> extends BlockTypeTile.BlockTileBuilder<MACHINE, TILE, T> {

        protected ExtraMachineBuilder(MACHINE holder) {
            super(holder);
        }

        public static <TILE extends TileEntityMekanism> ExtraMachineBuilder<ExtraFactoryMachine<TILE>, TILE, ?> createExtraFactoryMachine(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntityRegistrar,
                                                                                                                                          ILangEntry description, FactoryType factoryType) {
            return new ExtraMachineBuilder<>(new ExtraFactoryMachine<>(tileEntityRegistrar, description, factoryType));
        }

        public static <TILE extends TileEntityMekanism> ExtraMachineBuilder<ExtraFactoryMachine<TILE>, TILE, ?> createExtraAdvancedFactoryMachine(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntityRegistrar,
                                                                                                                                          ILangEntry description, AdvancedFactoryType factoryType) {
            return new ExtraMachineBuilder<>(new ExtraFactoryMachine<>(tileEntityRegistrar, description, factoryType));
        }

        public static <TILE extends TileEntityMekanism> ExtraMachineBuilder<ExtraFactoryMachine<TILE>, TILE, ?> createExtraMoreMachineFactoryMachine(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntityRegistrar,
                                                                                                                                                  ILangEntry description, MoreMachineFactoryType factoryType) {
            return new ExtraMachineBuilder<>(new ExtraFactoryMachine<>(tileEntityRegistrar, description, factoryType));
        }
    }
}
