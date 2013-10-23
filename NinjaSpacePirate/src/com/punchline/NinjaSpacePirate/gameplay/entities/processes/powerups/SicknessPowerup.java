package com.punchline.NinjaSpacePirate.gameplay.entities.processes.powerups;

import com.punchline.NinjaSpacePirate.gameplay.entities.processes.PowerupProcess;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.EntityWorld;

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
