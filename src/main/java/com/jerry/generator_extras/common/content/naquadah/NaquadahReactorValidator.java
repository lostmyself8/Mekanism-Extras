package com.jerry.generator_extras.common.content.naquadah;

import com.jerry.generator_extras.common.genregistry.ExtraGenBlockTypes;
import com.jerry.generator_extras.common.tile.TileEntityLeadCoatedGlass;
import com.jerry.generator_extras.common.tile.naquadah.TileEntityNaquadahReactorController;

import mekanism.common.MekanismLang;
import mekanism.common.content.blocktype.BlockType;
import mekanism.common.lib.math.voxel.VoxelCuboid;
import mekanism.common.lib.multiblock.*;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.EnumSet;

public class NaquadahReactorValidator extends CuboidStructureValidator<NaquadahReactorMultiblockData> {

    private static final VoxelCuboid BOUNDS = new VoxelCuboid(9, 9, 9);
    private static final byte[][] ALLOWED_GRID = {
            { 0, 0, 0, 1, 1, 1, 0, 0, 0 },
            { 0, 1, 1, 2, 2, 2, 1, 1, 0 },
            { 0, 1, 2, 2, 2, 2, 2, 1, 0 },
            { 1, 2, 2, 2, 2, 2, 2, 2, 1 },
            { 1, 2, 2, 2, 2, 2, 2, 2, 1 },
            { 1, 2, 2, 2, 2, 2, 2, 2, 1 },
            { 0, 1, 2, 2, 2, 2, 2, 1, 0 },
            { 0, 1, 1, 2, 2, 2, 1, 1, 0 },
            { 0, 0, 0, 1, 1, 1, 0, 0, 0 }
    };

    @Override
    protected FormationProtocol.StructureRequirement getStructureRequirement(BlockPos pos) {
        VoxelCuboid.WallRelative relative = cuboid.getWallRelative(pos);
        if (relative.isWall()) {
            Structure.Axis axis = Structure.Axis.get(cuboid.getSide(pos));
            Structure.Axis h = axis.horizontal(), v = axis.vertical();
            pos = pos.subtract(cuboid.getMinPos());
            return FormationProtocol.StructureRequirement.REQUIREMENTS[ALLOWED_GRID[h.getCoord(pos)][v.getCoord(pos)]];
        }
        return super.getStructureRequirement(pos);
    }

    @Override
    protected FormationProtocol.FormationResult validateFrame(FormationProtocol<NaquadahReactorMultiblockData> ctx, BlockPos pos, BlockState state, FormationProtocol.CasingType type, boolean needsFrame) {
        boolean isControllerPos = pos.getY() == cuboid.getMaxPos().getY() && pos.getX() == cuboid.getMinPos().getX() + 4 && pos.getZ() == cuboid.getMinPos().getZ() + 4;
        boolean controller = structure.getTile(pos) instanceof TileEntityNaquadahReactorController;
        if (isControllerPos && !controller) {
            return FormationProtocol.FormationResult.fail(MekanismLang.MULTIBLOCK_INVALID_NO_CONTROLLER);
        } else if (!isControllerPos && controller) {
            return FormationProtocol.FormationResult.fail(MekanismLang.MULTIBLOCK_INVALID_CONTROLLER_CONFLICT, true);
        }
        return super.validateFrame(ctx, pos, state, type, needsFrame);
    }

    @Override
    protected FormationProtocol.CasingType getCasingType(BlockState state) {
        Block block = state.getBlock();
        if (BlockType.is(block, ExtraGenBlockTypes.NAQUADAH_REACTOR_CASING)) {
            return FormationProtocol.CasingType.FRAME;
        } else if (BlockType.is(block, ExtraGenBlockTypes.NAQUADAH_REACTOR_PORT)) {
            return FormationProtocol.CasingType.VALVE;
        } else if (BlockType.is(block, ExtraGenBlockTypes.NAQUADAH_REACTOR_CONTROLLER,
                ExtraGenBlockTypes.NAQUADAH_REACTOR_LOGIC_ADAPTER, ExtraGenBlockTypes.LEAD_COATED_LASER_FOCUS_MATRIX)) {
                    return FormationProtocol.CasingType.OTHER;
                }
        return FormationProtocol.CasingType.INVALID;
    }

    @Override
    public boolean precheck() {
        cuboid = StructureHelper.fetchCuboid(structure, BOUNDS, BOUNDS, EnumSet.allOf(VoxelCuboid.CuboidSide.class), 120);
        return cuboid != null;
    }

    @Override
    protected boolean isFrameCompatible(BlockEntity tile) {
        if (tile instanceof TileEntityLeadCoatedGlass glass && glass.canInterface(manager)) {
            return true;
        }
        return manager.isCompatible(tile);
    }
}
