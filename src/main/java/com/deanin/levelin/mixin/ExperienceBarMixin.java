package com.deanin.levelin.mixin;

import com.deanin.levelin.Levelin;
import com.deanin.levelin.attributes.mining.MiningSpeed;
import com.deanin.levelin.skills.mining.Mining;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.Window;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.minecraft.client.gui.DrawableHelper.drawTexture;

@Mixin(InGameHud.class)
public class ExperienceBarMixin  extends DrawableHelper {
    private static final Identifier EXP_BAR_TEXTURE = new Identifier("levelin", "textures/gui/expbar.png");
    private static final Identifier EXP_BAR_FILLED_TEXTURE = new Identifier("levelin", "textures/gui/expbar_filled.png");
    private static final Identifier EXP_BAR_DECORATIONS_TEXTURE = new Identifier("levelin", "textures/gui/expbar_decorations.png");
    private static final int EXPERIENCE_BAR_TEXTURE_WIDTH = 420;
    private static final int EXPERIENCE_BAR_TEXTURE_HEIGHT = 69;
    private static final int EXPERIENCE_BAR_FILLED_TEXTURE_WIDTH_OFFSET = 6;
    private static final int EXPERIENCE_BAR_FILLED_TEXTURE_HEIGHT_OFFSET = 6;
    private double guiScale;
    private int scaledExperienceBarWidth;
    private int scaledExperienceBarHeight;
    private Mining mining;
    private MiningSpeed miningSpeed;

//    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;" +
//            "setShaderColor(FFFF)V"),
//            slice =
//            @Slice(from =
//            @At(value = "INVOKE",
//                    target = "Lnet/minecraft/client/gui/hud/PlayerListHud;" +
//                            "render(Lnet/minecraft/client/util/math/MatrixStack;I" +
//                            "Lnet/minecraft/scoreboard/Scoreboard;" +
//                            "Lnet/minecraft/scoreboard/ScoreboardObjective;)V")))
    @Inject(method="render", at = @At("TAIL"), cancellable = true)
    public void render(MatrixStack matrixStack, float tickDelta, CallbackInfo callbackInfo) {
        mining = Levelin.character.skills.getMining();
        miningSpeed = Levelin.character.attributes.getMiningSpeed();
        TextRenderer renderer = MinecraftClient.getInstance().textRenderer;
        Window window = MinecraftClient.getInstance().getWindow();
        guiScale = 1.0 / MinecraftClient.getInstance().options.guiScale;
        scaledExperienceBarWidth = (int)(EXPERIENCE_BAR_TEXTURE_WIDTH * guiScale);
        scaledExperienceBarHeight = (int)(EXPERIENCE_BAR_TEXTURE_HEIGHT * guiScale);

        String levelText = "Level: " + mining.getLevel();
        String totalExpText = "Total Exp: " + mining.getTotalExperience();
        String Experience = "To Next Level: " + mining.getCurrentExperience() +
                "/" +
                mining.getExperienceToNextLevel();
        String blockBreakingSpeedText = "Level: " + miningSpeed.calculatedMiningSpeed();

        renderer.draw(matrixStack,levelText, 25, 25, 0xffffff);
        renderer.draw(matrixStack,totalExpText, 25, 50, 0xffffff);
        renderer.draw(matrixStack,Experience, 25, 75, 0xffffff);
        renderer.draw(matrixStack,blockBreakingSpeedText, 25, 100, 0xffffff);


        drawExperienceBar(matrixStack, window);
        drawExperienceBarProgress(matrixStack, window);
        drawExperienceBarDecorations(matrixStack, window);

        drawExperienceBarText(matrixStack, window, renderer);

        HudRenderCallback.EVENT.invoker().onHudRender(matrixStack, tickDelta);
    }

    public MatrixStack drawExperienceBar(MatrixStack matrixStack, Window window) {

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, EXP_BAR_TEXTURE);

        int windowWidth = window.getScaledWidth();
        int windowHeight = window.getScaledHeight();

        int x = windowWidth - scaledExperienceBarWidth;
        int y = windowHeight - scaledExperienceBarHeight;

        drawTexture(matrixStack,
                x,
                y,
                scaledExperienceBarWidth,
                scaledExperienceBarHeight,
                0,
                0,
                scaledExperienceBarWidth,
                scaledExperienceBarHeight,
                scaledExperienceBarWidth,
                scaledExperienceBarHeight);
        return matrixStack;
    }
    public MatrixStack drawExperienceBarDecorations(MatrixStack matrixStack, Window window) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, EXP_BAR_DECORATIONS_TEXTURE);

        int windowWidth = window.getScaledWidth();
        int windowHeight = window.getScaledHeight();

        int x = windowWidth - scaledExperienceBarWidth;
        int y = windowHeight - scaledExperienceBarHeight;

        drawTexture(matrixStack,
                x,
                y,
                scaledExperienceBarWidth,
                scaledExperienceBarHeight,
                0,
                0,
                scaledExperienceBarWidth,
                scaledExperienceBarHeight,
                scaledExperienceBarWidth,
                scaledExperienceBarHeight);
        return matrixStack;
    }
    public MatrixStack drawExperienceBarProgress(MatrixStack matrixStack, Window window) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, EXP_BAR_FILLED_TEXTURE);

        float levelProgress = mining.getProgressToNextLevel();

        int windowWidth = window.getScaledWidth();
        int windowHeight = window.getScaledHeight();

        double widthOffset = (double)EXPERIENCE_BAR_FILLED_TEXTURE_WIDTH_OFFSET * guiScale;
        double heightOffset = (double)EXPERIENCE_BAR_FILLED_TEXTURE_HEIGHT_OFFSET * guiScale;

        double double_width = (scaledExperienceBarWidth - (EXPERIENCE_BAR_FILLED_TEXTURE_WIDTH_OFFSET)) * levelProgress;

        Levelin.LOGGER.info("LEVEL PROGRESS: " + levelProgress);

        double double_x = windowWidth - (scaledExperienceBarWidth - widthOffset);
        double double_y = windowHeight - (scaledExperienceBarHeight - heightOffset);

        int x = (int)double_x;
        int y = (int)double_y;
        int width = (int)double_width;
        int height = (int)(scaledExperienceBarHeight - (heightOffset * 2));

        // swapped region and texture width
        drawTexture(matrixStack,
                x,
                y,
                width,
                height,
                0,
                0,
                width,
                height,
                scaledExperienceBarWidth,
                height);


        return matrixStack;
    }

    public void drawExperienceBarText(MatrixStack matrixStack, Window window, TextRenderer renderer) {
        String experienceText = mining.getCurrentExperience() + " / " + mining.getExperienceToNextLevel();
        int width = scaledExperienceBarWidth / 2;
        int height = scaledExperienceBarHeight / 2;
        int windowWidth = window.getScaledWidth();
        int windowHeight = window.getScaledHeight();
        int x = windowWidth - width - (10 + 4 * calculateDigits(mining.getCurrentExperience()));
        int y = windowHeight - height;

        renderer.draw(matrixStack, experienceText, x, y, 0xffffff);
    }

    private int calculateDigits(int input) {
        int count = 0;
        while (input != 0) {
            input = input / 10;
            ++count;
        }
        if (count == 0) {
            return 1;
        }
        return count;
    }
}