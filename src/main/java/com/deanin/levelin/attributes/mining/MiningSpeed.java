package com.deanin.levelin.attributes.mining;

import com.deanin.levelin.Levelin;
import com.deanin.levelin.attributes.Attribute;
import com.deanin.levelin.player.PlayerCharacter;
import com.deanin.levelin.skills.mining.Mining;
import com.deanin.utils.MathHelpers;

public class MiningSpeed extends Attribute {
    private String name;

    private Mining mining;
    private float value;


    public MiningSpeed(Mining mining) {
        name = "Mining Speed";
        this.mining = mining;
    }


    /**
     * Calculate speed based on enchanting level
     */
    public float calculatedMiningSpeed() {
        float levelCurve = (float) (Math.log10((mining.getLevel() / 4.0D) + 0.25D) - Math.log10(0.25D));
        float clampedLevelCurve = MathHelpers.clampFloat(levelCurve, 0.25f, 3.0f);

        return clampedLevelCurve;
    }
}
