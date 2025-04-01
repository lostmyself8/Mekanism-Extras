package com.jerry.mekanism_extras.common.network.to_server;

import com.jerry.mekanism_extras.common.tile.multiblock.TileEntityReinforcedInductionCasing;
import com.jerry.mekanism_extras.common.registry.ExtraContainerTypes;
import mekanism.common.MekanismLang;
import mekanism.common.block.attribute.Attribute;
import mekanism.common.block.attribute.AttributeGui;
import mekanism.common.network.IMekanismPacket;
import mekanism.common.tile.base.TileEntityMekanism;
import mekanism.common.util.WorldUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkHooks;

import java.util.function.BiFunction;

public class ExtraPacketGuiButtonPress implements IMekanismPacket {
    private final Type type;
    private ClickedTileButton tileButton;
    private int extra;
    private BlockPos tilePosition;

    public ExtraPacketGuiButtonPress(ClickedTileButton buttonClicked, BlockEntity tile) {
        this(buttonClicked, tile.getBlockPos());
    }

    public ExtraPacketGuiButtonPress(ClickedTileButton buttonClicked, BlockPos tilePosition) {
        this(buttonClicked, tilePosition, 0);
    }

    public ExtraPacketGuiButtonPress(ClickedTileButton buttonClicked, BlockPos tilePosition, int extra) {
        type = Type.TILE;
        this.tileButton = buttonClicked;
        this.tilePosition = tilePosition;
        this.extra = extra;
    }

    @Override
    public void handle(NetworkEvent.Context context) {
        ServerPlayer player = context.getSender();
        if (player == null) {
            return;
        }
        if (type == Type.TILE) {
            TileEntityMekanism tile = WorldUtils.getTileEntity(TileEntityMekanism.class, player.level(), tilePosition);
            if (tile != null) {
                MenuProvider provider = tileButton.getProvider(tile, extra);
                if (provider != null) {
                    //Ensure valid data
                    NetworkHooks.openScreen(player, provider, buf -> {
                        buf.writeBlockPos(tilePosition);
                        buf.writeVarInt(extra);
                    });
                }
            }
        }
    }

    @Override
    public void encode(FriendlyByteBuf buffer) {
        buffer.writeEnum(type);
        if (type == Type.TILE) {
            buffer.writeEnum(tileButton);
            buffer.writeBlockPos(tilePosition);
            buffer.writeVarInt(extra);
        }
    }

    public static ExtraPacketGuiButtonPress decode(FriendlyByteBuf buffer) {
        return switch (buffer.readEnum(Type.class)) {
            case TILE -> new ExtraPacketGuiButtonPress(buffer.readEnum(ClickedTileButton.class), buffer.readBlockPos(), buffer.readVarInt());
        };
    }

    public enum ClickedTileButton {
        BACK_BUTTON((tile, extra) -> {
            //Special handling to basically reset to the tiles default gui container
            AttributeGui attributeGui = Attribute.get(tile.getBlockType(), AttributeGui.class);
            if (attributeGui != null) {
                return attributeGui.getProvider(tile);
            }
            return null;
        }),
        TAB_MAIN((tile, extra) -> {
            if (tile instanceof TileEntityReinforcedInductionCasing) {
                return ExtraContainerTypes.REINFORCED_INDUCTION_MATRIX.getProvider(MekanismLang.MATRIX, tile);
            }
            return null;
        }),
        TAB_STATS((tile, extra) -> {
            if (tile instanceof TileEntityReinforcedInductionCasing) {
                return ExtraContainerTypes.MATRIX_STATS.getProvider(MekanismLang.MATRIX_STATS, tile);
            }
            return null;
        });
        private final BiFunction<TileEntityMekanism, Integer, MenuProvider> providerFromTile;

        ClickedTileButton(BiFunction<TileEntityMekanism, Integer, MenuProvider> providerFromTile) {
            this.providerFromTile = providerFromTile;
        }

        public MenuProvider getProvider(TileEntityMekanism tile, int extra) {
            return providerFromTile.apply(tile, extra);
        }
    }

    public enum Type {
        TILE
    }
}
