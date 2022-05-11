package com.deanin.levelin;

import com.deanin.levelin.config.ConfigRegister;
import com.deanin.levelin.config.LevelinConfig;
import com.deanin.levelin.player.PlayerCharacter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {

    @Test
    void createManager() {
        ConfigRegister.register();
        Manager.createManager();
        assertNotNull(Manager.player);
        assertNotNull(Manager.config);
    }
}