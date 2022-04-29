package com.deanin.levelin.events;

import com.deanin.levelin.Levelin;
import com.deanin.levelin.config.ConfigRegister;
import com.deanin.levelin.player.Skills;
import com.deanin.levelin.skills.farming.Farming;
import com.deanin.levelin.skills.mining.Mining;
import com.deanin.utils.StringHelpers;
import com.deanin.levelin.Manager;
import com.deanin.levelin.skills.Skill;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.TagKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockBreakEvents {
    private Block previousBrokenBlock;
    private int blockBreakStreak = 0;
    private static final int REPLACEABLE_PLANT_EXPERIENCE = 1;
    private static final int GRASS_BLOCK_EXPERIENCE = 1;
    private static final int BASE_STONE_OVERWORLD_EXPERIENCE = 2;
    private static final int WOOD_CUTTING_EXPERIENCE = 2;
    private static final int COAL_EXPERIENCE = 3;
    private static final int COPPER_EXPERIENCE = 6;
    private static final int IRON_EXPERIENCE = 8;
    private static final int REDSTONE_EXPERIENCE = 8;
    private static final int GOLD_EXPERIENCE = 12;
    private static final int DIAMOND_EXPERIENCE = 16;
    private static final int NETHERITE_EXPERIENCE = 64;


    private Mining mining;
    private Farming farming;

    public BlockBreakEvents(Skills skills) {
        this.mining = skills.getMining();
        this.farming = skills.getFarming();
      
        previousBrokenBlock = Blocks.AIR;
        PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, blockEntity) -> {
            if (world.isClient) {
                return;
            }
            Levelin.LOGGER.info(state.toString());
            experienceManager(world, pos, state);
        });
    }

    public void experienceManager(World world, BlockPos pos, BlockState state) {
        Block brokenBlock = state.getBlock();
        if (manageFarming(world, pos, state, brokenBlock)) {
            return;
        }
        if (manageReplaceablePlants(state, brokenBlock)) {
            return;
        }
        if (manageGrassBlockExperience(state, brokenBlock)) {
            return;
        }
        if (manageBaseStoneOverworldExperience(state, brokenBlock)) {
            return;
        }
        if (manageWoodCuttingExperience(state, brokenBlock)) {
            return;
        }
        if (manageCoalExperience(state, brokenBlock)) {
            return;
        }
        if (manageCopperExperience(state, brokenBlock)) {
            return;
        }
        if (manageIronExperience(state, brokenBlock)) {
            return;
        }
        if (manageRedstoneExperience(state, brokenBlock)) {
            return;
        }
        if (manageGoldExperience(state, brokenBlock)) {
            return;
        }
        if (manageDiamondExperience(state, brokenBlock)) {
            return;
        }
        if (manageNetheriteExperience(state, brokenBlock)) {
            return;
        }

        previousBrokenBlock = brokenBlock;
    }

    private boolean manageFarming(World world, BlockPos pos, BlockState state, Block brokenBlock) {
        if (ConfigRegister.FARM_CONFIG.harvestCropXp.containsKey(StringHelpers.getBlockName(brokenBlock))) {
            if(handleFarmExperience(brokenBlock, ConfigRegister.FARM_CONFIG.harvestCropXp.get(StringHelpers.getBlockName(brokenBlock)) * Farming.getCropAge(state))) {
                return true;
            }
        }
        if (ConfigRegister.FARM_CONFIG.harvestMelonXp.containsKey(StringHelpers.getBlockName(brokenBlock))) {
            if(Farming.hasAttachedStem(world, brokenBlock, pos)) {
                if (handleFarmExperience(brokenBlock, ConfigRegister.FARM_CONFIG.harvestMelonXp.get(StringHelpers.getBlockName(brokenBlock)))) {
                    return true;
                }
            }
        }if (ConfigRegister.FARM_CONFIG.harvestStalkXp.containsKey(StringHelpers.getBlockName(brokenBlock))) {
            if (handleFarmExperience(brokenBlock, ConfigRegister.FARM_CONFIG.harvestStalkXp.get(StringHelpers.getBlockName(brokenBlock)) * Farming.getAttachedStalks(world, brokenBlock, state, pos))) {
                return true;
            }
        }
        return false;
    }

    private boolean manageCoalExperience(BlockState state, Block brokenBlock) {
        TagKey<Block> blockTags = BlockTags.COAL_ORES;
        if (state.isIn(blockTags)) {
            return handleBlockExperience(brokenBlock, COAL_EXPERIENCE, Manager.player.skills.getMining());
        }
        return false;
    }

    private boolean manageCopperExperience(BlockState state, Block brokenBlock) {
        TagKey<Block> blockTags = BlockTags.COPPER_ORES;
        if (state.isIn(blockTags)) {
            return handleBlockExperience(brokenBlock, COPPER_EXPERIENCE, Manager.player.skills.getMining());
        }
        return false;
    }

    private boolean manageIronExperience(BlockState state, Block brokenBlock) {
        TagKey<Block> blockTags = BlockTags.IRON_ORES;
        if (state.isIn(blockTags)) {
            return handleBlockExperience(brokenBlock, IRON_EXPERIENCE, Manager.player.skills.getMining());
        }
        return false;
    }

    private boolean manageRedstoneExperience(BlockState state, Block brokenBlock) {
        TagKey<Block> blockTags = BlockTags.REDSTONE_ORES;
        if (state.isIn(blockTags)) {
            return handleBlockExperience(brokenBlock, REDSTONE_EXPERIENCE, Manager.player.skills.getMining());
        }
        return false;
    }

    private boolean manageGoldExperience(BlockState state, Block brokenBlock) {
        TagKey<Block> blockTags = BlockTags.GOLD_ORES;
        if (state.isIn(blockTags)) {
            return handleBlockExperience(brokenBlock, GOLD_EXPERIENCE, Manager.player.skills.getMining());
        }
        return false;
    }

    private boolean manageDiamondExperience(BlockState state, Block brokenBlock) {
        TagKey<Block> blockTags = BlockTags.DIAMOND_ORES;
        if (state.isIn(blockTags)) {
            return handleBlockExperience(brokenBlock, DIAMOND_EXPERIENCE, Manager.player.skills.getMining());
        }
        return false;
    }

    private boolean manageNetheriteExperience(BlockState state, Block brokenBlock) {
        if (state.getBlock().equals(Blocks.ANCIENT_DEBRIS)) {
            return handleBlockExperience(brokenBlock, NETHERITE_EXPERIENCE, Manager.player.skills.getMining());
        }
        return false;
    }

    private boolean manageReplaceablePlants(BlockState state, Block brokenBlock) {
        TagKey<Block> blockTags = BlockTags.REPLACEABLE_PLANTS;
        if (state.isIn(blockTags)) {
            return handleBlockExperience(brokenBlock, REPLACEABLE_PLANT_EXPERIENCE, Manager.player.skills.getMining());
        }
        return false;
    }

    private boolean manageGrassBlockExperience(BlockState state, Block brokenBlock) {
        TagKey<Block> blockTags = BlockTags.SHOVEL_MINEABLE;
        if (state.isIn(blockTags)) {
            return handleBlockExperience(brokenBlock, GRASS_BLOCK_EXPERIENCE, Manager.player.skills.getWoodcutting());
        }
        return false;
    }

    private boolean manageBaseStoneOverworldExperience(BlockState state, Block brokenBlock) {
        TagKey<Block> blockTags = BlockTags.BASE_STONE_OVERWORLD;
        if (state.isIn(blockTags)) {
            return handleBlockExperience(brokenBlock, BASE_STONE_OVERWORLD_EXPERIENCE, Manager.player.skills.getMining());
        }
        return false;
    }

    private boolean manageWoodCuttingExperience(BlockState state, Block brokenBlock) {
        TagKey<Block> blockTags = BlockTags.LOGS;
        if (state.isIn(blockTags)){
            return handleBlockExperience(brokenBlock, WOOD_CUTTING_EXPERIENCE, Manager.player.skills.getWoodcutting());
        }
        return false;
    }

    private boolean handleBlockExperience(Block brokenBlock,
                                          int experienceToAward,
                                          Skill targetSkill) {
        if (calculateBlockStreak(brokenBlock)) {
            return true;
        }
        targetSkill.addExperience(experienceToAward);
        return false;
    }
    private boolean handleFarmExperience(Block brokenBlock,
                                          int experienceToAward) {
        if (calculateBlockStreak(brokenBlock)) {
            return true;
        }
        farming.addExperience(experienceToAward);
        return false;
    }

    private boolean calculateBlockStreak(Block brokenBlock) {
        if (brokenBlock.equals(previousBrokenBlock)) {
            blockBreakStreak++;
            int blockStreakLimit = 25;
            return blockBreakStreak > blockStreakLimit;
        } else {
            blockBreakStreak = 1;
        }
        return false;
    }
}
