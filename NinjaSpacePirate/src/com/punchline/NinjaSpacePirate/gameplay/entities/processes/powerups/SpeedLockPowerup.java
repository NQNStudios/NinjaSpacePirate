package com.punchline.NinjaSpacePirate.gameplay.entities.processes.powerups;

import com.punchline.NinjaSpacePirate.gameplay.entities.processes.PowerupProcess;
import com.punchline.javalib.entities.Entity;

/**
 * Locks the player's speed controls.
 * @author Natman64
 * @created Oct 17, 2013
 */
public class SpeedLockPowerup extends PowerupProcess {

	/**
	 * Creates a SpeedLockPowerup
	 * @param duration
	 * @param e
	 */
	public SpeedLockPowerup(float duration, Entity e) {
		super(duration, e);
	}

	@Override
	protected String getMessage() {
		return "Speed Lock";
	}

}
