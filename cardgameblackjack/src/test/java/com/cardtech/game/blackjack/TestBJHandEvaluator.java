package com.cardtech.game.blackjack;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.cardtech.core.Rank.*;
import static com.cardtech.core.Suit.*;
import com.cardtech.core.Card;

import com.cardtech.game.Hand;

import static com.cardtech.game.blackjack.BJHandEvaluator.*;
import static com.cardtech.game.blackjack.BJHandValue.Firmness.*;
import static com.cardtech.game.blackjack.BJHandValue.Precis.*;

class TestBJHandEvaluator {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testCardEvaluator01() {
		//System.out.println("testCardEvaluator01");
		assertEquals(2, cardEvaluator(new Card(SPADE, TWO.getValue())));
	}
	
	@Test
	void testCardEvaluator02() {
		//System.out.println("testCardEvaluator02");
		assertEquals(9, cardEvaluator(new Card(DIAMOND, NINE.getValue())));
	}
	
	@Test
	void testAceDetector() {
		//System.out.println("testAceDetector");
		assertFalse(aceDetector(new Card(DIAMOND, NINE.getValue())));
		assertTrue(aceDetector(new Card(DIAMOND, ACE.getValue())));
	}
	
	@Test
	void testTotalToPrecis() {
		//System.out.println("testTotalToPrecis");
		assertEquals(OVER21_VALUE, totalToPrecis(230));
		assertEquals(UNDER21_VALUE, totalToPrecis(2));
		assertEquals(EXACTLY21_VALUE, totalToPrecis(21));
	}
	
	@Test
	void testHandSimple01() {
		System.out.println("testHandSimple01");
		List<Card> cards = List.of(
				new Card(SPADE, TWO.getValue()));
		Hand hand = new Hand(cards);
		BJHandValue val = BJHandEvaluator.evaluate(hand);
		BJHandValue expected = new BJHandValue(HARD_HAND, UNDER21_VALUE, 0, 2, 2);
		System.out.println(val);
		assertEquals(expected, val);
	}

	@Test
	void testHandSimple02() {
		System.out.println("testHandSimple02");
		List<Card> cards = List.of(
				new Card(SPADE, TWO.getValue()),
				new Card(HEART, THREE.getValue()));
		Hand hand = new Hand(cards);
		BJHandValue val = BJHandEvaluator.evaluate(hand);
		BJHandValue expected = new BJHandValue(HARD_HAND, UNDER21_VALUE, 0, 5, 5);
		System.out.println(val);
		assertEquals(expected, val);
	}
	
	@Test
	void testHandSimple03() {
		System.out.println("testHandSimple03");
		List<Card> cards = List.of(
				new Card(SPADE, TWO.getValue()),
				new Card(HEART, THREE.getValue()),
				new Card(DIAMOND, TEN.getValue()));
		Hand hand = new Hand(cards);
		BJHandValue val = BJHandEvaluator.evaluate(hand);
		BJHandValue expected = new BJHandValue(HARD_HAND, UNDER21_VALUE, 0, 15, 15);
		System.out.println(val);
		assertEquals(expected, val);
	}

	@Test
	void testHandWithFaceCards01() {
		System.out.println("testHandWithFaceCards01");
		List<Card> cards = List.of(
				new Card(SPADE, TWO.getValue()),
				new Card(HEART, THREE.getValue()),
				new Card(DIAMOND, JACK.getValue()));
		Hand hand = new Hand(cards);
		BJHandValue val = BJHandEvaluator.evaluate(hand);
		BJHandValue expected = new BJHandValue(HARD_HAND, UNDER21_VALUE, 0, 15, 15);
		System.out.println(val);
		assertEquals(expected, val);
	}
	
	@Test
	void testHandWithFaceCards02() {
		System.out.println("testHandWithFaceCards02");
		List<Card> cards = List.of(
				new Card(DIAMOND, JACK.getValue()),
				new Card(CLUB, KING.getValue()));
		Hand hand = new Hand(cards);
		BJHandValue val = BJHandEvaluator.evaluate(hand);
		BJHandValue expected = new BJHandValue(HARD_HAND, UNDER21_VALUE, 0, 20, 20);
		System.out.println(val);
		assertEquals(expected, val);
	}
	
