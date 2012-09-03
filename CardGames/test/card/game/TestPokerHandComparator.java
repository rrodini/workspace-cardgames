package card.game;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static card.core.Card.*;
import static card.core.DeckOrder.*;
import card.core.Card;
import card.core.Deck;


public class TestPokerHandComparator {

	static Deck deck;	// this is a *new* deck with a pre-determined order
	@Before
	public void setUp() throws Exception {
		deck = new Deck();
	}

	@Test
	public void testHighCard() {
		System.out.println("testHighCard");
		ArrayList<Card> cards = new ArrayList<Card>(Arrays.asList(
						 deck.removeCard(THREE_SPADE),
						 deck.removeCard(FOUR_HEART),
				         deck.removeCard(QUEEN_HEART),
				         deck.removeCard(SIX_CLUB),
        				 deck.removeCard(SEVEN_DIAMOND)));
		PokerHand highCardHand = new PokerHand(cards);
		PokerRankWithHighCards rank = PokerRanker.rank(highCardHand);
		highCardHand.show();
		System.out.println("Rank is: " + rank.getRank());
		assertTrue("Result", rank.getRank() == PokerRank.HIGH_CARD);
		ArrayList<Card> cards2 = new ArrayList<Card>(Arrays.asList(
						  deck.removeCard(SIX_HEART),
		         		  deck.removeCard(EIGHT_DIAMOND),
		         		  deck.removeCard(TEN_SPADE),
		         		  deck.removeCard(TWO_CLUB),
						  deck.removeCard(THREE_CLUB)));
		PokerHand highCardHand2 = new PokerHand((ArrayList<Card>) cards2);
		rank = PokerRanker.rank(highCardHand2);
		highCardHand2.show();
		System.out.println("Rank is: " + rank.getRank());
		assertTrue("Result", rank.getRank() == PokerRank.HIGH_CARD);
		int comp = highCardHand.compareTo(highCardHand2);
		System.out.println("Comparison: " + comp);		
		assertTrue("Result", comp > 0);
		System.out.println();
	}

	@Test
	public void testHighCard2() {
		System.out.println("testHighCard2");
		ArrayList<Card> cards = new ArrayList<Card>(Arrays.asList(
				         deck.removeCard(THREE_SPADE),
				 	  	 deck.removeCard(FOUR_HEART),
				 	  	 deck.removeCard(FIVE_SPADE),
				 	  	 deck.removeCard(SIX_SPADE),
						 deck.removeCard(EIGHT_SPADE)));
		PokerHand highCardHand = new PokerHand(cards);
		PokerRankWithHighCards rank = PokerRanker.rank(highCardHand);
		highCardHand.show();
		System.out.println("Rank is: " + rank.getRank());
		assertTrue("Result", rank.getRank() == PokerRank.HIGH_CARD);
		ArrayList<Card> cards2 = new ArrayList<Card>(Arrays.asList(
						  deck.removeCard(TWO_SPADE),
		 	  	 		  deck.removeCard(FIVE_HEART),
		 	  	 		  deck.removeCard(SIX_HEART),
		 	  	 		  deck.removeCard(SEVEN_HEART),
		 	  	 		  deck.removeCard(EIGHT_HEART)));
		PokerHand highCardHand2 = new PokerHand(cards2);
		rank = PokerRanker.rank(highCardHand2);
		highCardHand2.show();
		System.out.println("Rank is: " + rank.getRank());
		assertTrue("Result", rank.getRank() == PokerRank.HIGH_CARD);
		int comp = highCardHand.compareTo(highCardHand2);
		System.out.println("Comparison: " + comp);
		System.out.println();
		assertTrue("Result", comp < 0);
	}
	
