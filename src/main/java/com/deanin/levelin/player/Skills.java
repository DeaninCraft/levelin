package com.deanin.levelin.player;

import com.deanin.levelin.skills.Skill;
import com.deanin.levelin.skills.farming.Farming;
import com.deanin.levelin.skills.mining.Mining;

public class Skills {
    private Skill[] skills;
    private Mining mining;
    private Farming farming;

    public Skill[] getSkills() {
        return skills;
    }

    public void setSkills(Skill[] skills) {
        this.skills = skills;
    }
    public Mining getMining() {
        return mining;
    }
    public Farming getFarming(){
        return farming;
    }

    public void setMining(Mining mining) {
        this.mining = mining;
    }
    public void setFarming(Farming farming) { this.farming = farming; }

    public Skills() {
        mining = new Mining();
        farming = new Farming();
    }
}
