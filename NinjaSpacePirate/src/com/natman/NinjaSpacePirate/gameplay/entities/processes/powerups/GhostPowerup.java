package com.natman.NinjaSpacePirate.gameplay.entities.processes.powerups;

import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.EntityWorld;
import com.lostcode.javalib.entities.components.render.AnimatedSprite;
import com.natman.NinjaSpacePirate.gameplay.entities.processes.PowerupProcess;

public class GhostPowerup extends PowerupProcess {

	public GhostPowerup(float duration, Entity e) {
		super(duration, e);
	}

	@Override
	protected void startEffect(EntityWorld world) {		
		super.startEffect(world);
		
		AnimatedSprite sprite = e.getComponent(AnimatedSprite.class);
		sprite.setState("Ghost", true);
	}

	@Override
	protected void endEffect(EntityWorld world) {
		super.endEffect(world);
		
		AnimatedSprite sprite = e.getComponent(AnimatedSprite.class);
		sprite.setState("Run", true);
	}

	@Override
	protected String getMessage() {
		return "Ghost";
	}

}
