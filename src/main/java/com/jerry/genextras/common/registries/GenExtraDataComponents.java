package com.jerry.genextras.common.registries;

import com.jerry.mekextras.MekanismExtras;

import com.jerry.genextras.common.tile.naquadah.TileEntityNaquadahReactorLogicAdapter;

import mekanism.common.registration.MekanismDeferredHolder;
import mekanism.common.registration.impl.DataComponentDeferredRegister;

import net.minecraft.core.component.DataComponentType;

public class GenExtraDataComponents {

    private GenExtraDataComponents() {}

    public static final DataComponentDeferredRegister GEN_EXTRA_DATA_COMPONENTS = new DataComponentDeferredRegister(MekanismExtras.MOD_ID);

    public static final MekanismDeferredHolder<DataComponentType<?>, DataComponentType<TileEntityNaquadahReactorLogicAdapter.NaquadahReactorLogic>> NAQUADAH_LOGIC_TYPE = GEN_EXTRA_DATA_COMPONENTS.simple("naquadah_logic",
            builder -> builder.persistent(TileEntityNaquadahReactorLogicAdapter.NaquadahReactorLogic.CODEC)
                    .networkSynchronized(TileEntityNaquadahReactorLogicAdapter.NaquadahReactorLogic.STREAM_CODEC));
}
