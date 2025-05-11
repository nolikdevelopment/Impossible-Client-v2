package me.alpha432.oyvey.mixin;

import me.alpha432.oyvey.OyVey;
import me.alpha432.oyvey.features.modules.misc.UnfocucedFPS;
import net.minecraft.client.option.InactivityFpsLimiter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static me.alpha432.oyvey.util.traits.Util.mc;

@Mixin(InactivityFpsLimiter.class)
public class InactivityFpsLimiterMixin {
    @Inject(method = "update", at = @At("HEAD"), cancellable = true)
    private void getFramerateLimit(CallbackInfoReturnable<Integer> info) {
        UnfocucedFPS unfocucedFPS = OyVey.moduleManager.getModuleByClass(UnfocucedFPS.class);

        if (unfocucedFPS.isEnabled() && !mc.isWindowFocused()) {
            info.setReturnValue(unfocucedFPS.limit.getValue().intValue());

        }


    }
}
