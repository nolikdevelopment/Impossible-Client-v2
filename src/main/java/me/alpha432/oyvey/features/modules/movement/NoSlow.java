package me.alpha432.oyvey.features.modules.movement;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.settings.Setting;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.realms.gui.screen.RealmsMainScreen;
import net.minecraft.client.util.InputUtil;


public class NoSlow extends Module {
    public Setting<Boolean> food = bool("Food", true);
    public Setting<Boolean> portals = bool("Portals", true);
    public Setting<Boolean> soulSand = bool("SoulSand", true);
    public Setting<Boolean> honey = bool("Honey", true);
    public Setting<Boolean> slime = bool("Slime", true);
    public Setting<Boolean> sneak = bool("Sneak", true);
    public Setting<Boolean> berry = bool("Berry", true);
    public Setting<Boolean> cobweb = bool("Cobweb", true);
    public Setting<Boolean> guimove = bool("GuiMove", true);


    public NoSlow() {
        super("NoSlow", "", Category.MOVEMENT, true, false, false);
    }
    @Override public void onUpdate() {
        if (fullNullCheck()) return;
        if (guimove.getValue()) {
            for (KeyBinding binding : new KeyBinding[]{mc.options.forwardKey, mc.options.jumpKey, mc.options.backKey, mc.options.sprintKey, mc.options.leftKey, mc.options.rightKey}) {
                binding.setPressed(InputUtil.isKeyPressed(mc.getWindow().getHandle(), InputUtil.fromTranslationKey(binding.getBoundKeyTranslationKey()).getCode()));
            }
        }
    }
}



