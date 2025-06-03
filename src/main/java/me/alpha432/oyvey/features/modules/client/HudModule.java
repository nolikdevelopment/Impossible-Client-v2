package me.alpha432.oyvey.features.modules.client;

import me.alpha432.oyvey.OyVey;
import me.alpha432.oyvey.event.Event;
import me.alpha432.oyvey.event.impl.Render2DEvent;
import me.alpha432.oyvey.features.modules.Module;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.MathHelper;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class HudModule extends Module {
    public HudModule() {
        super("Hud", "hud", Category.CLIENT, true, false, false);
    }

    @Override
    public void onRender2D(Render2DEvent event) {
        int width = mc.getWindow().getScaledWidth();
        int height = mc.getWindow().getScaledHeight();
        int y = 0;
        int j = (mc.currentScreen instanceof ChatScreen) ? 13 : 0;
        event.getContext().drawTextWithShadow(
                mc.textRenderer,
                OyVey.NAME,
                2, 2,
                -1
        );
        event.getContext().drawTextWithShadow(
                mc.textRenderer,
                "ServerBrand: " + OyVey.serverManager.getServerBrand(),
                2, 12,
                -1
        );
        event.getContext().drawTextWithShadow(
                mc.textRenderer,
                "Speed: " + (int) OyVey.speedManager.getSpeedKpH() + " km/h",
                2, 22,
                -1
        );
        event.getContext().drawTextWithShadow(
                mc.textRenderer,
                "Ping: " + OyVey.serverManager.getPing() + "ms",
                2, 34,
                -1
        );
        event.getContext().drawTextWithShadow(
                mc.textRenderer,
                "TPS: " + (int) OyVey.serverManager.getTPS(),
                2, 46,
                -1
        );
        event.getContext().drawTextWithShadow(
                mc.textRenderer,
                "FPS: " + mc.getCurrentFps(),
                2, 58,
                -1
        );
        event.getContext().drawTextWithShadow(
                mc.textRenderer,
                "XYZ: " + mc.player.getBlockX() + " " + mc.player.getBlockY() + " " + mc.player.getBlockZ(),
                2, height - 11 - j,
                -1
        );
        event.getContext().drawTextWithShadow(
                mc.textRenderer,
                getDirection4D(),
                2, height - 11 - j -11,
                -1
        );
        event.getContext().drawTextWithShadow(
                mc.textRenderer,
                getDirection4D(),
                2, height - 11 - j -11,
                -1
        );
        event.getContext().drawTextWithShadow(
                mc.textRenderer,
                "Good to see you, " + mc.player.getName().getString(),
                380, 2,
        -1
        );
        for (Module module : OyVey.moduleManager.getEnabledModules().stream().filter(Module::isEnabled).sorted(Comparator.comparing(module -> mc.textRenderer.getWidth(module.getDisplayName()) * -1)).collect(Collectors.toList())) {
            String text = module.getName() + Formatting.GRAY + (this.getDisplayInfo() != null ? " [" + Formatting.WHITE + this.getDisplayInfo() + Formatting.GRAY + "]" : "");
            event.getContext().drawTextWithShadow(mc.textRenderer, text, width - mc.textRenderer.getWidth(text), 2 + y * 10, -1);
            y++;
        }
    }
    public String getDirection4D() {
        int yaw = getYaw4D();

        if (yaw == 0) {
            return "South (+Z)";
        }
        if (yaw == 1) {
            return "West (-X)";
        }
        if (yaw == 2) {
            return "North (-Z)";
        }
        if (yaw == 3) {
            return "East (+X)";
        }
        return "Loading...";
    }


    private int getYaw4D() {
        return MathHelper.floor((double) (mc.player.getYaw() * 4.0f / 360.0f) + 0.5) & 3;
    }
}