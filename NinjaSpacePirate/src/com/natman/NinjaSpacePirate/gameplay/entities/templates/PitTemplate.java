package com.natman.NinjaSpacePirate.gameplay.entities.templates;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.EntityWorld;
import com.lostcode.javalib.entities.components.generic.Health;
import com.lostcode.javalib.entities.components.generic.TriggerZone;
import com.lostcode.javalib.entities.components.physical.Body;
import com.lostcode.javalib.entities.components.render.Renderable;
import com.lostcode.javalib.entities.templates.EntityTemplate;
import com.lostcode.javalib.utils.Convert;
import com.natman.NinjaSpacePirate.gameplay.entities.components.render.PlayerSprite;

/**
 * Creates an Entity that represents a pit the player can fall into.
 * @author Natman64
 *
 */
public class PitTemplate implements EntityTemplate {
	
	private static final float TILE_SIZE = Convert.pixelsToMeters(0.1f); //Players must be mostly on top of the tile to fall
	
	@Override
	public void dispose() {
		
	}

	@Override
	public Entity buildEntity(Entity e, EntityWorld world, Object... args) {
		e.init("", "", "Pit");
		
		Vector2 position = (Vector2) args[0];
		
		Body body = new Body(world, e, BodyType.StaticBody, position);
		e.addComponent(body);
		
		TriggerZone zone = new TriggerZone(e, TILE_SIZE, TILE_SIZE) {

			@Override
			public void onDetected(Entity e, EntityWorld world) {
				super.onDetected(e, world);
				
				if (e.getType().equals("Player")) {
					PlayerSprite sprite = (PlayerSprite) e.getComponent(Renderable.class);
					
					sprite.setState("Falling", false);
					
					Health h = e.getComponent(Health.class);
					
					h.drainEmpty();
				}
			}
			
		};
		
		e.addComponent(zone);
		 
		return e;
	}

}
