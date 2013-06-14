package com.cardtech.game;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TestWarGame2Players {
	WarPlayer player0;
	WarPlayer player1;
	WarPlayer [] players;
	WarGame game;
	

	@Before
	public void setUp() throws Exception {
		player0 = new WarPlayer("Player0");
		player1 = new WarPlayer("Player1");
		players = new WarPlayer[2];
		players[0] = player0;
		players[1] = player1;		
		game = new WarGame(players);
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInitialize() {
		game.initialize(false);
		assertEquals("Player0 should have 26 cards", 26, game.getPlayerHand(player0).getHandCount());
		assertEquals("Player1 should have 26 cards", 26, game.getPlayerHand(player1).getHandCount());
	}
	

	@Test
	public void testOneRoundWithUnshuffledDeck() {
		game.initialize(false);
		game.playRound(WarRoundContext.NO_WAR);
		assertEquals("Player0 should have 25 cards", 25, game.getPlayerHand(player0).getHandCount());
		assertEquals("Player1 should have 27 cards", 27, game.getPlayerHand(player1).getHandCount());
	}


	@Test
	public void testTenRoundsWithUnshuffledDeck() {
		game.initialize(false);
		for (int i = 0; i < 10; i++){
			game.playRound(WarRoundContext.NO_WAR);			
		}
		System.out.printf("Player0 has %d cards%n",game.getPlayerHand(player0).getHandCount());
		System.out.printf("Player1 has %d cards%n",game.getPlayerHand(player1).getHandCount());
	}

	@Test
	public void testFullGameWithUnshuffledDeck() {
		game.initialize(false);
		game.play();
		System.out.printf("Player1 has %d cards%n",game.getPlayerHand(player1).getHandCount());
		try {
			System.out.printf("Player0 has %d cards%n",game.getPlayerHand(player0).getHandCount());
		} catch (IllegalStateException e) {
			System.out.println("Exception indicates Player0 has been elminated");
		}
		assertEquals("Player1 should win game", player1, game.getWinner().get(0));
		System.out.printf("War game lasted %d rounds%n", game.rounds);
	}

	
}
