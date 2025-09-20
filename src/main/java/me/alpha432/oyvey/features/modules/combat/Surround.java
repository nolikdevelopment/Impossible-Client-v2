package me.alpha432.oyvey.features.modules.combat;

import me.alpha432.oyvey.OyVey;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.settings.Setting;
import me.alpha432.oyvey.util.InteractionUtil;
import me.alpha432.oyvey.util.InventoryUtil;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;


import java.util.ArrayList;
import java.util.List;

public class Surround extends Module {
    public Setting<Boolean> jump = bool("DisableJump", true);
    private List<BlockPos> position = new ArrayList<>();

    public Surround() {
        super("Surround", "", Category.COMBAT, true, false, false);
    }

    @Override
    public void onEnable() {
        setPosition();
    }

    @Override
    public void onDisable() {
        position.clear();
    }

    @Override
    public void onTick() {
        if (nullCheck()) return;
        int oldslot = mc.player.getInventory().selectedSlot;
        int slot = InventoryUtil.findBlockHotbar(Blocks.OBSIDIAN);
        if (slot != -1) {
            if (jump.getValue() && mc.options.jumpKey.isPressed()) {
                disable();
            }
            for (BlockPos pos : position) {
                InventoryUtil.switchSlot(slot);
                OyVey.rotationManager.rotateToBlockPos(pos);
                InteractionUtil.placeblock(pos, false);
            }
            InventoryUtil.switchSlot(oldslot);
        }
    }

    private void setPosition() {
        BlockPos pos = mc.player.getBlockPos();
        position.add(pos.east(1));
        position.add(pos.north(1));
        position.add(pos.west(1));
        position.add(pos.south(1));

    }
}
