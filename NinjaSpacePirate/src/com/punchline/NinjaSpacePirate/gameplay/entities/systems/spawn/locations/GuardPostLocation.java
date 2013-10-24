package com.punchline.NinjaSpacePirate.gameplay.entities.systems.spawn.locations;

import com.punchline.NinjaSpacePirate.gameplay.entities.systems.spawn.LocationTemplate;
import com.punchline.javalib.utils.Random;

public class GuardPostLocation extends LocationTemplate {

	private static Random r = new Random();
	
	public GuardPostLocation(int difficulty, int weight) {
		super(null, difficulty, weight);
	}

	@Override
	public String[] getRows() {
		String side = r.nextBoolean() ? "Left" : "Right";
		
		return new String[] {
				"HallSegment",
				"HallSegment",
				"HallSegment",
				side + "GuardPost0",
				side + "GuardPost1",
				side + "GuardPost2",
				"HallSegment",
				"HallSegment",
				"HallSegment"
		};
	}

}
