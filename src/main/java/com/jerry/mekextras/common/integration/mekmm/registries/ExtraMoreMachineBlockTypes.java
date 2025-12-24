package com.jerry.mekextras.common.integration.mekmm.registries;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.jerry.mekextras.common.block.attribute.ExtraAttributeUpgradeSupport;
import com.jerry.mekextras.common.content.blocktype.ExtraMachine.ExtraMachineBuilder;
import com.jerry.mekextras.common.content.blocktype.ExtraMachine.ExtraFactoryMachine;
import com.jerry.mekextras.common.integration.mekmm.content.blocktype.ExtraMoreMachineFactory;
import com.jerry.mekextras.common.integration.mekmm.content.blocktype.ExtraMoreMachineFactory.ExtraMoreMachineFactoryBuilder;
import com.jerry.mekextras.common.tier.ExtraFactoryTier;
import com.jerry.mekextras.common.util.ExtraEnumUtils;
import com.jerry.mekmm.common.MoreMachineLang;
import com.jerry.mekmm.common.config.MoreMachineConfig;
import com.jerry.mekmm.common.content.blocktype.MoreMachineFactoryType;
import com.jerry.mekmm.common.registries.MoreMachineContainerTypes;
import com.jerry.mekmm.common.registries.MoreMachineTileEntityTypes;
import com.jerry.mekmm.common.tile.machine.*;
import com.jerry.mekmm.common.util.MoreMachineEnumUtils;
import mekanism.common.block.attribute.*;
import mekanism.common.registries.MekanismSounds;

public class ExtraMoreMachineBlockTypes {

    private ExtraMoreMachineBlockTypes() {}

    private static final Table<ExtraFactoryTier, MoreMachineFactoryType, ExtraMoreMachineFactory<?>> MM_FACTORIES = HashBasedTable.create();

    // Recycler
    public static final ExtraFactoryMachine<TileEntityRecycler> RECYCLER = ExtraMachineBuilder
            .createExtraMoreMachineFactoryMachine(() -> MoreMachineTileEntityTypes.RECYCLER, MoreMachineLang.DESCRIPTION_RECYCLER, MoreMachineFactoryType.RECYCLING)
            .withGui(() -> MoreMachineContainerTypes.RECYCLER)
            .withSound(MekanismSounds.PRECISION_SAWMILL)
            .withEnergyConfig(MoreMachineConfig.usage.recycler, MoreMachineConfig.storage.recycler)
            .with(ExtraAttributeUpgradeSupport.EXTRA_MACHINE_UPGRADES)
            .build();

    // Planting Station
    public static final ExtraFactoryMachine<TileEntityPlantingStation> PLANTING_STATION = ExtraMachineBuilder
            .createExtraMoreMachineFactoryMachine(() -> MoreMachineTileEntityTypes.PLANTING_STATION, MoreMachineLang.DESCRIPTION_PLANTING_STATION, MoreMachineFactoryType.PLANTING_STATION)
            .withGui(() -> MoreMachineContainerTypes.PLANTING_STATION)
            .withEnergyConfig(MoreMachineConfig.usage.plantingStation, MoreMachineConfig.storage.plantingStation)
            .with(ExtraAttributeUpgradeSupport.EXTRA_ADVANCED_MACHINE_UPGRADES)
            .withSound(MekanismSounds.ENRICHMENT_CHAMBER)
            .with(AttributeHasBounding.ABOVE_ONLY)
            .build();

    // CNC Stamper
    public static final ExtraFactoryMachine<TileEntityStamper> CNC_STAMPER = ExtraMachineBuilder
            .createExtraMoreMachineFactoryMachine(() -> MoreMachineTileEntityTypes.CNC_STAMPER, MoreMachineLang.DESCRIPTION_CNC_STAMPER, MoreMachineFactoryType.CNC_STAMPING)
            .withGui(() -> MoreMachineContainerTypes.CNC_STAMPER)
            .withEnergyConfig(MoreMachineConfig.usage.cnc_stamper, MoreMachineConfig.storage.cnc_stamper)
            .with(ExtraAttributeUpgradeSupport.EXTRA_MACHINE_UPGRADES)
            .withSound(MekanismSounds.CRUSHER)
            .build();

    // CNC Lathe
    public static final ExtraFactoryMachine<TileEntityLathe> CNC_LATHE = ExtraMachineBuilder
            .createExtraMoreMachineFactoryMachine(() -> MoreMachineTileEntityTypes.CNC_LATHE, MoreMachineLang.DESCRIPTION_CNC_LATHE, MoreMachineFactoryType.CNC_LATHING)
            .withGui(() -> MoreMachineContainerTypes.CNC_LATHE)
            .withEnergyConfig(MoreMachineConfig.usage.cnc_lathe, MoreMachineConfig.storage.cnc_lathe)
            .with(ExtraAttributeUpgradeSupport.EXTRA_MACHINE_UPGRADES)
            .withSound(MekanismSounds.OSMIUM_COMPRESSOR)
            .build();

    // CNC Rolling Mill
    public static final ExtraFactoryMachine<TileEntityRollingMill> CNC_ROLLING_MILL = ExtraMachineBuilder
            .createExtraMoreMachineFactoryMachine(() -> MoreMachineTileEntityTypes.CNC_ROLLING_MILL, MoreMachineLang.DESCRIPTION_CNC_ROLLING_MILL, MoreMachineFactoryType.CNC_ROLLING_MILL)
            .withGui(() -> MoreMachineContainerTypes.CNC_ROLLING_MILL)
            .withEnergyConfig(MoreMachineConfig.usage.cnc_rollingMill, MoreMachineConfig.storage.cnc_rollingMill)
            .with(ExtraAttributeUpgradeSupport.EXTRA_MACHINE_UPGRADES)
            .withSound(MekanismSounds.COMBINER)
            .build();

    // Replicator
    public static final ExtraFactoryMachine<TileEntityReplicator> REPLICATOR = ExtraMachineBuilder
            .createExtraMoreMachineFactoryMachine(() -> MoreMachineTileEntityTypes.REPLICATOR, MoreMachineLang.DESCRIPTION_REPLICATOR, MoreMachineFactoryType.REPLICATING)
            .withGui(() -> MoreMachineContainerTypes.REPLICATOR)
            .withEnergyConfig(MoreMachineConfig.usage.itemReplicator, MoreMachineConfig.storage.itemReplicator)
            .with(ExtraAttributeUpgradeSupport.EXTRA_MACHINE_UPGRADES)
            .withSound(MekanismSounds.PURIFICATION_CHAMBER)
            .build();

    static {
        for (ExtraFactoryTier tier : ExtraEnumUtils.EXTRA_FACTORY_TIERS) {
            for (MoreMachineFactoryType type : MoreMachineEnumUtils.MM_FACTORY_TYPES) {
                MM_FACTORIES.put(tier, type, ExtraMoreMachineFactoryBuilder.createMoreMachineFactory(() -> ExtraMoreMachineTileEntityTypes.getMoreMachineFactoryTile(tier, type), type, tier).build());
            }
        }
    }

    public static ExtraMoreMachineFactory<?> getMoreMachineFactory(ExtraFactoryTier tier, MoreMachineFactoryType type) {
        return MM_FACTORIES.get(tier, type);
    }
}
