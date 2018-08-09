package com.cardtech.game.blackjack;

import com.cardtech.game.Player;
/**
 * BJPlayer class represents a blackjack player.
 */
public class BJPlayer extends Player {
	final BJStrategy strategy;
	
	BJPlayer(String name, BJStrategy strategy) {
		super(name);
		this.strategy = strategy;
	}
	
	BJStrategy getStrategy() {
		return strategy;
	}
}
