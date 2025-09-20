package me.alpha432.oyvey.features.modules.combat;

import com.mojang.datafixers.kinds.IdF;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.settings.Setting;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.SlotActionType;

public class AutoTotem extends Module {
    private Setting<Mode> mode = mode("Item:", Mode.Totem);
    private final Setting<Integer> hp = num("Healths:", 2, 1, 36);

    // TODO: ЕСЛИ У НАС ВКЛЮЧЕН КРИСТАЛЛ МОД, СВАПНУТЬ НА ТОТЕМ ПРИ МАЛЕНЬКОМ КОЛ-ВЕ ХП, СВАП В ОФФХЕНДЕ
    private enum Mode {
        Totem, Crystal, Appple
    }

    public AutoTotem() {
        super("AutoTotem", "", Category.COMBAT, true, false, false);
    }

    @Override
    public void onTick() {
        if (fullNullCheck()) return;
        if ((mc.player.getHealth() + mc.player.getAbsorptionAmount()) <= hp.getValue()) {
            if (mode.getValue() == Mode.Totem) {
                if (mc.player.getOffHandStack().getItem().equals(Items.TOTEM_OF_UNDYING)) return;
                if (mc.currentScreen instanceof InventoryScreen || mc.currentScreen == null) {
                    for (int i = 9; i < 45; i++) {
                        if (mc.player.getInventory().getStack(i >= 36 ? i - 36 : i).getItem() == Items.TOTEM_OF_UNDYING) {
                            mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId, i, 0, SlotActionType.PICKUP, mc.player);
                            mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId, 45, 0, SlotActionType.PICKUP, mc.player);
                        }
                    }
                }
            }
            if (mode.getValue() == Mode.Crystal) {
                if (mc.player.getOffHandStack().getItem().equals(Items.END_CRYSTAL)) return;
                if (mc.currentScreen instanceof InventoryScreen || mc.currentScreen == null) {
                    for (int i = 9; i < 45; i++) {
                        if (mc.player.getInventory().getStack(i >= 36 ? i - 36 : i).getItem() == Items.END_CRYSTAL) {
                            mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId, i, 0, SlotActionType.PICKUP, mc.player);
                            mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId, 45, 0, SlotActionType.PICKUP, mc.player);
                        }
                    }
                }
            }
            if (mode.getValue() == Mode.Appple) {
                if (mc.player.getOffHandStack().getItem().equals(Items.ENCHANTED_GOLDEN_APPLE)) return;
                if (mc.currentScreen instanceof InventoryScreen || mc.currentScreen == null) {
                    for (int i = 9; i < 45; i++) {
                        mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId, i, 0, SlotActionType.PICKUP, mc.player);
                        mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId, 45, 0, SlotActionType.PICKUP, mc.player);
                    }
                }
            }
        }
    }
}