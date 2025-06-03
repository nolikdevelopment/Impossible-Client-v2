package me.alpha432.oyvey.features.modules.combat;

import me.alpha432.oyvey.OyVey;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.settings.Setting;
import me.alpha432.oyvey.util.MathUtil;
import me.alpha432.oyvey.util.models.Timer;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;

public class Aura extends Module {
    private Timer timer = new Timer();
    public Setting<Boolean> players = bool("Players", true);
    public Setting<Boolean> mobs = bool("Mobs", true);
    private final Setting<Float> range = num("Range", 4f, 1f, 6f);



    public Aura() {
        super("Aura", "", Category.COMBAT, true, false, false);
    }

    @Override
    public void onTick() {
        if (!fullNullCheck())
            for (Entity entity : mc.world.getEntities()) {
                if (entity == mc.player) {
                    continue;
                }
                if (entity.getPos().distanceTo(mc.player.getPos()) > range.getValue()) {
                    continue;
                }
                if (!(mc.player.getMainHandStack().getItem() instanceof SwordItem)) return;
                {
                }
                if (entity instanceof PlayerEntity && players.getValue()) {
                    attackEntity(entity);
                }
                if (entity instanceof MobEntity && mobs.getValue()) {
                    MobEntity mob = (MobEntity) entity;
                    attackEntity(mob);
                }
                if (entity instanceof WitherEntity && mobs.getValue()) {
                    MobEntity mob = (MobEntity) entity;
                    attackEntity(mob);
                }
            }
    }

    private void attackEntity(Entity entity) {
        if (timer.passedS(0.8)) {
            if (OyVey.friendManager.isFriend(entity.getName().getString())) return;
            mc.interactionManager.attackEntity(mc.player, entity);
            mc.player.swingHand(Hand.MAIN_HAND);
            timer.reset();
        }
    }
}