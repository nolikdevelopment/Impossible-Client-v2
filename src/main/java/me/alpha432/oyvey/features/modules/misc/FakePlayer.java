package me.alpha432.oyvey.features.modules.misc;

import com.mojang.authlib.GameProfile;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.settings.Setting;
import net.minecraft.client.network.OtherClientPlayerEntity;
import net.minecraft.entity.Entity;

import java.util.UUID;

public class FakePlayer extends Module {
    private Setting<String> name = str("Name", "FakePlayer");
    private OtherClientPlayerEntity player;

    public FakePlayer() {
        super("FakePlayer", "", Category.MISC, true, false, false);
    }

    @Override
    public void onEnable() {
        if (fullNullCheck()) return;
        if (mc.world != null && mc.player != null) {
            player = new OtherClientPlayerEntity(mc.world, new GameProfile(UUID.randomUUID(), name.getValue()));
            player.copyFrom(mc.player);
            mc.world.addEntity(player);
            mc.player.setHealth(20);
        }
    }

    @Override
    public void onDisable() {
        if (mc.world != null && player != null) {
            mc.world.removeEntity(player.getId(), Entity.RemovalReason.KILLED);
            player = null;
        }
    }
}

