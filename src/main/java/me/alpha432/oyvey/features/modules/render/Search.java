package me.alpha432.oyvey.features.modules.render;

import me.alpha432.oyvey.event.impl.Render3DEvent;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.settings.Setting;
import me.alpha432.oyvey.util.BlockUtils;
import me.alpha432.oyvey.util.RenderUtil;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

import java.awt.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Search extends Module {
    public Setting<Boolean> chests = bool("Chests", true);
    public Setting<Boolean> echests = bool("EnderChests", true);
    public Setting<Boolean> shulkers = bool("Shulkers", true);
    public Setting<Boolean> items = bool("Items", true);
    public Setting<Boolean> beds = bool("Beds", true);
    public Setting<Boolean> furnace = bool("Furnaces", true);
    public Setting<Boolean> barrels = bool("Barrels", true);
    public Setting<Boolean> spawners = bool("Spawners", true);
    public Setting<Float> lines = this.register(new Setting<>("Line:", 1.5f, 0.1f, 5f));
    private final Setting<Integer> range = num("Range", 30, 1, 300);
    //
    public Search() {
        super("Search", "", Category.RENDER, true, false, false);
    }

    @Override
    public void onRender3D(Render3DEvent event) {
        //
        ArrayList<BlockEntity> blockEntities = BlockUtils.getTileEntities()
                .collect(Collectors.toCollection(ArrayList::new));
        //
        for (BlockEntity blockEntity : blockEntities) {
            BlockPos pos = mc.player.getBlockPos();
            BlockPos blockPos = blockEntity.getPos();
            if (blockPos.isWithinDistance(pos, range.getValue())) {
                //
            if (blockEntity instanceof ChestBlockEntity && chests.getValue()) {
                Box box = new Box(blockEntity.getPos());
                RenderUtil.drawBox(event.getMatrix(), box, Color.ORANGE, lines.getValue());
                RenderUtil.drawBoxFilled(event.getMatrix(), box, new Color(255, 165, 0, 86));
            }
            if (blockEntity instanceof EnderChestBlockEntity && echests.getValue()) {
                Box box = new Box(blockEntity.getPos());
                RenderUtil.drawBox(event.getMatrix(), box, Color.MAGENTA, lines.getValue());
                RenderUtil.drawBoxFilled(event.getMatrix(), box, new Color(255, 0, 255, 86));
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
                if (blockEntity instanceof BedBlockEntity && beds.getValue()) {
                    Box box = new Box(blockEntity.getPos());
                    RenderUtil.drawBox(event.getMatrix(), box, Color.ORANGE, lines.getValue());
                    RenderUtil.drawBoxFilled(event.getMatrix(), box, new Color(255, 165, 0, 86));
                }
            }
            if (items.getValue()) {
                for (Entity entity : mc.world.getEntities()) {
                    Vec3d pos1 = mc.player.getPos();
                    if (entity instanceof ItemEntity) {
                        Vec3d item = entity.getPos();
                        if (pos1.distanceTo(item) <= range.getValue()) {
                            RenderUtil.drawBox(event.getMatrix(), entity.getBoundingBox(), Color.WHITE, lines.getValue());
                            RenderUtil.drawBoxFilled(event.getMatrix(), entity.getBoundingBox(), new Color(227, 224, 216, 50));
                        }
                    }
                }
            }
            if (blockEntity instanceof Spawner && spawners.getValue()) {
                Box box = new Box(blockEntity.getPos());
                RenderUtil.drawBox(event.getMatrix(), box, Color.GREEN, lines.getValue());
                RenderUtil.drawBoxFilled(event.getMatrix(), box, new Color(5, 239, 114, 103));
            }
        }
    }
}
