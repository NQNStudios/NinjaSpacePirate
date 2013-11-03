package com.natman.NinjaSpacePirate.gameplay.stats;

import java.text.NumberFormat;

import com.natman.NinjaSpacePirate.gameplay.Stat;

public class DistanceStat extends Stat<Float> {

	private NumberFormat format;
	
	public DistanceStat(Float defaultValue) {
		super(defaultValue);
		
		format = NumberFormat.getNumberInstance();
		format.setMinimumFractionDigits(0);
		format.setMaximumFractionDigits(0);
	}

	@Override
	public void parseValue(String value) {
		this.value = Float.parseFloat(value);
	}

	@Override
	public String formatValue() {
		return format.format(value) + "m";
	}

}
