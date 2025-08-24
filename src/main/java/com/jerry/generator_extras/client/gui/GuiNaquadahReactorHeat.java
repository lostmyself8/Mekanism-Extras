package com.jerry.generator_extras.client.gui;

import com.jerry.generator_extras.client.gui.element.GuiNaquadahReactorTab;
import com.jerry.generator_extras.common.content.reactor.NaquadahReactorMultiblockData;
import com.jerry.generator_extras.common.tile.reactor.TileEntityNaquadahReactorController;
import mekanism.client.gui.element.gauge.*;
import mekanism.client.gui.element.progress.GuiProgress;
import mekanism.client.gui.element.progress.ProgressType;
import mekanism.client.render.MekanismRenderer;
import mekanism.common.inventory.container.tile.EmptyTileContainer;
import mekanism.common.util.MekanismUtils;
import mekanism.common.util.UnitDisplayUtils;
import mekanism.generators.common.GeneratorsLang;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.NotNull;

public class GuiNaquadahReactorHeat extends GuiNaquadahReactorInfo{
    private static final double MAX_LEVEL = 500_000_000;
    public GuiNaquadahReactorHeat(EmptyTileContainer<TileEntityNaquadahReactorController> container, Inventory inv, Component title) {
        super(container, inv, title);
    }

    @Override
    protected void addGuiElements() {
        super.addGuiElements();
        addRenderableWidget(new GuiNumberGauge(new GuiNumberGauge.INumberInfoHandler() {
            @Override
            public TextureAtlasSprite getIcon() {
                return MekanismRenderer.getBaseFluidTexture(Fluids.LAVA, MekanismRenderer.FluidTextureType.STILL);
            }

            @Override
            public double getLevel() {
                return tile.getMultiblock().getLastPlasmaTemp();
            }

            @Override
            public double getScaledLevel() {
                return Math.min(1, getLevel() / MAX_LEVEL);
            }

            @Override
            public Component getText() {
                return GeneratorsLang.REACTOR_PLASMA.translate(MekanismUtils.getTemperatureDisplay(getLevel(), UnitDisplayUtils.TemperatureUnit.KELVIN, true));
            }
        }, GaugeType.STANDARD, this, 7, 50));
        addRenderableWidget(new GuiProgress(() -> {
            NaquadahReactorMultiblockData multiblock = tile.getMultiblock();
            return multiblock.getLastPlasmaTemp() > multiblock.getLastCaseTemp();
        }, ProgressType.SMALL_RIGHT, this, 29, 76));
        addRenderableWidget(new GuiNumberGauge(new GuiNumberGauge.INumberInfoHandler() {
            @Override
            public TextureAtlasSprite getIcon() {
                return MekanismRenderer.getBaseFluidTexture(Fluids.LAVA, MekanismRenderer.FluidTextureType.STILL);
            }

            @Override
            public double getLevel() {
                return tile.getMultiblock().getLastCaseTemp();
            }

            @Override
            public double getScaledLevel() {
                return Math.min(1, getLevel() / MAX_LEVEL);
            }

            @Override
            public Component getText() {
                return GeneratorsLang.REACTOR_CASE.translate(MekanismUtils.getTemperatureDisplay(getLevel(), UnitDisplayUtils.TemperatureUnit.KELVIN, true));
            }
        }, GaugeType.STANDARD, this, 61, 50));
        addRenderableWidget(new GuiProgress(() -> tile.getMultiblock().getCaseTemp() > 0, ProgressType.SMALL_RIGHT, this, 83, 61));
        addRenderableWidget(new GuiProgress(() -> {
            NaquadahReactorMultiblockData multiblock = tile.getMultiblock();
            return multiblock.getCaseTemp() > 0 && !multiblock.waterTank.isEmpty() && multiblock.steamTank.getStored() < multiblock.steamTank.getCapacity();
        }, ProgressType.SMALL_RIGHT, this, 83, 91));
        addRenderableWidget(new GuiFluidGauge(() -> tile.getMultiblock().waterTank, () -> tile.getFluidTanks(null), GaugeType.SMALL, this, 115, 84));
        addRenderableWidget(new GuiGasGauge(() -> tile.getMultiblock().steamTank, () -> tile.getGasTanks(null), GaugeType.SMALL, this, 151, 84));
        addRenderableWidget(new GuiEnergyGauge(tile.getMultiblock().energyContainer, GaugeType.SMALL, this, 115, 46));
        addRenderableWidget(new GuiNaquadahReactorTab(this, tile, GuiNaquadahReactorTab.NaquadahReactorTab.FUEL));
        addRenderableWidget(new GuiNaquadahReactorTab(this, tile, GuiNaquadahReactorTab.NaquadahReactorTab.STAT));
    }

    @Override
    protected void drawForegroundText(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY) {
        renderTitleText(guiGraphics);
        super.drawForegroundText(guiGraphics, mouseX, mouseY);
    }
}
