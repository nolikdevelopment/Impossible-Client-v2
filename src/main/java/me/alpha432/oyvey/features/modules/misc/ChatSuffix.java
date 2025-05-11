package me.alpha432.oyvey.features.modules.misc;

import com.google.common.eventbus.Subscribe;
import me.alpha432.oyvey.event.impl.PacketEvent;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.settings.Setting;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;

import java.util.Objects;

public class ChatSuffix extends Module {
    private final Setting<String> messages = this.register(new Setting<>("Message:", " | Impossible Client 2.0"));
    public ChatSuffix() {

        super("ChatSuffix", "", Category.MISC, true,false,false);
    }
    private String message;
    @Subscribe
    public void onPacket(PacketEvent.Send event) {
        if (fullNullCheck()) return;
        if (event.getPacket() instanceof ChatMessageC2SPacket packet) {
            if (Objects.equals(packet.chatMessage(), message)) {
                return;
            }
            message = packet.chatMessage() + messages.getValue();
            mc.player.networkHandler.sendChatMessage(packet.chatMessage() + messages.getValue());
            event.cancel();

        }

}
}
