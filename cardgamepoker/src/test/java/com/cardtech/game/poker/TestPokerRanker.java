package com.cardtech.game.poker;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cardtech.core.Card;
import com.cardtech.core.Suit;


public class TestPokerRanker {

	@BeforeEach
	public void setUp() throws Exception {
	}

	@Test
	public void testRoyalFlush() {
		ArrayList<Card> hand = new ArrayList<Card>(Arrays.asList(
						new Card(Suit.SPADE, 14),
						new Card(Suit.SPADE, 13),
						new Card(Suit.SPADE, 12),
						new Card(Suit.SPADE, 11),
						new Card(Suit.SPADE, 10)));
//      Can't use this since you cannot sort an IMMUTABLE list.
//		List<Card> hand = List.of(
//				new Card(Suit.SPADE, 14),
//				new Card(Suit.SPADE, 13),
//				new Card(Suit.SPADE, 12),
//				new Card(Suit.SPADE, 11),
//				new Card(Suit.SPADE, 10));
		PokerHand royalFlush = new PokerHand(hand);
		PokerRankWithHighCards rank = PokerRanker.rank(royalFlush);
		Card [] highCards = rank.getHighCards();
		System.out.println("Rank is: " + rank);
		assertTrue( rank.getRank() == PokerRank.ROYAL_FLUSH);
		assertTrue( highCards.length == 1);
		assertTrue( highCards[0].getValue() == 14);
	}
	
	@Test
	public void testStraightFlush() {
		ArrayList<Card> hand = new ArrayList<Card>(Arrays.asList(
						new Card(Suit.DIAMOND, 2),
						new Card(Suit.DIAMOND, 3),
						new Card(Suit.DIAMOND, 4),
						new Card(Suit.DIAMOND, 5),
						new Card(Suit.DIAMOND, 6)));
		PokerHand straightFlush = new PokerHand(hand);
		PokerRankWithHighCards rank = PokerRanker.rank(straightFlush);
		Card [] highCards = rank.getHighCards();
		System.out.println("Rank is: " + rank);
		assertTrue( rank.getRank() == PokerRank.STRAIGHT_FLUSH);
		assertTrue( highCards.length == 1);
		assertTrue( highCards[0].getValue() == 6);
	}
	
	@Test
	public void testFourOfAKind() {
		ArrayList<Card> hand = new ArrayList<Card>(Arrays.asList(
						new Card(Suit.SPADE,   2),
						new Card(Suit.HEART,   2),
						new Card(Suit.DIAMOND, 2),
						new Card(Suit.SPADE,  10),
						new Card(Suit.CLUB,    2)));
		PokerHand fourOfAKind = new PokerHand(hand);
		PokerRankWithHighCards rank = PokerRanker.rank(fourOfAKind);
		Card [] highCards = rank.getHighCards();
		System.out.println("Rank is: " + rank.getRank());
		assertTrue( rank.getRank() == PokerRank.FOUR_OF_A_KIND);
		assertTrue( highCards.length == 1);
		assertTrue( highCards[0].getValue() == 2);
		ArrayList<Card> hand2 = new ArrayList<Card>(Arrays.asList(
						new Card(Suit.HEART,  11),
						new Card(Suit.SPADE,  11),
						new Card(Suit.SPADE,   3),
						new Card(Suit.DIAMOND,11),
						new Card(Suit.CLUB,   11)));
		fourOfAKind = new PokerHand(hand2);
		rank = PokerRanker.rank(fourOfAKind);
		highCards = rank.getHighCards();
		System.out.println("Rank is: " + rank.getRank());
		assertTrue( rank.getRank() == PokerRank.FOUR_OF_A_KIND);
		assertTrue( highCards.length == 1);
		assertTrue( highCards[0].getValue() == 11);
	}
	
	@Test
	public void testFullHouse() {
		ArrayList<Card> hand = new ArrayList<Card>(Arrays.asList(
						new Card(Suit.SPADE,   9),
						new Card(Suit.HEART,   9),
						new Card(Suit.SPADE,  11),
						new Card(Suit.DIAMOND,11),
						new Card(Suit.CLUB,   11)));
		PokerHand fullHouse = new PokerHand(hand);
		PokerRankWithHighCards rank = PokerRanker.rank(fullHouse);
		Card [] highCards = rank.getHighCards();
		System.out.println("Rank is: " + rank.getRank());
		assertTrue( highCards.length == 1);
		assertTrue( highCards[0].getValue() == 11);
		assertTrue( rank.getRank() == PokerRank.FULL_HOUSE);
		ArrayList<Card> hand2 = new ArrayList<Card>(Arrays.asList(
						new Card(Suit.SPADE,   8),
						new Card(Suit.DIAMOND, 8),
						new Card(Suit.CLUB,    8),
						new Card(Suit.SPADE,  12),
						new Card(Suit.HEART,  12)));
		fullHouse = new PokerHand(hand2);
		rank = PokerRanker.rank(fullHouse);
		highCards = rank.getHighCards();
		System.out.println("Rank is: " + rank.getRank());
		assertTrue( rank.getRank() == PokerRank.FULL_HOUSE);
		assertTrue( highCards.length == 1);
		assertTrue( highCards[0].getValue() == 8);
	}
	
