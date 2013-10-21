package com.punchline.NinjaSpacePirate.gameplay.entities.templates;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.punchline.NinjaSpacePirate.gameplay.StealthWorld;
import com.punchline.NinjaSpacePirate.gameplay.entities.components.EnemyCollisionHandler;
import com.punchline.NinjaSpacePirate.gameplay.entities.components.render.npcs.NPCMultiRenderable;
import com.punchline.NinjaSpacePirate.gameplay.entities.processes.ChasePlayerProcess;
import com.punchline.NinjaSpacePirate.gameplay.entities.processes.powerups.GhostPowerup;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.EntityWorld;
import com.punchline.javalib.entities.components.generic.Health;
import com.punchline.javalib.entities.components.generic.View;
import com.punchline.javalib.entities.components.physical.Body;
import com.punchline.javalib.entities.components.physical.Collidable;
import com.punchline.javalib.entities.components.render.MultiRenderable;
import com.punchline.javalib.entities.components.render.Renderable;
import com.punchline.javalib.entities.events.EventCallback;
import com.punchline.javalib.entities.processes.ProcessState;
import com.punchline.javalib.entities.templates.EntityTemplate;
import com.punchline.javalib.utils.Convert;
import com.punchline.javalib.utils.LogManager;
import com.punchline.javalib.utils.SoundManager;

/**
 * Template used to create NPCs.
 * @author Natman64
 *
 */
public class NPCTemplate implements EntityTemplate {
	
	//Body constants
	private static final float BODY_RADIUS = Convert.pixelsToMeters(4);
	
	//View constants
	private static final float VIEW_RANGE = Convert.pixelsToMeters(17.5f);
	private static final float VIEW_FOV = 0.18f;
	
	private BodyDef bodyDef;
	private FixtureDef fixtureDef;
	private CircleShape shape;
	
	/**
	 * Constructs the NPCTemplate.
	 */
	public NPCTemplate() {
		bodyDef = new BodyDef();
		bodyDef.type = BodyType.KinematicBody;
		
		shape = new CircleShape();
		shape.setRadius(BODY_RADIUS);
		
		fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
	}
	
	@Override
	public Entity buildEntity(final Entity e, EntityWorld world, Object... args) {		
		String spriteKey = (String) args[0];
		Vector2 position = (Vector2) args[1];
		String tag = (String) args[2];
		String group = (String) args[3];
		String type = (String) args[4];
		
		Vector2 velocity = new Vector2();
		if (args.length >= 5) {
			velocity = (Vector2) args[5];
		}
		
		e.init(tag, group, type);
		
		Body body = new Body(world, e, bodyDef, fixtureDef);
		body.setPosition(position);
		body.setRotation((float)Math.toRadians(270));
		body.setLinearVelocity(velocity);
		e.addComponent(body);

		Collidable collidable = new EnemyCollisionHandler();
		e.addComponent(collidable);
		
		Health health = new Health(e, world, 5f);
		health.deleteOnDeath = false;
		
		health.onDeath.addCallback(this, new EventCallback() {

			@Override
			public void invoke(Entity e, Object... args) {
				NPCMultiRenderable sprite = (NPCMultiRenderable) e.getComponent(Renderable.class);
				
				sprite.die();
				sprite.setLayer(2);
				
				Body body = e.getComponent(Body.class);
				body.setLinearVelocity(new Vector2(0, 0));
				
				body.getBody().getFixtureList().get(0).setSensor(true);
				
				SoundManager.playSound("Player_Hit");
				
				ChasePlayerProcess ps = e.getComponent(ChasePlayerProcess.class);
				if (ps != null) ps.end(ProcessState.ABORTED);
			}
			
		});
		
		e.addComponent(health);
		
		View view = new View(e, VIEW_RANGE, VIEW_FOV) {

			ChasePlayerProcess ps;
			
			@Override
			public void onDetected(Entity es, EntityWorld world) {
				super.onDetected(es, world);
				
				
				if(e.getType().equals("Tile"))
					LogManager.error("WeirdPool", "A tile was found instead of a NPC");
				
				if (!es.hasComponent(GhostPowerup.class) && ps == null)
				{
					ps = new ChasePlayerProcess(e, ((StealthWorld) world).getPlayer());
					world.getProcessManager().attach(ps);

				}

			}
			
		};
		e.addComponent(view);
		
		MultiRenderable mr = new NPCMultiRenderable(world.getSpriteSheet(), spriteKey, view);
		
		e.addComponent(mr);
		
		return e;
	}
	
	@Override
	public void dispose() {
		shape.dispose();
	}

}
