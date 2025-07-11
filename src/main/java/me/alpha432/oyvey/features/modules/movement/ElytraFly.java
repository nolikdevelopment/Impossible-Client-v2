package me.alpha432.oyvey.features.modules.movement;

import me.alpha432.oyvey.OyVey;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.settings.Setting;
import net.minecraft.block.LadderBlock;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;


public class ElytraFly extends Module {
    public Setting<ElytraFly.Mod> mode = this.register(new Setting<>("Mode", Mod.Control));

    public enum Mod {
        Control, Bounce
    }

    private final Setting<Float> horizontal = num("Vertical", 4f, 1f, 6f);
    private final Setting<Float> vertical = num("Horizontal", 4f, 1f, 6f);

    public ElytraFly() {
        super("ElytraFly", "", Category.MOVEMENT, true, false, false);
    }

    @Override
    public void onTick() {
        if (nullCheck()) return;
        if (mode.getValue() == Mod.Control && mc.player.isGliding()) {
            mc.player.setNoGravity(true); // ???

            Vec3d moveVec = Vec3d.ZERO;
            float y = 0f;

            if (mc.options.forwardKey.isPressed()) {
                moveVec = moveVec.add(Vec3d.fromPolar(0, mc.player.getYaw()).normalize());
            }
            if (mc.options.backKey.isPressed()) {
                moveVec = moveVec.add(Vec3d.fromPolar(0, mc.player.getYaw() + 180).normalize());
            }
            if (mc.options.rightKey.isPressed()) {
                moveVec = moveVec.add(Vec3d.fromPolar(0, mc.player.getYaw() + 90).normalize());
            }
            if (mc.options.leftKey.isPressed()) {
                moveVec = moveVec.add(Vec3d.fromPolar(0, mc.player.getYaw() - 90).normalize());
            }

            if (mc.options.jumpKey.isPressed()) {
                y = vertical.getValue();
            } else if (mc.options.sneakKey.isPressed()) {
                y = -vertical.getValue();
            } else {
                y = 0f;
            }

            moveVec = moveVec.normalize().multiply(horizontal.getValue());
            mc.player.setVelocity(moveVec.x, y, moveVec.z);

        } else {
            mc.player.setNoGravity(false);
        }

        if (mode.getValue() == Mod.Bounce) {
            if (mc.player.isGliding() && mc.player.isOnGround() && mc.player.input.movementForward > 0) {
                mc.player.networkHandler.sendPacket(new ClientCommandC2SPacket(mc.player, ClientCommandC2SPacket.Mode.START_FALL_FLYING));
                mc.player.startGliding();
                mc.player.jump();
            }
        }
    }
    @Override public void onDisable() {
        mc.player.setNoGravity(false);
    }
    @Override public String getDisplayInfo() {
       return mode.getValue().name();
    }
}
