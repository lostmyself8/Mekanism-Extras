package com.jerry.mekanism_extras.client.render.transmitter;

import com.jerry.mekanism_extras.common.content.network.transmitter.ExtraThermodynamicConductor;
import com.jerry.mekanism_extras.common.tile.transmitter.ExtraTileEntityThermodynamicConductor;

import mekanism.client.render.MekanismRenderer;
import mekanism.client.render.transmitter.RenderTransmitterBase;
import mekanism.common.base.ProfilerConstants;
import mekanism.common.util.HeatUtils;

import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.util.profiling.ProfilerFiller;

import com.mojang.blaze3d.vertex.PoseStack;
import org.jetbrains.annotations.NotNull;

public class ExtraRenderThermodynamicConductor extends RenderTransmitterBase<ExtraTileEntityThermodynamicConductor> {

    public ExtraRenderThermodynamicConductor(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected void render(ExtraTileEntityThermodynamicConductor tile, float partialTick, PoseStack matrix, MultiBufferSource renderer, int light, int overlayLight,
                          @NotNull ProfilerFiller profiler) {
        matrix.pushPose();
        matrix.translate(0.5, 0.5, 0.5);
        ExtraThermodynamicConductor conductor = tile.getTransmitter();
        int argb = HeatUtils.getColorFromTemp(conductor.getTotalTemperature(), conductor.getBaseColor()).argb();
        renderModel(tile, matrix, renderer.getBuffer(Sheets.translucentCullBlockSheet()), argb, MekanismRenderer.getAlpha(argb), LightTexture.FULL_BRIGHT,
                overlayLight, MekanismRenderer.heatIcon);
        matrix.popPose();
    }

    @Override
    protected @NotNull String getProfilerSection() {
        return ProfilerConstants.THERMODYNAMIC_CONDUCTOR;
    }
}
