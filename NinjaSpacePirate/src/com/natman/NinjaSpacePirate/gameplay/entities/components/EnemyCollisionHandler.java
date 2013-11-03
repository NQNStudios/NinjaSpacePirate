package com.natman.NinjaSpacePirate.gameplay.entities.components;

import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.components.ComponentManager;
import com.lostcode.javalib.entities.components.generic.Health;
import com.lostcode.javalib.entities.components.physical.Collidable;
import com.natman.NinjaSpacePirate.gameplay.entities.processes.powerups.GhostPowerup;
import com.natman.NinjaSpacePirate.gameplay.entities.processes.powerups.InvulnPowerup;

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
