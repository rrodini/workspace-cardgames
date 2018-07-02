package com.cardtech.game.poker;

import static com.cardtech.core.Card.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;

import com.cardtech.core.Card;
import com.cardtech.core.Deck;
import com.cardtech.core.Suit;


public class TestPokerRanker {

	Deck deck;	// this is a *new* deck with a pre-determined order
	@BeforeEach
	public void setUp() throws Exception {
		deck = new Deck();
	}

	@Test
	public void testRoyalFlush() {
		ArrayList<Card> hand = new ArrayList<Card>(Arrays.asList(
//						deck.removeCard(ACE_SPADE),
//						deck.removeCard(KING_SPADE),
//				        deck.removeCard(QUEEN_SPADE),
//				        deck.removeCard(JACK_SPADE),
//						deck.removeCard(TEN_SPADE)));
						deck.removeCard(new Card(Suit.SPADE, 14)),
						deck.removeCard(new Card(Suit.SPADE, 13)),
						deck.removeCard(new Card(Suit.SPADE, 12)),
						deck.removeCard(new Card(Suit.SPADE, 11)),
						deck.removeCard(new Card(Suit.SPADE, 10))));
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
//						deck.removeCard(TWO_DIAMOND),
//						deck.removeCard(THREE_DIAMOND),
//				        deck.removeCard(FOUR_DIAMOND),
//				        deck.removeCard(FIVE_DIAMOND),
//						deck.removeCard(SIX_DIAMOND)));
						deck.removeCard(new Card(Suit.DIAMOND, 2)),
						deck.removeCard(new Card(Suit.DIAMOND, 3)),
						deck.removeCard(new Card(Suit.DIAMOND, 4)),
						deck.removeCard(new Card(Suit.DIAMOND, 5)),
						deck.removeCard(new Card(Suit.DIAMOND, 6))));
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
//						deck.removeCard(TWO_SPADE), 
//						deck.removeCard(TWO_HEART),
//						deck.removeCard(TWO_DIAMOND),
//						deck.removeCard(TEN_SPADE),
//						deck.removeCard(TWO_CLUB)));
						deck.removeCard(new Card(Suit.SPADE, 2)),
						deck.removeCard(new Card(Suit.HEART, 2)),
						deck.removeCard(new Card(Suit.DIAMOND, 2)),
						deck.removeCard(new Card(Suit.SPADE, 10)),
						deck.removeCard(new Card(Suit.CLUB, 2))));
		PokerHand fourOfAKind = new PokerHand(hand);
		PokerRankWithHighCards rank = PokerRanker.rank(fourOfAKind);
		Card [] highCards = rank.getHighCards();
		System.out.println("Rank is: " + rank.getRank());
		assertTrue( rank.getRank() == PokerRank.FOUR_OF_A_KIND);
		assertTrue( highCards.length == 1);
		assertTrue( highCards[0].getValue() == 2);
		ArrayList<Card> hand2 = new ArrayList<Card>(Arrays.asList(
//						deck.removeCard(JACK_HEART),
//						deck.removeCard(JACK_SPADE),
//						deck.removeCard(THREE_SPADE),
//						deck.removeCard(JACK_DIAMOND),
//						deck.removeCard(JACK_CLUB)));
						deck.removeCard(new Card(Suit.HEART, 11)),
						deck.removeCard(new Card(Suit.SPADE, 11)),
						deck.removeCard(new Card(Suit.SPADE,  3)),
						deck.removeCard(new Card(Suit.DIAMOND, 11)),
						deck.removeCard(new Card(Suit.CLUB, 11))));
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
//						deck.removeCard(NINE_SPADE),
//						deck.removeCard(NINE_HEART),
//						deck.removeCard(JACK_SPADE),
//						deck.removeCard(JACK_DIAMOND),
//						deck.removeCard(JACK_CLUB)));
						deck.removeCard(new Card(Suit.SPADE,   9)),
						deck.removeCard(new Card(Suit.HEART,   9)),
						deck.removeCard(new Card(Suit.SPADE,  11)),
						deck.removeCard(new Card(Suit.DIAMOND,11)),
						deck.removeCard(new Card(Suit.CLUB,   11))));
		PokerHand fullHouse = new PokerHand(hand);
		PokerRankWithHighCards rank = PokerRanker.rank(fullHouse);
		Card [] highCards = rank.getHighCards();
		System.out.println("Rank is: " + rank.getRank());
		assertTrue( highCards.length == 1);
		assertTrue( highCards[0].getValue() == 11);
		assertTrue( rank.getRank() == PokerRank.FULL_HOUSE);
		ArrayList<Card> hand2 = new ArrayList<Card>(Arrays.asList(
//						 deck.removeCard(EIGHT_SPADE), 
// 						 deck.removeCard(EIGHT_DIAMOND),
//						 deck.removeCard(EIGHT_CLUB),
//						 deck.removeCard(QUEEN_SPADE),
//						 deck.removeCard(QUEEN_HEART)));
						deck.removeCard(new Card(Suit.SPADE,   8)),
						deck.removeCard(new Card(Suit.DIAMOND, 8)),
						deck.removeCard(new Card(Suit.CLUB,    8)),
						deck.removeCard(new Card(Suit.SPADE,  12)),
						deck.removeCard(new Card(Suit.HEART,  12))));
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
//						deck.removeCard(TWO_HEART),
//						deck.removeCard(SEVEN_HEART),
//						deck.removeCard(NINE_HEART),
//						deck.removeCard(JACK_HEART),
//						deck.removeCard(THREE_HEART)));
						deck.removeCard(new Card(Suit.HEART,  2)),
						deck.removeCard(new Card(Suit.HEART,  7)),
						deck.removeCard(new Card(Suit.HEART,  9)),
						deck.removeCard(new Card(Suit.HEART, 11)),
						deck.removeCard(new Card(Suit.HEART,  3))));
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
//				        deck.removeCard(THREE_SPADE), 
//						deck.removeCard(FOUR_CLUB),
//						deck.removeCard(FIVE_HEART),
//						deck.removeCard(SIX_CLUB),
//						deck.removeCard(SEVEN_HEART)));
						deck.removeCard(new Card(Suit.SPADE,  3)),
						deck.removeCard(new Card(Suit.CLUB,   4)),
						deck.removeCard(new Card(Suit.HEART,  5)),
						deck.removeCard(new Card(Suit.CLUB,   6)),
						deck.removeCard(new Card(Suit.HEART,  7))));
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
//						deck.removeCard(TEN_DIAMOND), 
//						deck.removeCard(TEN_SPADE),
//						deck.removeCard(TEN_CLUB),
//						deck.removeCard(EIGHT_CLUB),
//						deck.removeCard(TWO_HEART)));
						deck.removeCard(new Card(Suit.DIAMOND, 10)),
						deck.removeCard(new Card(Suit.SPADE,   10)),
						deck.removeCard(new Card(Suit.CLUB,    10)),
						deck.removeCard(new Card(Suit.CLUB,     8)),
						deck.removeCard(new Card(Suit.HEART,    2))));
		PokerHand kind3 = new PokerHand(hand);
		PokerRankWithHighCards rank = PokerRanker.rank(kind3);
		Card [] highCards = rank.getHighCards();
		System.out.println("Rank is: " + rank);
		assertTrue( rank.getRank() == PokerRank.THREE_OF_A_KIND);
		assertTrue( highCards.length == 1);
		assertTrue( highCards[0].getValue() == 10);
		ArrayList<Card> hand2 = new ArrayList<Card>(Arrays.asList(
//						deck.removeCard(TWO_SPADE), 
//						deck.removeCard(SEVEN_HEART),
//						deck.removeCard(SEVEN_CLUB),
//						deck.removeCard(JACK_CLUB),
//						deck.removeCard(SEVEN_DIAMOND)));
						deck.removeCard(new Card(Suit.SPADE,   2)),
						deck.removeCard(new Card(Suit.HEART,   7)),
						deck.removeCard(new Card(Suit.CLUB,    7)),
						deck.removeCard(new Card(Suit.CLUB,   11)),
						deck.removeCard(new Card(Suit.DIAMOND, 7))));
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
//					    deck.removeCard(FOUR_SPADE), 
//						deck.removeCard(FOUR_HEART),
//						deck.removeCard(KING_HEART),
//						deck.removeCard(EIGHT_CLUB),
//						deck.removeCard(EIGHT_DIAMOND)));
						deck.removeCard(new Card(Suit.SPADE,   4)),
						deck.removeCard(new Card(Suit.HEART,   4)),
						deck.removeCard(new Card(Suit.HEART,  13)),
						deck.removeCard(new Card(Suit.CLUB,    8)),
						deck.removeCard(new Card(Suit.DIAMOND, 8))));
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
//				 		deck.removeCard(NINE_HEART), 
//						deck.removeCard(NINE_DIAMOND),
//						deck.removeCard(JACK_SPADE),
//						deck.removeCard(JACK_CLUB),
//						deck.removeCard(THREE_CLUB)));
						deck.removeCard(new Card(Suit.HEART,    9)),
						deck.removeCard(new Card(Suit.DIAMOND,  9)),
						deck.removeCard(new Card(Suit.SPADE,   11)),
						deck.removeCard(new Card(Suit.CLUB,    11)),
						deck.removeCard(new Card(Suit.CLUB,     3))));
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
//						deck.removeCard(FOUR_SPADE), 
//						deck.removeCard(FOUR_HEART),
//						deck.removeCard(KING_HEART),
//						deck.removeCard(SEVEN_CLUB),
//						deck.removeCard(EIGHT_DIAMOND)));
						deck.removeCard(new Card(Suit.SPADE,   4)),
						deck.removeCard(new Card(Suit.HEART,   4)),
						deck.removeCard(new Card(Suit.HEART,  13)),
						deck.removeCard(new Card(Suit.CLUB,    7)),
						deck.removeCard(new Card(Suit.DIAMOND, 8))));
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
//						deck.removeCard(NINE_HEART), 
//						deck.removeCard(NINE_DIAMOND),
//						deck.removeCard(JACK_SPADE),
//						deck.removeCard(TWO_CLUB),
//						deck.removeCard(THREE_CLUB)));
						deck.removeCard(new Card(Suit.HEART,   9)),
						deck.removeCard(new Card(Suit.DIAMOND, 9)),
						deck.removeCard(new Card(Suit.SPADE,  11)),
						deck.removeCard(new Card(Suit.CLUB,    2)),
						deck.removeCard(new Card(Suit.CLUB,    3))));
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
//						deck.removeCard(FOUR_SPADE), 
//						deck.removeCard(FIVE_HEART),
//						deck.removeCard(KING_HEART),
//						deck.removeCard(SEVEN_CLUB),
//						deck.removeCard(EIGHT_DIAMOND)));
						deck.removeCard(new Card(Suit.SPADE,   4)),
						deck.removeCard(new Card(Suit.HEART,   5)),
						deck.removeCard(new Card(Suit.HEART,  13)),
						deck.removeCard(new Card(Suit.CLUB,    7)),
						deck.removeCard(new Card(Suit.DIAMOND, 8))));
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
//						deck.removeCard(SEVEN_HEART), 
//						deck.removeCard(NINE_DIAMOND),
//						deck.removeCard(JACK_SPADE),
//						deck.removeCard(TWO_CLUB),
//						deck.removeCard(THREE_CLUB)));
						deck.removeCard(new Card(Suit.HEART,  7)),
						deck.removeCard(new Card(Suit.DIAMOND, 9)),
						deck.removeCard(new Card(Suit.SPADE,  11)),
						deck.removeCard(new Card(Suit.CLUB,    2)),
						deck.removeCard(new Card(Suit.CLUB,    3))));
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
