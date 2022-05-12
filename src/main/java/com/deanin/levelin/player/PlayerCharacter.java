package com.deanin.levelin.player;

import com.deanin.levelin.Manager;
import com.deanin.levelin.events.BlockBreakEvents;
import com.deanin.levelin.events.BlockUseEvent;

public class PlayerCharacter {
    public Skills skills;
    public Attributes attributes;
    public TalentTrees talentTrees;
    public BlockBreakEvents blockBreakEvents;
    public BlockUseEvent blockUseEvents;

    public PlayerCharacter() {
        skills = new Skills();
        attributes = new Attributes(skills);
        talentTrees = new TalentTrees(skills);
        blockBreakEvents = new BlockBreakEvents(skills);
        //blockUseEvents = new BlockUseEvent(skills);


        skills.setActiveSkill(skills.getWoodcutting());
    }
}