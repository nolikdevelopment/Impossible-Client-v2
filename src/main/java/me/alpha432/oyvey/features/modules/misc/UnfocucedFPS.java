package me.alpha432.oyvey.features.modules.misc;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.settings.Setting;

public class UnfocucedFPS extends Module {
    public final Setting<Float> limit = num("Limit", 5f, 1f, 30f);
    public UnfocucedFPS() {
        super("UnfocucedFPS", "", Category.MISC, true,false,false);
    }
}
