package com.elise.klikrace;





public class Renbaan  {


	private int breedte;
	private int hoogte;
    private int binnenStraal;
	private int baanBreedte;
	
	
	public Renbaan(int breedte, int hoogte) {
		int defaultBaanBreedte = 20;
		int defaultStraal = 20;	
		init(breedte, hoogte, defaultBaanBreedte, defaultStraal);
	}
	
	public Renbaan(int breedte, int hoogte, int baanbreedte, int binnenStraal) {	
		init( breedte,  hoogte,  baanbreedte,  binnenStraal);
	}


	
	public Renbaan() {
		init(100,100,10,10);//TODO how to solve this?
	}

	private void init(int breedte, int hoogte, int baanBreedte,
			int binnenStraal) {
		this.breedte = breedte;
		this.hoogte = hoogte;
		this.baanBreedte = baanBreedte;
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
		this.baanBreedte = baanBreedte;	
	}

	
	
    public float getX(float percentage,  RenBaanBaan baan){
    	
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

    
public float getY(float percentage, RenBaanBaan baan) {
		
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
	private double getXinQuandrant(double percentage, RenBaanBaan baan) {
		if(percentage > 25){
			throw new RuntimeException("percentage  >  25 % should not happen!");
		}
		
		double result = 0;
		
		
		double hoogte = getQuadrantHoogte(baan);
		double breedte = getQuadrantBreedte(baan);
		
		
		double straal = baan.getStraal(binnenStraal, baanBreedte);		
		
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
	
	public double getYInQuadrant(double percentage, RenBaanBaan baan){
		if(percentage > 25){
			throw new RuntimeException("percentage  >  25 % should not happen!");
		}
		
		double result = 0;
		
		
		double hoogte = getQuadrantHoogte(baan);
		double breedte = getQuadrantBreedte(baan);
		double straal = baan.getStraal(binnenStraal, baanBreedte);
				
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
	
	public float getQuadrantHoogte(RenBaanBaan baan) {
		return getQuadrant(this.hoogte, baan);
	}

	private float getQuadrant(int size, RenBaanBaan baan) {	
		return  size - 2 * baan.getCorrectie() * baanBreedte;
	}

	public float getQuadrantBreedte(RenBaanBaan baan) {
		return getQuadrant(this.breedte, baan);
	}
	
	

	public float getTotaleAfstand(RenBaanBaan baan) {
		 
		float baanStraal =  baan.getStraal(binnenStraal, baanBreedte);
		float lengteHorizontaal = getQuadrantBreedte(baan) - 2 * baanStraal;
		float lengteVerticaal =  getQuadrantHoogte(baan) - 2 * baanStraal ;
		float lengteBochten = (float) (Math.PI * 2 * baanStraal);
		
		return lengteBochten + 2 * lengteHorizontaal + 2 * lengteVerticaal;
	}

	public float getStraal(RenBaanBaan baan) {
		float straal = baan.getStraal(binnenStraal, baanBreedte);
		return straal;
	}

	public void setBinnenStraal(int straal) {
		binnenStraal = straal;	
	}

	public int getBaanBreedte() {
		return baanBreedte;
	}


}
