package me.alpha432.oyvey.features.modules.movement;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.settings.Setting;

public class FastFall extends Module {
    private final Setting<Float> height = num("Height:", 3f, 1f, 10f);
    public FastFall() {
        super("FastFall", "step but reversed..", Category.MOVEMENT, true, false, false);
    }

    @Override public void onUpdate() {
        if (nullCheck()) return;
        if (mc.player.isInLava() || mc.player.isTouchingWater() || !mc.player.isOnGround() || mc.player.isInLava() || mc.player.isGliding() || mc.player.getAbilities().flying) return;
        mc.player.addVelocity(0, -height.getValue(), 0);
    }
}
