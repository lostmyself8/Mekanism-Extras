package com.jerry.mekanism_extras.common.registry;

import com.jerry.mekanism_extras.MekanismExtras;
import com.jerry.mekanism_extras.api.ExtraUpgrade;
import com.jerry.mekanism_extras.api.tier.AdvancedTier;
import com.jerry.mekanism_extras.api.tier.ExtraAlloyTier;
import com.jerry.mekanism_extras.common.item.*;
import com.jerry.mekanism_extras.common.resource.ExtraResource;
import com.jerry.mekanism_extras.common.tier.ExtraQIODriverTier;

import mekanism.api.Upgrade;
import mekanism.common.item.ItemUpgrade;
import mekanism.common.registration.impl.ItemDeferredRegister;
import mekanism.common.registration.impl.ItemRegistryObject;
import mekanism.common.resource.IResource;
import mekanism.common.resource.ResourceType;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;

public class ExtraItem {

    public static final ItemDeferredRegister EXTRA_ITEMS = new ItemDeferredRegister(MekanismExtras.MODID);

    // public static final RegistryObject<Item> NAQUADAH_ORE_ITEM = ITEMS.register("naquadah_ore",
    // () -> new BlockItem(ExtraBlock.NAQUADAH_ORE.get(), new Item.Properties()));
    // public static final RegistryObject<Item> END_NAQUADAH_ORE_ITEM = ITEMS.register("end_naquadah_ore",
    // () -> new BlockItem(ExtraBlock.END_NAQUADAH_ORE.get(), new Item.Properties()));

    public static final ItemRegistryObject<ExtraItemQIODrive> COLLAPSE_QIO_DRIVE = registryQIODrive(ExtraQIODriverTier.COLLAPSE);
    public static final ItemRegistryObject<ExtraItemQIODrive> GAMMA_QIO_DRIVE = registryQIODrive(ExtraQIODriverTier.GAMMA);
    public static final ItemRegistryObject<ExtraItemQIODrive> BLACK_HOLE_QIO_DRIVE = registryQIODrive(ExtraQIODriverTier.BLACK_HOLE);
    public static final ItemRegistryObject<ExtraItemQIODrive> SINGULARITY_QIO_DRIVE = registryQIODrive(ExtraQIODriverTier.SINGULARITY);

    public static final ItemRegistryObject<ItemUpgrade> STACK = registerUpgrade(ExtraUpgrade.STACK);
    public static final ItemRegistryObject<ItemUpgrade> IONIC_MEMBRANE = registerUpgrade(ExtraUpgrade.IONIC_MEMBRANE);
    public static final ItemRegistryObject<ItemUpgrade> CREATIVE = registerUpgrade(ExtraUpgrade.CREATIVE);

    public static final ItemRegistryObject<ExtraItemTierInstaller> ABSOLUTE_TIER_INSTALLER = registerInstaller(null, AdvancedTier.ABSOLUTE);
    public static final ItemRegistryObject<ExtraItemTierInstaller> SUPREME_TIER_INSTALLER = registerInstaller(AdvancedTier.ABSOLUTE, AdvancedTier.SUPREME);
    public static final ItemRegistryObject<ExtraItemTierInstaller> COSMIC_TIER_INSTALLER = registerInstaller(AdvancedTier.SUPREME, AdvancedTier.COSMIC);
    public static final ItemRegistryObject<ExtraItemTierInstaller> INFINITE_TIER_INSTALLER = registerInstaller(AdvancedTier.COSMIC, AdvancedTier.INFINITE);

    public static final ItemRegistryObject<Item> ABSOLUTE_CONTROL_CIRCUIT = registerCircuit("absolute", Rarity.COMMON);
    public static final ItemRegistryObject<Item> SUPREME_CONTROL_CIRCUIT = registerCircuit("supreme", Rarity.UNCOMMON);
    public static final ItemRegistryObject<Item> COSMIC_CONTROL_CIRCUIT = registerCircuit("cosmic", Rarity.RARE);
    public static final ItemRegistryObject<Item> INFINITE_CONTROL_CIRCUIT = registerCircuit("infinite", Rarity.EPIC);

    public static final ItemRegistryObject<Item> RADIANCE_ALLOY = EXTRA_ITEMS.register("alloy_radiance", properties -> new ItemAlloyRadiance(properties.rarity(Rarity.COMMON)));
    public static final ItemRegistryObject<ExtraItemAlloy> THERMONUCLEAR_ALLOY = registerAlloy(ExtraAlloyTier.THERMONUCLEAR, Rarity.UNCOMMON);
    public static final ItemRegistryObject<ExtraItemAlloy> SHINING_ALLOY = registerAlloy(ExtraAlloyTier.SHINING, Rarity.RARE);
    public static final ItemRegistryObject<ExtraItemAlloy> SPECTRUM_ALLOY = registerAlloy(ExtraAlloyTier.SPECTRUM, Rarity.EPIC);

    public static final ItemRegistryObject<Item> ENRICHED_OSMIUM = registerEnriched("osmium", Rarity.COMMON);
    public static final ItemRegistryObject<Item> ENRICHED_LEAD = registerEnriched("lead", Rarity.COMMON);
    public static final ItemRegistryObject<Item> ENRICHED_RADIANCE = registerEnriched("radiance", Rarity.COMMON);
    public static final ItemRegistryObject<Item> ENRICHED_THERMONUCLEAR = registerEnriched("thermonuclear", Rarity.UNCOMMON);
    public static final ItemRegistryObject<Item> ENRICHED_SHINING = registerEnriched("shining", Rarity.RARE);
    public static final ItemRegistryObject<Item> ENRICHED_SPECTRUM = registerEnriched("spectrum", Rarity.EPIC);

