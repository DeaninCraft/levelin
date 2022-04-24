package com.deanin.levelin.mixin;

import com.deanin.levelin.Levelin;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class ExperienceBarMixin {

    @Inject(method = "render",
            at = @At(value = "INVOKE",
                    target = "Lcom/mojang/blaze3d/systems/RenderSystem;setShaderColor(FFFF)V"),
            slice =
            @Slice(from =
            @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/hud/PlayerListHud;render(Lnet/minecraft/client/util/math/MatrixStack;ILnet/minecraft/scoreboard/Scoreboard;Lnet/minecraft/scoreboard/ScoreboardObjective;)V")))
    public void render(MatrixStack matrixStack, float tickDelta, CallbackInfo callbackInfo) {
//        HudRenderCallback.EVENT.register((e, T) -> {
//            TextRenderer renderer = MinecraftClient.getInstance().textRenderer;
//            renderer.draw(e,"Test", 1, 1, 0xffffff);
//            renderer.draw(matrixStack,"case", 2, 2, 0xffffff);
//        });
        TextRenderer renderer = MinecraftClient.getInstance().textRenderer;

        String levelText = "Level: " + Levelin.levels.getLevel();
        String totalExpText = "Total Exp: " + Levelin.levels.getTotalExperience();
        String Experience = "To Next Level: " + Levelin.levels.getCurrentExperience() +
                "/" +
                Levelin.levels.getExperienceToNextLevel();
        String blockBreakingSpeedText = "Level: " + Levelin.levels.calculatedMiningSpeed();

        renderer.draw(matrixStack,levelText, 25, 25, 0xffffff);
        renderer.draw(matrixStack,totalExpText, 25, 50, 0xffffff);
        renderer.draw(matrixStack,Experience, 25, 75, 0xffffff);
        renderer.draw(matrixStack,blockBreakingSpeedText, 25, 100, 0xffffff);

        HudRenderCallback.EVENT.invoker().onHudRender(matrixStack, tickDelta);
    }
}