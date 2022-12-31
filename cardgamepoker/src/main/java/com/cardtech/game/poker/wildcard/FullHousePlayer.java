package com.cardtech.game.poker.wildcard;

import java.util.List;

import com.cardtech.core.Card;
import com.cardtech.core.Suit;
import com.cardtech.game.poker.PokerHand;

/**
 * FullHousePlayer attempts to play the wildcards into
 * a full house.
 * 
 * The algorithm is similar to FourOfAKindPlayer.
 * 
 * See TestFullHousePlayer for examples.
 * 
 */
public class FullHousePlayer extends WildcardPlayer {
	public FullHousePlayer(PokerHand hand) {
		super(hand);
	}
	/**
	 * Attempt to play a full house.
	 * See TestFullHousePlayer for examples.
	 */
	@Override
	public boolean play() {
		int distinctValueCount = distinctValueCount(naturals);
		if (distinctValueCount == 2) {
			if (natCount == 4) {
				playWildcardsFullHouse(naturals);
				return true;
			}
		}
		return false;
	}

	private void playWildcardsFullHouse(List<Card> list) {
		// "high" list always returned
		List<Card> sameValueCards = findCardValueSame(list);
		// high card value should be exactly at index 1
		int newCardValue = sameValueCards.get(1).getValue();
		List<Suit> missingSuits = findMissingSuits(sameValueCards);
		playWildcard(list, new Card(missingSuits.remove(0), newCardValue));
	}
}
