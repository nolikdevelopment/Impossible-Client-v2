package me.alpha432.oyvey.features.modules.misc;

import me.alpha432.oyvey.OyVey;
import me.alpha432.oyvey.features.commands.Command;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.settings.Setting;
import me.alpha432.oyvey.util.InventoryUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.EnderPearlItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.PlayerInteractItemC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
import org.apache.http.util.EntityUtils;
import org.lwjgl.glfw.GLFW;

public class MCF extends Module {
    public Setting<Boolean> friend = bool("Friend", true);
    private boolean pressed;

    public MCF() {
        super("MCF", "Middle click friend", Category.MISC, true, false, false);
    }

    @Override
    public void onTick() {
        if (friend.getValue() == true) {
            if (GLFW.glfwGetMouseButton(mc.getWindow().getHandle(), 2) == 1) {
                if (!pressed) {
                    Entity targetedEntity = mc.targetedEntity;
                    if (!(targetedEntity instanceof PlayerEntity)) return;
                    String name = ((PlayerEntity) targetedEntity).getGameProfile().getName();

                    if (OyVey.friendManager.isFriend(name)) {
                        OyVey.friendManager.removeFriend(name);
                        Command.sendMessage(Formatting.RED + name + Formatting.RED + " has been unfriended.");
                    } else {
                        OyVey.friendManager.addFriend(name);
                        Command.sendMessage(Formatting.AQUA + name + Formatting.AQUA + " has been friended.");
                    }

                    pressed = true;
                }
            } else {
                pressed = false;
            }


        }



                     }
                 }


