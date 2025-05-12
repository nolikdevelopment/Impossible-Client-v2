package me.alpha432.oyvey.features.modules.combat;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.settings.Setting;
import net.minecraft.text.Text;

public class AutoLog extends Module {
    private final Setting<Float> hp = num("Healths:", 2f, 1f, 36f);
    public Setting<Boolean> autodisable = bool("AutoDisable", true);




    public AutoLog() {
        super("AutoLog", "", Category.COMBAT, true, false, false);
    }

    @Override
    public void onUpdate(){
        if (nullCheck()) return;

        int heal = (int) (mc.player.getHealth() + mc.player.getAbsorptionAmount());

        if (heal <= hp.getValue()){
            mc.getNetworkHandler().getConnection().disconnect(Text.of("Кикнут из за маленького количества ХП!"));



            if (autodisable.getValue()) disable();
        }
    }
}

