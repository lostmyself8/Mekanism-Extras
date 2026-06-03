package com.jerry.mekextras.common.network.to_client.transmitter;

import com.jerry.mekextras.MekanismExtras;
import com.jerry.mekextras.common.tile.transmitter.TileEntityExtraLogisticalTransporterBase;

import mekanism.common.content.network.transmitter.LogisticalTransporterBase;
import mekanism.common.content.transporter.TransporterStack;
import mekanism.common.network.IMekanismPacket;
import mekanism.common.network.PacketUtils;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap.Entry;
import it.unimi.dsi.fastutil.ints.Int2ObjectMaps;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntSet;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import org.jetbrains.annotations.NotNull;

public record ExtraPacketTransporterBatch(long pos, IntSet deletes, Int2ObjectMap<TransporterStack> updates) implements IMekanismPacket {

    public static final CustomPacketPayload.Type<ExtraPacketTransporterBatch> TYPE = new CustomPacketPayload.Type<>(MekanismExtras.rl("transporter_batch"));
    public static final StreamCodec<RegistryFriendlyByteBuf, ExtraPacketTransporterBatch> STREAM_CODEC = new StreamCodec<>() {

        @Override
        public @NotNull ExtraPacketTransporterBatch decode(@NotNull RegistryFriendlyByteBuf buffer) {
            long pos = buffer.readVarLong();
            int deleteCount = buffer.readVarInt();
            IntSet deletes = new IntOpenHashSet(deleteCount);
            for (int i = 0; i < deleteCount; i++) {
                deletes.add(buffer.readVarInt());
            }
            int updateCount = buffer.readVarInt();
            Int2ObjectMap<TransporterStack> updates = new Int2ObjectOpenHashMap<>(updateCount);
            for (int i = 0; i < updateCount; i++) {
                updates.put(buffer.readVarInt(), TransporterStack.STREAM_CODEC.decode(buffer));
            }
            return new ExtraPacketTransporterBatch(pos, deletes, updates);
        }

        @Override
        public void encode(@NotNull RegistryFriendlyByteBuf buffer, @NotNull ExtraPacketTransporterBatch packet) {
            buffer.writeVarLong(packet.pos);
            buffer.writeVarInt(packet.deletes.size());
            for (int toDelete : packet.deletes) {
                buffer.writeVarInt(toDelete);
            }
            buffer.writeVarInt(packet.updates.size());
            for (ObjectIterator<Entry<TransporterStack>> iterator = Int2ObjectMaps.fastIterator(packet.updates); iterator.hasNext();) {
                Entry<TransporterStack> entry = iterator.next();
                buffer.writeVarInt(entry.getIntKey());
                ExtraPacketTransporterSync.encodeClientSafeStack(buffer, entry.getValue());
            }
        }
    };

    public static ExtraPacketTransporterBatch create(long pos, IntSet deletes, Int2ObjectMap<TransporterStack> updates) {
        for (TransporterStack stack : updates.values()) {
            stack.updateForPos(pos);
        }
        return new ExtraPacketTransporterBatch(pos, deletes, updates);
    }

    @Override
    public @NotNull Type<ExtraPacketTransporterBatch> type() {
        return TYPE;
    }

    @Override
    public void handle(IPayloadContext context) {
        if (PacketUtils.blockEntity(context, pos) instanceof TileEntityExtraLogisticalTransporterBase tile) {
            LogisticalTransporterBase transporter = tile.getTransmitter();
            for (ObjectIterator<Entry<TransporterStack>> iterator = Int2ObjectMaps.fastIterator(updates); iterator.hasNext();) {
                Entry<TransporterStack> entry = iterator.next();
                transporter.addStack(entry.getIntKey(), entry.getValue());
            }
            for (int toDelete : deletes) {
                transporter.deleteStack(toDelete);
            }
        }
    }
}
