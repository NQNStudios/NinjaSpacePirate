package com.punchline.NinjaSpacePirate.gameplay.entities.systems.spawn.locations;

import com.punchline.NinjaSpacePirate.gameplay.entities.systems.spawn.LocationTemplate;
import com.punchline.javalib.utils.Random;

/**
 * Template for a hull breach obstacle.
 * @author Natman64
 * @created Oct 12, 2013
 */
public class HullBreachLocation extends LocationTemplate {

	private static final int ROWS = 7;
	private static final int PADDING_ROWS = 4;
	
	private Random r = new Random();
	
	/**
	 * Constructs a HullBreachLocation.
	 */
	public HullBreachLocation() {
		super(null, 0);
	}

	@Override
	public String[] getRows() {
		String prefix;
		
		String[] rows = new String[ROWS + PADDING_ROWS];
		
		if (r.nextBoolean()) {
			//left side
			prefix = "Left";
		} else {
			//right side
			prefix = "Right";
		}
		
		for (int i = 0; i < ROWS; i++) {
			rows[i] = prefix + "Breach" + i;
		}
		
		for (int i = ROWS; i < ROWS + PADDING_ROWS; i++) {
			rows[i] = "HallSegment";
		}
		
		return rows;
	}

	@Override
	public int getDifficulty() {
		return 1;
	}

	
	
}
