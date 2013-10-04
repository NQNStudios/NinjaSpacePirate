package com.punchline.NinjaSpacePirate.gameplay.entities.systems;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.math.Vector2;
import com.punchline.NinjaSpacePirate.gameplay.entities.processes.PatrolProcess;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.systems.InputSystem;
import com.punchline.javalib.utils.Convert;

/**
 * The PlayerControlSystem.
 * @author Natman64
 *
 */
public class PlayerControlSystem extends InputSystem {
	
	private static final float PLAYER_SPEED = Convert.pixelsToMeters(15);
	
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
		player = null;
		
		super.processEntities();
	}

	@Override
	public boolean keyDown(int keycode) {
		if (player == null) return false;
		
		return false;
	}
	
}
