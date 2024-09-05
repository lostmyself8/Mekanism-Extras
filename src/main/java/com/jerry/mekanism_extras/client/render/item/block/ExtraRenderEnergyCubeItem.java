package com.jerry.mekanism_extras.client.render.item.block;

import com.jerry.mekanism_extras.client.model.ExtraModelEnergyCore;
import com.jerry.mekanism_extras.client.render.tileentity.ExtraRenderEnergyCube;
import com.jerry.mekanism_extras.common.tier.ECTier;
import com.jerry.mekanism_extras.common.tier.TierColor;
import com.jerry.mekanism_extras.common.item.block.ExtraItemBlockEnergyCube;
import com.jerry.mekanism_extras.common.tile.ExtraTileEntityEnergyCube;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import mekanism.api.NBTConstants;
import mekanism.api.RelativeSide;
import mekanism.client.render.MekanismRenderer;
import mekanism.client.render.item.MekanismISTER;
import mekanism.common.lib.transmitter.TransmissionType;
import mekanism.common.tile.component.config.DataType;
import mekanism.common.util.EnumUtils;
import mekanism.common.util.ItemDataUtils;
import mekanism.common.util.StorageUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.model.data.ModelData;
import org.jetbrains.annotations.NotNull;

public class ExtraRenderEnergyCubeItem extends MekanismISTER {
    public static final ExtraRenderEnergyCubeItem EXTRA_RENDERER = new ExtraRenderEnergyCubeItem();
    private ExtraModelEnergyCore core;

    @Override
    public void onResourceManagerReload(@NotNull ResourceManager resourceManager) {
        core = new ExtraModelEnergyCore(getEntityModels());
    }

    @Override
    public void renderByItem(@NotNull ItemStack stack, @NotNull ItemDisplayContext displayContext, @NotNull PoseStack matrix, @NotNull MultiBufferSource renderer,
                             int light, int overlayLight) {
        ECTier tier = ((ExtraItemBlockEnergyCube) stack.getItem()).getAdvanceTier();

        ExtraTileEntityEnergyCube.CubeSideState[] sideStates = new ExtraTileEntityEnergyCube.CubeSideState[EnumUtils.SIDES.length];
        CompoundTag configData = ItemDataUtils.getDataMapIfPresent(stack);
        if (configData != null && configData.contains(NBTConstants.COMPONENT_CONFIG, Tag.TAG_COMPOUND)) {
            CompoundTag sideConfig = configData.getCompound(NBTConstants.COMPONENT_CONFIG).getCompound(NBTConstants.CONFIG + TransmissionType.ENERGY.ordinal());
            //TODO: Maybe improve on this, but for now this is a decent way of making it not have disabled sides show
            for (RelativeSide side : EnumUtils.SIDES) {
                DataType dataType = DataType.byIndexStatic(sideConfig.getInt(NBTConstants.SIDE + side.ordinal()));
                ExtraTileEntityEnergyCube.CubeSideState state = ExtraTileEntityEnergyCube.CubeSideState.INACTIVE;
                if (dataType != DataType.NONE) {
                    state = dataType.canOutput() ? ExtraTileEntityEnergyCube.CubeSideState.ACTIVE_LIT : ExtraTileEntityEnergyCube.CubeSideState.ACTIVE_UNLIT;
                }
                sideStates[side.ordinal()] = state;
            }
        } else {
            for (RelativeSide side : EnumUtils.SIDES) {
                sideStates[side.ordinal()] = side == RelativeSide.FRONT ? ExtraTileEntityEnergyCube.CubeSideState.ACTIVE_LIT : ExtraTileEntityEnergyCube.CubeSideState.ACTIVE_UNLIT;
            }
        }
        ModelData modelData = ModelData.builder().with(ExtraTileEntityEnergyCube.SIDE_STATE_PROPERTY, sideStates).build();
        renderBlockItem(stack, displayContext, matrix, renderer, light, overlayLight, modelData);
        double energyPercentage = StorageUtils.getStoredEnergyFromNBT(stack).divideToLevel(tier.getMaxEnergy());
        if (energyPercentage > 0) {
            float ticks = Minecraft.getInstance().levelRenderer.getTicks() + MekanismRenderer.getPartialTick();
            float scaledTicks = 4 * ticks;
            matrix.pushPose();
            matrix.translate(0.5, 0.5, 0.5);
            matrix.scale(0.4F, 0.4F, 0.4F);
            matrix.translate(0, Math.sin(Math.toRadians(3 * ticks)) / 7, 0);
            matrix.mulPose(Axis.YP.rotationDegrees(scaledTicks));
            matrix.mulPose(ExtraRenderEnergyCube.coreVec.rotationDegrees(36F + scaledTicks));
            core.render(matrix, renderer, LightTexture.FULL_BRIGHT, overlayLight, TierColor.getColor(tier), (float) energyPercentage);
            matrix.popPose();
        }
    }
}
