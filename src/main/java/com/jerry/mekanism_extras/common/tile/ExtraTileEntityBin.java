package com.jerry.mekanism_extras.common.tile;

import com.jerry.mekanism_extras.common.block.attribute.ExtraAttribute;
import com.jerry.mekanism_extras.common.tier.BTier;
import com.jerry.mekanism_extras.common.inventory.slot.ExtraBinInventorySlot;
import com.jerry.mekanism_extras.common.upgrade.ExtraBinUpgradeData;
import com.jerry.mekanism_extras.common.tile.transmitter.ExtraTileEntityLogisticalTransporterBase;
import mekanism.api.Action;
import mekanism.api.IConfigurable;
import mekanism.api.IContentsListener;
import mekanism.api.NBTConstants;
import mekanism.api.providers.IBlockProvider;
import mekanism.common.capabilities.Capabilities;
import mekanism.common.capabilities.holder.slot.IInventorySlotHolder;
import mekanism.common.capabilities.holder.slot.InventorySlotHelper;
import mekanism.common.capabilities.resolver.BasicCapabilityResolver;
import mekanism.common.integration.computer.ComputerException;
import mekanism.common.integration.computer.SpecialComputerMethodWrapper;
import mekanism.common.integration.computer.annotation.ComputerMethod;
import mekanism.common.integration.computer.annotation.WrappingComputerMethod;
import mekanism.common.inventory.slot.BinInventorySlot;
import mekanism.common.lib.inventory.TileTransitRequest;
import mekanism.common.lib.inventory.TransitRequest;
import mekanism.common.tile.base.TileEntityMekanism;
import mekanism.common.upgrade.BinUpgradeData;
import mekanism.common.upgrade.IUpgradeData;
import mekanism.common.util.MekanismUtils;
import mekanism.common.util.NBTUtils;
import mekanism.common.util.WorldUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class ExtraTileEntityBin extends TileEntityMekanism implements IConfigurable {
    public int addTicks = 0;
    public int removeTicks = 0;
    private int delayTicks;

    private BTier tier;

    @WrappingComputerMethod(wrapper = SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.class, methodNames = "getStored", docPlaceholder = "bin")
    ExtraBinInventorySlot binSlot;

    public ExtraTileEntityBin(IBlockProvider blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state);
        addCapabilityResolver(BasicCapabilityResolver.constant(Capabilities.CONFIGURABLE, this));
    }

    @Override
    protected void presetVariables() {
        super.presetVariables();
        tier = ExtraAttribute.getTier(getBlockType(), BTier.class);
    }

    @NotNull
    @Override
    protected IInventorySlotHolder getInitialInventory(IContentsListener listener) {
        InventorySlotHelper builder = InventorySlotHelper.forSide(this::getDirection);
        builder.addSlot(binSlot = ExtraBinInventorySlot.create(listener, tier));
        return builder.build();
    }

    public BTier getTier() {
        return tier;
    }

    public int getItemCount() {
        return binSlot.getCount();
    }

    public ExtraBinInventorySlot getBinSlot() {
        return binSlot;
    }

    @Override
    protected void onUpdateServer() {
        super.onUpdateServer();
        addTicks = Math.max(0, addTicks - 1);
        removeTicks = Math.max(0, removeTicks - 1);
        delayTicks = Math.max(0, delayTicks - 1);
        if (delayTicks == 0) {
            if (getActive()) {
                BlockEntity tile = WorldUtils.getTileEntity(getLevel(), getBlockPos().below());
                TileTransitRequest request = new TileTransitRequest(this, Direction.DOWN);
                request.addItem(binSlot.getBottomStack(), 0);
                TransitRequest.TransitResponse response;
                if (tile instanceof ExtraTileEntityLogisticalTransporterBase transporter) {
                    response = transporter.getTransmitter().insert(this, request, transporter.getTransmitter().getColor(), true, 0);
                } else {
                    response = request.addToInventory(tile, Direction.DOWN, 0, false);
                }
                if (!response.isEmpty()) {
                    int sendingAmount = response.getSendingAmount();
                    MekanismUtils.logMismatchedStackSize(binSlot.shrinkStack(sendingAmount, Action.EXECUTE), sendingAmount);
                }
                delayTicks = 10;
            }
        } else {
            delayTicks--;
        }
    }

    @Override
    public InteractionResult onSneakRightClick(Player player) {
        setActive(!getActive());
        Level world = getLevel();
        if (world != null) {
            world.playSound(null, getBlockPos().getX(), getBlockPos().getY(), getBlockPos().getZ(), SoundEvents.UI_BUTTON_CLICK.get(), SoundSource.BLOCKS, 0.3F, 1);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public InteractionResult onRightClick(Player player) {
        return InteractionResult.PASS;
    }

    public boolean toggleLock() {
        return setLocked(!binSlot.isLocked());
    }

    public boolean setLocked(boolean isLocked) {
        if (binSlot.setLocked(isLocked)) {
            if (getLevel() != null && !isRemote()) {
                sendUpdatePacket();
                markForSave();
                getLevel().playSound(null, getBlockPos().getX(), getBlockPos().getY(), getBlockPos().getZ(), SoundEvents.UI_BUTTON_CLICK.get(), SoundSource.BLOCKS, 0.3F, 1);
            }
            return true;
        }
        return false;
    }

    @Override
    public void parseUpgradeData(@NotNull IUpgradeData upgradeData) {
        if (upgradeData instanceof ExtraBinUpgradeData data) {
            redstone = data.redstone();
            ExtraBinInventorySlot previous = data.binSlot();
            binSlot.setStack(previous.getStack());
            binSlot.setLockStack(previous.getLockStack());
        } else if (upgradeData instanceof BinUpgradeData data) {
            redstone = data.redstone();
            BinInventorySlot previous = data.binSlot();
            binSlot.setStack(previous.getStack());
            binSlot.setLockStack(previous.getLockStack());
            binSlot.deserializeNBT(previous.serializeNBT());
        } else {
            super.parseUpgradeData(upgradeData);
        }
    }

    @NotNull
    @Override
    public ExtraBinUpgradeData getUpgradeData() {
        return new ExtraBinUpgradeData(redstone, getBinSlot());
    }

    @Override
    public void onContentsChanged() {
        super.onContentsChanged();
        if (level != null && !isRemote()) {
            sendUpdatePacket();
        }
    }

    @NotNull
    @Override
    public CompoundTag getReducedUpdateTag() {
        CompoundTag updateTag = super.getReducedUpdateTag();
        updateTag.put(NBTConstants.ITEM, binSlot.serializeNBT());
        return updateTag;
    }

    @Override
    public void handleUpdateTag(@NotNull CompoundTag tag) {
        super.handleUpdateTag(tag);
        NBTUtils.setCompoundIfPresent(tag, NBTConstants.ITEM, nbt -> binSlot.deserializeNBT(nbt));
    }

    //Methods relating to IComputerTile
    @ComputerMethod(methodDescription = "Get the maximum number of items the bin can contain.")
    int getCapacity() {
        return binSlot.getLimit(binSlot.getStack());
    }

    @ComputerMethod(methodDescription = "If true, the Bin is locked to a particular item type.")
    boolean isLocked() {
        return binSlot.isLocked();
    }

    @ComputerMethod(methodDescription = "Get the type of item the Bin is locked to (or Air if not locked)")
    ItemStack getLock() {
        return binSlot.getLockStack();
    }

    @ComputerMethod(methodDescription = "Lock the Bin to the currently stored item type. The Bin must not be creative, empty, or already locked")
    void lock() throws ComputerException {
        if (binSlot.isEmpty()) {
            throw new ComputerException("Empty bins cannot be locked!");
        } else if (!setLocked(true)) {
            throw new ComputerException("This bin is already locked!");
        }
    }

    @ComputerMethod(methodDescription = "Unlock the Bin's fixed item type. The Bin must not be creative, or already unlocked")
    void unlock() throws ComputerException {
        if (!setLocked(true)) {
            throw new ComputerException("This bin is not locked!");
        }
    }
}
