package com.jerry.generator_extras.client.gui.element;

import com.jerry.generator_extras.common.network.to_server.ExtraPacketGeneratorsGuiButtonPress;
import com.jerry.generator_extras.common.tile.reactor.TileEntityNaquadahReactorController;
import mekanism.api.text.ILangEntry;
import mekanism.client.SpecialColors;
import mekanism.client.gui.IGuiWrapper;
import mekanism.client.gui.element.tab.GuiTabElementType;
import mekanism.client.gui.element.tab.TabType;
import mekanism.client.render.lib.ColorAtlas;
import mekanism.common.util.MekanismUtils;
import mekanism.generators.client.GeneratorsSpecialColors;
import mekanism.generators.common.GeneratorsLang;
import mekanism.generators.common.MekanismGenerators;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class GuiNaquadahReactorTab extends GuiTabElementType<TileEntityNaquadahReactorController, GuiNaquadahReactorTab.NaquadahReactorTab> {

    public GuiNaquadahReactorTab(IGuiWrapper gui, TileEntityNaquadahReactorController tile, NaquadahReactorTab type) {
        super(gui, tile, type);
    }

    public enum NaquadahReactorTab implements TabType<TileEntityNaquadahReactorController> {
        HEAT(MekanismUtils.getResource(MekanismUtils.ResourceType.GUI, "heat.png"), GeneratorsLang.HEAT_TAB, 6, ExtraPacketGeneratorsGuiButtonPress.ExtraClickedGeneratorsTileButton.TAB_HEAT, GeneratorsSpecialColors.TAB_MULTIBLOCK_HEAT),
        FUEL(MekanismGenerators.rl(MekanismUtils.ResourceType.GUI.getPrefix() + "fuel.png"), GeneratorsLang.FUEL_TAB, 34, ExtraPacketGeneratorsGuiButtonPress.ExtraClickedGeneratorsTileButton.TAB_FUEL, GeneratorsSpecialColors.TAB_MULTIBLOCK_FUEL),
        STAT(MekanismUtils.getResource(MekanismUtils.ResourceType.GUI, "stats.png"), GeneratorsLang.STATS_TAB, 62, ExtraPacketGeneratorsGuiButtonPress.ExtraClickedGeneratorsTileButton.TAB_STATS, SpecialColors.TAB_MULTIBLOCK_STATS);

        private final ExtraPacketGeneratorsGuiButtonPress.ExtraClickedGeneratorsTileButton button;
        private final ColorAtlas.ColorRegistryObject colorRO;
        private final ILangEntry description;
        private final ResourceLocation path;
        private final int yPos;

        NaquadahReactorTab(ResourceLocation path, ILangEntry description, int y, ExtraPacketGeneratorsGuiButtonPress.ExtraClickedGeneratorsTileButton button, ColorAtlas.ColorRegistryObject colorRO) {
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
            MekanismGenerators.packetHandler().sendToServer(new ExtraPacketGeneratorsGuiButtonPress(button, tile.getBlockPos()));
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
