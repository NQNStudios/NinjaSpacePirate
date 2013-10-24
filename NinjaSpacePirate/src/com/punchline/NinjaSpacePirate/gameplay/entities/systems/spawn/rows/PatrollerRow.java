package com.punchline.NinjaSpacePirate.gameplay.entities.systems.spawn.rows;

import com.badlogic.gdx.math.Vector2;
import com.punchline.NinjaSpacePirate.gameplay.entities.systems.spawn.TileArgs;
import com.punchline.NinjaSpacePirate.gameplay.entities.systems.spawn.TileRow;
import com.punchline.javalib.entities.EntityWorld;

public class PatrollerRow extends TileRow {

	public PatrollerRow(TileArgs... tiles) {
		super(tiles);
	}

	@Override
	public void onCreated(EntityWorld world, float y) {
		Vector2 position = new Vector2(3, y);
		
		Vector2[] patrolPoints = new Vector2[2];
		patrolPoints[0] = new Vector2(-2, y);
		patrolPoints[1] = new Vector2(2, y);
		
		world.createEntity("Patroller", position, patrolPoints);
	}
	
}
