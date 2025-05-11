package me.alpha432.oyvey.mixin;

import me.alpha432.oyvey.OyVey;
import me.alpha432.oyvey.features.modules.render.NoRender;
import net.minecraft.block.enums.CameraSubmersionType;
import net.minecraft.client.render.Camera;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Camera.class)
public class CameraMixin {
    @Inject(method = "getSubmersionType", at = @At("HEAD"), cancellable = true)
    private void getSubmergedFluidState(CallbackInfoReturnable<CameraSubmersionType> ci) {
        NoRender noRender = OyVey.moduleManager.getModuleByClass(NoRender.class);
        ci.setReturnValue(CameraSubmersionType.NONE);
        if (noRender.isEnabled()&& noRender.liquidOverlay.getValue())
            ci.cancel();
    }
}
