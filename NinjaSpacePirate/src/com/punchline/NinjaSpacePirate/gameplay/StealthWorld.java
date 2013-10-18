package com.punchline.NinjaSpacePirate.gameplay;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.punchline.NinjaSpacePirate.gameplay.entities.processes.powerups.ReverseControlPowerup;
import com.punchline.NinjaSpacePirate.gameplay.entities.processes.powerups.SpeedLockPowerup;
import com.punchline.NinjaSpacePirate.gameplay.entities.systems.NPCAnimationSystem;
import com.punchline.NinjaSpacePirate.gameplay.entities.systems.PitBlockingSystem;
import com.punchline.NinjaSpacePirate.gameplay.entities.systems.PlayerControlSystem;
import com.punchline.NinjaSpacePirate.gameplay.entities.systems.TileSpawnSystem;
import com.punchline.NinjaSpacePirate.gameplay.entities.templates.HudWarningTemplate;
import com.punchline.NinjaSpacePirate.gameplay.entities.templates.NPCSpawnerTemplate;
import com.punchline.NinjaSpacePirate.gameplay.entities.templates.NPCTemplate;
import com.punchline.NinjaSpacePirate.gameplay.entities.templates.PitTemplate;
import com.punchline.NinjaSpacePirate.gameplay.entities.templates.PlayerTemplate;
import com.punchline.NinjaSpacePirate.gameplay.entities.templates.PotionMessageTemplate;
import com.punchline.NinjaSpacePirate.gameplay.entities.templates.PotionTemplate;
import com.punchline.NinjaSpacePirate.gameplay.entities.templates.TileTemplate;
import com.punchline.NinjaSpacePirate.gameplay.entities.templates.VoidTemplate;
import com.punchline.NinjaSpacePirate.gameplay.stats.TimeStat;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.EntityWorld;
import com.punchline.javalib.entities.systems.generic.TrackingCameraSystem;
import com.punchline.javalib.utils.Convert;
import com.punchline.javalib.utils.SpriteSheet;

/**
 * The NinjaSpacePirate game world.
 * @author Natman64
 *
 */
public class StealthWorld extends EntityWorld {

	/** The first y coordinate where tiles will spawn. */
	public static final int TILE_SPAWN_Y = -6;
	
	/** The speed of walking enemies. */
	public static final float ENEMY_SPEED = 3f;
	
	private Entity player;
	
	//region Initialization
	
	/**
	 * Makes a StealthWorld.
	 * @param input
	 * @param camera
	 * @param gravity
	 */
	public StealthWorld(InputMultiplexer input, Camera camera) {
		super(input, camera, new Vector2());
		
		debugView.enabled = true;
		debugView.visible = true;
	}

	//endregion
	
	//region Templates
	
	@Override
	protected void buildTemplates() {
		super.buildTemplates();
		
		//HUD
		addTemplate("HudWarning", new HudWarningTemplate());
		addTemplate("PotionMessage", new PotionMessageTemplate());
		
		//World
		addTemplate("Tile", new TileTemplate());
		addTemplate("Pit", new PitTemplate());
		addTemplate("EnemySpawner", new NPCSpawnerTemplate());
		addTemplate("Void", new VoidTemplate());
		
		//Characters
		addTemplate("Player", new PlayerTemplate());
		addTemplate("NPC", new NPCTemplate());
		
		//Items
		addTemplate("Potion", new PotionTemplate());
	}
	
	//endregion
	
	//region Systems
	
	@Override
	protected void buildSystems() {
		super.buildSystems();
		
		TrackingCameraSystem cameraSystem = 
				new TrackingCameraSystem("Player", camera);
		
		cameraSystem.setCameraOffset(Convert.metersToPixels(new Vector2(0, 4.5f)));
		systems.addSystem(cameraSystem);
		
		
		systems.addSystem(new TileSpawnSystem());
		systems.addSystem(new PlayerControlSystem(input));
		systems.addSystem(new NPCAnimationSystem());
		systems.addSystem(new PitBlockingSystem());
	}
	
