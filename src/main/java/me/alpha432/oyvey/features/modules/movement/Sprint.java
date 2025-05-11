package me.alpha432.oyvey.features.modules.movement;


import me.alpha432.oyvey.features.modules.Module;


public class Sprint extends Module {

    public Sprint() {
        super("Sprint", "", Category.MOVEMENT, true, false, false);
    }

    @Override
    public void onTick() {
        if (nullCheck()) return;
        if (!mc.player.horizontalCollision && mc.player.forwardSpeed > 0 && !mc.player.isSneaking() && !mc.player.isUsingItem()) {
            mc.player.setSprinting(true);
        }
    }
}

