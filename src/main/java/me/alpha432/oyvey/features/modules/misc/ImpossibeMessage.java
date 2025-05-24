package me.alpha432.oyvey.features.modules.misc;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.settings.Setting;
import me.alpha432.oyvey.util.models.Timer;

public class ImpossibeMessage extends Module {
    private Setting<Integer> delay = num("Delay:", 5, 1, 30);
    private Timer timer = new Timer();
    private String ChatSuffix = "";

    public ImpossibeMessage() {
        super("ImpossibleMessage", "", Category.MISC, true, false, false);
    }

    @Override
    public void onTick() {
        if (fullNullCheck()) return;
        if (timer.passedS(delay.getValue())) {
            mc.player.networkHandler.sendChatMessage("I just killed Vetmo Team using my client!" + ChatSuffix);
            timer.reset();
        }
    }
}
