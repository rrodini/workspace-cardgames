package com.cardtech.game.war;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;


import com.cardtech.core.Deck;

/**
 *  TestWarInitialConditions - very basic test of initial conditions of war game.
 *
 */
public class TestWarInitialConditions {
	Deck deck = null;
	WarHand player1 = null;
	WarHand player2 = null;

	
	@BeforeEach
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

	@AfterEach
	public void tearDown() throws Exception {
	}

	@Test
	public void testInitialDeal(){
		assertTrue( deck.isEmpty());
		assertEquals(26, player1.getHandCount());
		assertEquals( 0, player1.getDownCardsCount());
		assertEquals( 0, player1.getUpCardsCount());
		assertEquals(26, player2.getHandCount());
		assertEquals( 0, player2.getDownCardsCount());
		assertEquals( 0, player2.getUpCardsCount());
	}

	@Test
	public void testPlaying3DownCards() {
		player1.playDownCard(3);
		player2.playDownCard(3);
		assertEquals( 23, player1.getHandCount());
		assertEquals( 23, player2.getHandCount());
		assertEquals( 3, player1.getDownCardsCount());
		assertEquals( 3, player2.getDownCardsCount());		
	}
	
	@Test
	public void testPlaying1UpCard() {
		player1.playUpCard(WarRoundContext.NO_WAR);
		player2.playUpCard(WarRoundContext.NO_WAR);
		assertEquals( 25, player1.getHandCount());
		assertEquals( 25, player2.getHandCount());
		assertEquals( 1, player1.getUpCardsCount());
		assertEquals( 1, player2.getUpCardsCount());		
	}


}
