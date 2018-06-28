package com.cardtech.core;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * NOT USED.  Abandoned plan to use Guice within project.
 *
 */
class DeckTestInjection {

	private List<Card> cards;

	@BeforeEach
	void setUp() throws Exception {
		cards = new ArrayList<>();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testDeckWith104Cards() {
		createSuit(Suit.CLUB);
		createSuit(Suit.DIAMOND);
		createSuit(Suit.HEART);
		createSuit(Suit.SPADE);
		createSuit(Suit.CLUB);
		createSuit(Suit.DIAMOND);
		createSuit(Suit.HEART);
		createSuit(Suit.SPADE);
		Deck deck = new Deck(cards);
		assertEquals(104, deck.getSize() );
	}

	private void createSuit(Suit s) {
		for (int i = 2; i <= 14; i++) {
			cards.add(new Card(s, i));
		}
	}

	
}
