package com.cardtech.game.poker;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cardtech.core.Card;
import com.cardtech.core.Suit;
import com.cardtech.game.poker.wildcard.PlayWildcards;

class TestPlayWildcards {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testLogic1() {
		List<Card> startCards = new ArrayList<> (List.of(
			new Card(Suit.CLUB, 4),
			new Card(Suit.CLUB, 3),
			new Card(Suit.CLUB, 2),
			new Card(null, Integer.MAX_VALUE), // Joker 1
			new Card(null, Integer.MAX_VALUE - 1))); // Joker 2
//		PokerHand startHand = new PokerHand(startCards);
//		PlayWildcards pwc = new PlayWildcards(startHand);
//		PlayWildcards.play();
//		PokerHand virtualHand = PlayWildcards.getBestHand();
//		System.out.printf("start hand: %s virtual hand: %s%n", startHand, virtualHand);
	}
	@Test
	void testLogic2() {
		List<Card> startHand = new ArrayList<>(List.of(
				new Card(Suit.CLUB,    2),
				new Card(Suit.DIAMOND, 2),
				new Card(Suit.HEART,   2),
				new Card(Suit.HEART,   3),
				new Card(null, Integer.MAX_VALUE - 1))); // Joker 2
		PokerHand hand = new PokerHand(startHand);
//		PlayWildcards pwc = new PlayWildcards(hand);
//		pwc.play();
//		PokerHand virtualHand = pwc.getBestHand();
//		System.out.printf("start hand: %s virtual hand: %s%n", hand, virtualHand);
	}
	
}
