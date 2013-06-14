package com.cardtech.game;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TestWarGame3Players {
	WarPlayer player0;
	WarPlayer player1;
	WarPlayer player2;
	WarPlayer [] players;
	WarGame game;
	

	@Before
	public void setUp() throws Exception {
		player0 = new WarPlayer("Player0");
		player1 = new WarPlayer("Player1");
		player2 = new WarPlayer("Player2");
		players = new WarPlayer[3];
		players[0] = player0;
		players[1] = player1;		
		players[2] = player2;		
		game = new WarGame(players);
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInitialize() {
		game.initialize(false);
		assertEquals("Player0 should have 18 cards", 18, game.getPlayerHand(player0).getHandCount());
		assertEquals("Player1 should have 17 cards", 17, game.getPlayerHand(player1).getHandCount());
		assertEquals("Player2 should have 17 cards", 17, game.getPlayerHand(player2).getHandCount());
	}
	
	@Test
	public void testOneRoundWithUnshuffledDeck() {
		game.initialize(false);
		game.playRound(WarRoundContext.NO_WAR);
		assertEquals("Player0 should have 17 cards", 17, game.getPlayerHand(player0).getHandCount());
		assertEquals("Player1 should have 16 cards", 16, game.getPlayerHand(player1).getHandCount());
		assertEquals("Player2 should have 19 cards", 19, game.getPlayerHand(player2).getHandCount());
	}

	@Test
	public void testFourRoundsWithUnshuffledDeck() {
		game.initialize(false);
		for (int i = 0; i < 4; i++){
			game.playRound(WarRoundContext.NO_WAR);			
		}
		assertEquals("Player0 should have 14 cards", 14, game.getPlayerHand(player0).getHandCount());
		assertEquals("Player1 should have 13 cards", 13, game.getPlayerHand(player1).getHandCount());
		assertEquals("Player2 should have 25 cards", 25, game.getPlayerHand(player2).getHandCount());
	}

	@Test
	public void testFullGameWithUnshuffledDeck() {
		game.initialize(false);
		game.play();
		try {
			System.out.printf("Player0 has %d cards%n",game.getPlayerHand(player0).getHandCount());
		} catch (IllegalStateException e) {
			System.out.printf("Exception indicates Player0 has been elminated%n");
		}
		try {
			System.out.printf("Player1 has %d cards%n",game.getPlayerHand(player1).getHandCount());
		} catch (IllegalStateException e) {
			System.out.printf("Exception indicates Player1 has been elminated%n");
		}
		System.out.printf("Player2 has %d cards%n",game.getPlayerHand(player2).getHandCount());
		List<Player> winner = game.getWinner();
		assertEquals("Player2 should win game", player2, winner.get(0));
	}

	
}
