package com.punchline.NinjaSpacePirate;

import com.punchline.NinjaSpacePirate.screens.GameplayScreen;
import com.punchline.javalib.Game;
import com.punchline.javalib.utils.Convert;

public class NinjaSpacePirate extends Game {

	@Override
	public void create() {
		title = "Stealth Pirate";
		
		landscapeMode = false;
		
		Convert.init(4f);
		
		super.create();
		
		getScreenManager().addScreen(new GameplayScreen(this));
	}

	@Override
	protected void loadSounds() {
		
	}
	
}
