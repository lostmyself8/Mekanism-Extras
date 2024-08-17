package com.jerry.mekanism_extras.client.gui;

import com.jerry.mekanism_extras.common.network.to_server.ExtraPacketGuiButtonPress;
import com.jerry.mekanism_extras.common.tile.multiblock.ExtraTileEntityInductionCasing;
import mekanism.api.text.ILangEntry;
import mekanism.client.SpecialColors;
import mekanism.client.gui.IGuiWrapper;
import mekanism.client.gui.element.tab.GuiTabElementType;
import mekanism.client.gui.element.tab.TabType;
import mekanism.client.render.lib.ColorAtlas;
import mekanism.common.Mekanism;
import mekanism.common.MekanismLang;
import mekanism.common.util.MekanismUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class ExtraGuiMatrixTab extends GuiTabElementType<ExtraTileEntityInductionCasing, ExtraGuiMatrixTab.MatrixTab> {

    public ExtraGuiMatrixTab(IGuiWrapper gui, ExtraTileEntityInductionCasing tile, MatrixTab type) {
        super(gui, tile, type);
    }

    public enum MatrixTab implements TabType<ExtraTileEntityInductionCasing> {
        MAIN("energy.png", MekanismLang.MAIN_TAB, ExtraPacketGuiButtonPress.ClickedTileButton.TAB_MAIN, SpecialColors.TAB_MULTIBLOCK_MAIN),
        STAT("stats.png", MekanismLang.MATRIX_STATS, ExtraPacketGuiButtonPress.ClickedTileButton.TAB_STATS, SpecialColors.TAB_MULTIBLOCK_STATS);

        private final ColorAtlas.ColorRegistryObject colorRO;
        private final ExtraPacketGuiButtonPress.ClickedTileButton button;
        private final ILangEntry description;
        private final String path;

        MatrixTab(String path, ILangEntry description, ExtraPacketGuiButtonPress.ClickedTileButton button, ColorAtlas.ColorRegistryObject colorRO) {
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
        public void onClick(ExtraTileEntityInductionCasing tile) {
            Mekanism.packetHandler().sendToServer(new ExtraPacketGuiButtonPress(button, tile));
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
