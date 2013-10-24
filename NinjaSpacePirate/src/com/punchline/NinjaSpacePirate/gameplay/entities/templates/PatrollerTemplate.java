package com.punchline.NinjaSpacePirate.gameplay.entities.templates;

import com.badlogic.gdx.math.Vector2;
import com.punchline.NinjaSpacePirate.gameplay.entities.processes.movement.PatrolProcess;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.EntityWorld;
import com.punchline.javalib.entities.templates.EntityTemplate;

public class PatrollerTemplate implements EntityTemplate {

	@Override
	public void dispose() {
		
	}

	@Override
	public Entity buildEntity(Entity e, EntityWorld world, Object... args) {
		Vector2 position = (Vector2) args[0];
		Vector2[] patrolPoints = (Vector2[]) args[1];
		
		e = world.createEntity("NPC", "yellowSuitMan", position, "", "Enemies", "Patroller");
		
		PatrolProcess movement = new PatrolProcess(world, e, 3f, patrolPoints);
		e.addComponent(movement);
		
		return e;
	}

}
