package card.game;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import card.core.Deck;
import static card.core.DeckOrder.*;

public class TestWarGame4Players {
	WarPlayer player0;
	WarPlayer player1;
	WarPlayer player2;
	WarPlayer player3;
	WarPlayer [] players;
	WarGame game;
	

	@Before
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
		Deck deck = new Deck(true);  
		game = new WarGame(deck, players);
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInitialize() {
		game.initialize(false);
		assertEquals("Player0 should have 13 cards", 13, game.getPlayerHand(player0).getHandCount());
		assertEquals("Player1 should have 13 cards", 13, game.getPlayerHand(player1).getHandCount());
		assertEquals("Player2 should have 13 cards", 13, game.getPlayerHand(player2).getHandCount());
		assertEquals("Player3 should have 13 cards", 13, game.getPlayerHand(player3).getHandCount());
	}
	
	@Test
	public void testOneRoundWithUnshuffledDeck() {
		game.initialize(false);
		game.playRound(WarRoundContext.NO_WAR);
		assertEquals("Player0 should have 0 cards", 0, game.getPlayerHand(player0).getHandCount());
		assertEquals("Player1 should have 0 cards", 0, game.getPlayerHand(player1).getHandCount());
		assertEquals("Player2 should have 0 cards", 0, game.getPlayerHand(player2).getHandCount());
		assertEquals("Player3 should have 0 cards", 0, game.getPlayerHand(player3).getHandCount());
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
		Player winner = game.getWinner();
		System.out.printf("Winner of this tie is: %s%n", winner);
		assertEquals("Player0 should win game", player0, winner);
		assertEquals("Only one round in this game", 1, game.rounds);
	}

	
}
