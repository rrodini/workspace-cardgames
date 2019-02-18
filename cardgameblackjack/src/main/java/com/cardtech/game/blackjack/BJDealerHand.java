package com.cardtech.game.blackjack;

import java.util.List;

import com.cardtech.core.Card;
/**
 * BJDealerHand class represents the dealer's hand.
 */
public class BJDealerHand extends BJHand {
	private boolean isDownCardVisible = false;
  /**
   * Create the dealers hand with the given cards.
   * For test purposes only.	
   * @param cards given to the dealer.
   */
	BJDealerHand(List<Card> cards) {
		super(cards);
	}
  /**
   * Create an empty dealer hand.	
   */
	BJDealerHand() {
		super();
	}
  /** 
   * Get the dealer's up card.	
   * @return dealer's up card.
   */
	Card getUpCard() {
		return getCard(0);
	}
  /**
   * At this point in the game, it's okay to "show" (via toString())
   * the diealer's down card.
   */
	void showDownCard() {
		isDownCardVisible = true;
	}
  /**
   * Get the string that represents the dealer's hand.
   * While cards are still being dealt to the player, the
   * dealers down card should not be shown.
   */
	@Override
	public String toString() {
		String handString = super.toString();
		if (hand.size() == 2 && ! isDownCardVisible) {
			// hide the down card.
			handString = handString.substring(1, handString.length() -1);
			String [] cardString = handString.split(",");
			handString = "[" + cardString[0] + ",XX:XX]";
		}
		return handString;
	}

}
