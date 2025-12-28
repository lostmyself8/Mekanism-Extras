package com.jerry.genextras.common.registries;

import com.jerry.mekextras.MekanismExtras;

import com.jerry.genextras.common.GeneratorExtraTags;
import com.jerry.genextras.common.config.GeneratorsExtraConfig;
import com.jerry.genextras.common.item.ItemNaquadahHohlraum;

import mekanism.common.attachments.containers.ContainerType;
import mekanism.common.attachments.containers.chemical.ChemicalTanksBuilder;
import mekanism.common.registration.impl.ItemDeferredRegister;
import mekanism.common.registration.impl.ItemRegistryObject;
import mekanism.generators.common.config.MekanismGeneratorsConfig;

public class GenExtraItems {

    private GenExtraItems() {}

    public static final ItemDeferredRegister GEN_EXTRA_ITEMS = new ItemDeferredRegister(MekanismExtras.MOD_ID);

    public static final ItemRegistryObject<ItemNaquadahHohlraum> NAQUADAH_HOHLRAUM = GEN_EXTRA_ITEMS.registerItem("naquadah_hohlraum", ItemNaquadahHohlraum::new)
            .addAttachedContainerCapabilities(ContainerType.CHEMICAL, () -> ChemicalTanksBuilder.builder()
                    .addInternalStorage(GeneratorsExtraConfig.extraGenerators.hohlraumFillRate, GeneratorsExtraConfig.extraGenerators.hohlraumMaxGas,
                            gas -> gas.is(GeneratorExtraTags.Chemicals.NAQUADAH_URANIUM_FUEL))
                    .build(), MekanismGeneratorsConfig.generators);
}
