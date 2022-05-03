package com.deanin.levelin.attributes.mining;

import com.deanin.levelin.Manager;
import com.deanin.levelin.attributes.Attribute;
import com.deanin.levelin.config.ConfigRegister;
import com.deanin.utils.MathHelpers;

public class MiningSpeed extends Attribute {

    public MiningSpeed() {
        super("Mining Speed");
    }

    /**
     * Calculate speed based on enchanting level
     */
    public float calculatedBreakingSpeed(String blockName) {
        float result;
        int miningLevel = Manager.player.skills.getMining().getLevel();
        if (miningLevel >= ConfigRegister.MINING_CONFIG.miningBlockLevelRequirements.get(blockName)) {
            float levelCurve = (float) (Math.log10((miningLevel / 4.0D) + 0.25D) - Math.log10(0.25D));
            result = MathHelpers.clampFloat(levelCurve, 0.25f, 3.0f);
        } else {
            result = 0.0f;
        }
        return result;
    }
}
