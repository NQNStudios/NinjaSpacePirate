package com.punchline.NinjaSpacePirate.gameplay.entities.components.render.scenery;

import com.badlogic.gdx.math.Vector2;
import com.punchline.javalib.entities.EntityWorld;
import com.punchline.javalib.entities.components.render.MultiRenderable;
import com.punchline.javalib.utils.Random;

/**
 * A mini star field to show through a hull breach
 * @author Natman64
 * 
 */
public class MiniStarField extends MultiRenderable {

	private static final int SMALL_STARS = 40;
	
	private static final float OFFSET_RADIUS = 16 / SmallStar.STAR_SCALE;
	
	private Random r = new Random();
	
	/**
	 * Creates a star field
	 * @param spriteSheet
	 */
	public MiniStarField(EntityWorld world) {
		super(new SmallStar(world.getSpriteSheet(), new Vector2()));
		
		for (int i = 0; i < SMALL_STARS; i++) {
			children.add(new SmallStar(world.getSpriteSheet(), randomOffset()));
		}
	}
	
	private Vector2 randomOffset() {
		float x = r.nextFloat(-1, 1);
		float y = r.nextFloat(-1, 1);
		
		return new Vector2(x, y).scl(OFFSET_RADIUS);
	}

}
