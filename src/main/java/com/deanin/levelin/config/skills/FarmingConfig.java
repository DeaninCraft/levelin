package com.deanin.levelin.config.skills;


import blue.endless.jankson.Comment;
import com.deanin.levelin.Levelin;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

import java.util.Map;

@Config(name = Levelin.MOD_ID + "_farming")
public class FarmingConfig implements ConfigData {
    @ConfigEntry.Category("skill_settings")
    @Comment("Maximum percent chance that extra crops will drop with harvest.")
    public int maxExtraCropChance = 100;

    @ConfigEntry.Category("skill_settings")
    @Comment("Maximum percent chance that golden crops will drop with harvest.")
    public int maxGoldenCropChance = 75;

    @ConfigEntry.Category("skill_settings")
    @Comment("List of crops to go toward farming skill and their xp value.")
    public Map<String, Integer> harvestCropXp = Map.ofEntries(
            Map.entry("minecraft:potatoes", 2),
            Map.entry("minecraft:carrots", 2),
            Map.entry("minecraft:wheat", 2),
            Map.entry("minecraft:beetroots", 2),
            Map.entry("minecraft:nether_wart", 4),
            Map.entry("minecraft:cocoa", 3),
            Map.entry("minecraft:cactus", 1),
            Map.entry("minecraft:bamboo", 1),
            Map.entry("minecraft:melon", 3),
            Map.entry("minecraft:pumpkin", 3),
            Map.entry("minecraft:red_mushrooms", 1),
            Map.entry("minecraft:brown_mushrooms", 1),
            Map.entry("minecraft:sugar_cane", 1),
            Map.entry("minecraft:sweet_berry_bush", 2),
            Map.entry("minecraft:chorus_root", 3));
}
