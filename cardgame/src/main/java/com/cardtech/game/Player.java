package com.cardtech.game;

/**
 * Player is the superclass for various kinds of CardGame players.
 */
public class Player {
	String name;
	int winCount;
	
	public Player() {
		
	}
	
/**
 * Construct a player with the given name.
 * @param name - player's name.
 */
	public Player(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}
	
	public int getWinCount() {
		return winCount;
	}
	
	public synchronized void incrementWinCount() {
		winCount++;
	}
	
	public String toString() {
		if ((name == null) || (name.equals(""))) {
			name = "nameless";
		}
		return name;
	}
}
