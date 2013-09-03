package com.punchline.NinjaSpacePirate.gameplay.entities.systems;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.components.render.AnimatedSprite;
import com.punchline.javalib.entities.systems.InputSystem;

/**
 * The PlayerControlSystem.
 * @author Natman64
 *
 */
public class PlayerControlSystem extends InputSystem {
	
	private Entity player;
	
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
	protected void process(Entity e) {
		if (player == null) player = e;
	}

	@Override
	public void processEntities() {
		super.processEntities();
	}

	@Override
	public boolean keyDown(int keycode) {
		if (player == null) return false;
		
		AnimatedSprite sprite = (AnimatedSprite) player.getComponent(AnimatedSprite.class);
		
		if (keycode == Keys.LEFT) {
			sprite.setState("MoveLeft", true);
			return true;
		} else if (keycode == Keys.DOWN) {
			sprite.setState("MoveDown", true);
			return true;
		} else if (keycode == Keys.UP) {
			sprite.setState("MoveUp", true);
			return true;
		} else if (keycode == Keys.RIGHT) {
			sprite.setState("MoveRight", true);
			return true;
		} else if (keycode == Keys.ESCAPE) {
			sprite.setState("Dead", true);
			return true;
		}
		
		return false;
	}
	
}
