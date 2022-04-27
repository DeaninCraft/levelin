package com.deanin.levelin.skills;

/**
 * The base class for all abilities. An ability is a skill that can be used to
 * modify the player character's attributes, skill, or trigger a unique event.
 */
public class Ability {
    /**
     * The name of the ability
     */
    private String name;

    /**
     * Description of the ability.
     */
    private String description;

    /**
     * The experience cost of this ability.
     */
    private int cost;

    /**
     * The skill this ability belongs to.
     */
    private Skill skill;

    /**
     * The requirements to use this ability.
     */
    private Requirement[] requirements;

    /**
     * The unlockables that unlock this ability.
     */
    private Unlockable[] unlockables;

    /**
     * This is set to true when the ability is unlocked.
     */
    private boolean isUnlocked;

    /**
     * The level of this ability, in the event we add multiple levels to improve
     * an ability over time.
     */
    private int level;

    /**
     * Checks if the ability has been unlocked.
     * 
     * @return True if the ability is unlocked.
     */
    public boolean IsUnlocked() {
        for (Unlockable unlockable : unlockables) {
            if (unlockable.IsUnlocked()) {
                return true;
            }
        }
        return false;
    }
}
