package com.cardtech.game;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.cardtech.core.Card;

/**
 * Hand class is the superclass for various kinds of card game hands.
 */
public class Hand {

 /**
  * A List is the representation chosen for a card hand. 
  */
	protected List<Card> hand;
	
 /**
  * Default constructor.  No cards are placed in a hand at this point.  There should
  * be a call to setHand() in the future. 
  */
	protected Hand() {
		hand = new LinkedList<Card>();
	}
	
 /**
  * This constructor sets the cards in the hand. 
  * @param hand cards for the hand.
  */
	public Hand(List<Card> hand) {
//		this.hand = hand;
		this.hand = new LinkedList<>(hand);
	}
	
  /**
   * @return The cards in the hand.
   */
	public List<Card> getHand() {
		return hand;
	}

 /**
  * Sort the cards in the hand.  Remember Card implements Comparable making this possible.
  */
	public void sort() {
		Collections.sort(hand);
	}

 /**
  * Sort the cards in the hand.
  * @param orderBy a Card comparator.
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
// OLD
//		StringBuilder sHand = new StringBuilder();
//		sHand.append("[");
//		for (int i = 0; i < hand.size(); i++) {
//			Card card = getCard(i);
//			sHand.append(card.toString());
//			if (i < hand.size()-1) {
//				sHand.append(",");
//			}
//		}
//      return sHand.append("]").toString();
		String sHand = hand.stream().map( c -> c.toString())
				           .collect(Collectors.joining(",", "[", "]"));
		return sHand;
	}
		
  /**
   * Get the card at the index position.  This is a "read" interface and doesn't represent
   * removal of the card from the hand.
   * 
   * @param which index of card within hand.
   * @return card that was requested.
   * @throws IllegalArgumentException when bad index is passed.
   */
	public Card getCard(int which) {
		if (which < 0 || which > hand.size()-1) {
			throw new IllegalArgumentException("Bad index into hand:" + which);
		}
		return hand.get(which);
	}
  /**
   * Set the card at the index position. Added to support poker play
   * with wildcards when the wildcard is given an actual value;
   * 
   * @param which index of card within hand.
   * @param card to replace existing card (probably a joker).
   * @throws IllegalArgumentException when bad index is passed.
   */
	public void setCard(int which, Card card) {
		if (which < 0 || which > hand.size()-1) {
			throw new IllegalArgumentException("Bad index into hand:" + which);
		}
		hand.set(which, card);
	}
   /**
    * Does the hand contain the given card?
    * Note: relies on Card.equals().
    * @param which card sought.
    * @return true =&gt; yes hand does contain card.
    */
	public boolean contains(Card which) {
		return which == null ? false : hand.contains(which);
	}
  /**
   * Get the current number of cards in the hand.
   * @return # of cards.
   */
	public int getCardCount() {
		return hand.size();
	}

   /**
    * Add a card to the hand at the end.
    * @param c card to add.
    */
	public void addCard(Card c) {
		if (c == null) {
			return;
		}
		hand.add(c);
	}
	
}
