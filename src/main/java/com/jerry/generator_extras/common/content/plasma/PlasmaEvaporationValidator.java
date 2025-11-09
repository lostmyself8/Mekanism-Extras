package com.jerry.generator_extras.common.content.plasma;

import com.jerry.generator_extras.common.ExtraGenLang;
import com.jerry.generator_extras.common.content.plasma.PlasmaEvaporationMultiblockData.VentData;
import com.jerry.generator_extras.common.genregistry.ExtraGenBlockTypes;
import com.jerry.generator_extras.common.tile.plasma.TileEntityPlasmaEvaporationController;
import com.jerry.generator_extras.common.tile.plasma.TileEntityPlasmaEvaporationVent;
import com.jerry.generator_extras.common.tile.plasma.TileEntityPlasmaInsulationLayer;

import mekanism.common.MekanismLang;
import mekanism.common.content.blocktype.BlockType;
import mekanism.common.lib.math.voxel.VoxelCuboid;
import mekanism.common.lib.multiblock.CuboidStructureValidator;
import mekanism.common.lib.multiblock.FormationProtocol;
import mekanism.common.lib.multiblock.FormationProtocol.CasingType;
import mekanism.common.lib.multiblock.FormationProtocol.FormationResult;
import mekanism.common.lib.multiblock.StructureHelper;
import mekanism.common.util.WorldUtils;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;

import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PlasmaEvaporationValidator extends CuboidStructureValidator<PlasmaEvaporationMultiblockData> {

    private static final VoxelCuboid MIN_CUBOID = new VoxelCuboid(6, 8, 6);
    private static final VoxelCuboid MAX_CUBOID = new VoxelCuboid(6, 36, 6);

    private boolean foundController = false;

    @Override
    protected boolean validateInner(BlockState state, Long2ObjectMap<ChunkAccess> chunkMap, BlockPos pos) {
        return super.validateInner(state, chunkMap, pos) ||
                BlockType.is(state.getBlock(), ExtraGenBlockTypes.PLASMA_INSULATION_LAYER);
    }

    @Override
    protected CasingType getCasingType(BlockState state) {
        Block block = state.getBlock();
        if (BlockType.is(block, ExtraGenBlockTypes.PLASMA_EVAPORATION_BLOCK)) {
            return CasingType.FRAME;
        } else if (BlockType.is(block, ExtraGenBlockTypes.PLASMA_EVAPORATION_VALVE)) {
            return CasingType.VALVE;
        } else if (BlockType.is(block, ExtraGenBlockTypes.PLASMA_EVAPORATION_CONTROLLER,
                ExtraGenBlockTypes.PLASMA_EVAPORATION_VENT)) {
                    return CasingType.OTHER;
                }
        return CasingType.INVALID;
    }

    @Override
    public boolean precheck() {
        return (cuboid = StructureHelper.fetchCuboid(structure, MIN_CUBOID, MAX_CUBOID)) != null;
    }

    @Override
    public FormationResult postcheck(PlasmaEvaporationMultiblockData structure, Long2ObjectMap<ChunkAccess> chunkMap) {
        if (!foundController) return FormationResult.fail(MekanismLang.MULTIBLOCK_INVALID_NO_CONTROLLER);

        Set<Integer> insulationLayers = new ObjectOpenHashSet<>();
        int minLayer = structure.getMinPos().getY() + 2;
        int maxLayer = structure.getMaxPos().getY() - 2;
        int layers = 0;
        // Scan for insulation layers
        for (BlockPos pos : structure.internalLocations) {
            BlockEntity tile = WorldUtils.getTileEntity(world, chunkMap, pos);
            if (tile instanceof TileEntityPlasmaInsulationLayer) {
                insulationLayers.add(pos.getY());
                layers += 1;
            }
        }
        if (insulationLayers.size() != 1 || layers != 16) {
            return FormationResult.fail(ExtraGenLang.PLASMA_BAD_INSULATION_LAYER);
        }

        int lowerVolume, higherVolume, insulationLayerY;
        // Only passes if the layer is not close to the bottom and the top and
        // is a flat layer (all the Y coord should be the same)
        if (insulationLayers.stream()
                .allMatch(y -> y >= minLayer && y <= maxLayer)) {
            insulationLayerY = insulationLayers.stream().findFirst().get();
            lowerVolume = structure.length() * structure.width() *
                    (insulationLayerY - structure.getMinPos().getY() - 1);
            higherVolume = structure.length() * structure.width() *
                    (structure.getMaxPos().getY() - insulationLayerY - 1);
        } else {
            return FormationResult.fail(ExtraGenLang.PLASMA_BAD_INSULATION_LAYER);
        }

        // Scan for vents
        List<VentData> ventData = new ArrayList<>();
        for (BlockPos pos : structure.locations) {
            if (WorldUtils.getTileEntity(TileEntityPlasmaEvaporationVent.class, world, chunkMap, pos) != null) {
                if (pos.getY() != structure.getMaxPos().getY()) {
                    // Got a vent not at the top
                    return FormationResult.fail(ExtraGenLang.PLASMA_VENT_NOT_AT_TOP);
                }
                ventData.add(new VentData(pos, getSide(pos)));
            }
        }
        if (ventData.size() != (structure.width() - 2) * (structure.length() - 2)) {
            // The top frames are not fulfilled with vents
            return FormationResult.fail(ExtraGenLang.PLASMA_VENTS_NOT_FULFILL_TOP);
        }

        // No problems, update the multiblock data
        // TODO: It's really strange that these 3 attributes can't be synced. Why?
        // structure.insulationLayerY = insulationLayerY;
        // structure.lowerVolume = lowerVolume;
        // structure.higherVolume = higherVolume;
        structure.updateVentData(ventData);
        return FormationResult.SUCCESS;
    }

    @Override
    protected FormationResult validateFrame(FormationProtocol<PlasmaEvaporationMultiblockData> ctx, BlockPos pos, BlockState state, CasingType type, boolean needsFrame) {
        // Check duplicate controller
        boolean controller = structure.getTile(pos) instanceof TileEntityPlasmaEvaporationController;
        if (foundController && controller) {
            return FormationResult.fail(MekanismLang.MULTIBLOCK_INVALID_CONTROLLER_CONFLICT, pos, true);
        }
        foundController |= controller;
        return super.validateFrame(ctx, pos, state, type, needsFrame);
    }
}
