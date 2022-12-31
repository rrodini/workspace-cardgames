package com.cardtech.game.poker.wildcard;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.cardtech.core.Card;
import com.cardtech.core.Suit;
import com.cardtech.game.poker.PokerHand;

class TestFlushPlayer {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	// common code shared by every test.
	void playTheseCards(List<Card> startCards, List<Card> expected, boolean expectedResult) {
		PokerHand startHand = new PokerHand(startCards);
		FlushPlayer player = new FlushPlayer(startHand);
		boolean result = player.play();
		assertEquals(expectedResult, result);
		if (expectedResult) {
			// Should only call for best hand if it is expected.
			PokerHand bestHand = player.getBestHand();
			System.out.printf("start hand: %s best hand: %s%n", startHand, bestHand);
			assertEquals(expected, bestHand.getHand());
		}
	}

	@Test
	@DisplayName("Naturals: 2:C,K:C,A:C Jokers: J1,J2")
	void testLogic1() {
		List<Card> startCards = Arrays.asList(
				new Card(Suit.CLUB,    2),
				new Card(Suit.CLUB,   13),
				new Card(Suit.CLUB,   14),
				new Card(null, Integer.MAX_VALUE),    // Joker 1
				new Card(null, Integer.MAX_VALUE-1)); // Joker 2
		List<Card> expected = Arrays.asList(
				new Card(Suit.CLUB,    2),
				new Card(Suit.CLUB,   11),
				new Card(Suit.CLUB,   12),
				new Card(Suit.CLUB,   13),
				new Card(Suit.CLUB,   14));
		playTheseCards(startCards, expected, true);
	}
	@Test
	@DisplayName("Naturals: 2:C,J:C,A:C Jokers: J1,J2")
	void testLogic2() {
		List<Card> startCards = Arrays.asList(
				new Card(Suit.CLUB,    2),
				new Card(Suit.CLUB,   11),
				new Card(Suit.CLUB,   14),
				new Card(null, Integer.MAX_VALUE),    // Joker 1
				new Card(null, Integer.MAX_VALUE-1)); // Joker 2
		List<Card> expected = Arrays.asList(
				new Card(Suit.CLUB,    2),
				new Card(Suit.CLUB,   11),
				new Card(Suit.CLUB,   12),
				new Card(Suit.CLUB,   13),
				new Card(Suit.CLUB,   14));
		playTheseCards(startCards, expected, true);
	}
	@Test
	@DisplayName("Naturals: 2:S,5:S,7:S,K:S Jokers: J1")
	void testLogic3() {
		List<Card> startCards = Arrays.asList(
				new Card(Suit.SPADE,    2),
				new Card(Suit.SPADE,    5),
				new Card(Suit.SPADE,    7),
				new Card(Suit.SPADE,   13),
				new Card(null, Integer.MAX_VALUE)); // Joker 1
		List<Card> expected = Arrays.asList(
				new Card(Suit.SPADE,    2),
				new Card(Suit.SPADE,    5),
				new Card(Suit.SPADE,    7),
				new Card(Suit.SPADE,   13),
				new Card(Suit.SPADE,   14));
		playTheseCards(startCards, expected, true);
	}
	@Test
	@DisplayName("Naturals: 3:D,6:D,9D,A:D Jokers: J1")
	void testLogic4() {
		List<Card> startCards = Arrays.asList(
				new Card(Suit.DIAMOND,    3),
				new Card(Suit.DIAMOND,    6),
				new Card(Suit.DIAMOND,    9),
				new Card(Suit.DIAMOND,   14),
				new Card(null, Integer.MAX_VALUE)); // Joker 1
		List<Card> expected = Arrays.asList(
				new Card(Suit.DIAMOND,    3),
				new Card(Suit.DIAMOND,    6),
				new Card(Suit.DIAMOND,    9),
				new Card(Suit.DIAMOND,   13),
				new Card(Suit.DIAMOND,   14));
		playTheseCards(startCards, expected, true);
	}
	@Test
	@DisplayName("Naturals: 3:D,6:H,9D,A:D Jokers: J1")
	void testLogic5() {
		List<Card> startCards = Arrays.asList(
				new Card(Suit.DIAMOND,    3),
				new Card(Suit.HEART,      6),
				new Card(Suit.DIAMOND,    9),
				new Card(Suit.DIAMOND,   14),
				new Card(null, Integer.MAX_VALUE)); // Joker 1
		playTheseCards(startCards, null, false);
	}
}