	@Test
	void testHandWithFaceCards03() {
		System.out.println("testHandWithFaceCards03");
		List<Card> cards = List.of(
				new Card(DIAMOND, JACK.getValue()),
				new Card(CLUB, KING.getValue()),
				new Card(CLUB, TWO.getValue()));
		Hand hand = new Hand(cards);
		BJHandValue val = BJHandEvaluator.evaluate(hand);
		BJHandValue expected = new BJHandValue(HARD_HAND, OVER21_VALUE, 0, 22, 22);
		System.out.println(val);
		assertEquals(expected, val);
	}
	// From here out the hands contain aces!
	@Test
	void testHandWithOneAce01() {
		System.out.println("testHandWithOneAce01");
		List<Card> cards = List.of(
				new Card(HEART, ACE.getValue()));
		Hand hand = new Hand(cards);
		BJHandValue val = BJHandEvaluator.evaluate(hand);
		BJHandValue expected = new BJHandValue(SOFT_HAND, UNDER21_VALUE, 1, 1, 11);
		System.out.println(val);
		assertEquals(expected, val);
	}
	
	@Test
	void testHandWithOneAce02() {
		System.out.println("testHandWithOneAce02");
		List<Card> cards = List.of(
				new Card(CLUB, QUEEN.getValue()),
				new Card(HEART, ACE.getValue()));
		Hand hand = new Hand(cards);
		BJHandValue val = BJHandEvaluator.evaluate(hand);
		BJHandValue expected = new BJHandValue(SOFT_HAND, EXACTLY21_VALUE, 1, 11, 21);
		System.out.println(val);
		assertEquals(expected, val);
	}

	@Test
	void testHandWithOneAce03() {
		System.out.println("testHandWithOneAce03");
		List<Card> cards = List.of(
				new Card(DIAMOND, FOUR.getValue()),
				new Card(CLUB, QUEEN.getValue()),
				new Card(HEART, ACE.getValue()));
		Hand hand = new Hand(cards);
		BJHandValue val = BJHandEvaluator.evaluate(hand);
		BJHandValue expected = new BJHandValue(HARD_HAND, UNDER21_VALUE, 1, 15, 25);
		System.out.println(val);
		assertEquals(expected, val);
	}

	@Test
	void testHandWithOneAce04() {
		System.out.println("testHandWithOneAce04");
		List<Card> cards = List.of(
				new Card(DIAMOND, JACK.getValue()),
				new Card(CLUB, QUEEN.getValue()),
				new Card(HEART, ACE.getValue()));
		Hand hand = new Hand(cards);
		BJHandValue val = BJHandEvaluator.evaluate(hand);
		BJHandValue expected = new BJHandValue(HARD_HAND, EXACTLY21_VALUE, 1, 21, 31);
		System.out.println(val);
		assertEquals(expected, val);
	}
	@Test
	void testHandWithTwoAces01() {
		System.out.println("testHandWithTwoAces01");
		List<Card> cards = List.of(
				new Card(DIAMOND, ACE.getValue()),    // 1 or 11
				new Card(HEART, ACE.getValue()));     // 1 or 11
		Hand hand = new Hand(cards);
		BJHandValue val = BJHandEvaluator.evaluate(hand);
		BJHandValue expected = new BJHandValue(SOFT_HAND, UNDER21_VALUE, 2, 2, 12);
		System.out.println(val);
		assertEquals(expected, val);
	}
	@Test
	void testHandWithTwoAces02() {
		System.out.println("testHandWithTwoAces02");
		List<Card> cards = List.of(
				new Card(CLUB,  ACE.getValue()),     // 1 or 11
				new Card(HEART, ACE.getValue()),     // 1 or 11
				new Card(CLUB, QUEEN.getValue()));   // 10
		Hand hand = new Hand(cards);
		BJHandValue val = BJHandEvaluator.evaluate(hand);
		BJHandValue expected = new BJHandValue(HARD_HAND, UNDER21_VALUE, 2, 12, 22);
		System.out.println(val);
		assertEquals(expected, val);
	}

	@Test
	void testHandWithTwoAces03() {
		System.out.println("testHandWithTwoAces03");
		List<Card> cards = List.of(
				new Card(CLUB, ACE.getValue()),     // 1 or 11
				new Card(DIAMOND, FOUR.getValue()), // 4
				new Card(HEART, ACE.getValue()));   // 1 or 11
		Hand hand = new Hand(cards);
		BJHandValue val = BJHandEvaluator.evaluate(hand);
		BJHandValue expected = new BJHandValue(SOFT_HAND, UNDER21_VALUE, 2, 6, 16);
		System.out.println(val);
		assertEquals(expected, val);
	}

	@Test
	void testHandWithTwoAces04() {
		System.out.println("testHandWithTwoAces04");
		List<Card> cards = List.of(
				new Card(DIAMOND, FOUR.getValue()), // 4
				new Card(CLUB, ACE.getValue()),     // 1 or 11 
				new Card(DIAMOND, FIVE.getValue()), // 5
				new Card(HEART, ACE.getValue()));   // 1 or 11
		Hand hand = new Hand(cards);
		BJHandValue val = BJHandEvaluator.evaluate(hand);
		BJHandValue expected = new BJHandValue(SOFT_HAND, EXACTLY21_VALUE, 2, 11, 21);
		System.out.println(val);
		assertEquals(expected, val);
	}
	
