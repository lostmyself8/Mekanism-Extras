package com.jerry.mekextras.common.item;

import com.jerry.mekextras.common.tier.QIODriveAdvanceTier;
import mekanism.api.text.EnumColor;
import mekanism.api.text.TextComponentUtil;
import mekanism.common.MekanismLang;
import mekanism.common.attachments.qio.DriveMetadata;
import mekanism.common.content.qio.IQIODriveItem;
import mekanism.common.registries.MekanismDataComponents;
import mekanism.common.util.text.TextUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ExtraItemQIODrive extends Item implements IQIODriveItem {
    private final QIODriveAdvanceTier tier;

    public ExtraItemQIODrive(QIODriveAdvanceTier tier, Properties properties) {
        super(properties.stacksTo(1));
        this.tier = tier;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, Item.@NotNull TooltipContext context, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        DriveMetadata meta = stack.getOrDefault(MekanismDataComponents.DRIVE_METADATA, DriveMetadata.EMPTY);
        tooltip.add(MekanismLang.QIO_ITEMS_DETAIL.translateColored(EnumColor.GRAY, EnumColor.INDIGO,
                TextUtils.format(meta.count()), TextUtils.format(getCountCapacity(stack))));
        tooltip.add(MekanismLang.QIO_TYPES_DETAIL.translateColored(EnumColor.GRAY, EnumColor.INDIGO,
                TextUtils.format(meta.types()), TextUtils.format(getTypeCapacity(stack))));
    }

    @NotNull
    @Override
    public Component getName(@NotNull ItemStack stack) {
        return TextComponentUtil.build(tier.getAdvanceTier().getColor(), super.getName(stack));
    }

    @Override
    public long getCountCapacity(ItemStack stack) {
        return tier.getCount();
    }

    @Override
    public int getTypeCapacity(ItemStack stack) {
        return tier.getTypes();
    }
}
