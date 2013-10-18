package com.punchline.NinjaSpacePirate.gameplay.entities.processes.powerups;

import com.punchline.NinjaSpacePirate.gameplay.entities.processes.PowerupProcess;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.EntityWorld;
import com.punchline.javalib.entities.components.Component;
import com.punchline.javalib.entities.components.ComponentManager;

/**
 * Reverses control of the player.
 * @author Natman64
 * @created Oct 17, 2013
 */
public class ReverseControlPowerup extends PowerupProcess implements Component {

	/**
	 * Constructs a ReverseControlPowerup
	 * @param duration
	 * @param e
	 */
	public ReverseControlPowerup(float duration, Entity e) {
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
		 return "Reverse";
	}

	@Override
	public void onAdd(ComponentManager container) {
		
	}

	@Override
	public void onRemove(ComponentManager container) {
		
	}

}
