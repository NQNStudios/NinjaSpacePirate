package com.punchline.NinjaSpacePirate.gameplay.entities.templates;

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
 * Template used to create NPCs.
 * @author Natman64
 *
 */
public class NPCTemplate implements EntityTemplate {
	
	private BodyDef bodyDef;
	private FixtureDef fixtureDef;
	private CircleShape shape;
	
	/**
	 * Constructs the NPCTemplate.
	 */
	public NPCTemplate() {
		bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		
		shape = new CircleShape();
		shape.setRadius(Convert.pixelsToMeters(4));
		
		fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
	}
	
	@Override
	public Entity buildEntity(Entity e, EntityWorld world, Object... args) {		
		String spriteKey = (String) args[0];
		Vector2 position = (Vector2) args[1];
		String tag = (String) args[2];
		String group = (String) args[3];
		String type = (String) args[4];
		
		e.init(tag, group, type);
		
		Body body = new Body(world, e, bodyDef, fixtureDef);
		body.setPosition(position);
		body.setRotation((float)Math.toRadians(270));
		e.addComponent(body);
		
		AnimatedSprite sprite = new AnimatedSprite(world.getSpriteSheet(), spriteKey, 
				8, Animation.LOOP_PINGPONG, 0.3f) {

			public boolean moving = true;
			
			@Override
			public void setRotation(float degrees) {
				
				String state = "";
				
				if (moving) state += "Move";
				
				if (degrees < 45 || degrees > 315) {
					state += "Right";
				} else if (degrees >= 45 && degrees < 135) {
					state += "Up";
				} else if (degrees >= 135 && degrees < 225) {
					state += "Left";
				} else {
					state += "Down";
				}
				
				setState(state, true);
			}
			
		};
		
		Sprite viewSprite = new Sprite(world.getSpriteSheet(), "View");
		viewSprite.setOrigin(new Vector2(0f, 8f));
		viewSprite.setPosition(new Vector2(14, 5.5f));

		MultiRenderable mr = new MultiRenderable(sprite, viewSprite);
		
		e.addComponent(mr);
		
		return e;
	}
	
	@Override
	public void dispose() {
		shape.dispose();
	}

}
