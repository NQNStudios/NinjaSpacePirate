package com.punchline.NinjaSpacePirate.gameplay.entities.processes.powerups;

import com.punchline.NinjaSpacePirate.gameplay.entities.processes.PowerupProcess;
import com.punchline.javalib.entities.Entity;

public class InvulnPowerup extends PowerupProcess {

	public InvulnPowerup(float duration, Entity e) {
		super(duration, e);
	}

	@Override
	protected String getMessage() {
		return "Strength";
	}

}
