package com.cardtech.game.poker;
import com.cardtech.core.Card;

/**
 * PokerRankWithHighCards is a value class to hold the evaluated rank (an enum value)
 * and the kickers needed to break a potential tie.
 *
 */

public class PokerRankWithHighCards {
	final PokerRank rank;
	final Card [] highCards; // used to break ties when ranks are equal
	
 /**
  * Constructor that saves the values
  * @param rank - evaluated poker rank
  * @param highCards - kickers
  */
	PokerRankWithHighCards(PokerRank rank, Card [] highCards) {
		this.rank = rank;
		this.highCards = highCards;
	}

 /**
  * Get the rank.
  * @return the evaluated rank (see PokerRank)
  */
	public PokerRank getRank() {
		return rank;
	}

 /**
  * Get the kickers.
  * @return the kickers
  */
	public Card[] getHighCards() {
		return highCards;
	}
	
 /** 
  * @return the string representation of the rank and the kickers.
  */
	public String toString() {
		String sRank = rank.toString() + " kickers: ";
		for (int i=0; i < highCards.length ; i++) {
			sRank = sRank + highCards[i].toString();
			if (i < highCards.length - 1) {
				sRank = sRank + ", ";
			}
		}
		return sRank;
	}
	
}
