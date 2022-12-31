package com.cardtech.game.poker;

/**
 * This enum list the official poker hand rankings from lowest to highest.  See Enum Constant Details.  <br/>
 * Enum Constant Summary just lists the values in alphabetic order.  Don't let it fool you.
 */
public enum PokerRank {
	HIGH_CARD,
	ONE_PAIR,
	TWO_PAIR,
	THREE_OF_A_KIND,
	STRAIGHT,
	FLUSH,
	FULL_HOUSE,
	FOUR_OF_A_KIND,
	FIVE_OF_A_KIND, // ONLY FOR WILDCARDS
	STRAIGHT_FLUSH,
	ROYAL_FLUSH,  // STRAIGHT is implied
}
