package com.cardtech.core;
import static com.cardtech.core.Rank.*;
import static com.cardtech.core.Suit.*;

import java.util.Comparator;

import com.cardtech.core.Rank;
import com.cardtech.core.Suit;
/**
 * Card is an enum with 52 values.  Typical value SIX_HEART which is, of course, is the six of hearts.
 * There are no jokers -- just the 13 cards of each suit (clubs, hearts, diamonds, spades).
 *
 */
public enum Card implements Comparator<Card>{
	TWO_CLUB(TWO, CLUB, 2),
	THREE_CLUB(THREE, CLUB,3),
	FOUR_CLUB(FOUR, CLUB, 4),
	FIVE_CLUB(FIVE, CLUB, 5),
	SIX_CLUB(SIX, CLUB, 6),
	SEVEN_CLUB(SEVEN, CLUB, 7),
	EIGHT_CLUB(EIGHT, CLUB, 8),
	NINE_CLUB(NINE, CLUB, 9),
	TEN_CLUB(TEN, CLUB, 10),
	JACK_CLUB(JACK, CLUB, 11),
	QUEEN_CLUB(QUEEN, CLUB, 12),
	KING_CLUB(KING, CLUB, 13),
	ACE_CLUB(ACE, CLUB ,14),
	TWO_HEART(TWO, HEART, 2),
	THREE_HEART(THREE, HEART, 3),
	FOUR_HEART(FOUR, HEART, 4),
	FIVE_HEART(FIVE, HEART, 5),
	SIX_HEART(SIX, HEART, 6),
	SEVEN_HEART(SEVEN, HEART, 7),
	EIGHT_HEART(EIGHT, HEART, 8),
	NINE_HEART(NINE, HEART, 9),
	TEN_HEART(TEN, HEART, 10),
	JACK_HEART(JACK, HEART, 11),
	QUEEN_HEART(QUEEN, HEART, 12),
	KING_HEART(KING, HEART, 13),
	ACE_HEART(ACE, HEART, 14),
	TWO_DIAMOND(TWO, DIAMOND, 2),
	THREE_DIAMOND(THREE, DIAMOND, 3),
	FOUR_DIAMOND(FOUR, DIAMOND, 4),
	FIVE_DIAMOND(FIVE, DIAMOND, 5),
	SIX_DIAMOND(SIX, DIAMOND, 6),
	SEVEN_DIAMOND(SEVEN, DIAMOND, 7),
	EIGHT_DIAMOND(EIGHT, DIAMOND, 8),
	NINE_DIAMOND(NINE, DIAMOND, 9),
	TEN_DIAMOND(TEN, DIAMOND, 10),
	JACK_DIAMOND(JACK, DIAMOND, 11),
	QUEEN_DIAMOND(QUEEN, DIAMOND, 12),
	KING_DIAMOND(KING, DIAMOND, 13),
	ACE_DIAMOND(ACE, DIAMOND, 14),
	TWO_SPADE(TWO, SPADE, 2),
	THREE_SPADE(THREE, SPADE, 3),
	FOUR_SPADE(FOUR, SPADE, 4),
	FIVE_SPADE(FIVE, SPADE, 5),
	SIX_SPADE(SIX, SPADE, 6),
	SEVEN_SPADE(SEVEN, SPADE, 7),
	EIGHT_SPADE(EIGHT, SPADE, 8),
	NINE_SPADE(NINE, SPADE, 9),
	TEN_SPADE(TEN, SPADE, 10),
	JACK_SPADE(JACK, SPADE, 11),
	QUEEN_SPADE(QUEEN, SPADE, 12),
	KING_SPADE(KING, SPADE, 13),
	ACE_SPADE(ACE, SPADE, 14);
	
	private Rank rank;
	private Suit suit;
	private int value;
	
	private Card(Rank r, Suit s, int v) {
		rank = r;
		suit = s;
		value = v;
	}

 /**
  * Get the numeric value of the card.  This is needed because an enum provides an ordinal value for
  * each value, but we want each of the aces to have the same numeric value of 14.
  */
	public int getValue() {
		return value;
	}
	
 /**
  * Get the rank of the card, e.g. two, three, ...ace.
  */
	public Rank getRank() {
		return rank;
	}
	
 /**
  * Get the suit of the card.	
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
    * Since Card class implements Comparator a 'compare' method must be provided.
    * Compare two card values by comparing their numerical values.  At present suit is not considered.
    * Since suit is an enum its values are comparable too.  Note that many card games do not order
    * the suits but it may be convenient to do so in order to break ties.  When this is done no two cards
    * are "equal".
    * 
    * @param c2 - Card to compare "this" to.
    * @return int value as prescribed by Comparable. 	
    */
	public int compare(Card c1, Card c2) {
		Integer c1Integer = new Integer(c1.value);
		Integer c2Integer = new Integer(c2.value);
		int valueResult = c1Integer.compareTo(c2Integer);
		if (valueResult == 0) {
			// Activate line below if you want suits to be ordered.
			//return c1.suit.compareTo(c2.suit);
			return 0;
		}
		return valueResult;
	}
		

	
}
