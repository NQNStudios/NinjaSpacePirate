package com.punchline.NinjaSpacePirate.gameplay.entities.systems.spawn.rows;

import com.badlogic.gdx.math.Vector2;
import com.punchline.NinjaSpacePirate.gameplay.entities.systems.spawn.TileArgs;
import com.punchline.NinjaSpacePirate.gameplay.entities.systems.spawn.TileRow;
import com.punchline.javalib.entities.EntityWorld;
import com.punchline.javalib.utils.Random;

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
