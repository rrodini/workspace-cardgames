package com.cardtech.game.poker.wildcard;

import com.cardtech.game.poker.PokerHand;

import java.util.List;

import com.cardtech.core.Card;
import com.cardtech.core.Suit;

/**
 * StraightFlushPlayer attempts to play the wildcards into a 
 * straight flush (the highest rank poker hand). Playing a
 * straight is the hard part, so it delegates to StraightPlayer.
 * The flush part is easy.
 * 
 * See TestStraightFlushPlayer for examples.
 * 
 */
public class StraightFlushPlayer extends WildcardPlayer {

	private StraightPlayer sp;
	public StraightFlushPlayer(PokerHand hand) {
		//super(hand);
		sp = new StraightPlayer(hand);
	}
	/**
	 * Attempt to play into a Straight Flush.
	 * See TestStraightFlushPlayer for examples.
	 */
	@Override
	public boolean play() {
		boolean isStraight = sp.play();
//System.out.printf("StraightFlushPlayer: straight:%b%n", isStraight);
		if (isStraight) {
			boolean isFlush = sp.isFlush(sp.getBestHand().getHand());
//System.out.printf("StraightFlushPlayer: flush:%b%n", isFlush);
			return isFlush;
		}
		return false;
	}
	@Override
	public PokerHand getBestHand() {
		return sp.getBestHand();
	}

}
