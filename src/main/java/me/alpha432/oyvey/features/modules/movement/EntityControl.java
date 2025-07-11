package me.alpha432.oyvey.features.modules.movement;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.settings.Setting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.entity.vehicle.BoatEntity;

public class EntityControl extends Module {
    public Setting<Float> vertical = num("Vertical", 0.1f, 0.1f, 10.0f);
    public Setting<Float> horizontal = num("Horizontal", 0.1f, 0.1f, 10.0f);

    public EntityControl() {
        super("EntityControl", "", Category.MOVEMENT, true, false, false);
    }

    @Override
    public void onUpdate() {
        if (fullNullCheck()) return;
        Entity vehicle = mc.player.getVehicle();
        if (vehicle instanceof BoatEntity boat) {
            boat.setNoGravity(true);
            controlEntity(boat);
            mc.player.setPos(boat.getX(), boat.getY(), boat.getZ());
        } if (vehicle instanceof HorseEntity horse) {
            horse.setNoGravity(true);
            controlEntity(horse);
            mc.player.setPos(horse.getX(), horse.getY(), horse.getZ());
        }
    }

    private void controlEntity(Entity entity) {
        double yaw = Math.toRadians(mc.player.getYaw());
        double xSpeed = 0;
        double zSpeed = 0;
        float verticalSpeed = vertical.getValue();
        float horizontalSpeed = horizontal.getValue();

        if (mc.options.forwardKey.isPressed()) {
            xSpeed += -Math.sin(yaw) * horizontalSpeed;
            zSpeed += Math.cos(yaw) * horizontalSpeed;
        }
        if (mc.options.backKey.isPressed()) {
            xSpeed += Math.sin(yaw) * horizontalSpeed;
            zSpeed += -Math.cos(yaw) * horizontalSpeed;
        }
        if (mc.options.leftKey.isPressed()) {
            xSpeed += -Math.cos(yaw) * horizontalSpeed;
            zSpeed += -Math.sin(yaw) * horizontalSpeed;
        }
        if (mc.options.rightKey.isPressed()) {
            xSpeed += Math.cos(yaw) * horizontalSpeed;
            zSpeed += Math.sin(yaw) * horizontalSpeed;
        }

        float ySpeed = 0f;
        if (mc.options.jumpKey.isPressed()) {
            ySpeed = verticalSpeed;
        }
        if (mc.options.sneakKey.isPressed()) {
            ySpeed = -verticalSpeed;
        }

        entity.setVelocity(xSpeed, ySpeed, zSpeed);
    }
}