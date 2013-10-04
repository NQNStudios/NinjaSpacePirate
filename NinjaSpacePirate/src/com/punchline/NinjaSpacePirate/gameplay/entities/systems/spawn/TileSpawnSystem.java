package com.punchline.NinjaSpacePirate.gameplay.entities.systems.spawn;

import java.util.HashMap;
import java.util.LinkedList;

import com.badlogic.gdx.math.Vector2;
import com.punchline.NinjaSpacePirate.gameplay.StealthWorld;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.systems.EntitySystem;

/**
 * System that spawns the tiles making up the game world.
 * @author Natman64
 *
 */
public class TileSpawnSystem extends EntitySystem {
	
	//region Inner Classes
	
	/** Contains the information needed to construct a tile, minus its position. */
	private class TileArgs {
		/** The key of the sprite's TextureRegion in the game's SpriteSheet. */
		public String spriteKey;
		
		/** Whether this tile is a physical obstacle. */
		public boolean blocked;
		
		/** Constructs TileArgs.
		 * @param spriteKey The key of the tile's sprite.
		 * @param blocked Whether this tile is a physical obstacle. */
		public TileArgs(String spriteKey, boolean blocked) {
			this.spriteKey = spriteKey;
			this.blocked = blocked;
		}
	}
	
	/** Contains {@link TileArgs} for an entire row of tiles of size {@link #ROW_SIZE}. */
	private class TileRow {
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
			
			this.tiles = tiles;
		}
	}
	
	//endregion

	//region Fields
	
	private int y = StealthWorld.TILE_SPAWN_Y;
	private LinkedList<String> rowsToSpawn = new LinkedList<String>();
	private HashMap<String, TileRow> rowTemplates = new HashMap<String, TileRow>();
	
	//endregion
	
	//region Initialization
	
	/**
	 * Constructs and initializes. a TileSpawnSystem.
	 */
	public TileSpawnSystem() {
		buildRowTemplates();
	}
	
	private void buildRowTemplates() {
		TileArgs floor = new TileArgs("Floor", false);
		TileArgs whiteWallVertical = new TileArgs("WhiteWallVertical", true);
		
		TileArgs[] args = new TileArgs[TileRow.ROW_SIZE];
		TileRow row = null;
		
		args[0] = whiteWallVertical;
		args[1] = floor;
		args[2] = floor;
		args[3] = floor;
		args[4] = floor;
		args[5] = floor;
		args[6] = whiteWallVertical;
		row = new TileRow(args);
		
		rowTemplates.put("HallSegment", row);
	}
	
	//endregion
	
	@Override
	public void dispose() { }

	//region Processing
	
	@Override
	public void processEntities() {
		super.processEntities();
		
		spawnRow("HallSegment");
	}
	
	//endregion
	
	//region Entity Processing
	
	@Override
	protected void process(Entity e) { }

	@Override
	public boolean canProcess(Entity e) { return false; }
	
	//endregion

	//region Helpers
	
	private void spawnRow(String rowKey) {
		TileRow row = rowTemplates.get(rowKey);
		
		int i = 0;
		for (int x = -TileRow.ROW_SIZE / 2; x <= TileRow.ROW_SIZE / 2; x++, i++) {
			TileArgs args = row.tiles[i];
			
			world.createEntity("Tile", args.spriteKey, new Vector2(x, y), args.blocked);
		}
		
		y++;
	}
	
	//endregion
	
}
