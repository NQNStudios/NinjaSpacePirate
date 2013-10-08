package com.punchline.NinjaSpacePirate.gameplay.entities.systems.spawn;

import java.util.HashMap;
import java.util.LinkedList;

import com.badlogic.gdx.math.Vector2;
import com.punchline.NinjaSpacePirate.gameplay.StealthWorld;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.components.physical.Transform;
import com.punchline.javalib.entities.systems.EntitySystem;
import com.punchline.javalib.utils.Random;

/**
 * System that spawns the tiles making up the game world.
 * @author Natman64
 *
 */
public class TileSpawnSystem extends EntitySystem {

	//region Constants
	
	private static final float ROW_SPAWN_DISTANCE = 12f;
	private static final float LOCATION_SPAWN_DISTANCE = 24f;
	
	//endregion
	
	//region Fields
	
	private static Random r = new Random();
	
	private int y = StealthWorld.TILE_SPAWN_Y;
	private LinkedList<String> rowsToSpawn = new LinkedList<String>();
	private HashMap<String, TileRow> rowTemplates = new HashMap<String, TileRow>();
	private HashMap<String, LocationTemplate> locationTemplates = new HashMap<String, LocationTemplate>();
	
	private Entity player;
	
	//endregion
	
	//region Initialization
	
	/**
	 * Constructs and initializes. a TileSpawnSystem.
	 */
	public TileSpawnSystem() {
		buildRowTemplates();
		buildLocationTemplates();
		
		queueSpawnLocation("HallSegment");
	}
	
	private void buildRowTemplates() {
		TileArgs floor = new TileArgs("Floor", false);
		TileArgs floorVent = new TileArgs("FloorVent", false);
		TileArgs floorGrate = new TileArgs("FloorGrate", false);
		TileArgs floorLight = new TileArgs("FloorLight", false);
		TileArgs floorHole = new TileArgs("FloorHole", false);
		
		TileArgs whiteWallVertical = new TileArgs("WhiteWallVertical", true);
		TileArgs whiteWallVentEast = new TileArgs("WhiteWallVentEast", true);
		TileArgs whiteWallVentWest = new TileArgs("WhiteWallVentWest", true);
		TileArgs whiteWallRedLightEast = new TileArgs("WhiteWallRedLightEast", true);
		TileArgs whiteWallRedLightWest = new TileArgs("WhiteWallRedLightWest", true);
		
		TileArgs[] args = new TileArgs[TileRow.ROW_SIZE];
		
		args[0] = whiteWallVertical;
		args[1] = floorLight;
		args[2] = floor;
		args[3] = floor;
		args[4] = floor;
		args[5] = floorLight;
		args[6] = whiteWallVertical;
		
		rowTemplates.put("HallSegment", new TileRow(args));
		
		args[0] = whiteWallVentEast;
		args[6] = whiteWallVentWest;
		
		rowTemplates.put("HallSegmentWallVents", new TileRow(args));
		
		args[0] = whiteWallVertical;
		args[3] = floorVent;
		args[6] = whiteWallVertical;
		
		rowTemplates.put("HallSegmentFloorVent", new TileRow(args));
		
		args[0] = whiteWallRedLightEast;
		args[3] = floor;
		args[6] = whiteWallRedLightWest;
		
		rowTemplates.put("HallSegmentWallRedLights", new TileRow(args));
		
		args[0] = floor;
		args[6] = floor;
		
		rowTemplates.put("HallSegmentDoors", new TileRow(args));
		
		args[0] = whiteWallVertical;
		args[1] = floorHole;
		args[2] = floorHole;
		args[3] = floorHole;
		args[4] = floorHole;
		args[5] = floorHole;
		args[6] = whiteWallVertical;
		
		rowTemplates.put("Pit", new TileRow(args));
		
		args[1] = floorGrate;
		
		rowTemplates.put("PitGrate0", new TileRow(args));
		
		args[1] = floorHole;
		args[2] = floorGrate;
		
		rowTemplates.put("PitGrate1", new TileRow(args));
		
		args[2] = floorHole;
		args[3] = floorGrate;
		
		rowTemplates.put("PitGrate2", new TileRow(args));
		
		args[3] = floorHole;
		args[4] = floorGrate;
		
		rowTemplates.put("PitGrate3", new TileRow(args));
		
		args[4] = floorHole;
		args[5] = floorGrate;
		
		rowTemplates.put("PitGrate4", new TileRow(args));
	}
	
