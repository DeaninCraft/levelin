package com.deanin.levelin.unlockables;

import com.deanin.levelin.abilities.Ability;
import com.deanin.levelin.armor.Armor;
import com.deanin.levelin.attributes.Attribute;
import com.deanin.levelin.modifiers.Modifier;
import com.deanin.levelin.requirements.Requirement;
import com.deanin.levelin.skills.Skill;
import com.deanin.levelin.tools.Tool;
import com.deanin.levelin.weapons.Weapon;

import java.util.ArrayList;

/**
 * An unlockable contains a list of abilities, attributes, modifiers, and
 * requirements. Each unlockable is unlocked after a list of requirements are
 * met. The unlockables are unlocked in order of the tree, for a price of
 * experience points.
 * 
 * @author Dean DeHart
 */
public class Unlockable {
    /**
     * The skills this unlockable belongs to.
     */
    private ArrayList<Skill> skills;
    /**
     * This sets whether the unlockable has been unlocked.
     */
    private boolean isUnlocked;
    /**
     * The tools that this unlockable unlocks.
     */
    private Tool[] tools;
    /**
     * The weapons this unlockable unlocks.
     */
    private Weapon[] weapons;
    /**
     * The armors this unlockable unlocks;
     */
    private Armor[] armors;
    /**
     * The abilities this unlockable unlocks.
     */
    private Ability[] abilities;
    /**
     * The attributes this unlockable adds to the character.
     */
    private Attribute[] attributes;
    /**
     * The requirements to unlock this unlockable.
     */
    private Requirement[] requirements;
    /**
     * The modifiers this unlockable adds to your character.
     */
    private Modifier[] modifiers;

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<Skill> skills) {
        this.skills = skills;
    }

    public boolean isUnlocked() {
        return isUnlocked;
    }

    public void setUnlocked(boolean unlocked) {
        isUnlocked = unlocked;
    }

    public Tool[] getTools() {
        return tools;
    }

    public void setTools(Tool[] tools) {
        this.tools = tools;
    }

    public Weapon[] getWeapons() {
        return weapons;
    }

    public void setWeapons(Weapon[] weapons) {
        this.weapons = weapons;
    }

    public Armor[] getArmors() {
        return armors;
    }

    public void setArmors(Armor[] armors) {
        this.armors = armors;
    }

    public Ability[] getAbilities() {
        return abilities;
    }

    public void setAbilities(Ability[] abilities) {
        this.abilities = abilities;
    }

    public Attribute[] getAttributes() {
        return attributes;
    }

    public void setAttributes(Attribute[] attributes) {
        this.attributes = attributes;
    }

    public Requirement[] getRequirements() {
        return requirements;
    }

    public void setRequirements(Requirement[] requirements) {
        this.requirements = requirements;
    }

    public Modifier[] getModifiers() {
        return modifiers;
    }

    public void setModifiers(Modifier[] modifiers) {
        this.modifiers = modifiers;
    }

    /**
     * Returns false if any of the requirements have not been met.
     * 
     * @return
     */
    public boolean IsUnlocked() {
        if (isUnlocked) {
            return true;
        }
        for (Requirement requirement : requirements) {
            if (!requirement.getIsMet()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Can be called to unlock this unlockable.
     */
    public void unlock() {
        isUnlocked = true;
    }
}
