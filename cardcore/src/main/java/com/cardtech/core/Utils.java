package com.cardtech.core;

import static com.cardtech.core.Rank.ACE;
import static com.cardtech.core.Rank.TWO;

import java.util.ArrayList;
import java.util.List;

/**
 * Utilities for cardcore classes. Mainly methods to construct
 * various kinds of decks.
 */
public class Utils {

	/**
	 * Create 52 card deck by suit.
	 * @return a 52 card deck ordered by suit.
	 */
	public static Deck createDeckSuitOrder() {
		return createDecksSuitOrder(1);
	}
	/**
	 * Create 1 or more decks ordered by suit.
	 * @param count number of decks to create.
	 * @return Deck object.
	 */
	private static Deck createDecksSuitOrder(int count) {
		List<Card> cards = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			createSuit(Suit.CLUB, cards);
			createSuit(Suit.DIAMOND, cards);
			createSuit(Suit.HEART, cards);
			createSuit(Suit.SPADE, cards);
		}
		return new Deck(cards);
	}

	/**
	 * Create multiple 52 card deck by suit.
	 * 
	 * @param count number of decks to create.
	 * @return the created Deck.
	 */
	public static Deck createMultipleDecksSuitOrder(int count) {
		if (count < 1 || count > 10) {
			throw new IllegalArgumentException("Deck count must be between 1 and 10. You want " + count + ".");
		}
		return createDecksSuitOrder(count);
	}

	
	/**
	 * Create 14 cards of the given suit.
	 * 
	 * @param s suit to create.
	 * @param cards running list of cards.
	 */
	private static void createSuit(Suit s, List<Card> cards) {
		for (int i = TWO.getValue(); i <= ACE.getValue(); i++) {
			cards.add(new Card(s, i));
		}
	}

	/**
	 * Create a new deck in rank order. Useful for specific test cases.
	 * @return deck in rank order.
	 */
	public static Deck createDeckRankOrder() {
		List<Card> cards = new ArrayList<>();
		for (int i = TWO.getValue(); i <= ACE.getValue(); i++) {
			cards.add(new Card(Suit.CLUB, i));
			cards.add(new Card(Suit.DIAMOND, i));
			cards.add(new Card(Suit.HEART, i));
			cards.add(new Card(Suit.SPADE, i));
		}
		return new Deck(cards);
	}

}
