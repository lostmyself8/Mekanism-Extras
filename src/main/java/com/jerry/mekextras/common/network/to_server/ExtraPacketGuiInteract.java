package com.jerry.mekextras.common.network.to_server;

import com.jerry.mekextras.MekanismExtras;
import com.jerry.mekextras.common.integration.mekaf.tile.factory.base.TileEntityExtraAdvancedFactoryBase;
import com.jerry.mekextras.common.integration.mekmm.tile.factory.TileEntityExtraMoreMachineFactory;
import com.jerry.mekextras.common.tile.factory.TileEntityExtraFactory;

import mekanism.api.functions.TriConsumer;
import mekanism.common.network.IMekanismPacket;
import mekanism.common.tile.base.TileEntityMekanism;
import mekanism.common.util.WorldUtils;

import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ByIdMap;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import io.netty.buffer.ByteBuf;
import org.jetbrains.annotations.NotNull;

import java.util.function.IntFunction;

/**
 * Used for informing the server that an action happened in a GUI
 */
public class ExtraPacketGuiInteract implements IMekanismPacket {

    public static final Type<ExtraPacketGuiInteract> TYPE = new Type<>(MekanismExtras.rl("gui_interact"));
    public static final StreamCodec<RegistryFriendlyByteBuf, ExtraPacketGuiInteract> STREAM_CODEC = ExtraInteractionType.STREAM_CODEC.<RegistryFriendlyByteBuf>cast()
            .dispatch(packet -> packet.interactionType, type -> switch (type) {
                case ENTITY, ITEM -> null;
                case INT -> StreamCodec.composite(
                        ExtraGuiInteraction.STREAM_CODEC, packet -> packet.interaction,
                        BlockPos.STREAM_CODEC, packet -> packet.tilePosition,
                        // TODO - 1.18?: Eventually we may want to try to make some form of this that can compact
                        // negatives better as well
                        ByteBufCodecs.VAR_INT, packet -> packet.extra,
                        ExtraPacketGuiInteract::new);
                // case ITEM -> StreamCodec.composite(
                // GuiInteractionItem.STREAM_CODEC, packet -> packet.itemInteraction,
                // BlockPos.STREAM_CODEC, packet -> packet.tilePosition,
                // ItemStack.OPTIONAL_STREAM_CODEC, packet -> packet.extraItem,
                // ExtraPacketGuiInteract::new
                // );
            });

    private final ExtraInteractionType interactionType;

    private ExtraGuiInteraction interaction;
    // private GuiInteractionItem itemInteraction;
    // private PacketGuiInteract.GuiInteractionEntity entityInteraction;
    private BlockPos tilePosition;
    private ItemStack extraItem;
    private int entityID;
    private int extra;

    // public PacketGuiInteract(PacketGuiInteract.GuiInteractionEntity interaction, Entity entity) {
    // this(interaction, entity, 0);
    // }
    //
    // public PacketGuiInteract(PacketGuiInteract.GuiInteractionEntity interaction, Entity entity, int extra) {
    // this(interaction, entity.getId(), extra);
    // }
    //
    // public PacketGuiInteract(PacketGuiInteract.GuiInteractionEntity interaction, int entityID, int extra) {
    // this.interactionType = PacketGuiInteract.MMInteractionType.ENTITY;
    // this.entityInteraction = interaction;
    // this.entityID = entityID;
    // this.extra = extra;
    // }

    public ExtraPacketGuiInteract(ExtraGuiInteraction interaction, BlockEntity tile) {
        this(interaction, tile.getBlockPos());
    }

    public ExtraPacketGuiInteract(ExtraGuiInteraction interaction, BlockEntity tile, int extra) {
        this(interaction, tile.getBlockPos(), extra);
    }

    public ExtraPacketGuiInteract(ExtraGuiInteraction interaction, BlockPos tilePosition) {
        this(interaction, tilePosition, 0);
    }

    public ExtraPacketGuiInteract(ExtraGuiInteraction interaction, BlockPos tilePosition, int extra) {
        this.interactionType = ExtraInteractionType.INT;
        this.interaction = interaction;
        this.tilePosition = tilePosition;
        this.extra = extra;
    }

