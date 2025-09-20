package me.alpha432.oyvey.features.modules.misc;

import com.google.common.eventbus.Subscribe;
import me.alpha432.oyvey.event.impl.PacketEvent;
import me.alpha432.oyvey.features.modules.Module;
import net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

import java.util.Set;

public class NoSoundLag extends Module {
    public final static Set<RegistryEntry<SoundEvent>> ARMOUR = Set.of(
            SoundEvents.ITEM_ARMOR_EQUIP_GENERIC,
            SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,
            SoundEvents.ITEM_ARMOR_EQUIP_IRON,
            SoundEvents.ITEM_ARMOR_EQUIP_GOLD,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER
    );
    public final static Set<SoundEvent> WITHER = Set.of(
            SoundEvents.ENTITY_WITHER_AMBIENT,
            SoundEvents.ENTITY_WITHER_DEATH,
            SoundEvents.ENTITY_WITHER_BREAK_BLOCK,
            SoundEvents.ENTITY_WITHER_SPAWN,
            SoundEvents.ENTITY_WITHER_HURT,
            SoundEvents.ENTITY_WITHER_SKELETON_HURT
    );
    public NoSoundLag() {
        super("NoSoundLag", "", Category.MISC, true, false, false);
    }

    @Subscribe
    private void onPacket(PacketEvent.Receive event) {
        if (event.getPacket() instanceof PlaySoundS2CPacket packet) {
            if (ARMOUR.contains(packet.getSound())) {
                    event.cancel();
                }
            if (WITHER.contains(packet.getSound())) {
                event.cancel();
            }
            }
        }
    }