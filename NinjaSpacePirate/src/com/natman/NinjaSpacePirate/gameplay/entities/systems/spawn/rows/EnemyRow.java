package com.natman.NinjaSpacePirate.gameplay.entities.systems.spawn.rows;

import com.badlogic.gdx.math.Vector2;
import com.lostcode.javalib.entities.EntityWorld;
import com.lostcode.javalib.utils.Random;
import com.natman.NinjaSpacePirate.gameplay.entities.systems.spawn.TileArgs;
import com.natman.NinjaSpacePirate.gameplay.entities.systems.spawn.TileRow;

public class EnemyRow extends TileRow {

	private Random r = new Random();
	
	private static final float SPEED = 3f;
	private static final float OFFSET = 3;
	
	public EnemyRow(TileArgs... tiles) {
		super(tiles);
	}

	@Override
	public void onCreated(EntityWorld world, float y) {
		int x = r.nextInt(-2, 2);
		
		world.createEntity("NPC", "greenSuitMan", new Vector2(x, y + OFFSET), "", "Enemies", "Walker",
				new Vector2(0, -SPEED));
	}
	
}
