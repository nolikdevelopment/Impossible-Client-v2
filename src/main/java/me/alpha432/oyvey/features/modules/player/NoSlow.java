package me.alpha432.oyvey.features.modules.player;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.settings.Setting;
import net.minecraft.component.DataComponentTypes;

public class NoSlow extends Module {
    private final Setting<Boolean> food = new Setting<>("Food", true);

    public NoSlow() {
        super("NoSlow", "", Category.PLAYER, true, false, false);
    }

    @Override
    public void onUpdate() {
        mc.options.sneakKey.setPressed(false);
        if (mc.player.isUsingItem() && !mc.player.isRiding()) ;
    }

    private boolean canNoSlow() {
        if (!food.getValue() && mc.player.getActiveItem().getComponents().contains(DataComponentTypes.FOOD));
        return false;
    }
}

