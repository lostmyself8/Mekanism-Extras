package com.jerry.mekanism_extras.client.gui;

import com.jerry.mekanism_extras.common.content.matrix.ExtraMatrixMultiblockData;
import com.jerry.mekanism_extras.common.tile.multiblock.TileEntityReinforcedInductionCasing;
import mekanism.api.math.FloatingLong;
import mekanism.client.gui.GuiMekanismTile;
import mekanism.client.gui.element.bar.GuiBar;
import mekanism.client.gui.element.bar.GuiVerticalRateBar;
import mekanism.client.gui.element.gauge.GaugeType;
import mekanism.client.gui.element.gauge.GuiEnergyGauge;
import mekanism.client.gui.element.tab.GuiEnergyTab;
import mekanism.common.MekanismLang;
import mekanism.common.inventory.container.tile.EmptyTileContainer;
import mekanism.common.util.text.EnergyDisplay;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GuiReinforcedMatrixStats extends GuiMekanismTile<TileEntityReinforcedInductionCasing, EmptyTileContainer<TileEntityReinforcedInductionCasing>> {

    public GuiReinforcedMatrixStats(EmptyTileContainer<TileEntityReinforcedInductionCasing> container, Inventory inv, Component title) {
        super(container, inv, title);
    }

    @Override
    protected void addGuiElements() {
        super.addGuiElements();
        addRenderableWidget(new GuiReinforcedMatrixTab(this, tile, GuiReinforcedMatrixTab.MatrixTab.MAIN));
        addRenderableWidget(new GuiEnergyGauge(new GuiEnergyGauge.IEnergyInfoHandler() {
            @Override
            public FloatingLong getEnergy() {
                return tile.getMultiblock().getEnergy();
            }

            @Override
            public FloatingLong getMaxEnergy() {
                return tile.getMultiblock().getStorageCap();
            }
        }, GaugeType.STANDARD, this, 6, 13));
        addRenderableWidget(new GuiVerticalRateBar(this, new GuiBar.IBarInfoHandler() {
            @Override
            public Component getTooltip() {
                return MekanismLang.MATRIX_RECEIVING_RATE.translate(EnergyDisplay.of(tile.getMultiblock().getLastInput()));
            }

            @Override
            public double getLevel() {
                ExtraMatrixMultiblockData multiblock = tile.getMultiblock();
                return multiblock.isFormed() ? multiblock.getLastInput().divideToLevel(multiblock.getTransferCap()) : 0;
            }
        }, 30, 13));
        addRenderableWidget(new GuiVerticalRateBar(this, new GuiBar.IBarInfoHandler() {
            @Override
            public Component getTooltip() {
                return MekanismLang.MATRIX_OUTPUTTING_RATE.translate(EnergyDisplay.of(tile.getMultiblock().getLastOutput()));
            }

            @Override
            public double getLevel() {
                ExtraMatrixMultiblockData multiblock = tile.getMultiblock();
                if (!multiblock.isFormed()) {
                    return 0;
                }
                return multiblock.getLastOutput().divideToLevel(multiblock.getTransferCap());
            }
        }, 38, 13));
        addRenderableWidget(new GuiEnergyTab(this, () -> {
            ExtraMatrixMultiblockData multiblock = tile.getMultiblock();
            return List.of(MekanismLang.STORING.translate(EnergyDisplay.of(multiblock.getEnergy(), multiblock.getStorageCap())),
                    MekanismLang.MATRIX_INPUT_RATE.translate(EnergyDisplay.of(multiblock.getLastInput())),
                    MekanismLang.MATRIX_OUTPUT_RATE.translate(EnergyDisplay.of(multiblock.getLastOutput())));
        }));
    }

    @Override
    protected void drawForegroundText(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY) {
        renderTitleText(guiGraphics);
        ExtraMatrixMultiblockData multiblock = tile.getMultiblock();
        drawString(guiGraphics, MekanismLang.INPUT.translate(), 53, 26, subheadingTextColor());
        drawString(guiGraphics, EnergyDisplay.of(multiblock.getLastInput(), multiblock.getTransferCap()).getTextComponent(), 59, 35, titleTextColor());
        drawString(guiGraphics, MekanismLang.OUTPUT.translate(), 53, 46, subheadingTextColor());
        drawString(guiGraphics, EnergyDisplay.of(multiblock.getLastOutput(), multiblock.getTransferCap()).getTextComponent(), 59, 55, titleTextColor());
        drawString(guiGraphics, MekanismLang.MATRIX_DIMENSIONS.translate(), 8, 82, subheadingTextColor());
        if (multiblock.isFormed()) {
            drawString(guiGraphics, MekanismLang.MATRIX_DIMENSION_REPRESENTATION.translate(multiblock.width(), multiblock.height(), multiblock.length()), 14, 91, titleTextColor());
        }
        drawString(guiGraphics, MekanismLang.MATRIX_CONSTITUENTS.translate(), 8, 102, subheadingTextColor());
        drawString(guiGraphics, MekanismLang.MATRIX_CELLS.translate(multiblock.getCellCount()), 14, 111, titleTextColor());
        drawString(guiGraphics, MekanismLang.MATRIX_PROVIDERS.translate(multiblock.getProviderCount()), 14, 120, titleTextColor());
        super.drawForegroundText(guiGraphics, mouseX, mouseY);
    }
}
