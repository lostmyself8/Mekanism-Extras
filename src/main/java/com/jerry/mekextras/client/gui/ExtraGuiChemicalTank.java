package com.jerry.mekextras.client.gui;

import com.jerry.mekextras.client.gui.element.button.ExtraGuiGasMode;
import com.jerry.mekextras.common.tile.ExtraTileEntityChemicalTank;
import mekanism.api.chemical.IChemicalTank;
import mekanism.client.gui.GuiConfigurableTile;
import mekanism.client.gui.element.GuiInnerScreen;
import mekanism.client.gui.element.GuiSideHolder;
import mekanism.client.gui.element.bar.GuiChemicalBar;
import mekanism.common.MekanismLang;
import mekanism.common.inventory.container.tile.MekanismTileContainer;
import mekanism.common.util.text.TextUtils;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ExtraGuiChemicalTank extends GuiConfigurableTile<ExtraTileEntityChemicalTank, MekanismTileContainer<ExtraTileEntityChemicalTank>> {
    public ExtraGuiChemicalTank(MekanismTileContainer<ExtraTileEntityChemicalTank> container, Inventory inv, Component title) {
        super(container, inv, title);
        dynamicSlots = true;
    }

    @Override
    protected void addGuiElements() {
        //Add the side holder before the slots, as it holds a couple of the slots
        addRenderableWidget(GuiSideHolder.armorHolder(this));
        super.addGuiElements();
        addRenderableWidget(new GuiChemicalBar(this, GuiChemicalBar.getProvider(tile.getChemicalTank(), tile.getChemicalTanks(null)), 42, 16, 116, 10, true));
        addRenderableWidget(new GuiInnerScreen(this, 42, 37, 118, 28, () -> {
            List<Component> ret = new ArrayList<>();
            IChemicalTank tank = tile.getChemicalTank();
            if (tank.isEmpty()) {
                ret.add(MekanismLang.CHEMICAL.translate(MekanismLang.NONE));
                ret.add(MekanismLang.GENERIC_FRACTION.translate(0, TextUtils.format(tile.getTier().getStorage())));
            } else {
                ret.add(MekanismLang.CHEMICAL.translate(tank.getStack()));
                ret.add(MekanismLang.GENERIC_FRACTION.translate(TextUtils.format(tank.getStored()), TextUtils.format(tank.getCapacity())));
            }
            return ret;
        }));
        addRenderableWidget(new ExtraGuiGasMode(this, 159, 72, true, () -> tile.dumping, tile.getBlockPos(), 0));
    }

    @Override
    protected void drawForegroundText(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY) {
        renderTitleText(guiGraphics);
        renderInventoryText(guiGraphics);
        super.drawForegroundText(guiGraphics, mouseX, mouseY);
    }
}
