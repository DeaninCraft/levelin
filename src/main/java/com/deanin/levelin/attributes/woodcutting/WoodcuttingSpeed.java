package com.deanin.levelin.attributes.woodcutting;

import com.deanin.levelin.Manager;
import com.deanin.utils.MathHelpers;

public class WoodcuttingSpeed {

    /**
     * Calculate speed based on enchanting level
     */
    public float calculatedWoodcuttingSpeed() {
        int woodcuttingLevel = Manager.player.skills.getWoodcutting().getLevel();
        float levelCurve = (float) (Math.log10((woodcuttingLevel / 4.0D) + 0.25D) - Math.log10(0.25D));
        float clampedLevelCurve = MathHelpers.clampFloat(levelCurve, 0.25f, 3.0f);

        return clampedLevelCurve;
    }
}
