package com.deanin.levelin.config.skills;

import blue.endless.jankson.Comment;
import com.deanin.levelin.Levelin;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

import java.util.Map;

@Config(name = Levelin.MOD_ID + "_mining")
public class MiningConfig implements ConfigData {
    @ConfigEntry.Category("skill_settings")
    @Comment("Maximum percent chance that extra materials will drop with mining.")
    public int maxFortuneChance = 100;

    @ConfigEntry.Category("skill_settings")
    @Comment("List of blocks to go toward mining skill and their xp value.")
    public Map<String, Integer> miningBlockXP = Map.ofEntries(
            Map.entry("minecraft:grass", 1),
            Map.entry("minecraft:dirt", 1),
            Map.entry("minecraft:sand", 2),
            Map.entry("minecraft:gravel", 2),

            Map.entry("minecraft:stone", 3),
            Map.entry("minecraft:cobblestone", 3),
            Map.entry("minecraft:granite", 4),
            Map.entry("minecraft:andesite", 5),
            Map.entry("minecraft:diorite", 6),
            Map.entry("minecraft:iron_ore", 10),
            Map.entry("minecraft:redstone_ore", 15),
            Map.entry("minecraft:gold_ore", 20),
            Map.entry("minecraft:diamond_ore", 25),

            Map.entry("minecraft:deepslate", 26),
            Map.entry("minecraft:tuff", 27),
            Map.entry("minecraft:deepslate_iron_ore", 28),
            Map.entry("minecraft:deepslate_redstone_ore", 29),
            Map.entry("minecraft:deepslate_gold_ore", 30),
            Map.entry("minecraft:emerald_ore", 30),
            Map.entry("minecraft:deepslate_diamond_ore", 33),

            Map.entry("minecraft:ancient_debris", 66));

    @ConfigEntry.Category("skill_settings")
    @Comment("Level requirements to mine each block")
    public Map<String, Integer> miningBlockLevelRequirements = Map.ofEntries(
            Map.entry("minecraft:grass", 1),
            Map.entry("minecraft:dirt", 1),
            Map.entry("minecraft:sand", 2),
            Map.entry("minecraft:gravel", 2),

            Map.entry("minecraft:stone", 3),
            Map.entry("minecraft:cobblestone", 3),
            Map.entry("minecraft:granite", 4),
            Map.entry("minecraft:andesite", 5),
            Map.entry("minecraft:diorite", 6),
            Map.entry("minecraft:iron_ore", 10),

            Map.entry("minecraft:redstone_ore", 15),
            Map.entry("minecraft:gold_ore", 20),
            Map.entry("minecraft:diamond_ore", 25),

            Map.entry("minecraft:deepslate", 26),
            Map.entry("minecraft:tuff", 27),

            Map.entry("minecraft:deepslate_iron_ore", 28),
            Map.entry("minecraft:deepslate_redstone_ore", 29),
            Map.entry("minecraft:deepslate_gold_ore", 30),
            Map.entry("minecraft:deepslate_diamond_ore", 33));
}
