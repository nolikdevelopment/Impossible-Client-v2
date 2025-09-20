package me.alpha432.oyvey.features.modules.misc;

import me.alpha432.oyvey.features.modules.Module;

public class DeathCoordinates extends Module {
    public DeathCoordinates() {
        super("DeathCoordinates", "", Category.MISC, true, false, false);
    }

    @Override
    public void onUpdate() {
        if (fullNullCheck()) return;
        if (mc.player.isDead()) {
            double x = mc.player.getX();
            double y = mc.player.getY();
            double z = mc.player.getZ();
            mc.player.networkHandler.sendChatMessage("Я погиб! Мои координаты: " + (int) x + " " + (int) y + " " + (int) z);
            disable();
        }
    }
}
