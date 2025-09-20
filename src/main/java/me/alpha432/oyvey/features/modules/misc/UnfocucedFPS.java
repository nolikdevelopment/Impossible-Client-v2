package me.alpha432.oyvey.features.modules.misc;

import com.google.common.eventbus.Subscribe;
import me.alpha432.oyvey.event.impl.EventLimit;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.settings.Setting;

public class UnfocucedFPS extends Module {
    public final Setting<Integer> limit = num("Limit", 5, 1, 30);
    public UnfocucedFPS() {
        super("UnfocucedFPS", "", Category.MISC, true,false,false);
    }
    @Subscribe public void onEvent(EventLimit event) {
        if (!mc.isWindowFocused()) {
            event.cancel();
            event.setLimited(limit.getValue());
        }

    }
}
