package com.jerry.mekanism_extras.lib;

import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import mekanism.common.lib.math.voxel.IShape;
import mekanism.common.lib.multiblock.*;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtIo;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.ChunkAccess;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * This validator validates a multiblock structure with a given NBT structure file. However, it's hard or even impossible
 * to know where the block is put exactly. If it validates by trying suiting the block in every possible position, like
 * the SPS, it will greatly make TPS decrease. So, all validators who extend this should provide a few validating positions.
 * By specifying the validating positions, the validator will put the relative (0, 0, 0) point in the structure in those
 * positions, then the validator only need to validate the four rotations at each position. That's pretty faster than
 * SPS does!
 * @param <T> multiblock data type
 */
public class SpecificNBTStructureValidator<T extends MultiblockData> implements IStructureValidator<T> {
    protected NBTStructureFile structureFile;

    protected Level world;
    protected MultiblockManager<T> manager;
    protected Structure structure;

    public SpecificNBTStructureValidator(String schematicFileName) throws IOException, NullPointerException {
        File schematic = new File("schematics/" + schematicFileName);
        CompoundTag structureNBT = NbtIo.read(schematic);

        if (schematicFileName.endsWith(".nbt")) {
            if (structureNBT != null) {
                structureFile = NBTStructureFile.fromNBTTag(structureNBT);
            } else throw new NullPointerException();
        }
    }

    @Override
    public void init(Level world, MultiblockManager<T> manager, Structure structure) {
        this.world = world;
        this.manager = manager;
        this.structure = structure;
    }

    @Override
    public boolean precheck() {
        return true;
    }

    @Override
    public FormationProtocol.FormationResult validate(FormationProtocol<T> ctx, Long2ObjectMap<ChunkAccess> chunkMap) {
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        for (int x = 0; x < structureFile.size()[0]; x++) {
            for (int y = 0; y < structureFile.size()[1]; y++) {
                for (int z = 0; z < structureFile.size()[2]; z++) {
                    pos.set(x, y, z);

                }
            }
        }
        return null;
    }

    @Override
    public FormationProtocol.FormationResult postcheck(T structure, Long2ObjectMap<ChunkAccess> chunkMap) {
        return null;
    }

    @Override
    public IShape getShape() {
        return null;
    }
}
