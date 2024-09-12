package com.jerry.mekextras.common.network.to_server.button;

import com.jerry.mekextras.MekanismExtras;
import com.jerry.mekextras.common.registry.ExtraContainerTypes;
import com.jerry.mekextras.common.tile.multiblock.TileEntityReinforcedInductionCasing;
import mekanism.common.MekanismLang;
import mekanism.common.block.attribute.Attribute;
import mekanism.common.block.attribute.AttributeGui;
import mekanism.common.network.IMekanismPacket;
import mekanism.common.tile.base.TileEntityMekanism;
import mekanism.common.util.WorldUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public record ExtraPacketTileButtonPress(ExtraPacketTileButtonPress.ClickedTileButton buttonClicked, BlockPos pos) implements IMekanismPacket<PlayPayloadContext> {
    public static final ResourceLocation ID = MekanismExtras.rl("extra_tile_button");

    public ExtraPacketTileButtonPress(FriendlyByteBuf buffer) {
        this(buffer.readEnum(ClickedTileButton.class), buffer.readBlockPos());
    }

    public ExtraPacketTileButtonPress(ClickedTileButton buttonClicked, BlockEntity tile) {
        this(buttonClicked, tile.getBlockPos());
    }

    @Override
    public void handle(PlayPayloadContext context) {
        Player player = context.player().orElse(null);
        if (player != null) {
            TileEntityMekanism tile = WorldUtils.getTileEntity(TileEntityMekanism.class, player.level(), pos);
            if (tile != null) {
                player.openMenu(buttonClicked.getProvider(tile), pos);
            }
        }
    }

    @Override
    public void write(FriendlyByteBuf buffer) {
        buffer.writeEnum(buttonClicked);
        buffer.writeBlockPos(pos);
    }

    @Override
    public @NotNull ResourceLocation id() {
        return ID;
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
        private final Function<TileEntityMekanism, MenuProvider> providerFromTile;

        ClickedTileButton(Function<TileEntityMekanism, MenuProvider> providerFromTile) {
            this.providerFromTile = providerFromTile;
        }

        public MenuProvider getProvider(TileEntityMekanism tile) {
            return providerFromTile.apply(tile);
        }
    }
}
