
package com.cardtech.game.war;

import static com.cardtech.game.war.WarRoundContext.NO_WAR;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import com.cardtech.core.Card;
import com.cardtech.core.CardBySuitComparator;
import com.cardtech.core.Deck;
import com.cardtech.game.CardGame;
import com.cardtech.game.Hand;
import com.cardtech.game.Player;

/**
 * 
 * WarGame is a subclass of CardGame.<br/><br/>
 * This is a children's game with no real strategy.  That's why is good for a computer to play.<br/>
 * There should always be a winner although it is not predictable how many "rounds" will be needed to determine that winner. 
 */
public class WarGame extends CardGame {
 /**
  * This list tracks the identities of the active players.  At the end
  * there should be only one active player, namely the winner.  However,
  * in a very unusual (impossible?) case the game can end in an absolute draw.
  */
	// Used to track the identity of the remaining players
	ArrayList<Integer> activePlayers;
 /**
  * Count the top level rounds in this game of war.  Basically this is the number of times the "up" cards
  * are played by each player *outside* the context of war.	
  */
	int rounds = 0; 
	
 /**
  * Construct a WarGame with the given player objects.
  * @param players - array of player objects.
  */
	public WarGame(Player... players) {
		super(players);
		initializeActivePlayers();
	}
	
 /**
  * Construct a WarGame with the given deck and the given player objects.
  * @param deck - an artificial deck for testing purposes.
  * @param players - array of player objects.
  */
	public WarGame(Deck deck, Player... players) {
		super(deck, players);
		initializeActivePlayers();
	}

	private void initializeActivePlayers() {
		activePlayers = new ArrayList<Integer>(noOfPlayers);
		for (int i = 0; i < noOfPlayers; i++) {
			activePlayers.add(i, Integer.valueOf(i));
			hands.set(i, new WarHand() );
		}
	}
	
	@Override
	public void initialize(boolean doShuffle) {
		if (doShuffle) {
			deck.shuffle();
		}
		dealCardsToPlayers();
	}

	private void dealCardsToPlayers() {
		int playerNo = 0;
		// Just deal the cards to the players one by one until the deck is empty.
		while (!deck.isEmpty()) {               
			dealCardToPlayer(playerNo);
			playerNo++;
			playerNo %= noOfPlayers;
		}
	}
	
		
	private void dealCardToPlayer(int playerNo) {
		if (!deck.isEmpty()) {
			WarHand hand = (WarHand) hands.get(playerNo);
			hand.addCard(deck.deal(1));
		}
	}
	
	@Override
 /**
  * Play the game till it concludes.  The game consists of a number of "rounds" which can be simple	or complex.<br/>
  * In a simple or non-war round each player plays an up card and the player with the highest card wins them all. <br/>
  * In a complex or war round each player plays and up card and any match on the value of an up card triggers war.  <br/>
  * War consists of playing 3 down cards followed by another up card.  This means you can have war again... <br/> 
  */
	public void play() {
		while (!gameIsOver()) {
			playRound(NO_WAR);			
		}
	}

	private boolean gameIsOver() {
		int count = 0;
		for (Hand player : hands) {
			if (! ((WarHand) player).isHandEmpty()) {
				count++;
			}
		}
		// Count == 1  means that one player has all of the cards!
		// Count == 0 means that the players have played to a tie (most unusual).
		return (count == 1) || (count == 0);
	}
	
 /**
  * Play a round.  Usually this method would be private but it's public for testing purposes.
  * @param wc - used to detect nested war rounds.
  */
	public void playRound(WarRoundContext wc) {
		boolean tie = true;
		int result = 0;
		while (tie && !gameIsOver() ) {
			showPlayerCardCount();
			result = playUpCard(wc);
			if (result == -1) {
				playWarRound();
			} else {
				awardCards(result);
				tie = false;
			}
		}
	}
	
 /**
  * Buggy:  See new implementation below.
  */
//	private int playUpCard1(WarRoundContext wc) {
//		if (wc == NO_WAR) {
//			rounds++;
//		}
//		int which = 0;
//		TreeMap<Card, Integer> tm = new TreeMap<Card, Integer>();
//		for (Hand player : hands) {
//			((WarHand) player).playUpCard(wc);
//			Card c = ((WarHand) player).getUpCard();
//			//	This will help keep track of who wins the hand
//			// It relies on fact that CardComparator object gives a complete ordering of the cards.
//			tm.put(c, which);
//			which++;
//		}
//		// show the cards just for debugging
//		showUpCards();
//		Set<Card> keySet = tm.descendingKeySet();
//		Card lastCard = null;
//		for (Card key : keySet){
//			if (null == lastCard) {
//				// first time through this loop
//				// save the first key (Card) to see if the next card ties
//				lastCard = key;
//				// save the player with the highest card.  If there are no
//				// ties then this is the winner.
//				which = tm.get(key);
//			} else {
//				if (lastCard.getValue() == key.getValue()) {
//					// There's at least one tie so bail out now.
//					return -1;
//				}
//			}
//		}
//		return which;
//	}
  /**
   * CardCompartor exists just for PlayUpCard.	
   *
   */
//	private class CardBySuitComparator implements Comparator<Card> {
//
//		@Override
//		public int compare(Card c1, Card c2) {
//			int val1 = c1.getValue();
//			int val2 = c2.getValue();
//			int suit1 = c1.getSuit().ordinal();
//			int suit2 = c2.getSuit().ordinal();
//			// size should be 4 since there are 4 suits.
//			int size = Suit.values().length;
//			int total1 = val1 * size + suit1;
//			int total2 = val2 * size + suit2;
//			// no danger of overflow here since the numbers are small.
//			return total1 - total2;
//		}
//		
//	}
	
