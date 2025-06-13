package me.alpha432.oyvey.features.modules.combat;

import me.alpha432.oyvey.OyVey;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.settings.Setting;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.text.Text;

public class AutoLog extends Module {
    private final Setting<Integer> hp = num("Healths:", 2, 1, 36);
    private final Setting<Integer> totem = num("Totems:", 2, 1, 36);
    private final Setting<Integer> range = num("InRange:", 20, 1, 120);
    public Setting<Boolean> playerRange = bool("playerRange", true);
    public Setting<Boolean> Yheight = bool("YHeight", true);
    private final Setting<Integer> height = num("Height:", 0, 0, 256);

    public AutoLog() {
        super("AutoLog", "", Category.COMBAT, true, false, false);
    }

    @Override
    public void onTick() {
        if (fullNullCheck()) return;
        if (mc.player.getHealth() <= hp.getValue()) {
            mc.getNetworkHandler().getConnection().disconnect(Text.of("У вас маленькое количество здоровья! " + (int) mc.player.getHealth()));
            disable();
        }
        if (mc.player.getInventory().count(Items.TOTEM_OF_UNDYING) == totem.getValue()) {
            mc.getNetworkHandler().getConnection().disconnect(Text.of("У вас маленькое количество тотемов!"));
            disable();
        }
        for (PlayerEntity player : mc.world.getPlayers()) {
            if (player == mc.player || OyVey.friendManager.isFriend(player.getName().getString())) continue;
            if (player.distanceTo(player) <= range.getValue() && playerRange.getValue()) {
                mc.getNetworkHandler().getConnection().disconnect(Text.of("Рядом с вами находится игрок, будьте осторожны!"));
                disable();
            }
        }
        if (mc.player.getY() < height.getValue() && Yheight.getValue()) {
            mc.getNetworkHandler().getConnection().disconnect(Text.of("Предел вашей высоты достил предела, выхожу с сервера!"));
            disable();
        }
    }
}




