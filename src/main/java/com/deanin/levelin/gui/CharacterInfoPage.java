package com.deanin.levelin.gui;

import com.deanin.levelin.Manager;
import com.deanin.levelin.attributes.mining.MiningSpeed;
import com.deanin.levelin.skills.mining.Mining;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WDynamicLabel;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import net.minecraft.client.MinecraftClient;

public class CharacterInfoPage extends LightweightGuiDescription {
    private MiningSpeed miningSpeed;
    private Mining mining;
    public CharacterInfoPage(MinecraftClient client) {
        miningSpeed = Manager.player.attributes.getMiningSpeed();
        mining = Manager.player.skills.getMining();

        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(300,300);

        WDynamicLabel levelLabel = new WDynamicLabel(() ->
                "Level: " +
                        mining.getLevel());
        root.add(levelLabel, 1, 1 );

        WDynamicLabel totalExpLabel = new WDynamicLabel(() ->
                "Total Exp: " +
                        mining.getTotalExperience());
        root.add(totalExpLabel, 1, 2 );

        WDynamicLabel levelProgressLabel = new WDynamicLabel(() ->
                "Experience:" +
                        mining.getCurrentExperience() +
                "/" +
                        mining.getExperienceToNextLevel());
        root.add(levelProgressLabel, 1, 3 );

        WDynamicLabel label = new WDynamicLabel(() ->
                "Mining Speed: " +
                        miningSpeed.calculatedMiningSpeed());
        root.add(label, 1, 4 );
    }
}