    public static final ItemRegistryObject<Item> DUST_RADIANCE = EXTRA_ITEMS.register("dust_radiance");

    public static final ItemRegistryObject<Item> NAQUADAH_DUST = registerResource(ResourceType.DUST, ExtraResource.NAQUADAH);
    public static final ItemRegistryObject<Item> SHARD_NAQUADAH = registerResource(ResourceType.SHARD, ExtraResource.NAQUADAH);
    public static final ItemRegistryObject<Item> CRYSTAL_NAQUADAH = registerResource(ResourceType.CRYSTAL, ExtraResource.NAQUADAH);
    public static final ItemRegistryObject<Item> DIRTY_DUST_NAQUADAH = registerResource(ResourceType.DIRTY_DUST, ExtraResource.NAQUADAH);
    public static final ItemRegistryObject<Item> CLUMP_NAQUADAH = registerResource(ResourceType.CLUMP, ExtraResource.NAQUADAH);
    public static final ItemRegistryObject<Item> INGOT_NAQUADAH = registerResource(ResourceType.INGOT, ExtraResource.NAQUADAH);
    public static final ItemRegistryObject<Item> RAW_NAQUADAH = registerResource(ResourceType.RAW, ExtraResource.NAQUADAH);

    public static final ItemRegistryObject<Item> CRYSTAL_TUNGSTEN = registerUnburnableResource(ResourceType.CRYSTAL, ExtraResource.TUNGSTEN);
    public static final ItemRegistryObject<Item> SHARD_TUNGSTEN = registerUnburnableResource(ResourceType.SHARD, ExtraResource.TUNGSTEN);
    public static final ItemRegistryObject<Item> CLUMP_TUNGSTEN = registerUnburnableResource(ResourceType.CLUMP, ExtraResource.TUNGSTEN);
    public static final ItemRegistryObject<Item> DIRTY_DUST_TUNGSTEN = registerUnburnableResource(ResourceType.DIRTY_DUST, ExtraResource.TUNGSTEN);
    public static final ItemRegistryObject<Item> DUST_TUNGSTEN = registerUnburnableResource(ResourceType.DUST, ExtraResource.TUNGSTEN);
    public static final ItemRegistryObject<Item> INGOT_TUNGSTEN = registerUnburnableResource(ResourceType.INGOT, ExtraResource.TUNGSTEN);
    public static final ItemRegistryObject<Item> NUGGET_TUNGSTEN = registerUnburnableResource(ResourceType.NUGGET, ExtraResource.TUNGSTEN);

    public static final ItemRegistryObject<Item> REFINED_NETHERITE_INGOT = EXTRA_ITEMS.registerUnburnable("ingot_refined_netherite");

    private static ItemRegistryObject<ExtraItemQIODrive> registryQIODrive(ExtraQIODriverTier tier) {
        return EXTRA_ITEMS.register("qio_drive_" + tier.name().toLowerCase(Locale.ROOT), properties -> new ExtraItemQIODrive(tier, properties));
    }

    private static ItemRegistryObject<ItemUpgrade> registerUpgrade(Upgrade type) {
        return EXTRA_ITEMS.register("upgrade_" + type.getRawName(), properties -> new ItemUpgrade(type, properties));
    }

    private static ItemRegistryObject<ExtraItemTierInstaller> registerInstaller(@Nullable AdvancedTier fromTier, @NotNull AdvancedTier toTier) {
        // Ensure the name is lower case as with concatenating with values from enums it may not be
        return EXTRA_ITEMS.register(toTier.getLowerName() + "_tier_installer", properties -> new ExtraItemTierInstaller(fromTier, toTier, properties));
    }

    private static ItemRegistryObject<Item> registerCircuit(String name, Rarity rarity) {
        return EXTRA_ITEMS.register(name + "_control_circuit", properties -> new Item(properties.rarity(rarity)));
    }

    private static ItemRegistryObject<ExtraItemAlloy> registerAlloy(ExtraAlloyTier tier, Rarity rarity) {
        return EXTRA_ITEMS.register("alloy_" + tier.getName(), properties -> new ExtraItemAlloy(tier, properties.rarity(rarity)));
    }

    private static ItemRegistryObject<Item> registerEnriched(String name, Rarity rarity) {
        return EXTRA_ITEMS.register("enriched_" + name, properties -> new Item(properties.rarity(rarity)));
    }

    private static ItemRegistryObject<Item> registerResource(ResourceType type, IResource resource) {
        return EXTRA_ITEMS.register(type.getRegistryPrefix() + "_" + resource.getRegistrySuffix());
    }

    private static ItemRegistryObject<Item> registerUnburnableResource(ResourceType type, IResource resource) {
        return EXTRA_ITEMS.registerUnburnable(type.getRegistryPrefix() + "_" + resource.getRegistrySuffix());
    }

    public static void register(IEventBus eventBus) {
        EXTRA_ITEMS.register(eventBus);
    }
}
