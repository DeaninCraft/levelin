package com.deanin.levelin.skills;

import com.mojang.datafixers.types.templates.Tag;

/**
 * The base class for all attributes. An attribute is a value that can be
 * modified by a skill, and affects the way the player character behaves.
 * 
 * @author Dean DeHart
 */
public class Attribute {
    /**
     * The name of the attribute.
     */
    private String name;
    /**
     * The value of the attribute.
     */
    private int value;
    /**
     * The maximum value of the attribute.
     */
    private int maxValue;
    /**
     * The minimum value of the attribute.
     */
    private int minValue;
    /**
     * The tools that this attribute modifies. If the tool is impacted by the
     * attribute,
     * then the tool will calculate the value based on its own internal modifier and
     * the
     * attribute's value.
     */
    private Tag[] affectedTools;
    /**
     * The weapons that this attribute modifies. If the weapon is impacted by the
     * attribute,
     * then the weapon will calculate the value based on its own internal modifier
     * and the
     * attribute's value.
     */
    private Tag[] affectedWeapons;

}
