package com.deanin.levelin.attributes.mining;

import com.deanin.levelin.Manager;
import com.deanin.levelin.attributes.Attribute;
import com.deanin.levelin.config.ConfigRegister;
import com.deanin.utils.MathHelpers;
import oshi.util.tuples.Pair;

import java.util.Map;

public class MiningSpeed extends Attribute {

    public MiningSpeed() {
        super("Mining Speed");
    }

    /**
     * Calculate speed based on enchanting level
     */
    public float getBlockBreakingSpeed(String blockName) {
        float result;
        int miningLevel = Manager.player.skills.getMining().getLevel();
        Map<String, Integer> miningBlockLevelRequirements = ConfigRegister.MINING_CONFIG.miningBlockLevelRequirements;
        int levelRequirement = miningBlockLevelRequirements.get(blockName) == null ? 1 : miningBlockLevelRequirements.get(blockName);
        if (miningLevel >= levelRequirement) {
            result = getValue();
        } else {
            result = 0.0f;
        }
        return result;
    }

}
