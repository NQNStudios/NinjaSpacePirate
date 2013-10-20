package com.punchline.NinjaSpacePirate.gameplay.entities.systems.spawn.rows;

import com.badlogic.gdx.math.Vector2;
import com.punchline.NinjaSpacePirate.gameplay.StealthWorld;
import com.punchline.NinjaSpacePirate.gameplay.entities.systems.spawn.TileArgs;
import com.punchline.NinjaSpacePirate.gameplay.entities.systems.spawn.TileRow;
import com.punchline.javalib.entities.EntityWorld;
import com.punchline.javalib.utils.SoundManager;

/**
 * Row in the middle of a hull breach, where the vacuum of space enters the ship.
 * @author Natman64
 * @created Oct 12, 2013
 */
public class MidBreachRow extends TileRow {

	private static final float FORCE = 2.5f;
	
	private boolean leftSide;
	
	/**
	 * Constructs the row.
	 * @param leftSide Is the breach on the left side?
	 */
	public MidBreachRow(boolean leftSide, TileArgs[] args) {
		super(args);
		
		this.leftSide = leftSide;
	}

	@Override
	public void onCreated(EntityWorld world, float y) {
		Vector2 position = new Vector2(0, y);
		
		position.x = leftSide ? -4 : 4;
		
		world.createEntity("Void", ((StealthWorld) world).getPlayer(), position, FORCE);
		
		SoundManager.playSound("Space_Noise");
	}
	
}
