package com.jerry.mekextras.common.content.blocktype;

import com.jerry.mekextras.common.block.attribute.AdvancedAttributeUpgradeSupport;
import com.jerry.mekextras.common.block.attribute.ExtraAttributeUpgradeable;
import com.jerry.mekextras.common.registries.ExtraBlocks;
import com.jerry.mekextras.common.tier.AdvancedFactoryTier;
import mekanism.api.text.ILangEntry;
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

        public AdvancedFactoryMachine(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntitySupplier, ILangEntry description, FactoryType factoryType) {
            super(tileEntitySupplier, description);
            // 让高级工厂可以插入新的升级
            add(AdvancedAttributeUpgradeSupport.ADVANCED_MACHINE_UPGRADES);
            add(new AttributeFactoryType(factoryType), new ExtraAttributeUpgradeable(() -> ExtraBlocks.getAdvancedFactory(AdvancedFactoryTier.ABSOLUTE, getFactoryType())));
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
                                                                                                                                           ILangEntry description, FactoryType factoryType) {
            return new AdvancedMachineBuilder<>(new AdvancedFactoryMachine<>(tileEntityRegistrar, description, factoryType));
        }
    }
}
