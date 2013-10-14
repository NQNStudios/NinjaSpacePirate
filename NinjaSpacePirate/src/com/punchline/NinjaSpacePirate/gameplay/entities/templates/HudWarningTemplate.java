package com.punchline.NinjaSpacePirate.gameplay.entities.templates;

import com.punchline.NinjaSpacePirate.gameplay.entities.components.render.HudWarning;
import com.punchline.NinjaSpacePirate.gameplay.entities.components.render.HudWarning.WarningType;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.EntityWorld;
import com.punchline.javalib.entities.templates.EntityTemplate;

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
