package com.punchline.NinjaSpacePirate.gameplay.entities.templates;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.punchline.NinjaSpacePirate.gameplay.Stats;
import com.punchline.NinjaSpacePirate.gameplay.entities.processes.PowerupProcess;
import com.punchline.NinjaSpacePirate.gameplay.stats.IntStat;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.EntityWorld;
import com.punchline.javalib.entities.components.generic.TriggerZone;
import com.punchline.javalib.entities.components.physical.Body;
import com.punchline.javalib.entities.components.render.Sprite;
import com.punchline.javalib.entities.templates.EntityTemplate;
import com.punchline.javalib.utils.SoundManager;

/**
 * Creates a potion that applies a random effect to the player.
 * @author Natman64
 * @created Oct 17, 2013
 */
public class PotionTemplate implements EntityTemplate {

	@Override
	public void dispose() {
		
	}

	@Override
	public Entity buildEntity(Entity e, EntityWorld world, Object... args) {
		e.init("", "", "Potion");
		
		Vector2 position = (Vector2) args[0];
		final PowerupProcess powerup = (PowerupProcess) args[1];
		
		Sprite sprite = new Sprite(world.getSpriteSheet(), "Potion");
		sprite.setLayer(4);
		sprite.setScale(0.5f, 0.5f);
		e.addComponent(sprite);
		
		Body body = new Body(world, e, BodyType.KinematicBody, position);
		e.addComponent(body);
		
		TriggerZone triggerZone = new TriggerZone(e, 0.5f, 0.5f) {
			
			@Override
			public void onDetected(Entity e, EntityWorld world) {
				super.onDetected(e, world);
				
				if (e.getTag().equals("Player")) {
					world.getProcessManager().attach(powerup);
					
					IntStat potions = (IntStat) Stats.getStat("Potions Drunk");
					potions.increment();
					
					owner.delete(); //remove the potion from the world
					
					SoundManager.playSound("Potion_Sound");
				}
			}
			
		};
		
		e.addComponent(triggerZone);
		
		return e;
	}

}
