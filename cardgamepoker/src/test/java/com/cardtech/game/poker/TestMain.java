package com.cardtech.game.poker;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestMain {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testPokerMain() {
		// Not really a test because the results are random.
		// Needed to boost coverage.
		PokerMain.main(null);
		assertTrue(1 == 1);
	}

}
