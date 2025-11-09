package com.jerry.mekanism_extras.client.render.transmitter;

import com.jerry.mekanism_extras.common.content.network.transmitter.ExtraLogisticalTransporter;
import com.jerry.mekanism_extras.common.tier.transmitter.TPTier;
import com.jerry.mekanism_extras.common.tile.transmitter.ExtraTileEntityLogisticalTransporterBase;
import com.jerry.mekanism_extras.common.util.ExtraTransporterUtils;

import mekanism.api.text.EnumColor;
import mekanism.client.model.ModelTransporterBox;
import mekanism.client.render.MekanismRenderer;
import mekanism.client.render.transmitter.RenderTransmitterBase;
import mekanism.common.base.ProfilerConstants;
import mekanism.common.content.transporter.TransporterStack;
import mekanism.common.lib.inventory.HashedItem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import com.mojang.blaze3d.vertex.PoseStack;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import javax.annotation.Nullable;

public class ExtraRenderLogisticalTransporter extends RenderTransmitterBase<ExtraTileEntityLogisticalTransporterBase> {

    private static final Map<Direction, MekanismRenderer.Model3D> cachedOverlays = new EnumMap<>(Direction.class);
    private final ModelTransporterBox modelBox;
    private final LazyItemRenderer itemRenderer = new LazyItemRenderer();

    public ExtraRenderLogisticalTransporter(BlockEntityRendererProvider.Context context) {
        super(context);
        modelBox = new ModelTransporterBox(context.getModelSet());
    }

    public static void onStitch(TextureAtlas map) {
        cachedOverlays.clear();
    }

    @Override
    protected void render(ExtraTileEntityLogisticalTransporterBase tile, float partialTick, PoseStack matrix, @NotNull MultiBufferSource renderer, int light, int overlayLight,
                          @NotNull ProfilerFiller profiler) {
        ExtraLogisticalTransporter transporter = (ExtraLogisticalTransporter) tile.getTransmitter();
        Collection<TransporterStack> inTransit = transporter.getTransit();
        BlockPos pos = tile.getBlockPos();
        if (!inTransit.isEmpty()) {
            matrix.pushPose();
            itemRenderer.init(tile.getLevel(), pos);

            float partial = partialTick * TPTier.getSpeed(transporter.tier);

            Collection<TransporterStack> reducedTransit = getReducedTransit(inTransit);
            for (TransporterStack stack : reducedTransit) {
                float[] stackPos = ExtraTransporterUtils.getStackPosition(transporter, stack, partial);
                matrix.pushPose();
                matrix.translate(stackPos[0], stackPos[1], stackPos[2]);
                matrix.scale(0.75F, 0.75F, 0.75F);
                itemRenderer.renderAsStack(matrix, renderer, stack.itemStack, light);
                matrix.popPose();
                if (stack.color != null) {
                    modelBox.render(matrix, renderer, LightTexture.FULL_BRIGHT, overlayLight, stackPos[0], stackPos[1], stackPos[2], stack.color);
                }
            }
            matrix.popPose();
        }
    }

    @Override
    protected @NotNull String getProfilerSection() {
        return ProfilerConstants.LOGISTICAL_TRANSPORTER;
    }

    /**
     * Shrink the in transit list as much as possible. Don't try to render things of the same type that are in the same
     * spot with the same color, ignoring stack size
     */
    private Collection<TransporterStack> getReducedTransit(Collection<TransporterStack> inTransit) {
        Collection<TransporterStack> reducedTransit = new ArrayList<>();
        Set<TransportInformation> information = new ObjectOpenHashSet<>();
        for (TransporterStack stack : inTransit) {
            if (stack != null && !stack.itemStack.isEmpty() && information.add(new TransportInformation(stack))) {
                // Ensure the stack is valid AND we did not already have information matching the stack
                // We use add to check if it already contained the value, so that we only have to query the set once
                reducedTransit.add(stack);
            }
        }
        return reducedTransit;
    }

    private static class TransportInformation {

        @javax.annotation.Nullable
        private final EnumColor color;
        private final HashedItem item;
        private final int progress;

        private TransportInformation(TransporterStack transporterStack) {
            this.progress = transporterStack.progress;
            this.color = transporterStack.color;
            this.item = HashedItem.create(transporterStack.itemStack);
        }

        @Override
        public int hashCode() {
            int code = 1;
            code = 31 * code + progress;
            code = 31 * code + item.hashCode();
            if (color != null) {
                code = 31 * code + color.hashCode();
            }
            return code;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            return obj instanceof TransportInformation other && progress == other.progress && color == other.color && item.equals(other.item);
        }
    }

    private static class LazyItemRenderer {

        @javax.annotation.Nullable
        private ItemEntity entityItem;
        @Nullable
        private EntityRenderer<? super ItemEntity> renderer;

        public void init(Level world, BlockPos pos) {
            if (entityItem == null) {
                entityItem = new ItemEntity(EntityType.ITEM, world);
            } else {
                entityItem.setLevel(world);
            }
            entityItem.setPos(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
            entityItem.age = 0;
        }

        private void renderAsStack(PoseStack matrix, MultiBufferSource buffer, ItemStack stack, int light) {
            if (entityItem != null) {
                if (renderer == null) {
                    renderer = Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(entityItem);
                }
                entityItem.setItem(stack);
                renderer.render(entityItem, 0, 0, matrix, buffer, light);
            }
        }
    }
}
