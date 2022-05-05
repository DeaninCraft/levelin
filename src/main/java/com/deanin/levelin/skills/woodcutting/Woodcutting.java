package com.deanin.levelin.skills.woodcutting;

import com.deanin.levelin.Manager;
import com.deanin.levelin.attributes.Attribute;
import com.deanin.levelin.attributes.woodcutting.WoodcuttingSpeed;
import com.deanin.levelin.skills.Skill;

public class Woodcutting extends Skill {

    public Woodcutting(String name, String description, int level, int levelCap, Attribute primaryAttribute) {
        super(name, description, level, levelCap, primaryAttribute);
    }
    public Woodcutting() {
        super("Woodcutting",
                "Woodcutting is the skill of cutting down trees and gathering their usable resources.",
                1,
                100,
                new WoodcuttingSpeed());
    }
}
