package com.jerry.mekextras.client.gui.element.button;

import com.jerry.mekextras.common.tile.ExtraTileEntityChemicalTank;
import mekanism.client.gui.IGuiWrapper;
import mekanism.client.gui.element.button.MekanismImageButton;
import mekanism.common.network.PacketUtils;
import mekanism.common.network.to_server.PacketGuiInteract;
import mekanism.common.util.MekanismUtils;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

public class ExtraGuiGasMode extends MekanismImageButton {
    private static final ResourceLocation IDLE = MekanismUtils.getResource(MekanismUtils.ResourceType.GUI, "gas_mode_idle.png");
    private static final ResourceLocation EXCESS = MekanismUtils.getResource(MekanismUtils.ResourceType.GUI, "gas_mode_excess.png");
    private static final ResourceLocation DUMP = MekanismUtils.getResource(MekanismUtils.ResourceType.GUI, "gas_mode_dump.png");

    private final Tooltip dumpExcess;
    private final Tooltip dump;
    private final boolean left;
    private final Supplier<ExtraTileEntityChemicalTank.GasMode> gasModeSupplier;

    public ExtraGuiGasMode(IGuiWrapper gui, int x, int y, boolean left, Supplier<ExtraTileEntityChemicalTank.GasMode> gasModeSupplier, BlockPos pos, int tank) {
        this(gui, x, y, left, gasModeSupplier, pos, tank, null, null);
    }

    public ExtraGuiGasMode(IGuiWrapper gui, int x, int y, boolean left, Supplier<ExtraTileEntityChemicalTank.GasMode> gasModeSupplier, BlockPos pos, int tank, Tooltip dumpExcess, Tooltip dump) {
        super(gui, x, y, 10, IDLE, (element, mouseX, mouseY) -> PacketUtils.sendToServer(new PacketGuiInteract(PacketGuiInteract.GuiInteraction.GAS_MODE_BUTTON, pos, tank)));
        this.left = left;
        this.gasModeSupplier = gasModeSupplier;
        this.dumpExcess = dumpExcess;
        this.dump = dump;
    }

    @Override
    protected ResourceLocation getResource() {
        return switch (gasModeSupplier.get()) {
            case DUMPING_EXCESS -> EXCESS;
            case DUMPING -> DUMP;
            default -> super.getResource();
        };
    }

    @Override
    public void renderForeground(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        //Draw the text next to the button
        Component component = gasModeSupplier.get().getTextComponent();
        if (left) {
            drawTextScaledBound(guiGraphics, component, relativeX - 3 - (int) (getStringWidth(component) * getNeededScale(component, 66)), relativeY + 1, titleTextColor(), 66);
        } else {
            drawTextScaledBound(guiGraphics, component, relativeX + width + 5, relativeY + 1, titleTextColor(), 66);
        }
        super.renderForeground(guiGraphics, mouseX, mouseY);
    }

    @Override
    public void updateTooltip(int mouseX, int mouseY) {
        if (dumpExcess != null) {
            ExtraTileEntityChemicalTank.GasMode dumpMode = gasModeSupplier.get();
            if (dumpMode != ExtraTileEntityChemicalTank.GasMode.IDLE) {
                setTooltip(dumpMode == ExtraTileEntityChemicalTank.GasMode.DUMPING_EXCESS ? dumpExcess : dump);
            } else {
                clearTooltip();
            }
        }
    }
}
