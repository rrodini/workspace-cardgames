package com.cardtech.game;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.cardtech.core.Deck;

/**
 * CardGame is the superclass for WarGame and PokerGame. And Blackjack. <br/>
 * The class implements the GOF Template Pattern as follows: <br/>
 * 1. Construct the game subclass<br/>
 *    a) identify the number of players <br/>
 * 2. Initialize the game  <br/>
 *    a) shuffle the deck (optional for testing purposes) <br/>
 *    b) deal cards to the players <br/>
 * 3. Play the game as per its rules using a fixed strategy<br/>
 * 4. Determine the winning hand <br/>
 */
abstract public class CardGame {
 /**
  * the number of players in the game.	
  */
	protected int noOfPlayers = 0;
 /**
  * players is the list of player objects participating in the game.  Although the
  * number won't change during the course of the game it's best represented as a list.
  */
	protected List<Player> players;
 /**
  * hands represent the hands dealt to the players. 
  * So, hands is a "parallel" array to players.
  */
	protected List<Hand> hands;
	
 /**
  * deck of course is the deck of cards used for this instance of the game.	<br/>
  * Note: A deck may or may not be 52 cards.
  */
	protected Deck deck;
 /**
  * This constructor constructs a standard deck.
  * @param players players in the game.
  */
	public CardGame(Player... players) {
		if (players.length == 0) {
			throw new IllegalArgumentException("CardGame cannot have 0 players.");
		}
		initialize(players);
		deck = new Deck();	
	}
 /**
  * This constructor is used for an artificially ordered deck
  * @param deck a deck in a specific order
  * @param players players in the game.
  */
	public CardGame(Deck deck, Player... players) {
		if (players.length == 0) {
			throw new IllegalArgumentException("CardGame cannot have 0 players.");
		}
		if (deck == null) {
			throw new IllegalArgumentException("CardGame cannot have a null deck.");
		}
		initialize(players);
		this.deck = deck;		
	}


 /**
  * initialize the CardGame by saving off the player objects.
  * @param players array of player objects
  */
	protected void initialize(Player... players) {
		this.players = new ArrayList<Player>(Arrays.asList(players));
		// ATTENTION: Constructor below doesn't really allocate space for elements.
		this.hands = new ArrayList<Hand>(players.length);
		// ATTENTION: That's why this loop is necessary.
		for (int i= 0; i < players.length; i++) {
			this.hands.add(null);
		}
		noOfPlayers = players.length;
	}
 /**
  * This method represents the initialization needed for a card game.  For a random game "shuffle" should be true.
  * @param shuffle false means don't shuffle the deck before the deal.  true means shuffle (randomize) the deck before the deal.
  */
	public abstract void initialize(boolean shuffle);
	
 /**
  * Play the game according to its rules.
  */
	public abstract void play();

 /**
  * Determine the winner of the game.	
  * @return player player(s) that won the game. Plural in case of a tie.
  */
	public abstract List<Player> getWinner();
	
}
