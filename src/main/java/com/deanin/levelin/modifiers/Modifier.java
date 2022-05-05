package com.deanin.levelin.modifiers;

public class Modifier {
    public enum ModifierType {
        add,
        multiplier
    };

    public ModifierType getModifierType() {
        return modifierType;
    }

    public void setModifierType(ModifierType modifierType) {
        this.modifierType = modifierType;
    }

    private ModifierType modifierType;
    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    private float value = 1.0f;

    public Modifier(float value, ModifierType type) {
        this.value = value;
        this.modifierType = type;

    }

}
