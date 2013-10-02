package com.punchline.NinjaSpacePirate.gameplay.entities.components.render;

import com.badlogic.gdx.math.Vector2;
import com.punchline.javalib.entities.components.render.Sprite;
import com.punchline.javalib.utils.SpriteSheet;

/**
 * Sprite implementation specific to sprites representing an Entity's view sensor.
 * @author Natman64
 *
 */
public class NPCViewSprite extends Sprite {

	/**
	 * Constructs an NPCViewSprite, setting its origin and position 
	 * @param spriteSheet The game's SpriteSheet. The Sprite will be constructed from the given sheet's "View" region.
	 */
	public NPCViewSprite(SpriteSheet spriteSheet) {
		super(spriteSheet, "View");
		
		setOrigin(new Vector2(0f, 8f));
		setPosition(new Vector2(14, 5.5f));
	}
	
}
