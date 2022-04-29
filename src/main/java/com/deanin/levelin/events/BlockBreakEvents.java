package com.deanin.levelin.events;

import com.deanin.levelin.Levelin;
import com.deanin.levelin.config.ConfigRegister;
import com.deanin.levelin.player.Levels;
import com.deanin.levelin.player.Skills;
import com.deanin.levelin.skills.farming.Farming;
import com.deanin.levelin.skills.mining.Mining;
import com.deanin.utils.StringHelpers;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.gui.screen.LevelLoadingScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Property;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.TagKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.apache.commons.lang3.StringUtils;

public class BlockBreakEvents {
    private Block previousBrokenBlock;
    private int blockBreakStreak = 0;
    private int blockStreakLimit = 25;
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
            experienceManager(state);
        });
    }

    public void experienceManager(BlockState state) {
        Block brokenBlock = state.getBlock();
        if (manageFarming(state, brokenBlock)) {
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

    private boolean manageFarming(BlockState state, Block brokenBlock) {
        if (ConfigRegister.FARM_CONFIG.harvestCropXp.containsKey(StringHelpers.getBlockName(brokenBlock))) {
            Levelin.LOGGER.info("Block age: " + Farming.getCropAge(state));
            if (handleFarmExperience(brokenBlock, ConfigRegister.FARM_CONFIG.harvestCropXp.get(StringHelpers.getBlockName(brokenBlock)) * Farming.getCropAge(state))) {
                return true;
            }
        }
        return false;
    }

    private boolean manageCoalExperience(BlockState state, Block brokenBlock) {
        TagKey<Block> blockTags = BlockTags.COAL_ORES;
        if (state.isIn(blockTags)) {
            if (handleBlockExperience(brokenBlock, COAL_EXPERIENCE)) {
                return true;
            }
        }
        return false;
    }

    private boolean manageCopperExperience(BlockState state, Block brokenBlock) {
        TagKey<Block> blockTags = BlockTags.COPPER_ORES;
        if (state.isIn(blockTags)) {
            if (handleBlockExperience(brokenBlock, COPPER_EXPERIENCE)) {
                return true;
            }
        }
        return false;
    }

    private boolean manageIronExperience(BlockState state, Block brokenBlock) {
        TagKey<Block> blockTags = BlockTags.IRON_ORES;
        if (state.isIn(blockTags)) {
            if (handleBlockExperience(brokenBlock, IRON_EXPERIENCE)) {
                return true;
            }
        }
        return false;
    }

    private boolean manageRedstoneExperience(BlockState state, Block brokenBlock) {
        TagKey<Block> blockTags = BlockTags.REDSTONE_ORES;
        if (state.isIn(blockTags)) {
            if (handleBlockExperience(brokenBlock, REDSTONE_EXPERIENCE)) {
                return true;
            }
        }
        return false;
    }

    private boolean manageGoldExperience(BlockState state, Block brokenBlock) {
        TagKey<Block> blockTags = BlockTags.GOLD_ORES;
        if (state.isIn(blockTags)) {
            if (handleBlockExperience(brokenBlock, GOLD_EXPERIENCE)) {
                return true;
            }
        }
        return false;
    }

    private boolean manageDiamondExperience(BlockState state, Block brokenBlock) {
        TagKey<Block> blockTags = BlockTags.DIAMOND_ORES;
        if (state.isIn(blockTags)) {
            if (handleBlockExperience(brokenBlock, DIAMOND_EXPERIENCE)) {
                return true;
            }
        }
        return false;
    }

    private boolean manageNetheriteExperience(BlockState state, Block brokenBlock) {
        if (state.getBlock().equals(Blocks.ANCIENT_DEBRIS)) {
            if (handleBlockExperience(brokenBlock, NETHERITE_EXPERIENCE)) {
                return true;
            }
        }
        return false;
    }

    private boolean manageReplaceablePlants(BlockState state, Block brokenBlock) {
        TagKey<Block> blockTags = BlockTags.REPLACEABLE_PLANTS;
        if (state.isIn(blockTags)) {
            if (handleBlockExperience(brokenBlock, REPLACEABLE_PLANT_EXPERIENCE)) {
                return true;
            }
        }
        return false;
    }

    private boolean manageGrassBlockExperience(BlockState state, Block brokenBlock) {
        TagKey<Block> blockTags = BlockTags.SHOVEL_MINEABLE;
        if (state.isIn(blockTags)) {
            if (handleBlockExperience(brokenBlock, GRASS_BLOCK_EXPERIENCE)) {
                return true;
            }
        }
        return false;
    }

    private boolean manageBaseStoneOverworldExperience(BlockState state, Block brokenBlock) {
        TagKey<Block> blockTags = BlockTags.BASE_STONE_OVERWORLD;
        if (state.isIn(blockTags)) {
            if (handleBlockExperience(brokenBlock, BASE_STONE_OVERWORLD_EXPERIENCE)) {
                return true;
            }
        }
        return false;
    }

    private boolean manageWoodCuttingExperience(BlockState state, Block brokenBlock) {
        TagKey<Block> blockTags = BlockTags.LOGS;
        if (state.isIn(blockTags)){
            if (handleBlockExperience(brokenBlock, WOOD_CUTTING_EXPERIENCE)) {
                return true;
            }
        }
        return false;
    }

    private boolean handleBlockExperience(Block brokenBlock,
                                          int experienceToAward) {
        if (calculateBlockStreak(brokenBlock)) {
            return true;
        }
        mining.addExperience(experienceToAward);
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
            if (blockBreakStreak > blockStreakLimit) {
                return true;
            }
        } else {
            blockBreakStreak = 1;
        }
        return false;
    }
}
