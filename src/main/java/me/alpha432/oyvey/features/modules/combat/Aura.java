package me.alpha432.oyvey.features.modules.combat;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.settings.Setting;
import me.alpha432.oyvey.manager.RotationManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;

public class Aura extends Module {
    public Setting<Boolean> players = bool("Players", true);
    public Setting<Boolean> mobs = bool("Mobs", true);
    private long attackTime = 0;


    public Aura() {
        super("Aura", "", Category.COMBAT, true, false, false);
        attackTime = 0;
    }

    @Override
    public void onTick() {
        if (!fullNullCheck())
        for (Entity entity : mc.world.getEntities()) {
            if (entity == mc.player) {
                continue;
            }
            if (entity instanceof PlayerEntity && players.getValue()) {
                attackEntity(entity);
            }
            if (entity instanceof MobEntity && mobs.getValue()) {
                MobEntity mob = (MobEntity) entity;
                attackEntity(mob);
            }
        }
    }

    private void attackEntity(Entity entity) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - attackTime >= 800) {
            mc.interactionManager.attackEntity(mc.player, entity);
            mc.player.swingHand(Hand.MAIN_HAND);
            attackTime = currentTime;
        }
    }
}

