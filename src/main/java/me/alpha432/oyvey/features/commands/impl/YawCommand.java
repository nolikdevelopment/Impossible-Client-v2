package me.alpha432.oyvey.features.commands.impl;

import me.alpha432.oyvey.features.commands.Command;

public class YawCommand extends Command {
    public YawCommand() {
        super("yaw", new String[]{"value"});
    }

    @Override
    public void execute(String[] commands) {
        float yaw = Float.parseFloat(commands[0]);
        if (yaw > 180 || yaw < 180) {
            sendMessage("Неверное значение");
            return;
        }
        mc.player.setPitch(yaw);
    }
}
