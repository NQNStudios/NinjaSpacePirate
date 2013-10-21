package com.punchline.NinjaSpacePirate.gameplay.entities.processes.powerups;

import com.punchline.NinjaSpacePirate.gameplay.entities.processes.PowerupProcess;
import com.punchline.javalib.entities.Entity;

/**
 * Reverses control of the player.
 * @author Natman64
 * @created Oct 17, 2013
 */
public class ReverseControlPowerup extends PowerupProcess {

	/**
	 * Constructs a ReverseControlPowerup
	 * @param duration
	 * @param e
	 */
	public ReverseControlPowerup(float duration, Entity e) {
		super(duration, e);
	}

	@Override
	protected String getMessage() {
		 return "Confusion";
	}

}
