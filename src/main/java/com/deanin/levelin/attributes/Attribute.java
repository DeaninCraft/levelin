package com.deanin.levelin.attributes;

import com.deanin.levelin.Manager;
import com.deanin.levelin.modifiers.Modifier;
import com.deanin.levelin.talents.Talent;
import com.mojang.datafixers.types.templates.Tag;

import java.util.ArrayList;

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
    private float value;
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

    public ArrayList<Talent> talents;

    public Attribute(String name) {
        this.name = name;
        talents = new ArrayList<Talent>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
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

    public float[] cumulativeAttributeModifiers() {
        float[] result = {0, 1.0f};
        for (Talent talent : talents) {
            Modifier.ModifierType modifierType = talent.getModifier().getModifierType();
            float modifierValue = talent.getModifier().getValue();
            if (modifierType == Modifier.ModifierType.add) {
                result[0] += modifierValue;
            }
            if (modifierType == Modifier.ModifierType.multiplier) {
                result[1] *= modifierValue;
            }
        }
        return result;
    }
}
