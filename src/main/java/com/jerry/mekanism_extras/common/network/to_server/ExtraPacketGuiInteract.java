package com.jerry.mekanism_extras.common.network.to_server;

import com.jerry.mekanism_extras.common.tile.factory.TileEntityAdvancedFactory;
import mekanism.api.functions.TriConsumer;
import mekanism.common.network.IMekanismPacket;
import mekanism.common.tile.base.TileEntityMekanism;
import mekanism.common.util.WorldUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;

/**
 * Used for informing the server that an action happened in a GUI
 */
public class ExtraPacketGuiInteract implements IMekanismPacket {

    private final Type interactionType;

    private ExtraGuiInteraction interaction;
    private GuiInteractionItem itemInteraction;
    private GuiInteractionEntity entityInteraction;
    private BlockPos tilePosition;
    private ItemStack extraItem;
    private int entityID;
    private int extra;

    public ExtraPacketGuiInteract(GuiInteractionEntity interaction, Entity entity) {
        this(interaction, entity, 0);
    }

    public ExtraPacketGuiInteract(GuiInteractionEntity interaction, Entity entity, int extra) {
        this(interaction, entity.getId(), extra);
    }

    public ExtraPacketGuiInteract(GuiInteractionEntity interaction, int entityID, int extra) {
        this.interactionType = Type.ENTITY;
        this.entityInteraction = interaction;
        this.entityID = entityID;
        this.extra = extra;
    }

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
        this.interactionType = Type.INT;
        this.interaction = interaction;
        this.tilePosition = tilePosition;
        this.extra = extra;
    }

    public ExtraPacketGuiInteract(GuiInteractionItem interaction, BlockEntity tile, ItemStack stack) {
        this(interaction, tile.getBlockPos(), stack);
    }

    public ExtraPacketGuiInteract(GuiInteractionItem interaction, BlockPos tilePosition, ItemStack stack) {
        this.interactionType = Type.ITEM;
        this.itemInteraction = interaction;
        this.tilePosition = tilePosition;
        this.extraItem = stack;
    }

    @Override
    public void handle(NetworkEvent.Context context) {
        Player player = context.getSender();
        if (player != null) {
            if (interactionType == Type.ENTITY) {
                Entity entity = player.level().getEntity(entityID);
                if (entity != null) {
                    entityInteraction.consume(entity, player, extra);
                }
            } else {
                TileEntityMekanism tile = WorldUtils.getTileEntity(TileEntityMekanism.class, player.level(), tilePosition);
                if (tile != null) {
                    if (interactionType == Type.INT) {
                        interaction.consume(tile, player, extra);
                    } else if (interactionType == Type.ITEM) {
                        itemInteraction.consume(tile, player, extraItem);
                    }
                }
            }
        }
    }

    @Override
    public void encode(FriendlyByteBuf buffer) {
        buffer.writeEnum(interactionType);
        switch (interactionType) {
            case ENTITY -> {
                buffer.writeEnum(entityInteraction);
                buffer.writeVarInt(entityID);
                buffer.writeVarInt(extra);
            }
            case INT -> {
                buffer.writeEnum(interaction);
                buffer.writeBlockPos(tilePosition);
                //TODO - 1.18?: Eventually we may want to try to make some form of this that can compact negatives better as well
                buffer.writeVarInt(extra);
            }
            case ITEM -> {
                buffer.writeEnum(itemInteraction);
                buffer.writeBlockPos(tilePosition);
                buffer.writeItem(extraItem);
            }
        }
    }

    public static ExtraPacketGuiInteract decode(FriendlyByteBuf buffer) {
        return switch (buffer.readEnum(Type.class)) {
            case ENTITY -> new ExtraPacketGuiInteract(buffer.readEnum(GuiInteractionEntity.class), buffer.readVarInt(), buffer.readVarInt());
            case INT -> new ExtraPacketGuiInteract(buffer.readEnum(ExtraGuiInteraction.class), buffer.readBlockPos(), buffer.readVarInt());
            case ITEM -> new ExtraPacketGuiInteract(buffer.readEnum(GuiInteractionItem.class), buffer.readBlockPos(), buffer.readItem());
        };
    }

    public enum GuiInteractionItem {
        ;

        private final TriConsumer<TileEntityMekanism, Player, ItemStack> consumerForTile;

        GuiInteractionItem(TriConsumer<TileEntityMekanism, Player, ItemStack> consumerForTile) {
            this.consumerForTile = consumerForTile;
        }

        public void consume(TileEntityMekanism tile, Player player, ItemStack stack) {
            consumerForTile.accept(tile, player, stack);
        }
    }

    public enum ExtraGuiInteraction {//TODO: Cleanup this enum/the elements in it as it is rather disorganized order wise currently
        AUTO_SORT_BUTTON((tile, player, extra) -> {
            if (tile instanceof TileEntityAdvancedFactory<?> factory) {
                factory.toggleSorting();
            }
        });

        private final TriConsumer<TileEntityMekanism, Player, Integer> consumerForTile;

        ExtraGuiInteraction(TriConsumer<TileEntityMekanism, Player, Integer> consumerForTile) {
            this.consumerForTile = consumerForTile;
        }

        public void consume(TileEntityMekanism tile, Player player, int extra) {
            consumerForTile.accept(tile, player, extra);
        }
    }

    public enum GuiInteractionEntity {
        ;

        private final TriConsumer<Entity, Player, Integer> consumerForEntity;

        GuiInteractionEntity(TriConsumer<Entity, Player, Integer> consumerForEntity) {
            this.consumerForEntity = consumerForEntity;
        }

        public void consume(Entity entity, Player player, int extra) {
            consumerForEntity.accept(entity, player, extra);
        }
    }

    private enum Type {
        ENTITY,
        ITEM,
        INT
    }
}
