package com.deanin.levelin.config;

import blue.endless.jankson.Comment;
import com.deanin.levelin.Levelin;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = Levelin.MOD_ID)
public class LevelinConfig implements ConfigData {
    @ConfigEntry.Category("levelin_settings")
    @Comment("List of skills for the mod to use.")
    public String[] skillList = {
            "mining",
            "farming",
            "woodcutting"
    };


}
