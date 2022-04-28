package com.deanin.levelin;

import com.deanin.levelin.gui.ExampleGui;
import com.deanin.levelin.gui.ExampleScreen;
import com.deanin.levelin.player.PlayerCharacter;
import com.deanin.levelin.skills.mining.Mining;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class LevelinClient implements ClientModInitializer {

    // The KeyBinding declaration and registration are commonly executed here
    // statically
    private static KeyBinding levelUpButton;
    private static KeyBinding levelDownButton;

    private static KeyBinding characterInfoButton;


    /**
     * These keybindings are for proof of concept only.
     * THEY DO NOT AFFECT THE SERVER'S PLAYER LEVELS.
     */
    @Override
    public void onInitializeClient() {
        // Event registration will be executed inside this method
        levelUpButton = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.levelin.levelup", // The translation key of the keybinding's name
                InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
                GLFW.GLFW_KEY_PERIOD, // The keycode of the key
                "category.levelin.levels" // The translation key of the keybinding's category.
        ));
        levelDownButton = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.levelin.leveldown", // The translation key of the keybinding's name
                InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
                GLFW.GLFW_KEY_COMMA, // The keycode of the key
                "category.levelin.levels" // The translation key of the keybinding's category.
        ));
        characterInfoButton = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.levelin.characterInfo", // The translation key of the keybinding's name
                InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
                GLFW.GLFW_KEY_C, // The keycode of the key
                "category.levelin.levels" // The translation key of the keybinding's category.
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            Mining mining = Manager.player.skills.getMining();
            if (levelUpButton.wasPressed()) {
                mining.addExperience(1);
            }
            if (levelDownButton.wasPressed()) {
                mining.addExperience(-1);
            }
            if (characterInfoButton.wasPressed()) {
                MinecraftClient.getInstance().setScreen(new ExampleScreen(new ExampleGui()));
            }
        });
    }

}
