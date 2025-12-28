package com.jerry.mekextras.common.registries;

import com.jerry.mekextras.MekanismExtras;
import com.jerry.mekextras.api.ExtraUpgrade;
import com.jerry.mekextras.api.tier.AdvancedTier;
import com.jerry.mekextras.api.tier.ExtraAlloyTier;
import com.jerry.mekextras.common.item.*;
import com.jerry.mekextras.common.resource.ExtraResource;
import com.jerry.mekextras.common.tier.ExtraQIODriveTier;
import com.jerry.mekextras.common.util.ExtraEnumUtils;

import mekanism.api.Upgrade;
import mekanism.api.text.TextComponentUtil;
import mekanism.common.item.ItemUpgrade;
import mekanism.common.registration.impl.ItemDeferredRegister;
import mekanism.common.registration.impl.ItemRegistryObject;
import mekanism.common.resource.IResource;
import mekanism.common.resource.ResourceType;
import mekanism.common.util.EnumUtils;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.neoforged.bus.api.IEventBus;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;

public class ExtraItems {

    public static final ItemDeferredRegister EXTRA_ITEMS = new ItemDeferredRegister(MekanismExtras.MOD_ID);
    public static final Table<ResourceType, ExtraResource, ItemRegistryObject<Item>> PROCESSED_RESOURCES = HashBasedTable.create();

    // QIO Drives
    public static final ItemRegistryObject<ExtraItemQIODrive> COLLAPSE_QIO_DRIVE = registerQIODrive(ExtraQIODriveTier.COLLAPSE);
    public static final ItemRegistryObject<ExtraItemQIODrive> GAMMA_QIO_DRIVE = registerQIODrive(ExtraQIODriveTier.GAMMA);
    public static final ItemRegistryObject<ExtraItemQIODrive> BLACK_HOLE_QIO_DRIVE = registerQIODrive(ExtraQIODriveTier.BLACK_HOLE);
    public static final ItemRegistryObject<ExtraItemQIODrive> SINGULARITY_QIO_DRIVE = registerQIODrive(ExtraQIODriveTier.SINGULARITY);

    public static final ItemRegistryObject<ItemUpgrade> STACK = registerUpgrade(ExtraUpgrade.STACK, Rarity.RARE);
    public static final ItemRegistryObject<ItemUpgrade> IONIC_MEMBRANE = registerUpgrade(ExtraUpgrade.IONIC_MEMBRANE, Rarity.RARE);
    public static final ItemRegistryObject<ItemUpgrade> CREATIVE = registerUpgrade(ExtraUpgrade.CREATIVE, Rarity.EPIC);

    public static final ItemRegistryObject<ItemExtraTierInstaller> ABSOLUTE_TIER_INSTALLER = registerInstaller(null, AdvancedTier.ABSOLUTE);
    public static final ItemRegistryObject<ItemExtraTierInstaller> SUPREME_TIER_INSTALLER = registerInstaller(AdvancedTier.ABSOLUTE, AdvancedTier.SUPREME);
    public static final ItemRegistryObject<ItemExtraTierInstaller> COSMIC_TIER_INSTALLER = registerInstaller(AdvancedTier.SUPREME, AdvancedTier.COSMIC);
    public static final ItemRegistryObject<ItemExtraTierInstaller> INFINITE_TIER_INSTALLER = registerInstaller(AdvancedTier.COSMIC, AdvancedTier.INFINITE);

    public static final ItemRegistryObject<Item> ABSOLUTE_CONTROL_CIRCUIT = registerCircuit(AdvancedTier.ABSOLUTE);
    public static final ItemRegistryObject<Item> SUPREME_CONTROL_CIRCUIT = registerCircuit(AdvancedTier.SUPREME);
    public static final ItemRegistryObject<Item> COSMIC_CONTROL_CIRCUIT = registerCircuit(AdvancedTier.COSMIC);
    public static final ItemRegistryObject<Item> INFINITE_CONTROL_CIRCUIT = registerCircuit(AdvancedTier.INFINITE);

