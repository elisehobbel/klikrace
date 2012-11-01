package com.elise.klikrace;

public class Sum {
	
	private String sumString;

	public Sum(String sumString) {
		if(!validate()){
			throw new InstantiationError("this is not a valid String for a som" + sumString);
		}
		this.sumString = sumString;
	}

	
	private boolean validate(){		
		//TODO implement
		return true;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Sum){
			if(((Sum)o).toString().equals(sumString)){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		return sumString;
	}
	
}
