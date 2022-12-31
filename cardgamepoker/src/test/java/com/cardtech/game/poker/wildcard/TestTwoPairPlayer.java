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

class TestTwoPairPlayer {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	// common code shared by every test.
	void playTheseCards(List<Card> startCards, List<Card> expected, boolean expectedResult) {
		PokerHand startHand = new PokerHand(startCards);
		TwoPairPlayer player = new TwoPairPlayer(startHand);
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
	@DisplayName("Naturals: 2:C,8:D,9:H,A:H Jokers: J1")
	void testLogic1() {
		List<Card> startCards = Arrays.asList(
				new Card(Suit.CLUB,    2),
				new Card(Suit.DIAMOND, 8),
				new Card(Suit.HEART,   9),
				new Card(Suit.HEART,   14),
				new Card(null, Integer.MAX_VALUE));  // Joker 1
		playTheseCards(startCards, null, false);
	}

}
