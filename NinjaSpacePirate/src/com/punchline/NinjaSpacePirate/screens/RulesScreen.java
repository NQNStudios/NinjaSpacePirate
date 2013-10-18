package com.punchline.NinjaSpacePirate.screens;

import com.punchline.NinjaSpacePirate.screens.MenuButton.ButtonCallback;
import com.punchline.javalib.Game;

public class RulesScreen extends MenuScreen {

	public RulesScreen(Game game) {
		super(game, "TUTORIAL");
		
		MenuButton rule1 = new MenuButton(font, "Tilt to move");
		MenuButton rule2 = new MenuButton(font, "Avoid enemies");
		MenuButton exitButton = new MenuButton(font, "Back");
		
		exitButton.onTrigger = new ButtonCallback() {

			@Override
			public void invoke(Game game) {
				exit();
			}
			
		};
		
		buttons.add(rule1);
		buttons.add(rule2);
		buttons.add(exitButton);
	}
	
	

}
