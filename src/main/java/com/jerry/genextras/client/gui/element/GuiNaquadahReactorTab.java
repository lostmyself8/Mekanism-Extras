package com.jerry.genextras.client.gui.element;

import com.jerry.mekextras.common.network.to_server.button.ExtraPacketTileButtonPress;

import com.jerry.genextras.common.tile.naquadah.TileEntityNaquadahReactorController;

import mekanism.api.text.ILangEntry;
import mekanism.client.SpecialColors;
import mekanism.client.gui.IGuiWrapper;
import mekanism.client.gui.element.tab.GuiTabElementType;
import mekanism.client.gui.element.tab.TabType;
import mekanism.client.render.lib.ColorAtlas;
import mekanism.common.network.PacketUtils;
import mekanism.common.util.MekanismUtils;
import mekanism.generators.client.GeneratorsSpecialColors;
import mekanism.generators.common.GeneratorsLang;
import mekanism.generators.common.MekanismGenerators;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class GuiNaquadahReactorTab extends GuiTabElementType<TileEntityNaquadahReactorController, GuiNaquadahReactorTab.NaquadahReactorTab> {

    public GuiNaquadahReactorTab(IGuiWrapper gui, TileEntityNaquadahReactorController tileEntityNaquadahReactorController, NaquadahReactorTab type) {
        super(gui, tileEntityNaquadahReactorController, type);
    }

    public enum NaquadahReactorTab implements TabType<TileEntityNaquadahReactorController> {

        HEAT(MekanismUtils.getResource(MekanismUtils.ResourceType.GUI, "heat.png"), GeneratorsLang.HEAT_TAB, 6, ExtraPacketTileButtonPress.ClickedTileButton.TAB_HEAT, GeneratorsSpecialColors.TAB_MULTIBLOCK_HEAT),
        FUEL(MekanismGenerators.rl(MekanismUtils.ResourceType.GUI.getPrefix() + "fuel.png"), GeneratorsLang.FUEL_TAB, 34, ExtraPacketTileButtonPress.ClickedTileButton.TAB_FUEL, GeneratorsSpecialColors.TAB_MULTIBLOCK_FUEL),
        STAT(MekanismUtils.getResource(MekanismUtils.ResourceType.GUI, "stats.png"), GeneratorsLang.STATS_TAB, 62, ExtraPacketTileButtonPress.ClickedTileButton.TAB_STATS, SpecialColors.TAB_MULTIBLOCK_STATS);

        private final ExtraPacketTileButtonPress.ClickedTileButton button;
        private final ColorAtlas.ColorRegistryObject colorRO;
        private final ILangEntry description;
        private final ResourceLocation path;
        private final int yPos;

        NaquadahReactorTab(ResourceLocation path, ILangEntry description, int y, ExtraPacketTileButtonPress.ClickedTileButton button, ColorAtlas.ColorRegistryObject colorRO) {
            this.path = path;
            this.description = description;
            this.yPos = y;
            this.button = button;
            this.colorRO = colorRO;
        }

        @Override
        public ResourceLocation getResource() {
            return path;
        }

        @Override
        public void onClick(TileEntityNaquadahReactorController tile) {
            PacketUtils.sendToServer(new ExtraPacketTileButtonPress(button, tile.getBlockPos()));
        }

        @Override
        public Component getDescription() {
            return description.translate();
        }

        @Override
        public int getYPos() {
            return yPos;
        }

        @Override
        public ColorAtlas.ColorRegistryObject getTabColor() {
            return colorRO;
        }
    }
}
