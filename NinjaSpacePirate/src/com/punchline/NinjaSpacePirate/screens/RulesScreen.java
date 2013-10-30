package com.punchline.NinjaSpacePirate.screens;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.punchline.NinjaSpacePirate.screens.MenuButton.ButtonCallback;
import com.punchline.javalib.Game;

public class RulesScreen extends MenuScreen {

	public RulesScreen(Game game) {
		super(game, "TUTORIAL");
		
		MenuButton rule1 = new MenuButton(font, "Arrow Keys: ");
		
		if (Gdx.app.getType() == ApplicationType.Android) {
			rule1.setText("Tilt Device:");
		}
		
		MenuButton rule2 = new MenuButton(font, "move and control speed");
		MenuButton rule3 = new MenuButton(font, "Avoid being seen");
		MenuButton exitButton = new MenuButton(font, "Back");
		
		exitButton.onTrigger = new ButtonCallback() {

			@Override
			public void invoke(Game game) {
				exit();
			}
			
		};
		
		buttons.add(rule1);
		buttons.add(rule2);
		buttons.add(new MenuButton(font, ""));
		buttons.add(rule3);
		buttons.add(new MenuButton(font, ""));
		buttons.add(exitButton);
		
		buttonScale = 0.65f;
	}
	
	

}
