package com.punchline.NinjaSpacePirate.gameplay.entities.templates;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.EntityWorld;
import com.punchline.javalib.entities.components.physical.Body;
import com.punchline.javalib.entities.components.physical.Particle;
import com.punchline.javalib.entities.components.render.Sprite;
import com.punchline.javalib.entities.templates.EntityTemplate;

/**
 * Template for creating a tile of the space ship.
 * @author Natman64
 *
 */
public class TileTemplate implements EntityTemplate {

	private static final float BOX_SIZE = 1f;
	private static final int NORMAL_TILE_LAYER = 0;
	private static final int BLOCKED_TILE_LAYER = 100;
	
	private PolygonShape boxShape;
	
	/**
	 * Constructs a TileTemplate.
	 */
	public TileTemplate() {
		boxShape = new PolygonShape();
		boxShape.setAsBox(BOX_SIZE / 2, BOX_SIZE / 2);
	}
	
	@Override
	public void dispose() {
		boxShape.dispose();
	}

	@Override
	public Entity buildEntity(Entity e, EntityWorld world, Object... args) {
		e.init("", "Scenery", "Tile");
		
		String spriteKey = (String) args[0];
		Vector2 position = (Vector2) args[1];
		
		Boolean blocked = false;
		
		if (args.length >= 3) {
			blocked = (Boolean) args[2];
		}
		
		Sprite sprite = new Sprite(world.getSpriteSheet(), spriteKey);
		sprite.setLayer(1);
		e.addComponent(sprite);
		
		if (spriteKey.equals("FloorHole")) {
			world.createEntity("Pit", position);
			sprite.setLayer(-1);
		}
		
		if (blocked) {
			sprite.setLayer(BLOCKED_TILE_LAYER);
			
			Body body = new Body(world, e, BodyType.StaticBody, boxShape, position);
			e.addComponent(body);
		} else {
			sprite.setLayer(NORMAL_TILE_LAYER);
			
			Particle particle = new Particle(e, position, 0f);
			e.addComponent(particle);
		}
		
		return e;
	}

}
