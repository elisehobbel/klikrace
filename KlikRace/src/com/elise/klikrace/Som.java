package com.elise.klikrace;

public class Som {
	
	private String somString;

	public Som(String somString) {
		if(!validate()){
			throw new InstantiationError("this is not a valid String for a som" + somString);
		}
		this.somString = somString;
	}

	public String getSomString() {
		return somString;
	}
	
	private boolean validate(){		
		//TODO implement
		return true;
	}
	
	
}
