package com.deanin.levelin.skills.farming;

import com.deanin.levelin.Levelin;
import com.deanin.levelin.skills.Skill;
import com.deanin.levelin.skills.TalentTree;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.GourdBlock;
import net.minecraft.block.PumpkinBlock;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Farming extends Skill {

    public Farming(String name, String description, int level, int levelCap) {
        super(name, description, level, levelCap);
    }

    public Farming() {
        super("Farming", "Farming is the skill of harvesting crops and animals for food products.", 1, 100);
    }

    public static int getCropAge(BlockState state) {
        Property AGE = null;
        for (Property property : state.getProperties()) {
            if (property.getName() == "age")
                AGE = property;
        }
        return Integer.parseInt(state.get(AGE).toString());
    }

    public static boolean hasAttachedStem(World world, BlockPos pos, Block block, BlockState state) {
        Levelin.LOGGER.info("This is a gourd!");
        if (isAttachedStem(world.getBlockState(pos.east(1))) ||
                isAttachedStem(world.getBlockState(pos.west(1))) ||
                isAttachedStem(world.getBlockState(pos.north(1))) ||
                isAttachedStem(world.getBlockState(pos.south(1)))) {
            Levelin.LOGGER.info("It has a stem!");
            return true;
        }
        Levelin.LOGGER.info(world.getBlockState(pos.east(1)).toString());
        Levelin.LOGGER.info(world.getBlockState(pos.west(1)).toString());
        Levelin.LOGGER.info(world.getBlockState(pos.north(1)).toString());
        Levelin.LOGGER.info(world.getBlockState(pos.south(1)).toString());
        return false;
    }

    public static boolean isAttachedStem(BlockState state) {
        Levelin.LOGGER.info(state.toString());
        if (state.toString() == "Block{minecraft:pumpkin_stem}[age=7]"
                || state.toString() == "Block{minecraft:melon_stem}[age=7]") {
            Levelin.LOGGER.info("There is a stem near!");
            return true;
        }
        return false;
    }

}
