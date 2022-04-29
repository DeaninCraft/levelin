package com.deanin.levelin.skills.mining;

import com.deanin.levelin.config.LevelinConfig;
import com.deanin.levelin.skills.Skill;
import com.deanin.levelin.skills.TalentTree;
import com.deanin.utils.MathHelpers;
import me.shedaniel.autoconfig.AutoConfig;

public class Mining extends Skill {
    public Mining(String name, String description, int level, int levelCap) {
        super(name, description, level, levelCap);
    }

    public Mining() {
        super("Mining", "Mining is the skill of digging up ore and extracting it into usable resources.", 1, 100);

    }

}
