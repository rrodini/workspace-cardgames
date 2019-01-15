package com.cardtech.core;
import com.cardtech.core.Suit;
import static com.cardtech.core.Rank.TWO;
import static com.cardtech.core.Rank.ACE;

/**
 * Card is an object with suit &amp;  value.  Typical value is (HEART, 6) which is, of course, is the six of hearts.
 * There are no jokers -- just the 13 cards of each suit (clubs, hearts, diamonds, spades).
 *
 */
public final class Card implements Comparable<Card>{
	// make the instance variables immutable.
	private final Suit suit;
	private final int value;
/**
 * Construct a Card.	
 * @param s suit.
 * @param v numeric value.
 */
	public Card(Suit s, int v) {
		// only accept 2 .. 14 for now
		if (v < TWO.getValue() || v > ACE.getValue()) {
			throw new IllegalArgumentException("card value out of range: " + v);
		}
		if (s == null) {
			throw new IllegalArgumentException("suit cannot be null");
		}
		suit = s;
		value = v;
	}

 /**
  * Get the numeric value of the card.  This is needed because an enum provides an ordinal value for
  * each value, but we want each of the aces to have the same numeric value of 14.
  * @return value of card.
  */
	public int getValue() {
		return value;
	}
	
 /**
  * Get the suit of the card.	
  * @return suit of card.
  */
	public Suit getSuit() {
		return suit;
	}
	
 /**
  * Show the card "image."
  */
	public void show() {
		System.out.print(this.toString() + " ");  // 2:CLUB prints as "TWO_CLUB "
	}

  /**
   * Get the card string.
   * @return string value for card.
   */
	public String toString() {
		StringBuffer buf = new StringBuffer();
		switch (this.value) {
	case 11:
			buf.append("J:");
			break;
	case 12:
			buf.append("Q:");
			break;
	case 13:
			buf.append("K:");
			break;
	case 14:
			buf.append("A:");
			break;
	default:
			// all non-face cards here
			buf.append(this.value + ":");
		}
		buf.append(this.suit.toString());
		return buf.toString();
	}

   /**
    * Since Card class implements Comparable a 'compareTo' method must be provided.
    * Compare two card values by comparing their numerical values.  At present suit is not considered.
    * Since suit is an enum its values are comparable too.  Note that many card games do not order
    * the suits but it may be convenient to do so in order to break ties.  
    * 
    * @param c2 - Card to compare "this" to.
    * @return int value as prescribed by Comparable. 	    */
	@Override
	public int compareTo(Card c2) {
		int result = this.value - c2.value;
		if (result == 0) {
			// add code here to consider suit
			//return this.suit.compareTo(c2.suit); 
		}
		return result;
	}
	/**
	 * Implement equals as per "value equality."
	 * @param o - Card to compared.
	 * @return boolean true =&gt; card values are equal.
	 */
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Card)) {
			return false;
		}
		Card c2 = (Card) o;
		return (this.value == c2.value) && (this.suit.equals(c2.suit));
	}
	/**
	 * Implement hashCode (to complement equals() method. 
	 * @return int hash code for this card.
	 */
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + this.value;
		result = 31 * result + this.suit.ordinal();
		return result;
	}
}
