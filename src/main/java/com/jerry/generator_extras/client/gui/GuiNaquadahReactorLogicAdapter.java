package com.jerry.generator_extras.client.gui;

import com.jerry.generator_extras.common.network.to_server.ExtraPacketGeneratorsGuiInteract;
import com.jerry.generator_extras.common.tile.reactor.TileEntityNaquadahReactorLogicAdapter;
import mekanism.api.text.EnumColor;
import mekanism.client.gui.GuiMekanismTile;
import mekanism.client.gui.element.GuiElementHolder;
import mekanism.client.gui.element.button.ToggleButton;
import mekanism.client.gui.element.scroll.GuiScrollBar;
import mekanism.common.Mekanism;
import mekanism.common.MekanismLang;
import mekanism.common.inventory.container.tile.EmptyTileContainer;
import mekanism.common.network.to_server.PacketGuiInteract;
import mekanism.common.util.text.BooleanStateDisplay;
import mekanism.generators.client.gui.element.button.ReactorLogicButton;
import mekanism.generators.common.GeneratorsLang;
import mekanism.generators.common.MekanismGenerators;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

public class GuiNaquadahReactorLogicAdapter extends GuiMekanismTile<TileEntityNaquadahReactorLogicAdapter, EmptyTileContainer<TileEntityNaquadahReactorLogicAdapter>> {

    private static final int DISPLAY_COUNT = 4;

    private GuiScrollBar scrollBar;
    public GuiNaquadahReactorLogicAdapter(EmptyTileContainer<TileEntityNaquadahReactorLogicAdapter> container, Inventory inv, Component title) {
        super(container, inv, title);
    }

    @Override
    protected void addGuiElements() {
        super.addGuiElements();
        addRenderableWidget(new GuiElementHolder(this, 16, 31, 130, 90));
        addRenderableWidget(new ToggleButton(this, 16, 19, 11, tile::isActiveCooled,
                () -> Mekanism.packetHandler().sendToServer(new PacketGuiInteract(PacketGuiInteract.GuiInteraction.NEXT_MODE, tile)), getOnHover(GeneratorsLang.REACTOR_LOGIC_TOGGLE_COOLING)));
        scrollBar = addRenderableWidget(new GuiScrollBar(this, 146, 31, 90, () -> tile.getModes().length, () -> DISPLAY_COUNT));
        for (int i = 0; i < DISPLAY_COUNT; i++) {
            int typeShift = 22 * i;
            addRenderableWidget(new ReactorLogicButton<>(this, 17, 32 + typeShift, i, tile, scrollBar::getCurrentSelection, tile::getModes, type -> {
                if (type == null) {
                    return;
                }
                MekanismGenerators.packetHandler().sendToServer(new ExtraPacketGeneratorsGuiInteract(ExtraPacketGeneratorsGuiInteract.ExtraGeneratorsGuiInteraction.LOGIC_TYPE, tile, type.ordinal()));
            }));
        }
    }

    @Override
    protected void drawForegroundText(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY) {
        renderTitleText(guiGraphics);
        drawTextScaledBound(guiGraphics, GeneratorsLang.REACTOR_LOGIC_ACTIVE_COOLING.translate(EnumColor.RED, BooleanStateDisplay.OnOff.of(tile.isActiveCooled())), 29, 20, titleTextColor(), 117);
        drawTextScaledBound(guiGraphics, GeneratorsLang.REACTOR_LOGIC_REDSTONE_MODE.translate(EnumColor.RED, tile.logicType), 16, 123, titleTextColor(), 144);
        drawCenteredText(guiGraphics, MekanismLang.STATUS.translate(EnumColor.RED, tile.checkMode() ? GeneratorsLang.REACTOR_LOGIC_OUTPUTTING : MekanismLang.IDLE),
                0, imageWidth, 136, titleTextColor());
        super.drawForegroundText(guiGraphics, mouseX, mouseY);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
        return super.mouseScrolled(mouseX, mouseY, delta) || scrollBar.adjustScroll(delta);
    }
}
