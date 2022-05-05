package com.deanin.levelin.attributes.woodcutting;

import com.deanin.levelin.Manager;
import com.deanin.levelin.attributes.Attribute;
import com.deanin.levelin.config.ConfigRegister;
import com.deanin.utils.MathHelpers;

public class WoodcuttingSpeed extends Attribute {

    public WoodcuttingSpeed() {
        super("Woodcutting Speed");
    }

    /**
     * Calculate speed based on enchanting level
     */
    public float getBlockBreakingSpeed(String blockName) {
        float result;
        int woodCuttingLevel = Manager.player.skills.getWoodcutting().getLevel();
        if (woodCuttingLevel >= ConfigRegister.WOODCUTTING_CONFIG.woodcuttingBlockLevelRequirements.get(blockName)) {
            result = getValue();
        } else {
            result = 0.0f;
        }
        return result;
    }

}
