package com.jerry.mekanism_extras.client.render.tileentity;

import com.jerry.mekanism_extras.client.model.ColorModelEnergyCore;
import com.jerry.mekanism_extras.api.tier.AdvanceTier;
import com.jerry.mekanism_extras.common.tile.ExtraTileEntityEnergyCube;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.client.render.RenderTickHandler;
import mekanism.client.render.tileentity.ModelTileEntityRenderer;
import mekanism.common.base.ProfilerConstants;
import mekanism.common.util.MekanismUtils;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

@NothingNullByDefault
public class ExtraRenderEnergyCube extends ModelTileEntityRenderer<ExtraTileEntityEnergyCube, ColorModelEnergyCore> {

    public static final Axis coreVec = Axis.of(new Vector3f(0.0F, MekanismUtils.ONE_OVER_ROOT_TWO, MekanismUtils.ONE_OVER_ROOT_TWO));

    public ExtraRenderEnergyCube(BlockEntityRendererProvider.Context context) {
        super(context, ColorModelEnergyCore::new);
    }

    @Override
    protected void render(@NotNull ExtraTileEntityEnergyCube tile, float partialTick, @NotNull PoseStack matrix, MultiBufferSource renderer, int light, int overlayLight, ProfilerFiller profiler) {
        float energyScale = tile.getEnergyScale();
        Vec3 renderPos = tile.getBlockPos().getCenter();
        AdvanceTier advanceTier = tile.getAdvanceTier().getAdvanceTier();
        RenderTickHandler.addTransparentRenderer(new RenderTickHandler.LazyRender() {
            @Override
            public void render(Camera camera, VertexConsumer buffer, PoseStack poseStack, int renderTick, float partialTick, ProfilerFiller profiler) {
                float ticks = renderTick + partialTick;
                float scaledTicks = 4 * ticks;
                poseStack.pushPose();
                Vec3 offset = renderPos.subtract(camera.getPosition());
                poseStack.translate(offset.x, offset.y, offset.z);
                poseStack.scale(0.4F, 0.4F, 0.4F);
                poseStack.translate(0, Math.sin(Math.toRadians(3 * ticks)) / 7, 0);
                poseStack.mulPose(Axis.YP.rotationDegrees(scaledTicks));
                poseStack.mulPose(coreVec.rotationDegrees(36F + scaledTicks));
                model.render(poseStack, buffer, LightTexture.FULL_BRIGHT, overlayLight, advanceTier, energyScale);
                poseStack.popPose();
            }

            @Override
            @NotNull
            public Vec3 getCenterPos(float partialTick) {
                return renderPos;
            }

            @Override
            @NotNull
            public String getProfilerSection() {
                return ProfilerConstants.ENERGY_CUBE_CORE;
            }

            @Override
            @NotNull
            public RenderType getRenderType() {
                return ColorModelEnergyCore.BATCHED_RENDER_TYPE;
            }
        });
    }

    @Override
    protected String getProfilerSection() {
        return ProfilerConstants.ENERGY_CUBE;
    }

    @Override
    public boolean shouldRender(ExtraTileEntityEnergyCube tile, Vec3 camera) {
        return tile.getEnergyScale() > 0 && super.shouldRender(tile, camera);
    }
}
