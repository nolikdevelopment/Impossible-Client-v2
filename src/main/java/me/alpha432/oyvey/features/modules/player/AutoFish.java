package me.alpha432.oyvey.features.modules.player;

import com.google.common.eventbus.Subscribe;
import me.alpha432.oyvey.event.impl.PacketEvent;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.settings.Setting;
import me.alpha432.oyvey.util.models.Timer;
import net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;

public class AutoFish extends Module {
    private boolean castdelay = false;
    private final Setting<Integer> delay = num("Delay", 0, 1, 60);
    private Timer timer = new Timer();

    // TODO пофиксить баг: закинул удочку, поймал предмет, вытащило, закинуло, само по себе вытащило

    public AutoFish() {
        super("AutoFish", "", Category.PLAYER, true, false, false);
    }

    @Subscribe
    public void onPacket(PacketEvent.Receive event) {
        if (event.getPacket() instanceof PlaySoundS2CPacket packet) {
            if (packet.getSound().value() == SoundEvents.ENTITY_FISHING_BOBBER_SPLASH) {
                mc.interactionManager.interactItem(mc.player, Hand.MAIN_HAND);
                castdelay = true;
                timer.reset();
            }
        }
    }

    @Override
    public void onTick () {
        if (castdelay) {
            if (timer.passedS(delay.getValue())) {
                mc.interactionManager.interactItem(mc.player, Hand.MAIN_HAND);
                castdelay = false;
                timer.reset();
            }
        }
    }
}
