package com.jerry.mekanism_extras.client.gui.machine;

import com.jerry.mekanism_extras.client.gui.element.tab.ExtraGuiSortingTab;
import com.jerry.mekanism_extras.common.tile.factory.TileEntityExtraFactory;
import com.jerry.mekanism_extras.common.tile.factory.TileEntityItemStackGasToItemStackExtraFactory;
import com.jerry.mekanism_extras.common.tile.factory.TileEntityMetallurgicInfuserExtraFactory;
import com.jerry.mekanism_extras.common.tile.factory.TileEntitySawingExtraFactory;
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
import mekanism.common.inventory.warning.ISupportsWarning;
import mekanism.common.inventory.warning.WarningTracker.WarningType;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

public class GuiAdvancedFactory extends GuiConfigurableTile<TileEntityExtraFactory<?>, MekanismTileContainer<TileEntityExtraFactory<?>>> {

    public GuiAdvancedFactory(MekanismTileContainer<TileEntityExtraFactory<?>> container, Inventory inv, Component title) {
        super(container, inv, title);
        if (tile.hasSecondaryResourceBar()) {
            imageHeight += 11;
            inventoryLabelY = 85;
        } else if (tile instanceof TileEntitySawingExtraFactory) {
            imageHeight += 21;
            inventoryLabelY = 95;
        } else {
            inventoryLabelY = 75;
        }
        //这两个公式似乎并非完美，在index过大时可能会导致有细微的便宜，但未得到验证
        int index = tile.tier.ordinal();
        imageWidth += (36 * (index + 2)) + (2 * index);
        inventoryLabelX = (22 * (index + 2)) - (3 * index);
        titleLabelY = 4;
        dynamicSlots = true;
    }

    @Override
    protected void addGuiElements() {
        super.addGuiElements();
        addRenderableWidget(new ExtraGuiSortingTab(this, tile));
        addRenderableWidget(new GuiVerticalPowerBar(this, tile.getEnergyContainer(), imageWidth - 12, 16, tile instanceof TileEntitySawingExtraFactory ? 73 : 52))
                .warning(WarningType.NOT_ENOUGH_ENERGY, tile.getWarningCheck(RecipeError.NOT_ENOUGH_ENERGY, 0));
        addRenderableWidget(new GuiEnergyTab(this, tile.getEnergyContainer(), tile::getLastUsage));
        if (tile.hasSecondaryResourceBar()) {
            ISupportsWarning<?> secondaryBar = null;
            int index = tile.tier.ordinal();
            if (tile instanceof TileEntityMetallurgicInfuserExtraFactory factory) {
                secondaryBar = addRenderableWidget(new GuiChemicalBar<>(this, GuiChemicalBar.getProvider(factory.getInfusionTank(), tile.getInfusionTanks(null)),
                        7, 76, 210 + 38 * index, 4, true));
                addRenderableWidget(new GuiDumpButton<>(this, factory, 220 + 38 * index, 76));
            } else if (tile instanceof TileEntityItemStackGasToItemStackExtraFactory factory) {
                secondaryBar = addRenderableWidget(new GuiChemicalBar<>(this, GuiChemicalBar.getProvider(factory.getGasTank(), tile.getGasTanks(null)),
                        7, 76, 210 + 38 * index, 4, true));
                addRenderableWidget(new GuiDumpButton<>(this, factory, 220 + 38 * index, 76));
            }
            if (secondaryBar != null) {
                secondaryBar.warning(WarningType.NO_MATCHING_RECIPE, tile.getWarningCheck(RecipeError.NOT_ENOUGH_SECONDARY_INPUT, 0));
            }
        }

        int baseX = 27;
        int baseXMult = 19;
        for (int i = 0; i < tile.tier.processes; i++) {
            int cacheIndex = i;
            addProgress(new GuiProgress(() -> tile.getScaledProgress(1, cacheIndex), ProgressType.DOWN, this, 4 + baseX + (i * baseXMult), 33))
                    //Only can happen if recipes change because inputs are sanitized in the factory based on the output
                    .warning(WarningType.INPUT_DOESNT_PRODUCE_OUTPUT, tile.getWarningCheck(RecipeError.INPUT_DOESNT_PRODUCE_OUTPUT, cacheIndex));
        }
    }

    private GuiProgress addProgress(GuiProgress progressBar) {
        MekanismJEIRecipeType<?> jeiType = switch (tile.getFactoryType()) {
            case SMELTING -> MekanismJEIRecipeType.SMELTING;
            case ENRICHING -> MekanismJEIRecipeType.ENRICHING;
            case CRUSHING -> MekanismJEIRecipeType.CRUSHING;
            case COMPRESSING -> MekanismJEIRecipeType.COMPRESSING;
            case COMBINING -> MekanismJEIRecipeType.COMBINING;
            case PURIFYING -> MekanismJEIRecipeType.PURIFYING;
            case INJECTING -> MekanismJEIRecipeType.INJECTING;
            case INFUSING -> MekanismJEIRecipeType.METALLURGIC_INFUSING;
            case SAWING -> MekanismJEIRecipeType.SAWING;
        };
        return addRenderableWidget(progressBar.jeiCategories(jeiType));
    }

    @Override
    protected void drawForegroundText(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY) {
        renderTitleText(guiGraphics);
        drawString(guiGraphics, playerInventoryTitle, inventoryLabelX, inventoryLabelY, titleTextColor());
        super.drawForegroundText(guiGraphics, mouseX, mouseY);
    }
}