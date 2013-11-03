package com.natman.NinjaSpacePirate.gameplay.entities.processes.powerups;

import com.lostcode.javalib.entities.Entity;
import com.natman.NinjaSpacePirate.gameplay.entities.processes.PowerupProcess;

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
