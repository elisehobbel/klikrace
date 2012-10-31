package com.elise.klikrace;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RaceScore {
	//
	
	private RaceTrack race;
	
	private Runner runner;
	
	private long timeStamp;
	
	private Map<Sum, Integer> scores;

	public RaceScore(RaceTrack race, Runner runner) {
		this.race = race;
		this.runner = runner;
		this.scores = new HashMap<Sum, Integer>();
	}

	public RaceTrack getRace() {
		return race;
	}

	public Runner getRunner() {
		return runner;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public Map<Sum, Integer> getScores() {
		return scores;
	}

	public void addScore(Sum currentSom, Integer scoreTime) {
		scores.put(currentSom, scoreTime);
	}


	public float getPercentagePreviousQuestions(int msPassed) {
		
		if(scores.isEmpty()){
			return 0;
		}
		
		ArrayList<Sum> sommen = race.getSommen();

		int somIndex = getSomIndex(msPassed, sommen);		
		float percentage = (float)somIndex/sommen.size()*100;
	
		return percentage;
	}

	private int getSomIndex(int msPassed, ArrayList<Sum> sommen) {
		
		if(scores.size() == 0 || scores == null){
			throw new RuntimeException("there are no scores for runner: " + runner.getName());
		}
		
		
		int cummulative = 0;
		int somIndex = 0;
		
		for(Sum som :sommen){
			Integer somTime = scores.get(som);
			
			cummulative += somTime;
			if(msPassed > cummulative){
				somIndex += 1;
			}
		}
		
		
		return somIndex;
	}

	

	public float getTrackPercentage(int msPassed) {
		if(msPassed > getTotalScore()){ //TODO, betere oplossing
			return 100;
		}
		
		return  getPercentagePreviousQuestions(msPassed) + getPercentageCurrentQuestion(msPassed)/race.getSommen().size();
	}

	public float getPercentageCurrentQuestion(int msPassed) {
		int totalScore = getTotalScore();
		
		if(msPassed > totalScore){
			msPassed = totalScore;
		}
		
		ArrayList<Sum> sommen = race.getSommen();
		
		int somIndex = getSomIndex(msPassed, sommen);
		
		Sum currentSom = race.getSommen().get(somIndex);
		int scoreCurrentSom = scores.get(currentSom);
		int timePreviousSommen = getTimePreviousSommen(somIndex);
		int timeInCurrentSom = msPassed - timePreviousSommen;

		float percentageInCurrent = (float)timeInCurrentSom/(float)scoreCurrentSom* 100;	
		return  percentageInCurrent;
	}

	private int getTotalScore() {
		int total = 0;
		for(Integer val:scores.values()){
			total += val;
		}
		return total;
	}

	private int getTimePreviousSommen(int somIndex) {
		int total = 0;
		
		for(int i = 0; i < somIndex; i++){
			Sum som = race.getSommen().get(i);
			total += scores.get(som);
		}
		
		return total;
	}
	
	

}
