package com.natman.NinjaSpacePirate.gameplay.entities.templates;

import com.badlogic.gdx.math.Vector2;
import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.EntityWorld;
import com.lostcode.javalib.entities.templates.EntityTemplate;
import com.natman.NinjaSpacePirate.gameplay.entities.processes.movement.PatrolProcess;

public class PatrollerTemplate implements EntityTemplate {

	@Override
	public void dispose() {
		
	}

	@Override
	public Entity buildEntity(Entity e, EntityWorld world, Object... args) {
		Vector2 position = (Vector2) args[0];
		Vector2[] patrolPoints = (Vector2[]) args[1];
		
		PatrolProcess movement = new PatrolProcess(world, e, 3f, patrolPoints);
		
		e = world.createEntity("NPC", "yellowSuitMan", position, "", "Enemies", "Patroller", movement);
		
		return e;
	}

}
