package me.alpha432.oyvey.features.modules.player;

import me.alpha432.oyvey.features.modules.Module;
import net.minecraft.client.gui.screen.DeathScreen;

public class AutoRespawn extends Module {
    public AutoRespawn() {
        super("AutoRespawn", "", Category.PLAYER, true, false, false);
    }
    @Override public void onUpdate() {
        if (fullNullCheck()) return;
       if (mc.currentScreen instanceof DeathScreen) {
           mc.player.requestRespawn();
       }
    }
}