package com.punchline.NinjaSpacePirate.gameplay.entities.processes.powerups;

import com.punchline.NinjaSpacePirate.gameplay.entities.processes.PowerupProcess;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.EntityWorld;

public class InvulnPowerup extends PowerupProcess {

	public InvulnPowerup(float duration, Entity e) {
		super(duration, e);
	}

	@Override
	protected void startEffect(EntityWorld world) {
		e.addComponent(this);
	}

	@Override
	protected void endEffect(EntityWorld world) {
		e.removeComponent(this);
	}

	@Override
	protected String getMessage() {
		return "Strength";
	}

}
