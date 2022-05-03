package com.deanin.levelin.gui;

import com.deanin.levelin.Manager;
import com.deanin.levelin.skills.Skill;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.LiteralText;

import static com.deanin.levelin.gui.helpers.Layout.getBottomPosition;

public class CharacterInfoPage extends LightweightGuiDescription {
    private Skill activeSkill;
    MinecraftClient client;
    public CharacterInfoPage(MinecraftClient client) {
        this.client = client;
        activeSkill = Manager.player.skills.getActiveSkill();

        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(400,400);

        createGridItem(root, activeSkill, "Active Skill", 1);
        createGrid(root);
        createCloseButton(root);
    }
    public void createGrid(WGridPanel root) {
        Skill[] skills = Manager.player.skills.getSkills();

        for (int i = 0; i < skills.length; i++) {
            Skill skill = skills[i];
            createGridItem(root, skill,  skill.getName(), i + 2);
        }
    }
    public void createGridItem(WGridPanel root, Skill skill, String name, int row) {
        WDynamicLabel nameLabel = new WDynamicLabel(() ->
                name);


        WDynamicLabel levelLabel = new WDynamicLabel(() ->
                        Integer.toString(skill.getLevel()));


        WDynamicLabel levelProgressLabel = new WDynamicLabel(() ->
                        skill.getCurrentExperience() +
                        "/" +
                        skill.getExperienceToNextLevel());


        WDynamicLabel totalExpLabel = new WDynamicLabel(() ->
                "Total:" +
                        skill.getTotalExperience());
        createSkillButton(root, skill, row);

        WDynamicLabel attributeLabel = new WDynamicLabel(() ->
                skill.getPrimaryAttribute().getName() + ": " +
                        skill.getPrimaryAttribute().calculatedAttributeValue());

        root.add(nameLabel, 1, row);
        root.add(levelLabel, 5, row);
        root.add(levelProgressLabel, 6, row);
        root.add(totalExpLabel, 9, row);
        root.add(attributeLabel, 12, row);
    }

    private void createSkillButton(WGridPanel root, Skill skill, int row) {
        WButton skillInfoButton = new WButton(new LiteralText("Info"));

        skillInfoButton.setOnClick(() -> {
            client.setScreen(new LevelinScreen(new SkillInfoPage(client, skill, this)));
        });

        root.add(skillInfoButton, 20, row, 2, 1);
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
