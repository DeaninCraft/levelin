package com.deanin.levelin.gui;

import com.deanin.levelin.Manager;
import com.deanin.levelin.skills.Skill;
import com.deanin.utils.MathHelpers;
import io.github.cottonmc.cotton.gui.GuiDescription;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.LiteralText;
import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;

import static com.deanin.levelin.gui.helpers.Layout.getBottomPosition;

public class SkillInfoPage extends LightweightGuiDescription {
    /**
     * The skill whose details this page lists.
     */
    private Skill skill;
    /**
     * Reference to the local Minecraft Client object
     */
    private MinecraftClient client;

    private WToggleButton currentlyActiveToggle;

    int nameLabelXPos = 1;
    int levelLabelXPos = 6;
    int levelProgressXPos = 8;
    int totalExpXPos = 11;
    int attributeLabelXPos = 14;
    int activeXPos = 18;
    int talentsXPos = 20;
    public SkillInfoPage(MinecraftClient client, Skill skill) {
        this.client = client;
        this.skill = skill;
        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        int windowWidth = client.getWindow().getScaledWidth();
        int windowHeight = client.getWindow().getScaledHeight();
        int panelWidth = MathHelpers.clampInt(windowWidth, 200, 400);
        int panelHeight = MathHelpers.clampInt(windowHeight, 200, 400);
        root.setSize(panelWidth, panelHeight);

        createColumnTitles(root);
        displaySkillInfo(root);
        createBackButton(root);
        createTalentTreeButton(root);
    }
    public void displaySkillInfo(WGridPanel root) {
        WLabel skillNameLabel = new WLabel(skill.getName());

        WDynamicLabel levelLabel = createSkillLabel(Integer.toString(skill.getLevel()));
        WDynamicLabel levelProgressLabel = createSkillLabel(skill.getCurrentExperience() +
                "/" +
                skill.getExperienceToNextLevel());
        WDynamicLabel totalExpLabel = createSkillLabel("Total:" +
                skill.getTotalExperience());
        WDynamicLabel attributeLabel = createAttributeLabel();
        int row = 2;
        root.add(skillNameLabel, nameLabelXPos, row);
        root.add(levelLabel, levelLabelXPos, row );
        root.add(levelProgressLabel, levelProgressXPos, row );
        root.add(totalExpLabel, totalExpXPos, row );
        root.add(attributeLabel, attributeLabelXPos, row);
        createActiveSkillToggle(root, skill, activeXPos, row);
    }
    public void createColumnTitles(WGridPanel root) {
        int row = 1;
        WLabel nameLabel = new WLabel("Skill");
        WLabel levelLabel = new WLabel("Level");
        WLabel levelProgressLabel = new WLabel("Experience");
        WLabel totalExpLabel = new WLabel("Total Exp");
        WLabel attributeLabel = new WLabel("Attribute");
        WLabel activeToggle = new WLabel("Active?");

        root.add(nameLabel, nameLabelXPos, row);
        root.add(levelLabel, levelLabelXPos, row);
        root.add(levelProgressLabel, levelProgressXPos, row);
        root.add(totalExpLabel, totalExpXPos, row );
        root.add(attributeLabel, attributeLabelXPos, row);
        root.add(activeToggle, activeXPos, row);
    }

    @NotNull
    private WDynamicLabel createSkillLabel(String skill) {
        WDynamicLabel totalExpLabel = new WDynamicLabel(() ->
                skill);
        return totalExpLabel;
    }

    @NotNull
    private WDynamicLabel createAttributeLabel() {
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(2);
        String attributeToTwoDecimals = decimalFormat.format(skill.getPrimaryAttribute().getValue());
        WDynamicLabel attributeLabel = createSkillLabel(attributeToTwoDecimals);
        return attributeLabel;
    }

    /**
     * This should be the back button
     * @param root The root panel you're attaching this button to.
     */
    private void createBackButton(WGridPanel root) {
        int backButtonLocation = getBottomPosition(root.getHeight());
        WButton skillInfoButton = new WButton(new LiteralText("Back"));

        skillInfoButton.setOnClick(() -> {
            client.setScreen(new LevelinScreen(new CharacterInfoPage(client)));
        });

        root.add(skillInfoButton, 1, backButtonLocation, 2, 1);
    }

    private void createActiveSkillToggle(WGridPanel root, Skill skill, int x, int y) {
        WToggleButton activeToggleButton = new WToggleButton();
        activeToggleButton.setToggle(isSkillActive(skill));

        activeToggleButton.setOnToggle(on -> {
            activeToggleButtonOnClick(skill, activeToggleButton);
        });

        if (skill == Manager.player.skills.getActiveSkill()) {
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

    public void createTalentTreeButton(WGridPanel root) {
        int backButtonLocation = getBottomPosition(root.getHeight());
        WButton skillInfoButton = new WButton(new LiteralText("Talents"));

        skillInfoButton.setOnClick(() -> {
            client.setScreen(new LevelinScreen(new CharacterTalentPage(client, skill)));
        });

        root.add(skillInfoButton, talentsXPos, 2, 2, 1);
    }
}
