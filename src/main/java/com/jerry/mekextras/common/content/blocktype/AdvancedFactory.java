package com.jerry.mekextras.common.content.blocktype;

import com.jerry.mekextras.common.block.attribute.ExtraAttributeTier;
import com.jerry.mekextras.common.registry.ExtraBlockTypes;
import com.jerry.mekextras.common.registry.ExtraBlocks;
import com.jerry.mekextras.common.registry.ExtraContainerTypes;
import com.jerry.mekextras.common.tier.AdvancedFactoryTier;
import com.jerry.mekextras.common.tile.factory.TileEntityAdvancedFactory;
import com.jerry.mekextras.common.util.ExtraEnumUtils;
import mekanism.api.math.MathUtils;
import mekanism.common.MekanismLang;
import mekanism.common.block.attribute.*;
import mekanism.common.content.blocktype.BlockShapes;
import mekanism.common.content.blocktype.FactoryType;
import mekanism.common.inventory.container.MekanismContainer;
import mekanism.common.lib.math.Pos3D;
import mekanism.common.registration.impl.ContainerTypeRegistryObject;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import net.minecraft.core.particles.ParticleTypes;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class AdvancedFactory<TILE extends TileEntityAdvancedFactory<?>> extends AdvancedMachine.AdvancedFactoryMachine<TILE> {

    private final AdvancedMachine.AdvancedFactoryMachine<?> origMachine;

    public AdvancedFactory(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntityRegistrar, Supplier<ContainerTypeRegistryObject<? extends MekanismContainer>> containerRegistrar,
                           AdvancedMachine.AdvancedFactoryMachine<?> origMachine, AdvancedFactoryTier tier) {
        super(tileEntityRegistrar, MekanismLang.DESCRIPTION_FACTORY, origMachine.getFactoryType());
        this.origMachine = origMachine;
        setMachineData(tier);
        add(new AttributeGui(containerRegistrar, null), new ExtraAttributeTier<>(tier));

        if (tier.ordinal() < ExtraEnumUtils.ADVANCE_FACTORY_TIERS.length - 1) {
            add(new AttributeUpgradeable(() -> ExtraBlocks.getAdvancedFactory(ExtraEnumUtils.ADVANCE_FACTORY_TIERS[tier.ordinal() + 1], origMachine.getFactoryType())));
        }
    }

    private void setMachineData(AdvancedFactoryTier tier) {
        setFrom(origMachine, AttributeSound.class, AttributeFactoryType.class, AttributeUpgradeSupport.class);
        AttributeEnergy origEnergy = origMachine.get(AttributeEnergy.class);
        add(new AttributeEnergy(origEnergy::getUsage, () -> MathUtils.clampToLong(Math.max(origEnergy.getConfigStorage() * 0.5, origEnergy.getUsage()) * tier.processes)));
    }

    public static class AdvancedFactoryBuilder<FACTORY extends AdvancedFactory<TILE>, TILE extends TileEntityAdvancedFactory<?>, T extends AdvancedMachine.AdvancedMachineBuilder<FACTORY, TILE, T>>
            extends BlockTileBuilder<FACTORY, TILE, T> {

        protected AdvancedFactoryBuilder(FACTORY holder) {
            super(holder);
        }

        @SuppressWarnings("unchecked")
        public static <TILE extends TileEntityAdvancedFactory<?>> AdvancedFactoryBuilder<AdvancedFactory<TILE>, TILE, ?> createFactory(Supplier<?> tileEntityRegistrar, FactoryType type,
                                                                                                                       AdvancedFactoryTier tier) {

            AdvancedFactoryBuilder<AdvancedFactory<TILE>, TILE, ?> builder = getAdvancedFactoryTILEAdvancedFactoryBuilder((Supplier<TileEntityTypeRegistryObject<TILE>>) tileEntityRegistrar, type, tier);
            builder.withCustomShape(BlockShapes.getShape(null, type));
            builder.with(switch (type) {
                case SMELTING, ENRICHING, CRUSHING, COMBINING, SAWING -> AttributeSideConfig.ELECTRIC_MACHINE;
                case COMPRESSING, INJECTING, PURIFYING, INFUSING -> AttributeSideConfig.ADVANCED_ELECTRIC_MACHINE;
            });
            builder.replace(new AttributeParticleFX().addDense(ParticleTypes.SMOKE, 5, rand -> new Pos3D(
                    rand.nextFloat() * 0.7F - 0.3F,
                    rand.nextFloat() * 0.1F + 0.7F,
                    rand.nextFloat() * 0.7F - 0.3F
            )));
            return builder;
        }
    }

    private static <TILE extends TileEntityAdvancedFactory<?>> @NotNull AdvancedFactoryBuilder<AdvancedFactory<TILE>, TILE, ?> getAdvancedFactoryTILEAdvancedFactoryBuilder(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntityRegistrar, FactoryType type, AdvancedFactoryTier tier) {

        AdvancedFactoryBuilder<AdvancedFactory<TILE>, TILE, ?> builder = new AdvancedFactoryBuilder<>(new AdvancedFactory<>(tileEntityRegistrar,
                () -> ExtraContainerTypes.FACTORY,
                switch (type) {
                    case SAWING -> ExtraBlockTypes.PRECISION_SAWMILL;
                    case SMELTING -> ExtraBlockTypes.ENERGIZED_SMELTER;
                    case ENRICHING -> ExtraBlockTypes.ENRICHMENT_CHAMBER;
                    case CRUSHING -> ExtraBlockTypes.CRUSHER;
                    case COMPRESSING -> ExtraBlockTypes.OSMIUM_COMPRESSOR;
                    case COMBINING -> ExtraBlockTypes.COMBINER;
                    case PURIFYING -> ExtraBlockTypes.PURIFICATION_CHAMBER;
                    case INJECTING -> ExtraBlockTypes.CHEMICAL_INJECTION_CHAMBER;
                    case INFUSING -> ExtraBlockTypes.METALLURGIC_INFUSER;
                },
                tier)
        );
        //Note, we can't just return the builder here as then it gets all confused about object types, so we just
        // assign the value here, and then return the builder itself as it is the same object
        builder.withComputerSupport(tier.getAdvanceTier().getLowerName() + type.getRegistryNameComponentCapitalized() + "Factory");
        return builder;
    }
}
