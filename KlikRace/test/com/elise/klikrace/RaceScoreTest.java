package com.elise.klikrace;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class RaceScoreTest {

	private float delta = 0.1F;
	private Runner runner1;
	private Race race;
	private RaceScore raceScore;

	@Before
	public void setUp() {
		ArrayList<Sum> sommen = new ArrayList<Sum>();
		Sum som1 = new Sum("1+1");
		Sum som2 = new Sum("2+2");
		
		sommen.add(som1);
		sommen.add(som2);
		race = new Race(sommen);
		runner1 = new Runner("TestRunner");
		raceScore = new RaceScore(race, runner1);
		raceScore.addScore(som1, 2000);
		raceScore.addScore(som2, 2000);
		
	}
	
	@Test
	public void testGetPercentagePrevious() {
		assertEquals(50, raceScore.getPercentagePreviousQuestions(3000),delta);	
	}
	
	@Test
	public void testGetPercentageCurrent() {
		assertEquals(50, raceScore.getPercentageCurrentQuestion(3000),delta);
	}

	@Test
	public void testGetTrackPercentage() {
		assertEquals(75, raceScore.getTrackPercentage(3000),delta);	
	}
	
	@Test
	public void testGetTotal() {
		assertEquals(4000, raceScore.getTotalScore());	
	}	
	
	

	
}
