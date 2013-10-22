package com.punchline.NinjaSpacePirate.gameplay.entities.templates;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.punchline.NinjaSpacePirate.gameplay.GameScore;
import com.punchline.NinjaSpacePirate.gameplay.Stats;
import com.punchline.NinjaSpacePirate.gameplay.StealthGameOverInfo;
import com.punchline.NinjaSpacePirate.gameplay.StealthWorld;
import com.punchline.NinjaSpacePirate.gameplay.entities.components.render.PlayerSprite;
import com.punchline.NinjaSpacePirate.gameplay.entities.processes.GameOverProcess;
import com.punchline.NinjaSpacePirate.gameplay.stats.DistanceStat;
import com.punchline.NinjaSpacePirate.gameplay.stats.IntStat;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.EntityWorld;
import com.punchline.javalib.entities.GenericCollisionEvents;
import com.punchline.javalib.entities.components.generic.Health;
import com.punchline.javalib.entities.components.physical.Body;
import com.punchline.javalib.entities.components.physical.Collidable;
import com.punchline.javalib.entities.components.render.Renderable;
import com.punchline.javalib.entities.events.EventCallback;
import com.punchline.javalib.entities.processes.DelayProcess;
import com.punchline.javalib.entities.templates.EntityTemplate;
import com.punchline.javalib.utils.Convert;
import com.punchline.javalib.utils.SoundManager;

/**
 * Template for creating the player entity.
 * @author Natman64
 *
 */
public class PlayerTemplate implements EntityTemplate {

	private static final float POST_DEATH_TIME = 3f;
	
	//Body constants
	private static final float BODY_RADIUS = Convert.pixelsToMeters(3.5f);
	private static final Vector2 BODY_POSITION = new Vector2(0, -5);
	
	private CircleShape shape;
	
	/**
	 * Constructs a PlayerTemplate.
	 */
	public PlayerTemplate() {
		shape = new CircleShape();
		shape.setRadius(BODY_RADIUS);
	}
	
	@Override
	public void dispose() {
		shape.dispose();
	}

	@Override
	public Entity buildEntity(Entity e, final EntityWorld world, Object... args) {
		e.init("Player", "Players", "Player");
		
		PlayerSprite sprite = new PlayerSprite(world.getSpriteSheet());
		e.addComponent(sprite);
		
		Body body = new Body(world, e, BodyType.DynamicBody, shape, BODY_POSITION);
		e.addComponent(body);
		
		Health health = new Health(e, world, 1f);
		health.deleteOnDeath = false;
		health.onDeath.addCallback("KillSprite", new EventCallback() {

			@Override
			public void invoke(Entity e, Object... args) {
				PlayerSprite sprite = (PlayerSprite) (e.getComponent(Renderable.class));
				
				if (!sprite.isFalling()) {
					sprite.setState("Dead", false);
					SoundManager.playSound("Player_Hit");
				}
				
				Body body = e.getComponent(Body.class);
				body.getBody().getFixtureList().get(0).setSensor(true); //don't collide with anything now
				
				IntStat deaths = (IntStat) Stats.getStat("Deaths");
				deaths.increment();
				
				DistanceStat dist = (DistanceStat) Stats.getRecord("Farthest Run");
				float myDist = body.getPosition().y - StealthWorld.PLAYER_SPAWN_Y;
				if (myDist > dist.getValue()) {
					dist.setValue(myDist);
				}
				
				GameScore score = ((StealthWorld) world).getScore();
				score.meters = (int) myDist;
				
				world.getProcessManager().attach(new DelayProcess(POST_DEATH_TIME, 
						new GameOverProcess(new StealthGameOverInfo(score))));
				
				Entity msg = world.tryGetEntity("", "", "PotionMessage");
				if (msg != null) msg.delete();
			}
			
		});
		
		e.addComponent(health);
		
		Collidable onCollision = GenericCollisionEvents.empty();
		e.addComponent(onCollision);
		
		return e;
	}

}
