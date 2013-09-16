package com.punchline.NinjaSpacePirate.gameplay.entities.systems;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.utils.Array;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.components.physical.Body;
import com.punchline.javalib.entities.components.render.AnimatedSprite;
import com.punchline.javalib.entities.components.render.MultiRenderable;
import com.punchline.javalib.entities.systems.InputSystem;

/**
 * The PlayerControlSystem.
 * @author Natman64
 *
 */
public class PlayerControlSystem extends InputSystem {
	
	private Entity player;
	private Array<Integer> reverseOrder = new Array<Integer>();
	private boolean changeOrder = false;
	
	/**
	 * Constructs the PlayerControlSystem.
	 * @param input
	 */
	public PlayerControlSystem(InputMultiplexer input) {
		super(input);
		
		reverseOrder.add(1);
		reverseOrder.add(0);
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
		
		MultiRenderable mr = (MultiRenderable) player.getComponent(MultiRenderable.class);
		
		AnimatedSprite sprite = (AnimatedSprite) mr.getBase();
		
		Body b = (Body) player.getComponent(Body.class);
		
		if (keycode == Keys.LEFT) {
			if (changeOrder) {
				mr.reorder(reverseOrder);
				changeOrder = false;
			}
			
			sprite.setState("MoveLeft", true);
			b.setRotation((float)Math.toRadians(180));
			return true;
		} else if (keycode == Keys.DOWN) {
			if (changeOrder) {
				mr.reorder(reverseOrder);
				changeOrder = false;
			}
			
			sprite.setState("MoveDown", true);
			b.setRotation((float)Math.toRadians(270));
			return true;
		} else if (keycode == Keys.UP) {
			if (!changeOrder) {
				mr.reorder(reverseOrder);
				changeOrder = true;
			}
			
			sprite.setState("MoveUp", true);
			b.setRotation((float)Math.toRadians(90));
			return true;
		} else if (keycode == Keys.RIGHT) {
			if (changeOrder) {
				mr.reorder(reverseOrder);
				changeOrder = false;
			}
			
			sprite.setState("MoveRight", true);
			b.setRotation(0);
			return true;
		} else if (keycode == Keys.ESCAPE) {
			if (changeOrder) {
				mr.reorder(reverseOrder);
				changeOrder = false;
			}
			
			if (sprite.hasState("Dead")) sprite.setState("Dead", true);
			return true;
		} else if (keycode == Keys.SPACE) {
			if (changeOrder) {
				mr.reorder(reverseOrder);
				changeOrder = false;
			}
			
			String state = sprite.getState();
			state = state.replace("Move", "");
			sprite.setState(state, true);
			return true;
		}
		
		return false;
	}
	
}
