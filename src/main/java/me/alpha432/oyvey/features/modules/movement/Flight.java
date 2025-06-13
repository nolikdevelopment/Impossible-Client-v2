package me.alpha432.oyvey.features.modules.movement;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.settings.Setting;
import net.minecraft.util.math.Vec3d;


public class Flight extends Module {
    private final Setting<Float> horizontal = num("Vertical", 4f, 1f, 6f);
    private final Setting<Float> vertical = num("Horizontal", 4f, 1f, 6f);
    public Setting<Flight.Mod> mode = this.register(new Setting<>("Mode", Mod.Vanilla));

    public enum Mod {
        Vanilla,
    }

    public Flight() {
        super("Flight", "", Category.MOVEMENT, true, false, false);

    }

    @Override
    public void onTick() {
        switch (mode.getValue()) {
                case Vanilla -> {
                    if (mc.options.forwardKey.isPressed()) {
                       mc.player.getAbilities().setFlySpeed((float) horizontal.getValue() / 14);
                       mc.player.getAbilities().flying = true;
                    }
                    if (mc.options.backKey.isPressed()) {
                        mc.player.getAbilities().setFlySpeed((float) horizontal.getValue() / 14);
                        mc.player.getAbilities().flying = true;
                    }
                    if (mc.options.rightKey.isPressed()) {
                        mc.player.getAbilities().setFlySpeed((float) horizontal.getValue() / 14);
                        mc.player.getAbilities().flying = true;

                    }
                    if (mc.options.leftKey.isPressed()) {
                        mc.player.getAbilities().setFlySpeed((float) horizontal.getValue() / 14);
                        mc.player.getAbilities().flying = true;
                    }
                    if (mc.options.jumpKey.isPressed()) {
                        mc.player.getAbilities().setFlySpeed((float) vertical.getValue() / 14);
                        mc.player.getAbilities().flying = true;

                    }
                    if (mc.options.sneakKey.isPressed()) {
                        mc.player.getAbilities().setFlySpeed((float) vertical.getValue() / 1);
                        mc.player.getAbilities().flying = true;
                    }
                }
            }
        }
    }