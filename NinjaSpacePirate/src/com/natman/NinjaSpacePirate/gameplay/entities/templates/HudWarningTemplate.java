package com.natman.NinjaSpacePirate.gameplay.entities.templates;

import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.EntityWorld;
import com.lostcode.javalib.entities.templates.EntityTemplate;
import com.natman.NinjaSpacePirate.gameplay.entities.components.render.hud.HudWarning;
import com.natman.NinjaSpacePirate.gameplay.entities.components.render.hud.HudWarning.WarningType;

/**
 * Creates an Entity that shows the player a {@link HudWarning}.
 * @author Natman64
 * @created Oct 13, 2013
 */
public class HudWarningTemplate implements EntityTemplate {

	@Override
	public void dispose() {
		
	}

	@Override
	public Entity buildEntity(Entity e, EntityWorld world, Object... args) {
		e.init("", "Hud", "HudWarning");
		
		WarningType type = (WarningType) args[0];
		int lane = (Integer) args[1];
		
		HudWarning warning = new HudWarning(world, type, lane);
		e.addComponent(warning);
		
		return e;
	}

}
