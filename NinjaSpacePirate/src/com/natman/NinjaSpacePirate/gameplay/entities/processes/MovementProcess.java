package com.natman.NinjaSpacePirate.gameplay.entities.processes;

import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.EntityWorld;
import com.lostcode.javalib.entities.components.Component;
import com.lostcode.javalib.entities.components.ComponentManager;
import com.lostcode.javalib.entities.components.generic.Health;
import com.lostcode.javalib.entities.events.processes.EndProcessCallback;
import com.lostcode.javalib.entities.processes.Process;
import com.lostcode.javalib.entities.processes.ProcessState;

public abstract class MovementProcess extends Process implements Component {

	protected EntityWorld world;
	protected Entity e;
	
	public MovementProcess(EntityWorld world, Entity e) {
		this.world = world;
		this.e = e;
	}

	@Override
	public void onEnd(EntityWorld world, ProcessState endState) {
		e.onDeleted.removeCallback(this);
	}

	@Override
	public void onAdd(ComponentManager container) {
		world.getProcessManager().attach(this);
	}

	@Override
	public void onRemove(ComponentManager container) {
		end(ProcessState.ABORTED);
	}
	
	protected void endOnDeath(Entity e) {
		Health health = e.getComponent(Health.class);
		
		health.onDeath.addCallback(this, new EndProcessCallback(this, ProcessState.ABORTED));
	}

}
