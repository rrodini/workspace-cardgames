package com.cardtech.game.poker.wildcard;

import com.cardtech.game.poker.PokerHand;

/**
 * TwoPairPlayer attempts to play the wildcards into two pairs.
 * 
 * Analysis shows that this can never happen. Either 3 (or 4) of
 * a kind will be played first.
 * 
 * See TestTwoPairPlayer for examples.
 * 
 */

public class TwoPairPlayer extends WildcardPlayer {

	public TwoPairPlayer(PokerHand hand) {
		super(hand);
	}
	/**
	 * Attempt to play into two pairs.
	 * See TestTwoPairPlayer for examples.
	 */
	@Override
	public boolean play() {
		return false;
	}
}
