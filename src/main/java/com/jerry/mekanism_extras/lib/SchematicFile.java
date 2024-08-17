package com.jerry.mekanism_extras.lib;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;

import java.util.List;

/**
 * Represents a .schematic structure file. The block data is sorted in Y, Z and X order, similar to NBT.
 *
 * @param width size in X axis
 * @param height size in Y axis
 * @param length size in Z axis
 * @param blocks all the block ids in the structure, each block is represented by a byte. This is sorted in Y, Z and X
 *               axis order, in other words, the index of the block with coordination (x,y,z) is <code>(y * length + z) * width + x</code>.
 * @param addBlocks extra bits that can be used to further define terrain; optional. Two nibbles are put into each index
 *                  in this array. Unlike normal chunks, even indexes go on the high nibble and odd indexes go on the low nibble.
 * @param data block data additionally defining parts of the terrain. Only the lower 4 bits of each byte are used.
 * @param entities list of compound tags of entities
 * @param tileEntities list of compound tags of tile entities (block entities)
 */
public record SchematicFile(
        short width,
        short height,
        short length,
        byte[] blocks,
        byte[] addBlocks,
        byte[] data,
        List<CompoundTag> entities,
        List<CompoundTag> tileEntities
) {
    /**
     * Converts an NBT tag structure into a schematic one.
     * @param nbt the NBT tag structure
     * @return the schematic structure
     */
    public static SchematicFile fromSchematicTag(CompoundTag nbt) {
        return new SchematicFile(
                nbt.getShort("Width"),
                nbt.getShort("Height"),
                nbt.getShort("Length"),
                nbt.getByteArray("Blocks"),
                nbt.getByteArray("AddBlocks"),
                nbt.getByteArray("Data"),
                nbt.getList("Entities", Tag.TAG_COMPOUND).stream().map(tag -> (CompoundTag)tag).toList(),
                nbt.getList("TileEntities", Tag.TAG_COMPOUND).stream().map(tag -> (CompoundTag)tag).toList()
        );
    }
}
