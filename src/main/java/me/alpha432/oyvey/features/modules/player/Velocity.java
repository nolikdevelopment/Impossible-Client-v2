package me.alpha432.oyvey.features.modules.player;

import com.google.common.eventbus.Subscribe;
import me.alpha432.oyvey.event.impl.PacketEvent;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.settings.Setting;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.ExplosionS2CPacket;

public class Velocity extends Module {
    public Setting<Boolean> blockPush = bool("BlockPush", true);
    public Setting<Boolean> entityPush = bool("EntityPush", true);
    public Setting<Boolean> explosion = bool("Explosion", true);

    public Velocity() {
        super("Velocity", "Removes velocity from explosions and entities", Category.PLAYER, true, false, false);
    }

    @Subscribe private void onPacketReceive(PacketEvent.Receive event) {
        if (event.getPacket() instanceof EntityVelocityUpdateS2CPacket || event.getPacket() instanceof ExplosionS2CPacket) event.cancel();
    }
}
