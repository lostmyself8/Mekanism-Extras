package com.jerry.generator_extras.client.gui.plasma;

import com.jerry.generator_extras.common.ExtraGenLang;
import com.jerry.generator_extras.common.content.plasma.PlasmaEvaporationMultiblockData;
import com.jerry.generator_extras.common.tile.plasma.TileEntityPlasmaEvaporationController;

import mekanism.api.recipes.cache.CachedRecipe.OperationTracker.RecipeError;
import mekanism.client.gui.GuiMekanismTile;
import mekanism.client.gui.element.GuiInnerScreen;
import mekanism.client.gui.element.bar.GuiBar.IBarInfoHandler;
import mekanism.client.gui.element.bar.GuiHorizontalRateBar;
import mekanism.client.gui.element.gauge.GaugeType;
import mekanism.client.gui.element.gauge.GuiFluidGauge;
import mekanism.client.gui.element.gauge.GuiGasGauge;
import mekanism.client.gui.element.tab.GuiWarningTab;
import mekanism.common.MekanismLang;
import mekanism.common.inventory.container.tile.MekanismTileContainer;
import mekanism.common.inventory.warning.IWarningTracker;
import mekanism.common.inventory.warning.WarningTracker.WarningType;
import mekanism.common.util.MekanismUtils;
import mekanism.common.util.UnitDisplayUtils.TemperatureUnit;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.BooleanSupplier;

public class GuiPlasmaEvaporationController extends GuiMekanismTile<TileEntityPlasmaEvaporationController, MekanismTileContainer<TileEntityPlasmaEvaporationController>> {

    public GuiPlasmaEvaporationController(MekanismTileContainer<TileEntityPlasmaEvaporationController> container, Inventory inv, Component title) {
        super(container, inv, title);
        imageWidth = 196;
        imageHeight += 29;
        inventoryLabelX = 6;
        inventoryLabelY = imageHeight - 96;
        titleLabelY = 5;
        dynamicSlots = true;
    }

    @Override
    protected void addGuiElements() {
        super.addGuiElements();
        PlasmaEvaporationMultiblockData multiblock = tile.getMultiblock();

        // Add inner screen
        addRenderableWidget(new GuiInnerScreen(this, 49, 19, 100, 60, () -> List.of(
                MekanismLang.MULTIBLOCK_FORMED.translate(),
                MekanismLang.EVAPORATION_HEIGHT.translate(multiblock.height()),
                MekanismLang.TEMPERATURE.translate(MekanismUtils.getTemperatureDisplay(multiblock.getTemperature(), TemperatureUnit.KELVIN, true)),
                MekanismLang.FLUID_PRODUCTION.translate(Math.round(multiblock.lastGain * 100D) / 100D),
                ExtraGenLang.PLASMA_CONSUMPTION.translate(Math.round(multiblock.lastPlasmaConsumption * 100D) / 100D)))
                .spacing(1).jeiCategory(tile));

        // Add rate bar
        addRenderableWidget(new GuiHorizontalRateBar(this, new IBarInfoHandler() {

            @Override
            public Component getTooltip() {
                return MekanismUtils.getTemperatureDisplay(tile.getMultiblock().getTemperature(), TemperatureUnit.KELVIN, true);
            }

            @Override
            public double getLevel() {
                return Math.min(1, tile.getMultiblock().getTemperature() / 2_000_000L); // Does
                                                                                        // not
                                                                                        // mean
                                                                                        // that
                                                                                        // it
                                                                                        // really
                                                                                        // has
                                                                                        // a
                                                                                        // rate
                                                                                        // cap
            }
        }, 59, 83))
                .warning(WarningType.INPUT_DOESNT_PRODUCE_OUTPUT, getWarningCheck(RecipeError.INPUT_DOESNT_PRODUCE_OUTPUT));

        // Add input tanks
        addRenderableWidget(new GuiGasGauge(() -> tile.getMultiblock().plasmaInputTank,
                () -> multiblock.getGasTanks(null),
                GaugeType.STANDARD, this, 6, 13, 18, 80));
        addRenderableWidget(new GuiFluidGauge(() -> tile.getMultiblock().inputTank,
                () -> multiblock.getFluidTanks(null),
                GaugeType.STANDARD, this, 28, 13, 18, 80))
                .warning(WarningType.NO_MATCHING_RECIPE, getWarningCheck(RecipeError.NOT_ENOUGH_INPUT));

        // Add output tanks
        addRenderableWidget(new GuiFluidGauge(() -> tile.getMultiblock().outputTank,
                () -> multiblock.getFluidTanks(null),
                GaugeType.STANDARD, this, 152, 13, 18, 80))
                .warning(WarningType.NO_SPACE_IN_OUTPUT, getWarningCheck(RecipeError.NOT_ENOUGH_OUTPUT_SPACE));
        addRenderableWidget(new GuiGasGauge(() -> tile.getMultiblock().plasmaOutputTank,
                () -> multiblock.getGasTanks(null),
                GaugeType.STANDARD, this, 172, 13, 18, 80));
    }

    @Override
    protected void addWarningTab(IWarningTracker warningTracker) {
        // Put the warning tab where the energy tab is as we don't have energy
        addRenderableWidget(new GuiWarningTab(this, warningTracker, 137));
    }

    @Override
    protected void drawForegroundText(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY) {
        renderTitleText(guiGraphics);
        drawString(guiGraphics, playerInventoryTitle, inventoryLabelX, inventoryLabelY, titleTextColor());
        super.drawForegroundText(guiGraphics, mouseX, mouseY);
    }

    private BooleanSupplier getWarningCheck(RecipeError error) {
        return () -> tile.getMultiblock().hasWarning(error);
    }
}
