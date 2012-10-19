package com.elise.klikrace;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class ScoreTest {


	private Runner runner1;
	private Race race;
	private Score score;
	private double delta = 0.1F;

	@Before
	public void setUp() throws Exception {
		
		HashMap<Som,Integer> times = new HashMap<Som, Integer>();
		times.put(new Som("1+3"),2000);
		times.put(new Som("2+3"),800);
		times.put(new Som("8+3"),1200);
		
		race = new Race();
	    runner1 = new Runner("runner1");
	    score = new Score(race,runner1, times);
	   
	    
	}
	
	@Test
	public void testGetTrackPercentage() {		
		assertEquals(41.6, score.getTrackPercentage(2200), delta );
	}
	
	@Test
	public void testGetTrackPercentageBeforeEndTime() {		
		assertEquals(97.2, score.getTrackPercentage(3900), delta );
	}
	
	@Test
	public void testGetTrackPercentageEndTime() {		
		assertEquals(100, score.getTrackPercentage(4000), delta );
	}
	
	@Test
	public void testGetTrackPercentageAfterEndTime() {		
		assertEquals(100, score.getTrackPercentage(5000), delta );
	}
	
	@Test
	public void testGetCurrentQuestionIndex() {		
		assertEquals(1, score.getCurrentQuestionIndex(2200));
	}
	
	@Test
	public void testGetPercentageCurrentQuestion() {		
		assertEquals(25, score.getPercentageCurrentQuestion(2200),delta);
	}


}
