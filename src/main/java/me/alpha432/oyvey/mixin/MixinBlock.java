package me.alpha432.oyvey.mixin;

import me.alpha432.oyvey.OyVey;
import me.alpha432.oyvey.features.modules.movement.NoSlow;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Block.class)
public class MixinBlock {
     @Inject(method = "getSlipperiness", at = @At("HEAD"), cancellable = true)
    private void aVoid(CallbackInfoReturnable<Float> cir) {
         NoSlow noSlow = OyVey.moduleManager.getModuleByClass(NoSlow.class);
         if (noSlow.slime.getValue()&& noSlow.isEnabled()) {
             if ((Object) this == Blocks.SLIME_BLOCK) {
                 cir.setReturnValue(0.6f);
             }
         }
     }
}
