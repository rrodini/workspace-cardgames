package com.cardtech.core;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.cardtech.core.Card;
import com.cardtech.core.Deck;
import com.cardtech.core.Rank;
import com.cardtech.core.Suit;

import static com.cardtech.core.AddToDeck.*;
import static com.cardtech.core.Card.*;
import static com.cardtech.core.DealPosition.*;
import static com.cardtech.core.Rank.*;
import static com.cardtech.core.RemoveACard.*;
import static com.cardtech.core.Suit.*;

public class DeckTest {
	Deck deck;

	@Before
	public void setUp() throws Exception {
		deck = new Deck();
	}
	@Test
	public void cardEnum() {
		for (Card c: Card.values()) {
			System.out.println(c.toString());
		}
	}
	@Test
	public void deckInSuitOrder() {
		assertEquals(52, deck.getSize());
		for (Suit s : Suit.values()) {
			for (Rank r : Rank.values()) {
				Card c = deck.removeCard(TOP_CARD);
				String cardString = r.toString() + "_" + s.toString();
				assertEquals(cardString, c.toString());
			}
		}
	}
	@Test
	public void deckInRankOrder() {
		Deck deck = new Deck(true);
		assertEquals(52, deck.getSize());
		for (Rank r : Rank.values()) {
			for (Suit s : Suit.values()) {
				Card c = deck.removeCard(TOP_CARD);
				String cardString = r.toString() + "_" + s.toString();
				assertEquals(cardString, c.toString());
			}
		}
	}
	@Test
	public void testShufflingTheDeck() {
		deck.shuffle();
		// how to test for randomness?  Measuring the distance of each card from its unshuffle position is too hard to do. So...
		int counter = 0;
		assertEquals(52, deck.getSize());
		for (Card c: Card.values()) {
			deck.removeCard(c);
			counter++;
		}
		assertEquals(0, deck.getSize());
		assertEquals(52, counter);
	}
	@Test
	public void testCuttingTheDeck() {
		deck.cut();
		assertEquals(52, deck.getSize());
		Suit [] testOrder = {DIAMOND, SPADE, CLUB, HEART};
		for (Suit s: testOrder) {
			for (Rank r: Rank.values()) {
				Card c = deck.removeCard(TOP_CARD);
				assertEquals(r.toString()+"_"+s.toString(), c.toString());
			}
		}
		assertEquals(0, deck.getSize());
	}
	@Test
	public void testDealingFromTheDeck() {
		List<Card> hand1 = deck.deal(13);
		List<Card> hand2 = deck.deal(13);
		List<Card> hand3 = deck.deal(13);
		List<Card> hand4 = deck.deal(13);
		assertTrue(deck.isEmpty());
		for (Card c: hand1) {
			assertEquals(c.getRank().toString() + "_CLUB", c.toString());
		}
		for (Card c: hand2) {
			assertEquals(c.getRank().toString() + "_HEART", c.toString());
		}
		for (Card c: hand3) {
			assertEquals(c.getRank().toString() + "_DIAMOND", c.toString());
		}
		for (Card c: hand4) {
			assertEquals(c.getRank().toString() + "_SPADE", c.toString());
		}
	}
	@Test
	public void testRemovingCardsFromDifferentAreasOfTheDeck() {
		Card card1 = deck.removeCard(TOP_CARD);
		Card card2 = deck.removeCard(BOTTOM_CARD);
		Card card3 = deck.removeCard(RANDOM_CARD);
		assertEquals(card1, TWO_CLUB);
		assertEquals(card2, ACE_SPADE);
		assertTrue(card3 != TWO_CLUB && card3 != ACE_SPADE);		
	}
	@Test
	public void testRemovingAListOfCards() {
		Card [] aces = {
				ACE_CLUB,
				ACE_HEART,
				ACE_DIAMOND,
				ACE_SPADE
		};
		List<Card> acesList = (List<Card>) Arrays.asList(aces);
		deck.removeCard(acesList);
		assertEquals(48, deck.getSize());
		while (deck.getSize() > 0) {
			Card c = deck.removeCard(TOP_CARD);
			assertFalse(acesList.contains(c));
		}
		assertEquals(0, deck.getSize());
	}
	@Test
	public void testRemoveCardsThenAddBack() {
		ArrayList<Card> jacks = new ArrayList<Card>();
		jacks.add(JACK_CLUB);
		jacks.add(JACK_HEART);
		jacks.add(JACK_DIAMOND);
		jacks.add(JACK_SPADE);
		deck.removeCard(jacks);
		deck.addCards(jacks, ADD_TO_BOTTOM);
		Card jack4 = deck.removeCard(deck.getSize()-1);
		Card jack3 = deck.removeCard(deck.getSize()-1);
		Card jack2 = deck.removeCard(deck.getSize()-1);
		Card jack1 = deck.removeCard(deck.getSize()-1);
		assertEquals(jacks.get(0), jack1);
		assertEquals(jacks.get(1), jack2);
		assertEquals(jacks.get(2), jack3);
		assertEquals(jacks.get(3), jack4);	
	}
	@Test
	public void testShowTheDeckOfCards() {
		deck.show();
	}
	@Test(expected=IllegalStateException.class)
	public void TestExceptionWhenCardIsRemovedTwice() {
		Card c1 = deck.removeCard(TWO_CLUB);
		Card c2 = deck.removeCard(TWO_CLUB);
	}
	@Test(expected=IllegalStateException.class)
	public void testExceptionWhen53rdCardIsDealt() {
		List<Card> hand1 = deck.deal(13);
		List<Card> hand2 = deck.deal(13);
		List<Card> hand3 = deck.deal(13);
		List<Card> hand4 = deck.deal(13);
		List<Card> hand5 = deck.deal(1);
	}
	@Test(expected=IllegalStateException.class)
	public void testExceptionWhenTooManyCardsAreDealt() {
		List<Card> hand1 = deck.deal(14);
		List<Card> hand2 = deck.deal(14);
		List<Card> hand3 = deck.deal(14);
		List<Card> hand4 = deck.deal(14);
		List<Card> hand5 = deck.deal(1);
	}
	
	@Test
	public void testDealFromBottomOfDeck() {
		List<Card> hand1 = deck.deal(5, DEAL_FROM_BOTTOM);
		assertEquals(hand1.get(4), TEN_SPADE);
		assertEquals(hand1.get(3), JACK_SPADE);
		assertEquals(hand1.get(2), QUEEN_SPADE);
		assertEquals(hand1.get(1), KING_SPADE);
		assertEquals(hand1.get(0), ACE_SPADE);
	}
	
	@Test
	public void testDealFromTopOfDeck() {
		List<Card> hand1 = deck.deal(5, DEAL_FROM_TOP);
		assertEquals(hand1.get(0), TWO_CLUB);
		assertEquals(hand1.get(1), THREE_CLUB);
		assertEquals(hand1.get(2), FOUR_CLUB);
		assertEquals(hand1.get(3), FIVE_CLUB);
		assertEquals(hand1.get(4), SIX_CLUB);
	}
		
}
