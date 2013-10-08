package com.punchline.NinjaSpacePirate.gameplay.entities.templates;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.EntityWorld;
import com.punchline.javalib.entities.GenericCollisionEvents;
import com.punchline.javalib.entities.components.generic.Health;
import com.punchline.javalib.entities.components.physical.Body;
import com.punchline.javalib.entities.components.physical.Collidable;
import com.punchline.javalib.entities.components.render.Animation;
import com.punchline.javalib.entities.templates.EntityTemplate;
import com.punchline.javalib.utils.Convert;

/**
 * Template for creating the player entity.
 * @author Natman64
 *
 */
public class PlayerTemplate implements EntityTemplate {

	//Body constants
	private static final float BODY_RADIUS = Convert.pixelsToMeters(3.5f);
	private static final Vector2 BODY_POSITION = new Vector2(0, -5);
	
	private CircleShape shape;
	
	/**
	 * Constructs a PlayerTemplate.
	 */
	public PlayerTemplate() {
		shape = new CircleShape();
		shape.setRadius(BODY_RADIUS);
	}
	
	@Override
	public void dispose() {
		shape.dispose();
	}

	@Override
	public Entity buildEntity(Entity e, EntityWorld world, Object... args) {
		e.init("Player", "Players", "Player");
		
		Animation animation = new Animation(world.getSpriteSheet(), 
				"blueSuitManMoveUp", 2, 1, 1, 1, Animation.LOOP_PINGPONG, 0.3f);
		
		animation.setLayer(5);
		
		e.addComponent(animation);
		
		Body body = new Body(world, e, BodyType.DynamicBody, shape, BODY_POSITION);
		e.addComponent(body);
		
		Health health = new Health(e, world, 1f);
		e.addComponent(health);
		
		Collidable onCollision = GenericCollisionEvents.empty();
		e.addComponent(onCollision);
		
		return e;
	}

}
