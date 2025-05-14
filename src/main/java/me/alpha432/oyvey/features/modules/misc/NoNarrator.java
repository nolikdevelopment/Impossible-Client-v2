package me.alpha432.oyvey.features.modules.misc;

import me.alpha432.oyvey.features.modules.Module;
import net.minecraft.client.option.NarratorMode;

public class NoNarrator extends Module {


    public NoNarrator() {
        super("NoNarrator", "Disables the Minecraft narrator.", Category.MISC, true, false, false);
    }

    @Override
    public void onUpdate() {
        if (fullNullCheck()) return;

        if (mc.getNarratorManager().isActive()) {
            mc.options.getNarrator().setValue(NarratorMode.OFF);
        }
    }
}
