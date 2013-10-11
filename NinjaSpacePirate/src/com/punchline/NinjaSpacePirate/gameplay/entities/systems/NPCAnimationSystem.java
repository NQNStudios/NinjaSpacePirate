package com.punchline.NinjaSpacePirate.gameplay.entities.systems;

import com.punchline.NinjaSpacePirate.gameplay.entities.components.render.NPCMultiRenderable;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.components.physical.Body;
import com.punchline.javalib.entities.systems.ComponentSystem;

/**
 * Forces all NPC sprites to change their states and reflect what their Entity is doing. For example, sprites should switch to an animation state
 * showing movement automatically when their Entity is moving.
 * @author Natman64
 * @created Oct 2, 2013
 */
public class NPCAnimationSystem extends ComponentSystem {

	/**
	 * Creates an NPCAnimationSystem.
	 */
	@SuppressWarnings("unchecked")
	public NPCAnimationSystem() {
		super(NPCMultiRenderable.class, Body.class);
	}

	@Override
	public void dispose() {
		
	}

	@Override
	protected void process(Entity e) {
		NPCMultiRenderable mr = e.getComponent(NPCMultiRenderable.class);
		
		//Try to update state to reflect velocity.
		Body body = e.getComponent(Body.class);
		
		mr.setMoving(body.getLinearVelocity().len() > 0);
		
		if (body.getLinearVelocity().len() > 0) {
			body.setRotation((float) Math.toRadians(body.getLinearVelocity().angle()));
		}
	}

}
