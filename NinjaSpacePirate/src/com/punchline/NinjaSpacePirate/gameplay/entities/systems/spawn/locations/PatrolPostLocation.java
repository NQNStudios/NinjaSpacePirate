package com.punchline.NinjaSpacePirate.gameplay.entities.systems.spawn.locations;

import com.punchline.NinjaSpacePirate.gameplay.entities.systems.spawn.LocationTemplate;

public class PatrolPostLocation extends LocationTemplate {

	public PatrolPostLocation(int difficulty, int weight) {
		super(null, difficulty, weight);
	}

	@Override
	public String[] getRows() {
		return new String[] {
				"DualGuardPost0",
				"PatrolPost1",
				"DualGuardPost2"
		};
	}
	
	

}
