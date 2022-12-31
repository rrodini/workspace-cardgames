package com.cardtech.game.poker.wildcard;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.cardtech.core.Card;
import com.cardtech.core.Suit;
import com.cardtech.game.poker.PokerHand;

class TestPlayWildcards {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	void playTheseCards(List<Card> startCards, List<Card> expected, boolean expectedResult) {
		PlayWildcards playCards = new PlayWildcards(new PokerHand(startCards));
		PokerHand bestHand = playCards.getBestHand();
		if (expectedResult) {
			assertEquals(expected, bestHand.getHand());			
		}
	}
/**
 * The logic of testing here is to take a test case
 * from each of the concrete wildcard player classes
 * and test that PlayWildcards plays the expected hand.
 */
	@Test
	@DisplayName("Royal flush: hand: 10:D,J:D,Q:D,K:D,A:D ")
	void testLogic1() {
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
	@DisplayName("Four of a kind: 2:C,2:D,2:H,2:S,3:H")
	void testLogic2() {
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
	@DisplayName("Full house: 2:C,2:S,14:D,14:H,14:C")
	void testLogic3() {
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
	@DisplayName("Flush: 2:C,J:C,Q:C,K:C,A:C,")
	void testLogic4() {
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
	@DisplayName("Straight: 2:C,3:H,4:S,5:S,6:S")
	void testLogic5() {
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
	@DisplayName("Three of a kind: 2:C,8:D,8:H,8:C,9:C")
	void testLogic6() {
		List<Card> startCards = Arrays.asList(
				new Card(Suit.CLUB,    2),
				new Card(Suit.DIAMOND, 8),
				new Card(Suit.HEART,   8),
				new Card(Suit.CLUB,    9),
				new Card(null, Integer.MAX_VALUE));  // Joker 1
		List<Card> expected = Arrays.asList(
				new Card(Suit.CLUB,    2),
				new Card(Suit.DIAMOND, 8),
				new Card(Suit.HEART,   8),
				new Card(Suit.CLUB,    8),
				new Card(Suit.CLUB,    9));
		playTheseCards(startCards, expected, true);
	}
	@Test
	@DisplayName("Two Pair: 2:C,8:D,9:H,A:H Jokers: J1")
	void testLogic7() {
		List<Card> startCards = Arrays.asList(
				new Card(Suit.CLUB,    2),
				new Card(Suit.DIAMOND, 8),
				new Card(Suit.HEART,   9),
				new Card(Suit.HEART,   14),
				new Card(null, Integer.MAX_VALUE));  // Joker 1
		playTheseCards(startCards, null, false);
	}
	@Test
	@DisplayName("One Pair: 2:C,8:D,9:H,A:H,A:C")
	void testLogic8() {
		List<Card> startCards = Arrays.asList(
				new Card(Suit.CLUB,    2),
				new Card(Suit.DIAMOND, 8),
				new Card(Suit.HEART,   9),
				new Card(Suit.HEART,   14),
				new Card(null, Integer.MAX_VALUE));  // Joker 1
		List<Card> expected = Arrays.asList(
				new Card(Suit.CLUB,    2),
				new Card(Suit.DIAMOND, 8),
				new Card(Suit.HEART,   9),
				new Card(Suit.HEART,   14),
				new Card(Suit.CLUB,    14));
		playTheseCards(startCards, expected, true);
	}
	@Test
	@DisplayName("No wildcards -> Exception")
	void testLogic9() {
		List<Card> startCards = Arrays.asList(
				new Card(Suit.CLUB,    2),
				new Card(Suit.DIAMOND, 8),
				new Card(Suit.HEART,   9),
				new Card(Suit.HEART,  14),
				new Card(Suit.HEART,   3));
		IllegalArgumentException ex = Assertions.assertThrows(
				IllegalArgumentException.class, () ->
				new PlayWildcards(new PokerHand(startCards)));
		assertTrue(ex.getMessage().startsWith("Poker hand does not contain wildcards"));
	}
}
