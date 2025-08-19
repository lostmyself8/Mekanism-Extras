package com.jerry.genextras.common.network.to_server;

import com.jerry.genextras.common.tile.naquadah.TileEntityNaquadahReactorCasing;
import com.jerry.genextras.common.tile.naquadah.TileEntityNaquadahReactorLogicAdapter;
import io.netty.buffer.ByteBuf;
import mekanism.api.functions.TriConsumer;
import mekanism.common.network.IMekanismPacket;
import mekanism.common.tile.base.TileEntityMekanism;
import mekanism.common.util.WorldUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ByIdMap;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.NotNull;

import java.util.function.IntFunction;

/**
 * Used for informing the server that an action happened in a GUI
 */
public record PacketGenExtraGuiInteract(GenExtraGuiInteraction interaction, BlockPos tilePosition, double extra) implements IMekanismPacket {

    public static final Type<PacketGenExtraGuiInteract> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath("generator_extras", "gui_interact"));
    public static final StreamCodec<ByteBuf, PacketGenExtraGuiInteract> STREAM_CODEC = StreamCodec.composite(
          GenExtraGuiInteraction.STREAM_CODEC, PacketGenExtraGuiInteract::interaction,
          BlockPos.STREAM_CODEC, PacketGenExtraGuiInteract::tilePosition,
          ByteBufCodecs.DOUBLE, PacketGenExtraGuiInteract::extra,
          PacketGenExtraGuiInteract::new
    );

    public PacketGenExtraGuiInteract(GenExtraGuiInteraction interaction, BlockEntity tile) {
        this(interaction, tile.getBlockPos());
    }

    public PacketGenExtraGuiInteract(GenExtraGuiInteraction interaction, BlockEntity tile, double extra) {
        this(interaction, tile.getBlockPos(), extra);
    }

    public PacketGenExtraGuiInteract(GenExtraGuiInteraction interaction, BlockPos tilePosition) {
        this(interaction, tilePosition, 0);
    }

    @NotNull
    @Override
    public CustomPacketPayload.Type<PacketGenExtraGuiInteract> type() {
        return TYPE;
    }

    @Override
    public void handle(IPayloadContext context) {
        Player player = context.player();
        TileEntityMekanism tile = WorldUtils.getTileEntity(TileEntityMekanism.class, player.level(), tilePosition);
        if (tile != null) {
            interaction.consume(tile, player, extra);
        }
    }

    public enum GenExtraGuiInteraction {
        INJECTION_RATE((tile, player, extra) -> {
            if (tile instanceof TileEntityNaquadahReactorCasing reactorBlock) {
                reactorBlock.setInjectionRateFromPacket((int) Math.round(extra));
            }
        }),
        LOGIC_TYPE((tile, player, extra) -> {
            if (tile instanceof TileEntityNaquadahReactorLogicAdapter logicAdapter) {
                logicAdapter.setLogicTypeFromPacket(TileEntityNaquadahReactorLogicAdapter.NaquadahReactorLogic.BY_ID.apply((int) Math.round(extra)));
            }
        });

        public static final IntFunction<GenExtraGuiInteraction> BY_ID = ByIdMap.continuous(GenExtraGuiInteraction::ordinal, values(), ByIdMap.OutOfBoundsStrategy.WRAP);
        public static final StreamCodec<ByteBuf, GenExtraGuiInteraction> STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, GenExtraGuiInteraction::ordinal);

        private final TriConsumer<TileEntityMekanism, Player, Double> consumerForTile;

        GenExtraGuiInteraction(TriConsumer<TileEntityMekanism, Player, Double> consumerForTile) {
            this.consumerForTile = consumerForTile;
        }

        public void consume(TileEntityMekanism tile, Player player, double extra) {
            consumerForTile.accept(tile, player, extra);
        }
    }
}