package com.cardtech.core;

import static com.cardtech.core.Rank.ACE;
import static com.cardtech.core.Rank.TWO;
import static com.cardtech.core.RemoveACard.TOP_CARD;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UtilsTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testCreateDeckSuitOrder() {
		Deck deck = Utils.createDeckSuitOrder();
		System.out.println("testCreateDeckSuitOrder()");
		assertEquals(Deck.STANDARD_DECK_SIZE, deck.getSize());
		for (Suit s : Suit.values()) {
			for (int i = TWO.getValue(); i <= ACE.getValue(); i++) {
				Card c = deck.removeCard(TOP_CARD);
//				String cardString = i + ":" + s.toString();
				assertEquals(cardString(i, s), c.toString());
			}
		}
	}
	
	private String cardString(int i, Suit s) {
		StringBuffer buf = new StringBuffer();
		switch (i) {
	case 11:
			buf.append("J:");
			break;
	case 12:
			buf.append("Q:");
			break;
	case 13:
			buf.append("K:");
			break;
	case 14:
			buf.append("A:");
			break;
	default:
			// all non-face cards here
			buf.append(i + ":");
		}
		buf.append(s.toString());
		return buf.toString();
	}
	
	@Test
	void testCreateMultipleDecksSuitOrder() {
		int count = 3;
		Deck deck = Utils.createMultipleDecksSuitOrder(count);
		System.out.println("testCreateMultipleDecksSuitOrder");
		assertEquals(Deck.STANDARD_DECK_SIZE * count, deck.getSize());
		for (int i = 0; i < count; i++) {
			for (Suit s : Suit.values()) {
				for (int j = TWO.getValue(); j <= ACE.getValue(); j++) {
					Card c = deck.removeCard(TOP_CARD);
					assertEquals(cardString(j, s), c.toString());
				}
			} 
		}
	}
	@Test
	void testCreateMultipleDecksException() {
		// exception here
		assertThrows((IllegalArgumentException.class), () -> {
			@SuppressWarnings("unused")
			Deck deck = Utils.createMultipleDecksSuitOrder(0);
		});
		assertThrows((IllegalArgumentException.class), () -> {
			@SuppressWarnings("unused")
			Deck deck = Utils.createMultipleDecksSuitOrder(11);
		});
	}

	@Test
	void testCreateDeckRankOrder() {
		Deck deck = Utils.createDeckRankOrder();
		System.out.println("testCreateDeckRankOrder()");
		assertEquals(Deck.STANDARD_DECK_SIZE, deck.getSize());
		for (int i = TWO.getValue(); i <= ACE.getValue(); i++) {
			for (Suit s : Suit.values()) {
				Card c = deck.removeCard(TOP_CARD);
				assertEquals(cardString(i, s), c.toString());
			}
		}
	}
	
}
