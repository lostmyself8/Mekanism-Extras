package com.jerry.mekextras.common.integration.mekaf.content.blocktype;

import com.jerry.mekaf.common.block.attribute.AttributeAdvancedFactoryType;
import com.jerry.mekaf.common.content.blocktype.AdvancedFactoryBlockShapes;
import com.jerry.mekaf.common.content.blocktype.AdvancedFactoryType;
import com.jerry.mekextras.common.block.attribute.ExtraAttributeTier;
import com.jerry.mekextras.common.content.blocktype.ExtraMachine.ExtraMachineBuilder;
import com.jerry.mekextras.common.content.blocktype.ExtraMachine.ExtraFactoryMachine;
import com.jerry.mekextras.common.integration.mekaf.registries.ExtraAdvancedFactoryBlockTypes;
import com.jerry.mekextras.common.integration.mekaf.registries.ExtraAdvancedFactoryBlocks;
import com.jerry.mekextras.common.integration.mekaf.registries.ExtraAdvancedFactoryContainerTypes;
import com.jerry.mekextras.common.integration.mekaf.tile.factory.TileEntityExtraAdvancedBase;
import com.jerry.mekextras.common.tier.ExtraFactoryTier;
import com.jerry.mekextras.common.util.ExtraEnumUtils;
import mekanism.api.math.MathUtils;
import mekanism.common.MekanismLang;
import mekanism.common.block.attribute.*;
import mekanism.common.inventory.container.MekanismContainer;
import mekanism.common.lib.math.Pos3D;
import mekanism.common.lib.transmitter.TransmissionType;
import mekanism.common.registration.impl.ContainerTypeRegistryObject;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import net.minecraft.core.particles.ParticleTypes;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class ExtraAdvancedFactory<TILE extends TileEntityExtraAdvancedBase<?>> extends ExtraFactoryMachine<TILE> {

    private final ExtraFactoryMachine<?> origMachine;

    public ExtraAdvancedFactory(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntityRegistrar, Supplier<ContainerTypeRegistryObject<? extends MekanismContainer>> containerRegistrar,
                                ExtraFactoryMachine<?> origMachine, ExtraFactoryTier tier) {
        super(tileEntityRegistrar, MekanismLang.DESCRIPTION_FACTORY, origMachine.getAdvancedFactoryType());
        this.origMachine = origMachine;
        setMachineData(tier);
        add(new AttributeGui(containerRegistrar, null), new ExtraAttributeTier<>(tier));

        if (tier.ordinal() < ExtraEnumUtils.EXTRA_FACTORY_TIERS.length - 1) {
            add(new AttributeUpgradeable(() -> ExtraAdvancedFactoryBlocks.getAdvancedFactory(ExtraEnumUtils.EXTRA_FACTORY_TIERS[tier.ordinal() + 1], origMachine.getAdvancedFactoryType())));
        }
    }

    private void setMachineData(ExtraFactoryTier tier) {
        setFrom(origMachine, AttributeSound.class, AttributeAdvancedFactoryType.class, AttributeUpgradeSupport.class);
        AttributeEnergy origEnergy = origMachine.get(AttributeEnergy.class);
        if (origEnergy != null) {
            // 相比于原版，这里将0.5的乘数去除
            add(new AttributeEnergy(origEnergy::getUsage, () -> MathUtils.clampToLong(Math.max(origEnergy.getConfigStorage(), origEnergy.getUsage()) * tier.processes * tier.processes)));
        }
    }

    public static class ExtraAdvancedFactoryBuilder<FACTORY extends ExtraAdvancedFactory<TILE>, TILE extends TileEntityExtraAdvancedBase<?>, T extends ExtraMachineBuilder<FACTORY, TILE, T>>
                                              extends BlockTileBuilder<FACTORY, TILE, T> {

        protected ExtraAdvancedFactoryBuilder(FACTORY holder) {
            super(holder);
        }

        @SuppressWarnings("unchecked")
        public static <TILE extends TileEntityExtraAdvancedBase<?>> ExtraAdvancedFactoryBuilder<ExtraAdvancedFactory<TILE>, TILE, ?> createAdvancedFactory(Supplier<?> tileEntityRegistrar, AdvancedFactoryType type,
                                                                                                                                                           ExtraFactoryTier tier) {
            // this is dirty but unfortunately necessary for things to play right
            ExtraAdvancedFactoryBuilder<ExtraAdvancedFactory<TILE>, TILE, ?> builder = getExtraAdvancedFactoryTILEAdvancedFactoryBuilder((Supplier<TileEntityTypeRegistryObject<TILE>>) tileEntityRegistrar, type, tier);
            builder.withCustomShape(AdvancedFactoryBlockShapes.getShape(type));
            builder.with(switch (type) {
                case OXIDIZING, DISSOLVING, CRYSTALLIZING -> AttributeSideConfig.ADVANCED_ELECTRIC_MACHINE;
                case CHEMICAL_INFUSING, CENTRIFUGING -> AttributeSideConfig.create(TransmissionType.CHEMICAL, TransmissionType.ITEM, TransmissionType.ENERGY);
                case WASHING -> AttributeSideConfig.create(TransmissionType.CHEMICAL, TransmissionType.FLUID, TransmissionType.ITEM, TransmissionType.ENERGY);
                case PRESSURISED_REACTING -> AttributeSideConfig.create(TransmissionType.ITEM, TransmissionType.CHEMICAL, TransmissionType.FLUID, TransmissionType.ENERGY);
                case LIQUIFYING -> AttributeSideConfig.create(TransmissionType.FLUID, TransmissionType.ITEM, TransmissionType.ENERGY);
            });
            // 如果有Bounding属性就添加，但或许会有更复杂的形状
            if (type.getBaseMachine().has(AttributeHasBounding.class)) {
                builder.with(AttributeHasBounding.ABOVE_ONLY);
            }
            builder.replace(new AttributeParticleFX().addDense(ParticleTypes.SMOKE, 5, rand -> new Pos3D(
                    rand.nextFloat() * 0.7F - 0.3F,
                    rand.nextFloat() * 0.1F + 0.7F,
                    rand.nextFloat() * 0.7F - 0.3F)));
            return builder;
        }
    }

    private static <TILE extends TileEntityExtraAdvancedBase<?>> @NotNull ExtraAdvancedFactoryBuilder<ExtraAdvancedFactory<TILE>, TILE, ?> getExtraAdvancedFactoryTILEAdvancedFactoryBuilder(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntityRegistrar, AdvancedFactoryType type, ExtraFactoryTier tier) {

        ExtraAdvancedFactoryBuilder<ExtraAdvancedFactory<TILE>, TILE, ?> builder = new ExtraAdvancedFactoryBuilder<>(new ExtraAdvancedFactory<>(tileEntityRegistrar,
                () -> ExtraAdvancedFactoryContainerTypes.ADVANCED_FACTORY,
                switch (type) {
                    case OXIDIZING -> ExtraAdvancedFactoryBlockTypes.CHEMICAL_OXIDIZER;
                    case CHEMICAL_INFUSING -> ExtraAdvancedFactoryBlockTypes.CHEMICAL_INFUSER;
                    case DISSOLVING -> ExtraAdvancedFactoryBlockTypes.CHEMICAL_DISSOLUTION_CHAMBER;
                    case WASHING -> ExtraAdvancedFactoryBlockTypes.CHEMICAL_WASHER;
                    case CRYSTALLIZING -> ExtraAdvancedFactoryBlockTypes.CHEMICAL_CRYSTALLIZER;
                    case PRESSURISED_REACTING -> ExtraAdvancedFactoryBlockTypes.PRESSURIZED_REACTION_CHAMBER;
                    case CENTRIFUGING -> ExtraAdvancedFactoryBlockTypes.ISOTOPIC_CENTRIFUGE;
                    case LIQUIFYING -> ExtraAdvancedFactoryBlockTypes.NUTRITIONAL_LIQUIFIER;
                },
                tier)
        );
        builder.withComputerSupport(tier.getAdvanceTier().getLowerName() + type.getRegistryNameComponentCapitalized() + "Factory");
        return builder;
    }
}
