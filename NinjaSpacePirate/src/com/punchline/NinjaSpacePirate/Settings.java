package com.punchline.NinjaSpacePirate;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.punchline.javalib.utils.SoundManager;

public final class Settings {

	private static final String PREFERENCES_KEY = "NinjaSpacePirateSettings";
	
	private static boolean soundOn = true;
	private static boolean musicOn = true;
	
	public static void loadSettings() {
		Preferences prefs = Gdx.app.getPreferences(PREFERENCES_KEY);
		
		setSoundEnabled(prefs.getBoolean("Sound", soundOn));
		setMusicEnabled(prefs.getBoolean("Music", musicOn));
	}

	public static void saveSettings() {
		Preferences prefs = Gdx.app.getPreferences(PREFERENCES_KEY);
		
		prefs.putBoolean("Sound", soundOn);
		prefs.putBoolean("Music", musicOn);
		
		prefs.flush();
	}
	
	public static boolean isSoundOn() {
		return soundOn;
	}

	public static boolean isMusicOn() {
		return musicOn;
	}

	public static void setSoundEnabled(boolean enabled) {
		soundOn = enabled;
		SoundManager.setSoundVolume(soundOn ? 1f : 0f);
	}
	
	public static void setMusicEnabled(boolean enabled) {
		musicOn = enabled;
		SoundManager.setMusicVolume(musicOn ? 1f : 0f);
	}
	
}
