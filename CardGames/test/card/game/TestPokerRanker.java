package card.game;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import card.core.Card;
import static card.core.Card.*;
import static card.core.DeckOrder.*;
import card.core.*;


public class TestPokerRanker {

	static Deck deck;	// this is a *new* deck with a pre-determined order
	@Before
	public void setUp() throws Exception {
		deck = new Deck();
	}

	@Test
	public void testStraightFlush() {
		ArrayList<Card> hand = new ArrayList<Card>(Arrays.asList(
						deck.removeCard(ACE_SPADE),
						deck.removeCard(KING_SPADE),
				        deck.removeCard(QUEEN_SPADE),
				        deck.removeCard(JACK_SPADE),
						deck.removeCard(TEN_SPADE)));
		PokerHand straightFlush = new PokerHand(hand);
		PokerRankWithHighCards rank = PokerRanker.rank(straightFlush);
		Card [] highCards = rank.getHighCards();
		System.out.println("Rank is: " + rank);
		assertTrue("Result", rank.getRank() == PokerRank.STRAIGHT_FLUSH);
		assertTrue("Result", highCards.length == 1);
		assertTrue("Result", highCards[0].getValue() == 14);
	}
	
	@Test
	public void testFourOfAKind() {
		ArrayList<Card> hand = new ArrayList<Card>(Arrays.asList(
						deck.removeCard(TWO_SPADE), 
						deck.removeCard(TWO_HEART),
						deck.removeCard(TWO_DIAMOND),
						deck.removeCard(TEN_SPADE),
						deck.removeCard(TWO_CLUB)));
		PokerHand fourOfAKind = new PokerHand(hand);
		PokerRankWithHighCards rank = PokerRanker.rank(fourOfAKind);
		Card [] highCards = rank.getHighCards();
		System.out.println("Rank is: " + rank.getRank());
		assertTrue("Result", rank.getRank() == PokerRank.FOUR_OF_A_KIND);
		assertTrue("Result", highCards.length == 1);
		assertTrue("Result", highCards[0].getValue() == 2);
		ArrayList<Card> hand2 = new ArrayList<Card>(Arrays.asList(
						deck.removeCard(JACK_HEART),
						deck.removeCard(JACK_SPADE),
						deck.removeCard(THREE_SPADE),
						deck.removeCard(JACK_DIAMOND),
						deck.removeCard(JACK_CLUB)));
		fourOfAKind = new PokerHand(hand2);
		rank = PokerRanker.rank(fourOfAKind);
		highCards = rank.getHighCards();
		System.out.println("Rank is: " + rank.getRank());
		assertTrue("Result", rank.getRank() == PokerRank.FOUR_OF_A_KIND);
		assertTrue("Result", highCards.length == 1);
		assertTrue("Result", highCards[0].getValue() == 11);
	}
	
	@Test
	public void testFullHouse() {
		ArrayList<Card> hand = new ArrayList<Card>(Arrays.asList(
						deck.removeCard(NINE_SPADE),
						deck.removeCard(NINE_HEART),
						deck.removeCard(JACK_SPADE),
						deck.removeCard(JACK_DIAMOND),
						deck.removeCard(JACK_CLUB)));
		PokerHand fullHouse = new PokerHand(hand);
		PokerRankWithHighCards rank = PokerRanker.rank(fullHouse);
		Card [] highCards = rank.getHighCards();
		System.out.println("Rank is: " + rank.getRank());
		assertTrue("Result", highCards.length == 1);
		assertTrue("Result", highCards[0].getValue() == 11);
		assertTrue("Result", rank.getRank() == PokerRank.FULL_HOUSE);
		ArrayList<Card> hand2 = new ArrayList<Card>(Arrays.asList(
						 deck.removeCard(EIGHT_SPADE), 
 						 deck.removeCard(EIGHT_DIAMOND),
						 deck.removeCard(EIGHT_CLUB),
						 deck.removeCard(QUEEN_SPADE),
						 deck.removeCard(QUEEN_HEART)));
		fullHouse = new PokerHand(hand2);
		rank = PokerRanker.rank(fullHouse);
		highCards = rank.getHighCards();
		System.out.println("Rank is: " + rank.getRank());
		assertTrue("Result", rank.getRank() == PokerRank.FULL_HOUSE);
		assertTrue("Result", highCards.length == 1);
		assertTrue("Result", highCards[0].getValue() == 8);
	}
	
