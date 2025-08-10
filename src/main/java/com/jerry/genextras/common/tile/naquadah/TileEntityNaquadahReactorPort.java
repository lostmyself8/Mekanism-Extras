package com.jerry.genextras.common.tile.naquadah;

import com.jerry.genextras.common.registries.GenExtraBlocks;
import mekanism.api.IContentsListener;
import mekanism.api.chemical.IChemicalHandler;
import mekanism.api.heat.IHeatHandler;
import mekanism.api.text.EnumColor;
import mekanism.common.capabilities.Capabilities;
import mekanism.common.capabilities.heat.CachedAmbientTemperature;
import mekanism.common.capabilities.holder.chemical.IChemicalTankHolder;
import mekanism.common.capabilities.holder.energy.IEnergyContainerHolder;
import mekanism.common.capabilities.holder.fluid.IFluidTankHolder;
import mekanism.common.capabilities.holder.heat.IHeatCapacitorHolder;
import mekanism.common.integration.energy.BlockEnergyCapabilityCache;
import mekanism.common.lib.multiblock.MultiblockData;
import mekanism.common.util.WorldUtils;
import mekanism.common.util.text.BooleanStateDisplay;
import mekanism.generators.common.GeneratorsLang;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.BlockCapabilityCache;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class TileEntityNaquadahReactorPort extends TileEntityNaquadahReactorCasing{

    private final Map<Direction, BlockCapabilityCache<IChemicalHandler, @Nullable Direction>> chemicalCapabilityCaches = new EnumMap<>(Direction.class);
    private final Map<Direction, BlockEnergyCapabilityCache> energyCapabilityCaches = new EnumMap<>(Direction.class);

    public TileEntityNaquadahReactorPort(BlockPos pos, BlockState state) {
        super(GenExtraBlocks.NAQUADAH_REACTOR_PORT, pos, state);
    }

    @NotNull
    @Override
    public IChemicalTankHolder getInitialChemicalTanks(IContentsListener listener) {
        //Note: We can just use a proxied holder as the input/output restrictions are done in the tanks themselves
        return side -> getMultiblock().getChemicalTanks(side);
    }

    @NotNull
    @Override
    protected IFluidTankHolder getInitialFluidTanks(IContentsListener listener) {
        return side -> getMultiblock().getFluidTanks(side);
    }

    @NotNull
    @Override
    protected IEnergyContainerHolder getInitialEnergyContainers(IContentsListener listener) {
        return side -> getMultiblock().getEnergyContainers(side);
    }

    @NotNull
    @Override
    protected IHeatCapacitorHolder getInitialHeatCapacitors(IContentsListener listener, CachedAmbientTemperature ambientTemperature) {
        return side -> getMultiblock().getHeatCapacitors(side);
    }

    public void addGasTargetCapability(List<MultiblockData.CapabilityOutputTarget<IChemicalHandler>> outputTargets, Direction side) {
        BlockCapabilityCache<IChemicalHandler, @Nullable Direction> cache = chemicalCapabilityCaches.get(side);
        if (cache == null) {
            cache = Capabilities.CHEMICAL.createCache((ServerLevel) level, worldPosition.relative(side), side.getOpposite());
            chemicalCapabilityCaches.put(side, cache);
        }
        outputTargets.add(new MultiblockData.CapabilityOutputTarget<>(cache, this::getActive));
    }

    public void addEnergyTargetCapability(List<MultiblockData.EnergyOutputTarget> outputTargets, Direction side) {
        BlockEnergyCapabilityCache cache = energyCapabilityCaches.get(side);
        if (cache == null) {
            cache = BlockEnergyCapabilityCache.create((ServerLevel) level, worldPosition.relative(side), side.getOpposite());
            energyCapabilityCaches.put(side, cache);
        }
        outputTargets.add(new MultiblockData.EnergyOutputTarget(cache, this::getActive));
    }

    @Nullable
    @Override
    public IHeatHandler getAdjacent(@NotNull Direction side) {
        if (canHandleHeat() && getHeatCapacitorCount(side) > 0) {
            if (WorldUtils.getBlockState(level, getBlockPos().relative(side))
                    .filter(state -> !state.is(GenExtraBlocks.NAQUADAH_REACTOR_PORT))
                    .isPresent()) {
                return getAdjacentUnchecked(side);
            }
        }
        return null;
    }

    @Override
    public InteractionResult onSneakRightClick(Player player) {
        if (!isRemote()) {
            boolean oldMode = getActive();
            setActive(!oldMode);
            player.displayClientMessage(GeneratorsLang.REACTOR_PORT_EJECT.translateColored(EnumColor.GRAY, BooleanStateDisplay.InputOutput.of(oldMode, true)), true);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public int getRedstoneLevel() {
        return getMultiblock().getCurrentRedstoneLevel();
    }
}
