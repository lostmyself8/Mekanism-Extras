package com.jerry.genextras.client.gui;

import com.jerry.genextras.common.network.to_server.PacketGenExtraGuiInteract;
import com.jerry.genextras.common.tile.naquadah.TileEntityNaquadahReactorLogicAdapter;

import mekanism.api.text.EnumColor;
import mekanism.client.gui.GuiMekanismTile;
import mekanism.client.gui.element.GuiElementHolder;
import mekanism.client.gui.element.button.ToggleButton;
import mekanism.client.gui.element.scroll.GuiScrollBar;
import mekanism.common.MekanismLang;
import mekanism.common.inventory.container.tile.EmptyTileContainer;
import mekanism.common.network.PacketUtils;
import mekanism.common.network.to_server.PacketGuiInteract;
import mekanism.common.util.text.BooleanStateDisplay;
import mekanism.generators.client.gui.element.button.ReactorLogicButton;
import mekanism.generators.common.GeneratorsLang;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

import org.jetbrains.annotations.NotNull;

public class GuiNaquadahReactorLogicAdapter extends GuiMekanismTile<TileEntityNaquadahReactorLogicAdapter, EmptyTileContainer<TileEntityNaquadahReactorLogicAdapter>> {

    private static final int DISPLAY_COUNT = 4;

    private GuiScrollBar scrollBar;

    public GuiNaquadahReactorLogicAdapter(EmptyTileContainer<TileEntityNaquadahReactorLogicAdapter> container, Inventory inv, Component title) {
        super(container, inv, title);
        imageWidth += 20;
    }

    @Override
    protected void addGuiElements() {
        super.addGuiElements();
        addRenderableWidget(new GuiElementHolder(this, 26, 31, 130, 90));
        addRenderableWidget(new ToggleButton(this, 26, 19, 11, tile::isActiveCooled,
                (element, mouseX, mouseY) -> PacketUtils.sendToServer(new PacketGuiInteract(PacketGuiInteract.GuiInteraction.NEXT_MODE, ((GuiNaquadahReactorLogicAdapter) element.gui()).tile))))
                .setTooltip(GeneratorsLang.REACTOR_LOGIC_TOGGLE_COOLING);
        scrollBar = addRenderableWidget(new GuiScrollBar(this, 156, 31, 90, () -> tile.getModes().length, () -> DISPLAY_COUNT));
        for (int i = 0; i < DISPLAY_COUNT; i++) {
            int typeShift = 22 * i;
            addRenderableWidget(new ReactorLogicButton<>(this, 27, 32 + typeShift, i, tile, TileEntityNaquadahReactorLogicAdapter.NaquadahReactorLogic.class, scrollBar::getCurrentSelection, tile::getModes, this::changeLogic));
        }
    }

    private void changeLogic(TileEntityNaquadahReactorLogicAdapter.NaquadahReactorLogic type) {
        if (type != null) {
            PacketUtils.sendToServer(new PacketGenExtraGuiInteract(PacketGenExtraGuiInteract.GenExtraGuiInteraction.LOGIC_TYPE, tile, type.ordinal()));
        }
    }

    @Override
    protected void drawForegroundText(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY) {
        renderTitleText(guiGraphics);
        drawScrollingString(guiGraphics, GeneratorsLang.REACTOR_LOGIC_ACTIVE_COOLING.translate(EnumColor.RED, BooleanStateDisplay.OnOff.of(tile.isActiveCooled())), 23, 20, TextAlignment.LEFT,
                titleTextColor(), getXSize() - 23, 16, false);
        drawScrollingString(guiGraphics, GeneratorsLang.REACTOR_LOGIC_REDSTONE_MODE.translate(EnumColor.RED, tile.logicType), 0, 123, TextAlignment.CENTER, titleTextColor(), 4, false);
        drawScrollingString(guiGraphics, MekanismLang.STATUS.translate(EnumColor.RED, tile.checkMode() ? GeneratorsLang.REACTOR_LOGIC_OUTPUTTING : MekanismLang.IDLE),
                0, 136, TextAlignment.CENTER, titleTextColor(), 4, false);
        super.drawForegroundText(guiGraphics, mouseX, mouseY);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double deltaX, double deltaY) {
        return super.mouseScrolled(mouseX, mouseY, deltaX, deltaY) || scrollBar.adjustScroll(deltaY);
    }
}
