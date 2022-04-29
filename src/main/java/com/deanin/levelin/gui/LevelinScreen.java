package com.deanin.levelin.gui;

import com.deanin.levelin.KeyChain;
import io.github.cottonmc.cotton.gui.GuiDescription;
import io.github.cottonmc.cotton.gui.client.CottonClientScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class LevelinScreen extends CottonClientScreen {
    public LevelinScreen(GuiDescription description) {
        super(description);
    }
    @Override
    public boolean shouldPause() {
        return false;
    }

    @Override
    public boolean keyPressed(int ch, int keyCode, int modifiers) {
        if (client.options.inventoryKey.matchesKey(ch, keyCode)) {
            this.close();
            return true;
        } else if (KeyChain.characterInfoButton.matchesKey(ch, keyCode)) {
            this.close();
            return true;
        } else {
            return super.keyPressed(ch, keyCode, modifiers);
        }
    }

}
