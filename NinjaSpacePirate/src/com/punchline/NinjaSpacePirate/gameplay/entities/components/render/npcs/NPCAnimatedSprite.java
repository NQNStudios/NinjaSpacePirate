package com.punchline.NinjaSpacePirate.gameplay.entities.components.render.npcs;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.punchline.javalib.entities.components.render.AnimatedSprite;
import com.punchline.javalib.utils.SpriteSheet;

/**
 * AnimatedSprite implementation that handles many things that will be shared by all NPC sprites.
 * For instance, NPCSprites need to change their animation state key to reflect the rotation of their Entity's
 * Box2D body. This is handled by overriding setRotation().
 * @author Natman64
 *
 */
public class NPCAnimatedSprite extends AnimatedSprite {

	private static final int SPRITE_LAYER = 5;
	
	/**
	 * Whether the Entity that owns this Sprite is moving and the Sprite should animate to reflect this.
	 */
	public boolean moving = false;
	
	/**
	 * Constructs an NPCSprite.
	 * @param spriteSheet
	 * @param prefix The prefix of this Sprite's texture regions in the given SpriteSheet.
	 */
	public NPCAnimatedSprite(SpriteSheet spriteSheet, String prefix) {
		super(spriteSheet, prefix, 8, 1, 1, Animation.LOOP_PINGPONG, 0.3f);
		
		setLayer(SPRITE_LAYER);
	}
	
	@Override
	public void setRotation(float degrees) {
		super.setRotation(degrees);
		
		if (getState().equals("Dead")) return; //Dead sprites don't rotate
		
		String state = "";
		
		if (moving) state += "Move";
		
		if (degrees < 45 || degrees > 315) {
			state += "Right";
		} else if (degrees >= 45 && degrees < 135) {
			state += "Up";
		} else if (degrees >= 135 && degrees < 225) {
			state += "Left";
		} else {
			state += "Down";
		}
		
		setState(state, true);
	}

}
