package com.deanin.levelin.requirements;

import com.deanin.levelin.skills.Skill;

public class Requirement {
    private boolean isMet;
    private Skill skill;
    private int levelRequirement;

    public void setIsMet(boolean isMet) {
        this.isMet = isMet;
    }

    public boolean getIsMet() {
        return isMet;
    }

    public boolean isMet() {
        return isMet;
    }

    public void setMet(boolean met) {
        isMet = met;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public int getLevelRequirement() {
        return levelRequirement;
    }

    public void setLevelRequirement(int levelRequirement) {
        this.levelRequirement = levelRequirement;
    }

    public Requirement() {
        isMet = false;
        levelRequirement = 1;
    }

    public boolean hasLevelRequired() {
        return skill.getLevel() >= levelRequirement;
    }

}
