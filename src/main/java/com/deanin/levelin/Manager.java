package com.deanin.levelin;

import com.deanin.levelin.player.PlayerCharacter;

/**
 * This class
 */
public class Manager {
    public static PlayerCharacter player;

    /**
     * Create singleton Manager
     */
    public static void createManager() {
        if (Manager.player == null) {
            Manager.player = new PlayerCharacter();
        }
    }
}
