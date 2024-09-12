package com.jerry.mekextras.client.gui;

import com.jerry.mekextras.common.network.to_server.button.ExtraPacketTileButtonPress;
import com.jerry.mekextras.common.tile.multiblock.TileEntityReinforcedInductionCasing;
import mekanism.api.text.ILangEntry;
import mekanism.client.SpecialColors;
import mekanism.client.gui.IGuiWrapper;
import mekanism.client.gui.element.tab.GuiTabElementType;
import mekanism.client.gui.element.tab.TabType;
import mekanism.client.render.lib.ColorAtlas;
import mekanism.common.MekanismLang;
import mekanism.common.network.PacketUtils;
import mekanism.common.util.MekanismUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class GuiReinforcedMatrixTab extends GuiTabElementType<TileEntityReinforcedInductionCasing, GuiReinforcedMatrixTab.ReinforcedMatrixTab> {
    public GuiReinforcedMatrixTab(IGuiWrapper gui, TileEntityReinforcedInductionCasing tile, ReinforcedMatrixTab type) {
        super(gui, tile, type);
    }

    public enum ReinforcedMatrixTab implements TabType<TileEntityReinforcedInductionCasing> {
        MAIN("energy.png", MekanismLang.MAIN_TAB, ExtraPacketTileButtonPress.ClickedTileButton.TAB_MAIN, SpecialColors.TAB_MULTIBLOCK_MAIN),
        STAT("stats.png", MekanismLang.MATRIX_STATS, ExtraPacketTileButtonPress.ClickedTileButton.TAB_STATS, SpecialColors.TAB_MULTIBLOCK_STATS);

        private final ColorAtlas.ColorRegistryObject colorRO;
        private final ExtraPacketTileButtonPress.ClickedTileButton button;
        private final ILangEntry description;
        private final String path;

        ReinforcedMatrixTab(String path, ILangEntry description, ExtraPacketTileButtonPress.ClickedTileButton button, ColorAtlas.ColorRegistryObject colorRO) {
            this.path = path;
            this.description = description;
            this.button = button;
            this.colorRO = colorRO;
        }

        @Override
        public ResourceLocation getResource() {
            return MekanismUtils.getResource(MekanismUtils.ResourceType.GUI, path);
        }

        @Override
        public void onClick(TileEntityReinforcedInductionCasing tile) {
            PacketUtils.sendToServer(new ExtraPacketTileButtonPress(button, tile));
        }

        @Override
        public Component getDescription() {
            return description.translate();
        }

        @Override
        public ColorAtlas.ColorRegistryObject getTabColor() {
            return colorRO;
        }
    }
}
