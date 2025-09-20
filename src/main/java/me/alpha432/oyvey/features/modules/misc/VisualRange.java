package me.alpha432.oyvey.features.modules.misc;

import me.alpha432.oyvey.features.commands.Command;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.settings.Setting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.item.EnderPearlItem;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Formatting;

import java.util.ArrayList;

public class VisualRange extends Module {
    public Setting<Boolean> join = bool("Join", true);
    public Setting<Boolean> left = bool("Left", true);
    public Setting<Boolean> sound = bool("Sound", true);
    private final static ArrayList<PlayerEntity> inRangePlayers = new ArrayList<>();


    public VisualRange() {
        super("VisualRange", "", Category.MISC, true, false, false);
    }

    @Override
    public void onTick() {
        if (fullNullCheck()) return;
        if (join.getValue()) {
            for (Entity entity : mc.world.getPlayers()) {
                if (!(entity instanceof PlayerEntity player) || entity == mc.player) continue;
                if (!inRangePlayers.contains(player)) {
                    inRangePlayers.add(player);
                    if (sound.getValue()) {
                        mc.player.getWorld().playSound(mc.player.getX(), mc.player.getY(), mc.player.getZ(), SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER, mc.player.getSoundCategory(), 50.0F, 0, false);
                    }
                    String message = "[!] " + Formatting.WHITE + player.getName().getString() + Formatting.WHITE + " has enterned your visual range!";
                    Command.sendMessage(message);
                }

            }
            if (left.getValue()) {
                for (PlayerEntity player : new ArrayList<>(inRangePlayers)) {
                    if (!mc.world.getPlayers().contains(player)) {
                        inRangePlayers.remove(player);
                        if (sound.getValue()) {
                            mc.world.playSound(mc.player.getX(), mc.player.getY(), mc.player.getZ(), SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER, mc.player.getSoundCategory(), 1, 0, false);
                        }
                        String message = "[!] " + Formatting.WHITE + player.getName().getString() + Formatting.WHITE + " has left your visual range!";
                        Command.sendMessage(message);
                    }
                }
            }
        }
    }
}



