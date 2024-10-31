package com.jerry.mekextra.common.registry;

import com.jerry.mekextra.MekanismExtras;
import com.jerry.mekextra.common.tile.ExtraTileEntityChemicalTank;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.common.registration.MekanismDeferredHolder;
import mekanism.common.registration.impl.DataComponentDeferredRegister;
import net.minecraft.core.component.DataComponentType;
import net.neoforged.bus.api.IEventBus;

@NothingNullByDefault
public class ExtraDataComponents {

    private ExtraDataComponents() {

    }
    public static final DataComponentDeferredRegister EXTRA_DATA_COMPONENTS = new DataComponentDeferredRegister(MekanismExtras.MOD_ID);
    public static final MekanismDeferredHolder<DataComponentType<?>, DataComponentType<ExtraTileEntityChemicalTank.GasMode>> DUMP_MODE = EXTRA_DATA_COMPONENTS.simple("dump_mode",
            builder -> builder.persistent(ExtraTileEntityChemicalTank.GasMode.CODEC)
                    .networkSynchronized(ExtraTileEntityChemicalTank.GasMode.STREAM_CODEC)
    );

    public static void register(IEventBus eventBus) {
        EXTRA_DATA_COMPONENTS.register(eventBus);
    }
}
