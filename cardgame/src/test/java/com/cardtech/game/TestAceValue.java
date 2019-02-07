package com.cardtech.game;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.cardtech.game.AceValue.ACE_IS_HIGH;
import static com.cardtech.game.AceValue.ACE_IS_LOW;

class TestAceValue {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testAceIsHigh() {
		assertEquals(14, ACE_IS_HIGH.aceValue);
	}

	@Test
	void testAceIsLow() {
		assertEquals(1, ACE_IS_LOW.aceValue);
	}
	
	@Test
	void testEnum() {
		for (AceValue ace: AceValue.values()) {
			ace.getValue();
			assertTrue( ace instanceof AceValue);
		}
	}
}
