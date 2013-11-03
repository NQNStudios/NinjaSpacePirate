package com.natman.NinjaSpacePirate.gameplay.entities.systems.spawn.locations;

import com.lostcode.javalib.entities.EntityWorld;
import com.lostcode.javalib.utils.Random;
import com.natman.NinjaSpacePirate.gameplay.entities.components.render.hud.HudWarning.WarningType;
import com.natman.NinjaSpacePirate.gameplay.entities.systems.spawn.LocationTemplate;

/**
 * Creates a pit obstacle.
 * @author Natman64
 *
 */
public class PitLocation extends LocationTemplate {

	private Random r = new Random();
	
	private int grateLoc;
	
	/**
	 * Creates a pit location template.
	 */
	public PitLocation(int difficulty, int weight) {
		super(null, difficulty, weight);
	}

	@Override
	public String[] getRows() {
		
		grateLoc = r.nextInt(5);
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
		rows[10] = "HallSegmentWallVents";
		rows[11] = "HallSegment";
		rows[12] = "HallSegment";
		rows[13] = "HallSegment";
		rows[14] = "HallSegment";
		rows[15] = "HallSegment";
		
		return rows;
		
	}

	@Override
	public void onQueue(EntityWorld world) {
		world.createEntity("HudWarning", WarningType.GreenArrow, grateLoc + 1);
	}
	
}
