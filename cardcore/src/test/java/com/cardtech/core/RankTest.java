package com.cardtech.core;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RankTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testEnumValues() {
		int count = 0;
		for (Rank r : Rank.values() ) {
			assertEquals(count, r.ordinal());
			count++;
		}
	}

}
