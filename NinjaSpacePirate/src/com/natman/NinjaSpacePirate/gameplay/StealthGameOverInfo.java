package com.natman.NinjaSpacePirate.gameplay;

import com.lostcode.javalib.entities.GameOverInfo;

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
