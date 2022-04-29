package com.deanin.levelin;

import com.deanin.levelin.gui.CharacterInfoPage;
import com.deanin.levelin.gui.LevelinScreen;
import com.deanin.levelin.skills.mining.Mining;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class LevelinClient implements ClientModInitializer {

    /**
     * These keybindings are for proof of concept only.
     * THEY DO NOT AFFECT THE SERVER'S PLAYER LEVELS.
     */
    @Override
    public void onInitializeClient() {
        KeyChain.init();



    }
}
