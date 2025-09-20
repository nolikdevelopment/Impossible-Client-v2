package me.alpha432.oyvey.features.modules.combat;

import me.alpha432.oyvey.OyVey;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.settings.Setting;
import me.alpha432.oyvey.util.InventoryUtil;
import net.minecraft.item.ExperienceBottleItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.PlayerInteractItemC2SPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;

public class AutoExp extends Module {
    private final Setting<Integer> prochnost = num("Prochnosh:", 20, 1, 100);

    public AutoExp() {
        super("AutoExp", "", Category.COMBAT, true, false, false);
    }

    @Override
    public void onTick() {
        if (nullCheck()) return;
        for (ItemStack item : mc.player.getArmorItems()) {
            if (InventoryUtil.armourProchnost(item) < prochnost.getValue()) {
                int oldslot = mc.player.getInventory().selectedSlot;
                int slot = InventoryUtil.findHotbarItem(ExperienceBottleItem.class);
                if (slot != -1) {
                    InventoryUtil.switchSlot(slot);
                    mc.player.swingHand(Hand.MAIN_HAND);
                    mc.player.networkHandler.sendPacket(new PlayerInteractItemC2SPacket(Hand.MAIN_HAND, 0 , mc.player.getYaw(), 90));
                    OyVey.rotationManager.rotate(mc.player.getYaw(), 90);
                    InventoryUtil.switchSlot(oldslot);
                }
            }
        }
    }
}
