package com.cardtech.game;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.cardtech.core.Deck;
import com.cardtech.game.ConcreteCardGame;

class TestConcreteCardGame {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	@Test
	void testConstructor1() {
		Player [] players = new Player[0] ;
		
		Assertions.assertThrows(IllegalArgumentException.class, 
		() -> {
			ConcreteCardGame game = new ConcreteCardGame(players);
		});
	}
	
	@Test
	void testConstructor2() {
		Player [] players = new Player[1] ;
		ConcreteCardGame game = new ConcreteCardGame(players);
	}

	@Test
	void testConstructor3() {
		// should get the exception on the players parameter.
		Player [] players = new Player[0] ;
		Deck deck = new Deck();
		// should get the exception on the deck parameter.
		Assertions.assertThrows(IllegalArgumentException.class, 
		() -> {
			ConcreteCardGame game = new ConcreteCardGame(deck, players);
		});
	}
	
	@Test
	void testConstructor4() {
		// should not get the exception on the players parameter.
		Player [] players = new Player[1] ;
		Deck deck = null;
		// should get the exception on the deck parameter.
		Assertions.assertThrows(IllegalArgumentException.class, 
		() -> {
			ConcreteCardGame game = new ConcreteCardGame(deck, players);
		});
	}
	
	@Test
	void testConstructor5() {
		// should not get the exception on the players parameter.
		Player [] players = new Player[1] ;
		Deck deck = new Deck();
		// should not get the exception on the deck parameter.
		ConcreteCardGame game = new ConcreteCardGame(deck, players);
	}
	

	@Test
	void testMethods() {
		Player [] players = new Player[1] ;
		ConcreteCardGame game = new ConcreteCardGame(players);
		game.initialize(false);
		game.play();
		game.getWinner();
	}
}
