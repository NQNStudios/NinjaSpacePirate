package com.punchline.NinjaSpacePirate.gameplay.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.EntityWorld;
import com.punchline.javalib.entities.components.physical.Body;
import com.punchline.javalib.entities.components.render.AnimatedSprite;
import com.punchline.javalib.entities.components.render.MultiRenderable;
import com.punchline.javalib.entities.components.render.Sprite;
import com.punchline.javalib.entities.templates.EntityTemplate;
import com.punchline.javalib.utils.Convert;

/**
 * Template used to create the player.
 * @author Natman64
 *
 */
public class PlayerTemplate implements EntityTemplate {
	
	private BodyDef bodyDef;
	private FixtureDef fixtureDef;
	private CircleShape shape;
	
	/**
	 * Constructs the PlayerTemplate.
	 */
	public PlayerTemplate() {
		bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		
		shape = new CircleShape();
		shape.setRadius(Convert.pixelsToMeters(4));
		
		fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
	}
	
	@Override
	public Entity buildEntity(Entity e, EntityWorld world, Object... args) {
		e.init("Player", "Players", "Player");
		
		AnimatedSprite sprite = new AnimatedSprite(world.getSpriteSheet(), "walkingSlime2", 8, Animation.LOOP_PINGPONG, 0.3f);
		Sprite viewSprite = new Sprite(world.getSpriteSheet(), "View");
		viewSprite.setOrigin(new Vector2(0, 8));
		viewSprite.setPosition(new Vector2(10, 2));
		MultiRenderable mr = new MultiRenderable(sprite, viewSprite);
		
		e.addComponent(mr);
		
		Body body = new Body(world, e, bodyDef, fixtureDef);
		e.addComponent(body);
		
		return e;
	}
	
	@Override
	public void dispose() {
		shape.dispose();
	}

}
