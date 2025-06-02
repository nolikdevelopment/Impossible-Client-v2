package me.alpha432.oyvey.features.modules.player;

import com.google.common.eventbus.Subscribe;
import me.alpha432.oyvey.event.impl.PacketEvent;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.settings.Setting;
import me.alpha432.oyvey.mixin.EntityVelocityUpdateS2CPacketAccessor;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.ExplosionS2CPacket;

public class Velocity extends Module {
    private final Setting<Integer> vert = num("Vertical", 100, 0, 100);
    private final Setting<Integer> hort = num("Horizontal", 100, 0, 100);
    public Setting<Boolean> blockPush = bool("BlockPush", true);
    public Setting<Boolean> entityPush = bool("EntityPush", true);
    public Setting<Boolean> explosion = bool("Explosion", true);

    public Velocity() {
        super("Velocity", "Removes velocity from explosions and entities", Category.PLAYER, true, false, false);
    }
    @Subscribe public void onPacket(PacketEvent.Receive event) {
        if (event.getPacket() instanceof EntityVelocityUpdateS2CPacket packet && packet.getEntityId() == mc.player.getId()) {
         double x = (packet.getVelocityX() / 8000d - mc.player.getVelocity().x * hort.getValue());
         double y = (packet.getVelocityY() / 8000d - mc.player.getVelocity().y * vert.getValue());
         double z = (packet.getVelocityZ() / 8000d - mc.player.getVelocity().z * hort.getValue());
            ((EntityVelocityUpdateS2CPacketAccessor) packet).setX((int) (x * 8000 + mc.player.getVelocity().x * 8000));
            ((EntityVelocityUpdateS2CPacketAccessor) packet).setY((int) (y * 8000 + mc.player.getVelocity().y * 8000));
            ((EntityVelocityUpdateS2CPacketAccessor) packet).setZ((int) (z * 8000 + mc.player.getVelocity().z * 8000));

        }
    }
}


