package com.cardtech.game.poker;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.cardtech.core.Deck;
import com.cardtech.core.Suit;
import com.cardtech.core.Card;
import static com.cardtech.core.AddToDeck.*;

class TestPokerGame {

	PokerPlayer player0;
	PokerPlayer player1;
	PokerPlayer [] players = new PokerPlayer[2];
	PokerGame game;
	
	@BeforeEach
	void setUp() throws Exception {
		player0 = new PokerPlayer("Player0");
		player1 = new PokerPlayer("Player1");
		players[0] = player0;
		players[1] = player1;
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	@Disabled
	@Test
	void testPokerGameStandardDeck() {
		game = new PokerGame(players);
		Deck deck = game.getDeck();
		assertEquals(52, deck.getSize());
	}
	@Disabled
	@Test
	void testPokerGameWithJokerDeck() {
		Deck deck = new Deck();
		List<Card> wildCards = List.of(new Card(null, Integer.MAX_VALUE), new Card(null, Integer.MAX_VALUE));
		deck.addCards(wildCards, ADD_TO_BOTTOM);
		game = new PokerGame(deck, players);
		deck = game.getDeck();
		assertEquals(54, deck.getSize());
	}
	@Test
	void testPokerGameRiggedHands1() {
		// Must start with a modifiable list.
		List<Card> cards = new ArrayList<>() {{
				// arrange cards so that 
				// player0 gets 5:S 10:S A:C A:D A:H (3 aces)
				// player1 gets 2:C  2:H 2:D 2:S 2:C (5 twos)
				add(new Card(Suit.CLUB,    14));
				add(new Card(Suit.CLUB,     2));
				add(new Card(Suit.DIAMOND, 14));
				add(new Card(Suit.DIAMOND,  2));
				add(new Card(Suit.HEART,   14));
				add(new Card(Suit.HEART,    2));
				add(new Card(Suit.SPADE,   10));
				add(new Card(null, Integer.MAX_VALUE));
				add(new Card(Suit.SPADE,    5));	
				add(new Card(null, Integer.MAX_VALUE-1));
		}};
		Deck deck = new Deck(cards);
//System.out.println("Step00");
		game = new PokerGame(deck, players);
//System.out.println("Step01");
		game.initialize(false);
//System.out.println("Step02");
		game.play();
	}
	
	
	
}
