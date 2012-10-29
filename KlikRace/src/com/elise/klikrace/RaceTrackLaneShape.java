package com.elise.klikrace;




public enum RaceTrackLaneShape {
	

	OMTREK(0.0F), BUITENBAAN(0.5F ), MIDDENBAAN(1.5F),BINNENBAAN(2.5F);

	private float correctie;


	
	private RaceTrackLaneShape(float correctie){
		this.correctie = correctie;

	}
	
	public float getCorrectie() {
		return correctie;
	}

	public float getStraal(int binnenStraal, int baanBreedte) {
		System.out.println("binnenstraal: " + binnenStraal);
		System.out.println("baanBreedte: " + baanBreedte);
		return binnenStraal  + baanBreedte * (3-correctie) ;
	}

	
	
	
}
