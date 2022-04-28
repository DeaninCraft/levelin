package com.deanin.levelin.skills;

import com.deanin.levelin.abilities.Ability;
import com.deanin.levelin.attributes.Attribute;
import com.deanin.levelin.modifiers.Modifier;
import com.deanin.levelin.requirements.Requirement;
import com.deanin.levelin.talents.Talent;
import com.deanin.levelin.unlockables.Unlockable;

/**
 * TalentTree is a class that represents a talent tree. It contains a list of
 * abilities, attributes, modifiers, and requirements. Each talent is purchased
 * after a list of requirements are met. The talents are purchased in order of
 * the tree, for a price of experience points.
 * 
 * @author Dean DeHart
 */
public class TalentTree {
    /**
     * The name, or specialization, of the talent tree.
     */
    private String name;
    /**
     * The description of the talent tree.
     */
    private String description;
    /**
     * The skill that this talent tree belongs to.
     */
    private Skill skill;
    /**
     * The list of talents that belong to this talent tree.
     */
    private Talent[] talents;

    /**
     * The list of talents that can currently be purchased. This list is
     * automatically updated when the requirements are met.
     */
    private Talent[] purchasableTalents;
    /**
     * The list of requirements that must be met to unlock this talent tree.
     */
    private Requirement[] requirements;
    /**
     * The list of abilities that are unlocked by this talent tree.
     */
    private Ability[] abilities;
    /**
     * The list of attributes that are unlocked by this talent tree.
     */
    private Attribute[] attributes;
    /**
     * The list of modifiers that are unlocked by this talent tree.
     */
    private Modifier[] modifiers;
    /**
     * The list of unlockables that are unlocked by this talent tree.
     * An unlockable could be access to an area, a new tier of equipment
     * or weapon, or a new block.
     */
    private Unlockable[] unlockables;

    /**
     * The name of the talent tree.
     * 
     * @return The name of the talent tree.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the talent tree.
     * 
     * @param name The name of the talent tree.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * The description of the talent tree.
     * 
     * @return The description of the talent tree.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the talent tree.
     * 
     * @param description The description of the talent tree.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * The skill that this talent tree belongs to.
     * 
     * @return The skill that this talent tree belongs to.
     */
    public Skill getSkill() {
        return skill;
    }

    /**
     * Sets the skill that this talent tree belongs to.
     * 
     * @param skill The skill that this talent tree belongs to.
     */
    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    /**
     * The list of talents that belong to this talent tree.
     * 
     * @return The list of talents that belong to this talent tree.
     */
    public Talent[] getTalents() {
        return talents;
    }

    /**
     * Sets the list of talents that belong to this talent tree.
     * 
     * @param talents The list of talents that belong to this talent tree.
     */
    public void setTalents(Talent[] talents) {
        this.talents = talents;
    }

    /**
     * The list of talents that can currently be purchased. This list is
     * automatically updated when the requirements are met.
     * 
     * @return The list of talents that can currently be purchased.
     */
    public Talent[] getPurchasableTalents() {
        return purchasableTalents;
    }

    /**
     * Sets the list of talents that can currently be purchased. This list is
     * automatically updated when the requirements are met.
     */
    public void setPurchasableTalents(Talent[] purchasableTalents) {
        this.purchasableTalents = purchasableTalents;
    }

    /**
     * The list of requirements that must be met to unlock this talent tree.
     * 
     * @return The list of requirements that must be met to unlock this talent tree.
     */
    public Requirement[] getRequirements() {
        return requirements;
    }

    /**
     * Sets the list of requirements that must be met to unlock this talent tree.
     * 
     * @param requirements The list of requirements that must be met to unlock this
     *                     talent tree.
     */
    public void setRequirements(Requirement[] requirements) {
        this.requirements = requirements;
    }

    /**
     * The list of abilities that are unlocked by this talent tree.
     * 
     * @return The list of abilities that are unlocked by this talent tree.
     */
    public Ability[] getAbilities() {
        return abilities;
    }

    /**
     * Sets the list of abilities that are unlocked by this talent tree.
     * 
     * @param abilities The list of abilities that are unlocked by this talent tree.
     */
    public void setAbilities(Ability[] abilities) {
        this.abilities = abilities;
    }

    /**
     * The list of attributes that are unlocked by this talent tree.
     * 
     * @return The list of attributes that are unlocked by this talent tree.
     */
    public Attribute[] getAttributes() {
        return attributes;
    }

    /**
     * Sets the list of attributes that are unlocked by this talent tree.
     * 
     * @param attributes The list of attributes that are unlocked by this talent
     *                   tree.
     */
    public void setAttributes(Attribute[] attributes) {
        this.attributes = attributes;
    }

    /**
     * The list of modifiers that are unlocked by this talent tree.
     * 
     * @return The list of modifiers that are unlocked by this talent tree.
     */
    public Modifier[] getModifiers() {
        return modifiers;
    }

    /**
     * Sets the list of modifiers that are unlocked by this talent tree.
     * 
     * @param modifiers The list of modifiers that are unlocked by this talent tree.
     */
    public void setModifiers(Modifier[] modifiers) {
        this.modifiers = modifiers;
    }

    /**
     * The list of unlockables that are unlocked by this talent tree.
     * An unlockable could be access to an area, a new tier of equipment
     * or weapon, or a new block.
     * 
     * @return The list of unlockables that are unlocked by this talent tree.
     */
    public Unlockable[] getUnlockables() {
        return unlockables;
    }

    /**
     * Sets the list of unlockables that are unlocked by this talent tree.
     * An unlockable could be access to an area, a new tier of equipment
     * or weapon, or a new block.
     * 
     * @param unlockables The list of unlockables that are unlocked by this talent
     *                    tree.
     */
    public void setUnlockables(Unlockable[] unlockables) {
        this.unlockables = unlockables;
    }

    /**
     * Calculates which talents can be purchased. This is automatically updated
     * when the requirements are met.
     */
    public void calculatePurchaseableTalents() {
        for (int i = 0; i < talents.length; i++) {
            Talent talent = talents[i];
            if (talent.getRequirementsMet()) {
                purchasableTalents[i] = talent;
            }
        }
    }
}
