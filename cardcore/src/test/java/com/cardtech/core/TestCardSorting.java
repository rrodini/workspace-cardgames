package com.cardtech.core;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cardtech.core.Card;
import com.cardtech.core.CardByRank;
import com.cardtech.core.Suit;
import com.cardtech.core.CardBySuit;
import com.cardtech.core.Deck;

class TestCardSorting {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
   /**
    * testTreeMapUsingComparable - shows that a TreeMap w/o a comparator
    * will use Card's Comparable to compare cards resulting in cards
    * with the same value being considered the same card
    * for the map's key.
    */
	@Test
	void testTreeMapUsingComparable() {
		System.out.println("testTreeMapUsingComparable()");
		TreeMap<Card, Integer> tm = new TreeMap<Card, Integer>();
		Card c1 = new Card(Suit.CLUB, 5);
		Card c2 = new Card(Suit.DIAMOND, 5);
		Card c3 = new Card(Suit.HEART, 6);
		tm.put(c1, 0);
		tm.put(c2, 1);
		tm.put(c3, 2);
		System.out.println("tm size: " + tm.size());
		Set<Card> keySet = tm.descendingKeySet();
		System.out.println("tm keys (descending order:");
		for (Card key: keySet) {
			System.out.println("  key:" + key.toString());
		}
		// expect 2 since 5:CLUB and 5:DIAMOND are treated as the same key.
		assertEquals(2, tm.size());
	}
   /**
    * testTreeMapUsingCoparator - shows that a TreeMap w/ a comparator
    * will use the comparator to compare cards resulting in cards
    * with the same value being considered different cards
    * for the map's key.
    */
	@Test
	void testTreeMapUsingComparator() {
		System.out.println("testTreeMapUsingComparator()");
		CardBySuit cardComp = new CardBySuit();
		TreeMap<Card, Integer> tm = new TreeMap<Card, Integer>(cardComp);
		Card c1 = new Card(Suit.CLUB, 2);
		Card c2 = new Card(Suit.HEART, 2);
		Card c3 = new Card(Suit.CLUB, 7);
		tm.put(c1, 0);
		tm.put(c2, 1);
		tm.put(c3, 2);
		System.out.println("tm size: " + tm.size());
		Set<Card> keySet = tm.descendingKeySet();
		System.out.println("tm keys (descending order:");
		for (Card key: keySet) {
			System.out.println("  key:" + key.toString());
		}
		// expect 3 since 5:CLUB and 5:DIAMOND are NOT treated as the same key.
		assertEquals(3, tm.size());
	}
   /**
    * testDeckSortUsingOrderBySuit - shows how the orderBySuit comparator
    * fully orders all 52 cards in the deck.
    * Note: no TreeMap here.	
    */
	@Test
	void testDeckSortUsingOrderBySuit() {
		System.out.println("DeckSortUsingComparator()");
		Deck deck = new Deck();
		deck.shuffle();
		List<Card> hand = deck.deal(52);
		CardBySuit orderBySuit = new CardBySuit();
		hand.sort(orderBySuit);
		int count = 0;
		for (Card card: hand) {
			System.out.printf("%s ", card.toString());
			count++;
			if (count == 4) {
				System.out.println();
				count = 0;
			}
		}
		// look at first 4 cards.
		// Note: due to Card.equals() the test is for value equality.
		assertEquals(new Card(Suit.CLUB, 2),    hand.get(0));
		assertEquals(new Card(Suit.DIAMOND, 2), hand.get(1));
		assertEquals(new Card(Suit.HEART, 2),   hand.get(2));
		assertEquals(new Card(Suit.SPADE, 2),   hand.get(3));
		// look at last 4 cards.
		assertEquals(new Card(Suit.CLUB, 14),    hand.get(48));
		assertEquals(new Card(Suit.DIAMOND, 14), hand.get(49));
		assertEquals(new Card(Suit.HEART, 14),   hand.get(50));
		assertEquals(new Card(Suit.SPADE, 14),   hand.get(51));
	}
   /**
    * testDeckSortUsingOrderbyRank - shows how the OrderbyRank comparator
    * fully orders all 52 cards in the deck.
    * Note: no TreeMap here.	
    */
	@Test
	void testDeckSortUsingOrderbyRank() {
		System.out.println("DeckSortUsingOrderbyRank()");
		Deck deck = new Deck();
		deck.shuffle();
		List<Card> hand = deck.deal(52);
		CardByRank orderByRank = new CardByRank();
		hand.sort(orderByRank);
		int count = 0;
		for (Card card: hand) {
			System.out.printf("%s ", card.toString());
			count++;
			if (count == 13) {
				System.out.println();
				count = 0;
			}
		}
		// look at first 4 cards.
		// Note: due to Card.equals() the test is for value equality.
		assertEquals(new Card(Suit.CLUB, 2), hand.get(0));
		assertEquals(new Card(Suit.CLUB, 3), hand.get(1));
		assertEquals(new Card(Suit.CLUB, 4), hand.get(2));
		assertEquals(new Card(Suit.CLUB, 5), hand.get(3));
		// look at last 4 cards.
		assertEquals(new Card(Suit.SPADE, 11), hand.get(48));
		assertEquals(new Card(Suit.SPADE, 12), hand.get(49));
		assertEquals(new Card(Suit.SPADE, 13), hand.get(50));
		assertEquals(new Card(Suit.SPADE, 14), hand.get(51));
	}
}
