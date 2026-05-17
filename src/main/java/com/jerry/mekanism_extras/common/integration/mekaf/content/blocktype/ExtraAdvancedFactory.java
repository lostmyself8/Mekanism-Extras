package com.jerry.mekanism_extras.common.integration.mekaf.content.blocktype;

import com.jerry.mekanism_extras.common.block.attribute.ExtraAttributeTier;
import com.jerry.mekanism_extras.common.block.attribute.ExtraAttributeUpgradeable;
import com.jerry.mekanism_extras.common.content.blocktype.ExtraMachine.ExtraFactoryMachine;
import com.jerry.mekanism_extras.common.content.blocktype.ExtraMachine.ExtraMachineBuilder;
import com.jerry.mekanism_extras.common.integration.mekaf.registries.ExtraAdvancedFactoryBlockTypes;
import com.jerry.mekanism_extras.common.integration.mekaf.registries.ExtraAdvancedFactoryBlocks;
import com.jerry.mekanism_extras.common.integration.mekaf.registries.ExtraAdvancedFactoryContainerTypes;
import com.jerry.mekanism_extras.common.integration.mekaf.tile.factory.base.TileEntityExtraAdvancedFactoryBase;
import com.jerry.mekanism_extras.common.tier.ExtraFactoryTier;
import com.jerry.mekanism_extras.common.util.ExtraEnumUtils;

import mekanism.common.MekanismLang;
import mekanism.common.block.attribute.AttributeEnergy;
import mekanism.common.block.attribute.AttributeGui;
import mekanism.common.block.attribute.AttributeParticleFX;
import mekanism.common.block.attribute.AttributeSound;
import mekanism.common.block.attribute.AttributeUpgradeSupport;
import mekanism.common.inventory.container.MekanismContainer;
import mekanism.common.lib.math.Pos3D;
import mekanism.common.registration.impl.ContainerTypeRegistryObject;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;

import net.minecraft.core.particles.ParticleTypes;

