package com.punchline.NinjaSpacePirate.gameplay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.punchline.NinjaSpacePirate.gameplay.entities.PlayerTemplate;
import com.punchline.NinjaSpacePirate.gameplay.entities.systems.PlayerControlSystem;
import com.punchline.javalib.entities.EntityWorld;
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
	}

	@Override
	protected void buildTemplates() {
		super.buildTemplates();
		
		addTemplate("Player", new PlayerTemplate());
	}
	
	@Override
	protected void buildSystems() {
		super.buildSystems();
		
		systems.addSystem(new PlayerControlSystem(input));
	}
	
	@Override
	protected void buildEntities() {
		createEntity("Player");
	}
	
	@Override
	protected void buildSpriteSheet() {
		
		spriteSheet = new SpriteSheet(new Texture(Gdx.files.internal("data/lofi_scifi_v2_trans.png")));
		
		//Humans
		addAnimatedSprite("blueSuitMan", 8, 24);
		addAnimatedSprite("redSuitMan", 8, 32);
		addAnimatedSprite("greenSuitMan", 8, 40);
		addAnimatedSprite("yellowSuitMan", 8, 48);
		addAnimatedSprite("whiteSuitMan", 8, 56);
		addAnimatedSprite("ranger1", 8, 64);
		addAnimatedSprite("ranger2", 8, 72);
		addAnimatedSprite("officeWorker1", 8, 80);
		addAnimatedSprite("bandit1", 8, 88);
		addAnimatedSprite("cop1", 8, 96);
		addAnimatedSprite("cop2", 8, 104);
		addAnimatedSprite("bandit2", 8, 112);
		addAnimatedSprite("sherriff", 8, 120);
		addAnimatedSprite("ranger3", 8, 128);
		addAnimatedSprite("cop3", 8, 136);
		addAnimatedSprite("detective", 8, 144);
		addAnimatedSprite("officeWorker2", 8, 152);
		addAnimatedSprite("purpleDressLady", 8, 160);
		addAnimatedSprite("priest", 8, 168);
		addAnimatedSprite("priestess", 8, 176);
		addAnimatedSprite("plainWhiteGuy", 8, 184);
		
		//Monsters
		addAnimatedSprite("walkingSlime1", 128, 24);
		addAnimatedSprite("walkingSlime2", 128, 32);
		addAnimatedSprite("slime2", 128, 40);
		addAnimatedSprite("slime1", 128, 48);
		addAnimatedSprite("rat", 128, 56);
		addAnimatedSprite("glowingAlien1", 128, 64);
		addAnimatedSprite("brownMass1", 128, 72);
		addAnimatedSprite("floatingEye", 128, 80);
		addAnimatedSprite("spider", 128, 88);
		addAnimatedSprite("cat", 128, 96);
		addAnimatedSprite("zombieWorker", 128, 104);
		addAnimatedSprite("greenAlien", 128, 112);
		addAnimatedSprite("whiteAlien", 128, 120);
		addAnimatedSprite("blueAlien", 128, 128);
		addAnimatedSprite("blackAlien", 128, 136);
		addAnimatedSprite("fireGelAlien", 128, 144);
		addAnimatedSprite("glowingAlien2", 128, 152);
		addAnimatedSprite("snake", 128, 160);
		addAnimatedSprite("brownMass2", 128, 168);
		addAnimatedSprite("camoAlien", 128, 176);
		addAnimatedSprite("purpleAlien", 128, 184);
		addAnimatedSprite("paleAlien", 128, 192);
		addAnimatedSprite("robot1", 128, 200);
		addAnimatedSprite("robot2", 128, 208);
		addAnimatedSprite("graySquid", 128, 216);
		
	}

	private void addAnimatedSprite(String key, int x, int y) {
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
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle(0, 0, 480, 800);
	}

}
