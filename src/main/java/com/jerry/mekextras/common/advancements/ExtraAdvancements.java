package com.jerry.mekextras.common.advancements;

import com.jerry.mekextras.MekanismExtras;

import mekanism.common.advancements.MekanismAdvancement;
import mekanism.common.advancements.MekanismAdvancements;

import org.jetbrains.annotations.Nullable;

public class ExtraAdvancements {

    private ExtraAdvancements() {}

    private static MekanismAdvancement advancement(@Nullable MekanismAdvancement parent, String name) {
        return new MekanismAdvancement(parent, MekanismExtras.rl(name));
    }

    public static final MekanismAdvancement RADIANCE_ALLOY = advancement(MekanismAdvancements.ATOMIC_ALLOY, "radiance_alloy");
    public static final MekanismAdvancement THERMONUCLEAR_ALLOY = advancement(RADIANCE_ALLOY, "thermonuclear_alloy");
    public static final MekanismAdvancement SHINING_ALLOY = advancement(THERMONUCLEAR_ALLOY, "shining_alloy");
    public static final MekanismAdvancement SPECTRUM_ALLOY = advancement(SHINING_ALLOY, "spectrum_alloy");

    public static final MekanismAdvancement ABSOLUTE_CONTROL_CIRCUIT = advancement(RADIANCE_ALLOY, "absolute_control_circuit");
    public static final MekanismAdvancement SUPREME_CONTROL_CIRCUIT = advancement(THERMONUCLEAR_ALLOY, "supreme_control_circuit");
    public static final MekanismAdvancement COSMIC_CONTROL_CIRCUIT = advancement(SHINING_ALLOY, "cosmic_control_circuit");
    public static final MekanismAdvancement INFINITE_CONTROL_CIRCUIT = advancement(SPECTRUM_ALLOY, "infinite_control_circuit");

    public static final MekanismAdvancement STACK_UPGRADE = advancement(THERMONUCLEAR_ALLOY, "stack_upgrade");
    public static final MekanismAdvancement IONIC_MEMBRANE_UPGRADE = advancement(SHINING_ALLOY, "ionic_membrane_upgrade");
    public static final MekanismAdvancement CREATIVE_UPGRADE = advancement(MekanismAdvancements.ROOT, "creative_upgrade");
}
