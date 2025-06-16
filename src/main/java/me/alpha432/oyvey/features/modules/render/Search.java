package me.alpha432.oyvey.features.modules.render;

import me.alpha432.oyvey.event.impl.Render3DEvent;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.settings.Setting;
import me.alpha432.oyvey.util.BlockUtils;
import me.alpha432.oyvey.util.RenderUtil;
import net.minecraft.block.entity.*;
import net.minecraft.util.math.Box;

import java.awt.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Search extends Module {
    public Setting<Boolean> chests = bool("Chests", true);
    public Setting<Boolean> echests = bool("EnderChests", true);
    public Setting<Boolean> shulkers = bool("Shulkers", true);
    public Setting<Boolean> furnace = bool("Furnaces", true);
    public Setting<Boolean> barrels = bool("Barrels", true);
    public Setting<Float> lines = this.register(new Setting<>("Line:", 1.5f, 0.1f, 5f));
    public Search() {
        super("Search", "", Category.RENDER, true,false,false);
    }
    @Override
    public void onRender3D(Render3DEvent event) {
        if (chests.getValue()) {
            ArrayList<BlockEntity> blockEntities = BlockUtils.getTileEntities()
                    .collect(Collectors.toCollection(ArrayList::new));
            for (BlockEntity blockEntity : blockEntities) {
                if (blockEntity instanceof ChestBlockEntity || blockEntity instanceof TrappedChestBlockEntity || blockEntity instanceof BarrelBlockEntity) {
                    Box box = new Box(blockEntity.getPos());
                    RenderUtil.drawBox(event.getMatrix(), box, Color.ORANGE, lines.getValue());
                    RenderUtil.drawBoxFilled(event.getMatrix(), box, new Color(255, 165, 0, 86));
                }
                if (blockEntity instanceof EnderChestBlockEntity && echests.getValue()) {
                    Box box = new Box(blockEntity.getPos());
                    RenderUtil.drawBox(event.getMatrix(), box, Color.ORANGE, lines.getValue());
                    RenderUtil.drawBoxFilled(event.getMatrix(), box, new Color(255, 165, 0, 86));
                }
                if (blockEntity instanceof ShulkerBoxBlockEntity && shulkers.getValue()) {
                    Box box = new Box(blockEntity.getPos());
                    RenderUtil.drawBox(event.getMatrix(), box, Color.MAGENTA, lines.getValue());
                    RenderUtil.drawBoxFilled(event.getMatrix(), box, new Color(255, 0, 255, 86));
                }
                if (blockEntity instanceof FurnaceBlockEntity && furnace.getValue()) {
                    Box box = new Box(blockEntity.getPos());
                    RenderUtil.drawBox(event.getMatrix(), box, Color.MAGENTA, lines.getValue());
                    RenderUtil.drawBoxFilled(event.getMatrix(), box, new Color(255, 0, 255, 86));
                }
                if (blockEntity instanceof BarrelBlockEntity && barrels.getValue()) {
                    Box box = new Box(blockEntity.getPos());
                    RenderUtil.drawBox(event.getMatrix(), box, Color.ORANGE, lines.getValue());
                    RenderUtil.drawBoxFilled(event.getMatrix(), box, new Color(255, 165, 0, 86));
                }
            }
        }
        }
    }
