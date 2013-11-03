package com.natman.NinjaSpacePirate.gameplay.entities.processes;

import com.lostcode.javalib.entities.EntityWorld;
import com.lostcode.javalib.entities.GameOverInfo;
import com.lostcode.javalib.entities.processes.Process;
import com.lostcode.javalib.entities.processes.ProcessState;

public class GameOverProcess extends Process {

	private GameOverInfo info;
	
	public GameOverProcess(GameOverInfo info) {
		this.info = info;
	}

	@Override
	public void update(EntityWorld world, float deltaTime) {
		world.setGameOverInfo(info);
		end(ProcessState.SUCCEEDED);
	}

	@Override
	public void onEnd(EntityWorld world, ProcessState endState) {
		
	}

}
