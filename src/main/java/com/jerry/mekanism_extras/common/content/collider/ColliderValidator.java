package com.jerry.mekanism_extras.common.content.collider;

import com.jerry.mekanism_extras.registry.ExtraBlockType;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import mekanism.common.content.blocktype.BlockType;
import mekanism.common.lib.math.voxel.VoxelCuboid;
import mekanism.common.lib.multiblock.*;
import mekanism.common.registries.MekanismBlockTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;

import java.util.EnumSet;

public class ColliderValidator extends CuboidStructureValidator<ColliderMultiblockData> {

//    private static final VoxelCuboid BOUNDS = new VoxelCuboid(13, 3, 13);
        private static final VoxelCuboid BOUNDS = new VoxelCuboid(4, 4, 4);
    private static final byte[][] ALLOWED_GRID_1 = {
            {0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0}
    };
    private static final byte[][] ALLOWED_GRID_2 = {
            {0, 0, 0, 0, 0, 0, 2, 2, 2, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 0},
            {0, 0, 1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 0, 0},
            {0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0},
            {2, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 2},
            {2, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 2},
            {2, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 2},
            {0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 0, 0},
            {0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 2, 2, 2, 0, 0, 0, 0, 0, 0}
    };

    private static final byte[][] ALLOWED_GRID = {
            {0, 1, 1, 0},
            {1, 2, 2, 1},
            {1, 2, 2, 1},
            {0, 1, 1, 0}
    };

    @Override
    protected FormationProtocol.StructureRequirement getStructureRequirement(BlockPos pos) {
//        VoxelCuboid.WallRelative relative = cuboid.getWallRelative(pos);
//        if (relative.isWall()) {
//            Structure.Axis axis = Structure.Axis.get(cuboid.getSide(pos));
//            Structure.Axis h = axis.horizontal(), v = axis.vertical();
//            //Note: This ends up becoming immutable by doing this but that is fine and doesn't really matter
//            pos = pos.subtract(cuboid.getMinPos());
//            return pos.getY() == cuboid.getMaxPos().getY() || pos.getY() == cuboid.getMinPos().getY() ?
//                    FormationProtocol.StructureRequirement.REQUIREMENTS[ALLOWED_GRID_1[h.getCoord(pos)][v.getCoord(pos)]] :
//                    FormationProtocol.StructureRequirement.REQUIREMENTS[ALLOWED_GRID_2[h.getCoord(pos)][v.getCoord(pos)]];
//            if (pos.getY() == 0) {
//                return FormationProtocol.StructureRequirement.REQUIREMENTS[ALLOWED_GRID_1[h.getCoord(pos)][v.getCoord(pos)]];
//            }
//        }
//        return super.getStructureRequirement(pos);
        VoxelCuboid.WallRelative relative = cuboid.getWallRelative(pos);
        if (relative.isWall()) {
            Structure.Axis axis = Structure.Axis.get(cuboid.getSide(pos));
            Structure.Axis h = axis.horizontal(), v = axis.vertical();
            //Note: This ends up becoming immutable by doing this but that is fine and doesn't really matter
            pos = pos.subtract(cuboid.getMinPos());
            return FormationProtocol.StructureRequirement.REQUIREMENTS[ALLOWED_GRID[h.getCoord(pos)][v.getCoord(pos)]];
        }
//        VoxelCuboid.WallRelative relative = cuboid.getWallRelative(pos);
//        if (pos.getY() == cuboid.getMaxPos().getY()) {
//            if (relative.isOnCorner()) {
////                return FormationProtocol.StructureRequirement.IGNORED;
//            } else if (!relative.isOnEdge()) {
//                return FormationProtocol.StructureRequirement.INNER;
//            } else {
//                return FormationProtocol.StructureRequirement.OTHER;
//            }
//        }
        return super.getStructureRequirement(pos);
    }

//    @Override
//    protected FormationProtocol.FormationResult validateFrame(FormationProtocol<ColliderMultiblockData> ctx, BlockPos pos, BlockState state, FormationProtocol.CasingType type, boolean needsFrame) {
//        return super.validateFrame(ctx, pos, state, type, needsFrame);
//    }

    @Override
    protected FormationProtocol.CasingType getCasingType(BlockState state) {
        Block block = state.getBlock();
        if (BlockType.is(block, ExtraBlockType.COLLIDER_CASING)) {
            return FormationProtocol.CasingType.FRAME;
        }
        return FormationProtocol.CasingType.INVALID;
//        Block block = state.getBlock();
//        if (BlockType.is(block, MekanismBlockTypes.SPS_CASING)) {
//            return FormationProtocol.CasingType.FRAME;
//        } else if (BlockType.is(block, MekanismBlockTypes.SPS_PORT)) {
//            return FormationProtocol.CasingType.VALVE;
//        }
//        return FormationProtocol.CasingType.INVALID;
    }

    @Override
    public boolean precheck() {
        cuboid = StructureHelper.fetchCuboid(structure, BOUNDS, BOUNDS, EnumSet.complementOf(EnumSet.of(VoxelCuboid.CuboidSide.TOP)), 24);
        return cuboid != null;
    }

    @Override
    public FormationProtocol.FormationResult postcheck(ColliderMultiblockData structure, Long2ObjectMap<ChunkAccess> chunkMap) {
        return super.postcheck(structure, chunkMap);
    }

    @Override
    protected boolean validateInner(BlockState state, Long2ObjectMap<ChunkAccess> chunkMap, BlockPos pos) {
        if (super.validateInner(state, chunkMap, pos)) {
            return true;
        }
        return BlockType.is(state.getBlock(), MekanismBlockTypes.SUPERCHARGED_COIL);
    }

//    @Override
//    public FormationProtocol.FormationResult postcheck(ColliderMultiblockData structure, Long2ObjectMap<ChunkAccess> chunkMap) {
//        Set<BlockPos> validCoils = new ObjectOpenHashSet<>();
//        for (IValveHandler.ValveData valve : structure.valves) {
//            BlockPos pos = valve.location.relative(valve.side.getOpposite());
//            if (structure.internalLocations.contains(pos)) {
//                structure.addCoil(valve.location, valve.side.getOpposite());
//                validCoils.add(pos);
//            }
//        }
//        // fail if there's a coil not connected to a port
//        // Note: As we only support coils as internal multiblocks for the SPS we can just compare the size of the sets
//        if (structure.internalLocations.size() != validCoils.size()) {
//            return FormationProtocol.FormationResult.fail(MekanismLang.SPS_INVALID_DISCONNECTED_COIL);
//        }
//        return FormationProtocol.FormationResult.SUCCESS;
//    }
}
