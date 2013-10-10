package com.punchline.NinjaSpacePirate.gameplay.entities.systems;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.math.Vector2;
import com.punchline.NinjaSpacePirate.gameplay.entities.components.render.PlayerSprite;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.components.physical.Velocity;
import com.punchline.javalib.entities.components.render.Renderable;
import com.punchline.javalib.entities.systems.InputSystem;

/**
 * The PlayerControlSystem.
 * @author Natman64
 *
 */
public class PlayerControlSystem extends InputSystem {
	
	private static final float MAX_HORIZONTAL_SPEED = 3f;
	
	private static final float MIN_VERTICAL_SPEED = 2f;
	private static final float MAX_VERTICAL_SPEED = 6f;
	private static final float AVG_VERTICAL_SPEED = (MIN_VERTICAL_SPEED + MAX_VERTICAL_SPEED) / 2f;
	
	private static final float FALLING_HORIZONTAL_SCL = 0.3f;
	private static final float FALLING_VERTICAL_SCL = 0.3f;
	
	private boolean movingLeft = false;
	private boolean movingRight = false;
	
	private boolean movingSlow = false;
	private boolean movingFast = false;
	
	/** Whether the game is currently taking input. */
	public boolean inputEnabled = true;
	
	/**
	 * Constructs the PlayerControlSystem.
	 * @param input
	 */
	public PlayerControlSystem(InputMultiplexer input) {
		super(input);
	}

	@Override
	public boolean canProcess(Entity e) {
		return e.getTag().equals("Player");
	}

	@Override
	public void processEntities() {
		super.processEntities();
	}
	
	@Override
	protected void process(Entity e) {
		PlayerSprite sprite = (PlayerSprite) e.getComponent(Renderable.class);
		Velocity v = e.getComponent(Velocity.class);
		
		if (sprite.getState().equals("Dead")) {
			v.setLinearVelocity(new Vector2(0, 0));
			inputEnabled = false;
			
			return;
		}
		
		if (!inputEnabled) return;
		
		if (sprite.isFalling()) {
			Vector2 velocity = v.getLinearVelocity();
			velocity.x *= FALLING_HORIZONTAL_SCL;
			velocity.y *= FALLING_VERTICAL_SCL;
			v.setLinearVelocity(velocity);
			inputEnabled = false;
			
			return;
		}
		
		//set x velocity based on input flags
		float xVelocity = 0f;
		
		if (movingLeft) {
			xVelocity = -MAX_HORIZONTAL_SPEED;
		} else if (movingRight) {
			xVelocity = MAX_HORIZONTAL_SPEED;
		}
		
		//set y velocity based on input flags
		float yVelocity = AVG_VERTICAL_SPEED;
		
		if (movingSlow) {
			yVelocity = MIN_VERTICAL_SPEED;
		} else if (movingFast) {
			yVelocity = MAX_VERTICAL_SPEED;
		}
		
		//set entity velocity
		v.setLinearVelocity(new Vector2(xVelocity, yVelocity));
	}

	//region Keyboard Controls
	
	@Override
	public boolean keyDown(int keycode) {
		switch (keycode) {
		
		case Keys.LEFT:
			movingLeft = true;
			return true;
			
		case Keys.RIGHT:
			movingRight = true;
			return true;
			
		case Keys.DOWN:
			movingSlow = true;
			break;
			
		case Keys.UP:
			movingFast = true;
			break;
			
		}
		
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		switch (keycode) {
		
		case Keys.LEFT:
			movingLeft = false;
			return true;
			
		case Keys.RIGHT:
			movingRight = false;
			return true;
			
		case Keys.DOWN:
			movingSlow = false;
			break;
			
		case Keys.UP:
			movingFast = false;
			break;
		
		}
			
		return false;
	}

	//endregion
	
	//region Tilt Controls
	
	@Override
	protected void onTiltX(float x) {
		
	}

	@Override
	protected void onTiltY(float y) {
		
	}

	@Override
	protected void onTiltZ(float z) {
		
	}
	
	//endregion
	
}
