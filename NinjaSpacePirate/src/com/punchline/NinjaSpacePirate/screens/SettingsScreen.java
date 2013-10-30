package com.punchline.NinjaSpacePirate.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.punchline.NinjaSpacePirate.Settings;
import com.punchline.NinjaSpacePirate.screens.MenuButton.ButtonCallback;
import com.punchline.javalib.Game;

public class SettingsScreen extends MenuScreen {

	private MenuButton soundButton;
	private MenuButton musicButton;
	private MenuButton smallScreenButton;
	
	public SettingsScreen(Game game) {
		super(game, "Settings");
		
		buttonScale = 0.8f;
		
		MenuButton exitButton = new MenuButton(font, "Back");
		soundButton = new MenuButton(font, "");
		musicButton = new MenuButton(font, "");
		smallScreenButton = new MenuButton(font, "");
		
		soundButton.onTrigger = new ButtonCallback() {

			@Override
			public void invoke(Game game) {
				Settings.setSoundEnabled(!Settings.isSoundOn());
			}
			
		};
		
		musicButton.onTrigger = new ButtonCallback() {

			@Override
			public void invoke(Game game) {
				Settings.setMusicEnabled(!Settings.isMusicOn());
			}
			
		};
		
		smallScreenButton.onTrigger = new ButtonCallback() {

			@Override
			public void invoke(Game game) {
				Settings.setSmallScreen(!Settings.isSmallScreen());
			}

		};
		
		exitButton.onTrigger = new ButtonCallback() {

			@Override
			public void invoke(Game game) {
				exit();
			}
			
		};
		
		buttons.add(soundButton);
		//buttons.add(musicButton);
		
		if (Gdx.app.getType() != ApplicationType.Android) {
			buttons.add(smallScreenButton);
		}
		
		buttons.add(new MenuButton(font, ""));
		buttons.add(exitButton);
	}

	@Override
	public void render(float delta) {
		soundButton.setText("Sound: " + (Settings.isSoundOn() ? "On" : "Off"));
		musicButton.setText("Music: " + (Settings.isMusicOn() ? "On" : "Off"));
		smallScreenButton.setText("Window Size: " + (Settings.isSmallScreen() ? "Small" : "Normal"));
		
		super.render(delta);
	}
	
	

}
