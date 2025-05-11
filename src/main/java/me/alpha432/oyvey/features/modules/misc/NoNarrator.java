package me.alpha432.oyvey.features.modules.misc;

import me.alpha432.oyvey.features.modules.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.NarratorMode;

public class NoNarrator extends Module {
    private final MinecraftClient mc = MinecraftClient.getInstance();

    public NoNarrator() {
        super("NoNarrator", "Disables the Minecraft narrator.", Category.MISC, true, false, false);
    }

    @Override
    public void onUpdate() {
        if (mc.world == null || mc.player == null) return;

        if (mc.getNarratorManager().isActive()) {
            mc.options.getNarrator().setValue(NarratorMode.OFF);
        }
    }
}
