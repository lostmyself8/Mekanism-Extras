package com.jerry.mekanism_extras.lib;

import mekanism.api.annotations.NothingNullByDefault;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;

import java.util.List;

/**
 * Represents an NBT structure file.
 *
 * @param blocks all the blocks or block entities in the structure
 * @param entities all the entities (except block entities) in the structure
 * @param palette the set of all the block states
 * @param size the size in X, Y, Z axis
 */
public record NBTStructureFile(
        List<CompoundTag> blocks,
        List<CompoundTag> entities,
        List<CompoundTag> palette,
        int[] size
) {

    /**
     * Converts a compound tag into an NBT structure file.
     * @param nbt the compound tag
     * @return the NBT structure file
     */
    public static NBTStructureFile fromNBTTag(CompoundTag nbt) {
        return new NBTStructureFile(
                nbt.getList("blocks", Tag.TAG_COMPOUND).stream().map(tag -> (CompoundTag)tag).toList(),
                nbt.getList("entities", Tag.TAG_COMPOUND).stream().map(tag -> (CompoundTag)tag).toList(),
                nbt.getList("palette", Tag.TAG_COMPOUND).stream().map(tag -> (CompoundTag)tag).toList(),
                nbt.getIntArray("size")
        );
    }

    /**
     * Gets the block information with the relative position.
     * @param x the relative X coordination
     * @param y the relative Y coordination
     * @param z the relative Z coordination
     * @return the block information
     * @throws IndexOutOfBoundsException if any of <code>x</code>, <code>y</code> and <code>z</code> is out of the size
     */
    @NothingNullByDefault
    public BlockInfo getBlockInfo(int x, int y, int z) throws IndexOutOfBoundsException {
        if ((x > size[0]-1) || (y > size[1]-1) || (z > size[2]-1)) throw new IndexOutOfBoundsException();

        CompoundTag blockToGet = null;
        for (CompoundTag b : blocks) {
            ListTag pos = b.getList("pos", Tag.TAG_INT_ARRAY);
            int x0 = pos.getInt(0);
            int y0 = pos.getInt(1);
            int z0 = pos.getInt(2);

            if (x == x0 && y == y0 && z == z0) {
                blockToGet = b;
                break;
            }
        }
        return new BlockInfo(blockToGet.getCompound("nbt"), palette.get(blockToGet.getInt("palette")));
    }

}
