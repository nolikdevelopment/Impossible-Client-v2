package me.alpha432.oyvey.mixin;

import me.alpha432.oyvey.OyVey;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.SplashTextRenderer;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.realms.gui.screen.RealmsNotificationsScreen;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public abstract class TitleScreenMixin extends Screen {
    @Shadow
    private long backgroundFadeStart;

    @Shadow
    @Final
    private boolean doBackgroundFade;

    public TitleScreenMixin(Text title) {
        super(title);
    }

    @Inject(method = "render", at = @At("TAIL"))
    public void hookRender(final DrawContext context, final int mouseX, final int mouseY, final float delta, final CallbackInfo info) {
        float f = this.doBackgroundFade ? (float) (Util.getMeasuringTimeMs() - this.backgroundFadeStart) / 1000.0f : 1.0f;
        float g = this.doBackgroundFade ? MathHelper.clamp(f - 1.0f, 0.0f, 1.0f) : 1.0f;
        int i = MathHelper.ceil(g * 255.0f) << 24;
        if ((i & 0xFC000000) == 0) {
            return;
        }
        context.drawTextWithShadow(textRenderer, Formatting.WHITE + OyVey.NAME + " ", 1, height - (client.textRenderer.fontHeight * 60) - -1, 1  | i);


    }




}

