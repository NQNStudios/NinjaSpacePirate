package com.punchline.NinjaSpacePirate.gameplay.entities.systems;

import com.punchline.NinjaSpacePirate.gameplay.entities.components.render.NPCMultiRenderable;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.components.physical.Velocity;
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
		super(NPCMultiRenderable.class);
	}

	@Override
	public void dispose() {
		
	}

	@Override
	protected void process(Entity e) {
		NPCMultiRenderable mr = e.getComponent(NPCMultiRenderable.class);
		
		if (e.hasComponent(Velocity.class)) {
			//Try to update state to reflect velocity.
			Velocity v = e.getComponent(Velocity.class);
			
			mr.setMoving(v.getLinearVelocity().len() > 0);
		}
	}

}
