package com.deanin.levelin;

import com.deanin.levelin.config.ConfigRegister;
import com.deanin.levelin.enums.ExperienceSystem;
import com.deanin.levelin.events.BlockBreakEvents;
import com.deanin.levelin.player.Levels;
import com.deanin.levelin.player.PlayerCharacter;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.deanin.levelin.Manager.createManager;

public class Levelin implements ModInitializer {

	public static final String MOD_ID = "levelin";
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Initializing Levelin!");
		ConfigRegister.register();
		createManager();
	}
}