	@Test
	public void testHighCardTie() {
		System.out.println("testHighCardTie");
		ArrayList<Card> cards = new ArrayList<Card>(Arrays.asList(
						 deck.removeCard(FOUR_SPADE),
						 deck.removeCard(FIVE_HEART),
						 deck.removeCard(KING_HEART),
						 deck.removeCard(SEVEN_CLUB),
						 deck.removeCard(EIGHT_DIAMOND)));
		PokerHand highCardHand = new PokerHand(cards);
		PokerRankWithHighCards rank = PokerRanker.rank(highCardHand);
		highCardHand.show();
		System.out.println("Rank is: " + rank.getRank());
		assertTrue("Result", rank.getRank() == PokerRank.HIGH_CARD);
		ArrayList<Card> cards2 = new ArrayList<Card>(Arrays.asList(
						  deck.removeCard(SEVEN_HEART), 
				  		  deck.removeCard(EIGHT_HEART),
						  deck.removeCard(KING_SPADE),
						  deck.removeCard(FOUR_CLUB),
		  				  deck.removeCard(FIVE_CLUB)));
		PokerHand highCardHand2 = new PokerHand(cards2);
		rank = PokerRanker.rank(highCardHand2);
		highCardHand2.show();
		System.out.println("Rank is: " + rank.getRank());
		assertTrue("Result", rank.getRank() == PokerRank.HIGH_CARD);
		int comp = highCardHand.compareTo(highCardHand2);
		System.out.println("Comparison: " + comp);		
		System.out.println();
		assertTrue("Result", comp == 0);
	}
	
	@Test
	public void testThreeOfAKindAndTwoPair() {
		System.out.println("testThreeOfAKindAndTwoPair");
		ArrayList<Card> cards = new ArrayList<Card>(Arrays.asList(
						 deck.removeCard(TEN_DIAMOND),
						 deck.removeCard(TEN_SPADE),
						 deck.removeCard(TEN_CLUB),
						 deck.removeCard(EIGHT_CLUB),
						 deck.removeCard(TWO_HEART)));
		PokerHand kind3 = new PokerHand(cards);
		PokerRankWithHighCards rank = PokerRanker.rank(kind3);
		kind3.show();
		System.out.println("Rank is: " + rank.getRank());
		assertTrue("Result", rank.getRank() == PokerRank.THREE_OF_A_KIND);
		ArrayList<Card> cards2 = new ArrayList<Card>(Arrays.asList(
						  deck.removeCard(NINE_HEART),
						  deck.removeCard(NINE_DIAMOND),
						  deck.removeCard(JACK_SPADE),
						  deck.removeCard(JACK_CLUB),
						  deck.removeCard(THREE_CLUB)));
		PokerHand twoPair = new PokerHand(cards2);
		rank = PokerRanker.rank(twoPair);
		twoPair.show();
		System.out.println("Rank is: " + rank.getRank());
		int comp = kind3.compareTo(twoPair);
		System.out.println("Comparison: " + comp);
		System.out.println();
		assertTrue("Result", comp > 0);
	}
	
	@Test
	public void testThreeOfAKindBeatsHighCard() {
		System.out.println("testThreeOfAKindBeatsHighCard");
		// sounds uninteresting but this was a defect!
		ArrayList<Card> cards = new ArrayList<Card>(Arrays.asList(
						 deck.removeCard(EIGHT_DIAMOND),
						 deck.removeCard(SEVEN_CLUB),
						 deck.removeCard(THREE_HEART),
						 deck.removeCard(ACE_HEART),
						 deck.removeCard(TEN_HEART)));
		PokerHand highCard = new PokerHand(cards);
		PokerRankWithHighCards rank = PokerRanker.rank(highCard);
		highCard.show();
		System.out.println("Rank is: " + rank);
		assertTrue("Result", rank.getRank() == PokerRank.HIGH_CARD);
		ArrayList<Card> cards2 = new ArrayList<Card>(Arrays.asList(
						  deck.removeCard(FOUR_DIAMOND),
						  deck.removeCard(EIGHT_CLUB),
						  deck.removeCard(TWO_DIAMOND),
						  deck.removeCard(EIGHT_HEART),
						  deck.removeCard(EIGHT_SPADE)));
		PokerHand kind3 = new PokerHand(cards2);
		PokerRankWithHighCards rank2 = PokerRanker.rank(kind3);
		kind3.show();
		System.out.println("Rank is: " + rank2);
		assertTrue("Result", rank2.getRank() == PokerRank.THREE_OF_A_KIND);
		int comp = highCard.compareTo(kind3);
		System.out.println("Comparison: " + comp);		
		System.out.println();
		assertTrue("Result", comp < 0);
	}

}
