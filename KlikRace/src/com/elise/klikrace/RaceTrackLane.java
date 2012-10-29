package com.elise.klikrace;




public enum RaceTrackLane {
	

	OMTREK(0.0F), BUITENBAAN(0.5F ), MIDDENBAAN(1.5F),BINNENBAAN(2.5F);

	//nr of lane withs from the circumference(=omtrek)
	
	private float nrOfLaneWidths; 
	
	private RaceTrackLane(float correctie){
		this.nrOfLaneWidths = correctie;

	}
	
	public float getCorrectie() {
		return nrOfLaneWidths;
	}

	public float getStraal(int binnenStraal, int baanBreedte) {
		return binnenStraal  + baanBreedte * (3-nrOfLaneWidths) ;
	}

	
	
	
}
