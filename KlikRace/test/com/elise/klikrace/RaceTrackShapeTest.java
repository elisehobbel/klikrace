package com.elise.klikrace;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


//creating these tests involved drawing the Track on paper 
public class RaceTrackShapeTest {

	private float delta =  1F;
	RaceTrackShape renbaan = new RaceTrackShape(300, 100, 20, 30);
	
	@Test
	public void testGetStraal() {
		
		assertEquals(40, renbaan.getStraal(RaceTrackLane.BINNENBAAN),delta);
		assertEquals(60, renbaan.getStraal(RaceTrackLane.MIDDENBAAN),delta);
		assertEquals(80, renbaan.getStraal(RaceTrackLane.BUITENBAAN),delta);
		assertEquals(90, renbaan.getStraal(RaceTrackLane.OMTREK),delta);
		
	}
	
	@Test
	public void testGetX50Percent() {
		assertEquals(150, renbaan.getX(50, RaceTrackLane.BINNENBAAN),delta);
		assertEquals(150, renbaan.getX(50, RaceTrackLane.MIDDENBAAN),delta);
		assertEquals(150, renbaan.getX(50, RaceTrackLane.BUITENBAAN),delta);
		assertEquals(150, renbaan.getX(50, RaceTrackLane.OMTREK),delta);
	}
	
	@Test
	public void testGetY50Percent() {
		float percentage = 50;
		assertEquals(50, renbaan.getY(percentage, RaceTrackLane.BINNENBAAN),delta);
		assertEquals(70, renbaan.getY(percentage, RaceTrackLane.MIDDENBAAN),delta);
		assertEquals(90, renbaan.getY(percentage, RaceTrackLane.BUITENBAAN),delta);
		assertEquals(100, renbaan.getY(percentage, RaceTrackLane.OMTREK),delta);
	}

	
	@Test
	public void testGetX38Percent() {
		float percentage = 38F;
		assertEquals(186, renbaan.getX(percentage, RaceTrackLane.BINNENBAAN),delta);
		assertEquals(200, renbaan.getX(percentage, RaceTrackLane.MIDDENBAAN),delta);
		assertEquals(214, renbaan.getX(percentage, RaceTrackLane.BUITENBAAN),delta);
		assertEquals(221, renbaan.getX(percentage, RaceTrackLane.OMTREK),delta);
	}
	
	
	@Test
	public void testGetY38Percent() {
		float percentage = 38F;
		assertEquals(50, renbaan.getY(percentage, RaceTrackLane.BINNENBAAN),delta);
		assertEquals(70, renbaan.getY(percentage, RaceTrackLane.MIDDENBAAN),delta);
		assertEquals(89, renbaan.getY(percentage, RaceTrackLane.BUITENBAAN),delta);
		assertEquals(99, renbaan.getY(percentage, RaceTrackLane.OMTREK),delta);
	}

}
