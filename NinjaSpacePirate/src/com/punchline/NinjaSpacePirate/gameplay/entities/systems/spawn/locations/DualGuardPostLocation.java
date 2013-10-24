package com.punchline.NinjaSpacePirate.gameplay.entities.systems.spawn.locations;

import com.punchline.NinjaSpacePirate.gameplay.entities.systems.spawn.LocationTemplate;

public class DualGuardPostLocation extends LocationTemplate {

	public DualGuardPostLocation(int difficulty, int weight) {
		super(null, difficulty, weight);
	}

	@Override
	public String[] getRows() {

		return new String[] {
				"HallSegment",
				"HallSegment",
				"HallSegment",
				"DualGuardPost0",
				"DualGuardPost1",
				"DualGuardPost2",
				"HallSegment",
				"HallSegment",
				"HallSegment"
		};
	}

}
