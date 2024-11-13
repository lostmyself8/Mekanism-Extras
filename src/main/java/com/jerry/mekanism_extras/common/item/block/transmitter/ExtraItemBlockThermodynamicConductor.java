package com.jerry.mekanism_extras.common.item.block.transmitter;

import com.jerry.mekanism_extras.common.block.transmitter.ExtraBlockThermodynamicConductor;
import com.jerry.mekanism_extras.common.item.block.ExtraItemBlockMultipartAble;
import com.jerry.mekanism_extras.common.tier.transmitter.TCTier;
import mekanism.api.text.EnumColor;
import mekanism.client.key.MekKeyHandler;
import mekanism.client.key.MekanismKeyHandler;
import mekanism.common.MekanismLang;
import mekanism.common.block.attribute.Attribute;
import mekanism.common.tier.ConductorTier;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class ExtraItemBlockThermodynamicConductor extends ExtraItemBlockMultipartAble<ExtraBlockThermodynamicConductor> {

    public ExtraItemBlockThermodynamicConductor(ExtraBlockThermodynamicConductor block) {
        super(block);
    }

    @NotNull
    @Override
    public ConductorTier getTier() {
        return Objects.requireNonNull(Attribute.getTier(getBlock(), ConductorTier.class));
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, Level world, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        if (MekKeyHandler.isKeyPressed(MekanismKeyHandler.detailsKey)) {
            tooltip.add(MekanismLang.CAPABLE_OF_TRANSFERRING.translateColored(EnumColor.DARK_GRAY));
            tooltip.add(MekanismLang.HEAT.translateColored(EnumColor.PURPLE, MekanismLang.MEKANISM));
        } else {
            tooltip.add(MekanismLang.CONDUCTION.translateColored(EnumColor.INDIGO, EnumColor.GRAY, TCTier.getConduction(this.getTier())));
            tooltip.add(MekanismLang.INSULATION.translateColored(EnumColor.INDIGO, EnumColor.GRAY, TCTier.getConductionInsulation(this.getTier())));
            tooltip.add(MekanismLang.HEAT_CAPACITY.translateColored(EnumColor.INDIGO, EnumColor.GRAY, TCTier.getHeatCapacity(this.getTier())));
            tooltip.add(MekanismLang.HOLD_FOR_DETAILS.translateColored(EnumColor.GRAY, EnumColor.INDIGO, MekanismKeyHandler.detailsKey.getTranslatedKeyMessage()));
        }
    }
}
