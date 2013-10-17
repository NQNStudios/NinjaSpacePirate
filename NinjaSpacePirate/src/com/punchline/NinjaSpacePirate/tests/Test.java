package com.punchline.NinjaSpacePirate.tests;

import static org.junit.Assert.*;

import com.punchline.NinjaSpacePirate.gameplay.Stats;
import com.punchline.NinjaSpacePirate.gameplay.stats.TimeStat;

public class Test {

	@org.junit.Test
	public void test() {
		Stats.init();
		
		TimeStat stat = (TimeStat) Stats.getStat("Time Played");
		stat.setValue(5f);
		
		assertTrue(stat.formatValue().equals("0:00:05"));
		
	}

}
