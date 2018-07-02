package com.cardtech.game.war;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;


public class TestWarGame2Players {
	WarPlayer player0;
	WarPlayer player1;
	WarPlayer [] players;
	WarGame game;
	

	@BeforeEach
	public void setUp() throws Exception {
		player0 = new WarPlayer("Player0");
		player1 = new WarPlayer("Player1");
		players = new WarPlayer[2];
		players[0] = player0;
		players[1] = player1;		
		game = new WarGame(players);
		
	}

	@AfterEach
	public void tearDown() throws Exception {
	}
//@Disabled
	@Test
	public void testInitialize() {
		System.out.println("testInitialize()");
		game.initialize(false);
		assertEquals(26, game.getPlayerHand(player0).getHandCount(), "Player0 should have 26 cards");
		assertEquals( 26, game.getPlayerHand(player1).getHandCount(), "Player1 should have 26 cards");
	}
	
//@Disabled
	@Test
	public void testOneRoundWithUnshuffledDeck() {
		System.out.println("testOneRoundWithUnshuffledDeck()");
		game.initialize(false);
		game.playRound(WarRoundContext.NO_WAR);
		assertEquals( 25, game.getPlayerHand(player0).getHandCount(), "Player0 should have 25 cards");
		assertEquals( 27, game.getPlayerHand(player1).getHandCount(), "Player1 should have 27 cards");
	}


	@Test
	public void testTenRoundsWithUnshuffledDeck() {
		System.out.println("testTenRoundsWithUnshuffledDeck()");
		game.initialize(false);
		for (int i = 0; i < 10; i++){
			game.playRound(WarRoundContext.NO_WAR);			
		}
		System.out.printf("Player0 has %d cards%n",game.getPlayerHand(player0).getHandCount());
		System.out.printf("Player1 has %d cards%n",game.getPlayerHand(player1).getHandCount());
		assertEquals(18, game.getPlayerHand(player0).getHandCount());
		assertEquals(34, game.getPlayerHand(player1).getHandCount());
	}
 //@Disabled
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
		assertEquals( player1, game.getWinner().get(0), "Player1 should win game");
		System.out.printf("War game lasted %d rounds%n", game.rounds);
	}

	
}
