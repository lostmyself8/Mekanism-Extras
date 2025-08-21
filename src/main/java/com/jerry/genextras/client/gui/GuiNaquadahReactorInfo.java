package com.jerry.genextras.client.gui;

import com.jerry.genextras.common.content.naquadah.NaquadahReactorMultiblockData;
import com.jerry.genextras.common.tile.naquadah.TileEntityNaquadahReactorController;
import mekanism.client.gui.GuiMekanismTile;
import mekanism.client.gui.element.button.MekanismImageButton;
import mekanism.client.gui.element.tab.GuiEnergyTab;
import mekanism.client.gui.element.tab.GuiHeatTab;
import mekanism.client.gui.tooltip.TooltipUtils;
import mekanism.common.MekanismLang;
import mekanism.common.inventory.container.tile.EmptyTileContainer;
import mekanism.common.network.PacketUtils;
import mekanism.common.network.to_server.button.PacketTileButtonPress;
import mekanism.common.util.MekanismUtils;
import mekanism.common.util.UnitDisplayUtils;
import mekanism.common.util.text.EnergyDisplay;
import mekanism.generators.common.GeneratorsLang;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class GuiNaquadahReactorInfo extends GuiMekanismTile<TileEntityNaquadahReactorController, EmptyTileContainer<TileEntityNaquadahReactorController>> {

    public GuiNaquadahReactorInfo(EmptyTileContainer<TileEntityNaquadahReactorController> container, Inventory inv, Component title) {
        super(container, inv, title);
        imageWidth += 10;
        titleLabelY = 5;
    }

    @Override
    protected void addGuiElements() {
        super.addGuiElements();
        addRenderableWidget(new MekanismImageButton(this, 6, 6, 14, getButtonLocation("back"),
                (element, mouseX, mouseY) -> PacketUtils.sendToServer(new PacketTileButtonPress(PacketTileButtonPress.ClickedTileButton.BACK_BUTTON, ((GuiNaquadahReactorInfo) element.gui()).tile))))
                .setTooltip(TooltipUtils.BACK);
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
    }

    @Override
    protected void drawForegroundText(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY) {
        renderTitleTextWithOffset(guiGraphics, 18);//Adjust spacing for back button
        super.drawForegroundText(guiGraphics, mouseX, mouseY);
    }
}
