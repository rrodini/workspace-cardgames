package com.cardtech.core;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.cardtech.core.Card;
import com.cardtech.core.Deck;
import com.cardtech.core.Suit;

import static com.cardtech.core.AddToDeck.*;
import static com.cardtech.core.DealPosition.*;
import static com.cardtech.core.RemoveACard.*;
import static com.cardtech.core.Suit.*;
import static com.cardtech.core.Rank.TWO;
import static com.cardtech.core.Rank.FOUR;
import static com.cardtech.core.Rank.FIVE;
import static com.cardtech.core.Rank.SEVEN;
import static com.cardtech.core.Rank.ACE;

public class DeckTest {
	Deck deck;

	@BeforeEach
	void setUp() throws Exception {
		// for each test create a new 52 card deck.
		deck = new Deck(DeckOrder.SUIT_ORDER);
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	@Test
	public void testDeckInSuitOrder() {
		// line below forces coverage of constructor w/ no arguments.
		deck = new Deck();
		System.out.println("deckInSuitOrder()");
		assertEquals(Deck.STANDARD_DECK_SIZE, deck.getSize());
		for (Suit s : Suit.values()) {
			for (int i = TWO.getValue(); i <= ACE.getValue(); i++) {
				Card c = deck.removeCard(TOP_CARD);
//				String cardString = i + ":" + s.toString();
				assertEquals(cardString(i, s), c.toString());
			}
		}
	}
	private String cardString(int i, Suit s) {
		StringBuffer buf = new StringBuffer();
		switch (i) {
	case 11:
			buf.append("J:");
			break;
	case 12:
			buf.append("Q:");
			break;
	case 13:
			buf.append("K:");
			break;
	case 14:
			buf.append("A:");
			break;
	default:
			// all non-face cards here
			buf.append(i + ":");
		}
		buf.append(s.toString());
		return buf.toString();
	}
	
	@Test
	public void testDeckInRankOrder() {
		System.out.println("deckInRankOrder()");
		// Shadow the global "standard" deck.
		Deck deck1 = new Deck(DeckOrder.RANK_ORDER);
		assertEquals(Deck.STANDARD_DECK_SIZE, deck1.getSize());
		for (int i = TWO.getValue(); i <= ACE.getValue(); i++) {
			for (Suit s : Suit.values()) {
				Card c = deck1.removeCard(TOP_CARD);
				assertEquals(cardString(i, s), c.toString());
			}
		}
	}
	@Test
	void testDeckConstructorWithDeckParam() {
		List<Card> cards = new ArrayList<Card>();
		createSuit(cards, Suit.CLUB);
		createSuit(cards, Suit.DIAMOND);
		createSuit(cards, Suit.HEART);
		createSuit(cards, Suit.SPADE);
		createSuit(cards, Suit.CLUB);
		createSuit(cards, Suit.DIAMOND);
		createSuit(cards, Suit.HEART);
		createSuit(cards, Suit.SPADE);
		Deck deck = new Deck(cards);
		assertEquals(104, deck.getSize() );
	}
	private void createSuit(List<Card> cards, Suit s) {
		for (int i = TWO.getValue(); i <= ACE.getValue(); i++) {
			cards.add(new Card(s, i));
		}
	}

	@Test
	public void testDeckConstructorException() {
		assertThrows(IllegalArgumentException.class, () -> {
			DeckOrder order = null;
			Deck deck = new Deck(order);
		});
	}
	//@Disabled
	@Test
	public void testShufflingTheDeck() {
		System.out.println("testShufflingTheDeck()");
		deck.shuffle();
		// how to test for randomness?  Measuring the distance of each card from its unshuffle position is too hard to do. So...
		int counter = 0;
		assertEquals(Deck.STANDARD_DECK_SIZE, deck.getSize());
		Iterator<Card> iter = deck.iterator();
		while (iter.hasNext()) {
			Card c = iter.next();
			// Can't use below or get ConcurrentModificationException
//			deck.removeCard(c);
			iter.remove();
			counter++;
		}
		assertEquals(0, deck.getSize());
		assertEquals(Deck.STANDARD_DECK_SIZE, counter);
	}
	//@Disabled
	@Test
	public void testCuttingTheDeck() {
		System.out.println("testCuttingTheDeck()");
		deck.cut();
		assertEquals(Deck.STANDARD_DECK_SIZE, deck.getSize());
		Suit [] testOrder = {HEART, SPADE, CLUB, DIAMOND};
		for (Suit s: testOrder) {
			for (int i = TWO.getValue(); i <= ACE.getValue(); i++) {
				Card c = deck.removeCard(TOP_CARD);
				assertEquals(cardString(i, s), c.toString());
			}
		}
		assertEquals(0, deck.getSize());
	}
	//@Disabled
	@Test
	public void testDealingFromTheDeck() {
		System.out.println("testDealingFromTheDeck()");
		List<Card> hand1 = deck.deal(13);
		List<Card> hand2 = deck.deal(13);
		List<Card> hand3 = deck.deal(13);
		List<Card> hand4 = deck.deal(13);
		assertTrue(deck.isEmpty());
		for (Card c: hand1) {
			assertEquals(cardString(c.getValue(), c.getSuit()), c.toString());
		}
		for (Card c: hand2) {
			assertEquals(cardString(c.getValue(), c.getSuit()), c.toString());
		}
		for (Card c: hand3) {
			assertEquals(cardString(c.getValue(), c.getSuit()), c.toString());
		}
		for (Card c: hand4) {
			assertEquals(cardString(c.getValue(), c.getSuit()), c.toString());
		}
	}
	//@Disabled
	@Test
	public void testRemovingCardsFromDifferentAreasOfTheDeck() {
		System.out.println("testRemovingCardsFromDifferentAreasOfTheDeck()");
		Card card1 = deck.removeCard(TOP_CARD);
		Card card2 = deck.removeCard(BOTTOM_CARD);
		Card card3 = deck.removeCard(RANDOM_CARD);
		Card twoClub = new Card(Suit.CLUB, 2);
		Card aceSpade = new Card(Suit.SPADE, 14);
		assertEquals(card1, twoClub);
		assertEquals(card2, aceSpade);
		assertFalse(card3.equals(twoClub));		
		assertFalse(card3.equals(aceSpade));		
	}
	//@Disabled
	@Test
	public void testRemovingAListOfCards() {
		System.out.println("testRemovingAListOfCards()");
		Card [] aces = {
				new Card(Suit.CLUB, 14),
				new Card(Suit.HEART,14),
				new Card(Suit.DIAMOND, 14),
				new Card(Suit.SPADE,14)
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
	//@Disabled
	@Test
	public void testRemoveCardsThenAddBack() {
		System.out.println("testRemoveCardsThenAddBack()");
		ArrayList<Card> jacks = new ArrayList<Card>();
		jacks.add(new Card(Suit.CLUB, 11));
		jacks.add(new Card(Suit.HEART, 11));
		jacks.add(new Card(Suit.DIAMOND, 11));
		jacks.add(new Card(Suit.SPADE, 11));
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
	public void testAddCards() {
		List<Card> spades2_4 = createCardList(2);
		List<Card> spades5_7 = createCardList(5);
		Deck deck = new Deck(spades2_4);
		AddToDeck how = AddToDeck.ADD_TO_TOP;
		deck.addCards(spades5_7, how);
		assertEquals(6, deck.getSize());
		// spades 5, 6, 7 should be at top of deck.
		for (int i = FIVE.getValue(); i <= SEVEN.getValue(); i++) {
			Card c = deck.removeCard(TOP_CARD);
			assertEquals(cardString(i, Suit.CLUB), c.toString());
		}
		how = AddToDeck.ADD_TO_BOTTOM;
		deck.addCards(spades5_7, how);
		assertEquals(6, deck.getSize());
		// spades 2, 3, 4 should be at top of deck.
		for (int i = TWO.getValue(); i <= FOUR.getValue(); i++) {
			Card c = deck.removeCard(TOP_CARD);
			assertEquals(cardString(i, Suit.CLUB), c.toString());
		}
		how = AddToDeck.ADD_TO_RANDOM;
		deck.addCards(spades5_7, how);
		// can't predict order, only count
		assertEquals(6, deck.getSize());
	}
  /**
   * create a deck of 3 cards of the club suit.	
   * @param low rank of low card.
   * @return cards for a Deck object.
   */
	private ArrayList<Card> createCardList(int low) {
		ArrayList<Card> cards = new ArrayList<Card>(Arrays.asList(
				new Card(Suit.CLUB, low),
				new Card(Suit.CLUB, low + 1),
				new Card(Suit.CLUB, low + 2)));
		return cards;
	}
	//@Disabled
	@Test
	public void testShowTheDeckOfCards() {
		System.out.println("testShowTheDeckOfCards()");
		deck.show();
		assertEquals(Deck.STANDARD_DECK_SIZE, deck.getSize());
	}
	//@Disabled
	@Test
	public void testExceptionWhenCardIsRemovedTwice() {
		System.out.println("testExceptionWhenCardIsRemovedTwice()");
		Card twoClub = new Card(Suit.CLUB, 2);
		Card c1 = deck.removeCard(twoClub);
		// exception here
		assertThrows((IllegalStateException.class), () -> {
			Card c2 = deck.removeCard(twoClub);
		});
	}
	//@Disabled
	public void testExceptionWhen53rdCardIsDealt() {
		System.out.println("testExceptionWhen53rdCardIsDealt()");
		List<Card> hand1 = deck.deal(13);
		List<Card> hand2 = deck.deal(13);
		List<Card> hand3 = deck.deal(13);
		List<Card> hand4 = deck.deal(13);
		assertThrows((IllegalStateException.class), () -> {
			List<Card> hand5 = deck.deal(1);
		});
	}
	//@Disabled
	@Test
	public void testExceptionWhenTooManyCardsAreDealt() {
		System.out.println("testExceptionWhenTooManyCardsAreDealt()");
		List<Card> hand1 = deck.deal(14);
		List<Card> hand2 = deck.deal(14);
		List<Card> hand3 = deck.deal(14);
		assertThrows((IllegalStateException.class), () -> {
			List<Card> hand4 = deck.deal(14);
		});
	}
	//@Disabled
	@Test
	public void testDealFromBottomOfDeck() {
		System.out.println("testDealFromBottomOfDeck()");
		List<Card> hand1 = deck.deal(5, DEAL_FROM_BOTTOM);
		assertEquals(hand1.get(4), new Card(Suit.SPADE, 10));
		assertEquals(hand1.get(3), new Card(Suit.SPADE, 11));
		assertEquals(hand1.get(2), new Card(Suit.SPADE, 12));
		assertEquals(hand1.get(1), new Card(Suit.SPADE, 13));
		assertEquals(hand1.get(0), new Card(Suit.SPADE, 14));
	}
	//@Disabled
	@Test
	public void testDealFromTopOfDeck() {
		System.out.println("testDealFromTopOfDeck()");
		List<Card> hand1 = deck.deal(5, DEAL_FROM_TOP);
		assertEquals(hand1.get(0), new Card(Suit.CLUB, 2));
		assertEquals(hand1.get(1), new Card(Suit.CLUB, 3));
		assertEquals(hand1.get(2), new Card(Suit.CLUB, 4));
		assertEquals(hand1.get(3), new Card(Suit.CLUB, 5));
		assertEquals(hand1.get(4), new Card(Suit.CLUB, 6));
	}
	@Test
	public void testDeckGetDeck() {
		List cards = deck.getDeck();
		assertTrue(cards != null);
		assertEquals(Deck.STANDARD_DECK_SIZE, cards.size());
	}
}
