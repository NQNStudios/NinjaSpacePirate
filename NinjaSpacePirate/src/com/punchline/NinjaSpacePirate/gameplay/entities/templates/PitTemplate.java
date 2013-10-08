package com.punchline.NinjaSpacePirate.gameplay.entities.templates;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.punchline.NinjaSpacePirate.gameplay.entities.components.render.PlayerSprite;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.EntityWorld;
import com.punchline.javalib.entities.components.generic.TriggerZone;
import com.punchline.javalib.entities.components.physical.Body;
import com.punchline.javalib.entities.components.render.Renderable;
import com.punchline.javalib.entities.templates.EntityTemplate;
import com.punchline.javalib.utils.Convert;

/**
 * Creates an Entity that represents a pit the player can fall into.
 * @author Natman64
 *
 */
public class PitTemplate implements EntityTemplate {
	
	private static final float TILE_SIZE = Convert.pixelsToMeters(8);
	
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
				}
			}
			
		};
		
		e.addComponent(zone);
		
		return e;
	}

}
