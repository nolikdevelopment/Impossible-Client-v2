package me.alpha432.oyvey.features.modules.client;

import me.alpha432.oyvey.OyVey;
import me.alpha432.oyvey.event.impl.Render2DEvent;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.settings.Setting;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class HudModule extends Module {
    public Setting<Boolean> waterkmark = bool("Watermark", true);
    public Setting<Boolean> serverBrand = bool("ServerBrand", true);
    public Setting<Boolean> speed = bool("Speed", true);
    public Setting<Boolean> ping = bool("Ping", true);
    public Setting<Boolean> tps = bool("TPS", true);
    public Setting<Boolean> fps = bool("FPS", true);
    public Setting<Boolean> xyz = bool("XYZ", true);
    public Setting<Boolean> time = bool("Time", true);
    public Setting<Boolean> direction = bool("Direction", true);
    public Setting<Boolean> welcomer = bool("Welcomer", true);
    public Setting<Boolean> arrayList = bool("ArrayList", true);
    public HudModule() {
        super("Hud", "hud", Category.CLIENT, true, false, false);
    }

    @Override
    public void onRender2D(Render2DEvent event) {
        int width = mc.getWindow().getScaledWidth(); // ширина
        int height = mc.getWindow().getScaledHeight(); // высота
        int yawPitch = (int) MathHelper.wrapDegrees(mc.player.getYaw());
        boolean nether = mc.world.getRegistryKey() == World.NETHER;
        int y = 0;
        int j = (mc.currentScreen instanceof ChatScreen) ? 13 : 0;
        if (waterkmark.getValue()) {
            event.getContext().drawTextWithShadow(mc.textRenderer, OyVey.NAME + Formatting.WHITE + OyVey.VERSION, 2, 2, OyVey.colorManager.getColorWithAlpha(255));
        }
        if (serverBrand.getValue()) {
            event.getContext().drawTextWithShadow(mc.textRenderer, "ServerBrand: " + Formatting.WHITE + OyVey.serverManager.getServerBrand(), 2, 12, OyVey.colorManager.getColorWithAlpha(255));
        }
        if (speed.getValue()) {
            int speedValue = (int) OyVey.speedManager.getSpeedKpH();
            event.getContext().drawTextWithShadow(mc.textRenderer, "Speed: " + Formatting.WHITE + speedValue + " km/h", 2, 22, OyVey.colorManager.getColorWithAlpha(255));
        }
        if (ping.getValue()) {
            event.getContext().drawTextWithShadow(mc.textRenderer, "Ping: " + Formatting.WHITE + OyVey.serverManager.getPing() + "ms", 2, 34, OyVey.colorManager.getColorWithAlpha(255));
        }
        event.getContext().drawTextWithShadow(mc.textRenderer, "TPS: " + Formatting.WHITE + (int) OyVey.serverManager.getTPS(), 2, 46, OyVey.colorManager.getColorWithAlpha(255));
        if (tps.getValue()) {
            event.getContext().drawTextWithShadow(mc.textRenderer, "FPS: " + Formatting.WHITE + mc.getCurrentFps(), 2, 58, OyVey.colorManager.getColorWithAlpha(255));
        }
        if (xyz.getValue()) {
            event.getContext().drawTextWithShadow(mc.textRenderer, "XYZ: " + Formatting.WHITE + mc.player.getBlockX() + " " + mc.player.getBlockY() + " " + mc.player.getBlockZ() + (nether ? (" [" + mc.player.getBlockX() * 8 + ", " + mc.player.getBlockY() + ", " + mc.player.getBlockZ() * 8 + "]") : (" [" + mc.player.getBlockX() / 8 + ", " + mc.player.getBlockY() / 8 + ", " + mc.player.getBlockZ() / 8 + "]")), 2, height - 11 - j, OyVey.colorManager.getColorWithAlpha(255));
        }
        if (direction.getValue()) {
            event.getContext().drawTextWithShadow(mc.textRenderer, getDirection4D() + Formatting.WHITE + "Yaw: " + (int) yawPitch + " " + "Pitch: " + (int) mc.player.getPitch(), 2, height - 11 - j - 11, OyVey.colorManager.getColorWithAlpha(255));
        }
        if (time.getValue()) {
            String time = "Time: " + Formatting.WHITE + new SimpleDateFormat("HH:mm:ss").format(new Date());
            event.getContext().drawTextWithShadow(mc.textRenderer, time + Formatting.WHITE, (width / 2) - mc.textRenderer.getWidth(time) / 2,  11, OyVey.colorManager.getColorWithAlpha(255));
        }
        if (arrayList.getValue()) {
            event.getContext().drawTextWithShadow(mc.textRenderer, "Good to see you, " + Formatting.WHITE + mc.player.getName().getString(), (width / 2) - mc.textRenderer.getWidth("Good to see you, " + mc.player.getName().getString()) / 2, 2, OyVey.colorManager.getColorWithAlpha(255));
            for (Module module : OyVey.moduleManager.getEnabledModules().stream().filter(Module::isDrawn).sorted(Comparator.comparing(module -> mc.textRenderer.getWidth(module.getFullArrayString()) * -1)).collect(Collectors.toList())) {
                if (!module.isDrawn()) continue;
                String str = module.getName() + Formatting.GRAY + (module.getDisplayInfo() != null ? " [" + Formatting.WHITE + module.getDisplayInfo() + Formatting.GRAY + "]" : "");

                event.getContext().drawTextWithShadow(mc.textRenderer, str, (int) ((width - mc.textRenderer.getWidth(str) - 2f)), (2 + y * 10), OyVey.colorManager.getColorWithAlpha(255));

                y++;
            }
        }

        int z = mc.getWindow().getScaledHeight() - mc.textRenderer.fontHeight - 3;

        for (StatusEffectInstance effectInstance : mc.player.getStatusEffects()) {
            StatusEffect effect = effectInstance.getEffectType().value();
            String effectName = Text.translatable(effect.getTranslationKey()).getString();

            int durationTicks = effectInstance.getDuration();

            String timeText;

            if (durationTicks <= 0 || durationTicks > 1000000) {
                timeText = "\u221E";
            } else {
                int totalSeconds = durationTicks / 20;
                int minutes = totalSeconds / 60;
                int seconds = totalSeconds % 60;
                timeText = String.format("%d:%02d", minutes, seconds);
            }

            String text = effectName + ": " + Formatting.WHITE + timeText;

            int textWidth = mc.textRenderer.getWidth(text);
            int screenWidth = mc.getWindow().getScaledWidth();

            int x = screenWidth - textWidth;

            event.getContext().drawTextWithShadow(mc.textRenderer, text, x, z, OyVey.colorManager.getColorWithAlpha(255));
            z -= mc.textRenderer.fontHeight + 2;
        }
    }

    public String getDirection4D() {
        int yaw = getYaw4D();

        if (yaw == 0) {
            return "South (+Z) " ;
        }
        if (yaw == 1) {
            return "West (-X) " ;
        }
        if (yaw == 2) {
            return "North (-Z) " ;
        }
        if (yaw == 3) {
            return "East (+X) " ;
        }
        return "Loading..." ;
    }


    private int getYaw4D() {
        return MathHelper.floor((double) (mc.player.getYaw() * 4.0f / 360.0f) + 0.5) & 3;
    }
}