package me.alpha432.oyvey.features.modules.movement;

import com.google.common.eventbus.Subscribe;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.settings.Setting;


public class Flight extends Module {
    private final Setting<Float> speed = num("Speed", 1f, 0.5f, 3f);
    public Flight() {
        super("Flight", "", Category.MOVEMENT, true,false,false);

    }
    @Override
    public void onUpdate() {
        mc.player.getAbilities().setFlySpeed((float) speed.getValue()/ 11);
        mc.player.getAbilities().flying = true;
    }
    @Override
    public void onDisable() {
        mc.player.getAbilities().flying = false;
    }
}
