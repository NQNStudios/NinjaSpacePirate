package com.punchline.NinjaSpacePirate.gameplay.entities.components.render;

import com.badlogic.gdx.utils.Array;
import com.punchline.javalib.entities.components.generic.View;
import com.punchline.javalib.entities.components.render.MultiRenderable;
import com.punchline.javalib.utils.SpriteSheet;

/**
 * Wrapper class for the generic MultiRenderable that will be used by NPCs in NinjaSpacePirate.
 * @author Natman64
 *
 */
public class NPCMultiRenderable extends MultiRenderable {
	
	private Array<Integer> reverseOrder = new Array<Integer>();
	
	private NPCAnimatedSprite bodySprite;
	private NPCViewSprite viewSprite;
	
	/**
	 * Constructs an NPCMultiRenderable.
	 * @param spriteSheet The game's SpriteSheet.
	 * @param spritePrefix The prefix of this MultiRenderable's AnimatedSprite.
	 * @param view The NPC's View sensor, whose position has to change to reflect the sprite's state.
	 */
	public NPCMultiRenderable(SpriteSheet spriteSheet, String spritePrefix, View view) {
		super(new NPCAnimatedSprite(spriteSheet, spritePrefix), new NPCViewSprite(spriteSheet, view));
		
		bodySprite = (NPCAnimatedSprite) children.get(0);
		viewSprite = (NPCViewSprite) children.get(1);
		
		reverseOrder.add(1);
		reverseOrder.add(0);
	}
	
	/**
	 * @return Whether the body sprite is animating to reflect movement.
	 */
	public boolean isMoving() {
		return bodySprite.moving;
	}
	
	/**
	 * Sets the body sprite's state to reflect whether the Entity is moving or not.
	 * @param moving
	 */
	public void setMoving(boolean moving) {
		bodySprite.moving = moving;
	}
	
	public boolean isDead() {
		return bodySprite.getState().equals("Dead");
	}
	
	/**
	 * Sets the body sprite's state and removes the view sprite to reflect the Entity's dead status.
	 */
	public void die() {
		if (isDead()) return;
		
		bodySprite.setState("Dead", false);
		viewSprite.destroyView();
		children.removeValue(viewSprite, true);
	}
	
	@Override
	public void setRotation(float degrees) {
		float oldRotation = getRotation();
		
		super.setRotation(degrees);
		
		//The View sprite and AnimatedSprite need to be reordered to maintain the illusion that the view sprite
		//is emerging from the AnimatedSprite's eyes.
		
		if (bodySprite.getState().equals("Dead")) return; //dead sprites don't reorder, they have no view sprite
		
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
