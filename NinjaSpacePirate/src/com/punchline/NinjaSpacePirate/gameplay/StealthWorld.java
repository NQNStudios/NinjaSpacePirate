package com.punchline.NinjaSpacePirate.gameplay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.punchline.NinjaSpacePirate.gameplay.entities.systems.NPCAnimationSystem;
import com.punchline.NinjaSpacePirate.gameplay.entities.systems.PlayerControlSystem;
import com.punchline.NinjaSpacePirate.gameplay.entities.systems.spawn.TileSpawnSystem;
import com.punchline.NinjaSpacePirate.gameplay.entities.templates.NPCTemplate;
import com.punchline.NinjaSpacePirate.gameplay.entities.templates.PlayerTemplate;
import com.punchline.NinjaSpacePirate.gameplay.entities.templates.TileTemplate;
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

	public static final int TILE_SPAWN_Y = -6;
	
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

	@Override
	protected void buildTemplates() {
		super.buildTemplates();
		
		addTemplate("Player", new PlayerTemplate());
		addTemplate("Tile", new TileTemplate());
		addTemplate("NPC", new NPCTemplate());
	}
	
	@Override
	protected void buildSystems() {
		super.buildSystems();
		
		TrackingCameraSystem cameraSystem = 
				new TrackingCameraSystem("Player", camera);
		
		cameraSystem.setCameraOffset(Convert.metersToPixels(new Vector2(0, 5)));
		systems.addSystem(cameraSystem);
		
		
		systems.addSystem(new TileSpawnSystem());
		systems.addSystem(new PlayerControlSystem(input));
		systems.addSystem(new NPCAnimationSystem());
	}
	
	@Override
	protected void buildEntities() {		
		createEntity("Player");
		createEntity("NPC", "whiteSuitMan", new Vector2(0, 2), "Test", "Tests", "Test");
	}
	
	@Override
	protected void buildSpriteSheet() {
		
		spriteSheet = new SpriteSheet(new Texture(Gdx.files.internal("data/spritesheet.png")));
		
		//Tiles
		spriteSheet.addRegion("Floor", new Rectangle(1, 154, 8, 8));
		
		spriteSheet.addRegion("WhiteWallVertical", new Rectangle(1, 136, 8, 8));
		spriteSheet.addRegion("WhiteWallVentEast", new Rectangle(10, 136, 8, 8));
		spriteSheet.addRegion("WhiteWallVentWest", new Rectangle(19, 136, 8, 8));
		
		//View overlay
		spriteSheet.addRegion("View", new Rectangle(96, 236, 20, 15));
		
		//Humans
		addHumanSprite("blueSuitMan", 0, 0, 1);
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
	
	@Override
	public Rectangle getBounds() {
		float width = Convert.pixelsToMeters(camera.viewportWidth);
		float height = Convert.pixelsToMeters(camera.viewportHeight);
		
		float x = -width / 2;
		float y = Convert.pixelsToMeters(camera.position.y) - height / 2;
		
		return new Rectangle(x, y, width, height);
	}

}
