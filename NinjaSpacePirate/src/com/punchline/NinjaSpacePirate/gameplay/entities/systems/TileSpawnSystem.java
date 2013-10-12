package com.punchline.NinjaSpacePirate.gameplay.entities.systems;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.punchline.NinjaSpacePirate.gameplay.StealthWorld;
import com.punchline.NinjaSpacePirate.gameplay.entities.systems.spawn.LocationTemplate;
import com.punchline.NinjaSpacePirate.gameplay.entities.systems.spawn.TileArgs;
import com.punchline.NinjaSpacePirate.gameplay.entities.systems.spawn.TileRow;
import com.punchline.NinjaSpacePirate.gameplay.entities.systems.spawn.locations.HullBreachLocation;
import com.punchline.NinjaSpacePirate.gameplay.entities.systems.spawn.locations.PitLocation;
import com.punchline.NinjaSpacePirate.gameplay.entities.systems.spawn.rows.DoorRow;
import com.punchline.NinjaSpacePirate.gameplay.entities.systems.spawn.rows.MidBreachRow;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.components.physical.Transform;
import com.punchline.javalib.entities.systems.EntitySystem;
import com.punchline.javalib.utils.LogManager;
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
	
	private static final float FILLER_SECTION_CHANCE = 0.15f;
	
	//endregion
	
	//region Fields
	
	private static Random r = new Random();
	
	private int y = StealthWorld.TILE_SPAWN_Y;
	private LinkedList<String> rowsToSpawn = new LinkedList<String>();
	private HashMap<String, TileRow> rowTemplates = new HashMap<String, TileRow>();
	private HashMap<String, LocationTemplate> locationTemplates = new HashMap<String, LocationTemplate>();
	
	private float totalElapsedTime = 0f;
	private float difficulty = 0;
	
	private Array<String> availableLocations = new Array<String>();
	
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
	
	//endregion
	
	//region Rows
	
	private void buildRowTemplates() {
		//region TileArgs
		
		TileArgs floor = new TileArgs("Floor", false);
		TileArgs floorVent = new TileArgs("FloorVent", false);
		TileArgs floorGrate = new TileArgs("FloorGrate", false);
		TileArgs floorLight = new TileArgs("FloorLight", false);
		TileArgs floorHole = new TileArgs("FloorHole", false);
		TileArgs floorGreen = new TileArgs("FloorGreen", false);
		TileArgs floorDamaged0 = new TileArgs("FloorDamaged0", false);
		TileArgs floorDamaged1 = new TileArgs("FloorDamaged1", false);
		TileArgs floorDamaged2 = new TileArgs("FloorDamaged2", false);
		TileArgs floorDamaged3 = new TileArgs("FloorDamaged3", false);
		
		TileArgs whiteWallVertical = new TileArgs("WhiteWallVertical", true);
		TileArgs whiteWallVentEast = new TileArgs("WhiteWallVentEast", true);
		TileArgs whiteWallVentWest = new TileArgs("WhiteWallVentWest", true);
		TileArgs whiteWallRedLightEast = new TileArgs("WhiteWallRedLightEast", true);
		TileArgs whiteWallRedLightWest = new TileArgs("WhiteWallRedLightWest", true);
		TileArgs whiteWallVerticalDamaged = new TileArgs("WhiteWallVerticalDamaged", true);
		
		//endregion
		
		//region Hall
		
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
		
		args[0] = floorGreen;
		args[1] = floor;
		args[5] = floor;
		args[6] = floorGreen;
		
		rowTemplates.put("HallSegmentDoors", new DoorRow(args));
		
		//endregion
		
		//region Pits
		
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
		
		//endregion
		
		//region Hull Breach
		
		//Left side
		args[0] = whiteWallVerticalDamaged;
		args[1] = floorDamaged0;
		args[2] = floor;
		args[3] = floor;
		args[4] = floor;
		args[5] = floorLight;
		args[6] = whiteWallVertical;
		
		rowTemplates.put("LeftBreach0", new TileRow(args));
		
		args[0] = floorHole;
		args[1] = floorDamaged3;
		args[2] = floorDamaged1;
		
		rowTemplates.put("LeftBreach1", new TileRow(args));
		
		args[1] = floorHole;
		args[2] = floorDamaged0;
		
		rowTemplates.put("LeftBreach2", new TileRow(args));
		
		args[3] = floorDamaged2;
		
		rowTemplates.put("LeftBreach3", new MidBreachRow(true, args));
		
		args[2] = floorDamaged3;
		args[3] = floor;
		
		rowTemplates.put("LeftBreach4", new TileRow(args));
		
		args[1] = floorDamaged0;
		
		rowTemplates.put("LeftBreach5", new TileRow(args));
		
		args[0] = whiteWallVerticalDamaged;
		args[1] = floorDamaged3;
		args[2] = floor;
		
		rowTemplates.put("LeftBreach6", new TileRow(args));
		
		//Right side
		args[0] = whiteWallVertical;
		args[1] = floorLight;
		args[2] = floor;
		args[3] = floor;
		args[4] = floor;
		args[5] = floorDamaged0;
		args[6] = whiteWallVerticalDamaged;
		
		rowTemplates.put("RightBreach0", new TileRow(args));
		
		args[6] = floorHole;
		args[5] = floorDamaged3;
		args[4] = floorDamaged1;
		
		rowTemplates.put("RightBreach1", new TileRow(args));
		
		args[5] = floorHole;
		args[4] = floorDamaged0;
		
		rowTemplates.put("RightBreach2", new TileRow(args));
		
		args[3] = floorDamaged2;
		
		rowTemplates.put("RightBreach3", new MidBreachRow(false, args));
		
		args[4] = floorDamaged3;
		args[3] = floor;
		
		rowTemplates.put("RightBreach4", new TileRow(args));
		
		args[5] = floorDamaged0;
		
		rowTemplates.put("RightBreach5", new TileRow(args));
		
		args[6] = whiteWallVerticalDamaged;
		args[5] = floorDamaged3;
		args[4] = floor;
		
		rowTemplates.put("RightBreach6", new TileRow(args));
		
		//endregion
	}
	
	//endregion
	
	//region Locations
	
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
		
		locationTemplates.put("HallSegment", new LocationTemplate(loc, -1));
		
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
		
		locationTemplates.put("HallSegmentDoors", new LocationTemplate(loc, 1));
		
		LocationTemplate pitLocation = new PitLocation();
		
		locationTemplates.put("PitGrate", pitLocation);
		
		LocationTemplate breachLocation = new HullBreachLocation();
		
		locationTemplates.put("Breach", breachLocation);
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
		
		if (player == null) return;
		
		calculateDifficulty(deltaSeconds());
		
		Transform t = player.getComponent(Transform.class);
		
		if (t == null) return;
		
		if (t.getPosition().y + LOCATION_SPAWN_DISTANCE >= y) {
			queueNextLocation();
		}
		
		while (t.getPosition().y + ROW_SPAWN_DISTANCE >= y) {
			spawnRow(rowsToSpawn.removeFirst());
		}
	}
	
	private void calculateDifficulty(float deltaTime) {
		totalElapsedTime += deltaTime;
		
		int oldDifficulty = (int) difficulty;
		difficulty = (totalElapsedTime + 30f) / 30f; //add the extra count to avoid 0 casting.
		
		if ((int) difficulty - oldDifficulty >= 1) {
			Iterator<Entry<String, LocationTemplate>> it = locationTemplates.entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, LocationTemplate> entry = it.next();
				
				if (entry.getValue().getDifficulty() <= difficulty && !availableLocations.contains(entry.getKey(), false)) {
					availableLocations.add(entry.getKey());
				}
			}
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
		
		if (row == null) {
			LogManager.error("Space Pirate Log", "Invalid row key passed: " + rowKey);
		}
		
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
		
		if (r.percent(FILLER_SECTION_CHANCE)) {
			queueSpawnLocation("HallSegment");
			return;
		} else {
			int index = r.nextInt(availableLocations.size);
			
			queueSpawnLocation(availableLocations.get(index));
		}
	}
	
	//endregion
	
}