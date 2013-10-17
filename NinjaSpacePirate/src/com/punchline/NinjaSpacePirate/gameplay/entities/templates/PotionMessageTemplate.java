package com.punchline.NinjaSpacePirate.gameplay.entities.templates;

import com.punchline.NinjaSpacePirate.gameplay.entities.components.render.hud.PotionMessage;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.EntityWorld;
import com.punchline.javalib.entities.processes.ExpirationProcess;
import com.punchline.javalib.entities.templates.EntityTemplate;

/**
 * Creates a PotionMessage Entity.
 * @author Natman64
 * @created Oct 17, 2013
 */
public class PotionMessageTemplate implements EntityTemplate {

	@Override
	public void dispose() {
		
	}

	@Override
	public Entity buildEntity(Entity e, EntityWorld world, Object... args) {
		e.init("", "", "PotionMessage");
		
		String message = (String) args[0];
		float duration = (Float) args[1];
		
		PotionMessage msg = new PotionMessage(world, message, duration);
		e.addComponent(msg);
		
		world.getProcessManager().attach(new ExpirationProcess(duration, e));
		
		return e;
	}

}
