package com.jerry.mekanism_extras.client.render;

import com.jerry.mekanism_extras.client.render.item.block.ExtraRenderEnergyCubeItem;
import com.jerry.mekanism_extras.client.render.item.block.ExtraRenderFluidTankItem;
import mekanism.client.render.RenderPropertiesProvider;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

public class ExtraRenderPropertiesProvider {
    private ExtraRenderPropertiesProvider() {

    }
    public static IClientItemExtensions extraEnergyCube() {
        return new RenderPropertiesProvider.MekRenderProperties(ExtraRenderEnergyCubeItem.EXTRA_RENDERER);
    }
    public static IClientItemExtensions extraFluidTank() {
        return new RenderPropertiesProvider.MekRenderProperties(ExtraRenderFluidTankItem.EXTRA_RENDERER);
    }
}
