package com.cardtech.game;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cardtech.core.Card;
import com.cardtech.core.CardBySuit;
import com.cardtech.core.Deck;
import com.cardtech.core.Rank;
import com.cardtech.core.Suit;

class TestHand {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testConstructor1() {
		Hand hand = new Hand();
		assertTrue(hand != null);
	}

	@Test
	void testConstructor2() {
		Deck deck = new Deck();
		Hand hand = new Hand(deck.getDeck());
		assertTrue(hand != null);
	}
	
	@Test
	void testGetHand() {
		Deck deck = new Deck();
		Hand hand = new Hand(deck.getDeck());
		assertTrue(deck.getDeck() == hand.getHand());
	}

	@Test
	void testSort() {
		Deck deck = new Deck();
		deck.shuffle();
		Hand hand = new Hand(deck.getDeck());
		hand.sort();
		// first four cards should be TWOs (no order to suits).
		assertEquals(Rank.LOW_CARD.getValue(), hand.getCard(0).getValue());
		assertEquals(Rank.LOW_CARD.getValue(), hand.getCard(1).getValue());
		assertEquals(Rank.LOW_CARD.getValue(), hand.getCard(2).getValue());
		assertEquals(Rank.LOW_CARD.getValue(), hand.getCard(3).getValue());
		// last four cards should be ACEs (no order to suits).
		assertEquals(Rank.HIGH_CARD.getValue(), hand.getCard(48).getValue());
		assertEquals(Rank.HIGH_CARD.getValue(), hand.getCard(49).getValue());
		assertEquals(Rank.HIGH_CARD.getValue(), hand.getCard(50).getValue());
		assertEquals(Rank.HIGH_CARD.getValue(), hand.getCard(51).getValue());
	}
	
	@Test
	void testSortUsingComparator() {
		Deck deck = new Deck();
		deck.shuffle();
		Hand hand = new Hand(deck.getDeck());
		CardBySuit orderBySuit = new CardBySuit();
		hand.sort(orderBySuit);
		// first four cards should be TWOs and suits in SUIT order).
		assertEquals(Rank.LOW_CARD.getValue(), hand.getCard(0).getValue());
		assertEquals(Suit.CLUB, hand.getCard(0).getSuit());
		assertEquals(Rank.LOW_CARD.getValue(), hand.getCard(1).getValue());
		assertEquals(Suit.DIAMOND, hand.getCard(1).getSuit());
		assertEquals(Rank.LOW_CARD.getValue(), hand.getCard(2).getValue());
		assertEquals(Suit.HEART, hand.getCard(2).getSuit());
		assertEquals(Rank.LOW_CARD.getValue(), hand.getCard(3).getValue());
		assertEquals(Suit.SPADE, hand.getCard(3).getSuit());
		// last four cards should be ACEs (no order to suits).
		assertEquals(Rank.HIGH_CARD.getValue(), hand.getCard(48).getValue());
		assertEquals(Suit.CLUB, hand.getCard(48).getSuit());
		assertEquals(Rank.HIGH_CARD.getValue(), hand.getCard(49).getValue());
		assertEquals(Suit.DIAMOND, hand.getCard(49).getSuit());
		assertEquals(Rank.HIGH_CARD.getValue(), hand.getCard(50).getValue());
		assertEquals(Suit.HEART, hand.getCard(50).getSuit());
		assertEquals(Rank.HIGH_CARD.getValue(), hand.getCard(51).getValue());
		assertEquals(Suit.SPADE, hand.getCard(51).getSuit());
	}
	
