package me.alpha432.oyvey.features.modules.misc;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.settings.Setting;
import me.alpha432.oyvey.util.models.Timer;

public class AntiAFK extends Module {
    public Setting<Integer> delay = this.register(new Setting<>("Delay", 30, 1, 60));
    public Setting<Boolean> jump = bool("Jump", true);
    private Timer timer = new Timer();

    public AntiAFK() {
        super("AntiAFK", "", Category.MISC, true, false, false);
    }

    @Override
    public void onTick() {
        if (fullNullCheck()) return;
        if (timer.passedS(delay.getValue())) {
            if (jump.getValue()) {
                mc.player.jump();


            }

            }
            timer.reset();

        }

    }


