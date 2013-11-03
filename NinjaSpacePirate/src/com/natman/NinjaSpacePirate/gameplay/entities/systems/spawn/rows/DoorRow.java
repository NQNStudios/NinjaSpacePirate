package com.natman.NinjaSpacePirate.gameplay.entities.systems.spawn.rows;

import com.badlogic.gdx.math.Vector2;
import com.lostcode.javalib.entities.EntityWorld;
import com.lostcode.javalib.utils.LogManager;
import com.lostcode.javalib.utils.Random;
import com.natman.NinjaSpacePirate.gameplay.StealthWorld;
import com.natman.NinjaSpacePirate.gameplay.entities.systems.spawn.TileArgs;
import com.natman.NinjaSpacePirate.gameplay.entities.systems.spawn.TileRow;

/**
 * Row with enemy-spawning doors.
 * @author Natman64
 *
 */
public class DoorRow extends TileRow {

	private Random r = new Random();
	
	/**
	 * Makes a DoorRow.
	 * @param args
	 */
	public DoorRow(TileArgs[] args) {
		super(args);
	}
	
	@Override
	public void onCreated(EntityWorld world, float y) {
		super.onCreated(world, y);
		
		Vector2 position = new Vector2(0, y);
		Vector2 velocity = new Vector2(0, 0);
		
		//create spawner on either side
		if (r.nextBoolean()) { //left side
			position.x = -6;
			velocity.x = StealthWorld.ENEMY_SPEED;
		} else { //right side
			position.x = 6;
			velocity.x = -StealthWorld.ENEMY_SPEED;
		}
		
		LogManager.debug("World", "Starting to spawn red man");
		world.createEntity("EnemySpawner", "redSuitMan", position, "", "Enemies", "Patroller", velocity);
		LogManager.debug("World", "Finished spawning red man");
	}
	
}
