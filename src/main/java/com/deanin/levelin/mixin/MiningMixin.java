package com.deanin.levelin.mixin;

import com.deanin.levelin.Levelin;
import com.deanin.levelin.Manager;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
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
        Levelin.LOGGER.warn("ENTERING CALC!");
        float breakingSpeed = 1.0f;

        if (state.isIn(BlockTags.PICKAXE_MINEABLE)) {
        breakingSpeed = Manager.player.attributes.getMiningSpeed().calculatedMiningSpeed();
        }
        if (state.isIn(BlockTags.AXE_MINEABLE)) {
            breakingSpeed = Manager.player.attributes.getWoodcuttingSpeed().calculatedWoodcuttingSpeed();
        }

        cir.setReturnValue(cir.getReturnValueF() * breakingSpeed);
    }
}
