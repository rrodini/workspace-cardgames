package com.cardtech.game.poker.wildcard;

import java.util.List;

import com.cardtech.core.Card;
import com.cardtech.core.Suit;
import com.cardtech.game.poker.PokerHand;

public class OnePairPlayer extends WildcardPlayer {
	/**
	 * OnePairPlayer attempts to play the wildcards into a pair.
	 * 
	 * The algorithm is to take the "nothing" hand and play the joker
	 * as the highest value card.
	 * 
	 * See TestOnePairPlayer for examples.
	 * 
	 */
	public OnePairPlayer(PokerHand hand) {
		super(hand);
	}

	@Override
	public boolean play() {
		int distinctValueCount = distinctValueCount(naturals);
		if (distinctValueCount != 4) {
			throw new IllegalStateException("Should have 4 distinct naturals: " + naturals);
		}
		int highCardValue = naturals.get(3).getValue();
		List<Suit> missing = findMissingSuits(naturals.subList(3,4));
		playWildcard(naturals, new Card(missing.get(0), highCardValue));
		return true;
	}

}
