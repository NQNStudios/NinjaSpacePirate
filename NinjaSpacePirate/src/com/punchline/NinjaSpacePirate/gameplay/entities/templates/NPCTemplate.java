package com.punchline.NinjaSpacePirate.gameplay.entities.templates;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.punchline.NinjaSpacePirate.gameplay.entities.components.render.NPCMultiRenderable;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.EntityWorld;
import com.punchline.javalib.entities.components.generic.View;
import com.punchline.javalib.entities.components.physical.Body;
import com.punchline.javalib.entities.components.render.MultiRenderable;
import com.punchline.javalib.entities.templates.EntityTemplate;
import com.punchline.javalib.utils.Convert;

/**
 * Template used to create NPCs.
 * @author Natman64
 *
 */
public class NPCTemplate implements EntityTemplate {
	
	//View constants
	private static final float VIEW_RANGE = Convert.pixelsToMeters(17.5f);
	private static final float VIEW_FOV = 0.18f;
	
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

		View view = new View(e, VIEW_RANGE, VIEW_FOV);
		e.addComponent(view);
		
		MultiRenderable mr = new NPCMultiRenderable(world.getSpriteSheet(), spriteKey, view);
		
		e.addComponent(mr);
		
		return e;
	}
	
	@Override
	public void dispose() {
		shape.dispose();
	}

}
