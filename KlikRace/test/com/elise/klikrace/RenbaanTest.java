package com.elise.klikrace;

import static org.junit.Assert.*;

import org.junit.Test;

public class RenbaanTest {

	private float delta =  0.1F;
	
	@Test
	public void test() {
		RaceTrackShape renbaan = new RaceTrackShape(300, 100, 20, 30);
		
		assertEquals(90, renbaan.getStraal(RaceTrackLaneShape.OMTREK),delta);
		
	}

}
