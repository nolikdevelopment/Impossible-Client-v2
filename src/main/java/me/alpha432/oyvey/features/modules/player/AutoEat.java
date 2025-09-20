package me.alpha432.oyvey.features.modules.player;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.settings.Setting;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.Item;



public class AutoEat extends Module {
    private int slot = 0;
    private final Setting<Integer> hp = num("Healths:", 2, 1, 36);
    private final Setting<Float> hunger = num("HungerCheck:", 0F, 1F, 20F);

    public AutoEat() {
        super("AutoEat", "", Category.PLAYER, true, false, false);
    }

    @Override
    public void onUpdate() {
        if (fullNullCheck()) return;
        if (mc.player.getHungerManager().getFoodLevel() <= hunger.getValue()) {
            mc.options.useKey.setPressed(true);
        }
        if (mc.player.getHealth() <= hp.getValue()) {
            mc.options.useKey.setPressed(true);
        }
        if (mc.player.forwardSpeed == 0 && mc.player.sidewaysSpeed == 0) {
            mc.options.useKey.setPressed(false);
        }
        if (!setFoods()) {
            mc.options.useKey.setPressed(false);
        }
    }
    @Override public void onDisable() {
        mc.options.useKey.setPressed(false);
        mc.player.getInventory().selectedSlot = slot;
    }
    public boolean setFoods() {
      Item edablya = mc.player.getMainHandStack().getItem();
      if (edablya.getComponents().contains(DataComponentTypes.FOOD)) {
          return true;
      }
      return false;
    }
}


