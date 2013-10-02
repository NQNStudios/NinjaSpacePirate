package com.punchline.NinjaSpacePirate.gameplay.entities.components;

import com.punchline.javalib.entities.components.render.AnimatedSprite;
import com.punchline.javalib.utils.SpriteSheet;

/**
 * AnimatedSprite implementation that handles many things that will be shared by all NPC sprites.
 * For instance, NPCSprites need to change their animation state key to reflect the rotation of their Entity's
 * Box2D body. This is handled by overriding setRotation().
 * @author Natman64
 *
 */
public class NPCSprite extends AnimatedSprite {

	/**
	 * Whether the Entity that owns this Sprite is moving and the Sprite should animate to reflect this.
	 */
	public boolean moving;
	
	/**
	 * Constructs an NPCSprite.
	 * @param spriteSheet
	 * @param prefix The prefix of this Sprite's texture regions in the given SpriteSheet.
	 * @param frameWidth The width of this Sprite's frames.
	 * @param playType The type of animation this Sprite needs. Animation.LOOP, for example.
	 * @param frameDuration The duration of this Sprite's frames.
	 */
	public NPCSprite(SpriteSheet spriteSheet, String prefix, int frameWidth, int playType, float frameDuration) {
		super(spriteSheet, prefix, frameWidth, playType, frameDuration);
	}
	
	@Override
	public void setRotation(float degrees) {
		
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
