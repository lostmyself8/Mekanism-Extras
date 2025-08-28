package com.jerry.generator_extras.common.content.plasma;

import mekanism.common.lib.multiblock.CuboidStructureValidator;
import mekanism.common.lib.multiblock.FormationProtocol;
import net.minecraft.world.level.block.state.BlockState;

public class PlasmaEvaporationValidator extends CuboidStructureValidator<PlasmaEvaporationMultiblockData> {
    @Override
    protected FormationProtocol.CasingType getCasingType(BlockState state) {
        return null;
    }
}
