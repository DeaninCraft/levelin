package com.deanin.levelin.gui;

import com.deanin.levelin.Levelin;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WDynamicLabel;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
public class ExampleGui extends LightweightGuiDescription {

    public ExampleGui() {
        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(300,300);

        WDynamicLabel levelLabel = new WDynamicLabel(() ->
                "Level: " +
                Levelin.levels.getLevel());
        root.add(levelLabel, 1, 1 );

        WDynamicLabel totalExpLabel = new WDynamicLabel(() ->
                "Total Exp: " +
                Levelin.levels.getTotalExperience());
        root.add(totalExpLabel, 1, 2 );

        WDynamicLabel levelProgressLabel = new WDynamicLabel(() ->
                "Experience:" +
                Levelin.levels.getCurrentExperience() +
                "/" +
                Levelin.levels.getExperienceToNextLevel());
        root.add(levelProgressLabel, 1, 3 );

        WDynamicLabel label = new WDynamicLabel(() ->
                "Block Breaking Speed: " +
                Levelin.levels.calculatedMiningSpeed());
        root.add(label, 1, 4 );
    }
}
