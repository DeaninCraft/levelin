package com.deanin.levelin.events;

import com.deanin.levelin.Levelin;
import com.deanin.levelin.player.Levels;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.yarn.constants.MiningLevels;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.TagKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockBreakEvents {
    private Block previousBrokenBlock;
    private int blockBreakStreak = 0;
    private int blockStreakLimit = 25;
    private int replaceablePlantExperience = 1;
    private int grassBlockExperience = 1;
    private int baseStoneOverworldExperience = 2;
    private int woodCuttingExperience = 2;
    private int coalExperience = 3;
    private int copperExperience = 6;
    private int ironExperience = 8;
    private int redstoneExperience = 8;
    private int goldExperience = 12;
    private int diamondExperience = 16;
    private int netheriteExperience = 64;


    private static Levels levels;

    public BlockBreakEvents() {
        levels = Levelin.levels;
        previousBrokenBlock = Blocks.AIR;
        PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, blockEntity) -> {
            if (world.isClient) {
                return;
            }
            experienceManager(world, player, pos, state, blockEntity);
        });
    }

    public void experienceManager(World world, PlayerEntity player, BlockPos pos, BlockState state, BlockEntity blockEntity) {
        Block brokenBlock = state.getBlock();
        if (manageReplaceablePlants(state, brokenBlock)) return;
        if (manageGrassBlockExperience(state, brokenBlock)) return;
        if (manageBaseStoneOverworldExperience(state, brokenBlock)) return;
        if (manageWoodCuttingExperience(state, brokenBlock)) return;

        if (manageCoalExperience(state, brokenBlock)) return;
        if (manageCopperExperience(state, brokenBlock)) return;
        if (manageIronExperience(state, brokenBlock)) return;
        if (manageRedstoneExperience(state, brokenBlock)) return;
        if (manageGoldExperience(state, brokenBlock)) return;
        if (manageDiamondExperience(state, brokenBlock)) return;
        if (manageNetheriteExperience(state, brokenBlock)) return;

        previousBrokenBlock = brokenBlock;
    }

    private boolean manageCoalExperience(BlockState state, Block brokenBlock) {
        TagKey<Block> blockTags = BlockTags.COAL_ORES;
        if (state.isIn(blockTags)) {
            if (handleBlockExperience(brokenBlock, coalExperience)) return true;
        }
        return false;
    }

    private boolean manageCopperExperience(BlockState state, Block brokenBlock) {
        TagKey<Block> blockTags = BlockTags.COPPER_ORES;
        if (state.isIn(blockTags)) {
            if (handleBlockExperience(brokenBlock, copperExperience)) return true;
        }
        return false;
    }

    private boolean manageIronExperience(BlockState state, Block brokenBlock) {
        TagKey<Block> blockTags = BlockTags.IRON_ORES;
        if (state.isIn(blockTags)) {
            if (handleBlockExperience(brokenBlock, ironExperience)) return true;
        }
        return false;
    }

    private boolean manageRedstoneExperience(BlockState state, Block brokenBlock) {
        TagKey<Block> blockTags = BlockTags.REDSTONE_ORES;
        if (state.isIn(blockTags)) {
            if (handleBlockExperience(brokenBlock, redstoneExperience)) return true;
        }
        return false;
    }

    private boolean manageGoldExperience(BlockState state, Block brokenBlock) {
        TagKey<Block> blockTags = BlockTags.GOLD_ORES;
        if (state.isIn(blockTags)) {
            if (handleBlockExperience(brokenBlock, goldExperience)) return true;
        }
        return false;
    }

    private boolean manageDiamondExperience(BlockState state, Block brokenBlock) {
        TagKey<Block> blockTags = BlockTags.DIAMOND_ORES;
        if (state.isIn(blockTags)) {
            if (handleBlockExperience(brokenBlock, diamondExperience)) return true;
        }
        return false;
    }

    private boolean manageNetheriteExperience(BlockState state, Block brokenBlock) {
        if (state.getBlock().equals(Blocks.ANCIENT_DEBRIS)) {
            if (handleBlockExperience(brokenBlock, netheriteExperience)) return true;
        }
        return false;
    }

    private boolean manageReplaceablePlants(BlockState state, Block brokenBlock) {
        TagKey<Block> blockTags = BlockTags.REPLACEABLE_PLANTS;
        if (state.isIn(blockTags)) {
            if (handleBlockExperience(brokenBlock, replaceablePlantExperience)) return true;
        }
        return false;
    }

    private boolean manageGrassBlockExperience(BlockState state, Block brokenBlock) {
        TagKey<Block> blockTags = BlockTags.SHOVEL_MINEABLE;
        if (state.isIn(blockTags)) {
            if (handleBlockExperience(brokenBlock, grassBlockExperience)) return true;
        }
        return false;
    }

    private boolean manageBaseStoneOverworldExperience(BlockState state, Block brokenBlock) {
        TagKey<Block> blockTags = BlockTags.BASE_STONE_OVERWORLD;
        if (state.isIn(blockTags)) {
            if (handleBlockExperience(brokenBlock, baseStoneOverworldExperience)) return true;
        }
        return false;
    }

    private boolean manageWoodCuttingExperience(BlockState state, Block brokenBlock) {
        TagKey<Block> blockTags = BlockTags.LOGS;
        if (state.isIn(blockTags)){
            if (handleBlockExperience(brokenBlock, woodCuttingExperience)) return true;
        }
        return false;
    }

    private boolean handleBlockExperience(Block brokenBlock,
                                          int experienceToAward) {
        if (brokenBlock.equals(previousBrokenBlock)) {
            blockBreakStreak++;
            if (blockBreakStreak > blockStreakLimit) {
                return true;
            }
        } else {
            blockBreakStreak = 1;
        }
        levels.addExperience(experienceToAward);
        return false;
    }


}
