package com.jerry.mekextras.common.block.attribute;

import com.jerry.mekextras.api.ExtraUpgrade;

import mekanism.api.Upgrade;
import mekanism.common.block.attribute.Attribute;
import mekanism.common.block.attribute.AttributeUpgradeSupport;

import org.jetbrains.annotations.NotNull;

import java.util.Set;

public record ExtraAttributeUpgradeSupport(@NotNull Set<Upgrade> supportedUpgrades) implements Attribute {

    public static final AttributeUpgradeSupport EXTRA_MACHINE_NO_STACK_UPGRADES = AttributeUpgradeSupport.create(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.MUFFLING, ExtraUpgrade.CREATIVE);
    public static final AttributeUpgradeSupport EXTRA_ADVANCED_MACHINE_NO_STACK_UPGRADES = AttributeUpgradeSupport.create(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.CHEMICAL, Upgrade.MUFFLING, ExtraUpgrade.CREATIVE);
    public static final AttributeUpgradeSupport EXTRA_MACHINE_UPGRADES = AttributeUpgradeSupport.create(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.MUFFLING, ExtraUpgrade.STACK, ExtraUpgrade.CREATIVE);
    public static final AttributeUpgradeSupport EXTRA_ADVANCED_MACHINE_UPGRADES = AttributeUpgradeSupport.create(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.CHEMICAL, Upgrade.MUFFLING, ExtraUpgrade.STACK, ExtraUpgrade.CREATIVE);
}
