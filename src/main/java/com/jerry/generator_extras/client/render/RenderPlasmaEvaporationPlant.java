package com.jerry.generator_extras.client.render;

import com.jerry.generator_extras.common.content.plasma.PlasmaEvaporationMultiblockData;
import com.jerry.generator_extras.common.tile.plasma.TileEntityPlasmaEvaporationController;

import mekanism.api.annotations.NothingNullByDefault;
import mekanism.client.render.data.FluidRenderData;
import mekanism.client.render.data.RenderData;
import mekanism.client.render.tileentity.MultiblockTileEntityRenderer;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.phys.Vec3;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

@NothingNullByDefault
public class RenderPlasmaEvaporationPlant extends MultiblockTileEntityRenderer<PlasmaEvaporationMultiblockData, TileEntityPlasmaEvaporationController> {

    public RenderPlasmaEvaporationPlant(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected boolean shouldRender(TileEntityPlasmaEvaporationController tile, PlasmaEvaporationMultiblockData multiblock, Vec3 camera) {
        return super.shouldRender(tile, multiblock, camera) && !(multiblock.inputTank.isEmpty() && multiblock.plasmaInputTank.isEmpty());
    }

    @Override
    protected void render(TileEntityPlasmaEvaporationController tile, PlasmaEvaporationMultiblockData multiblock, float partialTick,
                          PoseStack matrix, MultiBufferSource renderer, int light, int overlayLight, ProfilerFiller profiler) {
        VertexConsumer buffer = renderer.getBuffer(Sheets.translucentCullBlockSheet());
        if (!multiblock.inputTank.isEmpty()) {
            FluidRenderData data = RenderData.Builder.create(multiblock.inputTank.getFluid())
                    .location(multiblock.renderLocation.offset(1, 0, 1))
                    .dimensions(4, multiblock.lowerHeight(), 4)
                    .build();
            renderObject(data, multiblock.valves, tile.getBlockPos(), matrix, buffer, overlayLight, Math.min(1, multiblock.prevScale));
        }
        if (!multiblock.plasmaInputTank.isEmpty()) {
            RenderData data = RenderData.Builder.create(multiblock.plasmaInputTank.getStack())
                    .location(multiblock.renderLocation.offset(1, 0, 1)
                            .atY(multiblock.insulationLayerY + 1))
                    .dimensions(4, multiblock.higherHeight(), 4)
                    .build();
            renderObject(data, multiblock.valves, tile.getBlockPos(), matrix, buffer, overlayLight, Math.min(1, multiblock.prevScale));
        }
    }

    @Override
    protected String getProfilerSection() {
        return "plasmaEvaporationPlant";
    }
}
