package me.alpha432.oyvey.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import me.alpha432.oyvey.OyVey;
import me.alpha432.oyvey.features.modules.exploit.AntiLevitation;
import me.alpha432.oyvey.features.modules.movement.ElytraFly;
import me.alpha432.oyvey.features.modules.movement.NoSlow;
import me.alpha432.oyvey.features.modules.render.NoRender;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import org.apache.commons.compress.harmony.unpack200.bytecode.ConstantValueAttribute;
import org.spongepowered.asm.mixin.Mixin;

import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Shadow
    public abstract boolean tryAttack(ServerWorld world, Entity target);

    @Unique
    private boolean prevFlying = false;

    @Shadow
    public abstract boolean teleport(double x, double y, double z, boolean particleEffects);

    @Shadow
    protected abstract boolean tryUseDeathProtector(DamageSource source);

    @Shadow public abstract boolean isGliding();

    @Inject(method = "spawnItemParticles", at = @At("HEAD"), cancellable = true)
    private void spawnItemParticles(ItemStack stack, int count, CallbackInfo info) {
        NoRender noRender = OyVey.moduleManager.getModuleByClass(NoRender.class);
        if (noRender.isEnabled() && stack.getComponents().contains(DataComponentTypes.FOOD) && noRender.eatparticle.getValue())
            info.cancel();
    }

    @ModifyExpressionValue(method = "travelMidAir", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;getStatusEffect(Lnet/minecraft/registry/entry/RegistryEntry;)Lnet/minecraft/entity/effect/StatusEffectInstance;"))
    private StatusEffectInstance travelMidAir$getStatusEffect(StatusEffectInstance original) {
        AntiLevitation antiLevitation = OyVey.moduleManager.getModuleByClass(AntiLevitation.class);
        if (antiLevitation.isEnabled()) return null;
        return original;
    }

    @ModifyExpressionValue(method = "tickMovement", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;hasStatusEffect(Lnet/minecraft/registry/entry/RegistryEntry;)Z", ordinal = 1))
    private boolean tickMovement$hasStatusEffect(boolean original) {
        AntiLevitation antiLevitation = OyVey.moduleManager.getModuleByClass(AntiLevitation.class);
        if (antiLevitation.isEnabled()) return false;
        return original;
    }

    // TODO: ПОЧИНИТЬ ЭТОТ ЕБНУТЫЙ МЕТОД НА ЭЛИТРУ ФЛАЙ
    @Inject(method = "isGliding", at = @At("TAIL"), cancellable = true)
    private void aVoid(CallbackInfoReturnable<Boolean> cir) {
        ElytraFly elytraFly = OyVey.moduleManager.getModuleByClass(ElytraFly.class);
        if (elytraFly.isEnabled() && elytraFly.mode.getValue() == ElytraFly.Mod.Bounce) {
            cir.setReturnValue(true);
        }
    }
}




