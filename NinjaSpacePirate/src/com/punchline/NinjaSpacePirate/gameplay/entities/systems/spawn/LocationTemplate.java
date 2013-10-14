package com.punchline.NinjaSpacePirate.gameplay.entities.systems.spawn;

import com.punchline.javalib.entities.EntityWorld;

/**
 * Contains the information for creating a location.
 * @author Natman64
 *
 */
public class LocationTemplate {

	private String[] rows;
	private int difficulty;
	private int weight;
	
	/**
	 * Constructs a LocationTemplate.
	 * @param rows
	 * @param difficulty How difficult this location is for the player to traverse.
	 * @param weight This location's weight when being selected for spawning
	 */
	public LocationTemplate(String[] rows, int difficulty, int weight) {
		this.rows = rows;
		this.difficulty = difficulty;
		this.weight = weight;
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
	
	/**
	 * @return This location's weight when being selected for spawning.
	 */
	public int getWeight() {
		return weight;
	}
	
	/**
	 * Called when this location is queued for spawning.
	 * @param world
	 */
	public void onQueue(EntityWorld world) {
		
	}
	
}
