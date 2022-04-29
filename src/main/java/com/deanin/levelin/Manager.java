package com.deanin.levelin;

import com.deanin.levelin.config.LevelinConfig;
import com.deanin.levelin.player.PlayerCharacter;
import me.shedaniel.autoconfig.AutoConfig;

/**
 * This class
 */
public class Manager {
    public static PlayerCharacter player;
    public static LevelinConfig config;

    /**
     * Create singleton Manager
     */
    public static void createManager() {
        if (Manager.player == null) {
            Manager.player = new PlayerCharacter();
        }
        if (Manager.config == null) {
            config = AutoConfig.getConfigHolder(LevelinConfig.class).getConfig();
        }
    }
}
