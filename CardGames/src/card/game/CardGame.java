package card.game;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import card.core.Deck;
import static card.core.DeckOrder.*;

/**
 * CardGame is the superclass for WarGame and PokerGame. <br/>
 * For the two games implemented the pattern for the game is simple: <br/>
 * 1. Construct the game <br/>
 *    a) identify the number of players <br/>
 * 2. Initialize the game  <br/>
 *    a) shuffle the deck (optional for testing purposes) <br/>
 *    b) deal cards to the players <br/>
 * 3. Play the game as per its rules <br/>
 * 4. Determine the winning hand <br/>
 */
abstract public class CardGame {
	
	protected int noOfPlayers = 0;
 /**
  * players is the list of player objects participating in the game.  Although the
  * number won't change during the course of the game it's best represented as a list.
  */
	protected ArrayList<Player> players;
 /**
  * hands represent the hands dealt to the players.  Rather than make them instance variables
  * of the player object it's better to have them as instance variables of the game object.
  * This permits many games to be played in parallel on a multi-core machine.
  * 
  * So hands is a "parallel" array to players.
  */
	protected ArrayList<Hand> hands;
	
 /**
  * deck of course is the deck of cards used for this instance of the game.	
  */
	protected Deck deck;
		
	public CardGame(Player... players) {
		initializeCardGame(players);
		deck = new Deck();	
	}

 /**
  * initialize the CardGame by saving off the player objects.
  * @param players - array of player objects
  */
	private void initializeCardGame(Player... players) {
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
  * This constructor is used for an artificially ordered deck
  * @param deck - a deck in a specific order
  * @param players - players in the game.
  */
	protected CardGame(Deck deck, Player... players) {
		initializeCardGame(players);
		this.deck = deck;		
	}

 /**
  * This method represents the initialization needed for a card game.  For a random game "shuffle" should be true.
  * @param shuffle - false means don't shuffle the deck before the deal.  true means shuffle (randomize) the deck before the deal.
  */
	public abstract void initialize(boolean shuffle);
	
 /**
  * Play the game according to its rules.
  */
	public abstract void play();

 /**
  * Determine the winner of the game.	
  * @return player (object) that won the game.
  */
	public abstract Player getWinner();
	
}
