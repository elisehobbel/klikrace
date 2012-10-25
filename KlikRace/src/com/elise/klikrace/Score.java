package com.elise.klikrace;

import java.util.Date;


public class Score {
	//this is a struct
	
	private Race race;
	private long time;
	private Som som;
	private Date momentPlayed;
	
	public Score(Race race, Som som, long time) {
		this.race = race;
		this.som = som;
		this.time = time;
		this.momentPlayed = new Date();
		
	}

	public Race getRace() {
		return race;
	}

	public long getTime() {
		return time;
	}

	public Som getSom() {
		return som;
	}

	public Date getMomentPlayed() {
		return momentPlayed;
	}
	
	
	
	

	
/*
	public float getTrackPercentage(int millies){
		int totalTime = getTotalTime();
		
		if(millies >= totalTime){
			return 100;
		}
		
		int currentQuestionIndex = getCurrentQuestionIndex(millies);

		float percentagePreviousQuestions = getPercentagePreviousQuestions(currentQuestionIndex);
		float percentageCurrentQuestion = getPercentageCurrentQuestion(millies)/getNrOfQuestions();
		float percentageOfTrack = percentagePreviousQuestions + percentageCurrentQuestion;
		
		return percentageOfTrack;
	}


	public float getPercentageCurrentQuestion(int currentTime) {
		int total = getTotalTime();
		if(currentTime >= total){
			return 100;
		}
		int index = getCurrentQuestionIndex(currentTime);
		float timeForCurrentQuestion = getQuestionNr(index);
		
		float timeInCurrentQuestion = currentTime - getTimePreviousQuestions(index);	
	    
		
		return timeInCurrentQuestion/timeForCurrentQuestion*100;
		
		
	}


	public float getPercentagePreviousQuestions(int currentQuestionIndex) {
		float value = 0; 
		if(getNrOfQuestions() != 0 && currentQuestionIndex != 0 ){
			value =( 100.0F/ (getNrOfQuestions() )* (currentQuestionIndex));
		}
		return value;
	}
	
	
	private int getTimePreviousQuestions(int currentQuestionIndex) {
		int cummulative = 0;
		for(int i = 0; i < currentQuestionIndex ; i++){
			cummulative += getQuestionNr(i);	
		}
		return cummulative;			
	}


	public int getCurrentQuestionIndex(int millies) {
		
		int cummulative = 0;
		int pointer = 0;
		boolean pointerset = false;
		
		for(int i = 0 ; i < getNrOfQuestions(); i++){
			cummulative += getQuestionNr(i);	
			if(cummulative > millies && pointerset == false){
				pointer = i;
				pointerset = true;
			}
		}
		
		return pointer;
	}
		
	public void addSomScore(Som som, int millies){
		this.times.put(som, millies);
	}
	*/
}
