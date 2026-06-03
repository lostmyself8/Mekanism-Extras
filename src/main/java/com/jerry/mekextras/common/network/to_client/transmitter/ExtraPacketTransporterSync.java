package com.jerry.mekextras.common.network.to_client.transmitter;

import com.jerry.mekextras.MekanismExtras;
import com.jerry.mekextras.common.tile.transmitter.TileEntityExtraLogisticalTransporterBase;

import mekanism.common.content.transporter.TransporterStack;
import mekanism.common.network.IMekanismPacket;
import mekanism.common.network.PacketUtils;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import org.jetbrains.annotations.NotNull;

public record ExtraPacketTransporterSync(long pos, int stackId, TransporterStack stack) implements IMekanismPacket {

    public static final CustomPacketPayload.Type<ExtraPacketTransporterSync> TYPE = new CustomPacketPayload.Type<>(MekanismExtras.rl("transporter_sync"));
    public static final StreamCodec<RegistryFriendlyByteBuf, ExtraPacketTransporterSync> STREAM_CODEC = new StreamCodec<>() {

        @Override
        public @NotNull ExtraPacketTransporterSync decode(@NotNull RegistryFriendlyByteBuf buffer) {
            return new ExtraPacketTransporterSync(buffer.readVarLong(), buffer.readVarInt(), TransporterStack.STREAM_CODEC.decode(buffer));
        }

        @Override
        public void encode(@NotNull RegistryFriendlyByteBuf buffer, @NotNull ExtraPacketTransporterSync packet) {
            buffer.writeVarLong(packet.pos);
            buffer.writeVarInt(packet.stackId);
            encodeClientSafeStack(buffer, packet.stack);
        }
    };

    public static ExtraPacketTransporterSync create(long pos, int stackId, TransporterStack stack) {
        return new ExtraPacketTransporterSync(pos, stackId, stack.updateForPos(pos));
    }

    static void encodeClientSafeStack(RegistryFriendlyByteBuf buffer, TransporterStack stack) {
        ItemStack original = stack.itemStack;
        if (!original.isEmpty() && original.getCount() > original.getMaxStackSize()) {
            stack.itemStack = original.copyWithCount(Math.min(original.getCount(), original.getMaxStackSize()));
            TransporterStack.STREAM_CODEC.encode(buffer, stack);
            stack.itemStack = original;
        } else {
            TransporterStack.STREAM_CODEC.encode(buffer, stack);
        }
    }

    @Override
    public @NotNull Type<ExtraPacketTransporterSync> type() {
        return TYPE;
    }

    @Override
    public void handle(IPayloadContext context) {
        if (PacketUtils.blockEntity(context, pos) instanceof TileEntityExtraLogisticalTransporterBase tile) {
            tile.getTransmitter().addStack(stackId, stack);
        }
    }
}
