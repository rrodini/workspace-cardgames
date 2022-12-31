package com.cardtech.game.poker.wildcard;

import com.cardtech.game.poker.PokerHand;
import com.cardtech.core.Card;
import com.cardtech.core.Suit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

/**
 * The visitor pattern is the model for WildcardPlayer (abstract) and its
 * concrete subclasses (e.g. StraightFlushPlayer, FourOfAKindPlayer, etc.).
 * Instead of traversing a data structure, the data structure PokerHand is given
 * to the constructor where it is examined by the concrete players.  The players
 * attempt to complete the "naturals" (i.e. non-wildcards) by substituting
 * concrete cards for the jokers.  The order of the Players is from highest
 * rank to lowest rank, just as it is for the PokerRanker class.
 * 
 * Notes:
 * - Code assumes that there are only two jokers, and no other cards are wild.
 * - Shared code is declared in WildcardPlayer and visible to subclasses.
 * - New cards to represent the jokers are created ("newed") when needed.
 * - When the sort method is called, it is assumed to be a "stable" sort
 *   This means that the suits don't change the sort order.
 * - Jokers are currently given Suit == null, value = Integer.MAX_VALUE
 *
 */
public abstract class WildcardPlayer {

	// All hands have 5 cards
	// Naturals are 0..natCount-1, Wildcards are natCount..5
	protected PokerHand startHand;
	// "naturals" are the real (non-joker) cards
	protected List<Card> naturals;
	protected int natCount = 0;
	// There may be 1 or 2 wildcards
	protected int wildCount;
	// Convenient to have names for card values.
	public final int TWO_VALUE   =  2;
	public final int THREE_VALUE =  3;
	public final int FOUR_VALUE  =  4;
	public final int FIVE_VALUE  =  5;
	public final int SIX_VALUE   =  6;
	public final int SEVEN_VALUE =  7;
	public final int EIGHT_VALUE =  8;
	public final int NINE_VALUE  =  9;
	public final int TEN_VALUE   = 10;
	public final int JACK_VALUE  = 11;
	public final int QUEEN_VALUE = 12;
	public final int KING_VALUE  = 13;
	public final int ACE_VALUE   = 14;
	/**
	 * Constructor does some basic analysis of the hand containing
	 * wildcards.
	 * @param hand Poker hand with 1 or 2 jokers.
	 */
	protected WildcardPlayer(final PokerHand hand) {
		startHand = new PokerHand(hand.getHand());
		wildCount = startHand.countWildcards();
		natCount = PokerHand.POKERHAND_COUNT - wildCount;
		startHand.sort();
		naturals = startHand.getHand();
		// remove the wildcards from startHand.
		// Note: wildcards sort to the end
		naturals.remove(4);
		if (wildCount == 2) {
			naturals.remove(3);
		}
	}
	// Constructor mandated
	protected WildcardPlayer() {
	}
	/**
	 * getBestHand() should only be called when the jokers have
	 * been substituted and one of the concrete players has returned
	 * "true."
	 * @return new PokerHand object with no jokers.
	 */
	public PokerHand getBestHand() {
		PokerHand endHand;
		endHand = new PokerHand(naturals);
		endHand.sort();
		return endHand;
	}
	// Must call play before calling getBestHand
	/**
	 * play() should be called in an attempt to play the wildcards
	 * into this class's name (e.g. FourOfAKind).
	 * @return true => was able to achieve hand.
	 */
	public abstract boolean play();
	/**
	 * maxGap() determines the largest "gap" between the values of the
	 * cards in the list of cards. It is used to assess the possibility
	 * of a straight.
	 * @param list of Card objects
	 * @return largest gap in possible straight.
	 */
	protected int maxGap(final List<Card> list) {
		OptionalInt optInt = IntStream.range(0, list.size()-1)
				.map(i -> list.get(i+1).getValue() - list.get(i).getValue())
				.max();
		// Note: if difference is 1 then gap is 0.
		return optInt.getAsInt() - 1;	
	}
	/**
	 * isFlush() determines if all the cards in the list have the same suit.
	 * @param list of Card objects.
	 * @return true => all cards do have the same suit.
	 */
	protected boolean isFlush(final List<Card> list) {
		Suit suitToMatch = list.get(0).getSuit();
		return list.stream().allMatch(c -> c.getSuit() == suitToMatch);
	}
	/**
	 * distinctValueCount() determines how "diverse" are the card pairings.
	 * For example, if distinctValueCount() returns 1 then all of the cards have
	 * the same value.
	 * @param list of Card objects.
	 * @return count of the # of distinct pairings.
	 */
	protected int distinctValueCount(final List<Card> list) {
		long count;
		count = list.stream().mapToInt(c -> c.getValue()).distinct().count();
		return (int) count;
	}
	/**
	 * distinctSuitCount() determines how "diverse" are the card suits.
	 * For example, if distinctSuitCount() returns 1 then all of the cards have
	 * the same suit.
	 * @param list of Card objects.
	 * @return count of the # of distinct suits.
	 */
	protected int distinctSuitCount(final List<Card> list) {
		long count;
		count = list.stream().mapToInt(c -> c.getSuit().ordinal()).distinct().count();
		return (int) count;
	}
	/**
	 * findCardValueSame() returns the longest sublist of the given list
	 * where all of the cards have the same value.
	 * Notes:
	 * - Assumes only 1 or 2 distinct card values in the list.
	 * @param list of Card objects
	 * @return sublist of longest match
	 */
	protected  List<Card> findCardValueSame(List<Card> list) {
		int i = 0;
		int j = 0;
		int size = list.size();
		if (list.get(0).getValue() == list.get(size-1).getValue()) {
			i = 0;
			j = size;
		} else {
			for (i = 0; i < size - 1; i++) {
				if (list.get(i).getValue() != list.get(i+1).getValue()) {
					break;
				}
			}
			j = i + 1;
			// > test below forces sublist to higher value cards.
			if (j > size - j) {
				i = 0;
			} else {
				i = j;
				j = size;
			}
		}
		return list.subList(i, j);
	}
	/**
	 * findMissingSuits() returns a list of the suits that are NOT
	 * found in the list of cards.
	 * @param list of Card objects.
	 * @return list of Suit objects that are missing.  May be empty.
	 */
	protected List<Suit> findMissingSuits(List<Card> list) {
		List<Suit> all = Arrays.asList(Suit.values());
		if (list == null) {
			return all;
		}
		List<Suit> present = new ArrayList<>();
		List<Suit> missing = new ArrayList<>();
		list.stream().map(c -> c.getSuit()).forEach(s -> present.add(s));
		all.stream().filter(s -> !present.contains(s)).forEach(s -> missing.add(s));
		return missing;
	}
	/**
	 * playWildcard() is the method called whenever a Joker is played.
	 * Note:
	 * - side-effect of modifying the list.
	 * - side-effect of decrementing wildCount variable.
	 * @param list of Card objects to be added to.
	 * @param Card object to add to list.
	 */
	protected void playWildcard(final List<Card> list, Card card) {
		list.add(card);
		wildCount--;
	}
}
