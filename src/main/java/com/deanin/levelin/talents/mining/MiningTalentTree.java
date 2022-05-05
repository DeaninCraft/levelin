package com.deanin.levelin.talents.mining;

import com.deanin.levelin.skills.Skill;
import com.deanin.levelin.talents.Talent;
import com.deanin.levelin.talents.TalentTree;

import java.util.ArrayList;

public class MiningTalentTree extends TalentTree {
    private HoleNewBeginning holeNewBeginning;
    public MiningTalentTree(Skill skill) {
        super("Mining", "It's the mining tree!");
        setSkill(skill);

        holeNewBeginning = new HoleNewBeginning(
                "Hole New Beginning",
                "Increases Mining Speed By 1%",
                1,
                skill);
        talents.add(holeNewBeginning);

        setPurchasableTalents(talents);
        skill.setTalentTree(this);
    }
}
