package com.jerry.mekextras.client.render;

import com.jerry.mekextras.MekanismExtras;
import com.jerry.mekextras.client.render.tileentity.RenderExtraFluidTank;
import net.minecraft.util.FastColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.TextureAtlasStitchedEvent;

@EventBusSubscriber(modid = MekanismExtras.MOD_ID, value = Dist.CLIENT)
public class ExtraRenderer {
    @SubscribeEvent
    public static void onStitch(TextureAtlasStitchedEvent event) {
        RenderExtraFluidTank.resetCachedModels();
    }

    /**
     * Mek在1.21.2-10.7.11.76删除了这些方法，但我需要它们
     */
    public static int getColorARGB(float red, float green, float blue, float alpha) {
        return getColorARGB((int)(255.0F * red), (int)(255.0F * green), (int)(255.0F * blue), alpha);
    }

    public static int getColorARGB(int red, int green, int blue, float alpha) {
        if (alpha < 0.0F) {
            alpha = 0.0F;
        } else if (alpha > 1.0F) {
            alpha = 1.0F;
        }

        return FastColor.ARGB32.color((int)(255.0F * alpha), red, green, blue);
    }
}
