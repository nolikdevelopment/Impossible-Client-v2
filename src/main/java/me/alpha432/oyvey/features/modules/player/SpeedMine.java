package me.alpha432.oyvey.features.modules.player;



import me.alpha432.oyvey.event.impl.Render3DEvent;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.util.InteractionUtil;

import me.alpha432.oyvey.util.RenderUtil;
import net.minecraft.block.Blocks;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

import java.awt.*;


public class SpeedMine extends Module {
    private BlockPos pos;
    public SpeedMine() {
        super("SpeedMine", "", Category.PLAYER, true,false,false);
    }
    @Override public void onUpdate() {
        if (fullNullCheck()) return;
        pos = ((BlockHitResult) mc.crosshairTarget).getBlockPos();
        if (mc.options.attackKey.isPressed()) {
            InteractionUtil.breakBlock(pos);
        }
    }
    public void onRender3D(Render3DEvent event) {
        if (mc.options.attackKey.isPressed()&& mc.world.getBlockState(pos).getBlock() != Blocks.AIR) {
           RenderUtil.drawBox(event.getMatrix(), pos, Color.GRAY, 1);
           RenderUtil.drawBoxFilled(event.getMatrix(), pos, new Color(255, 255, 255, 53));
        }
    }
}