	@Test
	public void testFlush() {
		ArrayList<Card> hand = new ArrayList<Card>(Arrays.asList(
						new Card(Suit.HEART,  2),
						new Card(Suit.HEART,  7),
						new Card(Suit.HEART,  9),
						new Card(Suit.HEART, 11),
						new Card(Suit.HEART,  3)));
		PokerHand flush = new PokerHand(hand);
		PokerRankWithHighCards rank = PokerRanker.rank(flush);
		Card [] highCards = rank.getHighCards();
		System.out.println("Rank is: " + rank.getRank());
		assertTrue( rank.getRank() == PokerRank.FLUSH);
		assertTrue( highCards.length == 5);
		assertTrue( highCards[0].getValue() == 11);
		assertTrue( highCards[1].getValue() ==  9);
		assertTrue( highCards[2].getValue() ==  7);
		assertTrue( highCards[3].getValue() ==  3);
		assertTrue( highCards[4].getValue() ==  2);
	}
	
	@Test
	public void testStraight() {
		ArrayList<Card> hand = new ArrayList<Card>(Arrays.asList(
						new Card(Suit.SPADE,  3),
						new Card(Suit.CLUB,   4),
						new Card(Suit.HEART,  5),
						new Card(Suit.CLUB,   6),
						new Card(Suit.HEART,  7)));
		PokerHand straight = new PokerHand(hand);
		PokerRankWithHighCards rank = PokerRanker.rank(straight);
		Card [] highCards = rank.getHighCards();
		System.out.println("Rank is: " + rank.getRank());
		assertTrue( rank.getRank() == PokerRank.STRAIGHT);
		assertTrue( highCards.length == 1);
		assertTrue( highCards[0].getValue() == 7);
	}
	
	@Test
	public void testThreeOfAKInd() {
		ArrayList<Card> hand = new ArrayList<Card>(Arrays.asList(
						new Card(Suit.DIAMOND, 10),
						new Card(Suit.SPADE,   10),
						new Card(Suit.CLUB,    10),
						new Card(Suit.CLUB,     8),
						new Card(Suit.HEART,    2)));
		PokerHand kind3 = new PokerHand(hand);
		PokerRankWithHighCards rank = PokerRanker.rank(kind3);
		Card [] highCards = rank.getHighCards();
		System.out.println("Rank is: " + rank);
		assertTrue( rank.getRank() == PokerRank.THREE_OF_A_KIND);
		assertTrue( highCards.length == 1);
		assertTrue( highCards[0].getValue() == 10);
		ArrayList<Card> hand2 = new ArrayList<Card>(Arrays.asList(
						new Card(Suit.SPADE,   2),
						new Card(Suit.HEART,   7),
						new Card(Suit.CLUB,    7),
						new Card(Suit.CLUB,   11),
						new Card(Suit.DIAMOND, 7)));
		kind3 = new PokerHand(hand2);
		rank = PokerRanker.rank(kind3);
		highCards = rank.getHighCards();
		System.out.println("Rank is: " + rank.getRank());
		assertTrue( rank.getRank() == PokerRank.THREE_OF_A_KIND);
		assertTrue( highCards.length == 1);
		assertTrue( highCards[0].getValue() == 7);
	}
	
