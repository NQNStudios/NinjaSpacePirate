package com.punchline.NinjaSpacePirate.gameplay.entities.templates;

import com.badlogic.gdx.math.Vector2;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.EntityWorld;
import com.punchline.javalib.entities.components.generic.EntitySpawner;
import com.punchline.javalib.entities.templates.EntityCreationArgs;
import com.punchline.javalib.entities.templates.EntityTemplate;

/**
 * Creates an NPC spawner.
 * @author Natman64
 *
 */
public class NPCSpawnerTemplate implements EntityTemplate {

	@Override
	public void dispose() {
		
	}

	@Override
	public Entity buildEntity(Entity e, EntityWorld world, Object... args) {
		e.init("", "", "Spawner");
		
		String spriteKey = (String) args[0];
		Vector2 position = (Vector2) args[1];
		String tag = (String) args[2];
		String group = (String) args[3];
		String type = (String) args[4];
		Vector2 velocity = (Vector2) args[5];
		
		EntitySpawner spawner = new EntitySpawner("NPC", 
				false, 3f, spriteKey, position, tag, group, type, velocity);
		
		e.addComponent(spawner);
		
		return e;
	}

}
