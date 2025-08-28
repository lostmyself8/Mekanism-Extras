package com.jerry.generator_extras.common.network.to_server;

import com.jerry.generator_extras.common.tile.naquadah.TileEntityNaquadahReactorController;
import com.jerry.generator_extras.common.tile.naquadah.TileEntityNaquadahReactorLogicAdapter;
import mekanism.api.functions.TriConsumer;
import mekanism.common.network.IMekanismPacket;
import mekanism.common.tile.base.TileEntityMekanism;
import mekanism.common.util.WorldUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;

public class ExtraPacketGeneratorsGuiInteract implements IMekanismPacket {

    private final ExtraGeneratorsGuiInteraction interaction;
    private final BlockPos tilePosition;
    private final double extra;

//    public ExtraPacketGeneratorsGuiInteract(ExtraGeneratorsGuiInteraction interaction, BlockEntity tile) {
//        this(interaction, tile.getBlockPos());
//    }

    public ExtraPacketGeneratorsGuiInteract(ExtraGeneratorsGuiInteraction interaction, BlockEntity tile, double extra) {
        this(interaction, tile.getBlockPos(), extra);
    }

//    public ExtraPacketGeneratorsGuiInteract(ExtraGeneratorsGuiInteraction interaction, BlockPos tilePosition) {
//        this(interaction, tilePosition, 0);
//    }

    public ExtraPacketGeneratorsGuiInteract(ExtraGeneratorsGuiInteraction interaction, BlockPos tilePosition, double extra) {
        this.interaction = interaction;
        this.tilePosition = tilePosition;
        this.extra = extra;
    }
    @Override
    public void handle(NetworkEvent.Context context) {
        Player player = context.getSender();
        if (player != null) {
            TileEntityMekanism tile = WorldUtils.getTileEntity(TileEntityMekanism.class, player.level(), tilePosition);
            if (tile != null) {
                interaction.consume(tile, player, extra);
            }
        }
    }

    @Override
    public void encode(FriendlyByteBuf buffer) {
        buffer.writeEnum(interaction);
        buffer.writeBlockPos(tilePosition);
        buffer.writeDouble(extra);
    }

    public static ExtraPacketGeneratorsGuiInteract decode(FriendlyByteBuf buffer) {
        return new ExtraPacketGeneratorsGuiInteract(buffer.readEnum(ExtraGeneratorsGuiInteraction.class), buffer.readBlockPos(), buffer.readDouble());
    }

    public enum ExtraGeneratorsGuiInteraction {
        INJECTION_RATE((tile, player, extra) -> {
            if (tile instanceof TileEntityNaquadahReactorController reactorBlock) {
                reactorBlock.setInjectionRateFromPacket((int) Math.round(extra));
            }
        }),
        LOGIC_TYPE((tile, player, extra) -> {
            if (tile instanceof TileEntityNaquadahReactorLogicAdapter logicAdapter) {
                logicAdapter.setLogicTypeFromPacket(TileEntityNaquadahReactorLogicAdapter.NaquadahReactorLogic.byIndexStatic((int) Math.round(extra)));
            }
        });

        private final TriConsumer<TileEntityMekanism, Player, Double> consumerForTile;

        ExtraGeneratorsGuiInteraction(TriConsumer<TileEntityMekanism, Player, Double> consumerForTile) {
            this.consumerForTile = consumerForTile;
        }

        public void consume(TileEntityMekanism tile, Player player, double extra) {
            consumerForTile.accept(tile, player, extra);
        }
    }
}
