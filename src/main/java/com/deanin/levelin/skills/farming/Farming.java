package com.deanin.levelin.skills.farming;

import com.deanin.levelin.Levelin;
import com.deanin.levelin.skills.Skill;
import com.deanin.levelin.skills.TalentTree;
import com.deanin.utils.StringHelpers;
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
        for (Property property:state.getProperties()) {
            if(property.getName() == "age") AGE = property;
        }
        return Integer.parseInt(state.get(AGE).toString());
    }

    public static boolean hasAttachedStem(World world, Block brokenBlock, BlockPos pos) {
        GourdBlock gourd = (GourdBlock) brokenBlock;
        BlockPos[] directions = {pos.north(1), pos.east(1), pos.south(1), pos.west(1)};
        for (BlockPos dir : directions) {
            if(isAttachedStem(world.getBlockState(dir), gourd)) return true;
        }
        return false;
    }

    public static boolean isAttachedStem(BlockState state, GourdBlock gourd) {
        if(gourd.getStem().toString().contains(StringHelpers.getBlockName(state.getBlock()))) return true;
        return false;
    }

    public static int getAttachedStalks(World world, Block brokenBlock, BlockState state, BlockPos pos) {
        int count = getAboveStalks(world, brokenBlock, pos);

        if(count == 0 && !brokenBlock.toString().contains(StringHelpers.getBlockName(world.getBlockState(pos.down((1))).getBlock())) && getCropAge(state) == 0) {
            return 0;
        }
        return count + 1;
    }
    public static int getAboveStalks(World world, Block block, BlockPos pos) {
        int count = 0;
        boolean hasAbove = true;
        while(hasAbove) {
            if (block.toString().contains(StringHelpers.getBlockName(world.getBlockState(pos.up((count + 1))).getBlock()))) {
                count++;
            }
            else hasAbove=false;
        }
        Levelin.LOGGER.info("Above stalks: " + count);
        return count;
    }

}