	@Test
	void testShow() {
		final PrintStream stdOut = System.out;
		final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
		List<Card> cards = List.of(
				new Card(Suit.CLUB, 2),
				new Card(Suit.DIAMOND, 3),
				new Card(Suit.HEART, Rank.KING.getValue()),
				new Card(Suit.SPADE, Rank.ACE.getValue())
				);
		String printResult = null;
		Hand hand = new Hand(cards);
		try {
			hand.show();
			System.setOut(new PrintStream(myOut));
			hand.show();
			printResult = myOut.toString();
			myOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.setOut(stdOut);
		String expectedResult = 
		 "[2:CLUB," +	
		  "3:DIAMOND," +		
		  "K:HEART," +		
		  "A:SPADE]\n";	
		assertEquals(expectedResult, printResult);
	}
	
	@Test
	void testToString() {
		List<Card> cards = Arrays.asList(
				new Card(Suit.CLUB, 2),
				new Card(Suit.DIAMOND, 3),
				new Card(Suit.HEART, Rank.KING.getValue()),
				new Card(Suit.SPADE, Rank.ACE.getValue())
				);
		Hand hand = new Hand(cards);
		System.out.println(hand);
		assertEquals("[2:CLUB,3:DIAMOND,K:HEART,A:SPADE]", hand.toString());
	}
	
	@Test
	void testGetCard() {
		List<Card> cards = Arrays.asList(
				new Card(Suit.CLUB, 2),
				new Card(Suit.DIAMOND, 3),
				new Card(Suit.HEART, Rank.KING.getValue()),
				new Card(Suit.SPADE, Rank.ACE.getValue())
				);
		Hand hand = new Hand(cards);
		assertEquals(new Card(Suit.CLUB, 2), hand.getCard(0));
		assertEquals(new Card(Suit.DIAMOND, 3), hand.getCard(1));
		assertEquals(new Card(Suit.HEART, Rank.KING.getValue()), hand.getCard(2));
		assertEquals(new Card(Suit.SPADE, Rank.ACE.getValue()), hand.getCard(3));
	}

	@Test
	void testGetCardException1() {
		List<Card> cards = Arrays.asList(
				new Card(Suit.CLUB, 2),
				new Card(Suit.DIAMOND, 3),
				new Card(Suit.HEART, Rank.KING.getValue()),
				new Card(Suit.SPADE, Rank.ACE.getValue())
				);
		Hand hand = new Hand(cards);
		Assertions.assertThrows(IllegalArgumentException.class, 
		() -> {
			Card card = hand.getCard(5);
		});
	}
	@Test
	void testGetCardException2() {
		Hand hand = new Hand();
		Assertions.assertThrows(IllegalArgumentException.class, 
		() -> {
			Card card = hand.getCard(-1);
		});
	}
	@Test
	void testContains() {
		Card which;
		List<Card> cards = List.of(
				new Card(Suit.CLUB, 2),
				which = new Card(Suit.DIAMOND, 3),
				new Card(Suit.HEART, Rank.KING.getValue()),
				new Card(Suit.SPADE, Rank.ACE.getValue())
				);
		Hand hand = new Hand(cards);
		assertTrue(hand.contains(which));
		assertFalse(hand.contains(null));
	}
	@Test
	void testGetCardCount() {
		List<Card> cards = List.of(
				new Card(Suit.CLUB, 2)
				);
		Hand hand = new Hand(cards);
		assertEquals(1, hand.getCardCount());
	}
	@Test
	void testAddCard() {
		Card which= new Card(Suit.DIAMOND, 3);
		List<Card> cards = new LinkedList<>();
		cards.add(new Card(Suit.CLUB, 2));
	    cards.add(new Card(Suit.HEART, Rank.KING.getValue()));
		cards.add(new Card(Suit.SPADE, Rank.ACE.getValue()));
		Hand hand = new Hand(cards);
		assertFalse(hand.contains(which));
		hand.addCard(which);
		assertTrue(hand.contains(which));
	}
	@Test
	void testAddCardWithNull() {
		Card which = null;
		List<Card> cards = new LinkedList<>();
		Hand hand = new Hand(cards);
		hand.addCard(which);
		assertFalse(hand.contains(which));
	}
	
}