	@Test
	public void testFlush() {
		ArrayList<Card> hand = new ArrayList<Card>(Arrays.asList(
						deck.removeCard(TWO_HEART),
						deck.removeCard(SEVEN_HEART),
						deck.removeCard(NINE_HEART),
						deck.removeCard(JACK_HEART),
						deck.removeCard(THREE_HEART)));
		PokerHand flush = new PokerHand(hand);
		PokerRankWithHighCards rank = PokerRanker.rank(flush);
		Card [] highCards = rank.getHighCards();
		System.out.println("Rank is: " + rank.getRank());
		assertTrue("Result", rank.getRank() == PokerRank.FLUSH);
		assertTrue("Result", highCards.length == 5);
		assertTrue("Result", highCards[0].getValue() == 11);
		assertTrue("Result", highCards[1].getValue() ==  9);
		assertTrue("Result", highCards[2].getValue() ==  7);
		assertTrue("Result", highCards[3].getValue() ==  3);
		assertTrue("Result", highCards[4].getValue() ==  2);
	}
	
	@Test
	public void testStraight() {
		ArrayList<Card> hand = new ArrayList<Card>(Arrays.asList(
				        deck.removeCard(THREE_SPADE), 
						deck.removeCard(FOUR_CLUB),
						deck.removeCard(FIVE_HEART),
						deck.removeCard(SIX_CLUB),
						deck.removeCard(SEVEN_HEART)));
		PokerHand straight = new PokerHand(hand);
		PokerRankWithHighCards rank = PokerRanker.rank(straight);
		Card [] highCards = rank.getHighCards();
		System.out.println("Rank is: " + rank.getRank());
		assertTrue("Result", rank.getRank() == PokerRank.STRAIGHT);
		assertTrue("Result", highCards.length == 1);
		assertTrue("Result", highCards[0].getValue() == 7);
	}
	
	@Test
	public void testThreeOfAKInd() {
		ArrayList<Card> hand = new ArrayList<Card>(Arrays.asList(
						deck.removeCard(TEN_DIAMOND), 
						deck.removeCard(TEN_SPADE),
						deck.removeCard(TEN_CLUB),
						deck.removeCard(EIGHT_CLUB),
						deck.removeCard(TWO_HEART)));
		PokerHand kind3 = new PokerHand(hand);
		PokerRankWithHighCards rank = PokerRanker.rank(kind3);
		Card [] highCards = rank.getHighCards();
		System.out.println("Rank is: " + rank);
		assertTrue("Result", rank.getRank() == PokerRank.THREE_OF_A_KIND);
		assertTrue("Result", highCards.length == 1);
		assertTrue("Result", highCards[0].getValue() == 10);
		ArrayList<Card> hand2 = new ArrayList<Card>(Arrays.asList(
						deck.removeCard(TWO_SPADE), 
						deck.removeCard(SEVEN_HEART),
						deck.removeCard(SEVEN_CLUB),
						deck.removeCard(JACK_CLUB),
						deck.removeCard(SEVEN_DIAMOND)));
		kind3 = new PokerHand(hand2);
		rank = PokerRanker.rank(kind3);
		highCards = rank.getHighCards();
		System.out.println("Rank is: " + rank.getRank());
		assertTrue("Result", rank.getRank() == PokerRank.THREE_OF_A_KIND);
		assertTrue("Result", highCards.length == 1);
		assertTrue("Result", highCards[0].getValue() == 7);
	}
	
	@Test
	public void testTwoPair() {
		ArrayList<Card> hand = new ArrayList<Card>(Arrays.asList(
					    deck.removeCard(FOUR_SPADE), 
						deck.removeCard(FOUR_HEART),
						deck.removeCard(KING_HEART),
						deck.removeCard(EIGHT_CLUB),
						deck.removeCard(EIGHT_DIAMOND)));
		PokerHand twoPair = new PokerHand(hand);
		PokerRankWithHighCards rank = PokerRanker.rank(twoPair);
		Card [] highCards = rank.getHighCards();
		System.out.println("Rank is: " + rank.getRank());
		assertTrue("Result", rank.getRank() == PokerRank.TWO_PAIR);
		assertTrue("Result", highCards.length == 3);
		assertTrue("Result", highCards[0].getValue() ==  8);
		assertTrue("Result", highCards[1].getValue() ==  4);
		assertTrue("Result", highCards[2].getValue() == 13);
		ArrayList<Card> hand2 = new ArrayList<Card>(Arrays.asList(
				 		deck.removeCard(NINE_HEART), 
						deck.removeCard(NINE_DIAMOND),
						deck.removeCard(JACK_SPADE),
						deck.removeCard(JACK_CLUB),
						deck.removeCard(THREE_CLUB)));
		twoPair = new PokerHand(hand2);
		rank = PokerRanker.rank(twoPair);
		highCards = rank.getHighCards();
		System.out.println("Rank is: " + rank.getRank());
		assertTrue("Result", rank.getRank() == PokerRank.TWO_PAIR);
		assertTrue("Result", highCards.length == 3);
		assertTrue("Result", highCards[0].getValue() == 11);
		assertTrue("Result", highCards[1].getValue() ==  9);
		assertTrue("Result", highCards[2].getValue() ==  3);
	}