	@Test
	public void testTwoPair() {
		ArrayList<Card> hand = new ArrayList<Card>(Arrays.asList(
						new Card(Suit.SPADE,   4),
						new Card(Suit.HEART,   4),
						new Card(Suit.HEART,  13),
						new Card(Suit.CLUB,    8),
						new Card(Suit.DIAMOND, 8)));
		PokerHand twoPair = new PokerHand(hand);
		PokerRankWithHighCards rank = PokerRanker.rank(twoPair);
		Card [] highCards = rank.getHighCards();
		System.out.println("Rank is: " + rank.getRank());
		assertTrue( rank.getRank() == PokerRank.TWO_PAIR);
		assertTrue( highCards.length == 3);
		assertTrue( highCards[0].getValue() ==  8);
		assertTrue( highCards[1].getValue() ==  4);
		assertTrue( highCards[2].getValue() == 13);
		ArrayList<Card> hand2 = new ArrayList<Card>(Arrays.asList(
						new Card(Suit.HEART,    9),
						new Card(Suit.DIAMOND,  9),
						new Card(Suit.SPADE,   11),
						new Card(Suit.CLUB,    11),
						new Card(Suit.CLUB,     3)));
		twoPair = new PokerHand(hand2);
		rank = PokerRanker.rank(twoPair);
		highCards = rank.getHighCards();
		System.out.println("Rank is: " + rank.getRank());
		assertTrue( rank.getRank() == PokerRank.TWO_PAIR);
		assertTrue( highCards.length == 3);
		assertTrue( highCards[0].getValue() == 11);
		assertTrue( highCards[1].getValue() ==  9);
		assertTrue( highCards[2].getValue() ==  3);
	}

	@Test
	public void testOnePair() {
		ArrayList<Card> hand = new ArrayList<Card>(Arrays.asList(
						new Card(Suit.SPADE,   4),
						new Card(Suit.HEART,   4),
						new Card(Suit.HEART,  13),
						new Card(Suit.CLUB,    7),
						new Card(Suit.DIAMOND, 8)));
		PokerHand onePair = new PokerHand(hand);
		PokerRankWithHighCards rank = PokerRanker.rank(onePair);
		Card [] highCards = rank.getHighCards();
		System.out.println("Rank is: " + rank.getRank());
		assertTrue( rank.getRank() == PokerRank.ONE_PAIR);
		assertTrue( highCards.length == 4);
		assertTrue( highCards[0].getValue() ==  4);
		assertTrue( highCards[1].getValue() == 13);
		assertTrue( highCards[2].getValue() ==  8);
		assertTrue( highCards[3].getValue() ==  7);
		ArrayList<Card> hand2 = new ArrayList<Card>(Arrays.asList(
						new Card(Suit.HEART,   9),
						new Card(Suit.DIAMOND, 9),
						new Card(Suit.SPADE,  11),
						new Card(Suit.CLUB,    2),
						new Card(Suit.CLUB,    3)));
		onePair = new PokerHand(hand2);
		rank = PokerRanker.rank(onePair);
		highCards = rank.getHighCards();
		System.out.println("Rank is: " + rank.getRank());
		assertTrue( rank.getRank() == PokerRank.ONE_PAIR);
		assertTrue( highCards.length == 4);
		assertTrue( highCards[0].getValue() ==  9);
		assertTrue( highCards[1].getValue() == 11);
		assertTrue( highCards[2].getValue() ==  3);
		assertTrue( highCards[3].getValue() ==  2);
	}

	@Test
	public void testHighCard() {
		ArrayList<Card> hand = new ArrayList<Card>(Arrays.asList(
						new Card(Suit.SPADE,   4),
						new Card(Suit.HEART,   5),
						new Card(Suit.HEART,  13),
						new Card(Suit.CLUB,    7),
						new Card(Suit.DIAMOND, 8)));
		PokerHand highCard = new PokerHand(hand);
		PokerRankWithHighCards rank = PokerRanker.rank(highCard);
		Card [] highCards = rank.getHighCards();
		System.out.println("Rank is: " + rank.getRank());
		assertTrue( rank.getRank() == PokerRank.HIGH_CARD);
		assertTrue( highCards.length == 5);
		assertTrue( highCards[0].getValue() == 13);
		assertTrue( highCards[1].getValue() ==  8);
		assertTrue( highCards[2].getValue() ==  7);
		assertTrue( highCards[3].getValue() ==  5);
		assertTrue( highCards[4].getValue() ==  4);
		ArrayList<Card> hand2 = new ArrayList<Card>(Arrays.asList(
						new Card(Suit.HEART,   7),
						new Card(Suit.DIAMOND, 9),
						new Card(Suit.SPADE,  11),
						new Card(Suit.CLUB,    2),
						new Card(Suit.CLUB,    3)));
		highCard = new PokerHand(hand2);
		rank = PokerRanker.rank(highCard);
		highCards = rank.getHighCards();
		System.out.println("Rank is: " + rank.getRank());
		assertTrue( rank.getRank() == PokerRank.HIGH_CARD);
		assertTrue( highCards.length == 5);
		assertTrue( highCards[0].getValue() == 11);
		assertTrue( highCards[1].getValue() ==  9);
		assertTrue( highCards[2].getValue() ==  7);
		assertTrue( highCards[3].getValue() ==  3);
		assertTrue( highCards[4].getValue() ==  2);
	}
}
