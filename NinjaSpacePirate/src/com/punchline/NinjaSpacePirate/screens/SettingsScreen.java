package com.punchline.NinjaSpacePirate.screens;

import com.punchline.NinjaSpacePirate.Settings;
import com.punchline.NinjaSpacePirate.screens.MenuButton.ButtonCallback;
import com.punchline.javalib.Game;

public class SettingsScreen extends MenuScreen {

	private MenuButton soundButton;
	private MenuButton musicButton;
	
	public SettingsScreen(Game game) {
		super(game, "Settings");
		
		MenuButton exitButton = new MenuButton(font, "Back");
		soundButton = new MenuButton(font, "");
		musicButton = new MenuButton(font, "");
		
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
		
		exitButton.onTrigger = new ButtonCallback() {

			@Override
			public void invoke(Game game) {
				exit();
			}
			
		};
		
		buttons.add(soundButton);
		buttons.add(musicButton);
		buttons.add(new MenuButton(font, ""));
		buttons.add(exitButton);
	}

	@Override
	public void render(float delta) {
		soundButton.setText("Sound: " + (Settings.isSoundOn() ? "On" : "Off"));
		musicButton.setText("Music: " + (Settings.isMusicOn() ? "On" : "Off"));
		
		super.render(delta);
	}
	
	

}
