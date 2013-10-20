package com.punchline.NinjaSpacePirate.gameplay.entities.processes.powerups;

import com.punchline.NinjaSpacePirate.gameplay.entities.processes.PowerupProcess;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.EntityWorld;
import com.punchline.javalib.entities.components.render.AnimatedSprite;

public class GhostPowerup extends PowerupProcess {

	public GhostPowerup(float duration, Entity e) {
		super(duration, e);
	}

	@Override
	protected void startEffect(EntityWorld world) {
		e.addComponent(this);
		
		AnimatedSprite sprite = e.getComponent(AnimatedSprite.class);
		sprite.setState("Ghost", true);
	}

	@Override
	protected void endEffect(EntityWorld world) {
		e.removeComponent(this);
		
		AnimatedSprite sprite = e.getComponent(AnimatedSprite.class);
		sprite.setState("Run", true);
	}

	@Override
	protected String getMessage() {
		return "Ghost";
	}

}
