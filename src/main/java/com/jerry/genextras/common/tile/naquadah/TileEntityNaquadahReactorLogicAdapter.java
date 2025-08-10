package com.jerry.genextras.common.tile.naquadah;

import com.jerry.genextras.common.content.naquadah.NaquadahReactorMultiblockData;
import com.jerry.genextras.common.registries.GenExtraBlocks;
import com.jerry.genextras.common.registries.GenExtraDataComponents;
import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import mekanism.api.SerializationConstants;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.text.EnumColor;
import mekanism.api.text.IHasTranslationKey;
import mekanism.api.text.ILangEntry;
import mekanism.common.integration.computer.annotation.ComputerMethod;
import mekanism.common.inventory.container.MekanismContainer;
import mekanism.common.inventory.container.sync.SyncableBoolean;
import mekanism.common.inventory.container.sync.SyncableEnum;
import mekanism.common.tile.interfaces.IHasMode;
import mekanism.common.util.NBTUtils;
import mekanism.generators.common.GeneratorsLang;
import mekanism.generators.common.base.IReactorLogic;
import mekanism.generators.common.base.IReactorLogicMode;
import mekanism.generators.common.registries.GeneratorsDataComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.event.EventHooks;
import org.jetbrains.annotations.NotNull;

import java.util.EnumSet;
import java.util.Locale;
import java.util.function.IntFunction;

public class TileEntityNaquadahReactorLogicAdapter extends TileEntityNaquadahReactorCasing implements IReactorLogic<TileEntityNaquadahReactorLogicAdapter.NaquadahReactorLogic>, IHasMode {

    public NaquadahReactorLogic logicType = NaquadahReactorLogic.DISABLED;
    private boolean activeCooled;
    private boolean prevOutputting;

    public TileEntityNaquadahReactorLogicAdapter(BlockPos pos, BlockState state) {
        super(GenExtraBlocks.NAQUADAH_REACTOR_LOGIC_ADAPTER, pos, state);
    }

    @Override
    protected boolean onUpdateServer(NaquadahReactorMultiblockData multiblock) {
        boolean needsPacket = super.onUpdateServer(multiblock);
        boolean outputting = checkMode();
        if (outputting != prevOutputting) {
            Level world = getLevel();
            if (world != null) {
                Direction side = multiblock.getOutsideSide(worldPosition);
                BlockState state = getBlockState();
                if (side == null) {
                    //Not formed, just update all sides
                    world.updateNeighborsAt(getBlockPos(), state.getBlock());
                } else if (!EventHooks.onNeighborNotify(world, worldPosition, state, EnumSet.of(side), false).isCanceled()) {
                    world.neighborChanged(worldPosition.relative(side), state.getBlock(), worldPosition);
                }
            }
            prevOutputting = outputting;
        }
        return needsPacket;
    }

    public int getRedstoneLevel(Direction side) {
        return !isRemote() && getMultiblock().isPositionOutsideBounds(worldPosition.relative(side)) && checkMode() ? 15 : 0;
    }

    public boolean checkMode() {
        if (isRemote()) {
            return prevOutputting;
        }
        NaquadahReactorMultiblockData multiblock = getMultiblock();
        if (multiblock.isFormed()) {
            return switch (logicType) {
                case READY -> multiblock.getLastPlasmaTemp() >= multiblock.getIgnitionTemperature(activeCooled);
                case CAPACITY -> multiblock.getLastPlasmaTemp() >= multiblock.getMaxPlasmaTemperature(activeCooled);
                case DEPLETED -> {
                    if (multiblock.fuelTank.isEmpty()) {
                        int injectionPortion = multiblock.getInjectionRate() / 2;
                        //No fuel and no injection rate set, or no fuel and not enough of at least one component
                        yield injectionPortion == 0 || multiblock.deuteriumTank.getStored() < injectionPortion || multiblock.tritiumTank.getStored() < injectionPortion;
                    }
                    yield false;
                }
                case DISABLED -> false;
            };
        }
        return false;
    }

    @Override
    public void readSustainedData(HolderLookup.Provider provider, @NotNull CompoundTag nbt) {
        super.readSustainedData(provider, nbt);
        NBTUtils.setEnumIfPresent(nbt, SerializationConstants.LOGIC_TYPE, NaquadahReactorLogic.BY_ID, logicType -> this.logicType = logicType);
        activeCooled = nbt.getBoolean(SerializationConstants.ACTIVE_COOLED);
    }

    @Override
    public void writeSustainedData(HolderLookup.Provider provider, CompoundTag nbtTags) {
        super.writeSustainedData(provider, nbtTags);
        NBTUtils.writeEnum(nbtTags, SerializationConstants.LOGIC_TYPE, logicType);
        nbtTags.putBoolean(SerializationConstants.ACTIVE_COOLED, activeCooled);
    }

