package me.alpha432.oyvey.mixin;

import me.alpha432.oyvey.OyVey;
import me.alpha432.oyvey.features.modules.movement.NoSlow;
import net.minecraft.block.BlockState;
import net.minecraft.block.CobwebBlock;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.LavaFluid;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CobwebBlock.class)
public class MixinCobwebBlock {
    @Inject(method = "onEntityCollision", at = @At("HEAD"), cancellable = true)
    private void aVoid(BlockState state, World world, BlockPos pos, Entity entity, CallbackInfo ci) {
        NoSlow noSlow = OyVey.moduleManager.getModuleByClass(NoSlow.class);
        if (noSlow.isEnabled() && noSlow.cobweb.getValue()) {
            ci.cancel();
        }
    }
}
