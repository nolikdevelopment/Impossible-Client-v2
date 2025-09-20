package me.alpha432.oyvey.features.modules.movement;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.settings.Setting;



public class Flight extends Module {
    public Setting<Flight.Mod> mode = this.register(new Setting<>("Mode", Mod.Vanilla));
    public Setting<Float> horizontal = num("Horizontal", 1.0f, 0.01f, 5.0f);
    public Setting<Float> vertical = num("Vertical", 1.0f, 0.01f, 5.0f);
    public Setting<Boolean> antikick = bool("AntiKick", false);
    public enum Mod {
        Vanilla
    }

    public Flight() {
        super("Flight", "", Category.MOVEMENT, true, false, false);

    }

    @Override
    public void onUpdate() {
        switch (mode.getValue()) {
            case Vanilla -> {
                if (mc.player.input.playerInput.forward()) {
                    mc.player.getAbilities().setFlySpeed(horizontal.getValue() / 11);
                    mc.player.getAbilities().flying = true;
                }
                if (mc.player.input.playerInput.backward()) {
                    mc.player.getAbilities().setFlySpeed(horizontal.getValue() / 11);
                    mc.player.getAbilities().flying = true;
                }
                if (mc.player.input.playerInput.left()) {
                    mc.player.getAbilities().setFlySpeed(vertical.getValue() / 11);
                    mc.player.getAbilities().flying = true;
                }
                if (mc.player.input.playerInput.right()) {
                    mc.player.getAbilities().setFlySpeed(vertical.getValue() / 11);
                    mc.player.getAbilities().flying = true;
                }
                if (antikick.getValue()) {
                    mc.player.setVelocity(mc.player.getVelocity().add(0, -0.05, 0));
                }
            }
        }
    }
    @Override public void onDisable() {
        mc.player.getAbilities().setFlySpeed(0.02F);
        mc.player.getAbilities().flying = false;
    }
}

