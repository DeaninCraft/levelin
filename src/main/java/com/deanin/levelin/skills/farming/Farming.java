package com.deanin.levelin.skills.farming;

import com.deanin.levelin.skills.Skill;
import com.deanin.levelin.skills.TalentTree;

public class Farming extends Skill {

    public Farming(String name, String description, int level, int levelCap) {
        super(name, description, level, levelCap);
    }

    public Farming() {
        super("Farming", "Farming is the skill of harvesting crops and animals for food products.", 1, 100);
    }

}
