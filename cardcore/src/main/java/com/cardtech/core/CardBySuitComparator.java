package com.cardtech.core;

import java.util.Comparator;

import com.cardtech.core.Card;
/**
 * CardBySuitComparator is a comparator that gives a total
 * ordering of all 52 cards by using the suit ordinal value.
 *
 * This comparator is needed so that a TreeMap<Card,Integer>
 * can be used in the WarGame implementation.
 */
public class CardBySuitComparator implements Comparator<Card> {

	@Override
	public int compare(Card c1, Card c2) {
		int val1 = c1.getValue();
		int val2 = c2.getValue();
		int suit1 = c1.getSuit().ordinal();
		int suit2 = c2.getSuit().ordinal();
		// size should be 4 since there are 4 suits.
		int size = Suit.values().length;
		int total1 = val1 * size + suit1;
		int total2 = val2 * size + suit2;
		// no danger of overflow here since the numbers are small.
		return total1 - total2;
	}

}
