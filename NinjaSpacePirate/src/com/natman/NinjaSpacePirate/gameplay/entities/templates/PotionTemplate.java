package com.natman.NinjaSpacePirate.gameplay.entities.templates;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.EntityWorld;
import com.lostcode.javalib.entities.components.generic.TriggerZone;
import com.lostcode.javalib.entities.components.physical.Body;
import com.lostcode.javalib.entities.components.render.Sprite;
import com.lostcode.javalib.entities.processes.ProcessState;
import com.lostcode.javalib.entities.templates.EntityTemplate;
import com.lostcode.javalib.utils.SoundManager;
import com.natman.NinjaSpacePirate.gameplay.GameScore;
import com.natman.NinjaSpacePirate.gameplay.Stats;
import com.natman.NinjaSpacePirate.gameplay.StealthWorld;
import com.natman.NinjaSpacePirate.gameplay.entities.processes.PowerupProcess;
import com.natman.NinjaSpacePirate.gameplay.entities.processes.powerups.SicknessPowerup;
import com.natman.NinjaSpacePirate.gameplay.stats.IntStat;

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
				
				GameScore score = ((StealthWorld) world).getScore();
				score.potions++;
				
				IntStat potions = (IntStat) Stats.getStat("Potions Drunk");
				potions.increment();
				
				owner.delete(); //remove the potion from the world
				
				SoundManager.playSound("Potion_Sound");
				
				if (e.getTag().equals("Player")) {
					if (e.hasComponent(PowerupProcess.class)) {
						Entity msg = world.tryGetEntity("", "", "PotionMessage"); //delete the old message
						if (msg != null) msg.delete();
						
						PowerupProcess p = e.getComponent(PowerupProcess.class);
						p.end(ProcessState.ABORTED);
						world.getProcessManager().attach(new SicknessPowerup(3, e));
						return;
					}
					
					world.getProcessManager().attach(powerup);
				}
			}
			
		};
		
		e.addComponent(triggerZone);
		
		return e;
	}

}
