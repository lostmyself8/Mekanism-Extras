package com.jerry.mekanism_extras.common.util;

import com.jerry.mekanism_extras.common.content.network.transmitter.ExtraLogisticalTransporter;
import com.jerry.mekanism_extras.common.content.network.transmitter.ExtraLogisticalTransporterBase;
import mekanism.api.text.EnumColor;
import mekanism.common.content.transporter.TransporterManager;
import mekanism.common.content.transporter.TransporterStack;
import mekanism.common.util.TransporterUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ExtraTransporterUtils {
    private ExtraTransporterUtils() {
    }

    public static final List<EnumColor> colors = List.of(EnumColor.DARK_BLUE, EnumColor.DARK_GREEN, EnumColor.DARK_AQUA, EnumColor.DARK_RED, EnumColor.PURPLE,
            EnumColor.INDIGO, EnumColor.BRIGHT_GREEN, EnumColor.AQUA, EnumColor.RED, EnumColor.PINK, EnumColor.YELLOW, EnumColor.BLACK);

    @Nullable
    public static EnumColor readColor(int inputColor) {
        return inputColor == -1 ? null : TransporterUtils.colors.get(inputColor);
    }

    public static int getColorIndex(@Nullable EnumColor color) {
        return color == null ? -1 : TransporterUtils.colors.indexOf(color);
    }

    public static void drop(ExtraLogisticalTransporterBase transporter, TransporterStack stack) {
        BlockPos blockPos = transporter.getTilePos();
        if (stack.hasPath()) {
            float[] pos = ExtraTransporterUtils.getStackPosition(transporter, stack, 0);
            blockPos = blockPos.offset(Mth.floor(pos[0]), Mth.floor(pos[1]), Mth.floor(pos[2]));
        }
        TransporterManager.remove(transporter.getTileWorld(), stack);
        Block.popResource(transporter.getTileWorld(), blockPos, stack.itemStack);
    }

    public static float[] getStackPosition(ExtraLogisticalTransporterBase transporter, TransporterStack stack, float partial) {
        Direction side = stack.getSide(transporter);
        float progress = ((stack.progress + partial) / 100F) - 0.5F;
        return new float[]{0.5F + side.getStepX() * progress, 0.25F + side.getStepY() * progress, 0.5F + side.getStepZ() * progress};
    }

    public static void incrementColor(ExtraLogisticalTransporter tile) {
        EnumColor color = tile.getColor();
        if (color == null) {
            tile.setColor(colors.get(0));
        } else {
            int index = colors.indexOf(color);
            if (index == colors.size() - 1) {
                tile.setColor(null);
            } else {
                tile.setColor(colors.get(index + 1));
            }
        }
    }
}
