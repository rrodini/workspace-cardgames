package com.cardtech.game.poker;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cardtech.core.Card;
import com.cardtech.core.Deck;
import com.cardtech.game.CardGame;
import com.cardtech.game.Hand;
import com.cardtech.game.Player;
import com.cardtech.game.poker.wildcard.PlayWildcards;

/**
 * PokerGame is a subclass of CardGame.  <br/><br/> 
 * This is five card poker with no draw and no betting as you go straight to "showdown." 
 */
public class PokerGame extends CardGame {

	// These values are after the game is played.
	List<Player> winners = null;
	PokerHand winningHand = null;
	PokerRankWithHighCards winningRank = null;
	// Addition for game with jokers (wildcards).
	// One for each player but entry may be null.
	List<PokerHand> wildcardHands;
/**
 * Construct a poker game with the given players
 * using a standard 52 card deck;
 * @param players - array of player objects.
 */
	public PokerGame(PokerPlayer... players) {
		super(players);
	}
 /**
  * Construct a PokerGame with the given deck and the given player objects.
  * @param deck - a standard deck with two jokers or
  *               an artificial deck for testing purposes.
  * @param players - array of player objects.
  */
	public PokerGame(Deck deck, Player... players) {
		super(deck, players);
		//initialize(players);
	}
	
	protected void initialize(Player... players) {
		super.initialize(players);
		wildcardHands = new ArrayList<>(players.length);
		// allocate space
		for (int i = 0; i < players.length; i++) {
			wildcardHands.add(null);
		}
	}

	public void initialize(boolean shuffle) {
		if (shuffle) {
			deck.shuffle();
		}
		dealCardsToPlayers();
	}
 /** 
  * Return a reference to the deck used for poker game.
  */
	public Deck getDeck() {
		return deck;
	}
 /**
  * Deal the cards to the poker players.  Note that cards are dealt one by one
  * to each player in turn.
  */
	private void dealCardsToPlayers() {
		ArrayList<Card>[] dealtCards = new ArrayList[noOfPlayers];
		for (int j = 0; j < noOfPlayers; j++) {
			dealtCards[j] = new ArrayList<Card>();
		}
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < noOfPlayers; j++) {
				dealACard(dealtCards[j]);
			}
		}
		for (int j = 0; j < noOfPlayers; j++) {
			PokerHand playerHand = new PokerHand(dealtCards[j]);
			// This is the crucial call that "sets" the player's hand.
			hands.set(j, playerHand );
			//wildcardHands.set(j, null);
			if (playerHand.containsWildcards()) {
				PlayWildcards pwc = new PlayWildcards(playerHand);
				wildcardHands.set(j, pwc.getBestHand());
			}
		}
		
	}
	
	private void dealACard(List<Card> hand) {
		hand.add(deck.deal(1).get(0));
	}
	
	@Override
 /**
  * play the game of *straight* five card poker.  No draw, no betting, just showdown.
  */
	public void play() {
		// PokerHandMultiMap data structure is crucial.  The key is the poker hand which is sorted in ascending order.
		// The value is the list of integer indexes of the players with the same poker hand.
		// Typically this list has a single element, but ties are possible in poker, hence the value is a list.
		PokerHandMultiMap treeMap = new PokerHandMultiMap();
		for (int i=0; i < noOfPlayers; i++) {
			// Here is where each hand gets ranked (showdown)!  This action is implicit in the
			// use of a TreeMap which requires Comparable<Hand> to be implemented.
			PokerHand playerHand = (PokerHand) hands.get(i);
			if (playerHand.containsWildcards()) {
				playerHand = wildcardHands.get(i);
			}
			treeMap.put(playerHand, i);
		}
		
		// Show the hand ranks in ascending order - just for debugging
		// DEBUG DEBUG DEBUG DEBUG
		for (Map.Entry<PokerHand, List<Integer>> entry : treeMap.entrySet()){
			PokerHand ph = entry.getKey();
			List<Integer> winners = entry.getValue();
			System.out.printf("Rank: %s [", ph.getRank().getRank());
			for (int i = 0; i < winners.size(); i++) {
				System.out.printf("p%d ", winners.get(i));
			}
			System.out.printf("]\n");
		}
		// The last entry is the highest poker hand.
		Map.Entry<PokerHand, List<Integer>> winningEntry = treeMap.lastEntry();
		// This is the winning hand. 
		winningHand = winningEntry.getKey();
		// First getRank() returns rank with high cards.  Second getRank() is just the PokerRank.
		winningRank = winningHand.getRank();
		// Now get the list of the winning players.
		List<Integer> winningIndexes = winningEntry.getValue();
		int noWinners = winningIndexes.size();
		winners = new ArrayList<Player>(noWinners);
		for (int i=0; i < noWinners; i++) {
			winners.add(players.get(winningIndexes.get(i)));
		}
	}

 /**
  * Show the player's hand. Just for debugging.
  */
	public void show() {
		int which = 0;
		// Show the player's hands (these have been sorted making things a little easier).
		System.out.println("Player's hands:");
		for (Hand hand: hands) {
			System.out.printf("%s's hand: %s wildcard hand: %s\n", players.get(which), hand, wildcardHands.get(which));
			// remember that players and hands are parallel arrays
			which++;
		}
	}
	
/**
 * Return the list of winning plays.  Typically the list has a single player object, but in case
 * of a tie there are multiple players.	
 * @return list of players
 */
	@Override
	public List<Player> getWinner() {
		
		//winner.incrementWinCount();
		// winner.setHighestWinningRank(winningRank);
		return  winners;
	}
	
/**
 * @return poker hands of the completed game.
 */
	public List<? extends Hand> getHands() {
		return /* List<PokerHands> */ hands;
	}

/**
 * @return the poker rank of the winning hand.
 */
	public PokerRankWithHighCards getWinningRank() {
		return winningRank;
	}
}
