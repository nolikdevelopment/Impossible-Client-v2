package me.alpha432.oyvey.mixin;

import me.alpha432.oyvey.OyVey;
import me.alpha432.oyvey.features.modules.movement.SafeWalk;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static me.alpha432.oyvey.util.traits.Util.mc;

@Mixin(value = PlayerEntity.class, priority = 800)
public class MixinPlayerEntity {
   @Inject(method = "clipAtLedge", at = @At("HEAD"), cancellable = true)
   private void clipAtLedge(CallbackInfoReturnable<Boolean> info) {
       SafeWalk safeWalk = OyVey.moduleManager.getModuleByClass(SafeWalk.class);
       if (safeWalk.isEnabled())
           info.setReturnValue(true);
   }
    }