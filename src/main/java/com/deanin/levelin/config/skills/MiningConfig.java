package com.deanin.levelin.config.skills;

import blue.endless.jankson.Comment;
import com.deanin.levelin.Levelin;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = Levelin.MOD_ID + "_mining")
public class MiningConfig implements ConfigData {
    @ConfigEntry.Category("skill_settings")
    @Comment("Maximum percent chance that extra materials will drop with mining.")
    public int maxFortuneChance = 100;
}