    // public ExtraPacketGuiInteract(GuiInteractionItem interaction, BlockEntity tile, ItemStack stack) {
    // this(interaction, tile.getBlockPos(), stack);
    // }
    //
    // public ExtraPacketGuiInteract(GuiInteractionItem interaction, BlockPos tilePosition, ItemStack stack) {
    // this.interactionType = ExtraInteractionType.ITEM;
    // this.itemInteraction = interaction;
    // this.tilePosition = tilePosition;
    // this.extraItem = stack;
    // }

    @Override
    public void handle(IPayloadContext context) {
        Player player = context.player();
        if (interactionType == ExtraInteractionType.ENTITY) {
            Entity entity = player.level().getEntity(entityID);
            // if (entity != null) {
            // entityInteraction.consume(entity, player, extra);
            // }
        } else {
            TileEntityMekanism tile = WorldUtils.getTileEntity(TileEntityMekanism.class, player.level(), tilePosition);
            if (tile != null) {
                if (interactionType == ExtraInteractionType.INT) {
                    interaction.consume(tile, player, extra);
                }
                // else if (interactionType == ExtraInteractionType.ITEM) {
                // itemInteraction.consume(tile, player, extraItem);
                // }
            }
        }
    }

    @Override
    public @NotNull Type<ExtraPacketGuiInteract> type() {
        return TYPE;
    }

    // public enum GuiInteractionItem {
    // ;
    //
    // public static final IntFunction<GuiInteractionItem> BY_ID = ByIdMap.continuous(GuiInteractionItem::ordinal,
    // values(), ByIdMap.OutOfBoundsStrategy.WRAP);
    // public static final StreamCodec<ByteBuf, GuiInteractionItem> STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID,
    // GuiInteractionItem::ordinal);
    //
    // private final TriConsumer<TileEntityMekanism, Player, ItemStack> consumerForTile;
    //
    // GuiInteractionItem(TriConsumer<TileEntityMekanism, Player, ItemStack> consumerForTile) {
    // this.consumerForTile = consumerForTile;
    // }
    //
    // public void consume(TileEntityMekanism tile, Player player, ItemStack stack) {
    // consumerForTile.accept(tile, player, stack);
    // }
    // }

    public enum ExtraGuiInteraction {

        AUTO_SORT_BUTTON((tile, player, extra) -> {
            if (tile instanceof TileEntityExtraFactory<?> factory) {
                factory.toggleSorting();
            } else if (MekanismExtras.hooks.mekmm.isLoaded()) {
                if (tile instanceof TileEntityExtraAdvancedFactoryBase<?, ?> factory) factory.toggleSorting();
                if (tile instanceof TileEntityExtraMoreMachineFactory<?> factory) factory.toggleSorting();
            }
        }),
        ;

        public static final IntFunction<ExtraGuiInteraction> BY_ID = ByIdMap.continuous(ExtraGuiInteraction::ordinal, values(), ByIdMap.OutOfBoundsStrategy.WRAP);
        public static final StreamCodec<ByteBuf, ExtraGuiInteraction> STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, ExtraGuiInteraction::ordinal);

        private final TriConsumer<TileEntityMekanism, Player, Integer> consumerForTile;

        ExtraGuiInteraction(TriConsumer<TileEntityMekanism, Player, Integer> consumerForTile) {
            this.consumerForTile = consumerForTile;
        }

        public void consume(TileEntityMekanism tile, Player player, int extra) {
            consumerForTile.accept(tile, player, extra);
        }
    }

    private enum ExtraInteractionType {

        ENTITY,
        ITEM,
        INT;

        public static final IntFunction<ExtraInteractionType> BY_ID = ByIdMap.continuous(ExtraInteractionType::ordinal, values(), ByIdMap.OutOfBoundsStrategy.WRAP);
        public static final StreamCodec<ByteBuf, ExtraInteractionType> STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, ExtraInteractionType::ordinal);
    }
}
