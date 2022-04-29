package com.deanin.levelin.config.skills;

import blue.endless.jankson.Comment;
import com.deanin.levelin.Levelin;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

import java.util.Map;

@Config(name = Levelin.MOD_ID + "_woodcutting")
public class WoodcuttingConfig implements ConfigData {
    @ConfigEntry.Category("skill_settings")
    @Comment("Maximum percent chance that extra materials will drop with woodcutting.")
    public int maxFortuneChance = 100;

    @ConfigEntry.Category("skill_settings")
    @Comment("List of blocks to go toward woodcutting skill and their xp value.")
    public Map<String, Integer> woodcuttingBlockXP = Map.ofEntries(
            Map.entry("minecraft:oak_log", 1),
            Map.entry("minecraft:spruce_log", 15),
            Map.entry("minecraft:acacia_log", 30),
            Map.entry("minecraft:dark_oak_log", 45),

            Map.entry("minecraft:jungle_log", 60),
            Map.entry("minecraft:birch_log", 75));

    @ConfigEntry.Category("skill_settings")
    @Comment("Level requirements to chop each block")
    public Map<String, Integer> woodcuttingBlockLevelRequirements = Map.ofEntries(
            Map.entry("minecraft:oak_log", 1),
            Map.entry("minecraft:spruce_log", 15),
            Map.entry("minecraft:acacia_log", 30),
            Map.entry("minecraft:dark_oak_log", 45),

            Map.entry("minecraft:jungle_log", 60),
            Map.entry("minecraft:birch_log", 75));
}
