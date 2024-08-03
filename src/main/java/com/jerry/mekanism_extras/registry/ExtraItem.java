package com.jerry.mekanism_extras.registry;

import com.jerry.mekanism_extras.MekanismExtras;
import com.jerry.mekanism_extras.common.api.tier.ExtraAlloyTier;
import com.jerry.mekanism_extras.common.item.ExtraItemAlloy;
import com.jerry.mekanism_extras.common.item.ExtraItemTierInstaller;
import com.jerry.mekanism_extras.common.item.qio.ExtraItemQIODrive;
import com.jerry.mekanism_extras.common.item.qio.ExtraQIODriverTier;
import com.jerry.mekanism_extras.common.api.tier.AdvanceTier;
import com.jerry.mekanism_extras.common.resource.ExtraResource;
import mekanism.common.registration.impl.ItemDeferredRegister;
import mekanism.common.registration.impl.ItemRegistryObject;
import mekanism.common.resource.IResource;
import mekanism.common.resource.ResourceType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;

public class ExtraItem {
    public static final ItemDeferredRegister EXTRA_ITEM = new ItemDeferredRegister(MekanismExtras.MODID);

//    public static final RegistryObject<Item> NAQUADAH_ORE_ITEM = ITEMS.register("naquadah_ore",
//            () -> new BlockItem(ExtraBlock.NAQUADAH_ORE.get(), new Item.Properties()));
//    public static final RegistryObject<Item> END_NAQUADAH_ORE_ITEM = ITEMS.register("end_naquadah_ore",
//            () -> new BlockItem(ExtraBlock.END_NAQUADAH_ORE.get(), new Item.Properties()));

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MekanismExtras.MODID);
    public static final ItemRegistryObject<ExtraItemQIODrive> ABSOLUTE_QIO_DRIVE = registryQIODrive(ExtraQIODriverTier.ABSOLUTE);
    public static final ItemRegistryObject<ExtraItemQIODrive> SUPREME_QIO_DRIVE = registryQIODrive(ExtraQIODriverTier.SUPREME);
    public static final ItemRegistryObject<ExtraItemQIODrive> COSMIC_QIO_DRIVE = registryQIODrive(ExtraQIODriverTier.COSMIC);
    public static final ItemRegistryObject<ExtraItemQIODrive> INFINITE_QIO_DRIVE = registryQIODrive(ExtraQIODriverTier.INFINITE);
    public static final ItemRegistryObject<ExtraItemTierInstaller> ABSOLUTE_TIER_INSTALLER = registerInstaller(null, AdvanceTier.ABSOLUTE);
    public static final ItemRegistryObject<ExtraItemTierInstaller> SUPREME_TIER_INSTALLER = registerInstaller(AdvanceTier.ABSOLUTE, AdvanceTier.SUPREME);
    public static final ItemRegistryObject<ExtraItemTierInstaller> COSMIC_TIER_INSTALLER = registerInstaller(AdvanceTier.SUPREME, AdvanceTier.COSMIC);
    public static final ItemRegistryObject<ExtraItemTierInstaller> INFINITE_TIER_INSTALLER = registerInstaller(AdvanceTier.COSMIC, AdvanceTier.INFINITE);
    public static final ItemRegistryObject<Item> ABSOLUTE_CONTROL_CIRCUIT = registerCircuit("absolute", Rarity.COMMON);
    public static final ItemRegistryObject<Item> SUPREME_CONTROL_CIRCUIT = registerCircuit("supreme", Rarity.UNCOMMON);
    public static final ItemRegistryObject<Item> COSMIC_CONTROL_CIRCUIT = registerCircuit("cosmic", Rarity.RARE);
    public static final ItemRegistryObject<Item> INFINITE_CONTROL_CIRCUIT = registerCircuit("infinite", Rarity.EPIC);
    public static final ItemRegistryObject<Item> RADIANCE_ALLOY = EXTRA_ITEM.register("alloy_radiance", properties -> new Item(properties.rarity(Rarity.COMMON)));
    public static final ItemRegistryObject<ExtraItemAlloy> THERMONUCLEAR_ALLOY = registerAlloy(ExtraAlloyTier.THERMONUCLEAR, Rarity.UNCOMMON);
    public static final ItemRegistryObject<ExtraItemAlloy> SHINING_ALLOY = registerAlloy(ExtraAlloyTier.SHINING, Rarity.RARE);
    public static final ItemRegistryObject<ExtraItemAlloy> SPECTRUM_ALLOY = registerAlloy(ExtraAlloyTier.SPECTRUM, Rarity.EPIC);
    public static final ItemRegistryObject<Item> ENRICHED_RADIANCE = registerItem("radiance", Rarity.COMMON);
    public static final ItemRegistryObject<Item> ENRICHED_THERMONUCLEAR = registerItem("thermonuclear", Rarity.UNCOMMON);
    public static final ItemRegistryObject<Item> ENRICHED_SHINING = registerItem("shining", Rarity.RARE);
    public static final ItemRegistryObject<Item> ENRICHED_SPECTRUM = registerItem("spectrum", Rarity.EPIC);
    public static final ItemRegistryObject<Item> DUST_RADIANCE = EXTRA_ITEM.register("dust_radiance");
    public static final ItemRegistryObject<Item> NAQUADAH_DUST = registerResource(ResourceType.DUST);
    public static final ItemRegistryObject<Item> SHARD_NAQUADAH = registerResource(ResourceType.SHARD);
    public static final ItemRegistryObject<Item> CRYSTAL_NAQUADAH = registerResource(ResourceType.CRYSTAL);
    public static final ItemRegistryObject<Item> DIRTY_DUST_NAQUADAH = registerResource(ResourceType.DIRTY_DUST);
    public static final ItemRegistryObject<Item> CLUMP_NAQUADAH = registerResource(ResourceType.CLUMP);
    public static final ItemRegistryObject<Item> INGOT_NAQUADAH = registerResource(ResourceType.INGOT);
    public static final ItemRegistryObject<Item> RAW_NAQUADAH = registerResource(ResourceType.RAW);

    private static ItemRegistryObject<ExtraItemQIODrive> registryQIODrive(ExtraQIODriverTier tier) {
        return EXTRA_ITEM.register("qio_drive_" + tier.name().toLowerCase(Locale.ROOT), properties -> new ExtraItemQIODrive(tier, properties));
    }

    private static ItemRegistryObject<ExtraItemTierInstaller> registerInstaller(@Nullable AdvanceTier fromTier, @NotNull AdvanceTier toTier) {
        //Ensure the name is lower case as with concatenating with values from enums it may not be
        return EXTRA_ITEM.register(toTier.getLowerName() + "_tier_installer", properties -> new ExtraItemTierInstaller(fromTier, toTier, properties));
    }

    private static ItemRegistryObject<Item> registerCircuit(String name, Rarity rarity) {
        return EXTRA_ITEM.register(name + "_control_circuit", properties -> new Item(properties.rarity(rarity)));
    }

    private static ItemRegistryObject<ExtraItemAlloy> registerAlloy(ExtraAlloyTier tier, Rarity rarity) {
        return EXTRA_ITEM.register("alloy_" + tier.getName(), properties -> new ExtraItemAlloy(tier, properties.rarity(rarity)));
    }

    private static ItemRegistryObject<Item> registerItem(String name, Rarity rarity) {
        return EXTRA_ITEM.register("enriched_" + name, properties -> new Item(properties.rarity(rarity)));
    }

    private static ItemRegistryObject<Item> registerResource(ResourceType type) {
        return EXTRA_ITEM.register(type.getRegistryPrefix() + "_" + ((IResource) ExtraResource.NAQUADAH).getRegistrySuffix());
    }

    public static void register(IEventBus eventBus) {
        EXTRA_ITEM.register(eventBus);
    }
}
