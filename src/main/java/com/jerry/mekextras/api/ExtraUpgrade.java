package com.jerry.mekextras.api;

import com.jerry.mekextras.api.text.APIExtraLang;
import mekanism.api.SerializationConstants;
import mekanism.api.Upgrade;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.math.MathUtils;
import mekanism.api.text.EnumColor;
import mekanism.api.text.IHasTranslationKey;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@NothingNullByDefault
public enum ExtraUpgrade implements IHasTranslationKey {
    IONIC_MEMBRANE("ionic_membrane", APIExtraLang.UPGRADE_IONIC_MEMBRANE, APIExtraLang.UPGRADE_IONIC_MEMBRANE_DESCRIPTION, 8, EnumColor.RED);

    private static final ExtraUpgrade[] UPGRADES = values();

    private final String name;
    private final APIExtraLang langKey;
    private final APIExtraLang descLangKey;
    private final int maxStack;
    private final EnumColor color;

    ExtraUpgrade(String name, APIExtraLang langKey, APIExtraLang descLangKey, int maxStack, EnumColor color) {
        this.name = name;
        this.langKey = langKey;
        this.descLangKey = descLangKey;
        this.maxStack = maxStack;
        this.color = color;
    }

    public static Map<ExtraUpgrade, Integer> buildMap(@Nullable CompoundTag nbtTags) {
        Map<ExtraUpgrade, Integer> upgrades = null;
        if (nbtTags != null && nbtTags.contains(SerializationConstants.UPGRADES, Tag.TAG_LIST)) {
            ListTag list = nbtTags.getList(SerializationConstants.UPGRADES, Tag.TAG_COMPOUND);
            for (int tagCount = 0; tagCount < list.size(); tagCount++) {
                CompoundTag compound = list.getCompound(tagCount);
                ExtraUpgrade upgrade = byIndexStatic(compound.getInt(SerializationConstants.TYPE));
                //Validate the nbt isn't malformed with a negative or zero amount
                int installed = Math.max(compound.getInt(SerializationConstants.AMOUNT), 0);
                if (installed > 0) {
                    if (upgrades == null) {
                        upgrades = new EnumMap<>(ExtraUpgrade.class);
                    }
                    upgrades.put(upgrade, installed);
                }
            }
        }
        return upgrades == null ? Collections.emptyMap() : upgrades;
    }

    /**
     * Writes a map of upgrades to their amounts to NBT.
     *
     * @param upgrades Upgrades to store.
     * @param nbtTags  Tag to write to.
     */
    public static void saveMap(Map<Upgrade, Integer> upgrades, CompoundTag nbtTags) {
        ListTag list = new ListTag();
        for (Map.Entry<Upgrade, Integer> entry : upgrades.entrySet()) {
            list.add(entry.getKey().getTag(entry.getValue()));
        }
        nbtTags.put(SerializationConstants.UPGRADES, list);
    }

    /**
     * Writes this upgrade with given amount to NBT.
     *
     * @param amount Amount.
     *
     * @return NBT.
     */
    public CompoundTag getTag(int amount) {
        CompoundTag compound = new CompoundTag();
        compound.putInt(SerializationConstants.TYPE, ordinal());
        compound.putInt(SerializationConstants.AMOUNT, amount);
        return compound;
    }

    /**
     * Gets the "raw" name of this upgrade for use in registry names.
     */
    public String getRawName() {
        return name;
    }

    @Override
    public String getTranslationKey() {
        return langKey.getTranslationKey();
    }

    /**
     * Gets the description for this upgrade.
     */
    public Component getDescription() {
        return descLangKey.translate();
    }

    /**
     * Gets the max number of upgrades of this type that can be installed.
     */
    public int getMax() {
        return maxStack;
    }

    /**
     * Gets the color to use when rendering various information related to this upgrade.
     */
    public EnumColor getColor() {
        return color;
    }

    /**
     * Gets an upgrade by index.
     *
     * @param index Index of the upgrade.
     */
    public static ExtraUpgrade byIndexStatic(int index) {
        return MathUtils.getByIndexMod(UPGRADES, index);
    }

    public interface IUpgradeInfoHandler {

        List<Component> getInfo(ExtraUpgrade upgrade);
    }
}
