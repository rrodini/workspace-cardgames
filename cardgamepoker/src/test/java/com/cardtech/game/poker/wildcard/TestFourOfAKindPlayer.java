package com.cardtech.game.poker.wildcard;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.cardtech.core.Card;
import com.cardtech.core.Suit;
import com.cardtech.game.poker.PokerHand;

class TestFourOfAKindPlayer {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	// common code shared by every test.
	void playTheseCards(List<Card> startCards, List<Card> expected, boolean expectedResult) {
		PokerHand startHand = new PokerHand(startCards);
		FourOfAKindPlayer player = new FourOfAKindPlayer(startHand);
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
	@DisplayName("Naturals: 2:C,2:D,2:H,3:HEART Jokers: J1")
	void testLogic1() {
		List<Card> startCards = Arrays.asList(
				new Card(Suit.CLUB,    2),
				new Card(Suit.DIAMOND, 2),
				new Card(Suit.HEART,   2),
				new Card(Suit.HEART,   3),
				new Card(null, Integer.MAX_VALUE)); // Joker 1
		List<Card> expected = Arrays.asList(
				new Card(Suit.CLUB, 2),
				new Card(Suit.DIAMOND, 2),
				new Card(Suit.HEART, 2),
				new Card(Suit.SPADE, 2),
				new Card(Suit.HEART, 3));
		playTheseCards(startCards, expected, true);
	}
	@Test
	@DisplayName("Naturals: 3:C,4:D,4:H,4:C Jokers: J1")
	void testLogic2() {
		List<Card> startCards = Arrays.asList(
				new Card(Suit.CLUB,    3),
				new Card(Suit.DIAMOND, 4),
				new Card(Suit.HEART,   4),
				new Card(Suit.CLUB,    4),
				new Card(null, Integer.MAX_VALUE)); // Joker 1
		List<Card> expected = Arrays.asList(
				new Card(Suit.CLUB,    3),
				new Card(Suit.DIAMOND, 4),
				new Card(Suit.HEART,   4),
				new Card(Suit.CLUB,   4),
				new Card(Suit.SPADE,    4));
		playTheseCards(startCards, expected, true);
	}
	@Test
	@DisplayName("Naturals: 2:C,2:D,2:H Jokers: J1,J2")
	void testLogic3() {
		List<Card> startCards = Arrays.asList(
				new Card(Suit.CLUB,    2),
				new Card(Suit.DIAMOND, 2),
				new Card(Suit.HEART,   2),
				new Card(null, Integer.MAX_VALUE), // Joker 1
				new Card(null, Integer.MAX_VALUE-1)); // Joker 2
		List<Card> expected = Arrays.asList(
				new Card(Suit.CLUB,    2),
				new Card(Suit.DIAMOND, 2),
				new Card(Suit.HEART,   2),
				new Card(Suit.SPADE,   2),
				new Card(Suit.CLUB,    2));
		playTheseCards(startCards, expected, true);
	}
	@Test
	@DisplayName("Naturals: 4:C,4:D,4:H,4: Jokers: J1")
	void testLogic4() {
		List<Card> startCards = Arrays.asList(
				new Card(Suit.CLUB,    4),
				new Card(Suit.DIAMOND, 4),
				new Card(Suit.HEART,   4),
				new Card(Suit.SPADE,   4),
				new Card(null, Integer.MAX_VALUE)); // Joker 1
		List<Card> expected = Arrays.asList(
				new Card(Suit.CLUB,    4),
				new Card(Suit.DIAMOND, 4),
				new Card(Suit.HEART,   4),
				new Card(Suit.SPADE,   4),
				new Card(Suit.CLUB,    4));
		playTheseCards(startCards, expected, true);
	}
	@Test
	@DisplayName("Naturals: 7:C,8:H,8:D Jokers: J1,J2")
	void testLogic5() {
		List<Card> startCards = Arrays.asList(
				new Card(Suit.CLUB,      7),
				new Card(Suit.HEART,     8),
				new Card(Suit.DIAMOND,   8),
				new Card(null, Integer.MAX_VALUE), // Joker 1
				new Card(null, Integer.MAX_VALUE-1)); // Joker 2
		List<Card> expected = Arrays.asList(
				new Card(Suit.CLUB,      7),
				new Card(Suit.HEART,     8),
				new Card(Suit.DIAMOND,   8),
				new Card(Suit.CLUB,      8),
				new Card(Suit.SPADE,     8));
		playTheseCards(startCards, expected, true);
	}
	@Test
	@DisplayName("Naturals: 8:H,8:D,10:D Jokers: J1,J2")
	void testLogic6() {
		List<Card> startCards = Arrays.asList(
				new Card(Suit.HEART,     8),
				new Card(Suit.DIAMOND,   8),
				new Card(Suit.DIAMOND,  10),
				new Card(null, Integer.MAX_VALUE), // Joker 1
				new Card(null, Integer.MAX_VALUE-1)); // Joker 2
		List<Card> expected = Arrays.asList(
				new Card(Suit.HEART,     8),
				new Card(Suit.DIAMOND,   8),
				new Card(Suit.CLUB,      8),
				new Card(Suit.SPADE,     8),
				new Card(Suit.DIAMOND,  10));
		playTheseCards(startCards, expected, true);
	}
	@Test
	@DisplayName("Naturals: 8:H,8:D,10:D,10:H Jokers: J1")
	void testLogic7() {
		List<Card> startCards = Arrays.asList(
				new Card(Suit.HEART,     8),
				new Card(Suit.DIAMOND,   8),
				new Card(Suit.DIAMOND,  10),
				new Card(Suit.HEART  ,  10),
				new Card(null, Integer.MAX_VALUE)); // Joker 1
		playTheseCards(startCards, null, false);
	}
	@Test
	@DisplayName("Naturals: 8:H,9:D,10:D,10:H Jokers: J1")
	void testLogic8() {
		List<Card> startCards = Arrays.asList(
				new Card(Suit.HEART,     8),
				new Card(Suit.DIAMOND,   9),
				new Card(Suit.DIAMOND,  10),
				new Card(Suit.HEART  ,  10),
				new Card(null, Integer.MAX_VALUE)); // Joker 1
		playTheseCards(startCards, null, false);
	}
}
