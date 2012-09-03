package card.game;
import card.core.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Hand class is the superclass for various kinds of card game hands.
 */
public class Hand {

 /**
  * An ArrayList is the representation chosen for a card hand. 
  */
	protected ArrayList<Card> hand;
	
 /**
  * Default constructor.  No cards are placed in a hand at this point.  There should
  * be a call to setHand() in the future. 
  */
	protected Hand() {	
	}
	
 /**
  * This constructor sets the cards in the hand. 
  */
	public Hand(ArrayList<Card> hand) {
		this.hand = hand;
	}
	
  /**
   * @return The cards in the hand.
   */
	protected ArrayList<Card> getHand() {
		return hand;
	}

 /**
  * Sort the cards in the hand.  Remember Card implements Comparator making this possible.
  */
	protected void sort() {
		// Looks strange but any Card object is-a Comparator object.
		Comparator<Card> c =  (Comparator<Card>) Card.TWO_CLUB; 
		Collections.sort(hand, c);
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
