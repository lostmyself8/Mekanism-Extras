package com.jerry.mekanism_extras.client.gui.machine;

import com.jerry.mekanism_extras.client.gui.element.tab.ExtraMoreMachineGuiSortingTab;
import com.jerry.mekanism_extras.common.integration.mekmm.tile.TileEntityExtraMoreMachineFactory;
import com.jerry.mekanism_extras.common.integration.mekmm.tile.TileEntityExtraPlantingFactory;
import com.jerry.mekanism_extras.common.integration.mekmm.tile.TileEntityExtraReplicatingFactory;

import mekanism.api.recipes.cache.CachedRecipe.OperationTracker.RecipeError;
import mekanism.client.gui.GuiConfigurableTile;
import mekanism.client.gui.element.GuiDumpButton;
import mekanism.client.gui.element.bar.GuiChemicalBar;
import mekanism.client.gui.element.bar.GuiVerticalPowerBar;
import mekanism.client.gui.element.progress.GuiProgress;
import mekanism.client.gui.element.progress.ProgressType;
import mekanism.client.gui.element.tab.GuiEnergyTab;
import mekanism.client.jei.MekanismJEIRecipeType;
import mekanism.common.inventory.container.tile.MekanismTileContainer;
import mekanism.common.inventory.warning.WarningTracker.WarningType;
import mekanism.common.tile.interfaces.IHasDumpButton;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

import com.jerry.mekmm.client.jei.MoreMachineJEIRecipeType;
import org.jetbrains.annotations.NotNull;

public class GuiExtraMoreMachineFactory extends GuiConfigurableTile<TileEntityExtraMoreMachineFactory<?>, MekanismTileContainer<TileEntityExtraMoreMachineFactory<?>>> {

    public GuiExtraMoreMachineFactory(MekanismTileContainer<TileEntityExtraMoreMachineFactory<?>> container, Inventory inv, Component title) {
        super(container, inv, title);
        if (tile.hasSecondaryResourceBar()) {
            imageHeight += 11;
            inventoryLabelY = 85;
            if (tile instanceof TileEntityExtraPlantingFactory) {
                imageHeight += 20;
                inventoryLabelY = 105;
            }
        } else {
            inventoryLabelY = 75;
        }
        // 这两个公式似乎并非完美，在index过大时可能会导致有细微的便宜，但未得到验证
        int index = tile.tier.ordinal();
        imageWidth += (36 * (index + 2)) + (2 * index);
        inventoryLabelX = (22 * (index + 2)) - (3 * index);
        titleLabelY = 4;
        dynamicSlots = true;
    }

    @Override
    protected void addGuiElements() {
        super.addGuiElements();
        addRenderableWidget(new ExtraMoreMachineGuiSortingTab(this, tile));
        addRenderableWidget(new GuiVerticalPowerBar(this, tile.getEnergyContainer(), imageWidth - 12, 16, tile instanceof TileEntityExtraPlantingFactory ? 73 : 52))
                .warning(WarningType.NOT_ENOUGH_ENERGY, tile.getWarningCheck(RecipeError.NOT_ENOUGH_ENERGY, 0));

        addRenderableWidget(new GuiEnergyTab(this, tile.getEnergyContainer(), tile::getLastUsage));
        if (tile.hasSecondaryResourceBar()) {
            if (tile instanceof TileEntityExtraPlantingFactory factory) {
                addRenderableWidget(new GuiChemicalBar<>(this, GuiChemicalBar.getProvider(factory.getGasTank(), tile.getGasTanks(null)), 7, 96,
                        getBarWidth(), 4, true))
                        .warning(WarningType.NO_MATCHING_RECIPE, tile.getWarningCheck(RecipeError.NOT_ENOUGH_SECONDARY_INPUT, 0));
                addRenderableWidget(new GuiDumpButton<>(this, (TileEntityExtraMoreMachineFactory<?> & IHasDumpButton) tile, getButtonX(), 96));
            }
            if (tile instanceof TileEntityExtraReplicatingFactory factory) {
                addRenderableWidget(new GuiChemicalBar<>(this, GuiChemicalBar.getProvider(factory.getGasTank(), tile.getGasTanks(null)), 7, 76,
                        getBarWidth(), 4, true))
                        .warning(WarningType.NO_MATCHING_RECIPE, tile.getWarningCheck(RecipeError.NOT_ENOUGH_SECONDARY_INPUT, 0));
                addRenderableWidget(new GuiDumpButton<>(this, (TileEntityExtraMoreMachineFactory<?> & IHasDumpButton) tile, getButtonX(), 76));
            }
        }

        for (int i = 0; i < tile.tier.processes; i++) {
            int cacheIndex = i;
            addProgress(new GuiProgress(() -> tile.getScaledProgress(1, cacheIndex), ProgressType.DOWN, this, 4 + tile.getXPos(i), 33))
                    // Only can happen if recipes change because inputs are sanitized in the factory based on the output
                    .warning(WarningType.INPUT_DOESNT_PRODUCE_OUTPUT, tile.getWarningCheck(RecipeError.INPUT_DOESNT_PRODUCE_OUTPUT, cacheIndex));
        }
    }

    private GuiProgress addProgress(GuiProgress progressBar) {
        MekanismJEIRecipeType<?> jeiType = switch (tile.getMMFactoryType()) {
            case RECYCLING -> MoreMachineJEIRecipeType.RECYCLING;
            case PLANTING -> MoreMachineJEIRecipeType.PLANTING;
            case CNC_STAMPING -> MoreMachineJEIRecipeType.CNC_STAMPING;
            case CNC_LATHING -> MoreMachineJEIRecipeType.CNC_LATHING;
            case CNC_ROLLING_MILL -> MoreMachineJEIRecipeType.CNC_ROLLING_MILL;
            case REPLICATING -> MoreMachineJEIRecipeType.REPLICATOR;
        };
        return addRenderableWidget(progressBar.jeiCategories(jeiType));
    }

    private int getBarWidth() {
        return 210 + 38 * tile.tier.ordinal();
    }

    private int getButtonX() {
        return 220 + 38 * tile.tier.ordinal();
    }

    @Override
    protected void drawForegroundText(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY) {
        renderTitleText(guiGraphics);
        drawString(guiGraphics, playerInventoryTitle, inventoryLabelX, inventoryLabelY, titleTextColor());
        super.drawForegroundText(guiGraphics, mouseX, mouseY);
    }
}
