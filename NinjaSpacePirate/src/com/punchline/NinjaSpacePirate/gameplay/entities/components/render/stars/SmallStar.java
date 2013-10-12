package com.punchline.NinjaSpacePirate.gameplay.entities.components.render.stars;

import com.punchline.javalib.entities.components.render.Animation;
import com.punchline.javalib.utils.Random;
import com.punchline.javalib.utils.SpriteSheet;

/**
 * A small animated star sprite.
 * @author Natman64
 * @created Oct 12, 2013
 */
public class SmallStar extends Animation {

	private static Random r = new Random();
	
	/**
	 * Creates a small star sprite
	 * @param spriteSheet
	 */
	public SmallStar(SpriteSheet spriteSheet) {
		super(spriteSheet, key(), 2, 1, 1, 0, Animation.LOOP_PINGPONG, 0.3f);
	}
	
	private static String key() {
		return r.nextBoolean() ? "SmallStar0" : "SmallStar1";
	}

}
