package com.cardtech.core;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CardTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testConstructorWithValidArgs() {
		int v = 5;
		Suit s = Suit.DIAMOND;
		Card fiveDiamond = new Card(s, v);
		assertEquals(v, fiveDiamond.getValue(), "value match");
		assertEquals(s, fiveDiamond.getSuit(), "suit match");
	}
	@Test
	void testConstructorWithInvalidSuit() {
		Assertions.assertThrows(IllegalArgumentException.class, 
				() -> { new Card(null, 2);
			});
	}
	@Test
	void testConstructorWithInvalidValue() {
		Assertions.assertThrows(IllegalArgumentException.class, 
				() -> { new Card(Suit.DIAMOND, 0);
			});
	}
	@Test
	void testToString() {
		// 2:CLUB, A:HEART, 10:DIAMOND, J:SPADE
		assertEquals( "2:CLUB", new Card(Suit.CLUB,  2).toString());
		assertEquals( "3:CLUB", new Card(Suit.CLUB,  3).toString());
		assertEquals( "4:CLUB", new Card(Suit.CLUB,  4).toString());
		assertEquals( "5:CLUB", new Card(Suit.CLUB,  5).toString());
		assertEquals( "6:CLUB", new Card(Suit.CLUB,  6).toString());
		assertEquals( "7:CLUB", new Card(Suit.CLUB,  7).toString());
		assertEquals( "8:CLUB", new Card(Suit.CLUB,  8).toString());
		assertEquals( "9:CLUB", new Card(Suit.CLUB,  9).toString());
		assertEquals("10:CLUB", new Card(Suit.CLUB, 10).toString());
		assertEquals( "J:CLUB", new Card(Suit.CLUB, 11).toString());
		assertEquals( "Q:CLUB", new Card(Suit.CLUB, 12).toString());
		assertEquals( "K:CLUB", new Card(Suit.CLUB, 13).toString());
		assertEquals( "A:CLUB", new Card(Suit.CLUB, 14).toString());
		assertEquals( "A:HEART", new Card(Suit.HEART, 14).toString());
		assertEquals("10:DIAMOND", new Card(Suit.DIAMOND, 10).toString());
		assertEquals( "J:SPADE", new Card(Suit.SPADE, 11).toString());
	}
	@Test
	void testCompareTo() {
		Card fiveClub = new Card(Suit.CLUB, 5);
		Card fiveSpade = new Card(Suit.SPADE, 5);
		Card tenHeart = new Card(Suit.HEART, 10);
		Card aceDiamond = new Card(Suit.DIAMOND, 14);
		assertTrue(fiveClub.compareTo(fiveSpade) == 0);
		assertTrue(fiveSpade.compareTo(fiveClub) == 0);
		assertTrue(fiveSpade.compareTo(tenHeart)  < 0);
		assertTrue(tenHeart.compareTo(fiveSpade)  > 0);
		assertTrue(aceDiamond.compareTo(tenHeart) > 0);
	}
	@Test
	void testEquals() {
		Card fiveClub = new Card(Suit.CLUB, 5);
		Card anotherFiveClub = new Card(Suit.CLUB, 5);
		Card fiveSpade = new Card(Suit.SPADE, 5);
		String sFiveClub = "5:CLUB";
		assertTrue(fiveClub.equals(fiveClub));
		assertTrue(fiveClub.equals(anotherFiveClub));
		assertTrue(anotherFiveClub.equals(fiveClub));
		assertFalse(fiveClub.equals(fiveSpade));
		assertFalse(fiveClub.equals(sFiveClub));
	}
	@Test
	void testHashCode() {
		int expected = 17;
		expected = 31* expected + 14;
		expected = 31* expected +  3;
		assertEquals(expected, new Card(Suit.SPADE, 14).hashCode());
	}
}
