package com.jerry.mekanism_extras.common.tile;

import com.jerry.mekanism_extras.common.capabilities.chemical.ExtraStackedWasteBarrel;
import com.jerry.mekanism_extras.common.config.LoadConfig;
import com.jerry.mekanism_extras.common.registry.ExtraBlock;
import mekanism.api.*;
import mekanism.api.chemical.gas.Gas;
import mekanism.api.chemical.gas.GasStack;
import mekanism.api.chemical.gas.IGasTank;
import mekanism.common.capabilities.Capabilities;
import mekanism.common.capabilities.holder.chemical.ChemicalTankHelper;
import mekanism.common.capabilities.holder.chemical.IChemicalTankHolder;
import mekanism.common.capabilities.resolver.BasicCapabilityResolver;
import mekanism.common.integration.computer.SpecialComputerMethodWrapper;
import mekanism.common.integration.computer.annotation.WrappingComputerMethod;
import mekanism.common.tags.MekanismTags;
import mekanism.common.tile.base.SubstanceType;
import mekanism.common.tile.base.TileEntityMekanism;
import mekanism.common.util.ChemicalUtil;
import mekanism.common.util.MekanismUtils;
import mekanism.common.util.NBTUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;

public class ExtraTileEntityRadioactiveWasteBarrel extends TileEntityMekanism implements IConfigurable {

    private long lastProcessTick;
    @WrappingComputerMethod(wrapper = SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.class, methodNames = {"getStored", "getCapacity", "getNeeded", "getFilledPercentage"}, docPlaceholder = "barrel")
    ExtraStackedWasteBarrel gasTank;
    private float prevScale;
    private int processTicks;

    public ExtraTileEntityRadioactiveWasteBarrel(BlockPos pos, BlockState state) {
        super(ExtraBlock.EXPAND_RADIOACTIVE_WASTE_BARREL, pos, state);
        addCapabilityResolver(BasicCapabilityResolver.constant(Capabilities.CONFIGURABLE, this));
    }

    @NotNull
    @Override
    public IChemicalTankHolder<Gas, GasStack, IGasTank> getInitialGasTanks(IContentsListener listener) {
        ChemicalTankHelper<Gas, GasStack, IGasTank> builder = ChemicalTankHelper.forSide(this::getDirection);
        builder.addTank(gasTank = ExtraStackedWasteBarrel.create(this, listener), RelativeSide.TOP, RelativeSide.BOTTOM);
        return builder.build();
    }

    @Override
    protected void onUpdateServer() {
        super.onUpdateServer();
        if (level.getGameTime() > lastProcessTick) {
            //If we are not on the same tick do stuff, otherwise ignore it (anti tick accelerator protection)
            lastProcessTick = level.getGameTime();
            if (LoadConfig.extraConfig.radioactiveWasteBarrelDecayAmount.get() > 0 && !gasTank.isEmpty() &&
                    !MekanismTags.Gases.WASTE_BARREL_DECAY_LOOKUP.contains(gasTank.getType()) &&
                    ++processTicks >= LoadConfig.extraConfig.radioactiveWasteBarrelProcessTicks.get()) {
                processTicks = 0;
                gasTank.shrinkStack(LoadConfig.extraConfig.radioactiveWasteBarrelDecayAmount.get(), Action.EXECUTE);
            }
            if (getActive()) {
                ChemicalUtil.emit(Collections.singleton(Direction.DOWN), gasTank, this);
            }
            //Note: We don't need to do any checking here if the packet needs due to capacity changing as we do it
            // in TileentityMekanism after this method is called. And given radioactive waste barrels can only contain
            // radioactive substances the check for radiation scale also will work for syncing capacity for purposes
            // of when the client sneak right-clicks on the barrel
        }
    }

    public ExtraStackedWasteBarrel getGasTank() {
        return gasTank;
    }

    public double getGasScale() {
        return gasTank.getStored() / (double) gasTank.getCapacity();
    }

    public GasStack getGas() {
        return gasTank.getStack();
    }

    @Override
    public InteractionResult onSneakRightClick(Player player) {
        if (!isRemote()) {
            setActive(!getActive());
            Level world = getLevel();
            if (world != null) {
                world.playSound(null, getBlockPos().getX(), getBlockPos().getY(), getBlockPos().getZ(), SoundEvents.UI_BUTTON_CLICK.get(), SoundSource.BLOCKS, 0.3F, 1);
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public InteractionResult onRightClick(Player player) {
        return InteractionResult.PASS;
    }

    @NotNull
    @Override
    public CompoundTag getReducedUpdateTag() {
        CompoundTag updateTag = super.getReducedUpdateTag();
        updateTag.put(NBTConstants.GAS_STORED, gasTank.serializeNBT());
        updateTag.putInt(NBTConstants.PROGRESS, processTicks);
        return updateTag;
    }

    @Override
    public void handleUpdateTag(@NotNull CompoundTag tag) {
        super.handleUpdateTag(tag);
        NBTUtils.setCompoundIfPresent(tag, NBTConstants.GAS_STORED, nbt -> gasTank.deserializeNBT(nbt));
        NBTUtils.setIntIfPresent(tag, NBTConstants.PROGRESS, val -> processTicks = val);
    }

    @Override
    public int getRedstoneLevel() {
        return MekanismUtils.redstoneLevelFromContents(gasTank.getStored(), gasTank.getCapacity());
    }

    @Override
    protected boolean makesComparatorDirty(@Nullable SubstanceType type) {
        return type == SubstanceType.GAS;
    }
}
