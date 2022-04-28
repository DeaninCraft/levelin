package com.deanin.levelin.player;

import com.deanin.levelin.skills.Skill;

/**
 * The total skill level of your character. This would allow us
 * to restrict the total level of the character across multiple skills,
 * in order to encourage leveling multiple skills at once to level up.
 */
public class TotalLevel {
    private int level = 1;
    private int totalExperience = 0;
    private Skills skills;

    public TotalLevel(Skills skills) {
        this.skills = skills;
    }

    public int calculatedTotalLevel() {
        level = 0;
        for (Skill skill : skills.getSkills()) {
            level += skill.getLevel();
        }
        return level;
    }

    public int getTotalExperience() {
        return totalExperience;
    }

    public void setTotalExperience(int totalExperience) {
        this.totalExperience = totalExperience;
    }

    public Skills getSkills() {
        return skills;
    }

    public void setSkills(Skills skills) {
        this.skills = skills;
    }

    /**
     * Sums up the experience across all skills.
     */
    public void calculateTotalExperience() {
        totalExperience = 0;
        for (Skill skill : skills.getSkills()) {
            totalExperience += skill.getCurrentExperience();
        }
    }
}
