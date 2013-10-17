package com.punchline.NinjaSpacePirate.gameplay;

/**
 * A stat that the player has set, such as high score or number of deaths.
 * @author Natman64
 * @created Oct 17, 2013
 */
public abstract class Stat<N extends Number> {
	
	/** This stat's value. */
	protected N value;
	
	/**
	 * Constructs a stat.
	 * @param defaultValue
	 */
	public Stat(N defaultValue) {
		this.value = defaultValue;
	}
	
	/**
	 * @return This stat's value.
	 */
	public N getValue() {
		return value;
	}
	
	/**
	 * Sets this stat's value.
	 * @param value
	 */
	public void setValue(N value) {
		this.value = value;
	}
	
	/**
	 * Sets this stat's value to the number parsed from the given String.
	 * @param value
	 */
	public abstract void parseValue(String value);
	
	/**
	 * @return This stat's value, formatted for being displayed to the player.
	 */
	public abstract String formatValue();
	
}
