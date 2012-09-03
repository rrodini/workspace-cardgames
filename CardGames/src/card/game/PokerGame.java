package card.game;
import java.util.ArrayList;
import java.util.NavigableSet;
import java.util.TreeMap;

import card.core.Card;

/**
 * PokerGame is a subclass of CardGame.  <br/><br/> 
 * This is five card poker with no draw and no betting as you go straight to "showdown." 
 */
public class PokerGame extends CardGame {

	// These values are after the game is played.
	PokerPlayer winner = null;
	PokerHand winningHand = null;
	PokerRank winningRank = null;
	
/**
 * Construct a poker game with the given players.
 * @param players - array of player objects.
 */
	public PokerGame(PokerPlayer... players) {
		super(players);
	}

	@Override
	public void initialize(boolean shuffle) {
		if (shuffle) {
			deck.shuffle();
		}
		dealCardsToPlayers();
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
			ArrayList<Card> playerHand = dealtCards[j];
			// This is the crucial call that "sets" the player's hand.
			hands.set(j, new PokerHand(playerHand) );
		}
		
	}
	
	private void dealACard(ArrayList<Card> hand) {
		hand.add(deck.deal(1).get(0));
	}
	
	@Override
 /**
  * play the game of *straight* five card poker.  No draw, no betting, just showdown.
  */
	public void play() {
		// TreeMap data structure is crucial.  The key is the poker hand which is sorted in ascending order.
		// The value is the integer index of the player's hand.
		TreeMap<PokerHand,Integer> treeMap = new TreeMap<PokerHand, Integer>();
		for (int i=0; i < noOfPlayers; i++) {
			treeMap.put((PokerHand) hands.get(i), i);
		}
		// Now we have to reverse the sort order of the hands so that the highest hand wins.
		NavigableSet<PokerHand> keySet = treeMap.descendingKeySet();
		
		// Show the hand ranks in descending order - just for debugging
//		for (PokerHand key : keySet){
//			System.out.printf("Rank: %s, p%d\n", key.getRank(), treeMap.get(key));
//		}
		// This is the winning hand. 
		winningHand = keySet.first();
		// First getRank() returns rank with high cards.  Second getRank() is just the PokerRank.
		winningRank = winningHand.getRank().getRank();
		// Now get the index of the winning player.
		int windex = treeMap.get(keySet.first());
		// Index into the parallel array of players to get the winner.
		winner = (PokerPlayer) players.get(windex);
	}
	
 /**
  * 
  */
	public void show() {
		int which = 0;
		// Show the player's hands (these have been sorted making things a little easier).
		System.out.println("Player's hands:");
		for (Hand hand: hands) {
			System.out.printf("%s's hand, %s\n", players.get(which), hand );
			// remember that players and hands are parallel arrays
			which++;
		}
	}
	
	
	@Override
	public PokerPlayer getWinner() {
		winner.incrementWinCount();
		winner.setHighestWinningRank(winningRank);
		return winner;
	}
}
