package com.punchline.NinjaSpacePirate.gameplay.entities.systems.spawn.rows;

import com.badlogic.gdx.math.Vector2;
import com.punchline.NinjaSpacePirate.gameplay.StealthWorld;
import com.punchline.NinjaSpacePirate.gameplay.entities.systems.spawn.TileArgs;
import com.punchline.NinjaSpacePirate.gameplay.entities.systems.spawn.TileRow;
import com.punchline.javalib.entities.EntityWorld;
import com.punchline.javalib.utils.Random;

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
		
		world.createEntity("EnemySpawner", "redSuitMan", position, "", "Enemies", "Patroller", velocity);
	}
	
}
