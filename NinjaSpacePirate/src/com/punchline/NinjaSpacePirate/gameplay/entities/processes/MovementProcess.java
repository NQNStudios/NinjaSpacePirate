package com.punchline.NinjaSpacePirate.gameplay.entities.processes;

import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.EntityWorld;
import com.punchline.javalib.entities.components.Component;
import com.punchline.javalib.entities.components.ComponentManager;
import com.punchline.javalib.entities.processes.Process;
import com.punchline.javalib.entities.processes.ProcessState;

public abstract class MovementProcess extends Process implements Component {

	protected EntityWorld world;
	protected Entity e;
	
	public MovementProcess(EntityWorld world, Entity e) {
		this.world = world;
		this.e = e;
	}

	@Override
	public void onEnd(EntityWorld world, ProcessState endState) {
		
	}

	@Override
	public void onAdd(ComponentManager container) {
		world.getProcessManager().attach(this);
	}

	@Override
	public void onRemove(ComponentManager container) {
		end(ProcessState.ABORTED);
	}

}
