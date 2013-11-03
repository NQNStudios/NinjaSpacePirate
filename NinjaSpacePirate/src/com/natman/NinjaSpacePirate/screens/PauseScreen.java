package com.natman.NinjaSpacePirate.screens;

import com.lostcode.javalib.Game;
import com.natman.NinjaSpacePirate.screens.MenuButton.ButtonCallback;

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
