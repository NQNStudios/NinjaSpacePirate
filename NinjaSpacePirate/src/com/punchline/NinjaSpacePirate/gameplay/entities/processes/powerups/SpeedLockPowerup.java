package com.punchline.NinjaSpacePirate.gameplay.entities.processes.powerups;

import com.punchline.NinjaSpacePirate.gameplay.entities.processes.PowerupProcess;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.EntityWorld;
import com.punchline.javalib.entities.components.Component;
import com.punchline.javalib.entities.components.ComponentManager;

/**
 * Locks the player's speed controls.
 * @author Natman64
 * @created Oct 17, 2013
 */
public class SpeedLockPowerup extends PowerupProcess implements Component {

	/**
	 * Creates a SpeedLockPowerup
	 * @param duration
	 * @param e
	 */
	public SpeedLockPowerup(float duration, Entity e) {
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
		return "Speed Lock";
	}

	@Override
	public void onAdd(ComponentManager container) {
		
	}

	@Override
	public void onRemove(ComponentManager container) {
		
	}

}
