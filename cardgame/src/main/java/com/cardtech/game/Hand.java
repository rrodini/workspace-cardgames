package com.cardtech.game;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.cardtech.core.Card;

/**
 * Hand class is the superclass for various kinds of card game hands.
 */
public class Hand {

 /**
  * An ArrayList is the representation chosen for a card hand. 
  */
	protected List<Card> hand;
	
 /**
  * Default constructor.  No cards are placed in a hand at this point.  There should
  * be a call to setHand() in the future. 
  */
	protected Hand() {	
	}
	
 /**
  * This constructor sets the cards in the hand. 
  */
	public Hand(List<Card> hand) {
		this.hand = hand;
	}
	
  /**
   * @return The cards in the hand.
   */
	protected List<Card> getHand() {
		return hand;
	}

 /**
  * Sort the cards in the hand.  Remember Card implements Comparable making this possible.
  */
	protected void sort() {
		Collections.sort(hand);
	}

 /**
  * Sort the cards in the hand.
  * @param orderBy - a Card comparator.
  */
	protected void sort(Comparator<Card> orderBy) {
		Collections.sort(hand, orderBy);
	}
	
	
 /**
  * Show the cards in the hand on the console.
  */
	public void show() {
		System.out.println(this.toString());
	}
	
 /**
  * Show the cards in the hand on the console.
  */
	public String toString() {
		StringBuilder sHand = new StringBuilder();
		sHand.append("[");
		for (int i = 0; i < hand.size(); i++) {
			Card card = getCard(i);
			sHand.append(card.toString());
			if (i < hand.size()-1) {
				sHand.append(",");
			}
		}
		return sHand.append("]").toString();
	}
		
  /**
   * Get the card at the index position.  This is a "read" interface and doesn't represent
   * removal of the card from the hand.
   * 
   * @param which - index of card within hand.
   * 
   * @throws IllegalArgumentException when bad index is passed.
   */
	protected Card getCard(int which) {
		if (which < 0 || which > hand.size()-1) {
			throw new IllegalArgumentException("Bad index into hand:" + which);
		}
		return hand.get(which);
	}
	
}
