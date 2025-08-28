package com.jerry.generator_extras.common.network.to_server;

import com.jerry.generator_extras.common.ExtraGenLang;
import com.jerry.generator_extras.common.tile.naquadah.TileEntityNaquadahReactorController;
import com.jerry.generator_extras.common.genregistry.ExtraGenContainerTypes;
import mekanism.common.network.IMekanismPacket;
import mekanism.common.tile.base.TileEntityMekanism;
import mekanism.common.util.WorldUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkHooks;

import java.util.function.BiFunction;

public class ExtraPacketGeneratorsGuiButtonPress implements IMekanismPacket {

    private final ExtraClickedGeneratorsTileButton tileButton;
    private final int extra;
    private final BlockPos tilePosition;

    public ExtraPacketGeneratorsGuiButtonPress(ExtraClickedGeneratorsTileButton buttonClicked, BlockPos tilePosition) {
        this(buttonClicked, tilePosition, 0);
    }

    public ExtraPacketGeneratorsGuiButtonPress(ExtraClickedGeneratorsTileButton buttonClicked, BlockPos tilePosition, int extra) {
        this.tileButton = buttonClicked;
        this.tilePosition = tilePosition;
        this.extra = extra;
    }
    @Override
    public void handle(NetworkEvent.Context context) {
        ServerPlayer player = context.getSender();
        if (player != null) {//If we are on the server (the only time we should be receiving this packet), let forge handle switching the Gui
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
        buffer.writeEnum(tileButton);
        buffer.writeBlockPos(tilePosition);
        buffer.writeVarInt(extra);
    }

    public static ExtraPacketGeneratorsGuiButtonPress decode(FriendlyByteBuf buffer) {
        return new ExtraPacketGeneratorsGuiButtonPress(buffer.readEnum(ExtraClickedGeneratorsTileButton.class), buffer.readBlockPos(), buffer.readVarInt());
    }

    public enum ExtraClickedGeneratorsTileButton {
//        TAB_MAIN((tile, extra) -> {
//            if (tile instanceof TileEntityTurbineCasing) {
//                return GeneratorsContainerTypes.INDUSTRIAL_TURBINE.getProvider(GeneratorsLang.TURBINE, tile);
//            } else if (tile instanceof TileEntityFissionReactorCasing) {
//                return GeneratorsContainerTypes.FISSION_REACTOR.getProvider(GeneratorsLang.FISSION_REACTOR, tile);
//            }
//            return null;
//        }),
        TAB_HEAT((tile, extra) -> ExtraGenContainerTypes.NAQUADAH_REACTOR_HEAT.getProvider(ExtraGenLang.NAQUADAH_REACTOR, tile)),
        TAB_FUEL((tile, extra) -> ExtraGenContainerTypes.NAQUADAH_REACTOR_FUEL.getProvider(ExtraGenLang.NAQUADAH_REACTOR, tile)),
        TAB_STATS((tile, extra) -> {
//            if (tile instanceof TileEntityTurbineCasing) {
//                return GeneratorsContainerTypes.TURBINE_STATS.getProvider(GeneratorsLang.TURBINE_STATS, tile);
//            } else if (tile instanceof TileEntityFusionReactorController) {
//                return GeneratorsContainerTypes.FUSION_REACTOR_STATS.getProvider(GeneratorsLang.FUSION_REACTOR, tile);
//            } else if (tile instanceof TileEntityFissionReactorCasing) {
//                return GeneratorsContainerTypes.FISSION_REACTOR_STATS.getProvider(GeneratorsLang.FISSION_REACTOR_STATS, tile);
//            }
            if (tile instanceof TileEntityNaquadahReactorController) {
                return ExtraGenContainerTypes.NAQUADAH_REACTOR_STATS.getProvider(ExtraGenLang.NAQUADAH_REACTOR, tile);
            }
            return null;
        });

        private final BiFunction<TileEntityMekanism, Integer, MenuProvider> providerFromTile;

        ExtraClickedGeneratorsTileButton(BiFunction<TileEntityMekanism, Integer, MenuProvider> providerFromTile) {
            this.providerFromTile = providerFromTile;
        }

        public MenuProvider getProvider(TileEntityMekanism tile, int extra) {
            return providerFromTile.apply(tile, extra);
        }
    }
}
