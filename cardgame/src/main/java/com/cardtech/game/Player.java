package com.cardtech.game;

/**
 * Player is the superclass for various kinds of CardGame players.
 */
public class Player {
	/** Name of the player. */
	String name;
	/** Not used. */
	int winCount;
	
	public Player() {
		
	}
	
/**
 * Construct a player with the given name.
 * @param name player's name.
 */
	public Player(String name) {
		this.name = name;
	}
/**
 * Get the name of the player
 * @return player name.
 */
	public String getName() {
		return name;
	}
/**
 * Set the name of the player
 * @param name of player.
 */
	protected void setName(String name) {
		this.name = name;
	}
/**
 * Get the player's win count.	
 * @return win count.
 */
	public int getWinCount() {
		return winCount;
	}
/**
 * Increment the win count.
 */
	public void incrementWinCount() {
		winCount++;
	}
/**
 * Simple toString method
 * @returns player name.	
 */
	public String toString() {
		if ((name == null) || (name.equals(""))) {
			name = "nameless";
		}
		return name;
	}
}
