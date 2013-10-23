package com.punchline.NinjaSpacePirate.gameplay.entities.components.render;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.punchline.javalib.entities.components.render.AnimatedSprite;
import com.punchline.javalib.entities.components.render.Animation;
import com.punchline.javalib.utils.SoundManager;
import com.punchline.javalib.utils.SpriteSheet;

/**
 * Sprite for the player.
 * @author Natman64
 *
 */
public class PlayerSprite extends AnimatedSprite {

	private static final float SCALE_DELTA = 0.005f;
	
	private float scaleX = 1f;
	private float scaleY = 1f;
	private boolean falling = false;
	
	public float timeCoefficient = 1f;
	
	/**
	 * Creates a player sprite.
	 * @param spriteSheet The game sprite sheet.
	 */
	public PlayerSprite(SpriteSheet spriteSheet) {
		super(spriteSheet, "Player", 8, 1, 0, Animation.LOOP_PINGPONG, 0.3f);
		
		setOrigin(new Vector2(4.5f, 4f));
		setLayer(5);
		setState("Run", false);
	}
	
	/**
	 * @return Whether the player is falling.
	 */
	public boolean isFalling() {
		return falling;
	}

	@Override
	public void setState(String state, boolean keepStateTime) throws IllegalArgumentException {
		if (state.equals("Falling")) {
			falling = true;
			setLayer(0);
			setState("Stationary", false);
			SoundManager.playSound("Player_Fall");
		} else {
			super.setState(state, keepStateTime);
		}
	}
	
	@Override
	public void setScale(float scaleX, float scaleY) {
		super.setScale(scaleX, scaleY);
		
		this.scaleX = scaleX;
		this.scaleY = scaleY;
	}

	@Override
	public void draw(SpriteBatch spriteBatch, float deltaSeconds) {
		if (falling) {
			scaleX -= SCALE_DELTA; 
			scaleY -= SCALE_DELTA;
			
			if (scaleX < 0) scaleX = 0f;
			if (scaleY < 0) scaleY = 0f;
			
			setScale(scaleX - SCALE_DELTA, scaleY - SCALE_DELTA);
		}
		
		super.draw(spriteBatch, deltaSeconds * timeCoefficient);
	}

}
