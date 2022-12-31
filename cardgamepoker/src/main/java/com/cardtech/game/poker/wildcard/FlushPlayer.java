package com.cardtech.game.poker.wildcard;

import java.util.List;

import com.cardtech.core.Card;
import com.cardtech.core.Suit;
import com.cardtech.game.poker.PokerHand;
/**
 * FlushPlayer attempts to play the wildcards into a flush.
 * 
 * The algorithm is similar to StraightFlush (the flush part).
 * 
 * See TestFlushPlayer for examples.
 * 
 */

public class FlushPlayer extends WildcardPlayer {

	public FlushPlayer(PokerHand hand) {
		super(hand);
	}
	/**
	 * Attempt to play a flush.
	 * See TestFlushPlayer for examples.
	 */
	@Override
	public boolean play() {
		int distinctSuitCount = distinctSuitCount(naturals);
		if (distinctSuitCount == 1) {
			// All naturals are the same suit.
			Suit suitToMatch = naturals.get(0).getSuit();
			addWildcardHigh(naturals, suitToMatch);
			if (wildCount == 1) {
				addWildcardHigh(naturals, suitToMatch);
			}
			return true;
		}
		return false;
	}
	/**
	 * addWildcardsHigh() plays one wildcard to the high end of the list.
	 * Note:
	 * - logic differs from StraightFlushPlayer.
	 * - side-effect of altering the list.
	 * @param list of Card objects.
	 * @param s suit of the card to add.
	 */
	protected void addWildcardHigh(final List<Card> list, final Suit s) {
		int highCardIndex = list.size()-1;
		for (int newCardValue = ACE_VALUE; newCardValue > TWO_VALUE; newCardValue--) {
			if (newCardValue != list.get(highCardIndex).getValue()) {
				playWildcard(list, new Card(s, newCardValue));
				break;
			}
			highCardIndex--;
		}
		list.sort(null);
	}
}
