package com.cardtech.game.blackjack;
import static com.cardtech.game.blackjack.BJResponse.*;
/**
 * BJStrategy class is an abstract strategy.  Must have concrete subclasses. 
 */
public interface BJStrategy {
	public int hit(BJPlayerHand playerHand, BJDealerHand dealerHand);
	static int alwaysHit() {
		return H;
	}
	static int neverHit() {
		return S;
	}
	static int randomHit() {
		double rand = Math.random();
		return rand >= 0.5? S : H;
	}
}
