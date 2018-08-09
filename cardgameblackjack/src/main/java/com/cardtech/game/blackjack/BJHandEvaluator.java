package com.cardtech.game.blackjack;

import static com.cardtech.game.blackjack.BJHandValue.Firmness.HARD_HAND;
import static com.cardtech.game.blackjack.BJHandValue.Firmness.SOFT_HAND;
import static com.cardtech.game.blackjack.BJHandValue.Precis.EXACTLY21_VALUE;
import static com.cardtech.game.blackjack.BJHandValue.Precis.OVER21_VALUE;
import static com.cardtech.game.blackjack.BJHandValue.Precis.UNDER21_VALUE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.cardtech.core.Card;
import com.cardtech.core.Rank;
import com.cardtech.game.Hand;

/**
 * BJHandEvaluator is given a hand it returns a BJHandValue object.
 * Note: all methods except evaluate should have private visibility,
 * but this has been relaxed for unit testing purposes.
 */
public class BJHandEvaluator {
	/**
	 * evaluate takes a blackjack hand and evaluates it.
	 * @param hand blackjack hand.
	 * @return a BJValue object.
	 */
	public static BJHandValue evaluate(Hand hand) {
		BJHandValue result = null;
		final List<Card> cards = hand.getHand();
		// Does the hand contain an ACE?
		if (cards.stream().anyMatch(c -> aceDetector(c))) {
			// evaluation is complex
			result = evaluateHandWithAces(hand);
		} else {
			// evaluation is straightforward.
			int total = cards.stream().mapToInt(c -> cardEvaluator(c)).sum();
			result = new BJHandValue(HARD_HAND, totalToPrecis(total), 0, total, total); 
		}
		return result;	
	}
	/**
	 * aceDetector returns true if card is an ace.
	 * @param c Card object.
	 * @return true if c is an ACE.
	 */
	/*private*/ static boolean aceDetector(Card c) {
		if (c.getValue() == Rank.ACE.getValue()) {
			return true;
		}
		return false;
	}
	/**
	 * cardEvaluator processes card of rank TWO through KING and returns
	 * the card's blackjack value.  Basically, face cards evaluate to 10.
	 * @param c Card object.
	 * @return blackjack value of card.
	 */
	/*private*/ static int cardEvaluator(Card c) {
		// always processes ACEs as 14!!!
		int processedValue = c.getValue();
		if (processedValue > 10 && processedValue < Rank.ACE.getValue() ) {
			processedValue = 10;
		}
		return processedValue;	
	}
	/**
	 * totalToPrecis maps the total to a summary (precis) value.
	 * @param total total value of a hand.
	 * @return a succinct summary (precis).
	 */
	/*private*/ static BJHandValue.Precis totalToPrecis(int total) {
		if (total == 21) {
			return EXACTLY21_VALUE;
		} else if (total < 21) {
			return UNDER21_VALUE;
		} else {
			return OVER21_VALUE;
		}
	}
	/**
	 * evaluateHandWithAces evaluate a hand that has aces as follows:
	 * 1. make a copy of the hand and sort it.
	 * 2. make two lists: list of non-aces, list of aces.
	 * 3. process the list of non-aces and get a total for the cards.
	 * 4. use the count of the number of aces to determine GLB and LUB of total
	 * @param hand blackjack hand.
	 * @return BJHandValue object.
	 */
	/*private*/ static BJHandValue evaluateHandWithAces(Hand hand) {
		// this constructor makes a copy so that the original hand is undisturbed.
		List<Card> sortedCards = new ArrayList<Card>(hand.getHand());
		Collections.sort(sortedCards);
		// after sort all aces are at the right end of the list.
		List<Card> nonAces;
		List<Card> aces;
		nonAces = sortedCards.stream().filter(c -> !aceDetector(c))
				.collect(Collectors.toList());
		aces = sortedCards.stream().filter(c -> aceDetector(c))
				.collect(Collectors.toList());
		int nonAceTotal = nonAces.stream().mapToInt(c -> cardEvaluator(c)).sum();
		int acesSize = aces.size();
		return evaluateAceTotals(nonAceTotal, acesSize);
	}
	// pre-computed values for the Greatest Lower Bound (GLB) for total
	// and the Least Upper Bound (LUB) given the # of aces.
	private static int [][] acesTotalLowMinLowMax = 
		{ {1, 11}, // 1 ace
		  {2, 12}, // 2 aces
		  {3, 13}, // 3 aces
		  {4, 14}, // 4 aces
		};
	/**
	 * evaluateAceTotals is the tricky one! It must figure out whether
	 * the aces can be played low or high in the context of the non-ace total.
	 * @param nonAceTotal total of blackjack values of the non-ace cards.
	 * @param numOfAces number of aces in the hand
	 * @return a BJHandValue object.
	 */
	/*private*/ static BJHandValue evaluateAceTotals(int nonAceTotal, int numOfAces) {
		int lowMin = acesTotalLowMinLowMax[numOfAces-1][0];
		int lowMax = acesTotalLowMinLowMax[numOfAces-1][1];
		BJHandValue.Precis lowPrecis = totalToPrecis(nonAceTotal + lowMin);
		BJHandValue.Precis highPrecis = totalToPrecis(nonAceTotal + lowMax);
		BJHandValue.Firmness firmness = (nonAceTotal + lowMax > 21? HARD_HAND : SOFT_HAND);
		BJHandValue.Precis precis = OVER21_VALUE;
		if (lowPrecis == EXACTLY21_VALUE || highPrecis == EXACTLY21_VALUE) {
			precis = EXACTLY21_VALUE;
		} else if (lowPrecis == UNDER21_VALUE) {
			precis = UNDER21_VALUE;
		}
		return new BJHandValue(firmness, precis, numOfAces,
				nonAceTotal + lowMin, nonAceTotal + lowMax);
	}
}
