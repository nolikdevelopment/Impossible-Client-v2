package me.alpha432.oyvey.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import me.alpha432.oyvey.OyVey;
import me.alpha432.oyvey.features.modules.player.Velocity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FishingBobberEntity.class)
public class MixinFishingBobberEntity {
    @WrapOperation(method = "handleStatus", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/projectile/FishingBobberEntity;pullHookedEntity(Lnet/minecraft/entity/Entity;)V"))
    private void aVoid(FishingBobberEntity instance, Entity entity, Operation<Void> original) {
        Velocity velocity = OyVey.moduleManager.getModuleByClass(Velocity.class);
        if (velocity.isEnabled() && velocity.fishings.getValue()) {
            return;
        }
       original.call(instance, entity);
    }
}
