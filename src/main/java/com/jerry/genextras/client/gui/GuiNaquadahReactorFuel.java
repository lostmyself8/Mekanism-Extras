package com.jerry.genextras.client.gui;

import com.jerry.genextras.client.gui.element.GuiNaquadahReactorTab;
import com.jerry.genextras.common.tile.naquadah.TileEntityNaquadahReactorController;
import com.jerry.mekextras.common.network.to_server.ExtraPacketGuiInteract;
import mekanism.client.gui.element.gauge.GaugeType;
import mekanism.client.gui.element.gauge.GuiChemicalGauge;
import mekanism.client.gui.element.progress.GuiProgress;
import mekanism.client.gui.element.progress.ProgressType;
import mekanism.client.gui.element.text.GuiTextField;
import mekanism.common.inventory.container.tile.EmptyTileContainer;
import mekanism.common.network.PacketUtils;
import mekanism.common.util.text.InputValidator;
import mekanism.generators.common.GeneratorsLang;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

public class GuiNaquadahReactorFuel extends GuiNaquadahReactorInfo {

    private GuiTextField injectionRateField;

    public GuiNaquadahReactorFuel(EmptyTileContainer<TileEntityNaquadahReactorController> container, Inventory inv, Component title) {
        super(container, inv, title);

    }

    @Override
    public void addGuiElements() {
        super.addGuiElements();
        addRenderableWidget(new GuiChemicalGauge(() -> tile.getMultiblock().deuteriumTank, () -> tile.getMultiblock().getChemicalTanks(null), GaugeType.SMALL, this, 30, 64));
        addRenderableWidget(new GuiChemicalGauge(() -> tile.getMultiblock().fuelTank, () -> tile.getMultiblock().getChemicalTanks(null), GaugeType.STANDARD, this, 84, 50));
        addRenderableWidget(new GuiChemicalGauge(() -> tile.getMultiblock().tritiumTank, () -> tile.getMultiblock().getChemicalTanks(null), GaugeType.SMALL, this, 138, 64));
        addRenderableWidget(new GuiProgress(() -> tile.getMultiblock().isBurning(), ProgressType.SMALL_RIGHT, this, 52, 76));
        addRenderableWidget(new GuiProgress(() -> tile.getMultiblock().isBurning(), ProgressType.SMALL_LEFT, this, 106, 76));
        addRenderableWidget(new GuiNaquadahReactorTab(this, tile, GuiNaquadahReactorTab.NaquadahReactorTab.HEAT));
        addRenderableWidget(new GuiNaquadahReactorTab(this, tile, GuiNaquadahReactorTab.NaquadahReactorTab.STAT));
        injectionRateField = addRenderableWidget(new GuiTextField(this, 103, 115, 26, 11));
        injectionRateField.setInputValidator(InputValidator.DIGIT)
                .setEnterHandler(this::setInjection)
                .setMaxLength(2);
        setInitialFocus(injectionRateField);
    }

    @Override
    protected void drawForegroundText(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY) {
        drawScrollingString(guiGraphics, GeneratorsLang.REACTOR_INJECTION_RATE.translate(tile.getMultiblock().getInjectionRate()), 0, 35, TextAlignment.CENTER, titleTextColor(), 16, false);
        drawScrollingString(guiGraphics, GeneratorsLang.REACTOR_EDIT_RATE.translate(), 4, 117, TextAlignment.RIGHT, titleTextColor(), 99, 2, false);
        super.drawForegroundText(guiGraphics, mouseX, mouseY);
    }

    private void setInjection() {
        if (!injectionRateField.getText().isEmpty()) {
            PacketUtils.sendToServer(new ExtraPacketGuiInteract(ExtraPacketGuiInteract.ExtraGuiInteraction.INJECTION_RATE, tile, Integer.parseInt(injectionRateField.getText())));
            injectionRateField.setText("");
        }
    }
}
