package com.elise.klikrace;

import java.util.ArrayList;



public class Runner {
		
	private String name;
	private ArrayList<Score> scores;

	private int percentage;

	public Runner(String name) {
		this.name = name;
		this.scores = new ArrayList<Score>();
	}
	

	public ArrayList<Score> getScores() {
		return scores;
	}



	public String getName() {
		return name;
	}
	

	public void increasePercentage(int i) {
		
		percentage += i;
		if(percentage > 100){
			percentage = 100;
		}
	}

	public float getPercentage() {
		
		return percentage;
	}


	public float getTrackPercentage(int duration, Race currentRace) {
		// TODO Auto-generated method stub
		return 50;
	}


	


	public void addScore(Score score) {
		scores.add(score);
		
	}



	
	
}
