package com.jerry.mekextras.client.gui;

import com.jerry.mekextras.common.content.matrix.ReinforcedMatrixMultiblockData;
import com.jerry.mekextras.common.tile.multiblock.TileEntityReinforcedInductionCasing;
import mekanism.api.math.MathUtils;
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
        addRenderableWidget(new GuiReinforcedMatrixTab(this, tile, GuiReinforcedMatrixTab.ReinforcedMatrixTab.MAIN));
        addRenderableWidget(new GuiEnergyGauge(new GuiEnergyGauge.IEnergyInfoHandler() {
            @Override
            public long getEnergy() {
                return tile.getMultiblock().getEnergy();
            }

            @Override
            public long getMaxEnergy() {
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
                ReinforcedMatrixMultiblockData multiBlock = tile.getMultiblock();
                return multiBlock.isFormed() ? MathUtils.divideToLevel(multiBlock.getLastInput(), multiBlock.getTransferCap()) : 0;
            }
        }, 30, 13));
        addRenderableWidget(new GuiVerticalRateBar(this, new GuiBar.IBarInfoHandler() {
            @Override
            public Component getTooltip() {
                return MekanismLang.MATRIX_OUTPUTTING_RATE.translate(EnergyDisplay.of(tile.getMultiblock().getLastOutput()));
            }

            @Override
            public double getLevel() {
                ReinforcedMatrixMultiblockData multiBlock = tile.getMultiblock();
                if (!multiBlock.isFormed()) {
                    return 0;
                }
                return MathUtils.divideToLevel(multiBlock.getLastOutput(), multiBlock.getTransferCap());
            }
        }, 38, 13));
        addRenderableWidget(new GuiEnergyTab(this, () -> {
            ReinforcedMatrixMultiblockData multiBlock = tile.getMultiblock();
            return List.of(MekanismLang.STORING.translate(EnergyDisplay.of(multiBlock.getEnergy(), multiBlock.getStorageCap())),
                    MekanismLang.MATRIX_INPUT_RATE.translate(EnergyDisplay.of(multiBlock.getLastInput())),
                    MekanismLang.MATRIX_OUTPUT_RATE.translate(EnergyDisplay.of(multiBlock.getLastOutput())));
        }));
    }

    @Override
    protected void drawForegroundText(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY) {
        renderTitleText(guiGraphics);
        ReinforcedMatrixMultiblockData multiBlock = tile.getMultiblock();
        drawScrollingString(guiGraphics, MekanismLang.INPUT.translate(), 45, 26, TextAlignment.LEFT, subheadingTextColor(), getXSize() - 54, 8, false);
        drawScrollingString(guiGraphics, EnergyDisplay.of(multiBlock.getLastInput(), multiBlock.getTransferCap()).getTextComponent(), 51, 35, TextAlignment.LEFT, titleTextColor(), getXSize() - 60, 8, false);
        drawScrollingString(guiGraphics, MekanismLang.OUTPUT.translate(), 45, 46, TextAlignment.LEFT, subheadingTextColor(), getXSize() - 54, 8, false);
        drawScrollingString(guiGraphics, EnergyDisplay.of(multiBlock.getLastOutput(), multiBlock.getTransferCap()).getTextComponent(), 51, 55, TextAlignment.LEFT, titleTextColor(), getXSize() - 60, 8, false);
        drawScrollingString(guiGraphics, MekanismLang.MATRIX_DIMENSIONS.translate(), 0, 82, TextAlignment.LEFT, subheadingTextColor(), 8, false);
        if (multiBlock.isFormed()) {
            drawScrollingString(guiGraphics, MekanismLang.MATRIX_DIMENSION_REPRESENTATION.translate(multiBlock.width(), multiBlock.height(), multiBlock.length()), 6, 91, TextAlignment.LEFT, titleTextColor(), getXSize() - 6, 8, false);
        }
        drawScrollingString(guiGraphics, MekanismLang.MATRIX_CONSTITUENTS.translate(), 0, 102, TextAlignment.LEFT, subheadingTextColor(), 8, false);
        drawScrollingString(guiGraphics, MekanismLang.MATRIX_CELLS.translate(multiBlock.getCellCount()), 6, 111, TextAlignment.LEFT, titleTextColor(), getXSize() - 6, 8, false);
        drawScrollingString(guiGraphics, MekanismLang.MATRIX_PROVIDERS.translate(multiBlock.getProviderCount()), 6, 120, TextAlignment.LEFT, titleTextColor(), getXSize() - 6, 8, false);
        super.drawForegroundText(guiGraphics, mouseX, mouseY);
    }
}
