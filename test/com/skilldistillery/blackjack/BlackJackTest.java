package com.skilldistillery.blackjack;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class BlackJackTest {
	
	private static BlackjackApplication app;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		app = new BlackjackApplication();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		app = null;
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
