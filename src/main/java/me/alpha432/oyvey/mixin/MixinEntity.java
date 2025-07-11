package me.alpha432.oyvey.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import me.alpha432.oyvey.OyVey;
import me.alpha432.oyvey.features.modules.exploit.AntiVanish;
import me.alpha432.oyvey.features.modules.movement.NoSlow;
import me.alpha432.oyvey.features.modules.player.Velocity;
import me.alpha432.oyvey.features.modules.render.NoRender;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(Entity.class)
public class MixinEntity {
    @Inject(method = "pushAwayFrom", at = @At("HEAD"), cancellable = true)
    public void pushAwayFrom(Entity entity, CallbackInfo callbackInfo) {
        Velocity velocity = OyVey.moduleManager.getModuleByClass(Velocity.class);
        if (velocity.isEnabled() && velocity.entityPush.getValue()) {
            callbackInfo.cancel();
        }
    }

    @Inject(method = "isOnFire", at = @At("HEAD"), cancellable = true)
    public void fire(CallbackInfoReturnable<Boolean> cir) {
        NoRender noRender = OyVey.moduleManager.getModuleByClass(NoRender.class);
        if (noRender.isEnabled() && noRender.fire.getValue()) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "isInvisible", at = @At("HEAD"), cancellable = true)
    private void rendervanish(CallbackInfoReturnable<Boolean> cir) {
        AntiVanish antiVanish = OyVey.moduleManager.getModuleByClass(AntiVanish.class);
        if (antiVanish != null && antiVanish.isEnabled()) {
            cir.setReturnValue(false);
        }
    }


        @Inject(method = "isInvisibleTo", at = @At("HEAD"), cancellable = true)
        private void vanish(PlayerEntity player, CallbackInfoReturnable <Boolean> cir){
            AntiVanish antiVanish = OyVey.moduleManager.getModuleByClass(AntiVanish.class);
            if (antiVanish != null && antiVanish.isEnabled()) {
                cir.setReturnValue(false);
            }
        }
        @ModifyExpressionValue(method = "getVelocityMultiplier", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;getBlock()Lnet/minecraft/block/Block;"))
         private Block push(Block original) {
            NoSlow noSlow = OyVey.moduleManager.getModuleByClass(NoSlow.class);
            if (noSlow.isEnabled()) {
                if ((original == Blocks.SOUL_SAND && noSlow.soulSand.getValue()) || (original == Blocks.HONEY_BLOCK && noSlow.honey.getValue())) {
                    return Blocks.OBSERVER;
                }
            }
            return original;
        }
    }