    @Override
    protected void collectImplicitComponents(@NotNull DataComponentMap.Builder builder) {
        super.collectImplicitComponents(builder);
        builder.set(GenExtraDataComponents.NAQUADAH_LOGIC_TYPE, logicType);
        builder.set(GeneratorsDataComponents.ACTIVE_COOLED, activeCooled);
    }

    @Override
    protected void applyImplicitComponents(@NotNull BlockEntity.DataComponentInput input) {
        super.applyImplicitComponents(input);
        logicType = input.getOrDefault(GenExtraDataComponents.NAQUADAH_LOGIC_TYPE, logicType);
        activeCooled = input.getOrDefault(GeneratorsDataComponents.ACTIVE_COOLED, activeCooled);
    }

    @Override
    public void nextMode() {
        activeCooled = !activeCooled;
        markForSave();
    }

    @Override
    public void previousMode() {
        //We only have two modes just flip it
        nextMode();
    }

    @ComputerMethod(nameOverride = "isActiveCooledLogic")
    public boolean isActiveCooled() {
        return activeCooled;
    }

    @Override
    @ComputerMethod(nameOverride = "getLogicMode")
    public NaquadahReactorLogic getMode() {
        return logicType;
    }

    @Override
    public NaquadahReactorLogic[] getModes() {
        return NaquadahReactorLogic.values();
    }

    @ComputerMethod(nameOverride = "setLogicMode")
    public void setLogicTypeFromPacket(NaquadahReactorLogic logicType) {
        if (this.logicType != logicType) {
            this.logicType = logicType;
            markForSave();
        }
    }

    @Override
    public void addContainerTrackers(MekanismContainer container) {
        super.addContainerTrackers(container);
        container.track(SyncableEnum.create(NaquadahReactorLogic.BY_ID, NaquadahReactorLogic.DISABLED, this::getMode, value -> logicType = value));
        container.track(SyncableBoolean.create(this::isActiveCooled, value -> activeCooled = value));
        container.track(SyncableBoolean.create(() -> prevOutputting, value -> prevOutputting = value));
    }

    //Methods relating to IComputerTile
    @ComputerMethod
    void setActiveCooledLogic(boolean active) {
        if (activeCooled != active) {
            nextMode();
        }
    }
    //End methods IComputerTile

    @NothingNullByDefault
    public enum NaquadahReactorLogic implements IReactorLogicMode<NaquadahReactorLogic>, IHasTranslationKey.IHasEnumNameTranslationKey, StringRepresentable {
        DISABLED(GeneratorsLang.REACTOR_LOGIC_DISABLED, GeneratorsLang.DESCRIPTION_REACTOR_DISABLED, new ItemStack(Items.GUNPOWDER)),
        READY(GeneratorsLang.REACTOR_LOGIC_READY, GeneratorsLang.DESCRIPTION_REACTOR_READY, new ItemStack(Items.REDSTONE)),
        CAPACITY(GeneratorsLang.REACTOR_LOGIC_CAPACITY, GeneratorsLang.DESCRIPTION_REACTOR_CAPACITY, new ItemStack(Items.REDSTONE)),
        DEPLETED(GeneratorsLang.REACTOR_LOGIC_DEPLETED, GeneratorsLang.DESCRIPTION_REACTOR_DEPLETED, new ItemStack(Items.REDSTONE));

        public static final Codec<NaquadahReactorLogic> CODEC = StringRepresentable.fromEnum(NaquadahReactorLogic::values);
        public static final IntFunction<NaquadahReactorLogic> BY_ID = ByIdMap.continuous(NaquadahReactorLogic::ordinal, values(), ByIdMap.OutOfBoundsStrategy.WRAP);
        public static final StreamCodec<ByteBuf, NaquadahReactorLogic> STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, NaquadahReactorLogic::ordinal);

        private final ILangEntry name;
        private final ILangEntry description;
        private final ItemStack renderStack;
        private final String serializedName;

        NaquadahReactorLogic(ILangEntry name, ILangEntry description, ItemStack stack) {
            this.name = name;
            this.description = description;
            this.renderStack = stack;
            this.serializedName = name().toLowerCase(Locale.ROOT);
        }

        @Override
        public ItemStack getRenderStack() {
            return renderStack;
        }

        @Override
        public String getTranslationKey() {
            return name.getTranslationKey();
        }

        @Override
        public Component getDescription() {
            return description.translate();
        }

        @Override
        public EnumColor getColor() {
            return EnumColor.RED;
        }

        @Override
        public String getSerializedName() {
            return serializedName;
        }
    }
}