    public static final ItemRegistryObject<Item> RADIANCE_ALLOY = EXTRA_ITEMS.registerItem("alloy_radiance", properties -> new ItemAlloyRadiance(properties.rarity(Rarity.COMMON)));
    public static final ItemRegistryObject<ItemExtraAlloy> THERMONUCLEAR_ALLOY = registerAlloy(ExtraAlloyTier.THERMONUCLEAR, Rarity.UNCOMMON);
    public static final ItemRegistryObject<ItemExtraAlloy> SHINING_ALLOY = registerAlloy(ExtraAlloyTier.SHINING, Rarity.RARE);
    public static final ItemRegistryObject<ItemExtraAlloy> SPECTRUM_ALLOY = registerAlloy(ExtraAlloyTier.SPECTRUM, Rarity.EPIC);

    public static final ItemRegistryObject<Item> ENRICHED_OSMIUM = registerEnrich("osmium", Rarity.COMMON);
    public static final ItemRegistryObject<Item> ENRICHED_LEAD = registerEnrich("lead", Rarity.COMMON);
    public static final ItemRegistryObject<Item> ENRICHED_RADIANCE = registerEnrich("radiance", Rarity.COMMON);
    public static final ItemRegistryObject<Item> ENRICHED_THERMONUCLEAR = registerEnrich("thermonuclear", Rarity.UNCOMMON);
    public static final ItemRegistryObject<Item> ENRICHED_SHINING = registerEnrich("shining", Rarity.RARE);
    public static final ItemRegistryObject<Item> ENRICHED_SPECTRUM = registerEnrich("spectrum", Rarity.EPIC);

    public static final ItemRegistryObject<Item> DUST_RADIANCE = EXTRA_ITEMS.register("dust_radiance");

    static {
        for (ResourceType type : EnumUtils.RESOURCE_TYPES) {
            for (ExtraResource resource : ExtraEnumUtils.EXTRA_RESOURCES) {
                if (resource.has(type)) {
                    PROCESSED_RESOURCES.put(type, resource, registerResource(type, resource));
                }
            }
        }
    }

    private static ItemRegistryObject<ExtraItemQIODrive> registerQIODrive(ExtraQIODriveTier tier) {
        return EXTRA_ITEMS.registerItem("qio_drive_" + tier.name().toLowerCase(Locale.ROOT), properties -> new ExtraItemQIODrive(tier, properties));
    }

    private static ItemRegistryObject<ItemUpgrade> registerUpgrade(Upgrade type, Rarity rarity) {
        return EXTRA_ITEMS.registerItem("upgrade_" + type.getSerializedName(), properties -> new ItemUpgrade(type, properties.rarity(rarity)));
    }

    private static ItemRegistryObject<ItemExtraTierInstaller> registerInstaller(@Nullable AdvancedTier fromTier, @NotNull AdvancedTier toTier) {
        // Ensure the name is lower case as with concatenating with values from enums it may not be
        return EXTRA_ITEMS.registerItem(toTier.getLowerName() + "_tier_installer", properties -> new ItemExtraTierInstaller(fromTier, toTier, properties));
    }

    private static ItemRegistryObject<Item> registerCircuit(AdvancedTier tier) {
        return EXTRA_ITEMS.registerItem(tier.getLowerName() + "_control_circuit", properties -> new Item(properties) {

            @NotNull
            @Override
            public Component getName(@NotNull ItemStack stack) {
                return TextComponentUtil.build(tier.getColor(), super.getName(stack));
            }
        });
    }

    private static ItemRegistryObject<ItemExtraAlloy> registerAlloy(ExtraAlloyTier tier, Rarity rarity) {
        return EXTRA_ITEMS.registerItem("alloy_" + tier.getName(), properties -> new ItemExtraAlloy(tier, properties.rarity(rarity)));
    }

    private static ItemRegistryObject<Item> registerResource(ResourceType type, IResource resource) {
        return EXTRA_ITEMS.register(type.getRegistryPrefix() + "_" + resource.getRegistrySuffix());
    }

    private static ItemRegistryObject<Item> registerEnrich(String name, Rarity rarity) {
        return EXTRA_ITEMS.registerItem("enriched_" + name, properties -> new Item(properties.rarity(rarity)));
    }

    public static void register(IEventBus eventBus) {
        EXTRA_ITEMS.register(eventBus);
    }
}
