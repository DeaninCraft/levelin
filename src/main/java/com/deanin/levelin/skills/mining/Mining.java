package com.deanin.levelin.skills.mining;

import com.deanin.levelin.Manager;
import com.deanin.levelin.attributes.Attribute;
import com.deanin.levelin.attributes.mining.MiningSpeed;
import com.deanin.levelin.skills.Skill;

public class Mining extends Skill {
    public Mining(String name, String description, int level, int levelCap, Attribute primaryAttribute) {
        super(name, description, level, levelCap, primaryAttribute);
    }

    public Mining() {
        super("Mining",
                "Mining is the skill of digging up ore and extracting it into usable resources.",
                1,
                100,
                new MiningSpeed());
    }

}
