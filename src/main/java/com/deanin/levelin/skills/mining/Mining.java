package com.deanin.levelin.skills.mining;

import com.deanin.levelin.skills.Skill;
import com.deanin.levelin.skills.TalentTree;
import com.deanin.utils.MathHelpers;

public class Mining extends Skill {
    private String name;
    private String description;
    private int level;
    private int levelCap;

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public int levelUp() {
        return super.levelUp();
    }

    @Override
    public int levelDown() {
        return super.levelDown();
    }

    @Override
    public int getCurrentExperience() {
        return super.getCurrentExperience();
    }

    @Override
    public int getExperienceToNextLevel() {
        return super.getExperienceToNextLevel();
    }

    @Override
    public int getTotalExperience() {
        return super.getTotalExperience();
    }

    @Override
    public void addExperience(int experienceToAdd) {
        super.addExperience(experienceToAdd);
    }

    @Override
    public float getProgressToNextLevel() {
        return super.getProgressToNextLevel();
    }

    @Override
    public int getLevelCap() {
        return levelCap;
    }

    @Override
    public void setLevelCap(int levelCap) {
        this.levelCap = levelCap;
    }

    @Override
    public void setCurrentExperience(int currentExperience) {
        super.setCurrentExperience(currentExperience);
    }

    @Override
    public void setExperienceToNextLevel(int experienceToNextLevel) {
        super.setExperienceToNextLevel(experienceToNextLevel);
    }

    @Override
    public void setTotalExperience(int totalExperience) {
        super.setTotalExperience(totalExperience);
    }

    @Override
    public int getMinLevel() {
        return super.getMinLevel();
    }

    @Override
    public void setMinLevel(int minLevel) {
        super.setMinLevel(minLevel);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }

    @Override
    public void setDescription(String description) {
        super.setDescription(description);
    }

    @Override
    public TalentTree getTalentTree() {
        return super.getTalentTree();
    }

    @Override
    public void setTalentTree(TalentTree talentTree) {
        super.setTalentTree(talentTree);
    }

    public Mining(String name, String description, int level, int levelCap) {
        super(name, description, level, levelCap);
    }

    public Mining() {
        super("Mining", "Mining is the skill of digging up ore and extracting it into usable resources.", 1, 100);
    }

}
