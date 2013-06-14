package com.cardtech.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cardtech.core.Deck;


public class TestWarInitialConditions {
	Deck deck = null;
	WarHand player1 = null;
	WarHand player2 = null;

	
	@Before
	public void setUp() throws Exception {
		deck = new Deck();
		player1 = new WarHand();
		player2 = new WarHand();
		deck.shuffle();
		while (!deck.isEmpty()) {
			player1.addCard(deck.deal(1));
			player2.addCard(deck.deal(1));
		}
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInitialDeal(){
		assertTrue("Deck should be empty", deck.isEmpty());
		assertEquals("Player1 should have 26 cards", 26, player1.getHandCount());
		assertEquals("Player1 should have  0 down cards", 0, player1.getDownCardsCount());
		assertEquals("Player1 should have  0 up cards", 0, player1.getUpCardsCount());
		assertEquals("Player2 should have 26 cards", 26, player2.getHandCount());
		assertEquals("Player2 should have  0 down cards", 0, player2.getDownCardsCount());
		assertEquals("Player2 should have  0 up cards", 0, player2.getUpCardsCount());
	}

	@Test
	public void testPlaying3DownCards() {
		player1.playDownCard(3);
		player2.playDownCard(3);
		assertEquals("Player1 should have 23 cards", 23, player1.getHandCount());
		assertEquals("Player2 should have 23 cards", 23, player2.getHandCount());
		assertEquals("Player1 should have  3 down cards", 3, player1.getDownCardsCount());
		assertEquals("Player2 should have  3 down cards", 3, player2.getDownCardsCount());		
	}
	
	@Test
	public void testPlaying1UpCard() {
		player1.playUpCard(WarRoundContext.NO_WAR);
		player2.playUpCard(WarRoundContext.NO_WAR);
		assertEquals("Player1 should have 25 cards", 25, player1.getHandCount());
		assertEquals("Player2 should have 25 cards", 25, player2.getHandCount());
		assertEquals("Player1 should have  1 up card", 1, player1.getUpCardsCount());
		assertEquals("Player2 should have  1 up card", 1, player2.getUpCardsCount());		
	}


}
