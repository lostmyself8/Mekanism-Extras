package com.jerry.mekextras.common.registry;

import com.jerry.mekextras.MekanismExtras;
import com.jerry.mekextras.common.capabilities.chemical.item.ExtraChemicalTankRateLimitChemicalTank;
import com.jerry.mekextras.common.item.block.ExtraItemBlockChemicalTank;
import com.jerry.mekextras.common.tile.ExtraTileEntityChemicalTank;
import mekanism.api.chemical.merged.MergedChemicalTank;
import mekanism.common.registration.MekanismDeferredHolder;
import mekanism.common.registration.impl.AttachmentTypeDeferredRegister;
import com.jerry.mekextras.common.tier.CTTier;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;

import java.util.Objects;

public class ExtraAttachmentTypes {
    private ExtraAttachmentTypes() {
    }

    public static final AttachmentTypeDeferredRegister ATTACHMENT_TYPES = new AttachmentTypeDeferredRegister(MekanismExtras.MOD_ID);
    public static final MekanismDeferredHolder<AttachmentType<?>, AttachmentType<ExtraTileEntityChemicalTank.GasMode>> EXTRA_DUMP_MODE = ATTACHMENT_TYPES.register("extra_dump_mode", ExtraTileEntityChemicalTank.GasMode.class);

    public static final MekanismDeferredHolder<AttachmentType<?>, AttachmentType<MergedChemicalTank>> CHEMICAL_TANK_CONTENTS_HANDLER = ATTACHMENT_TYPES.register("chemical_tank_contents_handler",
            () -> AttachmentType.builder(holder -> {
                if (holder instanceof ItemStack stack && !stack.isEmpty() && stack.getItem() instanceof ExtraItemBlockChemicalTank tank) {
                    CTTier tier = Objects.requireNonNull(tank.getAdvanceTier(), "Chemical tank tier cannot be null");
                    return MergedChemicalTank.create(
                            new ExtraChemicalTankRateLimitChemicalTank.GasTankRateLimitChemicalTank(tier, null),
                            new ExtraChemicalTankRateLimitChemicalTank.InfusionTankRateLimitChemicalTank(tier, null),
                            new ExtraChemicalTankRateLimitChemicalTank.PigmentTankRateLimitChemicalTank(tier, null),
                            new ExtraChemicalTankRateLimitChemicalTank.SlurryTankRateLimitChemicalTank(tier, null)
                    );
                }
                throw new IllegalArgumentException("Attempted to attach a CHEMICAL_TANK_CONTENTS_HANDLER to an object other than a chemical tank.");
            }).build());

    public static void register(IEventBus eventBus) {
        ATTACHMENT_TYPES.register(eventBus);
    }
}
