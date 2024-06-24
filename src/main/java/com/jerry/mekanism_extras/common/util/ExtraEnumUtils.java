package com.jerry.mekanism_extras.common.util;

import com.jerry.mekanism_extras.common.block.storage.bin.BTier;
import com.jerry.mekanism_extras.common.block.storage.chemicaltank.CTTier;
import com.jerry.mekanism_extras.common.block.storage.energycube.ECTier;
import com.jerry.mekanism_extras.common.block.storage.fluidtank.FTTier;
import com.jerry.mekanism_extras.common.tile.multiblock.cell.ICTier;
import com.jerry.mekanism_extras.common.tile.multiblock.provider.IPTier;

public class ExtraEnumUtils {
    public static final ECTier[] ENERGY_CUBE_TIERS = ECTier.values();
    public static final CTTier[] CHEMICAL_TANK_TIERS = CTTier.values();
    public static final FTTier[] FLUID_TANK_TIERS = FTTier.values();
    public static final BTier[] BIN_TIERS = BTier.values();
    public static final ICTier[] INDUCTION_CELL_TIERS = ICTier.values();
    public static final IPTier[] INDUCTION_PROVIDER_TIERS = IPTier.values();
}
