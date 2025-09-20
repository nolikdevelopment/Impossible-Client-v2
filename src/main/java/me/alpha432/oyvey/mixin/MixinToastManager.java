package me.alpha432.oyvey.mixin;

import me.alpha432.oyvey.OyVey;
import me.alpha432.oyvey.features.modules.render.NoRender;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.toast.ToastManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ToastManager.class)
public class MixinToastManager {
    @Inject(method = "draw", at = @At("HEAD"), cancellable = true)
    private void aVoid(DrawContext context, CallbackInfo ci) {
        NoRender noRender = OyVey.moduleManager.getModuleByClass(NoRender.class);
        if (noRender.isEnabled() && noRender.toast.getValue()) {
            ci.cancel();
        }
    }
}
