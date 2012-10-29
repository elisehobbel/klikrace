package com.elise.klikrace;




//the shape of the track is a rectangle with rounded edges, in the future other shapes might be added
public class RaceTrackShape  {


	private int breedte;
	private int hoogte;
    private int binnenStraal;
	private int laneWidth;
	
	
	public RaceTrackShape(int breedte, int hoogte) {
		int defaultBaanBreedte = 20;
		int defaultStraal = 20;	
		init(breedte, hoogte, defaultBaanBreedte, defaultStraal);
	}
	
	public RaceTrackShape(int breedte, int hoogte, int baanbreedte, int binnenStraal) {	
		init( breedte,  hoogte,  baanbreedte,  binnenStraal);
	}

	public RaceTrackShape() {
		init(100,100,10,10);//TODO how to solve this so i do not need dummy input?
	}

	private void init(int breedte, int hoogte, int baanBreedte,
			int binnenStraal) {
		this.breedte = breedte;
		this.hoogte = hoogte;
		this.laneWidth = baanBreedte;
		this.binnenStraal = binnenStraal;
		
	}



	public int getBreedte() {
		return breedte;
	}
	
	public int getHoogte() {
		return hoogte;
	}
	
	public void setHeight(int height) {
		hoogte = height;
	}
	
	public void setWidth(int width) {
		breedte = width;
	}

	public void setStraal(int binnenStraal) {
		this.binnenStraal = binnenStraal;
		
	}

	public void setBaanBreedte(int baanBreedte) {
		this.laneWidth = baanBreedte;	
	}

	
	
    public float getX(float percentage,  RaceTrackLane baan){
    	
	  if(percentage < 0 || percentage > 100){
		  percentage = 0;
		  //TODO: solve
		 // throw new RuntimeException("the percentage is  < 0 or > 100!");
	  }
	  
	  float result = 0;
	  
	  if(percentage <= 25){
		  result = (float) getXinQuandrant(percentage, baan); 
	  }else if(percentage <= 50){
		  result = (float) getXinQuandrant(50 - percentage, baan); 
	  }else if(percentage <= 75){
		  result = -(float) getXinQuandrant(percentage - 50, baan);
	  }else{
		  result = (float) - getXinQuandrant(100 - percentage, baan);
	  }
	  
	  result += breedte/2;
	  
	  return result;
        
    }

    
public float getY(float percentage, RaceTrackLane baan) {
		
	  if(percentage < 0 || percentage > 100){
		  //TODO solve
		  percentage = 0;
		  //throw new RuntimeException("the percentage is  < 0 or > 100!");
	  }
		
		float result = 0;
		  if(percentage <= 25){
			  result = (float) getYInQuadrant(percentage, baan); 
		  }else if(percentage <= 50){
			  result = (float) - getYInQuadrant(50 - percentage, baan); 
		  }else if(percentage <= 75){
			  result = -(float) getYInQuadrant(percentage - 50, baan);
		  }else{
			  result = (float)  getYInQuadrant(100 - percentage, baan);
		  }
		
		result = hoogte/2 - result;
		  
    	return result;
	}

	
	//returns the x to be added to the middel percentage shouldbe  < 25
	private double getXinQuandrant(double percentage, RaceTrackLane baan) {
		if(percentage > 25){
			throw new RuntimeException("percentage  >  25 % should not happen!");
		}
		
		double result = 0;
		
		
		double hoogte = getQuadrantHoogte(baan);
		double breedte = getQuadrantBreedte(baan);
		
		
		double straal = baan.getStraal(binnenStraal, laneWidth);		
		
		double horizontaal = breedte/2 - straal;
		double verticaal = hoogte/2 - straal;
		double booglengte = Math.PI/2 * straal;
		
		
		double totaallengte = horizontaal + verticaal + booglengte;
	
		double afstand = percentage * totaallengte /25;
	
		
		if (afstand < horizontaal){
			result =  afstand;
		}else if(afstand > horizontaal + booglengte){
			result =  breedte/2;
		}else{
			double rad = (afstand - horizontaal)/straal;
			result =  horizontaal  + Math.cos(Math.PI/2 - rad)*straal;	
		}
		
		return result;
	}
	
	public double getYInQuadrant(double percentage, RaceTrackLane baan){
		if(percentage > 25){
			throw new RuntimeException("percentage  >  25 % should not happen!");
		}
		
		double result = 0;
		
		
		double hoogte = getQuadrantHoogte(baan);
		double breedte = getQuadrantBreedte(baan);
		double straal = baan.getStraal(binnenStraal, laneWidth);
				
		double horizontaal = breedte/2 - straal;
		double verticaal = hoogte/2 - straal;
		double booglengte = Math.PI/2 * straal;
		
		
		double totaallengte = horizontaal + verticaal + booglengte;
		
		double afstand = percentage * totaallengte /25;
		
		if (afstand < horizontaal){
			result =  hoogte/2;
		}else if(afstand > horizontaal + booglengte){
			result =  totaallengte - afstand;
		}else{
			double rad = (afstand - horizontaal)/straal;
			result =  verticaal + Math.sin(Math.PI/2 - rad)*straal;	
		}
		
		return result;
	}
	
	public float getQuadrantHoogte(RaceTrackLane baan) {
		return getQuadrant(this.hoogte, baan);
	}

	private float getQuadrant(int size, RaceTrackLane baan) {	
		return  size - 2 * baan.getCorrectie() * laneWidth;
	}

	public float getQuadrantBreedte(RaceTrackLane baan) {
		return getQuadrant(this.breedte, baan);
	}

	public float getTotaleAfstand(RaceTrackLane baan) {
		 
		float baanStraal =  baan.getStraal(binnenStraal, laneWidth);
		float lengteHorizontaal = getQuadrantBreedte(baan) - 2 * baanStraal;
		float lengteVerticaal =  getQuadrantHoogte(baan) - 2 * baanStraal ;
		float lengteBochten = (float) (Math.PI * 2 * baanStraal);
		
		return lengteBochten + 2 * lengteHorizontaal + 2 * lengteVerticaal;
	}

	public float getStraal(RaceTrackLane baan) {
		float straal = baan.getStraal(binnenStraal, laneWidth);
		return straal;
	}

	public void setBinnenStraal(int straal) {
		binnenStraal = straal;	
	}

	public int getLaneWidth() {
		return laneWidth;
	}


}
