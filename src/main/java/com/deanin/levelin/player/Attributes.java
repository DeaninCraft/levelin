package com.deanin.levelin.player;

import com.deanin.levelin.attributes.Attribute;
import com.deanin.levelin.attributes.farming.FarmingAttribute;
import com.deanin.levelin.attributes.mining.MiningSpeed;
import com.deanin.levelin.attributes.woodcutting.WoodcuttingSpeed;

public class Attributes {
    private Attribute[] attributes;



    private FarmingAttribute farmingAttribute;
    private MiningSpeed miningSpeed;
    private WoodcuttingSpeed woodcuttingSpeed;
    private Skills skills;
    public Attribute[] getAttributes() {
        return attributes;
    }
    public void setAttributes(Attribute[] attributes) {
        this.attributes = attributes;
    }
    public MiningSpeed getMiningSpeed() {
        return miningSpeed;
    }
    public void setMiningSpeed(MiningSpeed miningSpeed) {
        this.miningSpeed = miningSpeed;
    }
    public WoodcuttingSpeed getWoodcuttingSpeed() {
        return woodcuttingSpeed;
    }
    public void setWoodcuttingSpeed(WoodcuttingSpeed woodcuttingSpeed) {
        this.woodcuttingSpeed = woodcuttingSpeed;
    }
    public FarmingAttribute getFarmingAttribute() {
        return farmingAttribute;
    }
    public void setFarmingAttribute(FarmingAttribute farmingAttribute) {
        this.farmingAttribute = farmingAttribute;
    }
    public Skills getSkills() {
        return skills;
    }
    public void setSkills(Skills skills) {
        this.skills = skills;
    }
    public Attributes(Skills skills) {
        this.skills = skills;
        farmingAttribute = (FarmingAttribute) skills.getFarming().getPrimaryAttribute();
        miningSpeed = (MiningSpeed) skills.getMining().getPrimaryAttribute();
        woodcuttingSpeed = (WoodcuttingSpeed) skills.getWoodcutting().getPrimaryAttribute();
    }
}