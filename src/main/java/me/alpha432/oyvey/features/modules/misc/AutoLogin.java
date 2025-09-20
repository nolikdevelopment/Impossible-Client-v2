package me.alpha432.oyvey.features.modules.misc;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.settings.Setting;


public class AutoLogin extends Module {
    public Setting<String> password = str("Password", "testclientlol");
    public AutoLogin() {
        super("AutoLogin", "", Category.MISC, true, false, false);
    }
    @Override public void onEnable() {
        if (fullNullCheck()) return;
        mc.player.networkHandler.sendChatCommand("login " + password.getValue() + " " + password.getValue());
        disable();
    }
}