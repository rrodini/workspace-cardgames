package com.cardtech.core;

import java.util.Comparator;

import com.cardtech.core.Card;
/**
 * CardByRankComparator is a comparator that gives a total
 * ordering of all 52 cards by using the suit ordinal value.
 *
 */
public class CardByRank implements Comparator<Card> {

	@Override
	public int compare(Card c1, Card c2) {
		int val1 = c1.getValue();
		int val2 = c2.getValue();
		int suit1 = c1.getSuit().ordinal();
		int suit2 = c2.getSuit().ordinal();
		// hate coding 13 here.  There are 13 cards per suit.
		int total1 = suit1 * 13 + val1;
		int total2 = suit2 * 13 + val2;
		// no danger of overflow here since the numbers are small.
		return total1 - total2;
	}

}
