package com.cardtech.game.blackjack;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cardtech.core.Card;
import static com.cardtech.core.Suit.*;
import static com.cardtech.game.blackjack.BJResponse.*;

class TestBJPlayerStrategy {
	BJPlayerStrategy strategy;
	final BJDealerHand dealHand2   = new BJDealerHand(List.of(new Card(CLUB, 2)));
	final BJDealerHand dealHand3   = new BJDealerHand(List.of(new Card(CLUB, 3)));
	final BJDealerHand dealHand4   = new BJDealerHand(List.of(new Card(CLUB, 4)));
	final BJDealerHand dealHand5   = new BJDealerHand(List.of(new Card(CLUB, 5)));
	final BJDealerHand dealHand6   = new BJDealerHand(List.of(new Card(CLUB, 6)));
	final BJDealerHand dealHand7   = new BJDealerHand(List.of(new Card(CLUB, 7)));
	final BJDealerHand dealHand8   = new BJDealerHand(List.of(new Card(CLUB, 8)));
	final BJDealerHand dealHand9   = new BJDealerHand(List.of(new Card(CLUB, 9)));
	final BJDealerHand dealHand10  = new BJDealerHand(List.of(new Card(CLUB, 10)));
	final BJDealerHand dealHandAce = new BJDealerHand(List.of(new Card(CLUB, 14)));

