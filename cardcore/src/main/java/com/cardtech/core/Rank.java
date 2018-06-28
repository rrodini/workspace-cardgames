package com.cardtech.core;
/**
 * This enum provides the standard rank order of cards in a deck. 
 * Caller should use CARD.getValue() and not CARD.ordinal().
 */
public enum Rank {
	LOW_CARD(2),	// alias for TWO
	HIGH_CARD(14),	// alias for ACE
	TWO(2),
	THREE(3),
	FOUR(4),
	FIVE(5),
	SIX(6),
	SEVEN(7),
	EIGHT(8),
	NINE(9),
	TEN(10),
	JACK(11),
	QUEEN(12),
	KING(13),
	ACE(14);
	
	private final int value;
	
	private Rank(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public static int getSuiteSize() {
		return (HIGH_CARD.value - LOW_CARD.value +1);
	}
}
