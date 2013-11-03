package com.natman.NinjaSpacePirate.gameplay.entities.processes;

import com.badlogic.gdx.math.Vector2;
import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.EntityWorld;
import com.lostcode.javalib.entities.components.ComponentManager;
import com.lostcode.javalib.entities.components.generic.Health;
import com.lostcode.javalib.entities.components.physical.Transform;
import com.lostcode.javalib.entities.components.physical.Velocity;
import com.lostcode.javalib.entities.events.EventCallback;
import com.lostcode.javalib.entities.processes.ProcessState;
import com.lostcode.javalib.utils.LogManager;
import com.natman.NinjaSpacePirate.gameplay.entities.processes.powerups.GhostPowerup;
import com.natman.NinjaSpacePirate.gameplay.entities.systems.PlayerControlSystem;

/**
 * A process that makes an enemy chase the player.
 * @author Natman64
 *
 */
public class ChasePlayerProcess extends MovementProcess {
	private class SuccessCallback implements EventCallback {
		
		private ChasePlayerProcess process;
		public SuccessCallback(ChasePlayerProcess process){
			this.process = process;
		}
		
		@Override
		public void invoke(Entity e, Object... args) {
			process.endAll = true;
		}
		
	}
	
	private static final float SPEED_ADVANTAGE_MODIFIER = 1.75f;
	
	private Entity chaser;
	private Entity player;
	
	/** Flag that triggers the ending of all ChasePlayerProcesses. */
	public boolean endAll = false;
	
	public ChasePlayerProcess(EntityWorld world, Entity chaser, Entity player) {
		super(world, chaser);
		
		this.player = player;
		this.chaser = chaser;
		
		
		endOnDeath(chaser);
		Health playerHealth = player.getComponent(Health.class);
		
		playerHealth.onDeath.addCallback(this, new SuccessCallback(this));
	}
	
	@Override
	public void update(EntityWorld world, float deltaTime) {
		if (endAll) {
			world.getProcessManager().endAll(getClass(), ProcessState.SUCCEEDED);
			
			return;
		}
		
		if (chaser == null || player == null) return;
		
		if (player.hasComponent(GhostPowerup.class)) {
			endAll = true;
		}
		
		if (!chaser.hasComponent(getClass())) {
			chaser.addComponent(this);
		}
		
		Health health = player.getComponent(Health.class);
		if (health.isEmpty()) {
			endAll = true; //this should never be called.
		}
		
		if(!chaser.getGroup().equals("Enemies")) {
			LogManager.error("WrongType", "A chase player process tried to operate on a non-enemy");
			end(ProcessState.ABORTED);
			return;
		}
		
		Transform chaserTransform = chaser.getComponent(Transform.class);
		Transform playerTransform = player.getComponent(Transform.class);
		
		if (chaserTransform == null || playerTransform == null) return;
		
		Vector2 position = chaserTransform.getPosition();
		Vector2 destination = playerTransform.getPosition();
		
		Vector2 velocity = destination.cpy().sub(position);
		velocity.nor();
		velocity.scl(PlayerControlSystem.movementSpeed * SPEED_ADVANTAGE_MODIFIER); //speed relative the player's regular speed
		
		Velocity v = chaser.getComponent(Velocity.class);
		v.setLinearVelocity(velocity);
	}

	@Override
	public void onEnd(EntityWorld world, ProcessState endState) {
		if (endState != ProcessState.SUCCEEDED && endState != ProcessState.FAILED) return;
		
		Velocity v = chaser.getComponent(Velocity.class);
		
		if (v == null) return;
		
		v.setLinearVelocity(new Vector2());
		
		//remove callbacks from the entities.
		chaser.onDeleted.removeCallback(this);
		player.onDeleted.removeCallback(this);
		
		Health chaserHealth = chaser.getComponent(Health.class);
		Health playerHealth = player.getComponent(Health.class);
		
		if (chaserHealth == null || playerHealth == null) return;
		
		chaserHealth.onDeath.removeCallback(this);
		playerHealth.onDeath.removeCallback(this);
		
		chaser.removeComponent(this);
	}

	@Override
	public void onAdd(ComponentManager container) {
		
	}

	@Override
	public void onRemove(ComponentManager container) {
		
	}

}
