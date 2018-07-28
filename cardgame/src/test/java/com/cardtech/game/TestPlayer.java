package com.cardtech.game;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestPlayer {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testConstructor1() {
		Player noName = new Player();
		assertEquals("nameless", noName.toString());
		assertTrue(noName != null);
		assertTrue(0 == noName.getWinCount());
	}

	@Test
	void testConstructor2() {
		Player bob = new Player("Bob");
		assertTrue(bob != null);
		assertTrue(bob.getName().equals("Bob"));
		assertTrue(bob.toString().equals("Bob"));
	}
	
	@Test
	void testIncWinCount() {
		Player joe = new Player("Joe");
		joe.incrementWinCount();
		assertTrue(joe.getWinCount() == 1);
	}
	
	@Test
	void testSetAndGetName() {
		Player noName = new Player();
		noName.setName("Robert");
		assertEquals("Robert", noName.getName());
	}
}
