package com.deanin.levelin.player;

import com.deanin.levelin.skills.Skill;
import com.deanin.levelin.skills.mining.Mining;

public class Skills {
    private Skill[] skills;
    private Mining mining;

    public Skill[] getSkills() {
        return skills;
    }

    public void setSkills(Skill[] skills) {
        this.skills = skills;
    }
    public Mining getMining() {
        return mining;
    }

    public void setMining(Mining mining) {
        this.mining = mining;
    }
    public Skills() {
        mining = new Mining();
    }
}
