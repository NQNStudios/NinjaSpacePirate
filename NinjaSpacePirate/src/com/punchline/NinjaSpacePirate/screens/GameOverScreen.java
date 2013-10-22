package com.punchline.NinjaSpacePirate.screens;

import com.punchline.NinjaSpacePirate.gameplay.GameScore;
import com.punchline.NinjaSpacePirate.screens.MenuButton.ButtonCallback;
import com.punchline.javalib.Game;
import com.punchline.javalib.utils.Display;

public class GameOverScreen extends MenuScreen {

	private GameScore score;
	
	public GameOverScreen(Game game, GameScore score) {
		super(game, "Game Over");
		
		this.score = score;
		
		titleY = 7 * Display.getPreferredHeight() / 8;
		buttonY = 2 * Display.getPreferredHeight() / 3;
		buttonScale = 0.6f;
		
		MenuButton retryButton = new MenuButton(font, "Retry");
		MenuButton backButton = new MenuButton(font, "Back");
		
		retryButton.onTrigger = new ButtonCallback() {

			@Override
			public void invoke(Game game) {
				game.getScreenManager().closeActiveScreen();
				game.getScreenManager().addScreen(new GameplayScreen(game));
			}
			
		};
		
		backButton.onTrigger = new ButtonCallback() {

			@Override
			public void invoke(Game game) {
				game.getScreenManager().closeActiveScreen();
			}
			
		};
		
		buttons.add(retryButton);
		buttons.add(backButton);
	}

}
