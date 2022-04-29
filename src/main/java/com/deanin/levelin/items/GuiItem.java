package com.deanin.levelin.items;

import com.deanin.levelin.gui.CharacterInfoPage;
import com.deanin.levelin.gui.LevelinScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class GuiItem extends Item {
    public GuiItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
//        if (MinecraftClient.getInstance().equals(null)) {
//            return super.use(world, player, hand);
//        }
//        MinecraftClient.getInstance().setScreen(new LevelinScreen(new CharacterInfoPage()));

        return super.use(world, player, hand);
    }

}
