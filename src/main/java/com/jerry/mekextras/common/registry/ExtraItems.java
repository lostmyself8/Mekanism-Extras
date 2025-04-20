package com.jerry.mekextras.common.registry;

import com.jerry.mekextras.MekanismExtras;
import com.jerry.mekextras.api.tier.AdvancedTier;
import com.jerry.mekextras.api.tier.ExtraAlloyTier;
import com.jerry.mekextras.common.item.ExtraItemAlloy;
import com.jerry.mekextras.common.item.ExtraItemTierInstaller;
import com.jerry.mekextras.common.item.ExtraItemQIODrive;
import com.jerry.mekextras.common.tier.QIODriveAdvancedTier;
import mekanism.api.text.TextComponentUtil;
import mekanism.common.registration.impl.ItemDeferredRegister;
import mekanism.common.registration.impl.ItemRegistryObject;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.neoforged.bus.api.IEventBus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;

public class ExtraItems {
    public static final ItemDeferredRegister EXTRA_ITEMS = new ItemDeferredRegister(MekanismExtras.MOD_ID);

    // QIO Drives
    public static final ItemRegistryObject<ExtraItemQIODrive> COLLAPSE_QIO_DRIVE = registerQIODrive(QIODriveAdvancedTier.COLLAPSE);
    public static final ItemRegistryObject<ExtraItemQIODrive> GAMMA_QIO_DRIVE = registerQIODrive(QIODriveAdvancedTier.GAMMA);
    public static final ItemRegistryObject<ExtraItemQIODrive> BLACK_HOLE_QIO_DRIVE = registerQIODrive(QIODriveAdvancedTier.BLACK_HOLE);
    public static final ItemRegistryObject<ExtraItemQIODrive> SINGULARITY_QIO_DRIVE = registerQIODrive(QIODriveAdvancedTier.SINGULARITY);

    public static final ItemRegistryObject<ExtraItemTierInstaller> ABSOLUTE_TIER_INSTALLER = registerInstaller(null, AdvancedTier.ABSOLUTE);
    public static final ItemRegistryObject<ExtraItemTierInstaller> SUPREME_TIER_INSTALLER = registerInstaller(AdvancedTier.ABSOLUTE, AdvancedTier.SUPREME);
    public static final ItemRegistryObject<ExtraItemTierInstaller> COSMIC_TIER_INSTALLER = registerInstaller(AdvancedTier.SUPREME, AdvancedTier.COSMIC);
    public static final ItemRegistryObject<ExtraItemTierInstaller> INFINITE_TIER_INSTALLER = registerInstaller(AdvancedTier.COSMIC, AdvancedTier.INFINITE);

    public static final ItemRegistryObject<Item> ABSOLUTE_CONTROL_CIRCUIT = registerCircuit(AdvancedTier.ABSOLUTE);
    public static final ItemRegistryObject<Item> SUPREME_CONTROL_CIRCUIT = registerCircuit(AdvancedTier.SUPREME);
    public static final ItemRegistryObject<Item> COSMIC_CONTROL_CIRCUIT = registerCircuit(AdvancedTier.COSMIC);
    public static final ItemRegistryObject<Item> INFINITE_CONTROL_CIRCUIT = registerCircuit(AdvancedTier.INFINITE);

    public static final ItemRegistryObject<Item> RADIANCE_ALLOY = EXTRA_ITEMS.register("alloy_radiance", Rarity.COMMON);
    public static final ItemRegistryObject<ExtraItemAlloy> THERMONUCLEAR_ALLOY = registerAlloy(ExtraAlloyTier.THERMONUCLEAR, Rarity.UNCOMMON);
    public static final ItemRegistryObject<ExtraItemAlloy> SHINING_ALLOY = registerAlloy(ExtraAlloyTier.SHINING, Rarity.RARE);
    public static final ItemRegistryObject<ExtraItemAlloy> SPECTRUM_ALLOY = registerAlloy(ExtraAlloyTier.SPECTRUM, Rarity.EPIC);

    public static final ItemRegistryObject<Item> ENRICHED_OSMIUM = registerEnrich("osmium", Rarity.COMMON);
    public static final ItemRegistryObject<Item> ENRICHED_RADIANCE = registerEnrich("radiance", Rarity.COMMON);
    public static final ItemRegistryObject<Item> ENRICHED_THERMONUCLEAR = registerEnrich("thermonuclear", Rarity.UNCOMMON);
    public static final ItemRegistryObject<Item> ENRICHED_SHINING = registerEnrich("shining", Rarity.RARE);
    public static final ItemRegistryObject<Item> ENRICHED_SPECTRUM = registerEnrich("spectrum", Rarity.EPIC);
    public static final ItemRegistryObject<Item> DUST_RADIANCE = EXTRA_ITEMS.register("dust_radiance");

    private static ItemRegistryObject<ExtraItemQIODrive> registerQIODrive(QIODriveAdvancedTier tier) {
        return EXTRA_ITEMS.registerItem("qio_drive_" + tier.name().toLowerCase(Locale.ROOT), properties -> new ExtraItemQIODrive(tier, properties));
    }

    private static ItemRegistryObject<ExtraItemTierInstaller> registerInstaller(@Nullable AdvancedTier fromTier, @NotNull AdvancedTier toTier) {
        //Ensure the name is lower case as with concatenating with values from enums it may not be
        return EXTRA_ITEMS.registerItem(toTier.getLowerName() + "_tier_installer", properties -> new ExtraItemTierInstaller(fromTier, toTier, properties));
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

    private static ItemRegistryObject<ExtraItemAlloy> registerAlloy(ExtraAlloyTier tier, Rarity rarity) {
        return EXTRA_ITEMS.registerItem("alloy_" + tier.getName(), properties -> new ExtraItemAlloy(tier, properties.rarity(rarity)));
    }

    private static ItemRegistryObject<Item> registerEnrich(String name, Rarity rarity) {
        return EXTRA_ITEMS.registerItem("enriched_" + name, properties -> new Item(properties.rarity(rarity)));
    }
    public static void register(IEventBus eventBus) {
        EXTRA_ITEMS.register(eventBus);
    }
}
