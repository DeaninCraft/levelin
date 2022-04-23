package com.deanin.levelin.mixin;

import com.deanin.levelin.events.BlockBreakEvents;
import net.minecraft.block.AbstractBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractBlock.class)
public class MiningMixin {

    private int miningSpeedModifier = 10;

    /**
     * The higher the mining speed modifier, the faster the player breaks the block.
     * @param cir
     */
    @Inject(at = @At("RETURN"), method="calcBlockBreakingDelta()F", cancellable = true)
    private void injected(CallbackInfoReturnable<Float> cir) {
        cir.setReturnValue(cir.getReturnValueF() * miningSpeedModifier);
    }
}
