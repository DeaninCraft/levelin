package com.deanin.levelin.talents.mining;

import com.deanin.levelin.modifiers.HoleInOneModifier;
import com.deanin.levelin.requirements.LevelOneRequirement;
import com.deanin.levelin.skills.Skill;
import com.deanin.levelin.talents.Talent;
import com.deanin.levelin.unlockables.Unlockable;

public class HoleNewBeginning extends Talent {
    private Unlockable unlockable;

    public HoleNewBeginning(String name, String description, int cost, Skill skill) {
        super(name, description, cost, skill);
        LevelOneRequirement levelOneRequirement = new LevelOneRequirement(skill);
        addRequirement(levelOneRequirement);
        setModifier(new HoleInOneModifier());
    }
}
