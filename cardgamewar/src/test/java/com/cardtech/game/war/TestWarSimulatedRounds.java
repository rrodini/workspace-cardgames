package com.cardtech.game.war;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;


import com.cardtech.core.Deck;
/**
 * TestWarSimulatedRounds - a little more that initialization, but not much.
 *
 */
public class TestWarSimulatedRounds {
	Deck deck = null;
	WarHand player1 = null;
	WarHand player2 = null;

	@BeforeEach
	public void setUp() throws Exception {
		deck = new Deck();
		player1 = new WarHand();
		player2 = new WarHand();
		// ATTENTION: Deck is *not* shuffled.
		while (!deck.isEmpty()) {
			player1.addCard(deck.deal(1));
			player2.addCard(deck.deal(1));
		}
	}

	@AfterEach
	public void tearDown() throws Exception {
	}

	@Test
	public void testBasicWar() {
		// assume the two up cards are the same.
		// obviously this is not the case since there are 0 up cards.
		player1.playDownCard(3);
		player2.playDownCard(3);
		player1.playUpCard(WarRoundContext.YES_WAR);
		player2.playUpCard(WarRoundContext.YES_WAR);
		player1.getUpCard().show();
		player2.getUpCard().show();
		
		int result = player1.getUpCard().compareTo(player2.getUpCard());
		assertEquals( -1, result);
		player2.addCards(player1.getDownCards());
		player2.addCards(player1.getUpCards());
		player2.addCards(player2.getDownCards());
		player2.addCards(player2.getUpCards());
		assertEquals( 22, player1.getHandCount());
		assertEquals( 30, player2.getHandCount());
	}

	
	
}
