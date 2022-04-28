package com.deanin.levelin.talents;

import com.deanin.levelin.abilities.Ability;
import com.deanin.levelin.attributes.Attribute;
import com.deanin.levelin.modifiers.Modifier;
import com.deanin.levelin.requirements.Requirement;
import com.deanin.levelin.skills.Skill;
import com.deanin.levelin.unlockables.Unlockable;

/**
 * A talent is a skill that can be purchased with experience
 * to increase a character's attribute, ability, or add a modifier.
 * 
 * Talents rely on a set of requirements to be purchased.
 * 
 * The requirements are represented by a list of {@link Requirement}s.
 * The unlocks include a list of {@link Ability}s, {@link Attribute}s,
 * {@link Unlockable}s and {@link Modifier}s.
 * 
 * @author Dean DeHart
 */
public class Talent {
    /**
     * The name of the talent.
     */
    private String name;
    /**
     * The description of the talent.
     */
    private String description;
    /**
     * The skill that this talent belongs to.
     */
    private Skill skill;
    /**
     * The category of this talent. I don't know why I want this to be a thing,
     * but I'm going to leave it for now. My brain thinks maybe for flavor text?
     */
    private String category;
    /**
     * The experience cost of this talent.
     */
    private String cost;
    /**
     * The attribute that this talent modifies.
     */
    private Attribute attribute;
    /**
     * The ability that this talent unlocks.
     */
    private Ability ability;
    /**
     * The attribute modifier that this talent adds.
     */
    private Modifier modifier;
    /**
     * The unlockable that this talent unlocks. An unlockable could be
     * access to an area, a new tier of equipment or weapon, or a new block.
     */
    private Unlockable unlockable;
    /**
     * The list of requirements that must be met to unlock this talent.
     */
    private Requirement[] requirements;

    /**
     * The list of requirements that must be met to unlock this talent.
     * 
     * @return The list of requirements that must be met to unlock this talent.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the talent.
     * 
     * @param name The name of the talent.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * The description of the talent.
     * 
     * @return The description of the talent.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the talent.
     * 
     * @param description The description of this talent.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * The skill that this talent belongs to.
     * 
     * @return The skill that this talent belongs to.
     */
    public Skill getSkill() {
        return skill;
    }

    /**
     * Sets the skill that this talent belongs to.
     * 
     * @param skill The skill that this talent belongs to.
     */
    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    /**
     * The category of this talent. I don't know why I want this to be a thing,
     * but I'm going to leave it for now. My brain thinks maybe for flavor text?
     * 
     * @return The category of this talent.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the category of this talent.
     * 
     * @param category The category of this talent.
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * The experience cost of this talent.
     * 
     * @return The experience cost of this talent.
     */
    public String getCost() {
        return cost;
    }

    /**
     * Sets the experience cost of this talent.
     * 
     * @param cost The experience cost of this talent.
     */
    public void setCost(String cost) {
        this.cost = cost;
    }

    /**
     * The attribute that this talent modifies.
     * 
     * @return The attribute that this talent modifies.
     */
    public Attribute getAttribute() {
        return attribute;
    }

    /**
     * Sets the attribute that this talent modifies.
     * 
     * @param attribute The attribute that this talent modifies.
     */
    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    /**
     * The ability that this talent unlocks.
     * 
     * @return The ability that this talent unlocks.
     */
    public Ability getAbility() {
        return ability;
    }

    /**
     * Sets the ability that this talent unlocks.
     * 
     * @param ability The ability that this talent unlocks.
     */
    public void setAbility(Ability ability) {
        this.ability = ability;
    }

    /**
     * The attribute modifier that this talent adds.
     * 
     * @return The attribute modifier that this talent adds.
     */
    public Modifier getModifier() {
        return modifier;
    }

    /**
     * Sets the attribute modifier that this talent adds.
     * 
     * @param modifier The attribute modifier that this talent adds.
     */
    public void setModifier(Modifier modifier) {
        this.modifier = modifier;
    }

    /**
     * The unlockable that this talent unlocks. An unlockable could be
     * access to an area, a new tier of equipment or weapon, or a new block.
     */
    public Unlockable getUnlockable() {
        return unlockable;
    }

    /**
     * Sets the unlockable that this talent unlocks. An unlockable could be
     * access to an area, a new tier of equipment or weapon, or a new block.
     * 
     * @param unlockable The unlockable that this talent unlocks.
     */
    public void setUnlockable(Unlockable unlockable) {
        this.unlockable = unlockable;
    }

    /**
     * The list of requirements that must be met to unlock this talent.
     * 
     * @return The list of requirements that must be met to unlock this talent.
     */
    public Requirement[] getRequirements() {
        return requirements;
    }

    /**
     * Sets the list of requirements that must be met to unlock this talent.
     * 
     * @param requirements The list of requirements that must be met to unlock
     */
    public void setRequirements(Requirement[] requirements) {
        this.requirements = requirements;
    }

    /**
     * Checks whether this skill can be unlocked. This is determined by
     * checking the requirements. If all requirements are met, then this
     * talent can be unlocked. If not, then this talent cannot be unlocked.
     */
    public boolean getRequirementsMet() {
        for (Requirement requirement : requirements) {
            if (!requirement.isMet()) {
                return false;
            }
        }
        return true;
    }
}
