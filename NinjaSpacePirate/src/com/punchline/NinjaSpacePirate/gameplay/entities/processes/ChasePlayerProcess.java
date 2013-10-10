package com.punchline.NinjaSpacePirate.gameplay.entities.processes;

import com.badlogic.gdx.math.Vector2;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.EntityWorld;
import com.punchline.javalib.entities.components.generic.Health;
import com.punchline.javalib.entities.components.physical.Transform;
import com.punchline.javalib.entities.components.physical.Velocity;
import com.punchline.javalib.entities.events.EventCallback;
import com.punchline.javalib.entities.processes.Process;
import com.punchline.javalib.entities.processes.ProcessState;

/**
 * A process that makes an enemy chase the player.
 * @author Natman64
 *
 */
public class ChasePlayerProcess extends Process {

	private class AbortionCallback implements EventCallback {

		@Override
		public void invoke(Entity e, Object... args) {
			ChasePlayerProcess.this.end(ProcessState.ABORTED);
		}
		
	}
	
	private class SuccessCallback implements EventCallback {

		@Override
		public void invoke(Entity e, Object... args) {
			ChasePlayerProcess.this.end(ProcessState.SUCCEEDED);
		}
		
	}
	
	private static final float CHASE_SPEED = 5f;
	
	private Entity chaser;
	private Entity player;
	
	/**
	 * Creates a ChasePlayerProcess.
	 * @param chaser
	 * @param player
	 */
	public ChasePlayerProcess(Entity chaser, Entity player) {
		this.chaser = chaser;
		this.player = player;
		
		chaser.onDeleted.addCallback("ChaseProcessEnd", new AbortionCallback());
		player.onDeleted.addCallback("ChaseProcessEnd", new AbortionCallback());
		
		Health chaserHealth = chaser.getComponent(Health.class);
		Health playerHealth = player.getComponent(Health.class);
		
		chaserHealth.onDeath.addCallback("ChaseProcessEnd", new AbortionCallback());
		playerHealth.onDeath.addCallback("ChaseProcessEnd", new SuccessCallback());
	}
	
	@Override
	public void update(EntityWorld world, float deltaTime) {
		Transform chaserTransform = chaser.getComponent(Transform.class);
		Transform playerTransform = player.getComponent(Transform.class);
		
		Vector2 position = chaserTransform.getPosition();
		Vector2 destination = playerTransform.getPosition();
		
		Vector2 velocity = destination.cpy().sub(position);
		velocity.nor();
		velocity.scl(CHASE_SPEED);
		
		Velocity v = chaser.getComponent(Velocity.class);
		v.setLinearVelocity(velocity);
	}

	@Override
	public void onEnd(EntityWorld world, ProcessState endState) {
		Velocity v = chaser.getComponent(Velocity.class);
		v.setLinearVelocity(new Vector2());
		
		//remove callbacks from the entities.
		chaser.onDeleted.removeCallback("ChaseProcessEnd");
		player.onDeleted.removeCallback("ChaseProcessEnd");
		
		Health chaserHealth = chaser.getComponent(Health.class);
		Health playerHealth = player.getComponent(Health.class);
		
		chaserHealth.onDeath.removeCallback("ChaseProcessEnd");
		playerHealth.onDeath.removeCallback("ChaseProcessEnd");
	}

}
