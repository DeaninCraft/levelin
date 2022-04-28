package com.deanin.levelin.skills;

import com.deanin.utils.MathHelpers;

/**
 * A skill is a collection of attributes that can be used to modify the
 * behavior of a character. A skill may include attributes, such as
 * strength, dexterity, intelligence, and so on. The skill also includes
 * a name and a description. The skill also includes a modifier, which
 * is a value that is added to the base value of the skill.
 * 
 * The skill also should include a list of requirements. A skill may
 * require a certain attribute to be present in the character's
 * attributes. The skill may also contain unlockable abilities.
 * 
 * These abilities can be unlocked through the talent tree system.
 * 
 * @author Dean
 * @version 1.0
 */
public abstract class Skill {
    /**
     * The level of the skill. This value is clamped between 0 and the level
     * cap.
     */
    private int level;
    private int currentExperience = 0;
    private int experienceToNextLevel = 0;
    private int totalExperience = 0;
    private int minLevel = 1;

    /**
     * The name of the skill. This is displayed in the GUI.
     */
    private String name;
    /**
     * The description of the skill. This is displayed in the GUI.
     */
    private String description;
    /**
     * The talent tree for this skill.
     */
    private TalentTree talentTree;

    /**
     * The maximum level of the skill.
     */
    public int getLevelCap() {
        return levelCap;
    }

    /**
     * Sets the maximum level of the skill.
     */
    public void setLevelCap(int levelCap) {
        this.levelCap = MathHelpers.clampInt(levelCap, 1, Integer.MAX_VALUE);
    }

    /**
     * The maximum level of this skill.
     */
    private int levelCap;

    public void setCurrentExperience(int currentExperience) {
        this.currentExperience = currentExperience;
    }

    public void setExperienceToNextLevel(int experienceToNextLevel) {
        this.experienceToNextLevel = experienceToNextLevel;
    }

    public void setTotalExperience(int totalExperience) {
        this.totalExperience = totalExperience;
    }

    public int getMinLevel() {
        return minLevel;
    }

    public void setMinLevel(int minLevel) {
        this.minLevel = minLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TalentTree getTalentTree() {
        return talentTree;
    }

    public void setTalentTree(TalentTree talentTree) {
        this.talentTree = talentTree;
    }

    /**
     * Constructor for a base skill.
     * 
     * @param level    The level of the skill.
     * @param levelCap The level cap of the skill.
     */
    public Skill(String name, String description, int level, int levelCap) {
        this.name = name;
        this.description = description;
        setLevel(level);
        setLevelCap(levelCap);
        calculateExperienceToNextLevel();
    }

    /**
     * Gets the level of the skill.
     * 
     * @return The level of the skill.
     */
    public int getLevel() {
        return level;
    }

    /**
     * Can be used to set the skill level to the desired level.
     * 
     * @param level The new level of the skill.
     */
    public void setLevel(int level) {
        this.level = MathHelpers.clampInt(level, 1, levelCap);
    }

    /**
     * Can be used to increase the skill level by one. This value is
     * clamped between 0 and the level cap of the skill.
     * 
     * @return The new skill level.
     */
    public int levelUp() {
        return MathHelpers.clampInt(level += 1, 0, levelCap);
    }

    /**
     * Can be used to decrease the skill level by one. This value is
     * clamped between 0 and the level cap of the skill.
     * 
     * @return The new skill level.
     */
    public int levelDown() {
        return MathHelpers.clampInt(level -= 1, minLevel, levelCap);
    }

    public int getCurrentExperience() {
        return currentExperience;
    }

    public int getExperienceToNextLevel() {
        return experienceToNextLevel;
    }

    public int getTotalExperience() {
        return totalExperience;
    }

    public void addExperience(int experienceToAdd) {
        currentExperience = MathHelpers.clampInt(currentExperience += experienceToAdd, 0, Integer.MAX_VALUE);
        totalExperience = MathHelpers.clampInt(totalExperience += experienceToAdd, 0, Integer.MAX_VALUE);
        while (currentExperience >= experienceToNextLevel) {
            level++;
            currentExperience -= experienceToNextLevel;
            calculateExperienceToNextLevel();
        }
        if (currentExperience < 0) {
            level--;
            calculateExperienceToNextLevel();
            currentExperience += experienceToNextLevel - 1;
        }
    }

    /**
     * f(x) = 2^(x/8)*(sqrt(x)+1)
     */
    public void calculateExperienceToNextLevel() {
        int base = 2;
        double exponent = level / 8.0;
        double square = Math.sqrt(level);

        double amountToNextLevel = Math.pow(base, exponent) * (square + 50);
        experienceToNextLevel = (int) amountToNextLevel;
    }

    public float getProgressToNextLevel() {
        return (float) currentExperience / (float) experienceToNextLevel;
    }
}
