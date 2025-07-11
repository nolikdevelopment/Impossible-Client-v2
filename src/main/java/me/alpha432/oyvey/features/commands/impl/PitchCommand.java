package me.alpha432.oyvey.features.commands.impl;

import me.alpha432.oyvey.features.commands.Command;

public class PitchCommand extends Command {
    public PitchCommand() {
        super("pitch", new String[]{"<value>"});
    }
    @Override
    public void execute(String[] commands) {
        float pitch = Float.parseFloat(commands[0]);
       if (pitch > 90 || pitch < -90) {
           sendMessage("Неверное значение");
           return;
       }
       mc.player.setPitch(pitch);
    }
}
