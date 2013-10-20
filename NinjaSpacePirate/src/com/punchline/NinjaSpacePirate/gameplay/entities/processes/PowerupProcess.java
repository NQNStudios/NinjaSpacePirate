package com.punchline.NinjaSpacePirate.gameplay.entities.processes;

import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.EntityWorld;
import com.punchline.javalib.entities.components.Component;
import com.punchline.javalib.entities.components.ComponentManager;
import com.punchline.javalib.entities.processes.Process;
import com.punchline.javalib.entities.processes.ProcessState;

/**
 * Base class for powerup processes
 * @author Natman64
 * @created Oct 17, 2013
 */
public abstract class PowerupProcess extends Process implements Component {

	private boolean firstUpdate = true;
	private float duration;

	/** The entity that this powerup boosts. */
	protected Entity e;
	
	/**
	 * Constructs a PowerupProcess
	 * @param duration The duration of the effect
	 * @param e The entity that this powerup boosts.
	 */
	public PowerupProcess(float duration, Entity e) {
		this.duration = duration;
		this.e = e;
	}
	
	@Override
	public void update(EntityWorld world, float deltaTime) {
		if (firstUpdate) {
			startEffect(world);
			firstUpdate = false;
			
			world.createEntity("PotionMessage", getMessage(), duration);
		}
		
		duration -= deltaTime;
		
		if (duration <= 0) {
			endEffect(world);
			end(ProcessState.SUCCEEDED);
		}
	}
	
	@Override
	public void onEnd(EntityWorld world, ProcessState endState) {

	}
	
	@Override
	public void onAdd(ComponentManager container) { }

	@Override
	public void onRemove(ComponentManager container) { }

	/** Applies the powerup effect to {@link #e}. */
	protected abstract void startEffect(EntityWorld world);
	
	/** Removes the powerup effect from {@link #e}. */
	protected abstract void endEffect(EntityWorld world);

	/** The message this powerup shows the player. */
	protected abstract String getMessage();
	
}
