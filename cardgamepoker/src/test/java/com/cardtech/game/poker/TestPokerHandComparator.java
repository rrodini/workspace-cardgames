package com.cardtech.game.poker;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;


import com.cardtech.core.Card;
import com.cardtech.core.Suit;


public class TestPokerHandComparator {

	@BeforeEach
	public void setUp() throws Exception {
	}

	@Test
	public void testHighCard() {
		System.out.println("testHighCard");
		List<Card> cards = new ArrayList<>(Arrays.asList(
				 new Card(Suit.SPADE,  3),
				 new Card(Suit.HEART,  4),
		         new Card(Suit.HEART, 12),
		         new Card(Suit.CLUB,   6),
				 new Card(Suit.DIAMOND,7)));
		PokerHand highCardHand = new PokerHand(cards);
		PokerRankWithHighCards rank = PokerRanker.rank(highCardHand);
		highCardHand.show();
		System.out.println("Rank is: " + rank.getRank());
		assertTrue(rank.getRank() == PokerRank.HIGH_CARD);
		List<Card> cards2 = new ArrayList<Card>(Arrays.asList(
				  new Card(Suit.HEART,    6),
		 		  new Card(Suit.DIAMOND,  8),
		 		  new Card(Suit.SPADE,   10),
		 		  new Card(Suit.CLUB,     2),
				  new Card(Suit.CLUB,     3)));
		PokerHand highCardHand2 = new PokerHand((ArrayList<Card>) cards2);
		rank = PokerRanker.rank(highCardHand2);
		highCardHand2.show();
		System.out.println("Rank is: " + rank.getRank());
		assertTrue(rank.getRank() == PokerRank.HIGH_CARD);
		int comp = highCardHand.compareTo(highCardHand2);
		System.out.println("Comparison: " + comp);		
		assertTrue( comp > 0);
		System.out.println();
	}

	@Test
	public void testHighCard2() {
		System.out.println("testHighCard2");
		List<Card> cards = new ArrayList<Card>(Arrays.asList(
						 new Card(Suit.SPADE,   3),
					  	 new Card(Suit.HEART,   4),
					  	 new Card(Suit.SPADE,   5),
					  	 new Card(Suit.SPADE,   6),
						 new Card(Suit.SPADE,   8)));
		PokerHand highCardHand = new PokerHand(cards);
		PokerRankWithHighCards rank = PokerRanker.rank(highCardHand);
		highCardHand.show();
		System.out.println("Rank is: " + rank.getRank());
		assertTrue( rank.getRank() == PokerRank.HIGH_CARD);
		List<Card> cards2 = new ArrayList<Card>(Arrays.asList(
						  new Card(Suit.SPADE,   2),
				 		  new Card(Suit.HEART,   5),
				 		  new Card(Suit.HEART,   6),
				 		  new Card(Suit.HEART,   7),
				 		  new Card(Suit.HEART,   8)));
		PokerHand highCardHand2 = new PokerHand(cards2);
		rank = PokerRanker.rank(highCardHand2);
		highCardHand2.show();
		System.out.println("Rank is: " + rank.getRank());
		assertTrue( rank.getRank() == PokerRank.HIGH_CARD);
		int comp = highCardHand.compareTo(highCardHand2);
		System.out.println("Comparison: " + comp);
		System.out.println();
		assertTrue( comp < 0);
	}
	
