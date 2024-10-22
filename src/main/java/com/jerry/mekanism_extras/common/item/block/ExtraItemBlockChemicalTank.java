package com.jerry.mekanism_extras.common.item.block;

import com.jerry.mekanism_extras.common.block.attribute.ExtraAttribute;
import com.jerry.mekanism_extras.common.registry.ExtraDataComponents;
import com.jerry.mekanism_extras.common.tier.CTTier;
import com.jerry.mekanism_extras.common.tile.ExtraTileEntityChemicalTank;
import mekanism.api.text.EnumColor;
import mekanism.common.MekanismLang;
import mekanism.common.attachments.component.AttachedEjector;
import mekanism.common.attachments.component.AttachedSideConfig;
import mekanism.common.block.prefab.BlockTile;
import mekanism.common.content.blocktype.Machine;
import mekanism.common.lib.transmitter.TransmissionType;
import mekanism.common.registries.MekanismDataComponents;
import mekanism.common.util.ChemicalUtil;
import mekanism.common.util.StorageUtils;
import mekanism.common.util.text.TextUtils;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ExtraItemBlockChemicalTank extends ExtraItemBlockTooltip<BlockTile.BlockTileModel<ExtraTileEntityChemicalTank, Machine<ExtraTileEntityChemicalTank>>> {

    private static final AttachedSideConfig SIDE_CONFIG = Util.make(() -> {
        Map<TransmissionType, AttachedSideConfig.LightConfigInfo> configInfo = new EnumMap<>(TransmissionType.class);
        configInfo.put(TransmissionType.ITEM, AttachedSideConfig.LightConfigInfo.FRONT_OUT_NO_EJECT);
        configInfo.put(TransmissionType.CHEMICAL, AttachedSideConfig.LightConfigInfo.FRONT_OUT_EJECT);
        return new AttachedSideConfig(configInfo);
    });

    public ExtraItemBlockChemicalTank(BlockTile.BlockTileModel<ExtraTileEntityChemicalTank, Machine<ExtraTileEntityChemicalTank>> block, Properties properties) {
        super(block, true, properties
                .component(ExtraDataComponents.DUMP_MODE, ExtraTileEntityChemicalTank.GasMode.IDLE)
                .component(MekanismDataComponents.EJECTOR, AttachedEjector.DEFAULT)
                .component(MekanismDataComponents.SIDE_CONFIG, SIDE_CONFIG)
        );
    }

    @Override
    public CTTier getAdvanceTier() {
        return ExtraAttribute.getAdvanceTier(getBlock(), CTTier.class);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable TooltipContext context, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        CTTier tier = getAdvanceTier();
        StorageUtils.addStoredSubstance(stack, tooltip, false);
        tooltip.add(MekanismLang.CAPACITY_MB.translateColored(EnumColor.INDIGO, EnumColor.GRAY, TextUtils.format(tier.getStorage())));
        super.appendHoverText(stack, context, tooltip, flag);
    }

    @Override
    public boolean isBarVisible(@NotNull ItemStack stack) {
        // No bar for empty or stacked containers as bars are drawn on top of stack count number
        if (stack.getCount() > 1) {
            //Note: Technically this is handled by the below checks as the capability isn't exposed,
            // but we may as well short circuit it here
            return false;
        }
        return ChemicalUtil.hasAnyChemical(stack);
    }

    @Override
    public int getBarWidth(@NotNull ItemStack stack) {
        return StorageUtils.getBarWidth(stack);
    }

    @Override
    public int getBarColor(@NotNull ItemStack stack) {
        return ChemicalUtil.getRGBDurabilityForDisplay(stack);
    }
}
