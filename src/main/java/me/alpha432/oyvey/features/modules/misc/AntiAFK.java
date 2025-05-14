package me.alpha432.oyvey.features.modules.misc;

import me.alpha432.oyvey.OyVey;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.settings.Setting;
import me.alpha432.oyvey.util.Ticker;
import me.alpha432.oyvey.util.models.Timer;

public class AntiAFK extends Module {
   private final Ticker delay = new Ticker();
    public Setting<Boolean> sneak = bool("Sneak", true);
    public Setting<Boolean> jump = bool("Jump", true);
    public Setting<Boolean> rotate = bool("Rotate", true);
    public Setting<Double> delaytick = this.register(new Setting<>("Delay", 5d, 1d, 60d));


    public AntiAFK() {
        super("AntiAFK", "", Category.MISC, true,false,false);
    }
    @Override public void onUpdate() {
        if (fullNullCheck()) return;
        if (sneak.getValue()) {
            if (nullCheck())
                return;
            mc.options.sneakKey.setPressed(true);
            delay.reset();
        }
        if (jump.getValue() && delay.passed(delaytick.getValue())) {
            if (fullNullCheck())
                return;
               mc.options.jumpKey.setPressed(true);
            delay.reset();
        }
        if (rotate.getValue()) {
            if (nullCheck())
                return;
            OyVey.rotationManager.updateRotations();


        }

    }
}
