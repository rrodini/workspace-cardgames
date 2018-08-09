package com.cardtech.game.blackjack;

import java.util.List;

import com.cardtech.core.Card;
import com.cardtech.game.Hand;

public class BJHand extends Hand {
	
	BJHandValue value = null;
	
	public BJHand(List<Card> cards) {
		super(cards);
	}
	
	public BJHand() {
		super();
	}
	
	public void evaluate() {
		value = BJHandEvaluator.evaluate(this);
	}
	
	public BJHandValue getValue() {
		return value;
	}
}
