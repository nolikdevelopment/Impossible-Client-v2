package me.alpha432.oyvey.features.modules.player;


import me.alpha432.oyvey.features.modules.Module;
import net.minecraft.item.Items;


public class AutoDrop extends Module {

    public AutoDrop() {
        super("AutoDrop", "", Category.PLAYER, true, false, false);
    }
    public void onUpdate() {
        if (fullNullCheck()) return;
        if (mc.player.getInventory().getMainHandStack().getItem().equals(Items.END_CRYSTAL)) {
            mc.player.dropSelectedItem(true);
        }
    }
}




