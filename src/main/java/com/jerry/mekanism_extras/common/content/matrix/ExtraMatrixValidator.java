package com.jerry.mekanism_extras.common.content.matrix;

import com.jerry.mekanism_extras.common.tile.multiblock.cell.ExtraTileEntityInductionCell;
import com.jerry.mekanism_extras.common.tile.multiblock.provider.ExtraTileEntityInductionProvider;
import com.jerry.mekanism_extras.registery.ExtraBlockType;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import mekanism.common.content.blocktype.BlockType;
import mekanism.common.lib.multiblock.CuboidStructureValidator;
import mekanism.common.lib.multiblock.FormationProtocol;
import mekanism.common.util.WorldUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;

import java.util.ArrayList;
import java.util.List;

public class ExtraMatrixValidator extends CuboidStructureValidator<ExtraMatrixMultiblockData> {
    private final List<ExtraTileEntityInductionCell> cells = new ArrayList<>();
    private final List<ExtraTileEntityInductionProvider> providers = new ArrayList<>();

    @Override
    protected FormationProtocol.CasingType getCasingType(BlockState state) {
        Block block = state.getBlock();
        if (BlockType.is(block, ExtraBlockType.INDUCTION_CASING)) {
            return FormationProtocol.CasingType.FRAME;
        } else if (BlockType.is(block, ExtraBlockType.INDUCTION_PORT)) {
            return FormationProtocol.CasingType.VALVE;
        }
        return FormationProtocol.CasingType.INVALID;
    }
    @Override
    public boolean validateInner(BlockState state, Long2ObjectMap<ChunkAccess> chunkMap, BlockPos pos) {
        if (super.validateInner(state, chunkMap, pos)) {
            return true;
        }
        if (BlockType.is(state.getBlock(), ExtraBlockType.ABSOLUTE_INDUCTION_CELL, ExtraBlockType.SUPREME_INDUCTION_CELL,
                ExtraBlockType.COSMIC_INDUCTION_CELL, ExtraBlockType.INFINITE_INDUCTION_CELL, ExtraBlockType.ABSOLUTE_INDUCTION_PROVIDER,
                ExtraBlockType.SUPREME_INDUCTION_PROVIDER, ExtraBlockType.COSMIC_INDUCTION_PROVIDER, ExtraBlockType.INFINITE_INDUCTION_PROVIDER)) {
            //Compare blocks against the type before bothering to look up the tile
            BlockEntity tile = WorldUtils.getTileEntity(world, chunkMap, pos);
            if (tile instanceof ExtraTileEntityInductionCell cell) {
                cells.add(cell);
                return true;
            } else if (tile instanceof ExtraTileEntityInductionProvider provider) {
                providers.add(provider);
                return true;
            }
            //Else something went wrong
        }
        return false;
    }

    @Override
    public FormationProtocol.FormationResult postcheck(ExtraMatrixMultiblockData structure, Long2ObjectMap<ChunkAccess> chunkMap) {
        cells.forEach(structure::addCell);
        providers.forEach(structure::addProvider);
        return FormationProtocol.FormationResult.SUCCESS;
    }
}
