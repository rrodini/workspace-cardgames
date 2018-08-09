package com.cardtech.game.blackjack;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cardtech.game.blackjack.BJHandValue.*;
import static com.cardtech.game.blackjack.BJHandValue.Firmness.*;
import static com.cardtech.game.blackjack.BJHandValue.Precis.*;

class TestBJHandValue {
	BJHandValue handValue;
	@BeforeEach
	void setUp() throws Exception {
		handValue = new BJHandValue(HARD_HAND, UNDER21_VALUE, 0, 13, 13);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testFirmnessEnum() {
		for (Firmness f: BJHandValue.Firmness.values()) {
			// this satisfies JUNIT
		}
	}

	@Test
	void testPrecisEnum() {
		for (Precis p: BJHandValue.Precis.values()) {
			// this satisfies JUNIT
		}
	}
	
	@Test
	void testGetFirmness() {
		assertEquals(HARD_HAND, handValue.getFirmness());
	}	
	
	@Test
	void testGetPrecis() {
		assertEquals(UNDER21_VALUE, handValue.getPrecis());
	}
	
	@Test
	void testGetAceCount() {
		assertEquals(0, handValue.getAceCount());
	}
	
	@Test
	void testGetLowValue() {
		assertEquals(13, handValue.getLowValue());
	}
	
	@Test
	void testGetHighValue() {
		assertEquals(13, handValue.getHighValue());
	}
	
	@Test
	void testToString() {
		assertEquals("BJHandValue[HARD_HAND,UNDER21_VALUE,0,13,13]", handValue.toString());
	}
	
	@Test
	void testEquals1() {
		String s = "";
		assertFalse(handValue.equals(s)); // different types
	}
	
	@Test
	void testEquals2() {
		assertTrue(handValue.equals(handValue));
	}
	
	
	@Test
	void testEquals3() {
		BJHandValue handValue1 = new BJHandValue(HARD_HAND, UNDER21_VALUE, 0, 13, 13);
		assertTrue(handValue.equals(handValue1));
	}
	
	@Test
	void testEquals4() {
		BJHandValue handValue1 = new BJHandValue(HARD_HAND, UNDER21_VALUE, 0, 14, 14);
		assertFalse(handValue.equals(handValue1));
	}

	@Test
	void testHashCode() {
		BJHandValue handValue1 = new BJHandValue(HARD_HAND, UNDER21_VALUE, 0, 14, 14);
		int i = handValue.hashCode();
		int j = handValue1.hashCode();
		assertFalse(i == j);
	}

}
