package me.alpha432.oyvey.mixin;

import me.alpha432.oyvey.OyVey;
import me.alpha432.oyvey.features.modules.player.Velocity;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(Entity.class)
public class MixinEntity {
    @Inject(method = "pushAwayFrom", at = @At("HEAD"), cancellable = true)
    public void pushAwayFrom(Entity entity, CallbackInfo callbackInfo) {
        Velocity velocity = OyVey.moduleManager.getModuleByClass(Velocity.class);
        if (velocity.isEnabled()&& velocity.entityPush.getValue()) {
            callbackInfo.cancel();

        }

    }

}
