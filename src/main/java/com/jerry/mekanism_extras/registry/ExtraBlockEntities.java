package com.jerry.mekanism_extras.registry;

import com.jerry.mekanism_extras.MekanismExtras;
import com.jerry.mekanism_extras.common.block.machine.forcefield.ForceFieldGeneratorEntity;
import com.mojang.datafixers.DSL;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ExtraBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> EXTRA_BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MekanismExtras.MODID);

    public static final RegistryObject<BlockEntityType<ForceFieldGeneratorEntity>> FORCEFIELD_GENERATOR_ENTITY
            = EXTRA_BLOCK_ENTITIES.register("forcefield_generator", () -> BlockEntityType.Builder.of(ForceFieldGeneratorEntity::new, ExtraBlock.FORCEFIELD_GENERATOR.getBlock()).build(DSL.remainderType()));

    public static void register(IEventBus bus) { EXTRA_BLOCK_ENTITIES.register(bus); }
}
