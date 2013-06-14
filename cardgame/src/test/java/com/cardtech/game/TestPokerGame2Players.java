package com.cardtech.game;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cardtech.core.Deck;


public class TestPokerGame2Players {
	
	PokerPlayer player0;
	PokerPlayer player1;
	PokerGame game;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testOneHandWithUnshuffledDeckAnd2Players() {
		player0 = new PokerPlayer("Player0");
		player1 = new PokerPlayer("Player1");
		PokerPlayer [] players = new PokerPlayer[2];
		players[0] = player0;
		players[1] = player1;
		System.out.println("\n\nPoker game with 2 players");
		game = new PokerGame(players);
		game.initialize(false);
		game.play();
		game.show();
		List<Player> winners = game.getWinner();
		assertEquals("Solo winner", 1, winners.size());
		System.out.printf("Winner is %s\n", winners.get(0));
		System.out.printf("Winning hand rank %s\n", game.winningRank);
		assertEquals("Player 1 should win", player1, winners.get(0));
		assertEquals("FLUSH is winning rank", PokerRank.FLUSH, game.getWinningRank());
		assertEquals("Two hands", 2, game.getHands().size());
	}
	
	@Test
	public void testOneHandWithUnshuffledRankOrderedDeckAnd2Players() {
		player0 = new PokerPlayer("Player0");
		player1 = new PokerPlayer("Player1");
		PokerPlayer [] players = new PokerPlayer[2];
		players[0] = player0;
		players[1] = player1;
		System.out.println("\n\nPoker game with 2 players");
		// Create a deck in rank order
		Deck deck = new Deck(true);  
		game = new PokerGame(deck, players);
		game.initialize(false);
		game.play();
		game.show();
		List<Player> winners = game.getWinner();
		assertEquals("Two winners", 2, winners.size());
		System.out.printf("Winner#1 is %s\n", winners.get(0));
		System.out.printf("Winner#2 is %s\n", winners.get(1));
		System.out.printf("Winning hand rank %s\n", game.winningRank);
		assertEquals("Player 1 should win", player0, winners.get(0));
		assertEquals("Player 1 should win", player1, winners.get(1));
		assertEquals("TWO_PAIR is winning rank", PokerRank.TWO_PAIR, game.getWinningRank());
		assertEquals("Two hands", 2, game.getHands().size());
	}
	
	@Test
	public void testOneHandWithUnshuffledDeckAnd10Players() {
		PokerPlayer[] players = new PokerPlayer[10];
		players[0] = new PokerPlayer("Player0");
		players[1] = new PokerPlayer("Player1");
		players[2] = new PokerPlayer("Player2");
		players[3] = new PokerPlayer("Player3");
		players[4] = new PokerPlayer("Player4");
		players[5] = new PokerPlayer("Player5");
		players[6] = new PokerPlayer("Player6");
		players[7] = new PokerPlayer("Player7");
		players[8] = new PokerPlayer("Player8");
		players[9] = new PokerPlayer("Player9");
		System.out.println("\n\nPoker game with 10 players");
		game = new PokerGame(players);
		game.initialize(false);
		game.play();
		game.show();
		List<Player> winners = game.getWinner();
		assertEquals("Solo winner", 1, winners.size());
		System.out.printf("Winner is %s\n", winners.get(0));
		System.out.printf("Winning hand rank %s\n", game.winningRank);
		assertEquals("Player 8 should win", players[8], winners.get(0));
		assertEquals("HIGH_CARD is winning rank", PokerRank.HIGH_CARD, game.getWinningRank());
		assertEquals("Nine hands", 10, game.getHands().size());
	}
}
