package com.natman.NinjaSpacePirate.gameplay.entities.processes;

import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.EntityWorld;
import com.lostcode.javalib.entities.components.Component;
import com.lostcode.javalib.entities.components.ComponentManager;
import com.lostcode.javalib.entities.processes.Process;
import com.lostcode.javalib.entities.processes.ProcessState;

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
		if (endState == ProcessState.ABORTED) {
			endEffect(world);
		}
	}
	
	@Override
	public void onAdd(ComponentManager container) { 

	}

	@Override
	public void onRemove(ComponentManager container) { }

	/** Applies the powerup effect to {@link #e}. */
	protected void startEffect(EntityWorld world) {
		e.addComponent(this);
	}
	
	/** Removes the powerup effect from {@link #e}. */
	protected void endEffect(EntityWorld world) {
		e.removeComponent(this);
	}

	/** The message this powerup shows the player. */
	protected abstract String getMessage();
	
}
