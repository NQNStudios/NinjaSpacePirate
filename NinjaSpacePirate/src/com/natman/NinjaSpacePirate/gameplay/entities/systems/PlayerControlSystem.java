package com.natman.NinjaSpacePirate.gameplay.entities.systems;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.math.Vector2;
import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.components.physical.Velocity;
import com.lostcode.javalib.entities.components.render.Renderable;
import com.lostcode.javalib.entities.systems.InputSystem;
import com.natman.NinjaSpacePirate.gameplay.entities.components.render.PlayerSprite;
import com.natman.NinjaSpacePirate.gameplay.entities.processes.powerups.ReverseControlPowerup;
import com.natman.NinjaSpacePirate.gameplay.entities.processes.powerups.SicknessPowerup;
import com.natman.NinjaSpacePirate.gameplay.entities.processes.powerups.SpeedLockPowerup;

/**
 * The PlayerControlSystem.
 * @author Natman64
 *
 */
public class PlayerControlSystem extends InputSystem {
	
	/** How fast the player accelerates */
	public static final float SPEED_DELTA = 0.06f;
	
	private static final float MAX_HORIZONTAL_SPEED = 3f;
	
	private static final float FALLING_HORIZONTAL_SCL = 0.3f;
	private static final float FALLING_VERTICAL_SCL = 0.3f;
	
	private static final float SLOW_SPEED_MODIFIER = .6f;
	private static final float FAST_SPEED_MODIFIER = 2f;
	
	private static final float MAX_SPEED = 20f;
	
	private static final float HORIZONTAL_TILT_MAX = 3f;
	
	private static final float VERTICAL_TILT_MIN = -3f;
	private static final float VERTICAL_TILT_MAX = 3f;
	
	private static final float START_SPEED = 3f;
	
	private boolean speedLock;
	private boolean wasSpeedLock;
	
	private boolean movingLeft = false;
	private boolean movingRight = false;
	
	private boolean movingSlow = false;
	private boolean movingFast = false;
	
	private float horizontalTilt = 0f;
	private float verticalTilt = 0f;
	
	/** The player's normal running speed. */
	public static float movementSpeed = START_SPEED;
	
	/** Whether the game is currently taking input. */
	public boolean inputEnabled = true;
	
	/**
	 * Constructs the PlayerControlSystem.
	 * @param input
	 */
	public PlayerControlSystem(InputMultiplexer input) {
		super(input);
		
		movementSpeed = START_SPEED;
		
		calibrateTilt();
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
		speedLock = e.hasComponent(SpeedLockPowerup.class);
		
		if (wasSpeedLock && !speedLock) {
			movingSlow = Gdx.input.isKeyPressed(Keys.DOWN);
			movingFast = Gdx.input.isKeyPressed(Keys.UP);
		}
		
		boolean reversed = e.hasComponent(ReverseControlPowerup.class);
		
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
		
		if (horizontalTilt != 0) {
			xVelocity = Math.min(
					MAX_HORIZONTAL_SPEED * -horizontalTilt / HORIZONTAL_TILT_MAX, 
					MAX_HORIZONTAL_SPEED);
		}
		
		//increase movement speed to ramp up the challenge
		movementSpeed += SPEED_DELTA * deltaSeconds();
		
		//apply a reasonable maximum
		if (movementSpeed > MAX_SPEED) movementSpeed = MAX_SPEED;
		
		//set y velocity based on input flags
		float yVelocity = movementSpeed;
		
		if (movingSlow) {
			yVelocity *= (reversed ? FAST_SPEED_MODIFIER : SLOW_SPEED_MODIFIER);
		} else if (movingFast) {
			yVelocity *= (reversed ? SLOW_SPEED_MODIFIER : FAST_SPEED_MODIFIER);
		}
		
		if (verticalTilt != 0 && !speedLock) {
			float tilt = (Math.min(Math.max(VERTICAL_TILT_MIN, -verticalTilt), VERTICAL_TILT_MAX) + VERTICAL_TILT_MAX) / (2 * VERTICAL_TILT_MAX);
			
			if (reversed) {
				tilt = 1 - tilt;
			}
			
			float speedModifier = SLOW_SPEED_MODIFIER + 
					((FAST_SPEED_MODIFIER - SLOW_SPEED_MODIFIER) * tilt);

			yVelocity = movementSpeed;
			yVelocity *= speedModifier;
		}
		
		//set entity velocity
		if (reversed) {
			xVelocity = -xVelocity;
		}
		
		if (e.hasComponent(SicknessPowerup.class)) {
			SicknessPowerup p = e.getComponent(SicknessPowerup.class);
			
			xVelocity = (float) (Math.sin(p.elapsed * 5) * MAX_HORIZONTAL_SPEED);
		}
		
		v.setLinearVelocity(new Vector2(xVelocity, yVelocity));
		
		sprite.timeCoefficient = yVelocity / movementSpeed / 2f;
		
		wasSpeedLock = speedLock;
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
			if (!speedLock) movingSlow = true;
			return true;
			
		case Keys.UP:
			if (!speedLock) movingFast = true;
			return true;
			
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
			if (!speedLock) movingSlow = false;
			return true;
			
		case Keys.UP:
			if (!speedLock) movingFast = false;
			return true;
		
		}
			
		return false;
	}

	//endregion
	
	//region Tilt Controls
	
	@Override
	protected void onTiltX(float x) {
		horizontalTilt = x;
	}

	@Override
	protected void onTiltY(float y) {
		verticalTilt = y;
	}

	@Override
	protected void onTiltZ(float z) {
		
	}
	
	//endregion
	
}
