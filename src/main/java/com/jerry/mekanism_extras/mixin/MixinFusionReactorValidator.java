package com.jerry.mekanism_extras.mixin;

import com.jerry.generator_extras.common.genregistry.ExtraGenBlockTypes;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import mekanism.common.content.blocktype.BlockType;
import mekanism.common.lib.multiblock.CuboidStructureValidator;
import mekanism.common.lib.multiblock.FormationProtocol.CasingType;
import mekanism.common.lib.multiblock.FormationProtocol.FormationResult;
import mekanism.common.util.WorldUtils;
import mekanism.generators.common.content.fusion.FusionReactorMultiblockData;
import mekanism.generators.common.content.fusion.FusionReactorValidator;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = FusionReactorValidator.class, remap = false)
public abstract class MixinFusionReactorValidator extends CuboidStructureValidator<FusionReactorMultiblockData> {

    @Inject(method = "getCasingType", at = @At(value = "TAIL"), cancellable = true)
    private void meke$customCasing(BlockState state, CallbackInfoReturnable<CasingType> cir) {
        if (BlockType.is(state.getBlock(), ExtraGenBlockTypes.FUSION_REACTOR_PLASMA_EXTRACTING_PORT)) {
            cir.setReturnValue(CasingType.OTHER);
        }
    }

    @Override
    public FormationResult postcheck(FusionReactorMultiblockData structure, Long2ObjectMap<ChunkAccess> chunkMap) {
        if (structure.locations.stream().anyMatch(pos -> BlockType.is(WorldUtils.getBlockState(world, pos).get().getBlock(),
                ExtraGenBlockTypes.FUSION_REACTOR_PLASMA_EXTRACTING_PORT))) {
            ((IFusionPlasmaHolder)structure).setOutputPlasma(true);
        }
        return super.postcheck(structure, chunkMap);
    }
}
