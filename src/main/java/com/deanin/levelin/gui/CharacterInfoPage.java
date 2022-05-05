package com.deanin.levelin.gui;

import com.deanin.levelin.Manager;
import com.deanin.levelin.skills.Skill;
import com.deanin.utils.MathHelpers;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.LiteralText;
import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static com.deanin.levelin.gui.helpers.Layout.getBottomPosition;

public class CharacterInfoPage extends LightweightGuiDescription {
    private Skill activeSkill;
    private MinecraftClient client;
    private WToggleButton currentlyActiveToggle;

    WDynamicLabel activeSkillNameLabel;
    WDynamicLabel activeSkillLevelLabel;
    WDynamicLabel activeSkillLevelProgressLabel;
    WDynamicLabel activeSkillAttributeLabel;

    int nameLabelXPos = 1;
    int levelLabelXPos = 6;
    int levelProgressXPos = 8;
    int attributeLabelXPos = 12;
    int activeXPos = 18;
    int infoButtonXPos = 20;
    public CharacterInfoPage(MinecraftClient client) {
        this.client = client;
        activeSkill = Manager.player.skills.getActiveSkill();

        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        int windowWidth = client.getWindow().getScaledWidth();
        int windowHeight = client.getWindow().getScaledHeight();
        int panelWidth = MathHelpers.clampInt(windowWidth, 200, 400);
        int panelHeight = MathHelpers.clampInt(windowHeight, 200, 400);
        root.setSize(panelWidth, panelHeight);

        createActiveSkillGridItem(root, activeSkill);
        createGrid(root);
        createCloseButton(root);
    }
    public void createGrid(WGridPanel root) {
        ArrayList<Skill> skills = Manager.player.skills.getSkills();

        createColumnTitles(root);
        for (int i = 0; i < skills.size(); i++) {
            int row = i + 3;
            Skill skill = skills.get(i);
            createGridItem(root, skill,  skill.getName(), row);
            createActiveSkillToggle(root, skill, 19, row);
        }
    }


    public void createColumnTitles(WGridPanel root) {
        int row = 1;
        WLabel nameLabel = new WLabel("Skill");
        WLabel levelLabel = new WLabel("Level");
        WLabel levelProgressLabel = new WLabel("Experience");
        WLabel attributeLabel = new WLabel("Attribute");
        WLabel activeToggle = new WLabel("Active?");

        root.add(nameLabel, nameLabelXPos, row);
        root.add(levelLabel, levelLabelXPos, row);
        root.add(levelProgressLabel, levelProgressXPos, row);
        root.add(attributeLabel, attributeLabelXPos, row);
        root.add(activeToggle, activeXPos, row);
    }
    public void createGridItem(WGridPanel root, Skill skill, String name, int row) {
        WDynamicLabel nameLabel = createSkillLabel(name);
        WDynamicLabel levelLabel = createSkillLabel(Integer.toString(skill.getLevel()));
        WDynamicLabel levelProgressLabel = createSkillLabel(skill.getCurrentExperience() +
                "/" +
                skill.getExperienceToNextLevel());
        createSkillButton(root, skill, row);
        WDynamicLabel attributeLabel = createAttributeLabel(skill);

        root.add(nameLabel, nameLabelXPos, row);
        root.add(levelLabel, levelLabelXPos, row);
        root.add(levelProgressLabel, levelProgressXPos, row);
        root.add(attributeLabel, attributeLabelXPos, row);
    }

    public void createActiveSkillGridItem(WGridPanel root, Skill skill) {
        String activeSkill = "Active Skill";
        int row = 2;
        activeSkillNameLabel = createSkillLabel(activeSkill);
        activeSkillLevelLabel = createSkillLabel(Integer.toString(skill.getLevel()));
        activeSkillLevelProgressLabel = createSkillLabel(skill.getCurrentExperience() +
                "/" +
                skill.getExperienceToNextLevel());
        createSkillButton(root, skill, row);
        activeSkillAttributeLabel = createAttributeLabel(skill);

        root.add(activeSkillNameLabel, nameLabelXPos, row);
        root.add(activeSkillLevelLabel, levelLabelXPos, row);
        root.add(activeSkillLevelProgressLabel, levelProgressXPos, row);
        root.add(activeSkillAttributeLabel, attributeLabelXPos, row);
    }


    public void recreateActiveSkillGridItem(WGridPanel root, Skill skill) {
        root.remove(activeSkillNameLabel);
        root.remove(activeSkillLevelLabel);
        root.remove(activeSkillLevelProgressLabel);
        root.remove(activeSkillAttributeLabel);
        createActiveSkillGridItem(root, skill);
    }


    @NotNull
    private WDynamicLabel createSkillLabel(String label) {
        WDynamicLabel wDynamicLabel = new WDynamicLabel(() -> label);
        return wDynamicLabel;
    }

    @NotNull
    private WDynamicLabel createAttributeLabel(Skill skill) {
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(2);
        String attributeToTwoDecimals = decimalFormat.format(skill.getPrimaryAttribute().getValue());
        WDynamicLabel attributeLabel = createSkillLabel(attributeToTwoDecimals);
        return attributeLabel;
    }

    private void createActiveSkillToggle(WGridPanel root, Skill skill, int x, int y) {
        WToggleButton activeToggleButton = new WToggleButton();
        activeToggleButton.setToggle(isSkillActive(skill));

        activeToggleButton.setOnToggle(on -> {
            activeToggleButtonOnClick(skill, activeToggleButton);
            recreateActiveSkillGridItem(root, skill);
        });
        if (skill == activeSkill) {
            activeToggleButton.setToggle(true);
            currentlyActiveToggle = activeToggleButton;
        }
        root.add(activeToggleButton, x, y);
    }

    private void activeToggleButtonOnClick(Skill skill, WToggleButton activeToggleButton) {
        if (currentlyActiveToggle != null) {
            currentlyActiveToggle.setToggle(false);
        }
        currentlyActiveToggle = activeToggleButton;
        Manager.player.skills.setActiveSkill(skill);
        currentlyActiveToggle.setToggle(true);
    }

    private boolean isSkillActive(Skill skill) {
        return Manager.player.skills.getActiveSkill() == skill;
    }

    private void createSkillButton(WGridPanel root, Skill skill, int row) {
        WButton skillInfoButton = new WButton(new LiteralText("Info"));

        skillInfoButton.setOnClick(() -> {
            client.setScreen(new LevelinScreen(new SkillInfoPage(client, skill)));
        });

        root.add(skillInfoButton, infoButtonXPos, row, 2, 1);
    }
    /**
     * This should be the back button
     * @param root The root panel you're attaching this button to.
     */
    private void createCloseButton(WGridPanel root) {
        int closeButtonLocation = getBottomPosition(root.getHeight());

        WButton skillInfoButton = new WButton(new LiteralText("Close"));

        skillInfoButton.setOnClick(() -> {
            client.setScreen(null);
        });
        root.add(skillInfoButton, 1, closeButtonLocation, 2, 1);
    }
}
