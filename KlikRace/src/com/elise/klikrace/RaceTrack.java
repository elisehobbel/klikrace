package com.elise.klikrace;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import android.util.Log;

public class RaceTrack {

	private ArrayList<Sum> sums;
	private HashSet<RaceScore> raceScores;
	private Sum currentSum;
	private Iterator<Sum> sumIterator;
	
	
	public RaceTrack(ArrayList<Sum> sommen) {
		
		this.sums = sommen;
		this.raceScores = new HashSet<RaceScore>();
		this.sumIterator = sums.iterator();
		this.currentSum = sumIterator.next();
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
		Log.d("currentSum", currentSum.getSomString());
	}
	
}
