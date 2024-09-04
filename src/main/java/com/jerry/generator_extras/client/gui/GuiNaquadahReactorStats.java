package com.jerry.generator_extras.client.gui;

import com.jerry.generator_extras.client.gui.element.GuiNaquadahReactorTab;
import com.jerry.generator_extras.common.content.reactor.NaquadahReactorMultiblockData;
import com.jerry.generator_extras.common.tile.reactor.TileEntityNaquadahReactorController;
import mekanism.api.text.EnumColor;
import mekanism.common.inventory.container.tile.EmptyTileContainer;
import mekanism.common.util.MekanismUtils;
import mekanism.common.util.UnitDisplayUtils;
import mekanism.common.util.text.EnergyDisplay;
import mekanism.common.util.text.TextUtils;
import mekanism.generators.common.GeneratorsLang;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

public class GuiNaquadahReactorStats extends GuiNaquadahReactorInfo{
    public GuiNaquadahReactorStats(EmptyTileContainer<TileEntityNaquadahReactorController> container, Inventory inv, Component title) {
        super(container, inv, title);
    }

    @Override
    protected void addGuiElements() {
        super.addGuiElements();
        addRenderableWidget(new GuiNaquadahReactorTab(this, tile, GuiNaquadahReactorTab.NaquadahReactorTab.HEAT));
        addRenderableWidget(new GuiNaquadahReactorTab(this, tile, GuiNaquadahReactorTab.NaquadahReactorTab.FUEL));
    }

    @Override
    protected void drawForegroundText(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY) {
        renderTitleText(guiGraphics);
        NaquadahReactorMultiblockData multiblock = tile.getMultiblock();
        if (multiblock.isFormed()) {
            //气冷
            drawString(guiGraphics, GeneratorsLang.REACTOR_PASSIVE.translateColored(EnumColor.DARK_GREEN), 6, 26, titleTextColor());
            //最小注入速率
            drawTextScaledBound(guiGraphics, GeneratorsLang.REACTOR_MIN_INJECTION.translate(multiblock.getMinInjectionRate(false)), 16, 36, titleTextColor(), 156);
            //引燃温度
            drawTextScaledBound(guiGraphics, GeneratorsLang.REACTOR_IGNITION.translate(MekanismUtils.getTemperatureDisplay(multiblock.getIgnitionTemperature(false),
                    UnitDisplayUtils.TemperatureUnit.KELVIN, true)), 16, 46, titleTextColor(), 156);
            //等离子体最高温度
            drawTextScaledBound(guiGraphics, GeneratorsLang.REACTOR_MAX_PLASMA.translate(MekanismUtils.getTemperatureDisplay(multiblock.getMaxPlasmaTemperature(false),
                    UnitDisplayUtils.TemperatureUnit.KELVIN, true)), 16, 56, titleTextColor(), 156);
            //外壳最高温度
            drawTextScaledBound(guiGraphics, GeneratorsLang.REACTOR_MAX_CASING.translate(MekanismUtils.getTemperatureDisplay(multiblock.getMaxCasingTemperature(false),
                    UnitDisplayUtils.TemperatureUnit.KELVIN, true)), 16, 66, titleTextColor(), 156);
            //被动产能
            drawTextScaledBound(guiGraphics, GeneratorsLang.REACTOR_PASSIVE_RATE.translate(EnergyDisplay.of(multiblock.getPassiveGeneration(false, false))),
                    16, 76, titleTextColor(), 156);

            //水冷
            drawString(guiGraphics, GeneratorsLang.REACTOR_ACTIVE.translateColored(EnumColor.DARK_BLUE), 6, 92, titleTextColor());
            //最小注入速率
            drawTextScaledBound(guiGraphics, GeneratorsLang.REACTOR_MIN_INJECTION.translate(multiblock.getMinInjectionRate(true)), 16, 102, titleTextColor(), 156);
            //引燃温度
            drawTextScaledBound(guiGraphics, GeneratorsLang.REACTOR_IGNITION.translate(MekanismUtils.getTemperatureDisplay(multiblock.getIgnitionTemperature(true),
                    UnitDisplayUtils.TemperatureUnit.KELVIN, true)), 16, 112, titleTextColor(), 156);
            //等离子体最高温度
            drawTextScaledBound(guiGraphics, GeneratorsLang.REACTOR_MAX_PLASMA.translate(MekanismUtils.getTemperatureDisplay(multiblock.getMaxPlasmaTemperature(true),
                    UnitDisplayUtils.TemperatureUnit.KELVIN, true)), 16, 122, titleTextColor(), 156);
            //外壳最高温度
            drawTextScaledBound(guiGraphics, GeneratorsLang.REACTOR_MAX_CASING.translate(MekanismUtils.getTemperatureDisplay(multiblock.getMaxCasingTemperature(true),
                    UnitDisplayUtils.TemperatureUnit.KELVIN, true)), 16, 132, titleTextColor(), 156);
            //被动产能
            drawTextScaledBound(guiGraphics, GeneratorsLang.REACTOR_PASSIVE_RATE.translate(EnergyDisplay.of(multiblock.getPassiveGeneration(true, false))),
                    16, 142, titleTextColor(), 156);
            //蒸汽产出
            drawTextScaledBound(guiGraphics, GeneratorsLang.REACTOR_STEAM_PRODUCTION.translate(TextUtils.format(multiblock.getSteamPerTick(false))),
                    16, 152, titleTextColor(), 156);
        }
        super.drawForegroundText(guiGraphics, mouseX, mouseY);
    }
}
