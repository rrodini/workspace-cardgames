package com.cardtech.game;
/**
 * This enum is not currently used.  When it is used it will determine whether the ace can be played high or low.
 */
public enum AceValue {
	/** When played as "low" the value is 1 */
	ACE_IS_LOW(1),
	/** When played as "high" the value is 14 */
	ACE_IS_HIGH(14);

	int aceValue;
	/**
	 * Constructor for enum constants.
	 * @param n numeric value of ace value.
	 */
	private AceValue(int n){
		this.aceValue = n;
	}
	/**
	 * Get the numeric value of the ace.
	 * @return 1 (low) or 14 (high)
	 */
	public int getValue() {
		return aceValue;
	}
	
}
