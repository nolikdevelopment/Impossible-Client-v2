package me.alpha432.oyvey.features.modules.combat;

import me.alpha432.oyvey.OyVey;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.settings.Setting;

import me.alpha432.oyvey.util.models.Timer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.RaycastContext;

public class Aura extends Module {
    private Timer timer = new Timer();
    public Setting<Boolean> targets = bool("Targets", true).setParent().setToggle();
    public Setting<Boolean> players = bool("Players", true);
    public Setting<Boolean> mobs = bool("Mobs", true);
    public Setting<Boolean> neutrals = bool("Neutrals", true);
    public Setting<Boolean> animals = bool("Animals", true);
    private final Setting<Float> range = num("Range", 4f, 1f, 6f);
    private PlayerEntity target;

    public Aura() {
        super("Aura", "", Category.COMBAT, true, false, false);
        players.setVisibility(v -> targets.isOpen());
        mobs.setVisibility(v -> targets.isOpen());
        animals.setVisibility(v -> targets.isOpen());
    }


    @Override
    public void onTick() {
        if (fullNullCheck()) return;
        if (!(mc.player.getMainHandStack().getItem() instanceof SwordItem)) return;
        for (Entity entity : mc.world.getEntities()) {
            if (entity == mc.player) continue;
            if (entity.getPos().distanceTo(mc.player.getPos()) > range.getValue()) continue;
            if (entity instanceof PlayerEntity player && players.getValue()) {
                target = player;
                attackEntity(entity);
            } if (entity instanceof Monster && mobs.getValue()) {
                attackEntity(entity);
            } if (entity instanceof AnimalEntity && animals.getValue()) {
                attackEntity(entity);
            }
            if (entity instanceof PassiveEntity && neutrals.getValue()) {
                attackEntity(entity);
            }
        }
    }xxxxxxxxxxxxx


    private void attackEntity(Entity entity) {
        if (timer.passedS(0.8)) {
            if (OyVey.friendManager.isFriend(entity.getName().getString())) return;
            OyVey.rotationManager.rotateToEntity(entity);
            mc.interactionManager.attackEntity(mc.player, entity);
            mc.player.swingHand(Hand.MAIN_HAND);
            timer.reset();
        }
    }
    @Override public String getDisplayInfo() {
        return target != null ? target.getName().getString(): null;
        }
    }
