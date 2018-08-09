package com.cardtech.game.blackjack;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cardtech.core.Card;
import static com.cardtech.core.Suit.*;
import com.cardtech.core.Deck;
import com.cardtech.game.Player;
import com.cardtech.game.blackjack.BJGame;

class TestBJGame {
	static BJPlayerStrategy strategy = new BJPlayerStrategy();
	static BJDealerStrategy dealerStrategy = new BJDealerStrategy();

	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
// ATTENTION:  The dealer is considered one of the players.
	@Test
	void testTwoPlayersWithSplit() {
		BJPlayer [] players = { 
				new BJPlayer("manny", strategy), 
				new BJPlayer("dealer", dealerStrategy) };
		// carefully chosen cards so that player can split
		// and win both of her hands.
		List<Card> cards = new ArrayList<>(Arrays.asList(
				new Card(SPADE,   14), // A
				new Card(HEART,    7),
				new Card(CLUB,    14), // A
				new Card(HEART,    5),
				new Card(CLUB,    12), // Q
				new Card(DIAMOND,  9),
				new Card(DIAMOND,  6)
				));
		Deck deck = new Deck(cards);
		BJGame game = new BJGame(deck, players);
		game.initialize(false);
		game.play();
		List<Player> winners = game.getWinner();
		assertTrue(winners.contains(players[0]));
		assertFalse(winners.contains(players[players.length - 1]));
	}

	@Test
	void testThreePlayersWithSplit() {
		BJPlayer [] players = { 
				new BJPlayer("manny", strategy), 
				new BJPlayer("moe", strategy), 
				new BJPlayer("dealer", dealerStrategy) };
		// carefully chosen cards so that player can split
		// and win both of her hands.
		List<Card> cards = new ArrayList<>(Arrays.asList(
				new Card(HEART,    2),
				new Card(SPADE,   14), // A
				new Card(HEART,    7),
				new Card(CLUB,     5),
				new Card(CLUB,    14), // A
				new Card(HEART,    5),
				new Card(SPADE,    7),
				new Card(SPADE,   11),
				new Card(CLUB,    12), // Q
				new Card(DIAMOND,  9),
				new Card(DIAMOND,  6)
				));
		Deck deck = new Deck(cards);
		BJGame game = new BJGame(deck, players);
		game.initialize(false);
		game.play();
		List<Player> winners = game.getWinner();
		assertTrue(winners.contains(players[1]));
		assertFalse(winners.contains(players[0]));
		assertFalse(winners.contains(players[players.length - 1]));
	}
	// ATTENTION:  The dealer is considered one of the players.
		@Test
		void testFourPlayersWithNatural() {
			BJPlayer [] players = { 
					new BJPlayer("manny", strategy), 
					new BJPlayer("moe", strategy), 
					new BJPlayer("jack", strategy), 
					new BJPlayer("dealer", dealerStrategy) };
			// carefully chosen cards so that player can split
			// and win both of her hands.
			List<Card> cards = new ArrayList<>(Arrays.asList(
					new Card(CLUB,     5),
					new Card(HEART,   12), // Q
					new Card(HEART,    7),
					new Card(SPADE,   14), // A
					new Card(HEART,   14), // A
					new Card(DIAMOND, 14), // A
					new Card(CLUB,    10),
					new Card(CLUB,    13)  // K
					));
			Deck deck = new Deck(cards);
			BJGame game = new BJGame(deck, players);
			game.initialize(false);
			game.play();
			List<Player> winners = game.getWinner();
			assertTrue(winners.contains(players[1]));
			assertTrue(winners.contains(players[3]));
			assertFalse(winners.contains(players[0]));
			assertFalse(winners.contains(players[2]));
		}

}
