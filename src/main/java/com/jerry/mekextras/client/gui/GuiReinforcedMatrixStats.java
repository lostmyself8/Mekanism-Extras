package com.jerry.mekextras.client.gui;

import com.jerry.mekextras.common.content.matrix.ReinforcedMatrixMultiblockData;
import com.jerry.mekextras.common.tile.multiblock.TileEntityReinforcedInductionCasing;
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
        addRenderableWidget(new GuiReinforcedMatrixTab(this, tile, GuiReinforcedMatrixTab.ReinforcedMatrixTab.MAIN));
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
                ReinforcedMatrixMultiblockData multiBlock = tile.getMultiblock();
                return multiBlock.isFormed() ? multiBlock.getLastInput().divideToLevel(multiBlock.getTransferCap()) : 0;
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
                return multiBlock.getLastOutput().divideToLevel(multiBlock.getTransferCap());
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
        drawString(guiGraphics, MekanismLang.INPUT.translate(), 53, 26, subheadingTextColor());
        drawString(guiGraphics, EnergyDisplay.of(multiBlock.getLastInput(), multiBlock.getTransferCap()).getTextComponent(), 59, 35, titleTextColor());
        drawString(guiGraphics, MekanismLang.OUTPUT.translate(), 53, 46, subheadingTextColor());
        drawString(guiGraphics, EnergyDisplay.of(multiBlock.getLastOutput(), multiBlock.getTransferCap()).getTextComponent(), 59, 55, titleTextColor());
        drawString(guiGraphics, MekanismLang.MATRIX_DIMENSIONS.translate(), 8, 82, subheadingTextColor());
        if (multiBlock.isFormed()) {
            drawString(guiGraphics, MekanismLang.MATRIX_DIMENSION_REPRESENTATION.translate(multiBlock.width(), multiBlock.height(), multiBlock.length()), 14, 91, titleTextColor());
        }
        drawString(guiGraphics, MekanismLang.MATRIX_CONSTITUENTS.translate(), 8, 102, subheadingTextColor());
        drawString(guiGraphics, MekanismLang.MATRIX_CELLS.translate(multiBlock.getCellCount()), 14, 111, titleTextColor());
        drawString(guiGraphics, MekanismLang.MATRIX_PROVIDERS.translate(multiBlock.getProviderCount()), 14, 120, titleTextColor());
        super.drawForegroundText(guiGraphics, mouseX, mouseY);
    }
}
