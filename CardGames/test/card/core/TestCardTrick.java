package card.core;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static card.core.AddToDeck.*;
import static card.core.DeckOrder.*;
import static card.core.RemoveACard.*;
import static card.core.Card.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestCardTrick {
	Deck deck;
	

	@Before
	public void setUp() throws Exception {
		//deck = Deck.getInstance();
		deck = new Deck();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
   /*
    * See http://www.card-trick.com/mathematical_card_tricks/mental_agility_plus_1_math_card_trick.htm
    */
	public void TestMentalAgilityPlus1() {
		deck.shuffle();
		Card initialCard = deck.removeCard(TOP_CARD);
		System.out.println("Initial card is: " + initialCard);
		ArrayList<Card> pile1 = deck.deal(4);
		ArrayList<Card> pile2 = deck.deal(4);
		ArrayList<Card> pileWithInitialCard = new ArrayList<Card>();
		pileWithInitialCard.add(initialCard);
		pileWithInitialCard.addAll(0, pile1);
		deck.addCards(pileWithInitialCard, ADD_TO_TOP);
		ArrayList<Card> pileOf15 = deck.deal(15);
		Collections.reverse(pileOf15);
		deck.addCards(pileOf15, ADD_TO_TOP);
		deck.addCards(pile2, ADD_TO_TOP);
		Card finalCard = deck.removeCard(14);
		System.out.println("Final card is: " + finalCard);
		assertTrue(initialCard.equals(finalCard));
		assertTrue(initialCard == finalCard);
	}

	@Test
   /*
    * See http://www.card-trick.com/mathematical_card_tricks/predicta_pair_math_card%20_trick.htm
    */
	public void TestPredictAPair() {
		ArrayList<Card> removal = new ArrayList<Card>(Arrays.asList(
		TEN_CLUB,JACK_CLUB,QUEEN_CLUB,KING_CLUB,ACE_CLUB,TEN_HEART,JACK_HEART,QUEEN_HEART,KING_HEART,ACE_HEART,   
		TEN_SPADE,JACK_SPADE,QUEEN_SPADE,KING_SPADE,ACE_SPADE,TEN_DIAMOND,JACK_DIAMOND,QUEEN_DIAMOND,KING_DIAMOND,ACE_DIAMOND
		));
		// okay remove tens and all face cards
		deck.removeCard(removal);
		// now shuffle the deck
		deck.shuffle();
		int rand1 = (int) Math.random() * 32;
		Card pick1 = deck.removeCard(rand1);
		System.out.println("Random pick1: " + pick1);
		int rand2 = (int) Math.random() * 31;
		Card pick2 = deck.removeCard(rand2);
		System.out.println("Random pick2: " + pick2);
		int v = pick1.getValue();
		v = (((2 * v) + 5) * 5) + pick2.getValue() - 25;
		System.out.println("Numeric prediction: " + v);
		assertTrue(v == (pick1.getValue()*10) + pick2.getValue());
	}

}
