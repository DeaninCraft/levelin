package com.deanin.levelin.player;

import com.deanin.levelin.events.BlockBreakEvents;

public class PlayerCharacter {
    public Skills skills;
    public Attributes attributes;
    public BlockBreakEvents blockBreakEvents;

    public PlayerCharacter() {
        skills = new Skills();
        attributes = new Attributes(skills);
        blockBreakEvents = new BlockBreakEvents(skills);

        skills.setActiveSkill(skills.getWoodcutting());
    }
}