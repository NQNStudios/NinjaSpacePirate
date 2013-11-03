package com.natman.NinjaSpacePirate.gameplay.entities.processes;

import com.badlogic.gdx.math.Vector2;
import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.EntityWorld;
import com.lostcode.javalib.entities.components.ComponentManager;
import com.lostcode.javalib.entities.components.generic.Health;
import com.lostcode.javalib.entities.components.physical.Body;
import com.lostcode.javalib.entities.components.physical.Transform;
import com.lostcode.javalib.entities.events.processes.EndProcessCallback;
import com.lostcode.javalib.entities.processes.Process;
import com.lostcode.javalib.entities.processes.ProcessState;

/**
 * Sucks an entity toward a point. When added to an entity, will automatically end.
 * @author Natman64
 * @created Oct 12, 2013
 */
public class VacuumProcess extends Process implements Transform {

	private EntityWorld world;
	private Entity e;
	private Vector2 origin;
	private float force;
	
	/**
	 * Constructs a vacuum process.
	 * @param world
	 * @param e The entity to pull.
	 * @param origin The point to pull towards
	 * @param force The force to apply every update
	 */
	public VacuumProcess(EntityWorld world, Entity e, Vector2 origin, float force) {
		this.world = world;
		this.e = e;
		this.origin = origin;
		this.force = force;
		
		e.onDeleted.addCallback(this, new EndProcessCallback(this, ProcessState.SUCCEEDED));
		
		Health health = e.getComponent(Health.class);
		health.onDeath.addCallback(this, new EndProcessCallback(this, ProcessState.SUCCEEDED));
	}
	
	@Override
	public void update(EntityWorld world, float deltaTime) {
		//apply a linear impulse to the entity's body
		Body b = e.getComponent(Body.class);
		
		Vector2 impulse = origin.cpy().sub(b.getPosition());
		impulse.nor();
		impulse.scl(force);
		
		b.getBody().applyLinearImpulse(impulse, b.getPosition(), true);
	}

	@Override
	public void onEnd(EntityWorld world, ProcessState endState) {
		e.onDeleted.removeCallback(this);
		
		if (e.hasComponent(Health.class)) {
			Health health = e.getComponent(Health.class);
			health.onDeath.removeCallback(this);
		}
	}

	@Override
	public void onAdd(ComponentManager container) {
		world.getProcessManager().attach(this);
	}

	@Override
	public void onRemove(ComponentManager container) {
		end(ProcessState.ABORTED);
	}

	@Override
	public Vector2 getPosition() {
		return origin;
	}

	@Override
	public void setPosition(Vector2 position) {
		origin = position;
	}

	@Override
	public float getRotation() {
		return 0f;
	}

	@Override
	public void setRotation(float rotation) {
		
	}

	@Override
	public Vector2 getOrigin() {
		return new Vector2();
	}

}
