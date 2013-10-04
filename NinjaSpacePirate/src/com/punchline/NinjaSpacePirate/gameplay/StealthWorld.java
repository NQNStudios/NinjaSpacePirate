package com.punchline.NinjaSpacePirate.gameplay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.punchline.NinjaSpacePirate.gameplay.entities.systems.NPCAnimationSystem;
import com.punchline.NinjaSpacePirate.gameplay.entities.systems.PlayerControlSystem;
import com.punchline.NinjaSpacePirate.gameplay.entities.templates.NPCTemplate;
import com.punchline.NinjaSpacePirate.gameplay.entities.templates.TileTemplate;
import com.punchline.javalib.entities.EntityWorld;
import com.punchline.javalib.utils.Convert;
import com.punchline.javalib.utils.SpriteSheet;

/**
 * The NinjaSpacePirate game world.
 * @author Natman64
 *
 */
public class StealthWorld extends EntityWorld {

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
		
		addTemplate("Player", new NPCTemplate());
		addTemplate("Tile", new TileTemplate());
	}
	
	@Override
	protected void buildSystems() {
		super.buildSystems();
		
		systems.addSystem(new PlayerControlSystem(input));
		systems.addSystem(new NPCAnimationSystem());
	}
	
	@Override
	protected void buildEntities() {
		createEntity("Tile", "WhiteWallVertical", new Vector2(-3, 0), true);
		createEntity("Tile", "Floor", new Vector2(-2, 0));
		createEntity("Tile", "Floor", new Vector2(-1, 0));
		createEntity("Tile", "Floor", new Vector2(0, 0));
		createEntity("Tile", "Floor", new Vector2(1, 0));
		createEntity("Tile", "Floor", new Vector2(2, 0));
		createEntity("Tile", "WhiteWallVertical", new Vector2(3, 0), true);
		
		createEntity("Tile", "WhiteWallVertical", new Vector2(-3, 1), true);
		createEntity("Tile", "Floor", new Vector2(-2, 1));
		createEntity("Tile", "Floor", new Vector2(-1, 1));
		createEntity("Tile", "Floor", new Vector2(0, 1));
		createEntity("Tile", "Floor", new Vector2(1, 1));
		createEntity("Tile", "Floor", new Vector2(2, 1));
		createEntity("Tile", "WhiteWallVertical", new Vector2(3, 1), true);
		
		createEntity("Tile", "WhiteWallVertical", new Vector2(-3, 2), true);
		createEntity("Tile", "Floor", new Vector2(-2, 2));
		createEntity("Tile", "Floor", new Vector2(-1, 2));
		createEntity("Tile", "Floor", new Vector2(0, 2));
		createEntity("Tile", "Floor", new Vector2(1, 2));
		createEntity("Tile", "Floor", new Vector2(2, 2));
		createEntity("Tile", "WhiteWallVertical", new Vector2(3, 2), true);

		createEntity("Tile", "WhiteWallVertical", new Vector2(-3, 3), true);
		createEntity("Tile", "Floor", new Vector2(-2, 3));
		createEntity("Tile", "Floor", new Vector2(-1, 3));
		createEntity("Tile", "Floor", new Vector2(0, 3));
		createEntity("Tile", "Floor", new Vector2(1, 3));
		createEntity("Tile", "Floor", new Vector2(2, 3));
		createEntity("Tile", "WhiteWallVertical", new Vector2(3, 3), true);
		
		createEntity("Tile", "WhiteWallVertical", new Vector2(-3, 4), true);
		createEntity("Tile", "Floor", new Vector2(-2, 4));
		createEntity("Tile", "Floor", new Vector2(-1, 4));
		createEntity("Tile", "Floor", new Vector2(0, 4));
		createEntity("Tile", "Floor", new Vector2(1, 4));
		createEntity("Tile", "Floor", new Vector2(2, 4));
		createEntity("Tile", "WhiteWallVertical", new Vector2(3, 4), true);
		
		createEntity("Player", "blueSuitMan", new Vector2(0, 0), "Player", "Players", "Player");
	}
	
	@Override
	protected void buildSpriteSheet() {
		
		spriteSheet = new SpriteSheet(new Texture(Gdx.files.internal("data/lofi_scifi_v2_trans.png")));
		
		//Tiles
		spriteSheet.addRegion("Floor", new Rectangle(8, 531, 8, 8));
		
		spriteSheet.addRegion("WhiteWallVertical", new Rectangle(40, 456, 8, 8));
		spriteSheet.addRegion("WhiteWallVentEast", new Rectangle(24, 456, 8, 8));
		spriteSheet.addRegion("WhiteWallVentWest", new Rectangle(24, 440, 8, 8));
		
		//View overlay
		spriteSheet.addRegion("View", new Rectangle(165, 326, 20, 15));
		
		//Humans
		addHumanSprite("blueSuitMan", 8, 24);
		addHumanSprite("redSuitMan", 8, 32);
		addHumanSprite("greenSuitMan", 8, 40);
		addHumanSprite("yellowSuitMan", 8, 48);
		addHumanSprite("whiteSuitMan", 8, 56);
		addHumanSprite("ranger1", 8, 64);
		addHumanSprite("ranger2", 8, 72);
		addHumanSprite("officeWorker1", 8, 80);
		addHumanSprite("bandit1", 8, 88);
		addHumanSprite("cop1", 8, 96);
		addHumanSprite("cop2", 8, 104);
		addHumanSprite("bandit2", 8, 112);
		addHumanSprite("sherriff", 8, 120);
		addHumanSprite("ranger3", 8, 128);
		addHumanSprite("cop3", 8, 136);
		addHumanSprite("detective", 8, 144);
		addHumanSprite("officeWorker2", 8, 152);
		addHumanSprite("purpleDressLady", 8, 160);
		addHumanSprite("priest", 8, 168);
		addHumanSprite("priestess", 8, 176);
		addHumanSprite("plainWhiteGuy", 8, 184);
		
		//Monsters
		addMonsterSprite("walkingSlime1", 128, 24);
		addMonsterSprite("walkingSlime2", 128, 32);
		addMonsterSprite("slime2", 128, 40);
		addMonsterSprite("slime1", 128, 48);
		addMonsterSprite("rat", 128, 56);
		addMonsterSprite("glowingAlien1", 128, 64);
		addMonsterSprite("brownMass1", 128, 72);
		addMonsterSprite("floatingEye", 128, 80);
		addMonsterSprite("spider", 128, 88);
		addMonsterSprite("cat", 128, 96);
		addMonsterSprite("zombieWorker", 128, 104);
		addMonsterSprite("greenAlien", 128, 112);
		addMonsterSprite("whiteAlien", 128, 120);
		addMonsterSprite("blueAlien", 128, 128);
		addMonsterSprite("blackAlien", 128, 136);
		addMonsterSprite("fireGelAlien", 128, 144);
		addMonsterSprite("glowingAlien2", 128, 152);
		addMonsterSprite("snake", 128, 160);
		addMonsterSprite("brownMass2", 128, 168);
		addMonsterSprite("camoAlien", 128, 176);
		addMonsterSprite("purpleAlien", 128, 184);
		addMonsterSprite("paleAlien", 128, 192);
		addMonsterSprite("robot1", 128, 200);
		addMonsterSprite("robot2", 128, 208);
		addMonsterSprite("graySquid", 128, 216);
		
	}

	private void addHumanSprite(String key, int x, int y) {
		Rectangle frame = new Rectangle(x, y, 8, 8);
		
		spriteSheet.addRegion(key + "Right", frame);
		frame.x += 8; frame.width = 16;
		spriteSheet.addRegion(key + "MoveRight", frame);
		frame.x += 16; frame.width = 8;
		spriteSheet.addRegion(key + "Down", frame);
		frame.x += 8; frame.width = 16;
		spriteSheet.addRegion(key + "MoveDown", frame);
		frame.x += 16; frame.width = 8;
		spriteSheet.addRegion(key + "Left", frame);
		frame.x += 8; frame.width = 16;
		spriteSheet.addRegion(key + "MoveLeft", frame);
		frame.x += 16; frame.width = 8;
		spriteSheet.addRegion(key + "Up", frame);
		frame.x += 8; frame.width = 16;
		spriteSheet.addRegion(key + "MoveUp", frame);
		frame.x += 16; frame.width = 8;
		spriteSheet.addRegion(key + "Dead", frame);
	}
	
	private void addMonsterSprite(String key, int x, int y) {
		Rectangle frame = new Rectangle(x, y, 8, 8);
		
		spriteSheet.addRegion(key + "Right", frame);
		frame.x += 8; frame.width = 16;
		spriteSheet.addRegion(key + "MoveRight", frame);
		frame.x += 16; frame.width = 8;
		spriteSheet.addRegion(key + "Down", frame);
		frame.x += 8; frame.width = 16;
		spriteSheet.addRegion(key + "MoveDown", frame);
		frame.x += 16; frame.width = 8;
		spriteSheet.addRegion(key + "Left", frame);
		frame.x += 8; frame.width = 16;
		spriteSheet.addRegion(key + "MoveLeft", frame);
		frame.x += 16; frame.width = 8;
		spriteSheet.addRegion(key + "Up", frame);
		frame.x += 8; frame.width = 16;
		spriteSheet.addRegion(key + "MoveUp", frame);
	}
	
	@Override
	public Rectangle getBounds() {
		float width = Convert.pixelsToMeters(camera.viewportWidth);
		float height = Convert.pixelsToMeters(camera.viewportHeight);
		
		float x = Convert.pixelsToMeters(camera.position.x) - width / 2;
		float y = Convert.pixelsToMeters(camera.position.y) - height / 2;
		
		return new Rectangle(x, y, width, height);
	}

}
