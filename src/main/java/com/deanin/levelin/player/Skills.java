package com.deanin.levelin.player;

import com.deanin.levelin.skills.Skill;
import com.deanin.levelin.skills.farming.Farming;
import com.deanin.levelin.skills.mining.Mining;
import com.deanin.levelin.skills.woodcutting.Woodcutting;

import java.util.ArrayList;

public class Skills {
    private ArrayList<Skill> skills;
    private Mining mining;
    private Farming farming;
    private Woodcutting woodcutting;
    private Skill activeSkill;

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<Skill> skills) {
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

    public Woodcutting getWoodcutting() {
        return woodcutting;
    }
    public void setWoodcutting(Woodcutting woodcutting) {
        this.woodcutting = woodcutting;
    }
    public Skill getActiveSkill() {
        return activeSkill;
    }
    public void setActiveSkill(Skill activeSkill) {
        this.activeSkill = activeSkill;
    }
    public Skills() {
        mining = new Mining();
        woodcutting = new Woodcutting();
        farming = new Farming();

        skills = new ArrayList<Skill>();

        skills.add(farming);
        skills.add(mining);
        skills.add(woodcutting);

    }
}