	@BeforeEach
	void setUp() throws Exception {
		strategy = new BJPlayerStrategy();
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	@Test
	void testPlayerHard01Dealer2() {
		System.out.println("testPlayerHard01Dealer2");
		// hard hand w/ total 12
		BJPlayerHand pHand = new BJPlayerHand(List.of(
				new Card(CLUB, 2),
				new Card(DIAMOND, 11)
				)); 
		int response = strategy.hit(pHand, dealHand2);
		assertEquals(H, response);
	}
	@Test
	void testPlayerHard02Dealer3() {
		System.out.println("testPlayerHard02Dealer3");
		// hard hand w/ total 20
		BJPlayerHand pHand = new BJPlayerHand(List.of(
				new Card(CLUB, 2),
				new Card(DIAMOND, 11),
				new Card(HEART, 8)
				)); 
		int response = strategy.hit(pHand, dealHand3);
		assertEquals(S, response);
	}
	@Test
	void testPlayerHard03Dealer4() {
		System.out.println("testPlayerHard03Dealer4");
		// hard hand w/ total 9
		BJPlayerHand pHand = new BJPlayerHand(List.of(
				new Card(CLUB, 5),
				new Card(HEART,4)
				)); 
		int response = strategy.hit(pHand, dealHand4);
		assertEquals(H, response);
	}
	@Test
	void testPlayerHard04Dealer5() {
		System.out.println("testPlayerHard04Dealer5");
		// hard hand w/ total 14
		BJPlayerHand pHand = new BJPlayerHand(List.of(
				new Card(CLUB, 5),
				new Card(HEART,4),
				new Card(HEART,5)
				)); 
		int response = strategy.hit(pHand, dealHand5);
		assertEquals(S, response);
	}
	@Test
	void testPlayerHard05Dealer6() {
		System.out.println("testPlayerHard05Dealer6");
		// hard hand w/ total 17
		BJPlayerHand pHand = new BJPlayerHand(List.of(
				new Card(CLUB, 7),
				new Card(HEART,13)
				)); 
		int response = strategy.hit(pHand, dealHand6);
		assertEquals(S, response);
	}
	@Test
	void testPlayerHard06Dealer7() {
		System.out.println("testPlayerHard06Dealer7");
		// hard hand w/ total 11
		BJPlayerHand pHand = new BJPlayerHand(List.of(
				new Card(CLUB, 7),
				new Card(HEART, 4)
				)); 
		int response = strategy.hit(pHand, dealHand7);
		assertEquals(H, response);
	}
	@Test
	void testPlayerHard07Dealer8() {
		System.out.println("testPlayerHard07Dealer8");
		// hard hand w/ total 17
		BJPlayerHand pHand = new BJPlayerHand(List.of(
				new Card(CLUB, 7),
				new Card(CLUB, 6),
				new Card(HEART, 4)
				)); 
		int response = strategy.hit(pHand, dealHand8);
		assertEquals(S, response);
	}
	@Test
	void testPlayerHard08Dealer9() {
		System.out.println("testPlayerHard08Dealer9");
		// hard hand w/ total 5
		BJPlayerHand pHand = new BJPlayerHand(List.of(
				new Card(CLUB, 2),
				new Card(CLUB, 3)
				)); 
		int response = strategy.hit(pHand, dealHand9);
		assertEquals(H, response);
	}
	@Test
	void testPlayerHard09Dealer10() {
		System.out.println("testPlayerHard09Dealer10");
		// hard hand w/ total 14
		BJPlayerHand pHand = new BJPlayerHand(List.of(
				new Card(CLUB, 2),
				new Card(CLUB, 3),
				new Card(HEART, 9)
				)); 
		int response = strategy.hit(pHand, dealHand10);
		assertEquals(H, response);
	}
	@Test
	void testPlayerHard10DealerAce() {
		System.out.println("testPlayerHard10DealerAce");
		// hard hand w/ total 16
		BJPlayerHand pHand = new BJPlayerHand(List.of(
				new Card(CLUB, 2),
				new Card(CLUB, 5),
				new Card(HEART, 9)
				)); 
		int response = strategy.hit(pHand, dealHandAce);
		assertEquals(H, response);
	}
	@Test
	void testPlayerSoft01Dealer2() {
		System.out.println("testPlayerSoft01Dealer2");
		// soft hand w/ total 9
		BJPlayerHand pHand = new BJPlayerHand(List.of(
				new Card(CLUB, 14), // ACE
				new Card(HEART, 9)
				)); 
		int response = strategy.hit(pHand, dealHand2);
		assertEquals(S, response);
	}
	@Test
	void testPlayerSoft02Dealer3() {
		System.out.println("testPlayerSoft02Dealer3");
		// soft hand w/ total 8
		BJPlayerHand pHand = new BJPlayerHand(List.of(
				new Card(HEART, 8),
				new Card(CLUB, 14) // ACE
				)); 
		int response = strategy.hit(pHand, dealHand3);
		assertEquals(S, response);
	}
	@Test
	void testPlayerSoft03Dealer4() {
		System.out.println("testPlayerSoft03Dealer4");
		// soft hand w/ total 4
		BJPlayerHand pHand = new BJPlayerHand(List.of(
				new Card(HEART, 4),
				new Card(CLUB, 14) // ACE
				)); 
		int response = strategy.hit(pHand, dealHand4);
		assertEquals(H, response);
	}
	@Test
	void testPlayerSoft04Dealer5() {
		System.out.println("testPlayerSoft04Dealer5");
		// soft hand w/ total 7
		BJPlayerHand pHand = new BJPlayerHand(List.of(
				new Card(HEART, 7),
				new Card(CLUB, 14) // ACE
				)); 
		int response = strategy.hit(pHand, dealHand5);
		assertEquals(S, response);
	}
	@Test
	void testPlayerSoft05Dealer6() {
		System.out.println("testPlayerSoft05Dealer6");
		// soft hand w/ total 3
		BJPlayerHand pHand = new BJPlayerHand(List.of(
				new Card(HEART, 3),
				new Card(CLUB, 14) // ACE
				)); 
		int response = strategy.hit(pHand, dealHand6);
		assertEquals(H, response);
	}
	@Test
	void testPlayerSoft06Dealer7() {
		System.out.println("testPlayerSoft06Dealer7");
		// soft hand w/ total 5
		BJPlayerHand pHand = new BJPlayerHand(List.of(
				new Card(HEART, 5),
				new Card(CLUB, 14) // ACE
				)); 
		int response = strategy.hit(pHand, dealHand7);
		assertEquals(H, response);
	}
	@Test
	void testPlayerSoft07Dealer8() {
		System.out.println("testPlayerSoft07Dealer8");
		// soft hand w/ total 7
		BJPlayerHand pHand = new BJPlayerHand(List.of(
				new Card(HEART, 7),
				new Card(CLUB, 14) // ACE
				)); 
		int response = strategy.hit(pHand, dealHand8);
		assertEquals(S, response);
	}
	@Test
	void testPlayerSoft08Dealer9() {
		System.out.println("testPlayerSoft08Dealer9");
		// soft hand w/ total 7
		BJPlayerHand pHand = new BJPlayerHand(List.of(
				new Card(HEART, 7),
				new Card(CLUB, 14) // ACE
				)); 
		int response = strategy.hit(pHand, dealHand9);
		assertEquals(H, response);
	}
	@Test
	void testPlayerSoft09Dealer10() {
		System.out.println("testPlayerSoft09Dealer10");
		// soft hand w/ total 8
		BJPlayerHand pHand = new BJPlayerHand(List.of(
				new Card(HEART, 8),
				new Card(CLUB, 14) // ACE
				)); 
		int response = strategy.hit(pHand, dealHand10);
		assertEquals(S, response);
	}
	@Test
	void testPlayerSoft10DealerAce() {
		System.out.println("testPlayerSoft10DealerAce");
		// soft hand w/ total 8
		BJPlayerHand pHand = new BJPlayerHand(List.of(
				new Card(HEART, 5),
				new Card(HEART, 4),
				new Card(CLUB, 14) // ACE
				)); 
		int response = strategy.hit(pHand, dealHandAce);
		assertEquals(S, response);
	}
	@Test
	void testPlayerSplit01Dealer2() {
		System.out.println("testPlayerSplit01Dealer2");
		// split hand A, A
		BJPlayerHand pHand = new BJPlayerHand(List.of(
				new Card(HEART,14), // ACE
				new Card(CLUB, 14)  // ACE
				)); 
		int response = strategy.hit(pHand, dealHand2);
		assertEquals(SP, response);
	}
	@Test
	void testPlayerSplit02Dealer3() {
		System.out.println("testPlayerSplit02Dealer3");
		// split hand 10, 10
		BJPlayerHand pHand = new BJPlayerHand(List.of(
				new Card(HEART,10),
				new Card(CLUB, 10)
				)); 
		int response = strategy.hit(pHand, dealHand3);
		assertEquals(S, response);
	}
	@Test
	void testPlayerSplit03Dealer4() {
		System.out.println("testPlayerSplit03Dealer4");
		// split hand 9, 9
		BJPlayerHand pHand = new BJPlayerHand(List.of(
				new Card(HEART, 9),
				new Card(CLUB,  9)
				)); 
		int response = strategy.hit(pHand, dealHand4);
		assertEquals(SP, response);
	}
	@Test
	void testPlayerSplit04Dealer5() {
		System.out.println("testPlayerSplit04Dealer5");
		// split hand 8, 8
		BJPlayerHand pHand = new BJPlayerHand(List.of(
				new Card(HEART, 8),
				new Card(CLUB,  8)
				)); 
		int response = strategy.hit(pHand, dealHand5);
		assertEquals(SP, response);
	}
	@Test
	void testPlayerSplit05Dealer6() {
		System.out.println("testPlayerSplit05Dealer6");
		// split hand 7, 7
		BJPlayerHand pHand = new BJPlayerHand(List.of(
				new Card(HEART, 7),
				new Card(CLUB,  7)
				)); 
		int response = strategy.hit(pHand, dealHand6);
		assertEquals(SP, response);
	}
	@Test
	void testPlayerSplit06Dealer7() {
		System.out.println("testPlayerSplit06Dealer7");
		// split hand 6, 6
		BJPlayerHand pHand = new BJPlayerHand(List.of(
				new Card(HEART, 6),
				new Card(CLUB,  6)
				)); 
		int response = strategy.hit(pHand, dealHand7);
		assertEquals(H, response);
	}
	@Test
	void testPlayerSplit07Dealer8() {
		System.out.println("testPlayerSplit07Dealer8");
		// split hand 5, 5
		BJPlayerHand pHand = new BJPlayerHand(List.of(
				new Card(HEART, 5),
				new Card(CLUB,  5)
				)); 
		int response = strategy.hit(pHand, dealHand8);
		assertEquals(H, response);
	}
	@Test
	void testPlayerSplit08Dealer9() {
		System.out.println("testPlayerSplit08Dealer9");
		// split hand 4, 4
		BJPlayerHand pHand = new BJPlayerHand(List.of(
				new Card(HEART, 4),
				new Card(CLUB,  4)
				)); 
		int response = strategy.hit(pHand, dealHand9);
		assertEquals(H, response);
	}
	@Test
	void testPlayerSplit09Dealer10() {
		System.out.println("testPlayerSplit09Dealer10");
		// split hand 3, 3
		BJPlayerHand pHand = new BJPlayerHand(List.of(
				new Card(HEART, 3),
				new Card(CLUB,  3)
				)); 
		int response = strategy.hit(pHand, dealHand10);
		assertEquals(H, response);
	}
	@Test
	void testPlayerSplit10DealerAce() {
		System.out.println("testPlayerSplit10DealerAce");
		// split hand 3, 3
		BJPlayerHand pHand = new BJPlayerHand(List.of(
				new Card(HEART, 14),
				new Card(CLUB,  14)
				)); 
		int response = strategy.hit(pHand, dealHandAce);
		assertEquals(SP, response);
	}
}
