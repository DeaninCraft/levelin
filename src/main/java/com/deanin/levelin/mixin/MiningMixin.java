package com.deanin.levelin.mixin;

import com.deanin.levelin.Levelin;
import com.deanin.levelin.player.Levels;
import com.deanin.utils.MathHelpers;
import net.minecraft.block.AbstractBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractBlock.class)
public class MiningMixin {
    /**
     * The higher the mining speed modifier, the faster the player breaks the block.
     * Current function is f(level) = log10( [level / 4] + 0.25 ) - log10(0.25)
     * @param cir
     */
    @Inject(at = @At("RETURN"), method="calcBlockBreakingDelta()F", cancellable = true)
    private void injected(CallbackInfoReturnable<Float> cir) {
        cir.setReturnValue(cir.getReturnValueF() * Levelin.levels.calculatedMiningSpeed());
    }
}
