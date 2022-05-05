package com.deanin.levelin.gui;

import com.deanin.levelin.Manager;
import com.deanin.levelin.skills.Skill;
import com.deanin.levelin.talents.Talent;
import com.deanin.levelin.talents.TalentTree;
import com.deanin.utils.MathHelpers;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;

import java.util.ArrayList;

import static com.deanin.levelin.gui.helpers.Layout.getBottomPosition;

public class CharacterTalentPage extends LightweightGuiDescription {

    /**
     * The skill whose details this page lists.
     */
    private Skill skill;
    /**
     * Reference to the local Minecraft Client object
     */
    private MinecraftClient client;
    public CharacterTalentPage(MinecraftClient client, Skill skill) {
        this.client = client;
        this.skill = skill;
        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        int windowWidth = client.getWindow().getScaledWidth();
        int windowHeight = client.getWindow().getScaledHeight();
        int panelWidth = MathHelpers.clampInt(windowWidth, 200, 400);
        int panelHeight = MathHelpers.clampInt(windowHeight, 200, 400);
        root.setSize(panelWidth, panelHeight);

        String skillName = skill.getName();
        WLabel skillNameLabel = new WLabel(skillName);
        root.add(skillNameLabel, 1, 10);
        createTalentGrid(root);
        createBackButton(root);
    }
    public void createTalentGrid(WGridPanel root) {
        TalentTree talentTree = Manager.player.talentTrees.getTalentTreeBySkill(skill);
        ArrayList<Talent> talents = talentTree.getTalents();
        for (int i = 0; i < talents.size(); i++) {
            int row = i + 2;
            Talent talent = talents.get(i);
            WButton button = new WButton(new LiteralText(talent.getName()));
            button.setEnabled(!talent.isPurchased());
            button.setOnClick(() -> {
                // TODO: Probably needs to happen on server?
                if (talent.getRequirementsMet()) {
                    boolean purchased = talent.tryBuy(client.player);
                    button.setEnabled(!purchased);
                }
            });
            root.add(button, 3, row);
        }
    }
    /**
     * This should be the back button
     * @param root The root panel you're attaching this button to.
     */
    private void createBackButton(WGridPanel root) {
        int backButtonLocation = getBottomPosition(root.getHeight());
        WButton skillInfoButton = new WButton(new LiteralText("Back"));

        skillInfoButton.setOnClick(() -> {
            client.setScreen(new LevelinScreen(new SkillInfoPage(client, skill)));
        });

        root.add(skillInfoButton, 1, backButtonLocation, 2, 1);
    }

}
