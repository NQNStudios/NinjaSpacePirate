package com.natman.NinjaSpacePirate.gameplay.entities.systems;

import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.processes.ProcessState;
import com.lostcode.javalib.entities.systems.EntitySystem;
import com.natman.NinjaSpacePirate.gameplay.entities.processes.ChasePlayerProcess;

public class PitBlockingSystem extends EntitySystem {

	@Override
	public void dispose() {
		
	}

	@Override
	protected void process(Entity e) {
		//enemies shouldn't pursue over pits
		world.getProcessManager().endAll(ChasePlayerProcess.class, ProcessState.FAILED);
	}

	@Override
	public boolean canProcess(Entity e) {
		return e.getType().equals("Pit");
	}

}
