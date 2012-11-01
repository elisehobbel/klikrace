package com.elise.klikrace;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

public class Race {

	private ArrayList<Sum> sums;
	private HashSet<RaceScore> raceScores  = new HashSet<RaceScore>();
	private Sum currentSum;
	private Iterator<Sum> sumIterator;
	
	
	public Race(ArrayList<Sum> sommen) {		
		this.sums = sommen;
		this.sumIterator = sums.iterator();
		this.currentSum = sumIterator.next();
		
		//TODO add two very slow scores so you always have 2 opponents?
		//TODO what is the minimum amount of sums? 
	}

	public ArrayList<Sum> getSommen() {
		return sums;
	}

	public HashSet<RaceScore> getRaceScores() {	
		return raceScores;	
	}

	public RaceScore getLastRaceOf(Runner runner) {
		
		long highestTimeStamp = 0;
		RaceScore raceScoreValue = null;
		
		for(RaceScore raceScore: raceScores){
			long currentTimeStamp = raceScore.getTimeStamp();
			if(raceScore.getRunner() == runner &&  currentTimeStamp > highestTimeStamp){
				highestTimeStamp = currentTimeStamp;
				raceScoreValue = raceScore;
			}
		}
		
		if(highestTimeStamp == 0){
			throw new RuntimeException("no timestamp or runner found");
		}
		
		return raceScoreValue;
		
	}

	public Sum getCurrentSum() {		
		return currentSum;
	}

	public boolean hasNextSum() {
		return sumIterator.hasNext();
	}
	
	public void nextSum() { //TODO UGLY
		currentSum = sumIterator.next();
		//Log.d("currentSum", currentSum.toString());
	}
	

	
	public void addRaceScore(RaceScore raceScore){
		raceScores.add(raceScore);
	} 

	
	public RaceScore getRaceScoreFastestOpponent() {
		long maxTimePerSum = 10000;//TODO
		long fastestTime = maxTimePerSum * sums.size();
		RaceScore fastestScore = null;
		for(RaceScore raceScore:raceScores){
			long currentTime = raceScore.getTotalScore(); 
			if (currentTime < fastestTime){
				fastestTime = currentTime;
				fastestScore = raceScore;
			}
		}
		return fastestScore;
	}
	
	
	public RaceScore getRaceScoreSecondFastestOpponent() {
		return getFastestOpponent(0);		
	}

	private RaceScore getFastestOpponent(int number) {
		ArrayList<RaceScore> orderedScores = new ArrayList<RaceScore>();
		orderedScores.addAll(raceScores);
		Collections.sort(orderedScores);
		return orderedScores.get(number);
	}
	
	///DUMMIES
	
	public static Race createDummyRace(){
		ArrayList<Sum> sums = createDummySums();
		Race race =  new Race(sums);
		Runner runner = new Runner("Opponent1");
		race.addRaceScore(RaceScore.createDummyRaceScore(race, runner, 1111,2222,3333)); //6666
		Runner runner2 = new Runner("Opponent2");
		race.addRaceScore(RaceScore.createDummyRaceScore(race, runner2, 3333, 1111, 3333)); //7777
		
		return race;
	}
	
	private static ArrayList<Sum> createDummySums() {
		ArrayList<Sum> sommen = new ArrayList<Sum>();
		sommen.add(new Sum("1+8"));
		sommen.add(new Sum("4+3"));
		sommen.add(new Sum("2+3"));
		return sommen;
	}
	
}
