package me.alpha432.oyvey.features.modules.movement;

import me.alpha432.oyvey.OyVey;
import me.alpha432.oyvey.features.commands.Command;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.settings.Setting;
import me.alpha432.oyvey.util.models.Timer;
import net.minecraft.util.Formatting;

public class AutoWalk extends Module {

    private final Setting<Boolean> autoJump = this.register(new Setting<>("AutoJump", false));

    public AutoWalk() {
        super("AutoWalk", "", Category.MOVEMENT, true, false, false);
    }

    @Override
    public void onTick() {
            mc.options.forwardKey.setPressed(true);
            if (autoJump.getValue()) {
                mc.options.jumpKey.setPressed(true);
            }
            if (mc.player.horizontalCollision) {
                String message = Formatting.DARK_RED + "[!] "  + Formatting.WHITE + "Вы врезались в блок!";
                Command.sendMessage(message);
            }
    }

    @Override
    public void onDisable() {
            mc.options.forwardKey.setPressed(false);
            mc.options.jumpKey.setPressed(false);
    }
}


