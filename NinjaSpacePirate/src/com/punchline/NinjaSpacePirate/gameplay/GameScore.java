package com.punchline.NinjaSpacePirate.gameplay;

/**
 * Contains the player's score information.
 * @author Natman64
 * @created Oct 21, 2013
 */
public class GameScore {

	public static final int POINTS_PER_METER = 1;
	public static final int POINTS_PER_COIN = 25;
	public static final int POINTS_PER_POTION = 50;
	public static final int POINTS_PER_KILL = 30;
	public static final int POINTS_PER_TIME_SEEN = -10;
	
	public int meters;
	public int coins;
	public int potions;
	public int kills;
	
	public int meterPoints() {
		return meters * POINTS_PER_METER;
	}
	
	public int coinPoints() {
		return coins * POINTS_PER_COIN;
	}
	
	public int potionPoints() {
		return potions * POINTS_PER_POTION;
	}
	
	public int killPoints() {
		return kills * POINTS_PER_KILL;
	}
	
	public int totalPoints() {
		return meterPoints()
				+ coinPoints()
				+ potionPoints()
				+ killPoints();
	}
	
}
