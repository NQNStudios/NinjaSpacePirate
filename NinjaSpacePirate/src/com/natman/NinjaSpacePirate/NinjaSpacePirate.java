package com.natman.NinjaSpacePirate;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.lostcode.javalib.Game;
import com.lostcode.javalib.utils.Convert;
import com.lostcode.javalib.utils.LogManager;
import com.lostcode.javalib.utils.SoundManager;
import com.lostcode.javalib.utils.LogManager.LogType;
import com.natman.NinjaSpacePirate.gameplay.Stats;
import com.natman.NinjaSpacePirate.screens.MainMenuScreen;

/**
 * The game class.
 * @author Natman64
 * @created Oct 17, 2013
 */
public class NinjaSpacePirate extends Game {
	
	@Override
	public void create() {
		Convert.init(8f);
		LogManager.init(Gdx.app, LogType.ERROR);
		Stats.init();
		
		title = "Ninja Space Pirate";
		
		landscapeMode = false;
		
		super.create();
		
		Settings.loadSettings();
		
		getScreenManager().addScreen(new MainMenuScreen(this));
	}
	
	@Override
	public void dispose() {
		super.dispose();
		
		Stats.saveStats();
		Settings.saveSettings();
	}

	@Override
	protected void loadSounds() {
		try {
			SoundManager.loadContent(Gdx.files.internal("data/audio.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
