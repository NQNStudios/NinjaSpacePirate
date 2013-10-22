package com.punchline.NinjaSpacePirate.gameplay.entities.templates;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.punchline.NinjaSpacePirate.gameplay.GameScore;
import com.punchline.NinjaSpacePirate.gameplay.Stats;
import com.punchline.NinjaSpacePirate.gameplay.StealthWorld;
import com.punchline.NinjaSpacePirate.gameplay.stats.IntStat;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.EntityWorld;
import com.punchline.javalib.entities.components.generic.TriggerZone;
import com.punchline.javalib.entities.components.physical.Body;
import com.punchline.javalib.entities.components.render.Sprite;
import com.punchline.javalib.entities.templates.EntityTemplate;
import com.punchline.javalib.utils.SoundManager;

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
