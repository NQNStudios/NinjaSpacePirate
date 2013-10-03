package com.punchline.NinjaSpacePirate.gameplay.entities.templates;

import com.badlogic.gdx.math.Vector2;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.EntityWorld;
import com.punchline.javalib.entities.components.physical.Particle;
import com.punchline.javalib.entities.components.render.Sprite;
import com.punchline.javalib.entities.templates.EntityTemplate;

/**
 * Template for creating a tile of the space ship.
 * @author Natman64
 *
 */
public class TileTemplate implements EntityTemplate {

	@Override
	public void dispose() {
		
	}

	@Override
	public Entity buildEntity(Entity e, EntityWorld world, Object... args) {
		e.init("", "Scenery", "Tile");
		
		String spriteKey = (String) args[0];
		Vector2 position = (Vector2) args[1];
		
		Sprite sprite = new Sprite(world.getSpriteSheet(), spriteKey);
		Particle particle = new Particle(e, position, 0f);
		
		e.addComponent(sprite);
		e.addComponent(particle);
		
		return e;
	}

}
