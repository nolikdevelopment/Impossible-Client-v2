package me.alpha432.oyvey.features.modules.misc;

import com.google.common.eventbus.Subscribe;
import me.alpha432.oyvey.event.impl.PacketEvent;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.settings.Setting;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;

public class AntiSpam extends Module {
    public Setting<Boolean> links = bool("Links", true);
    private String[] chat = new String[]{
            "hhtps:",
            ".ru",
            ".com",
            ".net",
            ".me",
            ".org",
            ".xyz"
    };


    public AntiSpam() {
        super("AntiSpam", "", Category.MISC, true, false, false);
    }

    @Subscribe
    public void onPacket(PacketEvent.Receive event) {
        if (event.getPacket() instanceof GameMessageS2CPacket packet) {
            if (links.getValue()) {
                for (String text : chat) {
                    if (packet.content().getString().contains(text)) {
                        event.cancel();
                    }
                }
            }
        }
    }
}

