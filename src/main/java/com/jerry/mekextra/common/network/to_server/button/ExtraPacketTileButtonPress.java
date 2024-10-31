package com.jerry.mekextra.common.network.to_server.button;

import com.jerry.mekextra.MekanismExtras;
import com.jerry.mekextra.common.registry.ExtraContainerTypes;
import com.jerry.mekextra.common.tile.multiblock.TileEntityReinforcedInductionCasing;
import io.netty.buffer.ByteBuf;
import mekanism.common.MekanismLang;
import mekanism.common.block.attribute.Attribute;
import mekanism.common.block.attribute.AttributeGui;
import mekanism.common.network.IMekanismPacket;
import mekanism.common.tile.base.TileEntityMekanism;
import mekanism.common.util.WorldUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.util.ByIdMap;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;
import java.util.function.IntFunction;

public record ExtraPacketTileButtonPress(ExtraPacketTileButtonPress.ClickedTileButton buttonClicked, BlockPos pos) implements IMekanismPacket {
    public static final CustomPacketPayload.Type<ExtraPacketTileButtonPress> TYPE = new CustomPacketPayload.Type<>(MekanismExtras.rl("tile_button"));
    public static final StreamCodec<ByteBuf, ExtraPacketTileButtonPress> STREAM_CODEC;

    public ExtraPacketTileButtonPress(ClickedTileButton buttonClicked, BlockEntity tile) {
        this(buttonClicked, tile.getBlockPos());
    }

    public ExtraPacketTileButtonPress(ExtraPacketTileButtonPress.ClickedTileButton buttonClicked, BlockPos pos) {
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
        if (tile != null) {
            player.openMenu(buttonClicked.getProvider(tile), pos);
        }
    }

    public ExtraPacketTileButtonPress.ClickedTileButton buttonClicked() {
        return this.buttonClicked;
    }

    public BlockPos pos() {
        return this.pos;
    }

    static {
        STREAM_CODEC = StreamCodec.composite(ExtraPacketTileButtonPress.ClickedTileButton.STREAM_CODEC, ExtraPacketTileButtonPress::buttonClicked, BlockPos.STREAM_CODEC, ExtraPacketTileButtonPress::pos, ExtraPacketTileButtonPress::new);
    }

    public enum ClickedTileButton {
        BACK_BUTTON(tile -> {
            //Special handling to basically reset to the tiles default gui container
            AttributeGui attributeGui = Attribute.get(tile.getBlockType(), AttributeGui.class);
            return attributeGui != null ? attributeGui.getProvider(tile, false) : null;
        }),
        TAB_MAIN(tile -> {
            if (tile instanceof TileEntityReinforcedInductionCasing) {
                return ExtraContainerTypes.REINFORCED_INDUCTION_MATRIX.getProvider(MekanismLang.MATRIX, tile);
            }
            return null;
        }),
        TAB_STATS(tile -> {
            if (tile instanceof TileEntityReinforcedInductionCasing) {
                return ExtraContainerTypes.REINFORCED_MATRIX_STATS.getProvider(MekanismLang.MATRIX_STATS, tile);
            }
            return null;
        });

        public static final IntFunction<ExtraPacketTileButtonPress.ClickedTileButton> BY_ID = ByIdMap.continuous(Enum::ordinal, values(), ByIdMap.OutOfBoundsStrategy.WRAP);
        public static final StreamCodec<ByteBuf, ExtraPacketTileButtonPress.ClickedTileButton> STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, Enum::ordinal);
        private final Function<TileEntityMekanism, MenuProvider> providerFromTile;

        ClickedTileButton(Function<TileEntityMekanism, MenuProvider> providerFromTile) {
            this.providerFromTile = providerFromTile;
        }

        public MenuProvider getProvider(TileEntityMekanism tile) {
            return providerFromTile.apply(tile);
        }
    }
}
