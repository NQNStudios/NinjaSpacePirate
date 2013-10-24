package com.punchline.NinjaSpacePirate.gameplay.entities.templates;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.EntityWorld;
import com.punchline.javalib.entities.components.ComponentManager;
import com.punchline.javalib.entities.components.physical.Body;
import com.punchline.javalib.entities.components.physical.Collidable;
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
	
	private static final int BACKGROUND_TILE_LAYER = -1;
	private static final int NORMAL_TILE_LAYER = 1;
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
		
		if (blocked) {
			sprite.setLayer(BLOCKED_TILE_LAYER);
			
			Body body = new Body(world, e, BodyType.StaticBody, boxShape, position);
			e.addComponent(body);
			
			if (spriteKey.equals("FloorGreen") || spriteKey.equals("FloorGreenDoor")) {
				sprite.setLayer(NORMAL_TILE_LAYER);
				Collidable c = new Collidable() {
	
					@Override
					public void onAdd(ComponentManager container) {
						
					}
	
					@Override
					public void onRemove(ComponentManager container) {
						
					}
	
					@Override
					public float continueCollision(Entity container, Entity victim) {
						return victim.getType().equals("Player") ? 1f : 0f;
					}
					
				};
				
				e.addComponent(c);
			}
		} else {
			sprite.setLayer(NORMAL_TILE_LAYER);
			
			Particle particle = new Particle(e, position, 0f);
			e.addComponent(particle);
		}
		
		if (spriteKey.equals("FloorHole")) {
			world.createEntity("Pit", position);
			sprite.setLayer(BACKGROUND_TILE_LAYER);
		}
		
		return e;
	}

}
