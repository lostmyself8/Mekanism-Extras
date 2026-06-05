package com.jerry.mekanism_extras.common.integration.mekmm.registries;

import com.jerry.mekanism_extras.api.ExtraUpgrade;
import com.jerry.mekanism_extras.common.content.blocktype.ExtraMachine.ExtraFactoryMachine;
import com.jerry.mekanism_extras.common.content.blocktype.ExtraMachine.ExtraMachineBuilder;
import com.jerry.mekanism_extras.common.integration.mekmm.content.blocktype.ExtraMoreMachineFactory;
import com.jerry.mekanism_extras.common.integration.mekmm.content.blocktype.ExtraMoreMachineFactory.ExtraMoreMachineFactoryBuilder;
import com.jerry.mekanism_extras.common.tier.ExtraFactoryTier;
import com.jerry.mekanism_extras.common.util.ExtraEnumUtils;

import mekanism.api.Upgrade;
import mekanism.common.registries.MekanismSounds;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.jerry.mekmm.common.MoreMachineLang;
import com.jerry.mekmm.common.config.MoreMachineConfig;
import com.jerry.mekmm.common.content.blocktype.MoreMachineFactoryType;
import com.jerry.mekmm.common.registries.MoreMachineContainerTypes;
import com.jerry.mekmm.common.registries.MoreMachineTileEntityTypes;
import com.jerry.mekmm.common.tile.machine.*;
import com.jerry.mekmm.common.util.MoreMachineEnumUtils;

import java.util.EnumSet;

public class ExtraMoreMachineBlockTypes {

    private ExtraMoreMachineBlockTypes() {}

    private static final Table<ExtraFactoryTier, MoreMachineFactoryType, ExtraMoreMachineFactory<?>> FACTORIES = HashBasedTable.create();

    // Recycler
    public static final ExtraFactoryMachine<TileEntityRecycler> RECYCLER = ExtraMachineBuilder
            .createExtraMoreMachineFactoryMachine(() -> MoreMachineTileEntityTypes.RECYCLER, MoreMachineLang.DESCRIPTION_RECYCLER, MoreMachineFactoryType.RECYCLING)
            .withSound(MekanismSounds.PRECISION_SAWMILL)
            .withEnergyConfig(MoreMachineConfig.usage.recycler, MoreMachineConfig.storage.recycler)
            .build();
    // Planting Station
    public static final ExtraFactoryMachine<TileEntityPlantingStation> PLANTING_STATION = ExtraMachineBuilder
            .createExtraMoreMachineFactoryMachine(() -> MoreMachineTileEntityTypes.PLANTING_STATION, MoreMachineLang.DESCRIPTION_PLANTING_STATION, MoreMachineFactoryType.PLANTING)
            .withSound(MekanismSounds.PRECISION_SAWMILL)
            .withEnergyConfig(MoreMachineConfig.usage.plantingStation, MoreMachineConfig.storage.plantingStation)
            .withSupportedUpgrades(EnumSet.of(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.MUFFLING, Upgrade.GAS, ExtraUpgrade.STACK, ExtraUpgrade.CREATIVE))
            .withBounding((pos, state, builder) -> builder.add(pos.above()))
            .build();
    // CNC Stamper
    public static final ExtraFactoryMachine<TileEntityStamper> CNC_STAMPER = ExtraMachineBuilder
            .createExtraMoreMachineFactoryMachine(() -> MoreMachineTileEntityTypes.CNC_STAMPER, MoreMachineLang.DESCRIPTION_CNC_STAMPER, MoreMachineFactoryType.CNC_STAMPING)
            .withSound(MekanismSounds.CRUSHER)
            .withEnergyConfig(MoreMachineConfig.usage.cnc_stamper, MoreMachineConfig.storage.cnc_stamper)
            .withSupportedUpgrades(EnumSet.of(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.MUFFLING, ExtraUpgrade.STACK, ExtraUpgrade.CREATIVE))
            .build();
    // CNC Lathe
    public static final ExtraFactoryMachine<TileEntityLathe> CNC_LATHE = ExtraMachineBuilder
            .createExtraMoreMachineFactoryMachine(() -> MoreMachineTileEntityTypes.CNC_LATHE, MoreMachineLang.DESCRIPTION_CNC_LATHE, MoreMachineFactoryType.CNC_LATHING)
            .withSound(MekanismSounds.CRUSHER)
            .withEnergyConfig(MoreMachineConfig.usage.cnc_lathe, MoreMachineConfig.storage.cnc_lathe)
            .withSupportedUpgrades(EnumSet.of(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.MUFFLING, ExtraUpgrade.STACK, ExtraUpgrade.CREATIVE))
            .build();
    // CNC Rolling Mill
    public static final ExtraFactoryMachine<TileEntityRollingMill> CNC_ROLLING_MILL = ExtraMachineBuilder
            .createExtraMoreMachineFactoryMachine(() -> MoreMachineTileEntityTypes.CNC_ROLLING_MILL, MoreMachineLang.DESCRIPTION_CNC_ROLLING_MILL, MoreMachineFactoryType.CNC_ROLLING_MILL)
            .withSound(MekanismSounds.CRUSHER)
            .withEnergyConfig(MoreMachineConfig.usage.cnc_rollingMill, MoreMachineConfig.storage.cnc_rollingMill)
            .withSupportedUpgrades(EnumSet.of(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.MUFFLING, ExtraUpgrade.STACK, ExtraUpgrade.CREATIVE))
            .build();
    // Replicator
    public static final ExtraFactoryMachine<TileEntityReplicator> REPLICATOR = ExtraMachineBuilder
            .createExtraMoreMachineFactoryMachine(() -> MoreMachineTileEntityTypes.REPLICATOR, MoreMachineLang.DESCRIPTION_REPLICATOR, MoreMachineFactoryType.REPLICATING)
            .withGui(() -> MoreMachineContainerTypes.REPLICATOR)
            .withSound(MekanismSounds.PRECISION_SAWMILL)
            .withEnergyConfig(MoreMachineConfig.usage.itemReplicator, MoreMachineConfig.storage.itemReplicator)
            .withSupportedUpgrades(EnumSet.of(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.MUFFLING, ExtraUpgrade.STACK, ExtraUpgrade.CREATIVE))
            .build();

    static {
        for (ExtraFactoryTier tier : ExtraEnumUtils.EXTRA_FACTORY_TIERS) {
            for (MoreMachineFactoryType type : MoreMachineEnumUtils.MM_FACTORY_TYPES) {
                FACTORIES.put(tier, type, ExtraMoreMachineFactoryBuilder.createExtraMoreMachineFactory(() -> ExtraMoreMachineTileEntityTypes.getExtraMoreMachineFactoryTile(tier, type), type, tier).build());
            }
        }
    }

    public static ExtraMoreMachineFactory<?> getExtraMoreMachineFactory(ExtraFactoryTier tier, MoreMachineFactoryType type) {
        return FACTORIES.get(tier, type);
    }
}
