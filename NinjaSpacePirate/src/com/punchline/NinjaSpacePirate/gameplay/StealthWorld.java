package com.punchline.NinjaSpacePirate.gameplay;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.punchline.javalib.entities.EntityWorld;

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
		//TODO Set up the SpriteSheet.
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(0, 0, 480, 800);
	}

}
