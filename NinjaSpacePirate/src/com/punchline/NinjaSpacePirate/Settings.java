package com.punchline.NinjaSpacePirate;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.punchline.javalib.utils.SoundManager;

public final class Settings {

	private static final String PREFERENCES_KEY = "NinjaSpacePirateSettings";
	private static final float SMALL_SCREEN_RATIO = .66f;
	
	private static boolean soundOn = true;
	private static boolean musicOn = true;
	private static boolean smallScreen = false;
	
	public static void loadSettings() {
		Preferences prefs = Gdx.app.getPreferences(PREFERENCES_KEY);
		
		setSoundEnabled(prefs.getBoolean("Sound", soundOn));
		setMusicEnabled(prefs.getBoolean("Music", musicOn));
		setSmallScreen(prefs.getBoolean("SmallScreen", smallScreen));
	}

	public static void saveSettings() {
		Preferences prefs = Gdx.app.getPreferences(PREFERENCES_KEY);
		
		prefs.putBoolean("Sound", soundOn);
		prefs.putBoolean("Music", musicOn);
		prefs.putBoolean("SmallScreen", smallScreen);
		
		prefs.flush();
	}
	
	public static boolean isSoundOn() {
		return soundOn;
	}

	public static boolean isMusicOn() {
		return musicOn;
	}

	public static boolean isSmallScreen() {
		return smallScreen;
	}
	
	public static void setSoundEnabled(boolean enabled) {
		soundOn = enabled;
		SoundManager.setSoundVolume(soundOn ? 1f : 0f);
	}
	
	public static void setMusicEnabled(boolean enabled) {
		musicOn = enabled;
		SoundManager.setMusicVolume(musicOn ? 1f : 0f);
	}
	
	public static void setSmallScreen(boolean enabled) {
		smallScreen = enabled;
		
		if (Gdx.app.getType() != ApplicationType.Android) {
			int width = 480;
			int height = 800;
			
			if (smallScreen) {
				width *= SMALL_SCREEN_RATIO;
				height *= SMALL_SCREEN_RATIO;
			}
			
			Gdx.graphics.setDisplayMode(width, height, false);
		}
	}
	
}
