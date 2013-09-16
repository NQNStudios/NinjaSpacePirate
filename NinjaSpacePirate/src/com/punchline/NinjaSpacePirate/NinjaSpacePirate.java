package com.punchline.NinjaSpacePirate;

import com.punchline.NinjaSpacePirate.screens.GameplayScreen;
import com.punchline.javalib.Game;
import com.punchline.javalib.utils.Convert;

public class NinjaSpacePirate extends Game {

	@Override
	public void create() {
		Convert.init(8f);
		
		title = "Stealth Pirate";
		
		landscapeMode = false;
		
		super.create();
		
		getScreenManager().addScreen(new GameplayScreen(this));
	}

	@Override
	protected void loadSounds() {
		
	}
	
}
