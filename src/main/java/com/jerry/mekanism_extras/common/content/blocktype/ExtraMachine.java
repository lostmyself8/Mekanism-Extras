package com.jerry.mekanism_extras.common.content.blocktype;

import com.jerry.mekanism_extras.api.ExtraUpgrade;
import com.jerry.mekanism_extras.common.block.attribute.ExtraAttributeUpgradeable;
import com.jerry.mekanism_extras.common.integration.mekaf.registries.ExtraAdvancedFactoryBlocks;
import com.jerry.mekanism_extras.common.integration.mekmm.registries.ExtraMoreMachineBlocks;
import com.jerry.mekanism_extras.common.registries.ExtraBlocks;
import com.jerry.mekanism_extras.common.tier.ExtraFactoryTier;

import mekanism.api.Upgrade;
import mekanism.api.text.ILangEntry;
import mekanism.common.MekanismLang;
import mekanism.common.block.attribute.*;
import mekanism.common.content.blocktype.BlockTypeTile;
import mekanism.common.content.blocktype.FactoryType;
import mekanism.common.lib.math.Pos3D;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import mekanism.common.tile.base.TileEntityMekanism;

import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;

import com.jerry.mekaf.common.block.attribute.AttributeAdvancedFactoryType;
import com.jerry.mekaf.common.content.blocktype.AdvancedFactoryType;
import com.jerry.mekmm.common.block.attribute.AttributeMoreMachineFactoryType;
import com.jerry.mekmm.common.content.blocktype.MoreMachineFactoryType;

import java.util.EnumSet;
import java.util.Objects;
import java.util.function.Supplier;

public class ExtraMachine<TILE extends TileEntityMekanism> extends BlockTypeTile<TILE> {

    public ExtraMachine(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntityRegistrar, ILangEntry description) {
        super(tileEntityRegistrar, description);
        add((new AttributeParticleFX())
                .add(ParticleTypes.SMOKE, (rand) -> new Pos3D(rand.nextFloat() * 0.6F - 0.3F, rand.nextFloat() * 6.0F / 16.0F, 0.52))
                .add(DustParticleOptions.REDSTONE, (rand) -> new Pos3D(rand.nextFloat() * 0.6F - 0.3F, rand.nextFloat() * 6.0F / 16.0F, 0.52)));
        add(Attributes.ACTIVE_LIGHT, new AttributeStateFacing(), Attributes.INVENTORY, Attributes.SECURITY, Attributes.REDSTONE, Attributes.COMPARATOR);
    }

    public static class ExtraFactoryMachine<TILE extends TileEntityMekanism> extends ExtraMachine<TILE> {

        public ExtraFactoryMachine(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntitySupplier, ILangEntry description, FactoryType factoryType) {
            super(tileEntitySupplier, description);
            add(new AttributeUpgradeSupport(EnumSet.of(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.MUFFLING, ExtraUpgrade.STACK, ExtraUpgrade.CREATIVE)));
            add(new AttributeFactoryType(factoryType), new ExtraAttributeUpgradeable(() -> ExtraBlocks.getAdvancedFactory(ExtraFactoryTier.ABSOLUTE, getFactoryType())));
        }

        public ExtraFactoryMachine(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntitySupplier, ILangEntry description, MoreMachineFactoryType factoryType) {
            super(tileEntitySupplier, description);
            add(new AttributeUpgradeSupport(EnumSet.of(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.MUFFLING, ExtraUpgrade.CREATIVE)));
            add(new AttributeMoreMachineFactoryType(factoryType), new ExtraAttributeUpgradeable(() -> ExtraMoreMachineBlocks.getExtraMoreMachineFactory(ExtraFactoryTier.ABSOLUTE, getMoreMachineFactoryType())));
        }

        public ExtraFactoryMachine(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntitySupplier, ILangEntry description, AdvancedFactoryType factoryType) {
            super(tileEntitySupplier, description);
            add(new AttributeUpgradeSupport(EnumSet.of(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.MUFFLING, ExtraUpgrade.CREATIVE)));
            add(new AttributeAdvancedFactoryType(factoryType), new ExtraAttributeUpgradeable(() -> ExtraAdvancedFactoryBlocks.getExtraAdvancedFactory(ExtraFactoryTier.ABSOLUTE, getAdvancedFactoryType())));
        }

        public FactoryType getFactoryType() {
            return Objects.requireNonNull(get(AttributeFactoryType.class)).getFactoryType();
        }

        public MoreMachineFactoryType getMoreMachineFactoryType() {
            return Objects.requireNonNull(get(AttributeMoreMachineFactoryType.class)).getMoreMachineFactoryType();
        }

        public AdvancedFactoryType getAdvancedFactoryType() {
            return Objects.requireNonNull(get(AttributeAdvancedFactoryType.class)).getAdvancedFactoryType();
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

        public static <TILE extends TileEntityMekanism> ExtraMachineBuilder<ExtraFactoryMachine<TILE>, TILE, ?> createExtraAdvancedFactoryMachine(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntityRegistrar,
                                                                                                                                                  ILangEntry description, AdvancedFactoryType factoryType) {
            return new ExtraMachineBuilder<>(new ExtraFactoryMachine<>(tileEntityRegistrar, description, factoryType));
        }
    }
}
