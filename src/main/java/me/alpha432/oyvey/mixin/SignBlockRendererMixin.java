package me.alpha432.oyvey.mixin;


import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.client.gui.screen.ingame.AbstractSignEditScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;


@Mixin(AbstractSignEditScreen.class)
public interface SignBlockRendererMixin {
    @Accessor("blockEntity")
    SignBlockEntity getBlockEntity();
}
