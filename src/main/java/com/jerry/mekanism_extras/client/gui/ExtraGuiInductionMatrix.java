package com.jerry.mekanism_extras.client.gui;

import com.jerry.mekanism_extras.common.content.matrix.ExtraMatrixMultiblockData;
import com.jerry.mekanism_extras.common.tile.multiblock.ExtraTileEntityInductionCasing;
import com.mojang.blaze3d.vertex.PoseStack;
import mekanism.api.math.FloatingLong;
import mekanism.client.SpecialColors;
import mekanism.client.gui.GuiMekanismTile;
import mekanism.client.gui.element.GuiElementHolder;
import mekanism.client.gui.element.GuiInnerScreen;
import mekanism.client.gui.element.GuiSideHolder;
import mekanism.client.gui.element.gauge.GaugeType;
import mekanism.client.gui.element.gauge.GuiEnergyGauge;
import mekanism.client.gui.element.slot.GuiSlot;
import mekanism.client.gui.element.slot.SlotType;
import mekanism.client.gui.element.tab.GuiEnergyTab;
import mekanism.common.MekanismLang;
import mekanism.common.inventory.container.tile.MekanismTileContainer;
import mekanism.common.util.text.EnergyDisplay;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ExtraGuiInductionMatrix extends GuiMekanismTile<ExtraTileEntityInductionCasing, MekanismTileContainer<ExtraTileEntityInductionCasing>> {

    public ExtraGuiInductionMatrix(MekanismTileContainer<ExtraTileEntityInductionCasing> container, Inventory inv, Component title) {
        super(container, inv, title);
        inventoryLabelY += 2;
        dynamicSlots = true;
    }

    @Override
    protected void addGuiElements() {
        //Add the side holder before the slots, as it holds a couple of the slots
        addRenderableWidget(GuiSideHolder.create(this, -26, 36, 98, true, true, SpecialColors.TAB_ARMOR_SLOTS));
        addRenderableWidget(new GuiElementHolder(this, 141, 16, 26, 56));
        super.addGuiElements();
        addRenderableWidget(new GuiSlot(SlotType.INNER_HOLDER_SLOT, this, 145, 20));
        addRenderableWidget(new GuiSlot(SlotType.INNER_HOLDER_SLOT, this, 145, 50));
        addRenderableWidget(new GuiInnerScreen(this, 49, 21, 84, 46, () -> {
            ExtraMatrixMultiblockData multiblock = tile.getMultiblock();
            return List.of(MekanismLang.ENERGY.translate(EnergyDisplay.of(multiblock.getEnergy())),
                    MekanismLang.CAPACITY.translate(EnergyDisplay.of(multiblock.getStorageCap())),
                    MekanismLang.MATRIX_INPUT_AMOUNT.translate(MekanismLang.GENERIC_PER_TICK.translate(EnergyDisplay.of(multiblock.getLastInput()))),
                    MekanismLang.MATRIX_OUTPUT_AMOUNT.translate(MekanismLang.GENERIC_PER_TICK.translate(EnergyDisplay.of(multiblock.getLastOutput()))));
        }).spacing(2));
        addRenderableWidget(new ExtraGuiMatrixTab(this, tile, ExtraGuiMatrixTab.MatrixTab.STAT));
        addRenderableWidget(new GuiEnergyGauge(new GuiEnergyGauge.IEnergyInfoHandler() {
            @Override
            public FloatingLong getEnergy() {
                return tile.getMultiblock().getEnergy();
            }

            @Override
            public FloatingLong getMaxEnergy() {
                return tile.getMultiblock().getStorageCap();
            }
        }, GaugeType.MEDIUM, this, 7, 16, 34, 56));
        addRenderableWidget(new GuiEnergyTab(this, () -> {
            ExtraMatrixMultiblockData multiblock = tile.getMultiblock();
            return List.of(MekanismLang.STORING.translate(EnergyDisplay.of(multiblock.getEnergy(), multiblock.getStorageCap())),
                    MekanismLang.MATRIX_INPUT_RATE.translate(EnergyDisplay.of(multiblock.getLastInput())),
                    MekanismLang.MATRIX_OUTPUT_RATE.translate(EnergyDisplay.of(multiblock.getLastOutput())));
        }));
    }

    @Override
    protected void drawForegroundText(@NotNull PoseStack matrix, int mouseX, int mouseY) {
        renderTitleText(matrix);
        drawString(matrix, playerInventoryTitle, inventoryLabelX, inventoryLabelY, titleTextColor());
        super.drawForegroundText(matrix, mouseX, mouseY);
    }
}
