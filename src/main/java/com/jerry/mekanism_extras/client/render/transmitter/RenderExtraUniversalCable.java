package com.jerry.mekanism_extras.client.render.transmitter;

import com.jerry.mekanism_extras.common.content.network.transmitter.ExtraUniversalCable;
import com.jerry.mekanism_extras.common.tile.transmitter.ExtraTileEntityUniversalCable;

import mekanism.api.annotations.NothingNullByDefault;
import mekanism.client.render.MekanismRenderer;
import mekanism.client.render.transmitter.RenderTransmitterBase;
import mekanism.common.base.ProfilerConstants;
import mekanism.common.content.network.EnergyNetwork;

import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.phys.Vec3;

import com.mojang.blaze3d.vertex.PoseStack;

@NothingNullByDefault
public class RenderExtraUniversalCable extends RenderTransmitterBase<ExtraTileEntityUniversalCable> {

    public RenderExtraUniversalCable(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected void render(ExtraTileEntityUniversalCable tile, float partialTick, PoseStack matrix, MultiBufferSource renderer, int light, int overlayLight,
                          ProfilerFiller profiler) {
        EnergyNetwork network = tile.getTransmitter().getTransmitterNetwork();
        if (network == null) {
            return;// race condition perhaps
        }
        matrix.pushPose();
        matrix.translate(0.5, 0.5, 0.5);
        renderModel(tile, matrix, renderer.getBuffer(Sheets.translucentCullBlockSheet()), 0xFFFFFF, network.currentScale, LightTexture.FULL_BRIGHT,
                overlayLight, MekanismRenderer.energyIcon);
        matrix.popPose();
    }

    @Override
    protected String getProfilerSection() {
        return ProfilerConstants.UNIVERSAL_CABLE;
    }

    @Override
    protected boolean shouldRenderTransmitter(ExtraTileEntityUniversalCable tile, Vec3 camera) {
        ExtraUniversalCable cable = tile.getTransmitter();
        if (cable.hasTransmitterNetwork()) {
            EnergyNetwork network = cable.getTransmitterNetwork();
            // Note: We don't check if the network is empty as we don't actually ever sync the energy value to the
            // client
            return network.currentScale > 0;
        }
        return false;
    }
}
