package com.natman.NinjaSpacePirate.gameplay.entities.components.render.scenery;

import com.badlogic.gdx.math.Vector2;
import com.lostcode.javalib.entities.components.render.Animation;
import com.lostcode.javalib.utils.Random;
import com.lostcode.javalib.utils.SpriteSheet;

/**
 * A small animated star sprite.
 * @author Natman64
 * @created Oct 12, 2013
 */
public class SmallStar extends Animation {

	/** The scale of the star sprites. */
	public static final float STAR_SCALE = 0.2f;
	
	private static Random r = new Random();
	
	/**
	 * Creates a small star sprite
	 * @param spriteSheet
	 * @param position
	 */
	public SmallStar(SpriteSheet spriteSheet, Vector2 position) {
		super(spriteSheet, key(), 2, 1, 1, 0, Animation.LOOP_PINGPONG, 0.3f);
		
		setOrigin(position);
		setLayer(0);
		setScale(STAR_SCALE, STAR_SCALE);
		setStateTime(r.nextFloat(5f));
	}
	
	private static String key() {
		return r.nextBoolean() ? "SmallStar0" : "SmallStar1";
	}

}
