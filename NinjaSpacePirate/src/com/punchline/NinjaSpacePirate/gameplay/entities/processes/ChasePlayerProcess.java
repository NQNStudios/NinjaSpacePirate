package com.punchline.NinjaSpacePirate.gameplay.entities.processes;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.EntityWorld;
import com.punchline.javalib.entities.components.generic.Health;
import com.punchline.javalib.entities.components.physical.Transform;
import com.punchline.javalib.entities.components.physical.Velocity;
import com.punchline.javalib.entities.events.EventCallback;
import com.punchline.javalib.entities.events.processes.EndProcessCallback;
import com.punchline.javalib.entities.processes.Process;
import com.punchline.javalib.entities.processes.ProcessState;
import com.punchline.javalib.utils.LogManager;

/**
 * A process that makes an enemy chase the player.
 * @author Natman64
 *
 */
public class ChasePlayerProcess extends Process {
	private class SuccessCallback implements EventCallback {
		
		private ChasePlayerProcess WhoIsDumb;
		public SuccessCallback(ChasePlayerProcess natIsDumb){
			WhoIsDumb = natIsDumb; // JK nat I actualyl love you, and yours was a very interesitng way of implementing it. I'm glad I could help :)
		}
		
		
		
		@Override
		public void invoke(Entity e, Object... args) {
			WhoIsDumb.endAll = true;
		}
		
	}
	
	private static final float CHASE_SPEED = 5f;
	
	private Entity chaser;
	private Entity player;
	
	/** Flag that triggers the ending of all ChasePlayerProcesses. */
	public boolean endAll = false;
	
	
	/**
	 * Creates a ChasePlayerProcess.
	 * @param chaser
	 * @param player
	 */
	public ChasePlayerProcess(Entity chaser, Entity player) {
		this.chaser = chaser;
		this.player = player;
		
		System.out.println("New Chase");
		
		if(chaser.getType().equals("Tile"))
			LogManager.error("WeirdPool", "A tile was found instead of a NPC");
		
		
		chaser.onDeleted.addCallback("ChaseProcessEnd", new EndProcessCallback(this, ProcessState.ABORTED));
		player.onDeleted.addCallback("ChaseProcessEnd", new EndProcessCallback(this, ProcessState.ABORTED));
		
		Health chaserHealth = chaser.getComponent(Health.class);
		Health playerHealth = player.getComponent(Health.class);
		
		chaserHealth.onDeath.addCallback("ChaseProcessEnd", new EndProcessCallback(this, ProcessState.ABORTED));
		playerHealth.onDeath.addCallback("ChaseProcessEnd", new SuccessCallback(this));
	}
	
	@Override
	public void update(EntityWorld world, float deltaTime) {
		if (endAll) {
			world.getProcessManager().endAll(getClass(), ProcessState.SUCCEEDED);
			
			return;
		}
		
		
		

		
		if (chaser == null || player == null) return;
		
		Transform chaserTransform = chaser.getComponent(Transform.class);
		Transform playerTransform = player.getComponent(Transform.class);
		
		if (chaserTransform == null || playerTransform == null) return;
		
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
		if (endState != ProcessState.SUCCEEDED) return;
		
		Velocity v = chaser.getComponent(Velocity.class);
		
		if (v == null) return;
		
		v.setLinearVelocity(new Vector2());
		
		//remove callbacks from the entities.
		chaser.onDeleted.removeCallback("ChaseProcessEnd");
		player.onDeleted.removeCallback("ChaseProcessEnd");
		
		Health chaserHealth = chaser.getComponent(Health.class);
		Health playerHealth = player.getComponent(Health.class);
		
		if (chaserHealth == null || playerHealth == null) return;
		
		chaserHealth.onDeath.removeCallback("ChaseProcessEnd");
		playerHealth.onDeath.removeCallback("ChaseProcessEnd");
	}

}
