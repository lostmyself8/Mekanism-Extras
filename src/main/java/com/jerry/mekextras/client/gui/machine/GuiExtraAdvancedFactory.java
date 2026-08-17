package com.jerry.mekextras.client.gui.machine;

import com.jerry.mekextras.client.gui.element.tab.ExtraAdvancedFactoryGuiSortingTab;
import com.jerry.mekextras.common.integration.mekaf.tile.factory.*;
import com.jerry.mekextras.common.integration.mekaf.tile.factory.base.TileEntityExtraAdvancedFactoryBase;
import com.jerry.mekextras.common.integration.mekaf.tile.factory.base.TileEntityExtraChemicalToChemicalFactory;
import com.jerry.mekextras.common.integration.mekaf.tile.factory.base.TileEntityExtraChemicalToItemFactory;
import com.jerry.mekextras.common.integration.mekaf.tile.factory.base.TileEntityExtraItemToChemicalFactory;

import mekanism.api.recipes.cache.CachedRecipe.OperationTracker.RecipeError;
import mekanism.client.SpecialColors;
import mekanism.client.gui.GuiConfigurableTile;
import mekanism.client.gui.element.GuiDownArrow;
import mekanism.client.gui.element.GuiDumpButton;
import mekanism.client.gui.element.GuiSideHolder;
import mekanism.client.gui.element.bar.GuiChemicalBar;
import mekanism.client.gui.element.bar.GuiFluidBar;
import mekanism.client.gui.element.bar.GuiVerticalPowerBar;
import mekanism.client.gui.element.gauge.GaugeType;
import mekanism.client.gui.element.gauge.GuiChemicalGauge;
import mekanism.client.gui.element.gauge.GuiFluidGauge;
import mekanism.client.gui.element.progress.GuiProgress;
import mekanism.client.gui.element.progress.ProgressType;
import mekanism.client.gui.element.tab.GuiEnergyTab;
import mekanism.common.inventory.container.tile.MekanismTileContainer;
import mekanism.common.inventory.warning.WarningTracker.WarningType;
import mekanism.common.tile.interfaces.IHasDumpButton;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

