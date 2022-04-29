package com.deanin.levelin.skills.farming;

import com.deanin.levelin.skills.Skill;
import com.deanin.levelin.skills.TalentTree;
import net.minecraft.block.BlockState;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Property;

public class Farming extends Skill {

    public Farming(String name, String description, int level, int levelCap) {
        super(name, description, level, levelCap);
    }

    public Farming() {
        super("Farming", "Farming is the skill of harvesting crops and animals for food products.", 1, 100);
    }

    public static int getCropAge(BlockState state) {
        Property AGE = null;
        for (Property property:state.getProperties()) {
            if(property.getName() == "age") AGE = property;
        }
        return Integer.parseInt(state.get(AGE).toString());
    }

}