import com.jerry.mekaf.common.block.attribute.AttributeAdvancedFactoryType;
import com.jerry.mekaf.common.content.blocktype.AdvancedFactoryBlockShapes;
import com.jerry.mekaf.common.content.blocktype.AdvancedFactoryType;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class ExtraAdvancedFactory<TILE extends TileEntityExtraAdvancedFactoryBase<?>> extends ExtraFactoryMachine<TILE> {

    private final ExtraFactoryMachine<?> origMachine;

    public ExtraAdvancedFactory(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntityRegistrar, Supplier<ContainerTypeRegistryObject<? extends MekanismContainer>> containerRegistrar,
                                ExtraFactoryMachine<?> origMachine, ExtraFactoryTier tier) {
        super(tileEntityRegistrar, MekanismLang.DESCRIPTION_FACTORY, origMachine.getAdvancedFactoryType());
        this.origMachine = origMachine;
        setMachineData(tier);
        add(new AttributeGui(containerRegistrar, null), new ExtraAttributeTier<>(tier));

        if (tier.ordinal() < ExtraEnumUtils.EXTRA_FACTORY_TIERS.length - 1) {
            add(new ExtraAttributeUpgradeable(() -> ExtraAdvancedFactoryBlocks.getExtraAdvancedFactory(ExtraEnumUtils.EXTRA_FACTORY_TIERS[tier.ordinal() + 1], origMachine.getAdvancedFactoryType())));
        }
    }

    private void setMachineData(ExtraFactoryTier tier) {
        setFrom(origMachine, AttributeSound.class, AttributeAdvancedFactoryType.class, AttributeUpgradeSupport.class);
        AttributeEnergy origEnergy = origMachine.get(AttributeEnergy.class);
        if (origEnergy != null) {
            add(new AttributeEnergy(origEnergy::getUsage, () -> origEnergy.getConfigStorage().max(origEnergy.getUsage()).multiply(tier.processes)));
        }
    }

    public static class ExtraAdvancedFactoryBuilder<FACTORY extends ExtraAdvancedFactory<TILE>, TILE extends TileEntityExtraAdvancedFactoryBase<?>, T extends ExtraMachineBuilder<FACTORY, TILE, T>>
                                                   extends BlockTileBuilder<FACTORY, TILE, T> {

        protected ExtraAdvancedFactoryBuilder(FACTORY holder) {
            super(holder);
        }

        @SuppressWarnings("unchecked")
        public static <TILE extends TileEntityExtraAdvancedFactoryBase<?>> ExtraAdvancedFactoryBuilder<ExtraAdvancedFactory<TILE>, TILE, ?> createAdvancedFactory(Supplier<?> tileEntityRegistrar,
                                                                                                                                                                  AdvancedFactoryType type, ExtraFactoryTier tier) {
            ExtraAdvancedFactoryBuilder<ExtraAdvancedFactory<TILE>, TILE, ?> builder = getExtraAdvancedFactoryBuilder((Supplier<TileEntityTypeRegistryObject<TILE>>) tileEntityRegistrar, type, tier);
            builder.withCustomShape(AdvancedFactoryBlockShapes.getShape(type));
            // builder.with(switch (type) {
            // case OXIDIZING, DISSOLVING, CRYSTALLIZING, PIGMENT_EXTRACTING, PAINTING ->
            // AttributeSideConfig.ADVANCED_ELECTRIC_MACHINE;
            // case CENTRIFUGING -> AttributeSideConfig.create(TransmissionType.GAS, TransmissionType.ITEM,
            // TransmissionType.ENERGY);
            // case WASHING -> AttributeSideConfig.create(TransmissionType.GAS, TransmissionType.FLUID,
            // TransmissionType.ITEM, TransmissionType.ENERGY);
            // case PRESSURISED_REACTING -> AttributeSideConfig.create(TransmissionType.ITEM, TransmissionType.GAS,
            // TransmissionType.FLUID, TransmissionType.ENERGY);
            // case LIQUIFYING -> AttributeSideConfig.create(TransmissionType.FLUID, TransmissionType.ITEM,
            // TransmissionType.ENERGY);
            // });
            builder.replace(new AttributeParticleFX().addDense(ParticleTypes.SMOKE, 5, rand -> new Pos3D(
                    rand.nextFloat() * 0.7F - 0.3F,
                    rand.nextFloat() * 0.1F + 0.7F,
                    rand.nextFloat() * 0.7F - 0.3F)));
            return builder;
        }
    }

    private static <TILE extends TileEntityExtraAdvancedFactoryBase<?>> @NotNull ExtraAdvancedFactoryBuilder<ExtraAdvancedFactory<TILE>, TILE, ?> getExtraAdvancedFactoryBuilder(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntityRegistrar, AdvancedFactoryType type, ExtraFactoryTier tier) {
        ExtraAdvancedFactoryBuilder<ExtraAdvancedFactory<TILE>, TILE, ?> builder = new ExtraAdvancedFactoryBuilder<>(new ExtraAdvancedFactory<>(tileEntityRegistrar,
                () -> ExtraAdvancedFactoryContainerTypes.ADVANCED_FACTORY,
                switch (type) {
                    case OXIDIZING -> ExtraAdvancedFactoryBlockTypes.CHEMICAL_OXIDIZER;
                    case DISSOLVING -> ExtraAdvancedFactoryBlockTypes.CHEMICAL_DISSOLUTION_CHAMBER;
                    case WASHING -> ExtraAdvancedFactoryBlockTypes.CHEMICAL_WASHER;
                    case CRYSTALLIZING -> ExtraAdvancedFactoryBlockTypes.CHEMICAL_CRYSTALLIZER;
                    case PRESSURISED_REACTING -> ExtraAdvancedFactoryBlockTypes.PRESSURIZED_REACTION_CHAMBER;
                    case CENTRIFUGING -> ExtraAdvancedFactoryBlockTypes.ISOTOPIC_CENTRIFUGE;
                    case LIQUIFYING -> ExtraAdvancedFactoryBlockTypes.NUTRITIONAL_LIQUIFIER;
                    case PIGMENT_EXTRACTING -> ExtraAdvancedFactoryBlockTypes.PIGMENT_EXTRACTOR;
                    case PAINTING -> ExtraAdvancedFactoryBlockTypes.PAINTING_MACHINE;
                },
                tier));
        builder.withComputerSupport(tier.getAdvanceTier().getLowerName() + type.getRegistryNameComponentCapitalized() + "Factory");
        return builder;
    }
}