	//endregion
	
	//region Entities
	
	@Override
	protected void buildEntities() {
		player = createEntity("Player");
		
		createEntity("Potion", new Vector2(), new ReverseControlPowerup(10f, player));
	}
	
	//endregion
	
	//region SpriteSheet
	
	@Override
	protected void buildSpriteSheet() {
		
		try {
			spriteSheet = SpriteSheet.fromXML(Gdx.files.internal("data/spritesheet.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Humans
		addHumanSprite("redSuitMan", 0, 9, 1);
		addHumanSprite("greenSuitMan", 0, 18, 1);
		addHumanSprite("yellowSuitMan", 0, 27, 1);
		addHumanSprite("whiteSuitMan", 0, 36, 1);
		
	}

	private void addHumanSprite(String key, int x, int y, int padding) {
		Rectangle frame = new Rectangle(x + padding, y + padding, 8, 8);
		
		spriteSheet.addRegion(key + "Right", frame);
		frame.x += 8 + padding; frame.width = 16 + padding;
		spriteSheet.addRegion(key + "MoveRight", frame);
		frame.x += 16 + padding + padding; frame.width = 8;
		spriteSheet.addRegion(key + "Down", frame);
		frame.x += 8 + padding; frame.width = 16 + padding;
		spriteSheet.addRegion(key + "MoveDown", frame);
		frame.x += 16 + padding + padding; frame.width = 8;
		spriteSheet.addRegion(key + "Left", frame);
		frame.x += 8 + padding; frame.width = 16 + padding;
		spriteSheet.addRegion(key + "MoveLeft", frame);
		frame.x += 16 + padding + padding; frame.width = 8;
		spriteSheet.addRegion(key + "Up", frame);
		frame.x += 8 + padding; frame.width = 16 + padding;
		spriteSheet.addRegion(key + "MoveUp", frame);
		frame.x += 16 + padding + padding; frame.width = 8;
		spriteSheet.addRegion(key + "Dead", frame);
	}
	
	@SuppressWarnings("unused")
	private void addMonsterSprite(String key, int x, int y, int padding) {
		Rectangle frame = new Rectangle(x + padding, y + padding, 8, 8);
		
		spriteSheet.addRegion(key + "Right", frame);
		frame.x += 8 + padding; frame.width = 16 + padding;
		spriteSheet.addRegion(key + "MoveRight", frame);
		frame.x += 16 + padding + padding; frame.width = 8;
		spriteSheet.addRegion(key + "Down", frame);
		frame.x += 8 + padding; frame.width = 16 + padding;
		spriteSheet.addRegion(key + "MoveDown", frame);
		frame.x += 16 + padding + padding; frame.width = 8;
		spriteSheet.addRegion(key + "Left", frame);
		frame.x += 8 + padding; frame.width = 16 + padding;
		spriteSheet.addRegion(key + "MoveLeft", frame);
		frame.x += 16 + padding + padding; frame.width = 8;
		spriteSheet.addRegion(key + "Up", frame);
		frame.x += 8 + padding; frame.width = 16 + padding;
		spriteSheet.addRegion(key + "MoveUp", frame);
	}
	
	//endregion
	
	//region Bounds
	
	@Override
	public Rectangle getBounds() {
		float width = Convert.pixelsToMeters(camera.viewportWidth);
		float height = Convert.pixelsToMeters(camera.viewportHeight);
		
		float x = -width / 2;
		float y = Convert.pixelsToMeters(camera.position.y) - height / 2;
		
		return new Rectangle(x, y, width, height);
	}

	//endregion

	//region Accessors
	
	/**
	 * @return The player Entity.
	 */
	public Entity getPlayer() {
		return player;
	}
	
	//endregion
	
	//region Processing
	
	@Override
	public void process() {
		super.process();
		
		TimeStat timePlayed = (TimeStat) Stats.getStat("Time Played");
		timePlayed.add(Gdx.graphics.getDeltaTime());
	}
	
	//endregion
	
}
