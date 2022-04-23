package com.deanin.levelin.events;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.TagKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockBreakEvents {
    private Block previousBrokenBlock;
    private int blockBreakStreak = 0;
    private int blockStreakLimit = 5;

    private int experienceAmount = 1;

    public BlockBreakEvents() {
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
        if (manageGrassExperience(world, pos, state, blockEntity, brokenBlock)) return;
        if (manageWoodCuttingExperiencce(world, pos, state, blockEntity, brokenBlock)) return;

        previousBrokenBlock = brokenBlock;
    }

    private boolean manageGrassExperience(World world, BlockPos pos, BlockState state, BlockEntity blockEntity, Block brokenBlock) {
        if (brokenBlock.equals(Blocks.GRASS)) {
            if (handleBlockExperience(world, pos, state, blockEntity, brokenBlock)) return true;
        }
        return false;
    }

    private boolean manageWoodCuttingExperiencce(World world, BlockPos pos, BlockState state, BlockEntity blockEntity, Block brokenBlock) {
        TagKey<Block> logs = BlockTags.LOGS;
        if (state.isIn(logs)){
            if (handleBlockExperience(world, pos, state, blockEntity, brokenBlock)) return true;
        }
        return false;
    }

    private boolean handleBlockExperience(World world, BlockPos pos, BlockState state, BlockEntity blockEntity, Block brokenBlock) {
        if (brokenBlock.equals(previousBrokenBlock)) {
            blockBreakStreak++;
            if (blockBreakStreak > blockStreakLimit) {
                return true;
            }
        } else {
            blockBreakStreak = 1;
        }
        brokenBlock.getDroppedStacks(
                state,
                (ServerWorld) world,
                pos,
                blockEntity
        );
        ExperienceOrbEntity experienceOrbEntity = new ExperienceOrbEntity(
                world,
                pos.getX(),
                pos.getY(),
                pos.getZ(),
                experienceAmount);
        world.spawnEntity(experienceOrbEntity);
        return false;
    }


}
