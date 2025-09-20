package me.alpha432.oyvey.features.modules.movement;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.settings.Setting;
import net.minecraft.entity.player.PlayerEntity;

public class HighJump extends Module {
    private final Setting<Float> height = num("Height:", 1f, 1f, 3f);

    public HighJump() {
        super("HightJump", "", Category.MOVEMENT, true, false, false);
    }

    @Override
    public void onTick() {
        if(fullNullCheck()) return;
        if (mc.player.input.movementForward > 0 && mc.player.input.movementSideways > 0 && mc.options.jumpKey.isPressed() && mc.player.isOnGround()) {
            mc.player.setVelocity(0f, height.getValue(), 0f);
        }
    }
}
