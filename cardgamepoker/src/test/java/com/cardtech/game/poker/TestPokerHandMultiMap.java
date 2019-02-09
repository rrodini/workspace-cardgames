package com.cardtech.game.poker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cardtech.core.Card;
import com.cardtech.core.Suit;

class TestPokerHandMultiMap {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testMultiMap1() {
		ArrayList<Card> royalFlushSpadeCards = new ArrayList<Card>(Arrays.asList(
				new Card(Suit.SPADE, 14),
				new Card(Suit.SPADE, 13),
				new Card(Suit.SPADE, 12),
				new Card(Suit.SPADE, 11),
				new Card(Suit.SPADE, 10)));
		ArrayList<Card> royalFlushClubCards = new ArrayList<Card>(Arrays.asList(
				new Card(Suit.CLUB, 14),
				new Card(Suit.CLUB, 13),
				new Card(Suit.CLUB, 12),
				new Card(Suit.CLUB, 11),
				new Card(Suit.CLUB, 10)));
		PokerHand royalFlushSpadeHand = new PokerHand(royalFlushSpadeCards);
		PokerHand royalFlushClubHand = new PokerHand(royalFlushClubCards);
		PokerHandMultiMap map = new PokerHandMultiMap();
		// should result in a tie
		map.put(royalFlushSpadeHand, 0);
		map.put(royalFlushClubHand,  1);
		assertEquals(1, map.keySet().size());
		List<Integer> list = map.get(royalFlushSpadeHand);
		assertEquals(2, list.size());
		Set<PokerHand> set = map.keySet();
		assertTrue(set.contains(royalFlushSpadeHand)); // true because a flush hand "is-a" Comparable<PokerHand>
		assertTrue(set.contains(royalFlushClubHand));  // true because a flush hand "is-a" Comparable<PokerHand>
	}
	@Test
	void testMultiMap2() {
		ArrayList<Card> cards1 = new ArrayList<Card>(Arrays.asList(
				// HIGH_CARD - 7
				new Card(Suit.CLUB,     5),
				new Card(Suit.HEART,    2),
				new Card(Suit.DIAMOND,  3),
				new Card(Suit.SPADE,    6),
				new Card(Suit.SPADE,    7)));
		ArrayList<Card> cards2 = new ArrayList<Card>(Arrays.asList(
				// HIGH_CARD - 10
				new Card(Suit.CLUB,     2),
				new Card(Suit.HEART,    9),
				new Card(Suit.DIAMOND,  4),
				new Card(Suit.SPADE,    8),
				new Card(Suit.CLUB,    10)));
		ArrayList<Card> cards3 = new ArrayList<Card>(Arrays.asList(
				// ONE_PAIR - 4s
				new Card(Suit.CLUB,     4),
				new Card(Suit.HEART,    4),
				new Card(Suit.DIAMOND,  8),
				new Card(Suit.DIAMOND, 11),
				new Card(Suit.SPADE,    9)));
		ArrayList<Card> cards4 = new ArrayList<Card>(Arrays.asList(
				// ONE_PAIR - 8s
				new Card(Suit.CLUB,     8),
				new Card(Suit.CLUB,    11),
				new Card(Suit.HEART,   12),
				new Card(Suit.DIAMOND,  8),
				new Card(Suit.SPADE,    2)));
		ArrayList<Card> cards5 = new ArrayList<Card>(Arrays.asList(
				// TWO_PAIR - 5s & 10s
				new Card(Suit.CLUB,     5),
				new Card(Suit.HEART,    5),
				new Card(Suit.DIAMOND, 10),
				new Card(Suit.SPADE,   10),
				new Card(Suit.SPADE,   14)));
		PokerHand hand1 = new PokerHand(cards1);
		PokerHand hand2 = new PokerHand(cards2);
		PokerHand hand3 = new PokerHand(cards3);
		PokerHand hand4 = new PokerHand(cards4);
		PokerHand hand5 = new PokerHand(cards5);
		PokerHandMultiMap map = new PokerHandMultiMap();
		// key: PokerHand, value: player index
		map.put(hand1, 1);
		map.put(hand2, 2);
		map.put(hand3, 3);
		map.put(hand4, 4);
		map.put(hand5, 5);
		// 5 distinct rankings
		assertEquals(5, map.keySet().size());
		// check uniqueness
		List<Integer> list5 = map.get(hand5);
		assertEquals(1, list5.size());
		assertEquals(5, (int)list5.get(0));
		List<Integer> list4 = map.get(hand4);
		assertEquals(1, list4.size());
		assertEquals(4, (int)list4.get(0));
		List<Integer> list3 = map.get(hand3);
		assertEquals(1, list3.size());
		assertEquals(3, (int)list3.get(0));
		List<Integer> list2 = map.get(hand2);
		assertEquals(1, list2.size());
		assertEquals(2, (int)list2.get(0));
		List<Integer> list1 = map.get(hand1);
		assertEquals(1, list1.size());
		assertEquals(1, (int)list1.get(0));
	}
	@Test
	void testMultiMap3() {
	}
}
