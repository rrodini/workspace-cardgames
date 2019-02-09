package com.cardtech.game.poker;


import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;


import com.cardtech.core.Deck;
import com.cardtech.core.DeckOrder;
import com.cardtech.game.Player;
import com.cardtech.game.Hand;


public class TestPokerGame2Players {
	
	PokerPlayer player0;
	PokerPlayer player1;
	PokerGame game;

	@BeforeEach
	public void setUp() throws Exception {
	}

	@AfterEach
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
		assertEquals( 1, winners.size(), "Solo winner");
		System.out.printf("Winner is %s\n", winners.get(0));
		System.out.printf("Winning hand rank %s\n", game.winningRank);
		assertEquals( player1, winners.get(0),"Player 1 should win");
		assertEquals( PokerRank.FLUSH, game.getWinningRank().getRank(), "FLUSH is winning rank");
		assertEquals( 2, game.getHands().size(), "Two hands");
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
		Deck deck = new Deck(DeckOrder.RANK_ORDER);  
		game = new PokerGame(deck, players);
		game.initialize(false);
		game.play();
		game.show();
		List<Player> winners = game.getWinner();
		assertEquals( 2, winners.size(), "Two winners");
		System.out.printf("Winner#1 is %s\n", winners.get(0));
		System.out.printf("Winner#2 is %s\n", winners.get(1));
		System.out.printf("Winning hand rank %s\n", game.winningRank);
		assertEquals( player0, winners.get(0), "Player 1 should win");
		assertEquals( player1, winners.get(1), "Player 1 should win");
		assertEquals( PokerRank.TWO_PAIR, game.getWinningRank().getRank(), "TWO_PAIR is winning rank");
		assertEquals( 2, game.getHands().size(), "Two hands");
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
		assertEquals( 1, winners.size(), "Solo winner");
		System.out.printf("Winner is %s\n", winners.get(0));
		System.out.printf("Winning hand rank %s\n", game.winningRank);
		assertEquals( players[8], winners.get(0), "Player 8 should win");
		assertEquals( PokerRank.HIGH_CARD, game.getWinningRank().getRank(), "HIGH_CARD is winning rank");
		assertEquals( 10, game.getHands().size(), "Nine hands");
	}
}
