package com.jerry.generator_extras.client.gui.plasma;

import com.jerry.generator_extras.common.ExtraGenLang;
import com.jerry.generator_extras.common.content.plasma.PlasmaEvaporationMultiblockData;
import com.jerry.generator_extras.common.tile.plasma.TileEntityPlasmaEvaporationController;
import mekanism.api.recipes.cache.CachedRecipe.OperationTracker.RecipeError;
import mekanism.client.gui.GuiMekanismTile;
import mekanism.client.gui.element.GuiInnerScreen;
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
        inventoryLabelY += 2;
        titleLabelY = 4;
        dynamicSlots = true;
    }

    @Override
    protected void addGuiElements() {
        super.addGuiElements();
        PlasmaEvaporationMultiblockData multiblock = tile.getMultiblock();

        // Add inner screen
        addRenderableWidget(new GuiInnerScreen(this, 78, 19, 80, 40, () -> {
            return List.of(MekanismLang.MULTIBLOCK_FORMED.translate(),
                    MekanismLang.EVAPORATION_HEIGHT.translate(multiblock.height()),
                    MekanismLang.TEMPERATURE.translate(MekanismUtils.getTemperatureDisplay(multiblock.getTemperature(), TemperatureUnit.KELVIN, true)),
                    MekanismLang.FLUID_PRODUCTION.translate(Math.round(multiblock.lastGain * 100D) / 100D),
                    ExtraGenLang.PLASMA_CONSUMPTION.translate(Math.round(multiblock.lastPlasmaConsumption * 100D) / 100D));
        }).spacing(1).jeiCategory(tile));

        // Add rate bar
        addRenderableWidget(new GuiHorizontalRateBar(this, () -> (double) multiblock.inputTank.getNeeded() / multiblock.inputTank.getCapacity(), 78, 63))
                .warning(WarningType.INPUT_DOESNT_PRODUCE_OUTPUT, getWarningCheck(RecipeError.INPUT_DOESNT_PRODUCE_OUTPUT));

        // Add input tanks
        addRenderableWidget(new GuiGasGauge(() -> multiblock.plasmaInputTank,
                () -> multiblock.getGasTanks(null),
                GaugeType.STANDARD, this, 6, 13));
        addRenderableWidget(new GuiFluidGauge(() -> multiblock.inputTank,
                () -> multiblock.getFluidTanks(null),
                GaugeType.STANDARD, this, 42, 13))
                .warning(WarningType.NO_MATCHING_RECIPE, getWarningCheck(RecipeError.NOT_ENOUGH_INPUT));

        // Add output tanks
        addRenderableWidget(new GuiFluidGauge(() -> tile.getMultiblock().outputTank,
                () -> multiblock.getFluidTanks(null),
                GaugeType.STANDARD, this, 178, 13))
                .warning(WarningType.NO_SPACE_IN_OUTPUT, getWarningCheck(RecipeError.NOT_ENOUGH_OUTPUT_SPACE));
        addRenderableWidget(new GuiGasGauge(() -> multiblock.plasmaOutputTank,
                () -> multiblock.getGasTanks(null),
                GaugeType.STANDARD, this, 214, 13));
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
