package me.alpha432.oyvey.features.modules.misc;

import com.google.common.eventbus.Subscribe;
import me.alpha432.oyvey.event.impl.PacketEvent;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.settings.Setting;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;

public class AntiSpam extends Module {
    public Setting<Boolean> links = bool("Links", true);
    public Setting<Boolean> anc = bool("Annoucers", true);
    public Setting<Boolean> clan = bool("CMKClan", true);
    private String[] chat = new String[]{
            "hhtps:",
            ".ru",
            ".com",
            ".net",
            ".me",
            ".org",
            ".xyz"
    };
    private String[] annoucers = new String[] {
            "I just",
            "I moved",
            "I placed"
    };
    private String[] clanspam = new String[] {
            "Привет, хочешь в клан? Именно сейчас проходит набор в CMK! За информацией пишите в дс: nolikcpvp"
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
                    if (anc.getValue()) {
                        for (String message : annoucers) {
                            if (packet.content().getString().contains(message)) {
                                event.cancel();
                            }
                        }
                        if (clan.getValue()) {
                            for (String messages : clanspam) {
                                if (packet.content().getString().contains(messages)) {
                                    event.cancel();
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

