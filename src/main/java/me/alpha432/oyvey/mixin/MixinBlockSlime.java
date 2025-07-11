package me.alpha432.oyvey.mixin;

import me.alpha432.oyvey.OyVey;
import me.alpha432.oyvey.features.modules.movement.NoSlow;
import net.minecraft.block.BlockState;
import net.minecraft.block.SlimeBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SlimeBlock.class)
public class MixinBlockSlime {
    @Inject(method = "onSteppedOn", at = @At("HEAD"), cancellable = true)
    private void aVoid(World world, BlockPos pos, BlockState state, Entity entity, CallbackInfo ci) {
        NoSlow noSlow = OyVey.moduleManager.getModuleByClass(NoSlow.class);
        if (noSlow.slime.getValue()&& noSlow.isEnabled()) {
            ci.cancel();
        }
    }
}
