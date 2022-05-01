package com.deanin.levelin.mixin;

import com.deanin.levelin.Levelin;
import com.deanin.levelin.Manager;
import com.deanin.levelin.config.ConfigRegister;
import com.deanin.utils.StringHelpers;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.world.BlockView;

@Mixin(AbstractBlock.class)
public abstract class MiningMixin {

    /**
     * The higher the mining speed modifier, the faster the player breaks the block.
     * Current function is f(level) = log10( [level / 4] + 0.25 ) - log10(0.25)
     */
    @Inject(at = @At("RETURN"),
            method="calcBlockBreakingDelta", cancellable = true)
    private void injected(BlockState state, PlayerEntity player, BlockView world, BlockPos pos, CallbackInfoReturnable<Float> cir) {
        float breakingSpeed = 1.0f;
        Block block = state.getBlock();
        String blockName = StringHelpers.getBlockName(block);
        if (ConfigRegister.MINING_CONFIG.miningBlockXP.containsKey(blockName)) {
            breakingSpeed = Manager.player.attributes.getMiningSpeed().calculatedBreakingSpeed(blockName);
        }
        if (ConfigRegister.WOODCUTTING_CONFIG.woodcuttingBlockXP.containsKey(blockName)) {
            breakingSpeed = Manager.player.attributes.getWoodcuttingSpeed().calculatedBreakingSpeed(blockName);
        }

        cir.setReturnValue(cir.getReturnValueF() * breakingSpeed);
    }
}
