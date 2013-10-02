package com.punchline.NinjaSpacePirate.gameplay.entities.components.render;

import com.badlogic.gdx.utils.Array;
import com.punchline.javalib.entities.components.render.MultiRenderable;
import com.punchline.javalib.utils.SpriteSheet;

/**
 * Wrapper class for the generic MultiRenderable that will be used by NPCs in NinjaSpacePirate.
 * @author Natman64
 *
 */
public class NPCMultiRenderable extends MultiRenderable {

	private Array<Integer> reverseOrder = new Array<Integer>();
	
	/**
	 * Constructs an NPCMultiRenderable.
	 * @param spriteSheet The game's SpriteSheet.
	 * @param spritePrefix The prefix of this MultiRenderable's AnimatedSprite.
	 */
	public NPCMultiRenderable(SpriteSheet spriteSheet, String spritePrefix) {
		super(new NPCAnimatedSprite(spriteSheet, spritePrefix), new NPCViewSprite(spriteSheet));
		
		reverseOrder.add(1);
		reverseOrder.add(0);
	}

	@Override
	public void setRotation(float degrees) {
		float oldRotation = getRotation();
		
		super.setRotation(degrees);
		
		//The View sprite and AnimatedSprite need to be reordered to maintain the illusion that the view sprite
		//is emerging from the AnimatedSprite's eyes.
		
		if (!facingUp(oldRotation) && facingUp(degrees)) {
			reorder(reverseOrder); //Reverse the order if the sprite is now facing up.
		} else if (facingUp(oldRotation) && !facingUp(degrees)) {
			reorder(reverseOrder); //Also reverse the order if the new order is no longer facing up.
		}
		
	}
	
	private boolean facingUp(float degrees) {
		return degrees >= 45 && degrees < 135;
	}
	
}
