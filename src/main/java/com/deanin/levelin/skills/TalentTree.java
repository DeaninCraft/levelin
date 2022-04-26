package com.deanin.levelin.skills;

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

}
