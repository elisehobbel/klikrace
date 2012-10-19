package com.elise.klikrace;

import static org.junit.Assert.*;

import org.junit.Test;

public class RenbaanTest {

	private float delta =  0.1F;
	
	@Test
	public void test() {
		Renbaan renbaan = new Renbaan(300, 100, 20, 30);
		
		assertEquals(20, renbaan.getStraal(RenBaanBaan.OMTREK),delta);
		
	}

}
