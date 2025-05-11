package me.alpha432.oyvey.features.modules.player;

import me.alpha432.oyvey.event.impl.Render3DEvent;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.util.InteractionUtil;
import me.alpha432.oyvey.util.InventoryUtil;
import me.alpha432.oyvey.util.RenderUtil;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;

import java.awt.*;

public class Scaffold extends Module {
    BlockPos pos;
    boolean zd;
    public Scaffold() {
        super("Scaffold", "", Category.PLAYER, true,false,false);
    }
    @Override
    public void onTick() {
        if (nullCheck()) return;
        if (mc.player.age % 2 == 0) zd = false;
        int oldslot = mc.player.getInventory().selectedSlot;
        int slot = InventoryUtil.findHotbarItem(BlockItem.class);
        pos = mc.player.getBlockPos().down();
        if (slot != -1) {
            InventoryUtil.switchSlot(slot);

            InteractionUtil.place(pos, true, Hand.MAIN_HAND);
            zd = true;
            InventoryUtil.switchSlot(oldslot);
        }
    }
    @Override
    public void onRender3D(Render3DEvent event) {
        if (pos == null || !zd) return;
        BlockPos pos = mc.player.getBlockPos().down();
        RenderUtil.drawBox(event.getMatrix(), pos, Color.white, 1);




    }
}
