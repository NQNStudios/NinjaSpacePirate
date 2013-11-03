package com.natman.NinjaSpacePirate.gameplay.entities.processes.powerups;

import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.EntityWorld;
import com.natman.NinjaSpacePirate.gameplay.entities.processes.PowerupProcess;

public class SicknessPowerup extends PowerupProcess {
	
	public float elapsed;
	
	public SicknessPowerup(float duration, Entity e) {
		super(duration, e);
	}
	
	@Override
	public void update(EntityWorld world, float deltaTime) {
		super.update(world, deltaTime);
		
		elapsed += deltaTime;
	}

	@Override
	protected String getMessage() {
		return "Overdose";
	}

}
