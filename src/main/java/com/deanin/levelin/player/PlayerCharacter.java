package com.deanin.levelin.player;

import com.deanin.levelin.events.BlockBreakEvents;

public class PlayerCharacter {
    public PlayerCharacter playerCharacter;
    public Skills skills;
    public Attributes attributes;

    public PlayerCharacter() {
        skills = new Skills();
        attributes = new Attributes(skills);
        BlockBreakEvents blockBreakEvents = new BlockBreakEvents(skills);
    }
}
