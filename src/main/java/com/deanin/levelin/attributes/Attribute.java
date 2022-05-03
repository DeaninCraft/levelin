package com.deanin.levelin.attributes;

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

    public Attribute(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public Tag[] getAffectedTools() {
        return affectedTools;
    }

    public void setAffectedTools(Tag[] affectedTools) {
        this.affectedTools = affectedTools;
    }

    public Tag[] getAffectedWeapons() {
        return affectedWeapons;
    }

    public void setAffectedWeapons(Tag[] affectedWeapons) {
        this.affectedWeapons = affectedWeapons;
    }
    public float calculatedAttributeValue() {
        return 1.0f;
    }


}
