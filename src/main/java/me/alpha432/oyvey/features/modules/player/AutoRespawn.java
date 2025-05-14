package me.alpha432.oyvey.features.modules.player;

import me.alpha432.oyvey.OyVey;
import me.alpha432.oyvey.features.commands.Command;
import me.alpha432.oyvey.features.modules.Module;
import net.minecraft.client.gui.screen.DeathScreen;
import net.minecraft.util.Formatting;

public class AutoRespawn extends Module {
    public AutoRespawn() {
        super("AutoRespawn", "", Category.PLAYER, true,false,false);
    }
    @Override
    public void onTick() {
        if (nullCheck())
            return;
        if (mc.currentScreen instanceof DeathScreen)
        {
            mc.player.requestRespawn();

        }
    }
}