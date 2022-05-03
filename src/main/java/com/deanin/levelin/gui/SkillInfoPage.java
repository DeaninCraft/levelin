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
        root.add(skillNameLabel, 1, 1);

        WDynamicLabel levelLabel = new WDynamicLabel(() ->
                Integer.toString(skill.getLevel()));

        root.add(levelLabel, 1, 1 );

        WDynamicLabel levelProgressLabel = new WDynamicLabel(() ->
                skill.getCurrentExperience() +
                        "/" +
                        skill.getExperienceToNextLevel());
        root.add(levelProgressLabel, 6, 1 );

        WDynamicLabel totalExpLabel = new WDynamicLabel(() ->
                "Total:" +
                        skill.getTotalExperience());
        root.add(totalExpLabel, 10, 1 );
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
