package com.punchline.NinjaSpacePirate.gameplay.entities.templates;

import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.EntityWorld;
import com.punchline.javalib.entities.components.render.Sprite;
import com.punchline.javalib.entities.templates.EntityTemplate;

public class PotionTemplate implements EntityTemplate {

	@Override
	public void dispose() {
		
	}

	@Override
	public Entity buildEntity(Entity e, EntityWorld world, Object... args) {
		e.init("", "", "Potion");
		
		Sprite sprite = new Sprite(world.getSpriteSheet(), "Potion");
		sprite.setLayer(20);
		sprite.setScale(0.5f, 0.5f);
		e.addComponent(sprite);
		
		//TODO add other components
		
		return e;
	}

}
