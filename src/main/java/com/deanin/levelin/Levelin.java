package com.deanin.levelin;

import com.deanin.levelin.enums.ExperienceSystem;
import com.deanin.levelin.events.BlockBreakEvents;
import com.deanin.levelin.items.GuiItem;
import com.deanin.levelin.player.Levels;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Levelin implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("levelin");
	/**
	 * Sets the experience system to toggle between vanilla or the custom Levelin system.
	 */
	public static ExperienceSystem experienceSystem = ExperienceSystem.Levelin;

	public static Levels levels;

//	public static Item GUI_ITEM = new GuiItem(new Item.Settings().group(ItemGroup.MISC));


	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");
		levels = new Levels(experienceSystem);
		BlockBreakEvents blockBreakEvents = new BlockBreakEvents();


//		Registry.register(Registry.ITEM, new Identifier("levelin", "gui_item"), GUI_ITEM);
	}
}