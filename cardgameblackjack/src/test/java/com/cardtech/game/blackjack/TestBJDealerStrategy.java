package com.cardtech.game.blackjack;

import static com.cardtech.core.Suit.CLUB;
import static com.cardtech.core.Suit.DIAMOND;
import static com.cardtech.game.blackjack.BJResponse.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cardtech.core.Card;

class TestBJDealerStrategy {
	BJDealerStrategy strategy;

	@BeforeEach
	void setUp() throws Exception {
		strategy = new BJDealerStrategy();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testDealerHand01() {
		System.out.println("testDealerHand01");
		// hard hand w/ total 12
		BJDealerHand pHand = new BJDealerHand(List.of(
				new Card(CLUB, 2),
				new Card(DIAMOND, 11) // J
				)); 
		int response = strategy.hit(null, pHand);
		assertEquals(H, response);
	}

	@Test
	void testDealerHand02() {
		System.out.println("testDealerHand02");
		// hard hand w/ total 18
		BJDealerHand pHand = new BJDealerHand(List.of(
				new Card(CLUB, 7),
				new Card(DIAMOND, 11) // J
				)); 
		int response = strategy.hit(null, pHand);
		assertEquals(S, response);
	}

	@Test
	void testDealerHand03() {
		System.out.println("testDealerHand03");
		// soft hand w/ total 17
		BJDealerHand pHand = new BJDealerHand(List.of(
				new Card(CLUB, 6),
				new Card(DIAMOND, 14) // A
				)); 
		int response = strategy.hit(null, pHand);
		assertEquals(H, response);
	}

	@Test
	void testDealerHand04() {
		System.out.println("testDealerHand04");
		// soft hand w/ total 18
		BJDealerHand pHand = new BJDealerHand(List.of(
				new Card(CLUB, 7),
				new Card(DIAMOND, 14) // A
				)); 
		int response = strategy.hit(null, pHand);
		assertEquals(S, response);
	}

}
