package me.alpha432.oyvey.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import me.alpha432.oyvey.OyVey;
import me.alpha432.oyvey.event.impl.EventLimit;
import me.alpha432.oyvey.features.modules.exploit.MultiTask;
import me.alpha432.oyvey.features.modules.misc.UnfocucedFPS;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static me.alpha432.oyvey.util.traits.Util.EVENT_BUS;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {

    @Shadow @Nullable public ClientPlayerEntity player;

    @Shadow public abstract boolean forcesUnicodeFont();

    @ModifyExpressionValue(method = "doItemUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerInteractionManager;isBreakingBlock()Z"))
    private boolean doItemUseModifyIsBreakingBlock(boolean original) {
        MultiTask multiTask = OyVey.moduleManager.getModuleByClass(MultiTask.class);
        if(multiTask.isEnabled()) return false;
        return original;
    }

    @ModifyExpressionValue(method = "handleBlockBreaking", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;isUsingItem()Z"))
    private boolean handleBlockBreakingModifyIsUsingItem(boolean original) {
        MultiTask multiTask = OyVey.moduleManager.getModuleByClass(MultiTask.class);
        if(multiTask.isEnabled()) return false;
        return original;
    }
    @Inject(method = "getInactivityFpsLimiter", at = @At("HEAD"), cancellable = true)
    private void aVoid(CallbackInfoReturnable<Integer> info) {
        UnfocucedFPS unfocucedFPS = OyVey.moduleManager.getModuleByClass(UnfocucedFPS.class);
        EventLimit eventLimit = new EventLimit();
        EVENT_BUS.post(new EventLimit());
        if (eventLimit.isCancelled() && unfocucedFPS.isEnabled()) {
            info.setReturnValue(eventLimit.getLimit());
        }
    }
    }
