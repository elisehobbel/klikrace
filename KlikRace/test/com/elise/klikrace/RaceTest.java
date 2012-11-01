package com.elise.klikrace;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class RaceTest {


	private Race race;


	@Before
	public void setUp() {		
		race = Race.createDummyRace();
	}
	
	@Test
	public void testgetCurrentSum() {
		assertEquals("1+8", race.getCurrentSum().toString());	
		race.nextSum();
		assertEquals("4+3", race.getCurrentSum().toString());	
	}
	
	@Test
	public void testgetRaceScoreFastestOpponent() {
		assertEquals(6666, race.getRaceScoreFastestOpponent().getTotalScore());
	}
	
	@Test
	public void testgetRaceScoreSecondFastestOpponent() {
		assertEquals(7777, race.getRaceScoreSecondFastestOpponent().getTotalScore());
	}
	

	
}
