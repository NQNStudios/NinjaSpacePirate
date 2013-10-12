package com.punchline.NinjaSpacePirate.gameplay.entities.systems.spawn.rows;

import com.punchline.NinjaSpacePirate.gameplay.entities.systems.spawn.TileArgs;
import com.punchline.NinjaSpacePirate.gameplay.entities.systems.spawn.TileRow;
import com.punchline.javalib.entities.EntityWorld;

/**
 * Row in the middle of a hull breach, where the vacuum of space enters the ship.
 * @author Natman64
 * @created Oct 12, 2013
 */
public class MidBreachRow extends TileRow {

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
		
	}
	
}
