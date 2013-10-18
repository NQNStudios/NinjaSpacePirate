package com.punchline.NinjaSpacePirate;

import com.badlogic.gdx.Gdx;
import com.punchline.NinjaSpacePirate.gameplay.Stats;
import com.punchline.NinjaSpacePirate.screens.GameplayScreen;
import com.punchline.NinjaSpacePirate.screens.MainMenuScreen;
import com.punchline.javalib.Game;
import com.punchline.javalib.utils.Convert;
import com.punchline.javalib.utils.LogManager;

/**
 * The game class.
 * @author Natman64
 * @created Oct 17, 2013
 */
public class NinjaSpacePirate extends Game {
	
	@Override
	public void create() {
		Convert.init(8f);
		LogManager.init(Gdx.app);
		Stats.init();
		
		title = "Ninja Space Pirate";
		
		landscapeMode = false;
		
		super.create();
		
		getScreenManager().addScreen(new MainMenuScreen(this));
	}
	
	@Override
	public void dispose() {
		super.dispose();
		
		Stats.saveStats();
	}

	@Override
	protected void loadSounds() {
		
	}
	
}
