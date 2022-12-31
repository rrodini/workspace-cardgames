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

class TestStraightPlayer {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	// common code shared by every test.
	void playTheseCards(List<Card> startCards, List<Card> expected, boolean expectedResult) {
		PokerHand startHand = new PokerHand(startCards);
		StraightPlayer player = new StraightPlayer(startHand);
		boolean result = player.play();
		if (expectedResult) {
			// Should only call for best hand if it is expected.
			PokerHand bestHand = player.getBestHand();
			System.out.printf("start hand: %s best hand: %s%n", startHand, bestHand);
			assertEquals(expected, bestHand.getHand());
		}
	}
	
	@Test
	@DisplayName("Naturals: 2:C,3:H,4:S  Jokers: J1,J2  Gap: 0")
	void testLogic01() {
		List<Card> startCards = Arrays.asList(
				new Card(Suit.CLUB, 4),
				new Card(Suit.HEART, 3),
				new Card(Suit.SPADE, 2),
				new Card(null, Integer.MAX_VALUE), // Joker 1
				new Card(null, Integer.MAX_VALUE - 1)); // Joker 2
		List<Card> expected = Arrays.asList(
				new Card(Suit.SPADE, 2),
				new Card(Suit.HEART, 3),
				new Card(Suit.CLUB,  4),
				new Card(Suit.SPADE, 5),
				new Card(Suit.SPADE, 6));
		playTheseCards(startCards, expected, true);
	}
	@Test
	@DisplayName("Naturals: 2:H,3:C,4:C,5:C Jokers: J1  Gap: 0")
	void testLogic02() {
		List<Card> startCards = Arrays.asList(
				new Card(Suit.CLUB, 4),
				new Card(Suit.CLUB, 3),
				new Card(Suit.HEART,2),
				new Card(Suit.CLUB, 5),
				new Card(null, Integer.MAX_VALUE - 1)); // Joker 2
		List<Card> expected = Arrays.asList(
				new Card(Suit.HEART,2),
				new Card(Suit.CLUB, 3),
				new Card(Suit.CLUB, 4),
				new Card(Suit.CLUB, 5),
				new Card(Suit.HEART,6));
		playTheseCards(startCards, expected, true);
	}
	@Test
	@DisplayName("Naturals: J:S,Q:D,K:H  Jokers: J1,J2  Gap: 0")
	void testLogic03() {
		List<Card> startCards = Arrays.asList(
				new Card(Suit.SPADE, 11),
				new Card(Suit.DIAMOND, 12),
				new Card(Suit.HEART, 13),
				new Card(null, Integer.MAX_VALUE), // Joker 1
				new Card(null, Integer.MAX_VALUE - 1)); // Joker 2
		List<Card> expected = Arrays.asList(
				new Card(Suit.SPADE, 10),
				new Card(Suit.SPADE, 11),
				new Card(Suit.DIAMOND, 12),
				new Card(Suit.HEART, 13),
				new Card(Suit.SPADE, 14));
		playTheseCards(startCards, expected, true);
	}
	@Test
	@DisplayName("Naturals: J:C,Q:D,K:D,A:D  Jokers: J2  Gap: 0")
	void testLogic04() {
		List<Card> startCards = Arrays.asList(
				new Card(Suit.CLUB, 11),
				new Card(Suit.DIAMOND, 12),
				new Card(Suit.DIAMOND, 13),
				new Card(Suit.DIAMOND, 14), // Joker 1
				new Card(null, Integer.MAX_VALUE - 1)); // Joker 2
		List<Card> expected = Arrays.asList(
				new Card(Suit.CLUB, 10),
				new Card(Suit.CLUB, 11),
				new Card(Suit.DIAMOND, 12),
				new Card(Suit.DIAMOND, 13),
				new Card(Suit.DIAMOND, 14));
		playTheseCards(startCards, expected, true);
	}
	@Test
	@DisplayName("Naturals: 3:D,4:H,6:S  Jokers: J1, J2  Gap: 1")
	void testLogic05() {
		List<Card> startCards = Arrays.asList(
				new Card(Suit.DIAMOND,4),
				new Card(Suit.HEART,  6),
				new Card(Suit.SPADE,  3),
				new Card(null, Integer.MAX_VALUE ), 	// Joker 1
				new Card(null, Integer.MAX_VALUE - 1)); // Joker 2
		List<Card> expected = Arrays.asList(
				new Card(Suit.SPADE, 3),
				new Card(Suit.DIAMOND,   4),
				new Card(Suit.SPADE,   5),
				new Card(Suit.HEART, 6),
				new Card(Suit.SPADE, 7));
		playTheseCards(startCards, expected, true);
	}
	@Test
	@DisplayName("Naturals: 3:D,4:D,6:D,7:C  Jokers: J2  Gap: 1")
	void testLogic06() {
		List<Card> startCards = Arrays.asList(
				new Card(Suit.DIAMOND,  4),
				new Card(Suit.DIAMOND,  6),
				new Card(Suit.DIAMOND,  3),
				new Card(Suit.CLUB,     7),
				new Card(null, Integer.MAX_VALUE - 1)); // Joker 2
		List<Card> expected = Arrays.asList(
				new Card(Suit.DIAMOND, 3),
				new Card(Suit.DIAMOND, 4),
				new Card(Suit.DIAMOND, 5),
				new Card(Suit.DIAMOND, 6),
				new Card(Suit.CLUB,    7));
		playTheseCards(startCards, expected, true);
	}
	@Test
	@DisplayName("Naturals: 3:D,4:H,7:C  Jokers: J1,J2  Gap: 2")
	void testLogic07() {
		List<Card> startCards = Arrays.asList(
				new Card(Suit.HEART,  4),
				new Card(Suit.CLUB,  7),
				new Card(Suit.DIAMOND,  3),
				new Card(null, Integer.MAX_VALUE ), 	// Joker 1
				new Card(null, Integer.MAX_VALUE - 1)); // Joker 2
		List<Card> expected = Arrays.asList(
				new Card(Suit.DIAMOND, 3),
				new Card(Suit.HEART, 4),
				new Card(Suit.DIAMOND, 5),
				new Card(Suit.DIAMOND, 6),
				new Card(Suit.CLUB, 7));
		playTheseCards(startCards, expected, true);
	}
	
}
