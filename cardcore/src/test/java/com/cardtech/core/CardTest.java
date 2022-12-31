package com.cardtech.core;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;

class CardTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
@Disabled("Due to Joker cards")
	@Test
	void testConstructorWithValidArgs() {
		int v = 5;
		Suit s = Suit.DIAMOND;
		Card fiveDiamond = new Card(s, v);
		assertEquals(v, fiveDiamond.getValue(), "value match");
		assertEquals(s, fiveDiamond.getSuit(), "suit match");
	}
@Disabled("Due to Joker cards")
	@Test
	void testConstructorWithInvalidSuit() {
		Assertions.assertThrows(IllegalArgumentException.class, 
				() -> { new Card(null, 2);
			});
	}
@Disabled("Due to Joker cards")
	@Test
	void testConstructorWithInvalidValue0() {
		Assertions.assertThrows(IllegalArgumentException.class, 
				() -> { new Card(Suit.DIAMOND, 0);
			});
	}
@Disabled("Due to Joker cards")
	@Test
	void testConstructorWithInvalidValue15() {
		Assertions.assertThrows(IllegalArgumentException.class, 
				() -> { new Card(Suit.DIAMOND, 15);
			});
	}
	@Test
	void testConstructorWithWildCards() {
		Card Joker1 = new Card(null, Integer.MAX_VALUE);
		Card Joker2 = new Card(null, Integer.MAX_VALUE - 1);
		assertTrue(Joker1.getValue() == Integer.MAX_VALUE);
		assertTrue(Joker2.getValue() == Integer.MAX_VALUE - 1);
	}
	@Test
	void testShow() {
		// must capture System.out printing
		final PrintStream stdOut = System.out;
		final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
		Card twoClub   = new Card(Suit.CLUB,   2);
		Card threeClub = new Card(Suit.CLUB, 3);
		Card fourClub  = new Card(Suit.CLUB,  4);
		Card fiveClub  = new Card(Suit.CLUB,  5);
		Card sixClub   = new Card(Suit.CLUB,  6);
		Card sevenClub = new Card(Suit.CLUB,  7);
		Card eightClub = new Card(Suit.CLUB,  8);
		Card nineClub  = new Card(Suit.CLUB,  9);
		Card tenClub   = new Card(Suit.CLUB, 10);
		Card jackClub  = new Card(Suit.CLUB, 11);
		Card queenClub = new Card(Suit.CLUB, 12);
		Card kingClub  = new Card(Suit.CLUB, 13);
		Card aceClub   = new Card(Suit.CLUB, 14);
		String printResult = null;
		try {
			System.setOut(new PrintStream(myOut));
			twoClub.show();
			threeClub.show();
			fourClub.show();
			fiveClub.show();
			sixClub.show();
			sevenClub.show();
			eightClub.show();
			nineClub.show();
			tenClub.show();
			jackClub.show();
			queenClub.show();
			kingClub.show();
			aceClub.show();
			printResult = myOut.toString();
			myOut.close();	
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.setOut(stdOut);
		String expectedResult = 
		 "2:CLUB " +	
		 "3:CLUB " +		
		 "4:CLUB " +		
		 "5:CLUB " +		
		 "6:CLUB " +		
		 "7:CLUB " +		
		 "8:CLUB " +		
		 "9:CLUB " +		
		"10:CLUB " +		
		 "J:CLUB " +		
		 "Q:CLUB " +		
		 "K:CLUB " +		
		 "A:CLUB " ;
		assertEquals(expectedResult, printResult);
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
	void testJokerToString() {
		// Joker1, Joker2
		assertEquals( "Joker1", new Card(null,  Integer.MAX_VALUE  ).toString());
		assertEquals( "Joker2", new Card(null,  Integer.MAX_VALUE-1).toString());
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
		Card sixClub = new Card(Suit.CLUB, 6);
		String sFiveClub = "5:CLUB";
		// reflexive
		assertTrue(fiveClub.equals(fiveClub));
		// symmetric
		assertTrue(fiveClub.equals(anotherFiveClub));
		assertTrue(anotherFiveClub.equals(fiveClub));
		// different values
		assertFalse(fiveClub.equals(fiveSpade));
		// different suits
		assertFalse(fiveClub.equals(sixClub));
		// different types
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
