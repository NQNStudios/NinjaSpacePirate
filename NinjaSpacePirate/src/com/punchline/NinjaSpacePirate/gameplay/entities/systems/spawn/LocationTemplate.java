package com.punchline.NinjaSpacePirate.gameplay.entities.systems.spawn;

/**
 * Contains the information for creating a location.
 * @author Natman64
 *
 */
public class LocationTemplate {

	private String[] rows;
	
	/**
	 * Constructs a LocationTemplate.
	 * @param rows
	 */
	public LocationTemplate(String[] rows) {
		this.rows = rows;
	}
	
	/**
	 * @return The rows in this location. Can be overridden for different runtime behavior.
	 */
	public String[] getRows() {
		return rows;
	}
	
}
