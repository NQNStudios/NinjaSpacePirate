package com.natman.NinjaSpacePirate.gameplay.entities.templates;

import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.EntityWorld;
import com.lostcode.javalib.entities.processes.ExpirationProcess;
import com.lostcode.javalib.entities.templates.EntityTemplate;
import com.natman.NinjaSpacePirate.gameplay.entities.components.render.hud.PotionMessage;

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
