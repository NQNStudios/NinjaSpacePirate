package com.punchline.NinjaSpacePirate.gameplay.entities.processes.powerups;

import com.punchline.NinjaSpacePirate.gameplay.entities.components.SpeedLock;
import com.punchline.NinjaSpacePirate.gameplay.entities.processes.PowerupProcess;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.EntityWorld;

/**
 * Locks the player's speed controls.
 * @author Natman64
 * @created Oct 17, 2013
 */
public class SpeedLockPowerup extends PowerupProcess {

	private SpeedLock lock = new SpeedLock();
	
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
		e.addComponent(lock);
	}

	@Override
	protected void endEffect(EntityWorld world) {
		e.removeComponent(lock);
	}

	@Override
	protected String getMessage() {
		return "Speed Lock";
	}

}
