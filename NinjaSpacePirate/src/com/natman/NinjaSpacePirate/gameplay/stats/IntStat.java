package com.natman.NinjaSpacePirate.gameplay.stats;

import com.natman.NinjaSpacePirate.gameplay.Stat;

/**
 * An integer stat.
 * @author Natman64
 * @created Oct 17, 2013
 */
public class IntStat extends Stat<Integer> {

	/**
	 * Constructs an IntStat.
	 * @param defaultValue
	 */
	public IntStat(Integer defaultValue) {
		super(defaultValue);
	}

	@Override
	public void parseValue(String value) {
		setValue(Integer.parseInt(value));
	}
	
	@Override
	public String formatValue() {
		return "" + value;
	}
	
	/**
	 * Increments the value.
	 */
	public void increment() {
		value++;
	}

}
