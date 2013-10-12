package com.punchline.NinjaSpacePirate.gameplay.entities.systems.spawn.locations;

import com.punchline.NinjaSpacePirate.gameplay.entities.systems.spawn.LocationTemplate;

/**
 * Template for a hull breach obstacle.
 * @author Natman64
 * @created Oct 12, 2013
 */
public class HullBreachLocation extends LocationTemplate {

	/**
	 * Constructs a HullBreachLocation.
	 */
	public HullBreachLocation() {
		super(null, 0);
	}

	@Override
	public String[] getRows() {
		return null;
	}

	@Override
	public int getDifficulty() {
		return 3;
	}

	
	
}
