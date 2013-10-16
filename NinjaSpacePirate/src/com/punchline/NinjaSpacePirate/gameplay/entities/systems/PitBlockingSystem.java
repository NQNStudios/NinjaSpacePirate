package com.punchline.NinjaSpacePirate.gameplay.entities.systems;

import com.punchline.NinjaSpacePirate.gameplay.entities.processes.ChasePlayerProcess;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.processes.ProcessState;
import com.punchline.javalib.entities.systems.EntitySystem;

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
