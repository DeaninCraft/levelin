package com.deanin.levelin.gui;

import com.deanin.levelin.Manager;
import com.deanin.levelin.skills.Skill;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WBar;
import io.github.cottonmc.cotton.gui.widget.WDynamicLabel;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import net.minecraft.client.MinecraftClient;

public class CharacterInfoPage extends LightweightGuiDescription {
    private Skill activeSkill;
    public CharacterInfoPage(MinecraftClient client) {
        activeSkill = Manager.player.skills.getActiveSkill();

        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(400,400);

        createGridItem(root, activeSkill, "Active Skill", 1);
        createGrid(root);
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
        root.add(nameLabel, 1, row );

        WDynamicLabel levelLabel = new WDynamicLabel(() ->
                        Integer.toString(skill.getLevel()));
        root.add(levelLabel, 5, row );

        WDynamicLabel levelProgressLabel = new WDynamicLabel(() ->
                        skill.getCurrentExperience() +
                        "/" +
                        skill.getExperienceToNextLevel());
        root.add(levelProgressLabel, 6, row );

        WDynamicLabel totalExpLabel = new WDynamicLabel(() ->
                "Total:" +
                        skill.getTotalExperience());
        root.add(totalExpLabel, 10, row );

        // Refactor to be the attribute name of the skill?
        WDynamicLabel label = new WDynamicLabel(() ->
                skill.getPrimaryAttribute().getName() +
                        skill.getPrimaryAttribute().calculatedBreakingSpeed());
    }
}
