package com.natman.NinjaSpacePirate.gameplay.entities.components.render.npcs;

import com.badlogic.gdx.math.Vector2;
import com.lostcode.javalib.entities.components.generic.View;
import com.lostcode.javalib.utils.Convert;
import com.lostcode.javalib.utils.SpriteSheet;
import com.natman.NinjaSpacePirate.gameplay.entities.components.render.OldSprite;

/**
 * Sprite implementation specific to sprites representing an Entity's view sensor.
 * @author Natman64
 *
 */
public class NPCViewSprite extends OldSprite {

	private static final Vector2 LEFT_POSITION = Convert.pixelsToMeters(new Vector2(0, -1.5f));
	private static final Vector2 RIGHT_POSITION = Convert.pixelsToMeters(new Vector2(0, 1.5f));
	private static final Vector2 UP_POSITION = Convert.pixelsToMeters(new Vector2(2, -0.5f));
	private static final Vector2 DOWN_POSITION = Convert.pixelsToMeters(new Vector2(-2, -0.5f));
	
	private View view;
	
	/**
	 * Constructs an NPCViewSprite, setting its origin and position 
	 * @param spriteSheet The game's SpriteSheet. The Sprite will be constructed from the given sheet's "View" region.
	 */
	public NPCViewSprite(SpriteSheet spriteSheet, View view) {
		super(spriteSheet, "View");
		
		setOrigin(new Vector2(0, 7.5f));
		setPosition(new Vector2(14, 5.5f));
		
		this.view = view;
		
		view.setPosition(RIGHT_POSITION);
	}
	
	/**
	 * Destroys the view fixture.
	 */
	public void destroyView() {
		view.destroy();
	}
	
	@Override
	public void setRotation(float degrees) {
		float oldRotation = getRotation();
		
		super.setRotation(degrees);
		
		//Now the view fixture has to change its position to better fit the view sprite
		if (facingRight(degrees) && !facingRight(oldRotation)) {
			view.setPosition(RIGHT_POSITION);
		} else if (facingUp(degrees) && !facingUp(oldRotation)) {
			view.setPosition(UP_POSITION);
		} else if (facingLeft(degrees) && !facingLeft(oldRotation)) {
			view.setPosition(LEFT_POSITION);
		} else if (facingDown(degrees) && !facingDown(oldRotation)) {
			view.setPosition(DOWN_POSITION);
		}
		
		//Now move the view sprite so it always appears to come from the AnimatedSprite's eyes
		if (facingDown(degrees) && !facingDown(oldRotation)) {
			setOrigin(new Vector2(0f, 8f));
		} else if (facingDown(oldRotation) && !facingDown(degrees)) {
			setOrigin(new Vector2(0, 7.5f));
		}
	}
	
	private boolean facingLeft(float degrees) {
		return degrees >= 135 && degrees < 225;
	}
	
	private boolean facingRight(float degrees) {
		return degrees < 45 || degrees > 315;
	}
	
	private boolean facingUp(float degrees) {
		return degrees >= 45 && degrees < 135;
	}
	
	private boolean facingDown(float degrees) {
		return degrees >= 225 && degrees <= 315;
	}
	
}
