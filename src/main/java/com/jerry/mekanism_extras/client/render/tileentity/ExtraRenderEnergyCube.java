package com.jerry.mekanism_extras.client.render.tileentity;

import com.jerry.mekanism_extras.client.model.ExtraModelEnergyCore;
import com.jerry.mekanism_extras.common.tier.TierColor;
import com.jerry.mekanism_extras.common.tile.ExtraTileEntityEnergyCube;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import mekanism.client.render.RenderTickHandler;
import mekanism.client.render.tileentity.ModelTileEntityRenderer;
import mekanism.common.base.ProfilerConstants;
import mekanism.common.util.MekanismUtils;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

public class ExtraRenderEnergyCube extends ModelTileEntityRenderer<ExtraTileEntityEnergyCube, ExtraModelEnergyCore> {
    public static final Axis coreVec = Axis.of(new Vector3f(0.0F, MekanismUtils.ONE_OVER_ROOT_TWO, MekanismUtils.ONE_OVER_ROOT_TWO));

    public ExtraRenderEnergyCube(BlockEntityRendererProvider.Context context) {
        super(context, ExtraModelEnergyCore::new);
    }

    @Override
    protected void render(ExtraTileEntityEnergyCube tile, float partialTicks, @NotNull PoseStack matrix, @NotNull MultiBufferSource renderer, int light, int overlayLight, @NotNull ProfilerFiller profiler) {
        float energyScale = tile.getEnergyScale();
        Vec3 renderPos = Vec3.atCenterOf(tile.getBlockPos());
        RenderTickHandler.addTransparentRenderer(ExtraModelEnergyCore.BATCHED_RENDER_TYPE, new RenderTickHandler.LazyRender() {
            @Override
            public void render(Camera camera, VertexConsumer buffer, PoseStack poseStack, int renderTick, float partialTick, ProfilerFiller profiler) {
                float ticks = renderTick + partialTick;
                float scaledTicks = 4 * ticks;
                poseStack.pushPose();
                poseStack.translate(renderPos.x, renderPos.y, renderPos.z);
                poseStack.scale(0.4F, 0.4F, 0.4F);
                poseStack.translate(0, Math.sin(Math.toRadians(3 * ticks)) / 7, 0);
                poseStack.mulPose(Axis.YP.rotationDegrees(scaledTicks));
                poseStack.mulPose(coreVec.rotationDegrees(36F + scaledTicks));
                model.render(poseStack, buffer, LightTexture.FULL_BRIGHT, overlayLight, TierColor.getColor(tile.getTier()), energyScale);
                poseStack.popPose();
            }

            @Override
            public Vec3 getCenterPos(float partialTick) {
                return renderPos;
            }

            @Override
            public String getProfilerSection() {
                return ProfilerConstants.ENERGY_CUBE_CORE;
            }
        });
    }

    @NotNull
    @Override
    protected String getProfilerSection() {
        return ProfilerConstants.ENERGY_CUBE;
    }

    @Override
    public boolean shouldRender(ExtraTileEntityEnergyCube tile, @NotNull Vec3 camera) {
        return tile.getTier().getMaxEnergy().intValue() > 0 && super.shouldRender(tile, camera);
    }
}
