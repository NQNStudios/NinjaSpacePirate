package com.natman.NinjaSpacePirate.gameplay.entities.templates;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.EntityWorld;
import com.lostcode.javalib.entities.components.generic.TriggerZone;
import com.lostcode.javalib.entities.components.physical.Body;
import com.lostcode.javalib.entities.components.render.Sprite;
import com.lostcode.javalib.entities.templates.EntityTemplate;
import com.lostcode.javalib.utils.SoundManager;
import com.natman.NinjaSpacePirate.gameplay.GameScore;
import com.natman.NinjaSpacePirate.gameplay.Stats;
import com.natman.NinjaSpacePirate.gameplay.StealthWorld;
import com.natman.NinjaSpacePirate.gameplay.stats.IntStat;

/**
 * Creates a coin
 * @author Natman64
 * @created Oct 17, 2013
 */
public class CoinTemplate implements EntityTemplate {

	@Override
	public void dispose() {
		
	}

	@Override
	public Entity buildEntity(Entity e, EntityWorld world, Object... args) {
		e.init("", "", "Coin");
		
		Vector2 position = (Vector2) args[0];
		
		Sprite sprite = new Sprite(world.getSpriteSheet(), "Coin");
		sprite.setLayer(4);
		sprite.setScale(0.5f, 0.5f);
		e.addComponent(sprite);
		
		Body body = new Body(world, e, BodyType.KinematicBody, position);
		e.addComponent(body);
		
		TriggerZone triggerZone = new TriggerZone(e, 0.3f, 0.5f) {
			
			@Override
			public void onDetected(Entity e, EntityWorld world) {
				super.onDetected(e, world);
				
				if (e.getTag().equals("Player")) {
					GameScore score = ((StealthWorld) world).getScore();
					score.coins++;
					
					IntStat coins = (IntStat) Stats.getStat("Coins Collected");
					coins.increment();
					
					owner.delete(); //remove the coin from the world
					
					SoundManager.playSound("Coin_Pickup");
				}
			}
			
		};
		
		e.addComponent(triggerZone);
		
		return e;
	}

}
