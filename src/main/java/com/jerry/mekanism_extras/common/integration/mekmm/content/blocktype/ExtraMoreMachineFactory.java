package com.jerry.mekanism_extras.common.integration.mekmm.content.blocktype;

import com.jerry.mekanism_extras.common.block.attribute.ExtraAttributeTier;
import com.jerry.mekanism_extras.common.block.attribute.ExtraAttributeUpgradeable;
import com.jerry.mekanism_extras.common.content.blocktype.ExtraMachine.ExtraFactoryMachine;
import com.jerry.mekanism_extras.common.content.blocktype.ExtraMachine.ExtraMachineBuilder;
import com.jerry.mekanism_extras.common.integration.mekmm.registries.ExtraMoreMachineBlockTypes;
import com.jerry.mekanism_extras.common.integration.mekmm.registries.ExtraMoreMachineBlocks;
import com.jerry.mekanism_extras.common.integration.mekmm.registries.ExtraMoreMachineContainerTypes;
import com.jerry.mekanism_extras.common.integration.mekmm.tile.TileEntityExtraMoreMachineFactory;
import com.jerry.mekanism_extras.common.tier.ExtraFactoryTier;
import com.jerry.mekanism_extras.common.util.ExtraEnumUtils;

import mekanism.common.MekanismLang;
import mekanism.common.block.attribute.*;
import mekanism.common.inventory.container.MekanismContainer;
import mekanism.common.lib.math.Pos3D;
import mekanism.common.registration.impl.ContainerTypeRegistryObject;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;

import net.minecraft.core.particles.ParticleTypes;

import com.jerry.mekmm.common.block.attribute.AttributeMoreMachineFactoryType;
import com.jerry.mekmm.common.content.blocktype.MoreMachineBlockShapes;
import com.jerry.mekmm.common.content.blocktype.MoreMachineFactoryType;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class ExtraMoreMachineFactory<TILE extends TileEntityExtraMoreMachineFactory<?>> extends ExtraFactoryMachine<TILE> {

    private final ExtraFactoryMachine<?> origMachine;

    public ExtraMoreMachineFactory(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntityRegistrar, Supplier<ContainerTypeRegistryObject<? extends MekanismContainer>> containerRegistrar,
                                   ExtraFactoryMachine<?> origMachine, ExtraFactoryTier tier) {
        super(tileEntityRegistrar, MekanismLang.DESCRIPTION_FACTORY, origMachine.getMoreMachineFactoryType());
        this.origMachine = origMachine;
        setMachineData(tier);
        add(new AttributeGui(containerRegistrar, null), new ExtraAttributeTier<>(tier));

        // 添加升级后的方块
        if (tier.ordinal() < ExtraEnumUtils.EXTRA_FACTORY_TIERS.length - 1) {
            add(new ExtraAttributeUpgradeable(() -> ExtraMoreMachineBlocks.getExtraMoreMachineFactory(ExtraEnumUtils.EXTRA_FACTORY_TIERS[tier.ordinal() + 1], origMachine.getMoreMachineFactoryType())));
        }
    }

    private void setMachineData(ExtraFactoryTier tier) {
        setFrom(origMachine, AttributeSound.class, AttributeMoreMachineFactoryType.class, AttributeUpgradeSupport.class);
        AttributeEnergy origEnergy = origMachine.get(AttributeEnergy.class);
        if (origEnergy != null) {
            add(new AttributeEnergy(origEnergy::getUsage, () -> origEnergy.getConfigStorage().multiply(0.5).max(origEnergy.getUsage()).multiply(tier.processes)));
        }
    }

    public static class ExtraMoreMachineFactoryBuilder<FACTORY extends ExtraMoreMachineFactory<TILE>, TILE extends TileEntityExtraMoreMachineFactory<?>, T extends ExtraMachineBuilder<FACTORY, TILE, T>>
                                                      extends BlockTileBuilder<FACTORY, TILE, T> {

        protected ExtraMoreMachineFactoryBuilder(FACTORY holder) {
            super(holder);
        }

        @SuppressWarnings("unchecked")
        public static <TILE extends TileEntityExtraMoreMachineFactory<?>> ExtraMoreMachineFactoryBuilder<ExtraMoreMachineFactory<TILE>, TILE, ?> createExtraMoreMachineFactory(Supplier<?> tileEntityRegistrar, MoreMachineFactoryType type,
                                                                                                                                                                               ExtraFactoryTier tier) {
            ExtraMoreMachineFactoryBuilder<ExtraMoreMachineFactory<TILE>, TILE, ?> builder = getExtraMoreMachineFactoryTILEAdvancedFactoryBuilder((Supplier<TileEntityTypeRegistryObject<TILE>>) tileEntityRegistrar, type, tier);
            builder.withCustomShape(MoreMachineBlockShapes.getShape(type));
            builder.replace(new AttributeParticleFX().addDense(ParticleTypes.SMOKE, 5, rand -> new Pos3D(
                    rand.nextFloat() * 0.7F - 0.3F,
                    rand.nextFloat() * 0.1F + 0.7F,
                    rand.nextFloat() * 0.7F - 0.3F)));
            return builder;
        }
    }

    private static <TILE extends TileEntityExtraMoreMachineFactory<?>> @NotNull ExtraMoreMachineFactoryBuilder<ExtraMoreMachineFactory<TILE>, TILE, ?> getExtraMoreMachineFactoryTILEAdvancedFactoryBuilder(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntityRegistrar, MoreMachineFactoryType type, ExtraFactoryTier tier) {
        ExtraMoreMachineFactoryBuilder<ExtraMoreMachineFactory<TILE>, TILE, ?> builder = new ExtraMoreMachineFactoryBuilder<>(new ExtraMoreMachineFactory<>(tileEntityRegistrar,
                () -> ExtraMoreMachineContainerTypes.MORE_MACHINE_FACTORY,
                switch (type) {
                    case RECYCLING -> ExtraMoreMachineBlockTypes.RECYCLER;
                    case PLANTING -> ExtraMoreMachineBlockTypes.PLANTING_STATION;
                    case CNC_STAMPING -> ExtraMoreMachineBlockTypes.CNC_STAMPER;
                    case CNC_LATHING -> ExtraMoreMachineBlockTypes.CNC_LATHE;
                    case CNC_ROLLING_MILL -> ExtraMoreMachineBlockTypes.CNC_ROLLING_MILL;
                    case REPLICATING -> ExtraMoreMachineBlockTypes.REPLICATOR;
                },
                tier));
        builder.withComputerSupport(tier.getAdvanceTier().getLowerName() + type.getRegistryNameComponentCapitalized() + "Factory");
        return builder;
    }
}
