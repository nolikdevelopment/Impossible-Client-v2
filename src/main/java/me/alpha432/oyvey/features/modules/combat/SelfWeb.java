package me.alpha432.oyvey.features.modules.combat;

import me.alpha432.oyvey.OyVey;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.util.InteractionUtil;
import me.alpha432.oyvey.util.InventoryUtil;
import net.minecraft.block.CobwebBlock;
import net.minecraft.util.math.BlockPos;

public class SelfWeb extends Module {

    public SelfWeb() {
        super("SelfWeb", "", Category.COMBAT, true, false, false);
    }

    @Override
    public void onTick() {
        if (fullNullCheck()) return;
        int oldslot = mc.player.getInventory().selectedSlot;
        int slot = InventoryUtil.findHotbarItem(CobwebBlock.class);
                BlockPos pos = mc.player.getBlockPos();
                if (slot != -1) {
                    InventoryUtil.switchSlot(slot);
                    InteractionUtil.placeblock(pos, false);
                    disable();
                    InventoryUtil.switchSlot(oldslot);
                }
            }
        }

