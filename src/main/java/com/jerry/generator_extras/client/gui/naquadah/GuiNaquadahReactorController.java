package com.jerry.generator_extras.client.gui.naquadah;

import com.jerry.generator_extras.client.gui.element.GuiNaquadahReactorTab;
import com.jerry.generator_extras.common.content.naquadah.NaquadahReactorMultiblockData;
import com.jerry.generator_extras.common.tile.naquadah.TileEntityNaquadahReactorController;
import mekanism.client.gui.GuiMekanismTile;
import mekanism.client.gui.element.tab.GuiEnergyTab;
import mekanism.client.gui.element.tab.GuiHeatTab;
import mekanism.common.MekanismLang;
import mekanism.common.inventory.container.tile.MekanismTileContainer;
import mekanism.common.util.MekanismUtils;
import mekanism.common.util.UnitDisplayUtils;
import mekanism.common.util.text.EnergyDisplay;
import mekanism.generators.common.GeneratorsLang;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GuiNaquadahReactorController extends GuiMekanismTile<TileEntityNaquadahReactorController, MekanismTileContainer<TileEntityNaquadahReactorController>> {

    public GuiNaquadahReactorController(MekanismTileContainer<TileEntityNaquadahReactorController> container, Inventory inv, Component title) {
        super(container, inv, title);
        dynamicSlots = true;
        titleLabelY = 5;
    }

    @Override
    protected void addGuiElements() {
        super.addGuiElements();
        if (tile.getMultiblock().isFormed()) {
            addRenderableWidget(new GuiEnergyTab(this, () -> {
                NaquadahReactorMultiblockData multiblock = tile.getMultiblock();
                return List.of(MekanismLang.STORING.translate(EnergyDisplay.of(multiblock.energyContainer)),
                        GeneratorsLang.PRODUCING_AMOUNT.translate(EnergyDisplay.of(multiblock.getPassiveGeneration(false, true))));
            }));
            addRenderableWidget(new GuiHeatTab(this, () -> {
                NaquadahReactorMultiblockData multiblock = tile.getMultiblock();
                Component transfer = MekanismUtils.getTemperatureDisplay(multiblock.lastTransferLoss, UnitDisplayUtils.TemperatureUnit.KELVIN, false);
                Component environment = MekanismUtils.getTemperatureDisplay(multiblock.lastEnvironmentLoss, UnitDisplayUtils.TemperatureUnit.KELVIN, false);
                return List.of(MekanismLang.TRANSFERRED_RATE.translate(transfer), MekanismLang.DISSIPATED_RATE.translate(environment));
            }));

            addRenderableWidget(new GuiNaquadahReactorTab(this, tile, GuiNaquadahReactorTab.NaquadahReactorTab.HEAT));
            addRenderableWidget(new GuiNaquadahReactorTab(this, tile, GuiNaquadahReactorTab.NaquadahReactorTab.FUEL));
            addRenderableWidget(new GuiNaquadahReactorTab(this, tile, GuiNaquadahReactorTab.NaquadahReactorTab.STAT));
        }
    }

    @Override
    protected void drawForegroundText(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY) {
        renderTitleText(guiGraphics);
        drawString(guiGraphics, MekanismLang.MULTIBLOCK_FORMED.translate(), 8, 16, titleTextColor());
        super.drawForegroundText(guiGraphics, mouseX, mouseY);
    }
}
