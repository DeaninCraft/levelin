package com.deanin.levelin.events;

import com.deanin.levelin.Levelin;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockBreakEvents {
    public BlockBreakEvents() {
        PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, blockEntity) -> {
            experienceManager(world, player, pos, state, blockEntity);
        });
    }

    private void experienceManager(World world, PlayerEntity player, BlockPos pos, BlockState state, BlockEntity blockEntity) {
        if (state.getBlock().equals(Blocks.GRASS)) {

            state.getBlock().
            player.addExperienceLevels(1);
        }
    }


}
