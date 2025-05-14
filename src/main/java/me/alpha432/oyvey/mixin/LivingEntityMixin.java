package me.alpha432.oyvey.mixin;

import me.alpha432.oyvey.OyVey;
import me.alpha432.oyvey.features.modules.render.NoRender;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(method = "spawnItemParticles", at = @At("HEAD"), cancellable = true)
    private void spawnItemParticles(ItemStack stack, int count, CallbackInfo info) {
        NoRender noRender = OyVey.moduleManager.getModuleByClass(NoRender.class);
        if (noRender.isEnabled() && stack.getComponents().contains(DataComponentTypes.FOOD) && noRender.eatparticle.getValue())
            info.cancel();
    }
}
