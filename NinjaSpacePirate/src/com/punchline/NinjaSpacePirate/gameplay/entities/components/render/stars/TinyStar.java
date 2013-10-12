package com.punchline.NinjaSpacePirate.gameplay.entities.components.render.stars;

import com.badlogic.gdx.math.Vector2;
import com.punchline.javalib.entities.components.render.Sprite;
import com.punchline.javalib.utils.Random;
import com.punchline.javalib.utils.SpriteSheet;

/**
 * The smallest star sprite.
 * @author Natman64
 * @created Oct 12, 2013
 */
public class TinyStar extends Sprite {

	private static Random r = new Random();
	
	/**
	 * Creates a tiny star sprite
	 * @param sheet
	 * @param position
	 */
	public TinyStar(SpriteSheet sheet, Vector2 position) {
		super(sheet, key());
		
		setPosition(position);
		setLayer(0);
		setScale(0.2f, 0.2f);
	}
	
	private static String key() {
		return r.nextBoolean() ? "TinyStar0" : "TinyStar1";
	}
	
}
