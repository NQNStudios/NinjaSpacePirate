package com.natman.NinjaSpacePirate.gameplay.stats;

import com.natman.NinjaSpacePirate.gameplay.Stat;

/**
 * A stat representing an amount of time.
 * @author Natman64
 * @created Oct 17, 2013
 */
public class TimeStat extends Stat<Float> {
	
	/**
	 * Constructs a TimeStat.
	 * @param defaultValue
	 */
	public TimeStat(Float defaultValue) {
		super(defaultValue);
	}

	@Override
	public void parseValue(String value) {
		setValue(Float.parseFloat(value));
	}
	
	@Override
	public String formatValue() {
		return String.format("%d:%02d:%02d", 
				value.intValue()/3600, 
				(value.intValue()%3600)/60, 
				(value.intValue()%60));
	}
	
	/**
	 * Adds an amount to the value.
	 * @param amount
	 */
	public void add(float amount) {
		value += amount;
	}

}
