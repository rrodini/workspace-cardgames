package com.cardtech.game.blackjack;

import com.cardtech.game.Player;
/**
 * BJPlayer class represents a blackjack player.
 */
public class BJDealer extends Player {
	final BJStrategy strategy;
	
	BJDealer(String name, BJStrategy strategy) {
		super(name);
		this.strategy = strategy;
	}
	
	BJStrategy getStrategy() {
		return strategy;
	}
}
