package com.deanin.levelin.player;

import com.deanin.levelin.Manager;
import com.deanin.levelin.skills.Skill;
import com.deanin.levelin.skills.mining.Mining;
import com.deanin.levelin.talents.TalentTree;
import com.deanin.levelin.talents.mining.MiningTalentTree;

import java.util.ArrayList;

public class TalentTrees {
    private ArrayList<TalentTree> talentTrees;

    public MiningTalentTree getMiningTalentTree() {
        return miningTalentTree;
    }

    public void setMiningTalentTree(MiningTalentTree miningTalentTree) {
        this.miningTalentTree = miningTalentTree;
    }

    private MiningTalentTree miningTalentTree;

    public TalentTrees(Skills skills) {
        miningTalentTree = new MiningTalentTree(skills.getMining());

        talentTrees = new ArrayList<TalentTree>();
        talentTrees.add(miningTalentTree);
    }
    public TalentTree getTalentTreeBySkill(Skill skill) {
        if (skill == Manager.player.skills.getMining()) {
            return miningTalentTree;
        }
        return new TalentTree("Undefined", "Undefined");
    }
}
