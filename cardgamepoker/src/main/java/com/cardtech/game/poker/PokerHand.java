package com.cardtech.game.poker;
import java.util.ArrayList;
import java.util.List;

import com.cardtech.core.Card;
import com.cardtech.game.Hand;

/**
 * PokerHand represents a 5 card poker hand.  It implements Comparable so that this hand
 * can be compared to other poker hands to determine which hand wins. 
 */
public class PokerHand extends Hand implements Comparable<PokerHand> {

    public static final int POKERHAND_COUNT = 5;
	private PokerRankWithHighCards rank;
	
	// This constructor doesn't reflect dealing a card to each player and repeating.
 /**
  * Construct a poker hand given the cards for the hand.
  * @param hand - cards of the hand.
  */
	public PokerHand(List<Card> hand) {
		super(hand);
		checkCardCount(hand);
	}

 /**
  * Use of this constructor requires a call to setHand() later.
  */
	public PokerHand() {		
	}

 /**
  * Set the cards of the poker hand.  Necessary for testing.
  * @param hand - specifically cards of the hand.
  */
//	public void setHand(List<Card> hand) {
//		this.hand = hand;
//		checkCardCount(hand);		
//	}
 /**
  * Check that there are 5 cards in the hand.	
  * @param hand
  */
	private void checkCardCount(List<Card> hand) {		
		if (hand.size() != POKERHAND_COUNT) {
			throw new IllegalArgumentException("Poker hand must have 5 cards.  This hand's count: " + hand.size());
		}		
	}
	
 /**
  * Get the poker rank of the hand.	
  * @return the rank including all of its kickers to break ties.
  */
	public PokerRankWithHighCards getRank() {
		if (rank == null) {
			rank = PokerRanker.rank(this);
		}
		return rank;
	}
  /** 
   * Count the # of wildcards in the hand (0, 1, 2).
   * Note: there are only two jokers.
   * @return
   */
	public int countWildcards() {
		long count = 0;
		count = hand.stream()
				.filter( c -> c.isWild() )
				.count();
		return (int) count;
	}
  /**
   * Does the hand contain any wildcards?
   * 
   * @return true if hand contains any wildcards.
   */
	public boolean containsWildcards() {
		return countWildcards() > 0;
	}
 /**
  * 
  * This method is mandated by Comparable interface. <br/>
  * The logic is straightforward: <br/>
  * 1) compare the basic rank of "this" hand to the basic poker rank of the "other" hand. <br/>
  * 2) if the result is a tie, then one or more kickers must be compared. <br/>
  * 3) if any of the kickers breaks the tie, we're done but if all of the kickers are exhausted it's really a tie. <br/>
  * 
  */
	public int compareTo(PokerHand hand) {
		PokerRankWithHighCards myRank = this.getRank();
		PokerRankWithHighCards yourRank = hand.getRank();
		int comp = (myRank.getRank()).compareTo(yourRank.getRank());
		if (0 == comp) {
			// use one or more kickers to break the tie
			// remember that with identical ranks there are the same
			// number of potential kickers (so you won't get an ArrayIndexException below).
			Card [] myHighCards = myRank.getHighCards();
			Card [] yourHighCards = yourRank.getHighCards();
			for (int i=0; i < myHighCards.length; i++) {
				comp = Integer.valueOf(myHighCards[i].getValue()).compareTo(yourHighCards[i].getValue());
				if (comp !=0) return comp;
			}
			return 0;  // it's actually a tie!
		}
		return comp;
	}
	
}
