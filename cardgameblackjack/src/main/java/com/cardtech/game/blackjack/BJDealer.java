package com.cardtech.game.blackjack;

import com.cardtech.game.Player;
/**
 * BJDealer class represents a blackjack dealer.  The dealer must
 * play according to a fixed strategy called "house rules."
 */
public class BJDealer extends Player {
	final BJStrategy strategy;
  /**
   * Create a blackjack dealer.	
   * @param name dealer's name.
   * @param strategy house dictated strategy.
   */
	BJDealer(String name, BJStrategy strategy) {
		super(name);
		this.strategy = strategy;
	}
  /**
   * For testing only.	
   * @return the house strategy.
   */
	BJStrategy getStrategy() {
		return strategy;
	}
}
