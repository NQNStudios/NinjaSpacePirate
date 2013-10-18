package com.punchline.NinjaSpacePirate.screens;

import com.punchline.NinjaSpacePirate.screens.MenuButton.ButtonCallback;
import com.punchline.javalib.Game;

public class PauseScreen extends MenuScreen {

	public PauseScreen(Game game) {
		super(game, "PAUSED");
		
		MenuButton resumeButton = new MenuButton(font, "Resume");
		MenuButton quitButton = new MenuButton(font, "Quit");
		
		resumeButton.onTrigger = new ButtonCallback() {

			@Override
			public void invoke(Game game) {
				exit();
			}
			
		};
		
		quitButton.onTrigger = new ButtonCallback() {

			@Override
			public void invoke(Game game) {
				exit();
				game.getScreenManager().closeActiveScreen();
			}
			
		};
		
		buttons.add(resumeButton);
		buttons.add(quitButton);
	}

}