	private void buildLocationTemplates() {
		String[] loc = new String[15];
		loc[0] = "HallSegment";
		loc[1] = "HallSegment";
		loc[2] = "HallSegment";
		loc[3] = "HallSegmentWallVents";
		loc[4] = "HallSegment";
		loc[5] = "HallSegment";
		loc[6] = "HallSegment";
		loc[7] = "HallSegmentFloorVent";
		loc[8] = "HallSegment";
		loc[9] = "HallSegment";
		loc[10] = "HallSegment";
		loc[11] = "HallSegmentWallVents";
		loc[12] = "HallSegment";
		loc[13] = "HallSegment";
		loc[14] = "HallSegment";
		
		locationTemplates.put("HallSegment", new LocationTemplate(loc));
		
		loc = null;
		loc = new String[15];
		loc[0] = "HallSegment";
		loc[1] = "HallSegment";
		loc[2] = "HallSegment";
		loc[3] = "HallSegmentWallVents";
		loc[4] = "HallSegment";
		loc[5] = "HallSegment";
		loc[6] = "HallSegmentWallRedLights";
		loc[7] = "HallSegmentDoors";
		loc[8] = "HallSegmentWallRedLights";
		loc[9] = "HallSegment";
		loc[10] = "HallSegment";
		loc[11] = "HallSegmentWallVents";
		loc[12] = "HallSegment";
		loc[13] = "HallSegment";
		loc[14] = "HallSegment";
		
		locationTemplates.put("HallSegmentDoors", new LocationTemplate(loc));
		
		LocationTemplate pitLocation = new LocationTemplate(null) {
			
			@Override
			public String[] getRows() {
				
				int grateLoc = r.nextInt(6);
				String rowKey = "PitGrate" + grateLoc;
				
				String[] rows = new String[16];
				rows[0] = rowKey;
				rows[1] = rowKey;
				rows[2] = rowKey;
				rows[3] = rowKey;
				rows[4] = rowKey;
				rows[5] = rowKey;
				rows[6] = "HallSegment";
				rows[7] = "HallSegment";
				rows[8] = "HallSegment";
				rows[9] = "HallSegment";
				rows[10] = "HallSegment";
				rows[11] = "HallSegment";
				rows[12] = "HallSegment";
				rows[13] = "HallSegment";
				rows[14] = "HallSegment";
				rows[15] = "HallSegment";
				
				return rows;
				
			}
			
		};
		
		locationTemplates.put("PitGrate", pitLocation);
	}
	
	//endregion
	
	//region Disposal
	
	@Override
	public void dispose() { }

	//endregion
	
	//region Processing
	
	@Override
	public void processEntities() {
		super.processEntities();
		
		Transform t = player.getComponent(Transform.class);
		
		if (t.getPosition().y + LOCATION_SPAWN_DISTANCE >= y) {
			queueNextLocation();
		}
		
		while (t.getPosition().y + ROW_SPAWN_DISTANCE >= y) {
			spawnRow(rowsToSpawn.removeFirst());
		}
	}
	
	//endregion
	
	//region Entity Processing
	
	@Override
	protected void process(Entity e) { 
		this.player = e;
	}

	@Override
	public boolean canProcess(Entity e) { 
		return e.getTag().equals("Player");
	}
	
	//endregion

	//region Helpers
	
	private void spawnRow(String rowKey) {
		TileRow row = rowTemplates.get(rowKey);
		
		int i = 0;
		for (int x = -TileRow.ROW_SIZE / 2; x <= TileRow.ROW_SIZE / 2; x++, i++) {
			TileArgs args = row.tiles[i];
			
			world.createEntity("Tile", args.spriteKey, new Vector2(x, y), args.blocked);
			args.onCreated(world, x, y);
		}
		
		row.onCreated(world, y);
		
		y++;
	}
	
	private void queueSpawnRow(String rowKey) {
		rowsToSpawn.add(rowKey);
	}
	
	private void queueSpawnLocation(String locKey) {
		String[] loc = locationTemplates.get(locKey).getRows();
		
		for (String row : loc) {
			queueSpawnRow(row);
		}
	}
	
	private void queueNextLocation() {
		//This is where the system decides what obstacle to throw at the player next.
		
		int loc = r.nextInt(3);
		
		switch (loc) {
		
		case 0:
			queueSpawnLocation("HallSegment");
			break;
			
		case 1:
			queueSpawnLocation("HallSegmentDoors");
			break;
			
		case 2:
			queueSpawnLocation("PitGrate");
			break;
		
		}
	}
	
	//endregion
	
}
