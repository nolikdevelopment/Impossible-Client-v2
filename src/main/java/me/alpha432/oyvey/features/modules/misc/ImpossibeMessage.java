package me.alpha432.oyvey.features.modules.misc;

import me.alpha432.oyvey.features.modules.Module;

public class ImpossibeMessage extends Module {
    private String ChatSuffix = "";

    public ImpossibeMessage() {
        super("ImpossibleMessage", "", Category.MISC, true,false,false);
    }
    @Override public void onEnable() {
        mc.player.networkHandler.sendChatMessage("I just killed Vetmo Team using my client!" + ChatSuffix);
        disable();
    }
}