	@Test
	public void testHighCardTie() {
		System.out.println("testHighCardTie");
		List<Card> cards = new ArrayList<Card>(Arrays.asList(
						 new Card(Suit.SPADE,   4),
						 new Card(Suit.HEART,   5),
						 new Card(Suit.HEART,  13),
						 new Card(Suit.CLUB,    7),
						 new Card(Suit.DIAMOND, 8)));
		PokerHand highCardHand = new PokerHand(cards);
		PokerRankWithHighCards rank = PokerRanker.rank(highCardHand);
		highCardHand.show();
		System.out.println("Rank is: " + rank.getRank());
		assertTrue( rank.getRank() == PokerRank.HIGH_CARD);
		ArrayList<Card> cards2 = new ArrayList<Card>(Arrays.asList(
		  new Card(Suit.HEART,   7), 
  		  new Card(Suit.HEART,   8),
		  new Card(Suit.SPADE,  13),
		  new Card(Suit.CLUB,    4),
		  new Card(Suit.CLUB,    5)));
		PokerHand highCardHand2 = new PokerHand(cards2);
		rank = PokerRanker.rank(highCardHand2);
		highCardHand2.show();
		System.out.println("Rank is: " + rank.getRank());
		assertTrue( rank.getRank() == PokerRank.HIGH_CARD);
		int comp = highCardHand.compareTo(highCardHand2);
		System.out.println("Comparison: " + comp);		
		System.out.println();
		assertTrue( comp == 0);
	}
	
	@Test
	public void testThreeOfAKindAndTwoPair() {
		System.out.println("testThreeOfAKindAndTwoPair");
		List<Card> cards = new ArrayList<Card>(Arrays.asList(
						 new Card(Suit.DIAMOND, 10),
						 new Card(Suit.SPADE,   10),
						 new Card(Suit.CLUB,    10),
						 new Card(Suit.CLUB,     8),
						 new Card(Suit.HEART,    2)));
		PokerHand kind3 = new PokerHand(cards);
		PokerRankWithHighCards rank = PokerRanker.rank(kind3);
		kind3.show();
		System.out.println("Rank is: " + rank.getRank());
		assertTrue( rank.getRank() == PokerRank.THREE_OF_A_KIND);
		ArrayList<Card> cards2 = new ArrayList<Card>(Arrays.asList(
						  new Card(Suit.HEART,    9),
						  new Card(Suit.DIAMOND,  9),
						  new Card(Suit.SPADE,   11),
						  new Card(Suit.CLUB,    11),
						  new Card(Suit.CLUB,     3)));
		PokerHand twoPair = new PokerHand(cards2);
		rank = PokerRanker.rank(twoPair);
		twoPair.show();
		System.out.println("Rank is: " + rank.getRank());
		int comp = kind3.compareTo(twoPair);
		System.out.println("Comparison: " + comp);
		System.out.println();
		assertTrue( comp > 0);
	}
	
	@Test
	public void testThreeOfAKindBeatsHighCard() {
		System.out.println("testThreeOfAKindBeatsHighCard");
		// sounds uninteresting but this was a defect!
		List<Card> cards = new ArrayList<Card>(Arrays.asList(
						 new Card(Suit.DIAMOND, 8),
						 new Card(Suit.CLUB,    7),
						 new Card(Suit.HEART,   3),
						 new Card(Suit.HEART,  14),
						 new Card(Suit.HEART,  10)));
		PokerHand highCard = new PokerHand(cards);
		PokerRankWithHighCards rank = PokerRanker.rank(highCard);
		highCard.show();
		System.out.println("Rank is: " + rank);
		assertTrue( rank.getRank() == PokerRank.HIGH_CARD);
		List<Card> cards2 = new ArrayList<Card>(Arrays.asList(
						  new Card(Suit.DIAMOND, 4),
						  new Card(Suit.CLUB,    8),
						  new Card(Suit.DIAMOND, 2),
						  new Card(Suit.HEART,   8),
						  new Card(Suit.SPADE,   8)));
		PokerHand kind3 = new PokerHand(cards2);
		PokerRankWithHighCards rank2 = PokerRanker.rank(kind3);
		kind3.show();
		System.out.println("Rank is: " + rank2);
		assertTrue( rank2.getRank() == PokerRank.THREE_OF_A_KIND);
		int comp = highCard.compareTo(kind3);
		System.out.println("Comparison: " + comp);		
		System.out.println();
		assertTrue( comp < 0);
	}

}
