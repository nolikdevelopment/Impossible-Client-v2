package me.alpha432.oyvey.features.modules.movement;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.settings.Setting;


public class Flight extends Module {
    public Setting<Flight.Mod> mode = this.register(new Setting<>("Mode", Mod.Vanilla));
    private final Setting<Integer> speed = num("Speed:", 4, 1, 6);
    public enum Mod {
        Vanilla, Static
    }
    public Flight() {
        super("Flight", "", Category.MOVEMENT, true, false, false);

    }
    @Override public void onTick() {
        switch (mode.getValue()) {
            case Vanilla -> {
                mc.player.getAbilities().flying = true;
                mc.player.getAbilities().setFlySpeed((float) (speed.getValue()) / 11);
            }
            case Static -> {
                mc.player.getAbilities().setFlySpeed((float) (speed.getValue()));
                mc.player.getAbilities().flying = true;
                if (mc.options.jumpKey.isPressed()) mc.player.getAbilities().setFlySpeed((float) (speed.getValue() / 4));
                if (mc.options.sneakKey.isPressed()) mc.player.getAbilities().setFlySpeed((float) (speed.getValue() / 4));
                mc.player.setVelocity(0, 0, 0);
            }
        }

    }
}