	@Test
	void testHandWithThreeAces01() {
		System.out.println("testHandWithThreeAces01");
		List<Card> cards = List.of(
				new Card(CLUB,  ACE.getValue()),     // 1 or 11
				new Card(HEART, ACE.getValue()),     // 1 or 11
				new Card(DIAMOND, ACE.getValue()));  // 1 or 11
		Hand hand = new Hand(cards);
		BJHandValue val = BJHandEvaluator.evaluate(hand);
		BJHandValue expected = new BJHandValue(SOFT_HAND, UNDER21_VALUE, 3, 3, 13);
		System.out.println(val);
		assertEquals(expected, val);
	}

	@Test
	void testHandWithThreeAces02() {
		System.out.println("testHandWithThreeAces02");
		List<Card> cards = List.of(
				new Card(CLUB,  ACE.getValue()),     // 1 or 11
				new Card(HEART, ACE.getValue()),     // 1 or 11
				new Card(CLUB, QUEEN.getValue()),    // 10
				new Card(DIAMOND, ACE.getValue()));  // 1 or 11
		Hand hand = new Hand(cards);
		BJHandValue val = BJHandEvaluator.evaluate(hand);
		BJHandValue expected = new BJHandValue(HARD_HAND, UNDER21_VALUE, 3, 13, 23);
		System.out.println(val);
		assertEquals(expected, val);
	}

	@Test
	void testHandWithThreeAces03() {
		System.out.println("testHandWithThreeAces03");
		List<Card> cards = List.of(
				new Card(HEART, FOUR.getValue()),   // 4
				new Card(CLUB, ACE.getValue()),     // 1 or 11
				new Card(SPADE, ACE.getValue()),    // 1 or 11
				new Card(DIAMOND, FOUR.getValue()), // 4
				new Card(HEART, ACE.getValue()));   // 1 or 11
		Hand hand = new Hand(cards);
		BJHandValue val = BJHandEvaluator.evaluate(hand);
		BJHandValue expected = new BJHandValue(SOFT_HAND, EXACTLY21_VALUE, 3, 11, 21);
		System.out.println(val);
		assertEquals(expected, val);
	}

	@Test
	void testHandWithThreeAces04() {
		System.out.println("testHandWithThreeAces04");
		List<Card> cards = List.of(
				new Card(DIAMOND, SIX.getValue()),  // 6
				new Card(CLUB, ACE.getValue()),     // 1 or 11 
				new Card(DIAMOND, FIVE.getValue()), // 5
				new Card(SPADE, ACE.getValue()),    // 1 or 11
				new Card(HEART, ACE.getValue()));   // 1 or 11
		Hand hand = new Hand(cards);
		BJHandValue val = BJHandEvaluator.evaluate(hand);
		BJHandValue expected = new BJHandValue(HARD_HAND, UNDER21_VALUE, 3, 14, 24);
		System.out.println(val);
		assertEquals(expected, val);
	}

	@Test
	void testHandWithFourAces01() {
		System.out.println("testHandWithFourAces01");
		List<Card> cards = List.of(
				new Card(CLUB,  ACE.getValue()),     // 1 or 11
				new Card(HEART, ACE.getValue()),     // 1 or 11
				new Card(SPADE, ACE.getValue()),     // 1 or 11
				new Card(DIAMOND, ACE.getValue()));  // 1 or 11
		Hand hand = new Hand(cards);
		BJHandValue val = BJHandEvaluator.evaluate(hand);
		BJHandValue expected = new BJHandValue(SOFT_HAND, UNDER21_VALUE, 4, 4, 14);
		System.out.println(val);
		assertEquals(expected, val);
	}

	@Test
	void testHandWithFourAces02() {
		System.out.println("testHandWithFourAces02");
		List<Card> cards = List.of(
				new Card(CLUB,  ACE.getValue()),     // 1 or 11
				new Card(HEART, ACE.getValue()),     // 1 or 11
				new Card(CLUB, QUEEN.getValue()),    // 10
				new Card(SPADE, ACE.getValue()),     // 1 or 11
				new Card(DIAMOND, ACE.getValue()));  // 1 or 11
		Hand hand = new Hand(cards);
		BJHandValue val = BJHandEvaluator.evaluate(hand);
		BJHandValue expected = new BJHandValue(HARD_HAND, UNDER21_VALUE, 4, 14, 24);
		System.out.println(val);
		assertEquals(expected, val);
	}


}
