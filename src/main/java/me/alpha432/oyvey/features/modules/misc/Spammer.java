package me.alpha432.oyvey.features.modules.misc;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.settings.Setting;
import me.alpha432.oyvey.util.models.Timer;

import java.io.File;
import java.util.Random;

public class Spammer extends Module {
    private Setting<Integer> delay = num("Delay:", 5, 1, 30);
    private Timer timer = new Timer();


    public Spammer() {
        super("Spammer", "", Category.MISC, true, false, false);
    }

    @Override
    public void onTick() {
        if (fullNullCheck()) return;
        if (timer.passedS(delay.getValue())) {
            mc.player.networkHandler.sendChatMessage(walkingmessage());
            timer.reset();
        }
    }

    private String walkingmessage() {
        String[] walking = {
                "Привет, хочешь в клан? Именно сейчас проходит набор в CMK! За информацией пишите в дс: nolikcpvp",
        };
        return walking[new Random().nextInt(walking.length)];
    }
}
