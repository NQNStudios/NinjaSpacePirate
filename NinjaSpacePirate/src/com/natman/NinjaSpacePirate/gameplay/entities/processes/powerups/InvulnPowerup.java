package com.natman.NinjaSpacePirate.gameplay.entities.processes.powerups;

import com.lostcode.javalib.entities.Entity;
import com.natman.NinjaSpacePirate.gameplay.entities.processes.PowerupProcess;

public class InvulnPowerup extends PowerupProcess {

	public InvulnPowerup(float duration, Entity e) {
		super(duration, e);
	}

	@Override
	protected String getMessage() {
		return "Strength";
	}

}
