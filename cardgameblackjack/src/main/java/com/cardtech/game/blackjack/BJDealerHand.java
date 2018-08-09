package com.cardtech.game.blackjack;

import java.util.List;

import com.cardtech.core.Card;
/**
 * BJDealerHand class represents the dealer's hand.
 * Not sure if this is a specialization of BJHand or not.
 */
public class BJDealerHand extends BJHand {
	private boolean isDownCardVisible = false;
	
	public BJDealerHand(List<Card> cards) {
		super(cards);
	}
	
	public BJDealerHand() {
		super();
	}
	public Card getUpCard() {
		return getCard(0);
	}
	
	public void showDownCard() {
		isDownCardVisible = true;
	}
	
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
