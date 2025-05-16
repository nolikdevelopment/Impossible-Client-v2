package me.alpha432.oyvey.features.modules.render;

import com.google.common.eventbus.Subscribe;
import me.alpha432.oyvey.event.impl.PacketEvent;
import me.alpha432.oyvey.features.modules.Module;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class FullBright extends Module {

    public FullBright() {
        super("FullBright", "", Category.RENDER, true, false, false);
    }

    @Override
    public void onEnable() {
        if (nullCheck()) return;
        if (!mc.player.hasStatusEffect(StatusEffects.NIGHT_VISION)) {
            mc.player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, StatusEffectInstance.INFINITE));


        }
    }

    @Override
    public void onDisable() {
        if (nullCheck()) return;
        if (mc.player.hasStatusEffect(StatusEffects.NIGHT_VISION)) {
            mc.player.removeStatusEffect(StatusEffects.NIGHT_VISION);
        }

    }
    @Subscribe
    public void onPacket(PacketEvent.Send event) {
        if (!mc.player.hasStatusEffect(StatusEffects.NIGHT_VISION));
        mc.player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, StatusEffectInstance.INFINITE));

    }
}

