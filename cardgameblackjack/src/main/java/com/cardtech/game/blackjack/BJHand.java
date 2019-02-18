package com.cardtech.game.blackjack;

import java.util.List;

import com.cardtech.core.Card;
import com.cardtech.game.Hand;

/**
 * BJHand is an extension of the Hand class.
 * It stores the blackjack value of the had after
 * the hand has been evaluated.
 *
 */
public class BJHand extends Hand {
	
	BJHandValue value = null;
	
	public BJHand(List<Card> cards) {
		super(cards);
	}
	
	public BJHand() {
		super();
	}
  /**
   * Evaluate the hand.
   */
	public void evaluate() {
		value = BJHandEvaluator.evaluate(this);
	}
  /**
   * Get the value of the hand.
   * @return BJ value of the hand.
   */
	public BJHandValue getValue() {
		return value;
	}
}
