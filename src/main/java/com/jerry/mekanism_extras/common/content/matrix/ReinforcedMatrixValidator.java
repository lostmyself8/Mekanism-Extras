package com.jerry.mekanism_extras.common.content.matrix;

import com.jerry.mekanism_extras.common.registry.ExtraBlockTypes;
import com.jerry.mekanism_extras.common.tile.multiblock.ExtraTileEntityInductionCell;
import com.jerry.mekanism_extras.common.tile.multiblock.ExtraTileEntityInductionProvider;
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

public class ReinforcedMatrixValidator extends CuboidStructureValidator<ReinforcedMatrixMultiblockData> {

    private final List<ExtraTileEntityInductionCell> cells = new ArrayList<>();
    private final List<ExtraTileEntityInductionProvider> providers = new ArrayList<>();

    @Override
    protected FormationProtocol.CasingType getCasingType(BlockState state) {
        Block block = state.getBlock();
        if (BlockType.is(block, ExtraBlockTypes.REINFORCED_INDUCTION_CASING)) {
            return FormationProtocol.CasingType.FRAME;
        } else if (BlockType.is(block, ExtraBlockTypes.REINFORCED_INDUCTION_PORT)) {
            return FormationProtocol.CasingType.VALVE;
        }
        return FormationProtocol.CasingType.INVALID;
    }

    @Override
    public boolean validateInner(BlockState state, Long2ObjectMap<ChunkAccess> chunkMap, BlockPos pos) {
        if (super.validateInner(state, chunkMap, pos)) {
            return true;
        }
        if (BlockType.is(state.getBlock(), ExtraBlockTypes.ABSOLUTE_INDUCTION_CELL, ExtraBlockTypes.SUPREME_INDUCTION_CELL,
                ExtraBlockTypes.COSMIC_INDUCTION_CELL, ExtraBlockTypes.INFINITE_INDUCTION_CELL, ExtraBlockTypes.ABSOLUTE_INDUCTION_PROVIDER,
                ExtraBlockTypes.SUPREME_INDUCTION_PROVIDER, ExtraBlockTypes.COSMIC_INDUCTION_PROVIDER, ExtraBlockTypes.INFINITE_INDUCTION_PROVIDER)) {
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
    public FormationProtocol.FormationResult postcheck(ReinforcedMatrixMultiblockData structure, Long2ObjectMap<ChunkAccess> chunkMap) {
        for (ExtraTileEntityInductionCell cell : cells) {
            structure.addCell(cell);
        }
        for (ExtraTileEntityInductionProvider provider : providers) {
            structure.addProvider(provider);
        }
        return FormationProtocol.FormationResult.SUCCESS;
    }
}
