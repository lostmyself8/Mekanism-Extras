package com.jerry.mekextras.common.network.to_server.button;

import com.jerry.mekextras.MekanismExtras;
import com.jerry.mekextras.common.registries.ExtraContainerTypes;
import com.jerry.mekextras.common.tile.multiblock.TileEntityReinforcedInductionCasing;

import com.jerry.genextras.common.GenExtraLang;
import com.jerry.genextras.common.registries.GenExtraContainerTypes;
import com.jerry.genextras.common.tile.naquadah.TileEntityNaquadahReactorController;

import mekanism.common.MekanismLang;
import mekanism.common.block.attribute.Attribute;
import mekanism.common.block.attribute.AttributeGui;
import mekanism.common.network.IMekanismPacket;
import mekanism.common.tile.base.TileEntityMekanism;
import mekanism.common.util.WorldUtils;

import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.util.ByIdMap;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import io.netty.buffer.ByteBuf;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.IntFunction;

public record ExtraPacketTileButtonPress(ClickedTileButton buttonClicked,
                                         BlockPos pos)
        implements IMekanismPacket {

    public static final Type<ExtraPacketTileButtonPress> TYPE = new Type<>(MekanismExtras.rl("tile_button"));
    public static final StreamCodec<ByteBuf, ExtraPacketTileButtonPress> STREAM_CODEC = StreamCodec.composite(ClickedTileButton.STREAM_CODEC, ExtraPacketTileButtonPress::buttonClicked, BlockPos.STREAM_CODEC, ExtraPacketTileButtonPress::pos, ExtraPacketTileButtonPress::new);

    public ExtraPacketTileButtonPress(ClickedTileButton buttonClicked, BlockEntity tile) {
        this(buttonClicked, tile.getBlockPos());
    }

    public ExtraPacketTileButtonPress(ClickedTileButton buttonClicked, BlockPos pos) {
        this.buttonClicked = buttonClicked;
        this.pos = pos;
    }

    @NotNull
    public CustomPacketPayload.@NotNull Type<ExtraPacketTileButtonPress> type() {
        return TYPE;
    }

    @Override
    public void handle(IPayloadContext context) {
        Player player = context.player();
        TileEntityMekanism tile = WorldUtils.getTileEntity(TileEntityMekanism.class, player.level(), pos);
        MenuProvider provider = buttonClicked.getProvider(tile);
        if (provider != null) {
            player.openMenu(provider, buf -> {
                buf.writeBlockPos(pos);
                buttonClicked.encodeExtraData(buf, tile);
            });
        }
    }

    public ClickedTileButton buttonClicked() {
        return this.buttonClicked;
    }

    public BlockPos pos() {
        return this.pos;
    }

    public enum ClickedTileButton {

        BACK_BUTTON(tile -> {
            // Special handling to basically reset to the tiles default gui container
            AttributeGui attributeGui = Attribute.get(tile.getBlockHolder(), AttributeGui.class);
            return attributeGui != null ? attributeGui.getProvider(tile, false) : null;
        }),
        TAB_MAIN(tile -> {
            if (tile instanceof TileEntityReinforcedInductionCasing) {
                return ExtraContainerTypes.REINFORCED_INDUCTION_MATRIX.getProvider(MekanismLang.MATRIX, tile);
            }
            return null;
        }),
        TAB_HEAT(tile -> GenExtraContainerTypes.NAQUADAH_REACTOR_HEAT.getProvider(GenExtraLang.NAQUADAH_REACTOR, tile)),
        TAB_FUEL(tile -> GenExtraContainerTypes.NAQUADAH_REACTOR_FUEL.getProvider(GenExtraLang.NAQUADAH_REACTOR, tile)),
        TAB_STATS(tile -> {
            if (tile instanceof TileEntityReinforcedInductionCasing) {
                return ExtraContainerTypes.REINFORCED_MATRIX_STATS.getProvider(MekanismLang.MATRIX_STATS, tile);
            } else if (tile instanceof TileEntityNaquadahReactorController) {
                return GenExtraContainerTypes.NAQUADAH_REACTOR_STATS.getProvider(GenExtraLang.NAQUADAH_REACTOR, tile);
            }
            return null;
        });

        public static final IntFunction<ClickedTileButton> BY_ID = ByIdMap.continuous(Enum::ordinal, values(), ByIdMap.OutOfBoundsStrategy.WRAP);
        public static final StreamCodec<ByteBuf, ClickedTileButton> STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, Enum::ordinal);
        private final Function<TileEntityMekanism, MenuProvider> providerFromTile;
        @Nullable
        private final BiConsumer<RegistryFriendlyByteBuf, TileEntityMekanism> extraEncodingData;

        ClickedTileButton(Function<TileEntityMekanism, @Nullable MenuProvider> providerFromTile) {
            this(providerFromTile, null);
        }

        ClickedTileButton(Function<TileEntityMekanism, MenuProvider> providerFromTile, @Nullable BiConsumer<RegistryFriendlyByteBuf, TileEntityMekanism> extraEncodingData) {
            this.providerFromTile = providerFromTile;
            this.extraEncodingData = extraEncodingData;
        }

        public MenuProvider getProvider(TileEntityMekanism tile) {
            return tile == null ? null : providerFromTile.apply(tile);
        }

        private void encodeExtraData(RegistryFriendlyByteBuf buffer, TileEntityMekanism tile) {
            if (extraEncodingData != null) {
                extraEncodingData.accept(buffer, tile);
            }
        }
    }
}
