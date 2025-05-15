package me.alpha432.oyvey.features.modules.player;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.settings.Setting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;

public class ItemSaver extends Module {
    public Setting<Double> save = this.register(new Setting<>("Save", 5d, 1d, 100d));
    public ItemSaver() {
        super("ItemSaver", "", Category.PLAYER, true,false,false);
    }
    @Override public void onUpdate() {
        ItemStack tool = mc.player.getMainHandStack();
        if (!(tool.getItem() instanceof MiningToolItem)) return;
        float dur = tool.getMaxDamage() - tool.getDamage();
        int prch = (int) ((dur / (float) tool.getMaxDamage() * 100));
        if (prch <= save.getValue()) {
            mc.player.getInventory().selectedSlot = findSlot();
        }
    }
    public static int findSlot() {
        int i = mc.player.getInventory().selectedSlot;
        if (i == 8) return 7;
        if (i == 0) return 1;
        return i - 1;
    }
}
