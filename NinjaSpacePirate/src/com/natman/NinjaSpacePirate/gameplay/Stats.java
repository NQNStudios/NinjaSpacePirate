package com.natman.NinjaSpacePirate.gameplay;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.natman.NinjaSpacePirate.gameplay.stats.DistanceStat;
import com.natman.NinjaSpacePirate.gameplay.stats.IntStat;
import com.natman.NinjaSpacePirate.gameplay.stats.TimeStat;

/**
 * Static class containing 
 * @author Natman64
 * @created Oct 17, 2013
 */
@SuppressWarnings("rawtypes")
public final class Stats {

	private static final String PREFERENCES_KEY = "NinjaSpacePirateStats";
	
	private static LinkedHashMap<String, Stat> records = new LinkedHashMap<String, Stat>();
	private static LinkedHashMap<String, Stat> stats = new LinkedHashMap<String, Stat>();
	
	
	/**
	 * Initializes the game's stats.
	 */
	public static void init() {
		records.put("High Score", new IntStat(0));
		records.put("Farthest Run", new DistanceStat(0f));
		
		stats.put("Deaths", new IntStat(0));
		stats.put("Times Seen", new IntStat(0));
		stats.put("Coins Collected", new IntStat(0));
		stats.put("Potions Drunk", new IntStat(0));
		stats.put("Enemies Killed", new IntStat(0));
		stats.put("Time Played", new TimeStat(0f));
		
		loadStats();
	}
	
	/**
	 * Loads saved stats.
	 */
	public static void loadStats() {
		Preferences prefs = Gdx.app.getPreferences(PREFERENCES_KEY);
		
		Iterator<Entry<String, Stat>> it = records.entrySet().iterator();
		
		while (it.hasNext()) {
			Entry<String, Stat> entry = it.next();
			String key = entry.getKey();
			Stat value = entry.getValue();
			
			value.parseValue(prefs.getString(key, value.getValue().toString()));
		}
		
		it = stats.entrySet().iterator();
		
		while (it.hasNext()) {
			Entry<String, Stat> entry = it.next();
			String key = entry.getKey();
			Stat value = entry.getValue();
			
			value.parseValue(prefs.getString(key, value.getValue().toString()));
		}
	}
	
	/**
	 * Saves stats.
	 */
	public static void saveStats() {
		Preferences prefs = Gdx.app.getPreferences(PREFERENCES_KEY);
		
		Iterator<Entry<String, Stat>> it = records.entrySet().iterator();
		
		while (it.hasNext()) {
			Entry<String, Stat> entry = it.next();
			String key = entry.getKey();
			Stat value = entry.getValue();
			
			prefs.putString(key, value.getValue().toString());
		}
		
		it = stats.entrySet().iterator();
		
		while (it.hasNext()) {
			Entry<String, Stat> entry = it.next();
			String key = entry.getKey();
			Stat value = entry.getValue();
			
			prefs.putString(key, value.getValue().toString());
		}
		
		prefs.flush();
	}
	
	/**
	 * @param key
	 * @return A record by the given key.
	 */
	public static Stat getRecord(String key) {
		return records.get(key);
	}
	
	/**
	 * @param key
	 * @return A stat by the given key.
	 */
	public static Stat getStat(String key) {
		return stats.get(key);
	}
	
	/**
	 * @return The records map.
	 */
	public static Map<String, Stat> getRecords() {
		return records;
	}
	
	/**
	 * @return The stats map.
	 */
	public static Map<String, Stat> getStats() {
		return stats;
	}
	
}
