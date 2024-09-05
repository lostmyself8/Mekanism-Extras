package com.jerry.mekanism_extras.common.util;

import com.jerry.mekanism_extras.common.tier.BTier;
import com.jerry.mekanism_extras.common.tier.CTTier;
import com.jerry.mekanism_extras.common.tier.ECTier;
import com.jerry.mekanism_extras.common.tier.FTTier;
import com.jerry.mekanism_extras.common.resource.ore.ExtraOreType;
import com.jerry.mekanism_extras.common.tier.ICTier;
import com.jerry.mekanism_extras.common.tier.IPTier;

public class ExtraEnumUtils {
    public static final ECTier[] ENERGY_CUBE_TIERS = ECTier.values();
    public static final CTTier[] CHEMICAL_TANK_TIERS = CTTier.values();
    public static final FTTier[] FLUID_TANK_TIERS = FTTier.values();
    public static final BTier[] BIN_TIERS = BTier.values();
    public static final ICTier[] INDUCTION_CELL_TIERS = ICTier.values();
    public static final IPTier[] INDUCTION_PROVIDER_TIERS = IPTier.values();
    public static final ExtraOreType[] ORE_TYPES = ExtraOreType.values();
}
