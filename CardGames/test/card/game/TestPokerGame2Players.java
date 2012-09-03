package card.game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


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
		PokerPlayer winner = game.getWinner();
		System.out.printf("Winner is %s\n", winner);
		System.out.printf("Winning hand rank %s\n", game.winningRank);
		assertEquals("Player 1 should win", player1, winner);
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
		PokerPlayer winner = game.getWinner();
		System.out.printf("Winner is %s\n", winner);
		System.out.printf("Winning hand rank %s\n", game.winningRank);
		assertEquals("Player 8 should win", players[8], winner);
	}
}
