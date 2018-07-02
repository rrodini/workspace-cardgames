package com.cardtech.game.war;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;

import com.cardtech.core.Deck;
import com.cardtech.core.DeckOrder;
import com.cardtech.game.Player;

public class TestWarGame4Players {
	WarPlayer player0;
	WarPlayer player1;
	WarPlayer player2;
	WarPlayer player3;
	WarPlayer [] players;
	WarGame game;
	

	@BeforeEach
	public void setUp() throws Exception {
		player0 = new WarPlayer("Player0");
		player1 = new WarPlayer("Player1");
		player2 = new WarPlayer("Player2");
		player3 = new WarPlayer("Player3");
		players = new WarPlayer[4];
		players[0] = player0;
		players[1] = player1;		
		players[2] = player2;		
		players[3] = player3;
		// create the deck in rank order (twos, threes, fours, etc.)
		Deck deck = new Deck(DeckOrder.RANK_ORDER);  
		game = new WarGame(deck, players);
		
	}

	@AfterEach
	public void tearDown() throws Exception {
	}

	@Test
	public void testInitialize() {
		game.initialize(false);
		assertEquals( 13, game.getPlayerHand(player0).getHandCount());
		assertEquals( 13, game.getPlayerHand(player1).getHandCount());
		assertEquals( 13, game.getPlayerHand(player2).getHandCount());
		assertEquals( 13, game.getPlayerHand(player3).getHandCount());
	}
	
	@Test
	public void testOneRoundWithUnshuffledDeck() {
		game.initialize(false);
		game.playRound(WarRoundContext.NO_WAR);
		assertEquals( 0, game.getPlayerHand(player0).getHandCount());
		assertEquals( 0, game.getPlayerHand(player1).getHandCount());
		assertEquals( 0, game.getPlayerHand(player2).getHandCount());
		assertEquals( 0, game.getPlayerHand(player3).getHandCount());
	}

	@Test
	public void testFullGameWithUnshuffledDeck() {
		game.initialize(false);
		game.play();
		System.out.printf("Player0 has %d cards%n",game.getPlayerHand(player0).getHandCount());
		System.out.printf("Player1 has %d cards%n",game.getPlayerHand(player1).getHandCount());
		System.out.printf("Player2 has %d cards%n",game.getPlayerHand(player2).getHandCount());
		System.out.printf("Player3 has %d cards%n",game.getPlayerHand(player3).getHandCount());
//		assertEquals("All players eliminated", 0 , game.getActivePlayerCount());
		List<Player> winners = game.getWinner();
		assertEquals( 4, winners.size(), "Four way tie");
		for (int i=0; i < winners.size(); i++) {
			assertEquals( players[i], winners.get(i), "Player should win game");			
		}
		assertEquals( 1, game.rounds, "Only one round in this game");
	}

	
}
