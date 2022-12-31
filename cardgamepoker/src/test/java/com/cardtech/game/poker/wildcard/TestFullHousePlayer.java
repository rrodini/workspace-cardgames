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

class TestFullHousePlayer {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	// common code shared by every test.
	void playTheseCards(List<Card> startCards, List<Card> expected, boolean expectedResult) {
		PokerHand startHand = new PokerHand(startCards);
		FullHousePlayer player = new FullHousePlayer(startHand);
		boolean result = player.play();
		assertEquals(expectedResult, result);
		if (expectedResult) {
			// Should only call for best hand if it is expected.
			PokerHand bestHand = player.getBestHand();
			System.out.printf("start hand: %s best hand: %s%n", startHand, bestHand);
			assertEquals(expected, bestHand.getHand());
		}
	}
	// Note that a hand with two Jokers will be played
	// as four of a kind.
	@Test
	@DisplayName("Naturals: 8:H,8:D,10:D,10:H Jokers: J1")
	void testLogic1() {
		List<Card> startCards = Arrays.asList(
				new Card(Suit.HEART,     8),
				new Card(Suit.DIAMOND,   8),
				new Card(Suit.DIAMOND,  10),
				new Card(Suit.HEART  ,  10),
				new Card(null, Integer.MAX_VALUE)); // Joker 1
		List<Card> expected = Arrays.asList(
				new Card(Suit.HEART,     8),
				new Card(Suit.DIAMOND,   8),
				new Card(Suit.DIAMOND,  10),
				new Card(Suit.HEART  ,  10),
				new Card(Suit.CLUB  ,  10));
		playTheseCards(startCards, expected, true);
	}
	@Test
	@DisplayName("Naturals: 2:C,2:S,14:D,14:H Jokers: J2")
	void testLogic2() {
		List<Card> startCards = Arrays.asList(
				new Card(Suit.CLUB,     2),
				new Card(Suit.SPADE,    2),
				new Card(Suit.DIAMOND, 14),
				new Card(Suit.HEART  , 14),
				new Card(null, Integer.MAX_VALUE - 1)); // Joker 2
		List<Card> expected = Arrays.asList(
				new Card(Suit.CLUB,     2),
				new Card(Suit.SPADE,    2),
				new Card(Suit.DIAMOND, 14),
				new Card(Suit.HEART  , 14),
				new Card(Suit.CLUB  ,  14));
		playTheseCards(startCards, expected, true);
	}
	@Test
	@DisplayName("Naturals: 2:C,3:S,14:D,14:H Jokers: J2")
	void testLogic3() {
		List<Card> startCards = Arrays.asList(
				new Card(Suit.CLUB,     2),
				new Card(Suit.SPADE,    3),
				new Card(Suit.DIAMOND, 14),
				new Card(Suit.HEART  , 14),
				new Card(null, Integer.MAX_VALUE - 1)); // Joker 2
		playTheseCards(startCards, null, false);
	}
}
