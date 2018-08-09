package com.cardtech.game.blackjack;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestBJDealer {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testConstructor() {
		BJDealerStrategy strat = new BJDealerStrategy();
		BJDealer dealer = new BJDealer("bob", strat);
		assertEquals("bob", dealer.getName());
		assertTrue(dealer.getStrategy() instanceof BJDealerStrategy);
	}

}
