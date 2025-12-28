package com.jerry.mekextras.client.render.item.block;

import com.jerry.mekextras.client.model.ColorModelEnergyCore;
import com.jerry.mekextras.client.render.tileentity.RenderExtraEnergyCube;
import com.jerry.mekextras.common.item.block.ItemBlockExtraEnergyCube;
import com.jerry.mekextras.common.tier.ECTier;
import com.jerry.mekextras.common.tile.TileEntityExtraEnergyCube;

import mekanism.api.RelativeSide;
import mekanism.client.render.MekanismRenderer;
import mekanism.client.render.item.MekanismISTER;
import mekanism.common.attachments.component.AttachedSideConfig;
import mekanism.common.lib.transmitter.TransmissionType;
import mekanism.common.tile.component.config.DataType;
import mekanism.common.tile.component.config.IPersistentConfigInfo;
import mekanism.common.util.EnumUtils;
import mekanism.common.util.StorageUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.model.data.ModelData;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import org.jetbrains.annotations.NotNull;

public class RenderExtraEnergyCubeItem extends MekanismISTER {

    public static final RenderExtraEnergyCubeItem EXTRA_RENDERER = new RenderExtraEnergyCubeItem();
    private ColorModelEnergyCore core;

    @Override
    public void onResourceManagerReload(@NotNull ResourceManager resourceManager) {
        core = new ColorModelEnergyCore(getEntityModels());
    }

    @Override
    public void renderByItem(@NotNull ItemStack stack, @NotNull ItemDisplayContext displayContext, @NotNull PoseStack matrix, @NotNull MultiBufferSource renderer,
                             int light, int overlayLight) {
        ECTier tier = ((ItemBlockExtraEnergyCube) stack.getItem()).getAdvancedTier();
        TileEntityExtraEnergyCube.CubeSideState[] sideStates = new TileEntityExtraEnergyCube.CubeSideState[EnumUtils.SIDES.length];
        AttachedSideConfig fallback = ItemBlockExtraEnergyCube.SIDE_CONFIG;
        IPersistentConfigInfo sideConfig = AttachedSideConfig.getStoredConfigInfo(stack, fallback, TransmissionType.ENERGY);
        for (RelativeSide side : EnumUtils.SIDES) {
            DataType dataType = sideConfig.getDataType(side);
            TileEntityExtraEnergyCube.CubeSideState state = TileEntityExtraEnergyCube.CubeSideState.INACTIVE;
            if (dataType != DataType.NONE) {
                state = dataType.canOutput() ? TileEntityExtraEnergyCube.CubeSideState.ACTIVE_LIT : TileEntityExtraEnergyCube.CubeSideState.ACTIVE_UNLIT;
            }
            sideStates[side.ordinal()] = state;
        }
        ModelData modelData = ModelData.builder().with(TileEntityExtraEnergyCube.SIDE_STATE_PROPERTY, sideStates).build();
        renderBlockItem(stack, displayContext, matrix, renderer, light, overlayLight, modelData);
        double energyPercentage = StorageUtils.getEnergyRatio(stack);
        if (energyPercentage > 0) {
            float ticks = Minecraft.getInstance().levelRenderer.getTicks() + MekanismRenderer.getPartialTick();
            float scaledTicks = 4 * ticks;
            matrix.pushPose();
            matrix.translate(0.5, 0.5, 0.5);
            matrix.scale(0.4F, 0.4F, 0.4F);
            matrix.translate(0, Math.sin(Math.toRadians(3 * ticks)) / 7, 0);
            matrix.mulPose(Axis.YP.rotationDegrees(scaledTicks));
            matrix.mulPose(RenderExtraEnergyCube.coreVec.rotationDegrees(36F + scaledTicks));
            core.render(matrix, renderer, LightTexture.FULL_BRIGHT, overlayLight, tier.getAdvanceTier(), (float) energyPercentage);
            matrix.popPose();
        }
    }
}