	private int playUpCard(WarRoundContext wc) {
		if (wc == NO_WAR) {
			rounds++;
		}
		int which = 0;
		CardBySuitComparator cardComp = new CardBySuitComparator();
		TreeMap<Card, Integer> tm = new TreeMap<Card, Integer>(cardComp);
		for (Hand player : hands) {
			((WarHand) player).playUpCard(wc);
			Card c = ((WarHand) player).getUpCard();
			//	This will help keep track of who wins the hand
			// It relies on fact that CardComparator object separates cards of equal value.
			tm.put(c, which);
			which++;
		}
		// show the cards just for debugging
		showUpCards();
		Set<Card> keySet = tm.descendingKeySet();
		// assume at least two players
		Card lastCard = null;
		Card penultimateCard = null;
		Iterator<Card> iter = keySet.iterator();
		if (iter.hasNext()) {
			lastCard = (Card) iter.next();
			which = tm.get(lastCard);
		}
		if (iter.hasNext()) {
			penultimateCard = (Card) iter.next();
		}
		if (( lastCard != null && penultimateCard != null ) &&
			( lastCard.getValue() == penultimateCard.getValue() )) {
			// there is a tie!
			return -1;
		}
		// this player is the winner.
		return which;
	}
	/**
	 * for debugging only
	 */
	private void showPlayerCardCount() {
		int which = 0;
		System.out.print("# Cards:   ");
		for (Hand player : hands) {
			System.out.printf("p%d %d ", which, ((WarHand) player).getHandCount());
			which++;
		}
		System.out.println();
	}
	/**
	 * for debugging only
	 */
	private void showUpCards() {
		int which = 0;
		System.out.print("upCards:   ");
		for (Hand player : hands) {
			Card c = ((WarHand) player).getUpCard();
			System.out.printf("p%d %s ", which, c.toString());
			which++;
		}
		System.out.println();
	}
	
 /**
  * Play a round of war by simply playing three down cards followed by another "round." <br/>
  * Recursion alert!  This may lead to another war round.<br/>
  * Note: Some rules play 3 down cards, others only 1.
  */
	private void playWarRound() {
		showWar();
		for (Hand player : hands) {
			((WarHand) player).playDownCard(3);
		}
		showDownCardsCount();
		playRound(WarRoundContext.YES_WAR);
	}
// for debugging
	private void showWar() {
		System.out.println("WAR...WAR...WAR");
	}
// for debugging	
	private void showDownCardsCount() {
		int which = 0;
		System.out.print("downCards: ");
		for (Hand player : hands) {
			int count = ((WarHand) player).getDownCardsCount();
			System.out.printf("p%d %d ", which, count);
			which++;
		}
		System.out.println();
	}
	
 /**
  * Award cards (upCards and downCards) to the winner of the round.  The upCards should never be empty
  * but the downCards may be empty.
  * @param which - index of player who won the round.
  */
	private void awardCards(int which) {
		// debug statement
		System.out.printf("p%d wins round%n", which);
		WarHand winner = (WarHand) hands.get(which);		
		for (Hand hand : hands) {
			winner.addCards(((WarHand) hand).getDownCards());
			winner.addCards(((WarHand) hand).getUpCards());
		}
		eliminateEmptyHands();
	}

 /**
  * At this point some players may have no cards and should be removed from the game.
  */
	private void eliminateEmptyHands() {
		int which = 0;
		for (Hand player : hands) {
			int count = ((WarHand) player).getHandCount();
			if (count == 0) {
// debug statement				
System.out.println("Eliminating p" + activePlayers.get(which));
				players.remove(which);
				hands.remove(which);
				activePlayers.remove(which);
			}
			which++;
		}
// debug statement				
System.out.print("Active: ");
for (Integer alive: activePlayers) {
	System.out.printf("p%d", alive );
}
System.out.println();
		
	}
	
 /**
  * Get the given player's hand.  Used for testing only.
  * @param player object
  * @return player's hand
  */
	public WarHand getPlayerHand(Player player) {
		int which = players.indexOf(player);
		if (which < 0) {
			throw new IllegalStateException("Player cannot be found: " + player);
		}
		return (WarHand) hands.get(which);
	}
	
 /**
  * Get the number of active players.  Used for testing only.
  * @return number of active players.
  */
	public int getActivePlayerCount() {
		return activePlayers.size();
	}

	@Override
 /**
  * Get the winner of this war game. This is super easy due to the logic of elminateEmptyHands() method.
  */
	public List<Player> getWinner() {
		List<Player> winners = new ArrayList<Player>();
		if (getActivePlayerCount() == 4) {
		//  This is the highly unusual case that the game ends in a tie.
		//  The is a test case for this.
			winners = players;
		} else if (getActivePlayerCount() == 1 ) {
		    winners.add(players.get(0));
		}
		//winner.incrementWinCount();
		//winner.setNewRoundsToAWin(rounds);
		return winners;
	}
	
}
