package com.punchline.NinjaSpacePirate.screens;

import com.punchline.NinjaSpacePirate.screens.MenuButton.ButtonCallback;
import com.punchline.javalib.Game;

public class SettingsScreen extends MenuScreen {

	public SettingsScreen(Game game) {
		super(game, "Settings");
		
		MenuButton exitButton = new MenuButton(font, "Back");
		
		exitButton.onTrigger = new ButtonCallback() {

			@Override
			public void invoke(Game game) {
				exit();
			}
			
		};
		
		buttons.add(exitButton);
	}

}
