package com.natman.NinjaSpacePirate.gameplay.entities.systems.spawn;

import com.lostcode.javalib.entities.EntityWorld;

/** 
 * Contains {@link TileArgs} for an entire row of tiles of size {@link #ROW_SIZE}. 
 */
public class TileRow {
	
	/** The size of a row of tiles. */
	public static final int ROW_SIZE = 7;
	
	/** The TileArgs for creating this row of tiles. */
	public TileArgs[] tiles = new TileArgs[ROW_SIZE];
	
	/** Constructs a TileRow.
	 * @param tiles The TileArgs array for creating this row of tiles. Must have size {@link #ROW_SIZE}. */
	public TileRow(TileArgs... tiles) {
		if (tiles.length != ROW_SIZE) {
			throw new IllegalArgumentException("Wrong number of tiles passed to TileRow constructor.");
		}
		
		for (int i = 0; i < ROW_SIZE; i++) {
			this.tiles[i] = tiles[i];
		}
	}
	
	/**
	 * Event for performing extra processing when the row is created, such as making entities.
	 * @param world Reference to the EntityWorld.
	 * @param y The y coordinate of the row.
	 */
	public void onCreated(EntityWorld world, float y) {
		
	}
	
}
