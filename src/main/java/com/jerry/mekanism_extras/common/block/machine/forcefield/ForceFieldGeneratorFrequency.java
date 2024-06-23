package com.jerry.mekanism_extras.common.block.machine.forcefield;

import com.jerry.mekanism_extras.util.FrequencyTypeHelper;
import com.jerry.mekanism_extras.util.ListHelper;
import mekanism.api.Coord4D;
import mekanism.api.text.EnumColor;
import mekanism.common.lib.frequency.Frequency;
import mekanism.common.lib.frequency.IColorableFrequency;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.List;

public class ForceFieldGeneratorFrequency extends Frequency implements IColorableFrequency {
    private final Map<String, UUID> nameToUUIDLookupMap = new HashMap<>();
    private final Map<UUID, List<Coord4D>> freqToCoordsLookupMap = new HashMap<>();
    private EnumColor color = EnumColor.PURPLE;

    /**
     * Only call for base creation.
     */
    public ForceFieldGeneratorFrequency() {
        super(FrequencyTypeHelper.FORCEFIELD);
    }

    public ForceFieldGeneratorFrequency(String freq) {
        this(freq, UUID.randomUUID());
    }

    public ForceFieldGeneratorFrequency(String freq, @Nullable UUID uuid) {
        super(FrequencyTypeHelper.FORCEFIELD, freq, uuid);
        this.nameToUUIDLookupMap.put(freq, uuid);
    }

    public void addForceFieldGenerator(String freq, Coord4D coord4D) {
        addForceFieldGenerator(nameToUUIDLookupMap.get(freq), coord4D);
    }

    private void addForceFieldGenerator(UUID freq, Coord4D coord4D) {
        if (freqToCoordsLookupMap.containsKey(freq)) {
            freqToCoordsLookupMap.get(freq).add(coord4D);
        } else {
            freqToCoordsLookupMap.put(freq, Collections.singletonList(coord4D));
        }
        this.dirty = true;
    }

    public List<Coord4D> getForceFieldGeneratorsWithFreq(String freq) {
        return freqToCoordsLookupMap.get(nameToUUIDLookupMap.get(freq));
    }

    private List<Coord4D> getForceFieldGeneratorsWithFreq(UUID freq) {
        return freqToCoordsLookupMap.get(freq);
    }

    public ForceFieldMatrixFormationResult isGeneratorMatrixFormed(String freq) {
        if (!nameToUUIDLookupMap.containsKey(freq)) return ForceFieldMatrixFormationResult.ofFailed(Component.nullToEmpty(null));
        return isGeneratorMatrixFormed(nameToUUIDLookupMap.get(freq));
    }

    private ForceFieldMatrixFormationResult isGeneratorMatrixFormed(UUID freq) {
        // Wrong quantity of generators
        if (freqToCoordsLookupMap.get(freq).size() != 8) return ForceFieldMatrixFormationResult.ofFailed(Component.translatable("forcefield.mekanism_extras.failed.wrong_quantity_of_generators").withStyle(ChatFormatting.RED));

        // How cool the stream is!
        List<Coord4D> coords = freqToCoordsLookupMap.get(freq);
        List<BlockPos> coord3DList = coords.stream().map((Coord4D::getPos)).toList();
        List<ResourceKey<Level>> dimensions = coords.stream().map(coord4D -> coord4D.dimension).toList();
        // Cross dimension
        if (new HashSet<>(dimensions).size() != 1) return ForceFieldMatrixFormationResult.ofFailed(Component.translatable("forcefield.mekanism_extras.failed.cross_dimension").withStyle(ChatFormatting.RED));

        // Validate the cuboid, we need to get the 12 lengths of the sides
        List<Integer> xList = new ArrayList<>(), yList = new ArrayList<>(), zList = new ArrayList<>();
        ListHelper.permutation(coord3DList).stream()
                .filter(pair -> {
                    // Step 1: reduce the pairs
                    // We don't need to check pairs which is not on any of X, Y or Z axis
                    // So we get the sides
                    BlockPos left = pair.getLeft();
                    BlockPos right = pair.getRight();
                    return  left.getX() == right.getX() ||
                            left.getY() == right.getY() ||
                            left.getZ() == right.getZ();
                }).forEach(pair -> {
                    // Step 2: map the pair to the length
                    BlockPos left = pair.getLeft();
                    BlockPos right = pair.getRight();

                    if (left.getX() == right.getX()) {
                        xList.add(Math.abs(left.getX() - right.getX()));
                    } else if (left.getY() == right.getY()) {
                        yList.add(Math.abs(left.getY() - right.getY()));
                    } else if (left.getZ() == right.getZ()) {
                        zList.add(Math.abs(left.getZ() - right.getZ()));
                    }
                });
        if (new HashSet<>(xList).size() != 1 || new HashSet<>(yList).size() != 1 || new HashSet<>(zList).size() != 1) {
            return ForceFieldMatrixFormationResult.ofSuccess(Component.translatable("forcefield.mekanism_extras.success").withStyle(ChatFormatting.GREEN));
        } else {
            return ForceFieldMatrixFormationResult.ofFailed(Component.translatable("forcefield.mekanism_extras.failed.invalid_cuboid").withStyle(ChatFormatting.RED));
        }
    }

    @Override
    public EnumColor getColor() {
        return color;
    }

    @Override
    public void setColor(EnumColor color) {
        if (this.color != color) {
            this.color = color;
            this.dirty = true;
        }
    }
}
