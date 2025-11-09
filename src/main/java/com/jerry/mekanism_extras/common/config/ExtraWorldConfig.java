package com.jerry.mekanism_extras.common.config;

import com.jerry.mekanism_extras.common.resource.ore.ExtraOreType;
import com.jerry.mekanism_extras.common.util.ExtraEnumUtils;

import mekanism.api.functions.FloatSupplier;
import mekanism.common.config.BaseMekanismConfig;
import mekanism.common.config.IMekanismConfig;
import mekanism.common.config.value.CachedBooleanValue;
import mekanism.common.config.value.CachedFloatValue;
import mekanism.common.config.value.CachedIntValue;
import mekanism.common.resource.ore.BaseOreConfig;
import mekanism.common.world.height.ConfigurableHeightRange;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;

import com.google.common.collect.ImmutableList;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.BooleanSupplier;
import java.util.function.IntSupplier;

public class ExtraWorldConfig extends BaseMekanismConfig {

    private final ForgeConfigSpec configSpec;
    public final CachedBooleanValue enableRegeneration;
    public final CachedIntValue userGenVersion;

    private final Map<ExtraOreType, OreConfig> ores = new EnumMap<>(ExtraOreType.class);

    ExtraWorldConfig() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.comment("World generation settings for Mekanism Extras. This config is synced from server to client").push("world_generation");
        enableRegeneration = CachedBooleanValue.wrap(this, builder.comment("Allows chunks to retrogen Mekanism Extras ore blocks.")
                .define("enableRegeneration", false));
        userGenVersion = CachedIntValue.wrap(this, builder.comment("Change this value to cause Mekanism Extras to regen its ore in all loaded chunks.")
                .defineInRange("userWorldGenVersion", 0, 0, Integer.MAX_VALUE));
        for (ExtraOreType ore : ExtraEnumUtils.EXTRA_ORE_TYPES) {
            ores.put(ore, new OreConfig(this, builder, ore));
        }
        builder.pop();
        configSpec = builder.build();
    }

    @Override
    public String getFileName() {
        return "world";
    }

    @Override
    public ForgeConfigSpec getConfigSpec() {
        return configSpec;
    }

    @Override
    public ModConfig.Type getConfigType() {
        return ModConfig.Type.SERVER;
    }

    @Override
    public boolean addToContainer() {
        return false;
    }

    public OreVeinConfig getVeinConfig(ExtraOreType.OreVeinType oreVeinType) {
        return ores.get(oreVeinType.type()).veinConfigs.get(oreVeinType.index());
    }

    public record OreVeinConfig(BooleanSupplier shouldGenerate, CachedIntValue perChunk, IntSupplier maxVeinSize, FloatSupplier discardChanceOnAirExposure,
                                ConfigurableHeightRange range) {}

    private static class OreConfig {

        private final CachedBooleanValue shouldGenerate;
        private final List<OreVeinConfig> veinConfigs;

        private OreConfig(IMekanismConfig config, ForgeConfigSpec.Builder builder, ExtraOreType extraOreType) {
            String ore = extraOreType.getResource().getRegistrySuffix();
            builder.comment("Generation Settings for " + ore + " ore.").push(ore);
            this.shouldGenerate = CachedBooleanValue.wrap(config, builder.comment("Determines if " + ore + " ore should be added to world generation.")
                    .define("shouldGenerate", true));
            ImmutableList.Builder<OreVeinConfig> veinBuilder = ImmutableList.builder();
            for (BaseOreConfig baseConfig : extraOreType.getBaseConfigs()) {
                String veinType = baseConfig.name() + " " + ore + " vein";
                builder.comment(veinType + " Generation Settings.").push(baseConfig.name());
                CachedBooleanValue shouldVeinTypeGenerate = CachedBooleanValue.wrap(config, builder.comment("Determines if " + veinType + "s should be added to world generation. Note: Requires generating " + ore + " ore to be enabled.")
                        .define("shouldGenerate", true));
                veinBuilder.add(new OreVeinConfig(
                        () -> this.shouldGenerate.get() && shouldVeinTypeGenerate.get(),
                        CachedIntValue.wrap(config, builder.comment("Chance that " + veinType + "s generates in a chunk.")
                                .defineInRange("perChunk", baseConfig.perChunk(), 1, 256)),
                        CachedIntValue.wrap(config, builder.comment("Maximum number of blocks in a " + veinType + ".")
                                .defineInRange("maxVeinSize", baseConfig.maxVeinSize(), 1, 64)),
                        CachedFloatValue.wrap(config, builder.comment("Chance that blocks that are directly exposed to air in a " + veinType + " are not placed.")
                                .defineInRange("discardChanceOnAirExposure", baseConfig.discardChanceOnAirExposure(), 0, 1)),
                        ConfigurableHeightRange.create(config, builder, veinType, baseConfig)));
                builder.pop();
            }
            veinConfigs = veinBuilder.build();
            builder.pop();
        }
    }
}
