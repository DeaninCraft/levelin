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
public class Skill {
    /**
     * The level of the skill. This value is clamped between 0 and and the level
     * cap.
     */
    private int level;
    /**
     * The name of the skill. This is displayed in the GUI.
     */
    private String name;
    /**
     * The description of the skill. This is displayed in the GUI.
     */
    private String description;

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
        this.levelCap = MathHelpers.clampInt(levelCap, 0, Integer.MAX_VALUE);
    }

    /**
     * The maximum level of this skill.
     */
    private int levelCap;

    /**
     * Constructor for a base skill.
     * 
     * @param level    The level of the skill.
     * @param levelCap The level cap of the skill.
     */
    public Skill(int level, int levelCap) {
        this.level = level;
        this.levelCap = levelCap;
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
        this.level = MathHelpers.clampInt(level, 0, levelCap);
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
        return MathHelpers.clampInt(level -= 1, 0, levelCap);
    }
}
