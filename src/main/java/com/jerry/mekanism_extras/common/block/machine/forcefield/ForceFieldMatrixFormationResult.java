package com.jerry.mekanism_extras.common.block.machine.forcefield;

import net.minecraft.network.chat.Component;

public class ForceFieldMatrixFormationResult {
    public final boolean isFormed;
    public final Component result;

    private ForceFieldMatrixFormationResult(boolean isFormed, Component result) {
        this.isFormed = isFormed;
        this.result = result;
    }

    public static ForceFieldMatrixFormationResult ofSuccess(Component result) {
        return new ForceFieldMatrixFormationResult(true, result);
    }

    public static ForceFieldMatrixFormationResult ofFailed(Component result) {
        return new ForceFieldMatrixFormationResult(false, result);
    }
}
