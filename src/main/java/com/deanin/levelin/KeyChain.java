package com.deanin.levelin;

import com.deanin.levelin.gui.CharacterInfoPage;
import com.deanin.levelin.gui.LevelinScreen;
import com.deanin.levelin.skills.mining.Mining;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class KeyChain {
    private static KeyBinding levelUpButton;
    private static KeyBinding levelDownButton;
    public static KeyBinding characterInfoButton;
    public static boolean characterInfoIsOpen;
    public static void init() {
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
        // Callback
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            Mining mining = Manager.player.skills.getMining();
            if (levelUpButton.wasPressed()) {
                mining.addExperience(1);
            }
            if (levelDownButton.wasPressed()) {
                mining.addExperience(-1);
            }
            if (characterInfoButton.wasPressed()) {
                if (!characterInfoIsOpen) {
                    client.setScreen(new LevelinScreen(new CharacterInfoPage(client)));
                }
                characterInfoIsOpen = true;
            } else if (characterInfoIsOpen) {
                characterInfoIsOpen = false;
            }
        });
    }
}
