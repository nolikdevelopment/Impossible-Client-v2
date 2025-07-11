package me.alpha432.oyvey.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import me.alpha432.oyvey.OyVey;
import me.alpha432.oyvey.features.modules.exploit.NoEntityTrace;
import me.alpha432.oyvey.features.modules.render.NoBob;
import me.alpha432.oyvey.features.modules.render.NoRender;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {


    @Inject(method = "showFloatingItem", at = @At("HEAD"), cancellable = true)
    private void showFloatingItem(ItemStack floatingItem, CallbackInfo callbackInfo) {
        NoRender noRender = OyVey.moduleManager.getModuleByClass(NoRender.class);
        if (noRender.isEnabled() && noRender.totemanimation.getValue())
            callbackInfo.cancel();
    }

    @Inject(method = "bobView", at = @At("HEAD"), cancellable = true)
    private void bobViewHook(MatrixStack matrices, float tickDelta, CallbackInfo callbackInfo) {
        NoBob noBob = OyVey.moduleManager.getModuleByClass(NoBob.class);
        if (noBob.isEnabled())
            callbackInfo.cancel();
    }

    @Inject(method = "tiltViewWhenHurt", at = @At("HEAD"), cancellable = true)
    private void tiltViewWhenHurt(CallbackInfo callbackInfo) {
        NoRender noRender = OyVey.moduleManager.getModuleByClass(NoRender.class);
        if (noRender.isEnabled() && noRender.hurtcamera.getValue())
            callbackInfo.cancel();
    }

    @ModifyExpressionValue(method = "findCrosshairTarget", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/projectile/ProjectileUtil;raycast(Lnet/minecraft/entity/Entity;Lnet/minecraft/util/math/Vec3d;Lnet/minecraft/util/math/Vec3d;Lnet/minecraft/util/math/Box;Ljava/util/function/Predicate;D)Lnet/minecraft/util/hit/EntityHitResult;"))
    private EntityHitResult render(EntityHitResult original) {
        NoEntityTrace noEntityTrace = OyVey.moduleManager.getModuleByClass(NoEntityTrace.class);
        if (noEntityTrace.isEnabled()) {
            return null;
        }
        return original;
    }
}

