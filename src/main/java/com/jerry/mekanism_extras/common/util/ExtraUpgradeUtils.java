package com.jerry.mekanism_extras.common.util;

import com.jerry.mekanism_extras.api.ExtraUpgrade;
import com.jerry.mekanism_extras.common.ExtraLang;

import mekanism.api.Upgrade;
import mekanism.common.MekanismLang;
import mekanism.common.tile.interfaces.IUpgradeTile;

import net.minecraft.network.chat.Component;

import java.util.List;

public class ExtraUpgradeUtils {

    public static List<Component> getMultScaledInfo(List<Component> ret, IUpgradeTile tile, Upgrade upgrade) {
        if (tile.supportsUpgrades()) {
            if (upgrade == ExtraUpgrade.STACK) {
                // 有堆叠会默认添加上效率，因此这里清除掉
                ret.clear();
                double stack = Math.pow(2, tile.getComponent().getUpgrades(upgrade));
                ret.add(ExtraLang.UPGRADES_STACK.translate(stack));
            } else if (upgrade == ExtraUpgrade.CREATIVE) {
                // 没堆叠的不会被添加任何东西
                ret.add(MekanismLang.UPGRADES_EFFECT.translate("∞"));
                ret.add(ExtraLang.ENERGY_CONSUMPTION.translate(0));
            }
        }
        return ret;
    }
}
