package com.deanin.levelin.unlockables;

import com.deanin.levelin.abilities.Ability;
import com.deanin.levelin.armor.Armor;
import com.deanin.levelin.attributes.Attribute;
import com.deanin.levelin.modifiers.Modifier;
import com.deanin.levelin.requirements.Requirement;
import com.deanin.levelin.skills.Skill;
import com.deanin.levelin.tools.Tool;
import com.deanin.levelin.weapons.Weapon;

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
    private Skill[] skills;
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
            if (!requirement.isMet()) {
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
