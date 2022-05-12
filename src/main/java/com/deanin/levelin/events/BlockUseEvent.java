package com.deanin.levelin.events;

import com.deanin.levelin.Levelin;
import com.deanin.levelin.config.ConfigRegister;
import com.deanin.levelin.player.Skills;
import com.deanin.levelin.skills.Skill;
import com.deanin.levelin.skills.farming.Farming;
import com.deanin.utils.StringHelpers;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

public class BlockUseEvent {

    private Farming farming;

    public BlockUseEvent(Skills skills) {

        this.farming = skills.getFarming();

        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            if (world.isClient) {
                return ActionResult.FAIL;
            }
            BlockState state = world.getBlockState(hitResult.getBlockPos());

            Levelin.LOGGER.info(state.toString());
            experienceManager(world, state);
            return ActionResult.PASS;
        });
    }


    public void experienceManager(World world, BlockState state) {
        Block usedBlock = state.getBlock();
        if (manageFarming(world, usedBlock, state)) {
            return;
        }
    }

    private boolean manageFarming(World world, Block usedBlock, BlockState state) {
        if (ConfigRegister.FARM_CONFIG.harvestCropXp.containsKey(StringHelpers.getBlockName(usedBlock))) {
            if (handleSkillExperience(ConfigRegister.FARM_CONFIG.harvestCropXp.get(StringHelpers.getBlockName(usedBlock)),
                    farming)) {
                return true;
            }
        }

        return false;
    }

    private boolean handleSkillExperience(int experienceToAward,
                                          Skill skillToAwardXP) {
        skillToAwardXP.addExperience(experienceToAward);
        return false;
    }
}
