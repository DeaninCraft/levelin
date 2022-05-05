package com.deanin.levelin.unlockables.mining;

import com.deanin.levelin.Manager;
import com.deanin.levelin.player.Skills;
import com.deanin.levelin.requirements.Requirement;
import com.deanin.levelin.skills.Skill;
import com.deanin.levelin.skills.mining.Mining;
import com.deanin.levelin.talents.mining.HoleNewBeginning;
import com.deanin.levelin.unlockables.Unlockable;

import java.util.ArrayList;

public class HoleNewBeginningUnlockable extends Unlockable {

    public HoleNewBeginningUnlockable() {
        ArrayList<Skill> skills = new ArrayList<Skill>(Manager.player.skills.getSkills());
        Mining mining = Manager.player.skills.getMining();
        skills.add(mining);
        setSkills(skills);

        ArrayList<Requirement> requirements = new ArrayList<Requirement>();
        Requirement requirement = new Requirement();
    }
}
