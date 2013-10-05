package com.punchline.NinjaSpacePirate;

import com.badlogic.gdx.Gdx;
import com.punchline.NinjaSpacePirate.screens.GameplayScreen;
import com.punchline.javalib.Game;
import com.punchline.javalib.utils.Convert;
import com.punchline.javalib.utils.LogManager;

public class NinjaSpacePirate extends Game {

	@Override
	public void create() {
		Convert.init(8f);
		LogManager.init(Gdx.app);
		
		title = "Stealth Pirate";
		
		landscapeMode = false;
		
		backgroundRed = 0.125f;
		backgroundGreen = 0.125f;
		backgroundBlue = 0.125f;
		
		super.create();
		
		getScreenManager().addScreen(new GameplayScreen(this));
	}

	@Override
	protected void loadSounds() {
		
	}
	
}
