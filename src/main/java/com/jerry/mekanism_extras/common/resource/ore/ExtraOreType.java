package com.jerry.mekanism_extras.common.resource.ore;

import com.jerry.mekanism_extras.common.resource.ExtraResource;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import mekanism.common.resource.IResource;
import mekanism.common.resource.ore.BaseOreConfig;
import mekanism.common.resource.ore.OreAnchor;
import mekanism.common.world.height.HeightShape;
import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public enum ExtraOreType implements StringRepresentable {
    NAQUADAH(ExtraResource.NAQUADAH,
            new BaseOreConfig("small", 6, 0, 4, HeightShape.TRAPEZOID, OreAnchor.absolute(-63), OreAnchor.absolute(-60))),
    END_NAQUADAH(ExtraResource.END_NAQUADAH,
            new BaseOreConfig("middle", 8, 0, 4, HeightShape.TRAPEZOID, OreAnchor.absolute(15), OreAnchor.absolute(25)));

    public static Codec<ExtraOreType> CODEC = StringRepresentable.fromEnum(ExtraOreType::values);

    private final List<BaseOreConfig> baseConfigs;
    private final IResource resource;
    private final int minExp;
    private final int maxExp;

    ExtraOreType(IResource resource, BaseOreConfig... configs) {
        this(resource, 0, configs);
    }

    ExtraOreType(IResource resource, int exp, BaseOreConfig... configs) {
        this(resource, exp, exp, configs);
    }

    ExtraOreType(IResource resource, int minExp, int maxExp, BaseOreConfig... configs) {
        this.resource = resource;
        this.minExp = minExp;
        this.maxExp = maxExp;
        this.baseConfigs = List.of(configs);
    }

    public IResource getResource() {
        return resource;
    }

    public List<BaseOreConfig> getBaseConfigs() {
        return baseConfigs;
    }

    public int getMinExp() {
        return minExp;
    }

    public int getMaxExp() {
        return maxExp;
    }

    public static ExtraOreType get(IResource resource) {
        for (ExtraOreType ore : values()) {
            if (resource == ore.resource) {
                return ore;
            }
        }
        return null;
    }

    @NotNull
    @Override
    public String getSerializedName() {
        return resource.getRegistrySuffix();
    }

    public record OreVeinType(ExtraOreType type, int index) {

        public static final Codec<OreVeinType> CODEC = RecordCodecBuilder.create(builder -> builder.group(
                ExtraOreType.CODEC.fieldOf("type").forGetter(config -> config.type),
                Codec.INT.fieldOf("index").forGetter(config -> config.index)
        ).apply(builder, OreVeinType::new));

        //如果枚举类型中new了n个BaseOreConfig，那么index就是n-1也对应；了json中的index
        //If n BaseOreConfig objects are newed in the enumeration type, then the index is n - 1; it also corresponds to the index in the json.
        public OreVeinType {
            if (index < 0 || index >= type.getBaseConfigs().size()) {
                throw new IndexOutOfBoundsException("Vein Type index out of range: " + index);
            }
        }

        public String name() {
            return "ore_" + type.getResource().getRegistrySuffix() + "_" + type.getBaseConfigs().get(index).name();
        }
    }
}
