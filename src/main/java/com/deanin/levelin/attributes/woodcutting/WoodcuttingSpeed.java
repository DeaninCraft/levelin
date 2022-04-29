package com.deanin.levelin.attributes.woodcutting;

import com.deanin.levelin.Manager;
import com.deanin.levelin.attributes.Attribute;
import com.deanin.levelin.config.ConfigRegister;
import com.deanin.utils.MathHelpers;

public class WoodcuttingSpeed extends Attribute {

    /**
     * Calculate speed based on enchanting level
     */
    public float calculatedBreakingSpeed(String blockName) {
        float result;
        int woodCuttingLevel = Manager.player.skills.getWoodcutting().getLevel();
        if (woodCuttingLevel >= ConfigRegister.WOODCUTTING_CONFIG.woodcuttingBlockLevelRequirements.get(blockName)) {
            float levelCurve = (float) (Math.log10((woodCuttingLevel / 4.0D) + 0.25D) - Math.log10(0.25D));
            result = MathHelpers.clampFloat(levelCurve, 0.25f, 3.0f);
        } else {
            result = 0.0f;
        }

        return result;
    }
}
