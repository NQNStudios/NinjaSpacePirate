package com.punchline.NinjaSpacePirate.gameplay.entities.components;

import com.punchline.NinjaSpacePirate.gameplay.entities.processes.powerups.GhostPowerup;
import com.punchline.NinjaSpacePirate.gameplay.entities.processes.powerups.InvulnPowerup;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.components.ComponentManager;
import com.punchline.javalib.entities.components.generic.Health;
import com.punchline.javalib.entities.components.physical.Collidable;

public class EnemyCollisionHandler extends Collidable {

	@Override
	public void onAdd(ComponentManager container) {
		
	}

	@Override
	public void onRemove(ComponentManager container) {
		
	}

	
	
	@Override
	public float continueCollision(Entity container, Entity victim) {
		if (victim.hasComponent(GhostPowerup.class)) {
			return 0;
		}
		
		return 1;
	}

	@Override
	public void onBeginContact(Entity container, Entity victim) {
		if (victim.hasComponent(InvulnPowerup.class)) {
			Health health = container.getComponent(Health.class);
			health.drainEmpty();
			
			return;
		}
		
		Health health = victim.getComponent(Health.class);
		health.drainEmpty();
	}

	@Override
	public void onEndContact(Entity container, Entity victim) {
		super.onEndContact(container, victim);
	}

	
	
}
