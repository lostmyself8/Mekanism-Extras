package com.jerry.generator_extras.client.gui.naquadah;

import com.jerry.generator_extras.client.gui.element.GuiNaquadahReactorTab;
import com.jerry.generator_extras.common.network.to_server.ExtraPacketGeneratorsGuiInteract;
import com.jerry.generator_extras.common.tile.naquadah.TileEntityNaquadahReactorController;

import mekanism.client.gui.element.gauge.GaugeType;
import mekanism.client.gui.element.gauge.GuiGasGauge;
import mekanism.client.gui.element.progress.GuiProgress;
import mekanism.client.gui.element.progress.ProgressType;
import mekanism.client.gui.element.text.GuiTextField;
import mekanism.common.inventory.container.tile.EmptyTileContainer;
import mekanism.common.util.text.InputValidator;
import mekanism.generators.common.GeneratorsLang;
import mekanism.generators.common.MekanismGenerators;

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
    protected void addGuiElements() {
        super.addGuiElements();
        addRenderableWidget(new GuiGasGauge(() -> tile.getMultiblock().naquadahTank, () -> tile.getMultiblock().getGasTanks(null), GaugeType.SMALL, this, 25, 64));
        addRenderableWidget(new GuiGasGauge(() -> tile.getMultiblock().fuelTank, () -> tile.getMultiblock().getGasTanks(null), GaugeType.STANDARD, this, 79, 50));
        addRenderableWidget(new GuiGasGauge(() -> tile.getMultiblock().uraniumTank, () -> tile.getMultiblock().getGasTanks(null), GaugeType.SMALL, this, 133, 64));
        addRenderableWidget(new GuiProgress(() -> tile.getMultiblock().isBurning(), ProgressType.SMALL_RIGHT, this, 47, 76));
        addRenderableWidget(new GuiProgress(() -> tile.getMultiblock().isBurning(), ProgressType.SMALL_LEFT, this, 101, 76));
        addRenderableWidget(new GuiNaquadahReactorTab(this, tile, GuiNaquadahReactorTab.NaquadahReactorTab.HEAT));
        addRenderableWidget(new GuiNaquadahReactorTab(this, tile, GuiNaquadahReactorTab.NaquadahReactorTab.STAT));
        injectionRateField = addRenderableWidget(new GuiTextField(this, 98, 115, 26, 11));
        injectionRateField.setFocused(true);
        injectionRateField.setInputValidator(InputValidator.DIGIT)
                .setEnterHandler(this::setInjection)
                .setMaxLength(2);
    }

    @Override
    protected void drawForegroundText(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY) {
        renderTitleText(guiGraphics);
        drawCenteredText(guiGraphics, GeneratorsLang.REACTOR_INJECTION_RATE.translate(tile.getMultiblock().getInjectionRate()), 0, imageWidth, 35, titleTextColor());
        drawString(guiGraphics, GeneratorsLang.REACTOR_EDIT_RATE.translate(), 50, 117, titleTextColor());
        super.drawForegroundText(guiGraphics, mouseX, mouseY);
    }

    private void setInjection() {
        if (!injectionRateField.getText().isEmpty()) {
            MekanismGenerators.packetHandler().sendToServer(new ExtraPacketGeneratorsGuiInteract(ExtraPacketGeneratorsGuiInteract.ExtraGeneratorsGuiInteraction.INJECTION_RATE, tile, Integer.parseInt(injectionRateField.getText())));
            injectionRateField.setText("");
        }
    }
}
