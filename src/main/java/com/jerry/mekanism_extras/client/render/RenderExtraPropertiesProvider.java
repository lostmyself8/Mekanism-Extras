package com.jerry.mekanism_extras.client.render;

import com.jerry.mekanism_extras.client.render.item.block.RenderExtraEnergyCubeItem;
import com.jerry.mekanism_extras.client.render.item.block.RenderExtraFluidTankItem;

import mekanism.client.render.RenderPropertiesProvider;

import net.minecraftforge.client.extensions.common.IClientItemExtensions;

public class RenderExtraPropertiesProvider {

    private RenderExtraPropertiesProvider() {}

    public static IClientItemExtensions extraEnergyCube() {
        return new RenderPropertiesProvider.MekRenderProperties(RenderExtraEnergyCubeItem.EXTRA_RENDERER);
    }

    public static IClientItemExtensions extraFluidTank() {
        return new RenderPropertiesProvider.MekRenderProperties(RenderExtraFluidTankItem.EXTRA_RENDERER);
    }
}