	@Test
	public void testOnePair() {
		ArrayList<Card> hand = new ArrayList<Card>(Arrays.asList(
						deck.removeCard(FOUR_SPADE), 
						deck.removeCard(FOUR_HEART),
						deck.removeCard(KING_HEART),
						deck.removeCard(SEVEN_CLUB),
						deck.removeCard(EIGHT_DIAMOND)));
		PokerHand onePair = new PokerHand(hand);
		PokerRankWithHighCards rank = PokerRanker.rank(onePair);
		Card [] highCards = rank.getHighCards();
		System.out.println("Rank is: " + rank.getRank());
		assertTrue("Result", rank.getRank() == PokerRank.ONE_PAIR);
		assertTrue("Result", highCards.length == 4);
		assertTrue("Result", highCards[0].getValue() ==  4);
		assertTrue("Result", highCards[1].getValue() == 13);
		assertTrue("Result", highCards[2].getValue() ==  8);
		assertTrue("Result", highCards[3].getValue() ==  7);
		ArrayList<Card> hand2 = new ArrayList<Card>(Arrays.asList(
						deck.removeCard(NINE_HEART), 
						deck.removeCard(NINE_DIAMOND),
						deck.removeCard(JACK_SPADE),
						deck.removeCard(TWO_CLUB),
						deck.removeCard(THREE_CLUB)));
		onePair = new PokerHand(hand2);
		rank = PokerRanker.rank(onePair);
		highCards = rank.getHighCards();
		System.out.println("Rank is: " + rank.getRank());
		assertTrue("Result", rank.getRank() == PokerRank.ONE_PAIR);
		assertTrue("Result", highCards.length == 4);
		assertTrue("Result", highCards[0].getValue() ==  9);
		assertTrue("Result", highCards[1].getValue() == 11);
		assertTrue("Result", highCards[2].getValue() ==  3);
		assertTrue("Result", highCards[3].getValue() ==  2);
	}

	@Test
	public void testHighCard() {
		ArrayList<Card> hand = new ArrayList<Card>(Arrays.asList(
						deck.removeCard(FOUR_SPADE), 
						deck.removeCard(FIVE_HEART),
						deck.removeCard(KING_HEART),
						deck.removeCard(SEVEN_CLUB),
						deck.removeCard(EIGHT_DIAMOND)));
		PokerHand highCard = new PokerHand(hand);
		PokerRankWithHighCards rank = PokerRanker.rank(highCard);
		Card [] highCards = rank.getHighCards();
		System.out.println("Rank is: " + rank.getRank());
		assertTrue("Result", rank.getRank() == PokerRank.HIGH_CARD);
		assertTrue("Result", highCards.length == 5);
		assertTrue("Result", highCards[0].getValue() == 13);
		assertTrue("Result", highCards[1].getValue() ==  8);
		assertTrue("Result", highCards[2].getValue() ==  7);
		assertTrue("Result", highCards[3].getValue() ==  5);
		assertTrue("Result", highCards[4].getValue() ==  4);
		ArrayList<Card> hand2 = new ArrayList<Card>(Arrays.asList(
						deck.removeCard(SEVEN_HEART), 
						deck.removeCard(NINE_DIAMOND),
						deck.removeCard(JACK_SPADE),
						deck.removeCard(TWO_CLUB),
						deck.removeCard(THREE_CLUB)));
		highCard = new PokerHand(hand2);
		rank = PokerRanker.rank(highCard);
		highCards = rank.getHighCards();
		System.out.println("Rank is: " + rank.getRank());
		assertTrue("Result", rank.getRank() == PokerRank.HIGH_CARD);
		assertTrue("Result", highCards.length == 5);
		assertTrue("Result", highCards[0].getValue() == 11);
		assertTrue("Result", highCards[1].getValue() ==  9);
		assertTrue("Result", highCards[2].getValue() ==  7);
		assertTrue("Result", highCards[3].getValue() ==  3);
		assertTrue("Result", highCards[4].getValue() ==  2);
	}
}
