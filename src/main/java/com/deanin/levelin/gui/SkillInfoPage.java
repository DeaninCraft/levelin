package com.deanin.levelin.gui;

import com.deanin.levelin.skills.Skill;
import io.github.cottonmc.cotton.gui.GuiDescription;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WDynamicLabel;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.LiteralText;

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

    GuiDescription previousScreen;
    public SkillInfoPage(MinecraftClient client, Skill skill, GuiDescription previousScreen) {
        this.client = client;
        this.skill = skill;
        this.previousScreen = previousScreen;
        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(400,400);

        displaySkillInfo(root);
        createBackButton(root);
    }
    public void displaySkillInfo(WGridPanel root) {
        WLabel skillNameLabel = new WLabel(skill.getName());


        WDynamicLabel levelLabel = new WDynamicLabel(() ->
                Integer.toString(skill.getLevel()));



        WDynamicLabel levelProgressLabel = new WDynamicLabel(() ->
                skill.getCurrentExperience() +
                        "/" +
                        skill.getExperienceToNextLevel());


        WDynamicLabel totalExpLabel = new WDynamicLabel(() ->
                "Total:" +
                        skill.getTotalExperience());


        WDynamicLabel attributeLabel = new WDynamicLabel(() ->
                skill.getPrimaryAttribute().getName() + ": " +
                        skill.getPrimaryAttribute().calculatedAttributeValue());
        root.add(skillNameLabel, 1, 1);
        root.add(levelLabel, 5, 1 );
        root.add(levelProgressLabel, 6, 1 );
        root.add(totalExpLabel, 9, 1 );
        root.add(attributeLabel, 12, 1);

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
}
