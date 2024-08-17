package com.jerry.mekanism_extras.common.item.qio;

import mekanism.api.text.EnumColor;
import mekanism.api.text.TextComponentUtil;
import mekanism.common.MekanismLang;
import mekanism.common.content.qio.IQIODriveItem;
import mekanism.common.util.text.TextUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ExtraItemQIODrive extends Item implements IQIODriveItem {

    private final ExtraQIODriverTier tier;

    public ExtraItemQIODrive(ExtraQIODriverTier tier, Properties properties) {
        super(properties.stacksTo(1));
        this.tier = tier;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemStack, @Nullable Level level, List<Component> components, @NotNull TooltipFlag tooltipFlag) {
        DriveMetadata meta = DriveMetadata.load(itemStack);
        components.add(MekanismLang.QIO_ITEMS_DETAIL.translateColored(EnumColor.GRAY, EnumColor.INDIGO,
                TextUtils.format(meta.count()), TextUtils.format(getCountCapacity(itemStack))));
        components.add(MekanismLang.QIO_TYPES_DETAIL.translateColored(EnumColor.GRAY, EnumColor.INDIGO,
                TextUtils.format(meta.types()), TextUtils.format(getTypeCapacity(itemStack))));
    }

    @Override
    public @NotNull Component getName(@NotNull ItemStack itemStack) {
        return TextComponentUtil.build(this.tier.getBaseTier().getColor(), super.getName(itemStack));
    }

    @Override
    public long getCountCapacity(ItemStack stack) {
        return this.tier.getMaxCount();
    }

    @Override
    public int getTypeCapacity(ItemStack stack) {
        return this.tier.getMaxTypes();
    }
}
