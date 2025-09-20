package me.alpha432.oyvey.features.modules.player;

import me.alpha432.oyvey.OyVey;
import me.alpha432.oyvey.event.impl.Render3DEvent;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.settings.Setting;
import me.alpha432.oyvey.util.InteractionUtil;
import me.alpha432.oyvey.util.InventoryUtil;
import me.alpha432.oyvey.util.RenderUtil;
import me.alpha432.oyvey.util.models.Timer;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;

import java.awt.*;

public class Scaffold extends Module {

    BlockPos pos;
    private boolean render;
    public Scaffold() {

        super("Scaffold", "", Category.PLAYER, true, false, false);
    }

    @Override
    public void onTick() {
        if (nullCheck()) return;
        if (mc.player.age % 2 == 0) render = false;
        int oldslot = mc.player.getInventory().selectedSlot;
        int slot = InventoryUtil.findHotbarItem(BlockItem.class);
        pos = mc.player.getBlockPos().offset(Direction.DOWN);
        if (slot != -1 && mc.world.getBlockState(pos).getBlock() == Blocks.AIR) {
            InventoryUtil.switchSlot(slot);
            OyVey.rotationManager.rotateToBlockPos(pos);
            mc.player.swingHand(Hand.MAIN_HAND);
            InteractionUtil.placeblock(pos, false);
            render = true;
            InventoryUtil.switchSlot(oldslot);
        }
    }
    @Override public void onRender3D(Render3DEvent event) {
        if (pos != null && !render) return; {
            RenderUtil.drawBox(event.getMatrix(), new Box(pos), Color.WHITE, 1);
            RenderUtil.drawBoxFilled(event.getMatrix(), new Box(pos), new Color(255, 255, 255, 56));
        }
    }
}
