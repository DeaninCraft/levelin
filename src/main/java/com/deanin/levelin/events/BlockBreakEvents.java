package com.deanin.levelin.events;

import com.deanin.levelin.Levelin;
import com.deanin.levelin.config.ConfigRegister;
import com.deanin.levelin.player.Skills;
import com.deanin.levelin.skills.farming.Farming;
import com.deanin.levelin.skills.mining.Mining;
import com.deanin.levelin.skills.woodcutting.Woodcutting;
import com.deanin.utils.StringHelpers;
import com.deanin.levelin.skills.Skill;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockBreakEvents {

    private Block previousBrokenBlock;
    private int blockBreakStreak = 0;

    private Mining mining;
    private Farming farming;
    private Woodcutting woodcutting;

    public BlockBreakEvents(Skills skills) {
        this.mining = skills.getMining();
        this.farming = skills.getFarming();
        this.woodcutting = skills.getWoodcutting();

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
        if (manageMining(world, pos, state, brokenBlock)) {
            return;
        }
        if (manageWoodcutting(world, pos, state, brokenBlock)) {
            return;
        }
        previousBrokenBlock = brokenBlock;
    }

    private boolean manageFarming(World world, BlockPos pos, BlockState state, Block brokenBlock) {
        if (ConfigRegister.FARM_CONFIG.harvestCropXp.containsKey(StringHelpers.getBlockName(brokenBlock))) {
            if (handleSkillExperience(brokenBlock,
                    ConfigRegister.FARM_CONFIG.harvestCropXp.get(StringHelpers.getBlockName(brokenBlock))
                            * Farming.getCropAge(state),
                    farming)) {
                return true;
            }
        }
        if (ConfigRegister.FARM_CONFIG.harvestMelonXp.containsKey(StringHelpers.getBlockName(brokenBlock))) {
            if (Farming.hasAttachedStem(world, brokenBlock, pos)) {
                if (handleSkillExperience(brokenBlock,
                        ConfigRegister.FARM_CONFIG.harvestMelonXp.get(StringHelpers.getBlockName(brokenBlock)),
                        farming)) {
                    return true;
                }
            }
        }
        if (ConfigRegister.FARM_CONFIG.harvestStalkXp.containsKey(StringHelpers.getBlockName(brokenBlock))) {
            if (handleSkillExperience(brokenBlock,
                    ConfigRegister.FARM_CONFIG.harvestStalkXp.get(StringHelpers.getBlockName(brokenBlock))
                            * Farming.getAttachedStalks(world, brokenBlock, state, pos),
                    farming)) {
                return true;
            }
        }
        return false;
    }

    private boolean manageMining(World world, BlockPos pos, BlockState state, Block brokenBlock) {
        if (ConfigRegister.MINING_CONFIG.miningBlockXP.containsKey(StringHelpers.getBlockName(brokenBlock))) {
            if (handleSkillExperience(brokenBlock,
                    ConfigRegister.MINING_CONFIG.miningBlockXP.get(StringHelpers.getBlockName(brokenBlock)), mining)) {
                return true;
            }
        }
        return false;
    }

    private boolean manageWoodcutting(World world, BlockPos pos, BlockState state, Block brokenBlock) {
        if (ConfigRegister.WOODCUTTING_CONFIG.woodcuttingBlockXP.containsKey(StringHelpers.getBlockName(brokenBlock))) {
            if (handleSkillExperience(brokenBlock,
                    ConfigRegister.WOODCUTTING_CONFIG.woodcuttingBlockXP.get(StringHelpers.getBlockName(brokenBlock)),
                    woodcutting)) {
                return true;
            }
        }
        return false;
    }

    private boolean handleSkillExperience(Block brokenBlock,
            int experienceToAward,
            Skill skillToAwardXP) {
        if (calculateBlockStreak(brokenBlock)) {
            return true;
        }
        skillToAwardXP.addExperience(experienceToAward);
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