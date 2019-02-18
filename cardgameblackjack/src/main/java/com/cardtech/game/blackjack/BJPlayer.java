package com.cardtech.game.blackjack;

import com.cardtech.game.Player;
/**
 * BJPlayer class represents a blackjack player.
 * Every player is using a fixed strategy "stolen" from Wikipedia.
 */
public class BJPlayer extends Player {
	final BJStrategy strategy;
	/**
	 * Create the player and give a strategy.
	 * @param name player name.
	 * @param strategy player strategy.
	 */
	BJPlayer(String name, BJStrategy strategy) {
		super(name);
		this.strategy = strategy;
	}
	/**
	 * Get the player's strategy.
	 * @return player's strategy.
	 */
	BJStrategy getStrategy() {
		return strategy;
	}
}