import com.jerry.mekaf.common.tile.factory.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GuiExtraAdvancedFactory extends GuiConfigurableTile<TileEntityExtraAdvancedFactoryBase<?, ?>, MekanismTileContainer<TileEntityExtraAdvancedFactoryBase<?, ?>>> {

    @Nullable
    private GuiDumpButton<?> dumpButton;
    private final int tankCount;

    public GuiExtraAdvancedFactory(MekanismTileContainer<TileEntityExtraAdvancedFactoryBase<?, ?>> container, Inventory inv, Component title) {
        super(container, inv, title);
        tankCount = tile.getTankCount();
        // 根据储罐数量决定gui布局
        imageHeight += 13 * tankCount;
        inventoryLabelY = 75 + 13 * tankCount;
        if (tile.hasExtraResourceBar()) {
            int num = tile.getBarCount() - 1;
            imageHeight += 11 + 8 * num;
            // 第一个额外资源槽加10像素，后面的加8像素
            inventoryLabelY += 10 + 8 * num;
        }

        int index = tile.tier.ordinal();
        imageWidth += (36 * (index + 2)) + (2 * index);
        inventoryLabelX = (22 * (index + 2)) - (3 * index);

        titleLabelY = 4;
        dynamicSlots = true;
    }

    @Override
    protected void addGuiElements() {
        if (tile instanceof TileEntityExtraWashingFactory) {
            addRenderableWidget(GuiSideHolder.create(this, imageWidth, 66, 57, false, true, SpecialColors.TAB_CHEMICAL_WASHER));
        }
        super.addGuiElements();
        if (tile instanceof TileEntityExtraWashingFactory) {
            addRenderableWidget(new GuiDownArrow(this, imageWidth + 8, 90));
        }
        addRenderableWidget(new ExtraAdvancedFactoryGuiSortingTab(this, tile));
        addRenderableWidget(new GuiVerticalPowerBar(this, tile.getEnergyContainer(), imageWidth - 12, 16, 13 * tankCount + 52))
                .warning(WarningType.NOT_ENOUGH_ENERGY, tile.getWarningCheck(RecipeError.NOT_ENOUGH_ENERGY, 0));
        addRenderableWidget(new GuiEnergyTab(this, tile.getEnergyContainer(), tile::getLastUsage));

        if (tile.hasExtraResourceBar()) {
            if (tile instanceof TileEntityExtraWashingFactory factory) {
                addRenderableWidget(new GuiFluidBar(this, GuiFluidBar.getProvider(factory.getFluidTankBar(), tile.getFluidTanks(null)), 7, 102,
                        getBarWidth(), 4, true))
                        .warning(WarningType.NO_MATCHING_RECIPE, tile.getWarningCheck(RecipeError.NOT_ENOUGH_SECONDARY_INPUT, 0));
                dumpButton = addRenderableWidget(new GuiDumpButton<>(this, (TileEntityExtraAdvancedFactoryBase<?, ?> & IHasDumpButton) tile, getButtonX(), 102));
            } else if (tile instanceof TileEntityExtraPRCFactory factory) {
                // 出输出化学储罐
                addRenderableWidget(new GuiChemicalGauge(() -> factory.outputChemicalTank, () -> tile.getChemicalTanks(null), GaugeType.SMALL, this, 6, 44))
                        .warning(WarningType.NO_SPACE_IN_OUTPUT, factory.getWarningCheck(TileEntityPressurizedReactingFactory.NOT_ENOUGH_SPACE_GAS_OUTPUT_ERROR, 0));
                // 化学储罐条
                addRenderableWidget(new GuiChemicalBar(this, GuiChemicalBar.getProvider(factory.getChemicalTankBar(), tile.getChemicalTanks(null)), 7, 76,
                        getBarWidth(), 4, true))
                        .warning(WarningType.NO_MATCHING_RECIPE, tile.getWarningCheck(RecipeError.NOT_ENOUGH_SECONDARY_INPUT, 0));
                // 流体储罐条
                addRenderableWidget(new GuiFluidBar(this, GuiFluidBar.getProvider(factory.getFluidTankBar(), tile.getFluidTanks(null)), 7, 84,
                        getBarWidth(), 4, true))
                        .warning(WarningType.NO_MATCHING_RECIPE, tile.getWarningCheck(RecipeError.NOT_ENOUGH_SECONDARY_INPUT, 0));
                // dump按钮
                dumpButton = addRenderableWidget(new GuiDumpButton<>(this, (TileEntityExtraAdvancedFactoryBase<?, ?> & IHasDumpButton) tile, getButtonX(), 76));
            } else {
                addRenderableWidget(new GuiChemicalBar(this, GuiChemicalBar.getProvider(tile.getChemicalTankBar(), tile.getChemicalTanks(null)),
                        7, 13 * tankCount + 76, getBarWidth(), 4, true))
                        .warning(WarningType.NO_MATCHING_RECIPE, tile.getWarningCheck(RecipeError.NOT_ENOUGH_SECONDARY_INPUT, 0));
                dumpButton = addRenderableWidget(new GuiDumpButton<>(this, (TileEntityExtraAdvancedFactoryBase<?, ?> & IHasDumpButton) tile, getButtonX(), 13 * tankCount + 76));
            }
        }

        if (tile instanceof TileEntityExtraLiquifyingFactory factory) {
            addRenderableWidget(new GuiFluidGauge(() -> factory.fluidTank, () -> factory.getFluidTanks(null), GaugeType.SMALL, this, 6, 44))
                    .warning(WarningType.NO_MATCHING_RECIPE, factory.getWarningCheck(RecipeError.NOT_ENOUGH_OUTPUT_SPACE, 0));
        }

        // 物品到气体的工厂只需要一排储罐，物品槽位在TileEntity中被添加
        if (tile instanceof TileEntityExtraItemToChemicalFactory<?, ?> factory) {
            for (int i = 0; i < tile.tier.processes; i++) {
                int index = i;
                addRenderableWidget(new GuiChemicalGauge(() -> factory.outputChemicalTanks.get(index), () -> tile.getChemicalTanks(null), GaugeType.SMALL, this, factory.getXPos(index) - 1, 57))
                        .warning(WarningType.NO_SPACE_IN_OUTPUT, factory.getWarningCheck(RecipeError.NOT_ENOUGH_OUTPUT_SPACE, index));
            }
        }

        // 气体到物品的工厂只需要一排储罐，但储罐在上面
        if (tile instanceof TileEntityExtraChemicalToItemFactory<?, ?> factory) {
            for (int i = 0; i < tile.tier.processes; i++) {
                int index = i;
                addRenderableWidget(new GuiChemicalGauge(() -> factory.inputChemicalTanks.get(index), () -> tile.getChemicalTanks(null), GaugeType.SMALL, this, factory.getXPos(index) - 1, 13))
                        .warning(WarningType.NO_MATCHING_RECIPE, factory.getWarningCheck(RecipeError.NOT_ENOUGH_INPUT, index));
            }
        }

        // 气体生产气体的工厂需要两排储罐
        if (tile instanceof TileEntityExtraChemicalToChemicalFactory<?, ?> factory) {
            for (int i = 0; i < tile.tier.processes; i++) {
                int index = i;
                addRenderableWidget(new GuiChemicalGauge(() -> factory.inputChemicalTanks.get(index), () -> tile.getChemicalTanks(null), GaugeType.SMALL, this, factory.getXPos(index) - 1, 13))
                        .warning(WarningType.NO_MATCHING_RECIPE, factory.getWarningCheck(RecipeError.NOT_ENOUGH_LEFT_INPUT, index));
                addRenderableWidget(new GuiChemicalGauge(() -> factory.outputChemicalTanks.get(index), () -> tile.getChemicalTanks(null), GaugeType.SMALL, this, factory.getXPos(index) - 1, 70))
                        .warning(WarningType.NO_SPACE_IN_OUTPUT, factory.getWarningCheck(RecipeError.NOT_ENOUGH_OUTPUT_SPACE, index));
            }
        }

        // 所有工厂都有的进度条
        for (int i = 0; i < tile.tier.processes; i++) {
            int cacheIndex = i;
            addRenderableWidget(new GuiProgress(() -> tile.getScaledProgress(1, cacheIndex), ProgressType.DOWN, this, 4 + tile.getXPos(i), 13 * tile.UpperTankCount() + 33))
                    .recipeViewerCategory(tile)
                    .warning(WarningType.INPUT_DOESNT_PRODUCE_OUTPUT, tile.getWarningCheck(RecipeError.INPUT_DOESNT_PRODUCE_OUTPUT, cacheIndex));
        }
    }

    private int getBarWidth() {
        int index = tile.tier.ordinal();
        return 210 + 38 * index;
    }

    private int getButtonX() {
        int index = tile.tier.ordinal();
        return 220 + 38 * index;
    }

    @Override
    protected void drawForegroundText(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY) {
        renderTitleText(guiGraphics);
        renderInventoryText(guiGraphics, dumpButton == null ? getXSize() : dumpButton.getRelativeX());
        super.drawForegroundText(guiGraphics, mouseX, mouseY);
    }
}
