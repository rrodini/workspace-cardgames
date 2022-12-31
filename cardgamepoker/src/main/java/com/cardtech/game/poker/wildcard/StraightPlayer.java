package com.cardtech.game.poker.wildcard;

import java.util.List;

import com.cardtech.core.Card;
import com.cardtech.core.Suit;
import com.cardtech.game.poker.PokerHand;

/**
 * StraightPlayer attempts to play the wildcards into a straight.
 * 
 * The algorithm is to find the gaps between the naturals and
 * see if those gaps can be filled by one or both wildcards.
 * There are many edge cases.
 * 
 * See TestStraightPlayer for examples.
 * 
 */
public class StraightPlayer extends WildcardPlayer {
	
	public StraightPlayer(PokerHand hand) {
		 super(hand);
	}
	/**
	 * Attempt to play into a straight.
	 * 
	 * See TestStraightPlayer for examples.
	 */
	@Override
	public boolean play() {
		boolean isStraight = false;
		int maxGap = maxGap(naturals);
		Suit suitToMatch = naturals.get(0).getSuit();
		if (maxGap < 3) {
			if (maxGap == 0) {
				// add wildcards at high end (if possible).
				addWildcardsHigh(naturals, suitToMatch);
				isStraight = true;
			} else if (maxGap == 1) {
				// use one wildcard to fill gap.
				addWildcardsGap(naturals, suitToMatch, maxGap);
				isStraight = true;
			} else if (maxGap == 2 && wildCount == 2) {
				// use both wildcards to fill gap.
				addWildcardsGap(naturals, suitToMatch, maxGap);
				isStraight = true;
			} else {
				// gap can't be filled by wildcards.
			}
		}
		return isStraight;
	}

	/**
	 * findGapCardValue() finds the value of the "gap" card.
	 * 
	 * @param list of Card objects.
	 * @return value of "gap" card (3 .. 13)
	 */
	public int findGapCardValue(List<Card> list) {
		for (int i = 0; i < list.size() - 1; i++) {
			if (list.get(i + 1).getValue() - list.get(i).getValue() > 1) {
				return list.get(i).getValue() + 1;
			}
		}
		throw new IllegalStateException("Could not find gap in list: " + list);
	}

	/**
	 * addWildcardsHigh() adds the wildcards to the high end of the list. Note: -
	 * side-effect of altering the list.
	 * 
	 * @param list of Card objects.
	 * @param s    suit of the card to add.
	 */
	protected void addWildcardsHigh(final List<Card> list, final Suit s) {
		int highCardValue = list.get(list.size() - 1).getValue();
		int newCardValue = 0; // Illegal
		if (highCardValue == ACE_VALUE) {
			newCardValue = ACE_VALUE - natCount;
			// play wildcard as 10
			playWildcard(list, new Card(s, newCardValue));
			if (wildCount == 1) {
				// second wildcard
				playWildcard(list, new Card(s, newCardValue - 1));
			}
		} else if (highCardValue == KING_VALUE) {
			newCardValue = KING_VALUE - natCount;
			// play wildcard as as ace (14).
			playWildcard(list, new Card(s, ACE_VALUE));
			if (wildCount == 1) {
				// second wildcard
				playWildcard(list, new Card(s, newCardValue));
			}
		} else {
			newCardValue = highCardValue + 1;
			// play wildcard above current run.
			playWildcard(list, new Card(s, newCardValue));
			if (wildCount == 1) {
				// second wildcard
				playWildcard(list, new Card(s, newCardValue + 1));
			}
		}
		list.sort(null);
	}

// use wildcards to fill a gap and make a straight.
// Notes: 
// 1) method has side-effect of altering list
// 2) list is resorted.
	/**
	 * addWildcardsGap() plays the wildcards to fill a gap in the straight. Notes: -
	 * side-effect of altering the list.
	 * 
	 * @param list     of Card objects.
	 * @param s        suit of new card.
	 * @param gapCount "gap" between card values.
	 */
	protected void addWildcardsGap(final List<Card> list, final Suit s, final int gapCount) {
		int gapCardValue = findGapCardValue(list);
		// play first wildcard
		playWildcard(list, new Card(s, gapCardValue));
		list.sort(null);
		if (gapCount == 2 && wildCount == 1) {
			gapCardValue = findGapCardValue(list);
			playWildcard(list, new Card(s, gapCardValue));
		} else if (wildCount == 1) {
			addWildcardsHigh(list, s);
		}
		list.sort(null);
	}

}
