package com.cardtech.game.blackjack;
import static com.cardtech.game.blackjack.BJResponse.*;
/**
 * BJStrategy class is an abstract strategy.  Must have concrete subclasses. 
 */
public interface BJStrategy {
	/** Used. */
	public int hit(BJPlayerHand playerHand, BJDealerHand dealerHand);
	/** Not used. */
	static int alwaysHit() {
		return H;
	}
	/** Not used. */
	static int neverHit() {
		return S;
	}
	/** Not used. */
	static int randomHit() {
		double rand = Math.random();
		return rand >= 0.5? S : H;
	}
}
