package com.deanin.levelin.config;

import com.deanin.levelin.config.skills.FarmingConfig;
import com.deanin.levelin.config.skills.MiningConfig;
import com.deanin.levelin.skills.mining.Mining;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;

import java.util.Arrays;

public class ConfigRegister {
    public static LevelinConfig MAIN_CONFIG = new LevelinConfig();
    public static FarmingConfig FARM_CONFIG = new FarmingConfig();
    public static MiningConfig MINE_CONFIG = new MiningConfig();

    public static void register() {
        AutoConfig.register(LevelinConfig.class, JanksonConfigSerializer::new);
        MAIN_CONFIG = AutoConfig.getConfigHolder(LevelinConfig.class).getConfig();

        if(Arrays.asList(MAIN_CONFIG.skillList).contains("farming")) {
            AutoConfig.register(FarmingConfig.class, JanksonConfigSerializer::new);
            FARM_CONFIG = AutoConfig.getConfigHolder(FarmingConfig.class).getConfig();
        }
        if(Arrays.asList(MAIN_CONFIG.skillList).contains("mining")) {
            AutoConfig.register(MiningConfig.class, JanksonConfigSerializer::new);
            MINE_CONFIG = AutoConfig.getConfigHolder(MiningConfig.class).getConfig();
        }
    }
}
