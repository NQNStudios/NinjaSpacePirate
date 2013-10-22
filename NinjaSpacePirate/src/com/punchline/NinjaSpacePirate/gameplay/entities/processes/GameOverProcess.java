package com.punchline.NinjaSpacePirate.gameplay.entities.processes;

import com.punchline.javalib.entities.EntityWorld;
import com.punchline.javalib.entities.GameOverInfo;
import com.punchline.javalib.entities.processes.Process;
import com.punchline.javalib.entities.processes.ProcessState;

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
