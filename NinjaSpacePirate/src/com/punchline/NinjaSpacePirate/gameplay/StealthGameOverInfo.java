package com.punchline.NinjaSpacePirate.gameplay;

import com.punchline.javalib.entities.GameOverInfo;

/**
 * GameOverInfo struct.
 * @author Natman64
 *
 */
public class StealthGameOverInfo extends GameOverInfo {
	
	public GameScore score;
	
	public StealthGameOverInfo(GameScore score) {
		this.score = score;
	}
	
}
