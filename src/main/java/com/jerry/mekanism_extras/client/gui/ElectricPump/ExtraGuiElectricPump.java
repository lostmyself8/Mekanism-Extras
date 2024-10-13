package com.jerry.mekanism_extras.client.gui.ElectricPump;

import com.jerry.mekanism_extras.common.tile.machine.ExtraTileEntityElectricPump;
import com.mojang.blaze3d.vertex.PoseStack;
import mekanism.client.gui.GuiMekanismTile;
import mekanism.client.gui.element.GuiDownArrow;
import mekanism.client.gui.element.GuiInnerScreen;
import mekanism.client.gui.element.bar.GuiVerticalPowerBar;
import mekanism.client.gui.element.gauge.GaugeType;
import mekanism.client.gui.element.gauge.GuiFluidGauge;
import mekanism.client.gui.element.tab.GuiEnergyTab;
import mekanism.common.MekanismLang;
import mekanism.common.capabilities.energy.MachineEnergyContainer;
import mekanism.common.inventory.container.tile.MekanismTileContainer;
import mekanism.common.inventory.warning.WarningTracker;
import mekanism.common.util.text.EnergyDisplay;
import mekanism.common.util.text.TextUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ExtraGuiElectricPump extends GuiMekanismTile<ExtraTileEntityElectricPump, MekanismTileContainer<ExtraTileEntityElectricPump>> {

    public ExtraGuiElectricPump(MekanismTileContainer<ExtraTileEntityElectricPump> container, Inventory inv, Component title) {
        super(container, inv, title);
        inventoryLabelY += 2;
        dynamicSlots = true;
    }

    @Override
    protected void addGuiElements() {
        super.addGuiElements();
        addRenderableWidget(new GuiInnerScreen(this, 54, 23, 80, 41, () -> {
            List<Component> list = new ArrayList<>();
            list.add(EnergyDisplay.of(tile.getEnergyContainer()).getTextComponent());
            FluidStack fluidStack = tile.fluidTank.getFluid();
            if (fluidStack.isEmpty()) {
                FluidStack fallBack = tile.getActiveType();
                if (fallBack.isEmpty()) {
                    list.add(MekanismLang.NO_FLUID.translate());
                } else {
                    list.add(fallBack.getDisplayName());
                }
            } else {
                list.add(MekanismLang.GENERIC_STORED_MB.translate(fluidStack, TextUtils.format(fluidStack.getAmount())));
            }
            return list;
        }));
        addRenderableWidget(new GuiDownArrow(this, 32, 39));
        addRenderableWidget(new GuiVerticalPowerBar(this, tile.getEnergyContainer(), 164, 15))
                .warning(WarningTracker.WarningType.NOT_ENOUGH_ENERGY, () -> {
                    MachineEnergyContainer<ExtraTileEntityElectricPump> energyContainer = tile.getEnergyContainer();
                    return energyContainer.getEnergyPerTick().greaterThan(energyContainer.getEnergy());
                });
        addRenderableWidget(new GuiFluidGauge(() -> tile.fluidTank, () -> tile.getFluidTanks(null), GaugeType.STANDARD, this, 6, 13))
                .warning(WarningTracker.WarningType.NO_SPACE_IN_OUTPUT, () -> tile.fluidTank.getNeeded() < tile.estimateIncrementAmount());
        //TODO: Eventually we may want to consider showing a warning if the block under the pump is of the wrong type or there wasn't a valid spot to suck
        addRenderableWidget(new GuiEnergyTab(this, tile.getEnergyContainer(), tile::usedEnergy));
    }

    @Override
    protected void drawForegroundText(@NotNull PoseStack matrix, int mouseX, int mouseY) {
        renderTitleText(matrix);
        drawString(matrix, playerInventoryTitle, inventoryLabelX, inventoryLabelY, titleTextColor());
        super.drawForegroundText(matrix, mouseX, mouseY);
    }
}
