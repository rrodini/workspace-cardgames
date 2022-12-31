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

class TestStraightFlushPlayer {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	// common code shared by every test.
	void playTheseCards(List<Card> startCards, List<Card> expected, boolean expectedResult) {
		PokerHand startHand = new PokerHand(startCards);
		StraightFlushPlayer player = new StraightFlushPlayer(startHand);
		boolean result = player.play();
		if (expectedResult) {
			// Should only call for best hand if it is expected.
			PokerHand bestHand = player.getBestHand();
			System.out.printf("start hand: %s best hand: %s%n", startHand, bestHand);
			assertEquals(expected, bestHand.getHand());
		}
	}
	
	@Test
	@DisplayName("Naturals: 2:C,3:C,4:C  Jokers: J1,J2  Gap: 0")
	void testLogic01() {
		List<Card> startCards = Arrays.asList(
				new Card(Suit.CLUB, 4),
				new Card(Suit.CLUB, 3),
				new Card(Suit.CLUB, 2),
				new Card(null, Integer.MAX_VALUE), // Joker 1
				new Card(null, Integer.MAX_VALUE - 1)); // Joker 2
		List<Card> expected = Arrays.asList(
				new Card(Suit.CLUB, 2),
				new Card(Suit.CLUB, 3),
				new Card(Suit.CLUB, 4),
				new Card(Suit.CLUB, 5),
				new Card(Suit.CLUB, 6));
		playTheseCards(startCards, expected, true);
	}
	@Test
	@DisplayName("Naturals: 2:C,3:C,4:C,5:C Jokers: J1  Gap: 0")
	void testLogic02() {
		List<Card> startCards = Arrays.asList(
				new Card(Suit.CLUB, 4),
				new Card(Suit.CLUB, 3),
				new Card(Suit.CLUB, 2),
				new Card(Suit.CLUB, 5),
				new Card(null, Integer.MAX_VALUE - 1)); // Joker 2
		List<Card> expected = Arrays.asList(
				new Card(Suit.CLUB, 2),
				new Card(Suit.CLUB, 3),
				new Card(Suit.CLUB, 4),
				new Card(Suit.CLUB, 5),
				new Card(Suit.CLUB, 6));
		playTheseCards(startCards, expected, true);
	}
	@Test
	@DisplayName("Naturals: J:D,Q:D,K:D  Jokers: J1,J2  Gap: 0")
	void testLogic03() {
		List<Card> startCards = Arrays.asList(
				new Card(Suit.DIAMOND, 11),
				new Card(Suit.DIAMOND, 12),
				new Card(Suit.DIAMOND, 13),
				new Card(null, Integer.MAX_VALUE), // Joker 1
				new Card(null, Integer.MAX_VALUE - 1)); // Joker 2
		List<Card> expected = Arrays.asList(
				new Card(Suit.DIAMOND, 10),
				new Card(Suit.DIAMOND, 11),
				new Card(Suit.DIAMOND, 12),
				new Card(Suit.DIAMOND, 13),
				new Card(Suit.DIAMOND, 14));
		playTheseCards(startCards, expected, true);
	}
	@Test
	@DisplayName("Naturals: J:D,Q:D,K:D,A:D  Jokers: J2  Gap: 0")
	void testLogic04() {
		List<Card> startCards = Arrays.asList(
				new Card(Suit.DIAMOND, 11),
				new Card(Suit.DIAMOND, 12),
				new Card(Suit.DIAMOND, 13),
				new Card(Suit.DIAMOND, 14), // Joker 1
				new Card(null, Integer.MAX_VALUE - 1)); // Joker 2
		List<Card> expected = Arrays.asList(
				new Card(Suit.DIAMOND, 10),
				new Card(Suit.DIAMOND, 11),
				new Card(Suit.DIAMOND, 12),
				new Card(Suit.DIAMOND, 13),
				new Card(Suit.DIAMOND, 14));
		playTheseCards(startCards, expected, true);
	}
	@Test
	@DisplayName("Naturals: 3:D,4:D,6:D  Jokers: J1, J2  Gap: 1")
	void testLogic05() {
		List<Card> startCards = Arrays.asList(
				new Card(Suit.DIAMOND,  4),
				new Card(Suit.DIAMOND,  6),
				new Card(Suit.DIAMOND,  3),
				new Card(null, Integer.MAX_VALUE ), 	// Joker 1
				new Card(null, Integer.MAX_VALUE - 1)); // Joker 2
		List<Card> expected = Arrays.asList(
				new Card(Suit.DIAMOND, 3),
				new Card(Suit.DIAMOND, 4),
				new Card(Suit.DIAMOND, 5),
				new Card(Suit.DIAMOND, 6),
				new Card(Suit.DIAMOND, 7));
		playTheseCards(startCards, expected, true);
	}
	@Test
	@DisplayName("Naturals: 3:D,4:D,6:D,7:D  Jokers: J2  Gap: 1")
	void testLogic06() {
		List<Card> startCards = Arrays.asList(
				new Card(Suit.DIAMOND,  4),
				new Card(Suit.DIAMOND,  6),
				new Card(Suit.DIAMOND,  3),
				new Card(Suit.DIAMOND,  7),
				new Card(null, Integer.MAX_VALUE - 1)); // Joker 2
		List<Card> expected = Arrays.asList(
				new Card(Suit.DIAMOND, 3),
				new Card(Suit.DIAMOND, 4),
				new Card(Suit.DIAMOND, 5),
				new Card(Suit.DIAMOND, 6),
				new Card(Suit.DIAMOND, 7));
		playTheseCards(startCards, expected, true);
	}
	@Test
	@DisplayName("Naturals: 3:D,4:D,7:D  Jokers: J1,J2  Gap: 2")
	void testLogic07() {
		List<Card> startCards = Arrays.asList(
				new Card(Suit.DIAMOND,  4),
				new Card(Suit.DIAMOND,  7),
				new Card(Suit.DIAMOND,  3),
				new Card(null, Integer.MAX_VALUE ), 	// Joker 1
				new Card(null, Integer.MAX_VALUE - 1)); // Joker 2
		List<Card> expected = Arrays.asList(
				new Card(Suit.DIAMOND, 3),
				new Card(Suit.DIAMOND, 4),
				new Card(Suit.DIAMOND, 5),
				new Card(Suit.DIAMOND, 6),
				new Card(Suit.DIAMOND, 7));
		playTheseCards(startCards, expected, true);
	}
	
}
