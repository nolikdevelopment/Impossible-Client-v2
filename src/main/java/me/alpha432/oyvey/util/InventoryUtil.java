package me.alpha432.oyvey.util;
import me.alpha432.oyvey.util.traits.Util;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.c2s.play.UpdateSelectedSlotC2SPacket;


public class InventoryUtil implements Util {
    public static void switchSlot(int slot) {
        mc.player.getInventory().selectedSlot = slot;
        mc.player.networkHandler.sendPacket(new UpdateSelectedSlotC2SPacket(slot));
    }


    public static int findHotbarItem(Class clazz) {
        for (int i = 0; i < 9; ++i) {
            ItemStack stack = mc.player.getInventory().getStack(i);
            if (stack == ItemStack.EMPTY) continue;
            if (clazz.isInstance(stack.getItem())) {
                return i;
            }
            if (!(stack.getItem() instanceof BlockItem) || !clazz.isInstance(((BlockItem) stack.getItem()).getBlock()))
                continue;
            return i;
        }
        return -1;
    }

    public static int findBlockHotbar(Block block) {
        for (int i = 0; i < 9; i++) {
            if (mc.player.getInventory().getStack(i).getItem() == block.asItem()) {
                return i;
            }
        }
        return -1;
    }
    public static double armourProchnost(ItemStack stack) {
        return ((double) (stack.getMaxDamage() - stack.getDamage()) /  stack.getMaxDamage()) * 100;
    }
}