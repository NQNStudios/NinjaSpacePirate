package com.punchline.NinjaSpacePirate.gameplay.entities.systems.spawn;

/**
 * Contains the information for creating a location.
 * @author Natman64
 *
 */
public class LocationTemplate {

	private String[] rows;
	private int difficulty;
	
	/**
	 * Constructs a LocationTemplate.
	 * @param rows
	 * @param difficulty How difficult this location is for the player to traverse.
	 */
	public LocationTemplate(String[] rows, int difficulty) {
		this.rows = rows;
		this.difficulty = difficulty;
	}
	
	/**
	 * @return The rows in this location. Can be overridden for different runtime behavior.
	 */
	public String[] getRows() {
		return rows;
	}
	
	/**
	 * @return How difficult this location is for the player to traverse.
	 */
	public int getDifficulty() {
		return difficulty;
	}
	
}
