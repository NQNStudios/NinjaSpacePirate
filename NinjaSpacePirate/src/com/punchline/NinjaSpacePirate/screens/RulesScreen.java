package com.punchline.NinjaSpacePirate.screens;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.punchline.NinjaSpacePirate.screens.MenuButton.ButtonCallback;
import com.punchline.javalib.Game;

public class RulesScreen extends MenuScreen {

	public RulesScreen(Game game) {
		super(game, "TUTORIAL");
		
		MenuButton rule1 = new MenuButton(font, "Arrows to move");
		
		if (Gdx.app.getType() == ApplicationType.Android) {
			rule1.setText("Tilt to move");
		}
		
		MenuButton rule2 = new MenuButton(font, "Avoid being seen");
		MenuButton rule3 = new MenuButton(font, "");
		MenuButton exitButton = new MenuButton(font, "Back");
		
		exitButton.onTrigger = new ButtonCallback() {

			@Override
			public void invoke(Game game) {
				exit();
			}
			
		};
		
		buttons.add(rule1);
		buttons.add(rule2);
		buttons.add(rule3);
		buttons.add(exitButton);
	}
	
	

}
