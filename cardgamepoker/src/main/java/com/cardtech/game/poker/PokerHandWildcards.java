package com.cardtech.game.poker;
// TO BE DELETED.
import java.util.ArrayList;
import java.util.List;

import com.cardtech.core.Card;
import com.cardtech.game.Hand;
import com.cardtech.game.poker.wildcard.PlayWildcards;

/**
 * PokerHandWildcards determines how to play the jokers in the hand.
 * Notes:
 * 1) should only be called if one or two of the hands contains jokers
 * 2) jokers are considered wild and can be played as any card.
 *
 */
public class PokerHandWildcards {
	// All hands have 5 cards
	static PokerHand wildcardHand;
	// Naturals are 0..natCount-1, Wildcards are natCount..5
	static PokerHand startHand;
	
	PokerHandWildcards(PokerHand hand) {
		if (!hand.containsWildcards()) {
			throw new IllegalArgumentException("Poker hand does not contain wildcards.");
		}
		// Make a shallow copies of hands
		startHand = hand;
		hand.sort();
		wildcardHand = new PokerHand(hand.getHand());
		wildcardHand.sort();
	}
	/**
	 * This method is the tricky one as it determines how to play 
	 * the jokers within the hand.
	 * Logic follows that of PokerRanker in order to find the 
	 * highest rank first (first may not be efficient though).
	 */
	/* private */ 
	static void playWildcards() {
//		PlayWildcards pwc = new PlayWildcards(startHand);
//		if (PlayStraightFlush.play()) {
//		} else if (PlayFourOfAKind.play()) {
//		} else if (playFullHouse()) {
//		} else if (playFlush()) {
//		} else if (playStraight()) {
//		} else if (playThreeOfAKind()) {
//		} else if (playTwoPair()) {
//		} else if (playOnePair()) {		
//		}
//		if (null == rank) {
//			throw new IllegalStateException("Can't play wildcards " + startHand.toString());
//		}
	}
	
	// this is a helper method
	private static boolean isValueEqual(int i, int j, PokerHand sortedHand) {
		if (i == j) {
			throw new IllegalArgumentException("Can't have i==j");
		}
		// do two cards within the hand have the same value?
		return sortedHand.getCard(i).getValue() == sortedHand.getCard(j).getValue();
	}
	// this is a helper method
	private static boolean isSuitEqual(int i, int j, PokerHand sortedHand) {
		if (i == j) {
			throw new IllegalArgumentException("Can't have i==j");
		}
		// do two cards within the hand have the same suit?
		return sortedHand.getCard(i).getSuit() == sortedHand.getCard(j).getSuit();
	}

	
	
	/* private */
	static boolean playStraightFlush() {
		boolean isStraightFlush = false;
		return isStraightFlush;
	}
	/* private */
	static boolean playFourOfAKind() {
		return false;
	}
	/* private */
	static boolean playFullHouse() {
		return false;
	}
	/* private */
	static boolean playFlush() {
		return false;
	}
	/* private */
	static boolean playStraight() {
		return false;
	}
	/* private */
	static boolean playThreeOfAKind() {
		return false;
	}
	/* private */
	static boolean playTwoPair() {
		return false;
	}
	/* private */
	static boolean playOnePair() {
		return false;
	}
	public static PokerHand getWildcardHand() {
		return (PokerHand) wildcardHand;
	}
}
