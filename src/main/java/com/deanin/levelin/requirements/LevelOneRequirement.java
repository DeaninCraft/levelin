package com.deanin.levelin.requirements;

import com.deanin.levelin.skills.Skill;

public class LevelOneRequirement extends Requirement {

    public LevelOneRequirement(Skill skill) {
        setSkill(skill);
        setLevelRequirement(1);
        setIsMet(hasLevelRequired());
    }
}
