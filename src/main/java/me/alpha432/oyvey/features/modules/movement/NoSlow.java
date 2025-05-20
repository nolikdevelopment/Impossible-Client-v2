package me.alpha432.oyvey.features.modules.movement;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.settings.Setting;
import net.minecraft.component.DataComponentTypes;

public class NoSlow extends Module {
    public Setting<Boolean> food = bool("Food", true);
    public NoSlow() {
        super("NoSlow", "", Category.MOVEMENT, true,false,false);
    }
    @Override
    public void onUpdate() {
        mc.options.sneakKey.setPressed(false);
    }
    public void noSlow() {
        if (!food.getValue()&& mc.player.getActiveItem().getComponents().contains(DataComponentTypes.FOOD));
    }
}
