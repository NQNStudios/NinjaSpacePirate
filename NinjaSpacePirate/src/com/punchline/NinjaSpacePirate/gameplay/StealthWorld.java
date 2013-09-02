package com.punchline.NinjaSpacePirate.gameplay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
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
	}

	@Override
	protected void buildSpriteSheet() {
		
		spriteSheet = new SpriteSheet(new Texture(Gdx.files.internal("lofi_scifi.png")));
		
		//Humans
		spriteSheet.addAnimatedSprite("blueSuitMan", new Rectangle(8, 24, 96, 8));
		spriteSheet.addAnimatedSprite("redSuitMan", new Rectangle(8, 32, 96, 8));
		spriteSheet.addAnimatedSprite("greenSuitMan", new Rectangle(8, 40, 96, 8));
		spriteSheet.addAnimatedSprite("yellowSuitMan", new Rectangle(8, 48, 96, 8));
		spriteSheet.addAnimatedSprite("whiteSuitMan", new Rectangle(8, 56, 96, 8));
		spriteSheet.addAnimatedSprite("ranger1", new Rectangle(8, 64, 96, 8));
		spriteSheet.addAnimatedSprite("ranger2", new Rectangle(8, 72, 96, 8));
		spriteSheet.addAnimatedSprite("officeWorker1", new Rectangle(8, 80, 96, 8));
		spriteSheet.addAnimatedSprite("bandit1", new Rectangle(8, 88, 96, 8));
		spriteSheet.addAnimatedSprite("cop1", new Rectangle(8, 96, 96, 8));
		spriteSheet.addAnimatedSprite("cop2", new Rectangle(8, 104, 96, 8));
		spriteSheet.addAnimatedSprite("bandit2", new Rectangle(8, 112, 96, 8));
		spriteSheet.addAnimatedSprite("sherriff", new Rectangle(8, 120, 96, 8));
		spriteSheet.addAnimatedSprite("ranger3", new Rectangle(8, 128, 96, 8));
		spriteSheet.addAnimatedSprite("cop3", new Rectangle(8, 136, 96, 8));
		spriteSheet.addAnimatedSprite("detective", new Rectangle(8, 144, 96, 8));
		spriteSheet.addAnimatedSprite("officeWorker2", new Rectangle(8, 152, 96, 8));
		spriteSheet.addAnimatedSprite("purpleDressLady", new Rectangle(8, 160, 96, 8));
		spriteSheet.addAnimatedSprite("priest", new Rectangle(8, 168, 96, 8));
		spriteSheet.addAnimatedSprite("priestess", new Rectangle(8, 176, 96, 8));
		spriteSheet.addAnimatedSprite("plainWhiteGuy", new Rectangle(8, 184, 96, 8));
		
		//Monsters
		spriteSheet.addAnimatedSprite("walkingSlime1", new Rectangle(128, 24, 96, 8));
		spriteSheet.addAnimatedSprite("walkingSlime2", new Rectangle(128, 32, 96, 8));
		spriteSheet.addAnimatedSprite("slime2", new Rectangle(128, 40, 96, 8));
		spriteSheet.addAnimatedSprite("slime1", new Rectangle(128, 48, 96, 8));
		spriteSheet.addAnimatedSprite("rat", new Rectangle(128, 56, 96, 8));
		spriteSheet.addAnimatedSprite("glowingAlien1", new Rectangle(128, 64, 96, 8));
		spriteSheet.addAnimatedSprite("brownMass1", new Rectangle(128, 72, 96, 8));
		spriteSheet.addAnimatedSprite("floatingEye", new Rectangle(128, 80, 96, 8));
		spriteSheet.addAnimatedSprite("spider", new Rectangle(128, 88, 96, 8));
		spriteSheet.addAnimatedSprite("cat", new Rectangle(128, 96, 96, 8));
		spriteSheet.addAnimatedSprite("zombieWorker", new Rectangle(128, 104, 96, 8));
		spriteSheet.addAnimatedSprite("greenAlien", new Rectangle(128, 112, 96, 8));
		spriteSheet.addAnimatedSprite("whiteAlien", new Rectangle(128, 120, 96, 8));
		spriteSheet.addAnimatedSprite("blueAlien", new Rectangle(128, 128, 96, 8));
		spriteSheet.addAnimatedSprite("blackAlien", new Rectangle(128, 136, 96, 8));
		spriteSheet.addAnimatedSprite("fireGelAlien", new Rectangle(128, 144, 96, 8));
		spriteSheet.addAnimatedSprite("glowingAlien2", new Rectangle(128, 152, 96, 8));
		spriteSheet.addAnimatedSprite("snake", new Rectangle(128, 160, 96, 8));
		spriteSheet.addAnimatedSprite("brownMass2", new Rectangle(128, 168, 96, 8));
		spriteSheet.addAnimatedSprite("camoAlien", new Rectangle(128, 176, 96, 8));
		spriteSheet.addAnimatedSprite("purpleAlien", new Rectangle(128, 184, 96, 8));
		spriteSheet.addAnimatedSprite("paleAlien", new Rectangle(128, 192, 96, 8));
		spriteSheet.addAnimatedSprite("robot1", new Rectangle(128, 200, 96, 8));
		spriteSheet.addAnimatedSprite("robot2", new Rectangle(128, 208, 96, 8));
		spriteSheet.addAnimatedSprite("graySquid", new Rectangle(128, 216, 96, 8));
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(0, 0, 480, 800);
	}

}
