package com.jerry.mekanism_extras.common.util;

import com.jerry.mekanism_extras.common.block.storage.bin.BTier;
import com.jerry.mekanism_extras.common.block.storage.chemicaltank.CTTier;
import com.jerry.mekanism_extras.common.block.storage.fluidtank.FTTier;

public class ExtraEnumUtils {
    public static final CTTier[] CHEMICAL_TANK_TIERS = CTTier.values();

    public static final FTTier[] FLUID_TANK_TIERS = FTTier.values();

    public static final BTier[] BIN_TIERS = BTier.values();
}
