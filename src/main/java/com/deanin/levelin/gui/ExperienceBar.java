package com.deanin.levelin.gui;


import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.renderer.v1.Renderer;
import net.fabricmc.fabric.api.renderer.v1.RendererAccess;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.Window;
import net.minecraft.client.util.math.MatrixStack;

@Environment(value= EnvType.CLIENT)
public class ExperienceBar
        extends DrawableHelper  {
//
//    private final MinecraftClient minecraft;
//    private final TextRenderer fontRenderer;
//
//    public ExperienceBar() {
//        minecraft = MinecraftClient.getInstance();
//        Window window = minecraft.getWindow();
//        fontRenderer = minecraft.textRenderer;
//
//    }
//
//
//    public void renderExperienceBar(MatrixStack matrices, int x) {
//        Window mainWindow = MinecraftClient.getInstance().getWindow();
//
//        int scaledWidth = mainWindow.getScaledWidth();
//        int scaledHeight = mainWindow.getScaledHeight();
//
//        DrawableHelper.fill(matrices, 1, 1, scaledWidth, scaledHeight, 1);
//
//    }
}
