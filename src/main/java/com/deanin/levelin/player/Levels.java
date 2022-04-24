package com.deanin.levelin.player;

import com.deanin.levelin.enums.ExperienceSystem;
import com.deanin.utils.MathHelpers;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;

public class Levels {

    public int getCurrentExperience() {
        return currentExperience;
    }

    public int getExperienceToNextLevel() {
        return experienceToNextLevel;
    }

    public int getLevel() {
        return level;
    }

    private int currentExperience = 0;
    private int experienceToNextLevel = 0;

    public int getTotalExperience() {
        return totalExperience;
    }

    private int totalExperience = 0;
    private int level = 1;

    public int getLevelCap() {
        return levelCap;
    }

    private int minLevel = 1;
    private int levelCap = 100;
    /**
     * Sets the experience system to toggle between vanilla or the custom Levelin system.
     */
    public static ExperienceSystem experienceSystem;

    public Levels(ExperienceSystem _experienceSystem) {
        experienceSystem = _experienceSystem;
        calculateExperienceToNextLevel();
    }

    public void addExperience(int experienceToAdd) {
        currentExperience += experienceToAdd;
        totalExperience += experienceToAdd;
        while (currentExperience >= experienceToNextLevel) {
            level++;
            currentExperience -= experienceToNextLevel;
            calculateExperienceToNextLevel();
        }
    }

    /**
     * f(x) = 2^(x/8)*(sqrt(x)+1)
     */
    private void calculateExperienceToNextLevel() {
        int base = 2;
        double exponent = level / 8.0;
        double square = Math.sqrt(level) + 1;

        double amountToNextLevel = Math.pow(base, exponent) * square;
        experienceToNextLevel = (int)amountToNextLevel;
    }

    /**
     * Calculate speed based on enchanting level
     */
//    public float calculatedMiningSpeed() {
//        PlayerEntity player = MinecraftClient.getInstance().player;
//        float levelCurve = (float)(Math.log10((player.experienceLevel / 4.0D) + 0.25D) - Math.log10(0.25D));
//        float clampedLevelCurve = MathHelpers.clamp(levelCurve, 0.25f, 3.0f);
//
//        return clampedLevelCurve;
//    }
    public float calculatedMiningSpeed() {
        float levelCurve = (float)(Math.log10((level / 4.0D) + 0.25D) - Math.log10(0.25D));
        float clampedLevelCurve = MathHelpers.clamp(levelCurve, 0.25f, 3.0f);

        return clampedLevelCurve;
    }
    public void levelUp() {
        level += 1;
    }
    public void levelDown() {
        level = (int)MathHelpers.clamp(level - 1, minLevel, levelCap);
    }
}
