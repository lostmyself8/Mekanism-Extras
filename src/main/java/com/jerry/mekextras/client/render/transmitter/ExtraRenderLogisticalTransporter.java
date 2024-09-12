package com.jerry.mekextras.client.render.transmitter;

import com.jerry.mekextras.common.content.network.transmitter.ExtraLogisticalTransporterBase;
import com.jerry.mekextras.common.tier.TPTier;
import com.jerry.mekextras.common.tile.transmitter.ExtraTileEntityLogisticalTransporterBase;
import com.mojang.blaze3d.vertex.PoseStack;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import mekanism.api.text.EnumColor;
import mekanism.client.model.ModelTransporterBox;
import mekanism.client.render.MekanismRenderer;
import mekanism.client.render.transmitter.RenderTransmitterBase;
import mekanism.common.base.ProfilerConstants;
import mekanism.common.config.MekanismConfig;
import mekanism.common.content.network.transmitter.DiversionTransporter;
import mekanism.common.content.transporter.TransporterStack;
import mekanism.common.lib.inventory.HashedItem;
import mekanism.common.util.EnumUtils;
import mekanism.common.util.TransporterUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class ExtraRenderLogisticalTransporter extends RenderTransmitterBase<ExtraTileEntityLogisticalTransporterBase> {

    private static final Map<Direction, MekanismRenderer.Model3D> cachedOverlays = new EnumMap<>(Direction.class);
    private static final int DIVERSION_OVERLAY_ARGB = MekanismRenderer.getColorARGB(255, 255, 255, 0.8F);
    @Nullable
    private static TextureAtlasSprite gunpowderIcon;
    @Nullable
    private static TextureAtlasSprite torchOffIcon;
    @Nullable
    private static TextureAtlasSprite torchOnIcon;
    private final ModelTransporterBox modelBox;
    private final ExtraRenderLogisticalTransporter.LazyItemRenderer itemRenderer = new ExtraRenderLogisticalTransporter.LazyItemRenderer();

    public ExtraRenderLogisticalTransporter(BlockEntityRendererProvider.Context context) {
        super(context);
        modelBox = new ModelTransporterBox(context.getModelSet());
    }

    @Override
    protected void render(@NotNull ExtraTileEntityLogisticalTransporterBase tile, float partialTick, @NotNull PoseStack matrix, @NotNull MultiBufferSource renderer, int light, int overlayLight, @NotNull ProfilerFiller profiler) {
        ExtraLogisticalTransporterBase transporter = tile.getTransmitter();
        BlockPos pos = tile.getBlockPos();
        if (!MekanismConfig.client.opaqueTransmitters.get()) {
            Collection<TransporterStack> inTransit = transporter.getTransit();
            if (!inTransit.isEmpty()) {
                matrix.pushPose();
                itemRenderer.init(tile.getLevel(), pos);

                float partial = partialTick * TPTier.getSpeed(transporter.tier);
                Collection<TransporterStack> reducedTransit = getReducedTransit(inTransit);
                for (TransporterStack stack : reducedTransit) {
                    float[] stackPos = TransporterUtils.getStackPosition(transporter, stack, partial);
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
    }

    @Override
    protected @NotNull String getProfilerSection() {
        return ProfilerConstants.LOGISTICAL_TRANSPORTER;
    }

    @Override
    protected boolean shouldRenderTransmitter(@NotNull ExtraTileEntityLogisticalTransporterBase tile, @NotNull Vec3 camera) {
        return super.shouldRenderTransmitter(tile, camera);
    }
    private Collection<TransporterStack> getReducedTransit(Collection<TransporterStack> inTransit) {
        Collection<TransporterStack> reducedTransit = new ArrayList<>();
        Set<ExtraRenderLogisticalTransporter.TransportInformation> information = new ObjectOpenHashSet<>();
        for (TransporterStack stack : inTransit) {
            if (stack != null && !stack.itemStack.isEmpty() && information.add(new ExtraRenderLogisticalTransporter.TransportInformation(stack))) {
                reducedTransit.add(stack);
            }
        }
        return reducedTransit;
    }

    private MekanismRenderer.Model3D getOverlayModel(DiversionTransporter transporter, Direction side) {
        MekanismRenderer.Model3D model = cachedOverlays.computeIfAbsent(side, face -> {
            MekanismRenderer.Model3D model3D = new MekanismRenderer.Model3D().prepSingleFaceModelSize(face);
            for (Direction direction : EnumUtils.DIRECTIONS) {
                model3D.setSideRender(direction, direction == face);
            }
            return model3D;
        });
        return model.setTexture(side, switch (transporter.modes[side.ordinal()]) {
            case DISABLED -> gunpowderIcon;
            case HIGH -> torchOnIcon;
            case LOW -> torchOffIcon;
        });
    }

    private static class TransportInformation {

        @Nullable
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
            return obj instanceof ExtraRenderLogisticalTransporter.TransportInformation other && progress == other.progress && color == other.color && item.equals(other.item);
        }
    }

    private static class LazyItemRenderer {

        @Nullable
        private ItemEntity entityItem;
        @Nullable
        private EntityRenderer<? super ItemEntity> renderer;

        public void init(Level world, BlockPos pos) {
            if (entityItem == null) {
                entityItem = new ItemEntity(EntityType.ITEM, world);
            } else {
//                entityItem.setLevel(world);
            }
            entityItem.setPos(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
//            entityItem.age = 0;
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
